package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase Singleton para gestionar una única conexión a la base de datos.
 */
public class ConexionBD {

    private static final String URL      = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER     = "postgres";
    private static final String PASSWORD = "";

    // Única instancia de la conexión
    private static Connection connection = null;

    // Constructor privado para evitar instanciación externa
    private ConexionBD() {}

    /**
     * Devuelve la instancia única de la conexión.
     * Si no existe o está cerrada, la crea.
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.err.println("Error al conectar: " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }

    /**
     * Método para cerrar la conexión permanentemente cuando la app termine.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}