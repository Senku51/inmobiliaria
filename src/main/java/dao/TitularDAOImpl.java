package dao;

import conexion.ConexionBD;
import modelo.Titular;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Proyecto:
 *
 * @author Daniel Lagares Paz
 * @since 21/04/2026
 */
public class TitularDAOImpl implements TitularDAO {

    // Consultas SQL
    private static final String INSERT_TITULAR = "INSERT INTO titular (codigo_empleado, nombre, telefono, fecha_nacimiento, titulacion, zona_agencia) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM titular";
    private static final String SELECT_BY_CODIGO = "SELECT * FROM titular WHERE codigo_empleado = ?";
    private static final String SELECT_BY_NOMBRE = "SELECT * FROM titular WHERE nombre ILIKE ?";
    private static final String SELECT_BY_AGENCIA = "SELECT * FROM titular WHERE zona_agencia = ?";
    private static final String UPDATE_TITULAR = "UPDATE titular SET nombre = ?, telefono = ?, fecha_nacimiento = ?, titulacion = ? WHERE codigo_empleado = ?";
    private static final String DELETE_TITULAR = "DELETE FROM titular WHERE codigo_empleado = ?";
    
    @Override
    public void crearTabla() {
        String sqlTitular = "CREATE TABLE IF NOT EXISTS titular (" +
                "codigo_empleado VARCHAR(50) PRIMARY KEY, " +
                "nombre VARCHAR(150) NOT NULL, " +
                "telefono VARCHAR(20), " +
                "fecha_nacimiento DATE, " +
                "titulacion VARCHAR(100), " +
                "zona_agencia VARCHAR(100) UNIQUE, " + // UNIQUE porque el titular es propio de una agencia
                "FOREIGN KEY (zona_agencia) REFERENCES agencia(zona_actuacion) ON DELETE SET NULL)";

        try (Connection con = ConexionBD.getConnection();
             Statement st = con.createStatement()) {

            st.execute(sqlTitular);
            System.out.println("Tabla de Titular verificada/creada.");

        } catch (SQLException e) {
            System.out.println("Error al crear la tabla de Titular: " + e.getMessage());
        }
    }
    @Override
    public void insertar(Titular titular, String zonaAgencia) {
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement ps = con.prepareStatement(INSERT_TITULAR);
            ps.setString(1, titular.getCodigoEmpleado());
            ps.setString(2, titular.getNombre());
            ps.setString(3, titular.getTelefono());
            // Conversión de LocalDate a java.sql.Date
            ps.setDate(4, Date.valueOf(titular.getFechaNacimiento()));
            ps.setString(5, titular.getTitulacion());
            ps.setString(6, zonaAgencia);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar titular: " + e.getMessage());
        }
    }

    @Override
    public Titular buscarPorCodigo(String codigoEmpleado) {
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement ps = con.prepareStatement(SELECT_BY_CODIGO);
            ps.setString(1, codigoEmpleado);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearTitular(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar titular por código: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Titular> buscarPorNombre(String nombre) {
        List<Titular> titulares = new ArrayList<>();
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement ps = con.prepareStatement(SELECT_BY_NOMBRE);
            ps.setString(1, "%" + nombre + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    titulares.add(mapearTitular(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar titular por nombre: " + e.getMessage());
        }
        return titulares;
    }

    @Override
    public Titular buscarPorAgencia(String zonaAgencia) {
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement ps = con.prepareStatement(SELECT_BY_AGENCIA);
            ps.setString(1, zonaAgencia);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearTitular(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar titular por agencia: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Titular> obtenerTodos() {
        List<Titular> titulares = new ArrayList<>();
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement ps = con.prepareStatement(SELECT_ALL);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    titulares.add(mapearTitular(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todos los titulares: " + e.getMessage());
        }
        return titulares;
    }

    @Override
    public void actualizar(Titular titular) {
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement ps = con.prepareStatement(UPDATE_TITULAR);
            ps.setString(1, titular.getNombre());
            ps.setString(2, titular.getTelefono());
            ps.setDate(3, Date.valueOf(titular.getFechaNacimiento()));
            ps.setString(4, titular.getTitulacion());
            ps.setString(5, titular.getCodigoEmpleado());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar titular: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String codigoEmpleado) {
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement ps = con.prepareStatement(DELETE_TITULAR);
            ps.setString(1, codigoEmpleado);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar titular: " + e.getMessage());
        }
    }

    private Titular mapearTitular(ResultSet rs) throws SQLException {
        return new Titular(
                rs.getString("codigo_empleado"),
                rs.getString("nombre"),
                rs.getString("telefono"),
                rs.getDate("fecha_nacimiento").toLocalDate(), // Conversión inversa
                rs.getString("titulacion")
        );
    }
}
