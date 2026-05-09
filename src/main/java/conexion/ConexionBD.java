package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase utilitaria para gestionar la conexión a la base de datos.
 * Proporciona una conexión nueva por cada llamada, lo que evita
 * problemas de conexiones cerradas o compartidas entre operaciones.
 * Uso recomendado con try-with-resources:
 *   try (Connection con = ConexionBD.getConnection()) { ... }
 *
 * @author Daniel Lagares Paz
 * @since 21/04/2026
 */
public class ConexionBD {

    private static final String URL      = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER     = "postgres";
    private static final String PASSWORD = "";

    // Constructor privado: evita que se instancie la clase con 'new'
    private ConexionBD() {}

    /**
     * Devuelve una conexión nueva a la base de datos.
     * Cada llamada abre una conexión independiente.
     * El llamador es responsable de cerrarla (idealmente con try-with-resources).
     *
     * @return Connection lista para usar.
     * @throws SQLException si no se puede establecer la conexión.
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver de PostgreSQL no encontrado en el classpath.", e);
        }
    }
}