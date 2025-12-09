package edu.arf.liceo.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionbd {

    private static conexionbd instance;
    private Connection connection;

    private final String URL = "jdbc:mysql://localhost:3306/marketplacesteam_db";
    private final String USER = "root";
    private final String PASSWORD = "";

    private conexionbd() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static conexionbd getInstance() {
        if (instance == null) {
            instance = new conexionbd();
        } else {
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new conexionbd();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}