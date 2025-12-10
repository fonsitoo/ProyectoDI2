package edu.arf.liceo.dao;

import edu.arf.liceo.model.Usuario;
import edu.arf.liceo.utils.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario validarLogin(String nickname, String password) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE nickname = ? AND password = ?";

        try {
            Connection connection = ConexionBD.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nickname);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(resultSet.getInt("id_usuario"));
                usuario.setNickname(resultSet.getString("nickname"));
                usuario.setPassword(resultSet.getString("password"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setSaldo(resultSet.getDouble("saldo"));
                usuario.setEsAdmin(resultSet.getBoolean("es_admin"));
            }

            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            System.err.println("Error en el login: " + e.getMessage());
            e.printStackTrace();
        }

        return usuario;
    }
}