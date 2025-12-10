package edu.arf.liceo.dao;

import edu.arf.liceo.model.Categoria;
import edu.arf.liceo.utils.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public List<Categoria> listarTodas() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria";

        try {
            Connection con = ConexionBD.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Categoria(rs.getInt("id_categoria"), rs.getString("nombre")));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void guardarRelacionCategorias(int idItem, List<Categoria> categoriasSeleccionadas) {
        String sql = "INSERT INTO item_categoria (id_item, id_categoria) VALUES (?, ?)";

        try {
            Connection con = ConexionBD.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            for (Categoria cat : categoriasSeleccionadas) {
                ps.setInt(1, idItem);
                ps.setInt(2, cat.getIdCategoria());
                ps.addBatch();
            }

            ps.executeBatch();
            ps.close();

        } catch (SQLException e) {
            System.err.println("Error al asociar categorías: " + e.getMessage());
        }
    }

    public int obtenerUltimoIdItem() {
        int id = 0;
        String sql = "SELECT MAX(id_item) AS last_id FROM item";
        try {
            Connection con = ConexionBD.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("last_id");
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}