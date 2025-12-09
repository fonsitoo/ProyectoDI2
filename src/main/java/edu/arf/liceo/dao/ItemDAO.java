package edu.arf.liceo.dao;

import edu.arf.liceo.model.Item;
import edu.arf.liceo.utils.conexionbd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    public List<Item> listarItems() {
        List<Item> lista = new ArrayList<>();
        String sql = "SELECT * FROM item";

        try {
            Connection con = conexionbd.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item();
                item.setIdItem(rs.getInt("id_item"));
                item.setNombre(rs.getString("nombre"));
                item.setDescripcion(rs.getString("descripcion"));
                item.setPrecio(rs.getDouble("precio"));
                item.setFloatValue(rs.getDouble("float_value"));

                if (rs.getDate("fecha_publicacion") != null) {
                    item.setFechaPublicacion(rs.getDate("fecha_publicacion").toLocalDate());
                }

                item.setIdUsuario(rs.getInt("id_usuario"));
                item.setIdJuego(rs.getInt("id_juego"));

                lista.add(item);
            }

            ps.close();
            rs.close();

        } catch (SQLException e) {
            System.err.println("Error al listar items: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
    public boolean insertarItem(Item item) {
        String sql = "INSERT INTO item (nombre, descripcion, precio, float_value, fecha_publicacion, id_usuario, id_juego) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection con = conexionbd.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, item.getNombre());
            ps.setString(2, item.getDescripcion());
            ps.setDouble(3, item.getPrecio());
            ps.setDouble(4, item.getFloatValue());
            ps.setDate(5, java.sql.Date.valueOf(item.getFechaPublicacion()));
            ps.setInt(6, item.getIdUsuario());
            ps.setInt(7, item.getIdJuego());

            int filasAfectadas = ps.executeUpdate();
            ps.close();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar item: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarItem(int idItem) {
        String sql = "DELETE FROM item WHERE id_item = ?";

        try {
            Connection con = conexionbd.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idItem);

            int filasAfectadas = ps.executeUpdate();
            ps.close();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar item: " + e.getMessage());
            return false;
        }
    }
}