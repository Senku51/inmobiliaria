package conexion;

import java.sql.*;

public class ConexionDB {
    private static Connection conexion;

    private ConexionDB() {
    }

    public static Connection getConexion() {
        String url = "jdbc:sqlite:inmobiliaria.db";

        try (Connection connection = DriverManager.getConnection(url)) {

            if (connection != null || !connection.isClosed()) {
                System.out.println("Conexión SQLite realizada correctamente.");
                conexion = connection;
            }

        } catch (Exception e) {
            System.err.println("Error en la conexión con SQLite.");
            e.printStackTrace();
        }
        return conexion;
    }
}





