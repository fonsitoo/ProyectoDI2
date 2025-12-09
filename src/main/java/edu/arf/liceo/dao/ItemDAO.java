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
}