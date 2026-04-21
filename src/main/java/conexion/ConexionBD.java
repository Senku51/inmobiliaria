package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "";

    // 1. Atributo estático que guardará la única conexión
    private static Connection connection = null;

    // 2. Constructor privado: evita que se instancie la clase con 'new'
    private ConexionBD() {}

    /**
     * Implementación Singleton
     */
    public static Connection getConnection() throws SQLException {
        // 3. Verificamos si la conexión ya existe o si está cerrada
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión creada por primera vez.");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver no encontrado", e);
            }
        }
        // 4. Retornamos la conexión existente
        return connection;
    }
}