package dao;

import conexion.ConexionBD;
import modelo.Agencia;
import modelo.Titular;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación completa del DAO para Agencia con persistencia en Base de Datos.
 * Incluye gestión de teléfonos en tabla auxiliar.
 */
public class AgenciaDAOImpl implements AgenciaDAO {

    private static final String INSERT_AGENCIA = "INSERT INTO agencia (direccion, fax, zona_actuacion) VALUES (?, ?, ?)";
    private static final String INSERT_TELEFONO = "INSERT INTO agencia_telefonos (zona_agencia, telefono) VALUES (?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM agencia";
    private static final String SELECT_BY_DIR = "SELECT * FROM agencia WHERE direccion ILIKE ?";
    private static final String SELECT_BY_ZONA = "SELECT * FROM agencia WHERE zona_actuacion = ?";
    private static final String UPDATE_AGENCIA = "UPDATE agencia SET direccion = ?, fax = ? WHERE zona_actuacion = ?";
    private static final String DELETE_AGENCIA = "DELETE FROM agencia WHERE zona_actuacion = ?";
    private static final String DELETE_TEL_ESPECIFICO = "DELETE FROM agencia_telefonos WHERE zona_agencia = ? AND telefono = ?";

    @Override
    public void crearTabla() {
        String sqlAgencia = "CREATE TABLE IF NOT EXISTS agencia (" +
                "zona_actuacion VARCHAR(100) PRIMARY KEY, " +
                "direccion VARCHAR(255) NOT NULL, " +
                "fax VARCHAR(50))";

        String sqlTelefonos = "CREATE TABLE IF NOT EXISTS agencia_telefonos (" +
                "zona_agencia VARCHAR(100), " +
                "telefono VARCHAR(20), " +
                "PRIMARY KEY (zona_agencia, telefono), " +
                "FOREIGN KEY (zona_agencia) REFERENCES agencia(zona_actuacion) ON DELETE CASCADE)";

        try (Connection con = ConexionBD.getConnection();
             Statement st = con.createStatement()) {
            st.execute(sqlAgencia);
            st.execute(sqlTelefonos);
            System.out.println("Tablas de Agencia y Teléfonos verificadas.");
        } catch (SQLException e) {
            System.err.println("Error al crear las tablas: " + e.getMessage());
        }
    }

    @Override
    public void insertar(Agencia agencia) {
        try (Connection con = ConexionBD.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement(INSERT_AGENCIA)) {
                ps.setString(1, agencia.getDireccion());
                ps.setString(2, agencia.getFax());
                ps.setString(3, agencia.getZonaActuacion());
                ps.executeUpdate();

                for (String tel : agencia.getTelefono()) {
                    try (PreparedStatement psTel = con.prepareStatement(INSERT_TELEFONO)) {
                        psTel.setString(1, agencia.getZonaActuacion());
                        psTel.setString(2, tel);
                        psTel.executeUpdate();
                    }
                }
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar agencia: " + e.getMessage());
        }
    }

    @Override
    public List<Agencia> obtenerTodas() {
        List<Agencia> agencias = new ArrayList<>();
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                agencias.add(mapearAgencia(rs, con));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todas las agencias: " + e.getMessage());
        }
        return agencias;
    }

    @Override
    public List<Agencia> buscarPorDireccion(String dir) {
        List<Agencia> agencias = new ArrayList<>();
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_DIR)) {
            ps.setString(1, "%" + dir + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    agencias.add(mapearAgencia(rs, con));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar por dirección: " + e.getMessage());
        }
        return agencias;
    }

    @Override
    public Agencia buscarPorZona(String zona) {
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ZONA)) {
            ps.setString(1, zona);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearAgencia(rs, con);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar por zona: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(Agencia agencia) {
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_AGENCIA)) {
            ps.setString(1, agencia.getDireccion());
            ps.setString(2, agencia.getFax());
            ps.setString(3, agencia.getZonaActuacion());
            ps.executeUpdate();
            System.out.println("Datos de la agencia actualizados.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar agencia: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String zonaActuacion) {
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_AGENCIA)) {
            ps.setString(1, zonaActuacion);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Agencia eliminada correctamente.");
            } else {
                System.out.println("No se encontró ninguna agencia en esa zona.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
        }
    }

    @Override
    public void agregarTelefono(String zona, String tel) {
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_TELEFONO)) {
            ps.setString(1, zona);
            ps.setString(2, tel);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar teléfono (¿Ya existe o no existe la agencia?): " + e.getMessage());
        }
    }

    @Override
    public void eliminarTelefono(String zona, String tel) {
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_TEL_ESPECIFICO)) {
            ps.setString(1, zona);
            ps.setString(2, tel);
            ps.executeUpdate();
            System.out.println("Teléfono eliminado.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar teléfono: " + e.getMessage());
        }
    }

    private Agencia mapearAgencia(ResultSet rs, Connection con) throws SQLException {
        Agencia agencia = new Agencia(
                rs.getString("direccion"),
                rs.getString("fax"),
                rs.getString("zona_actuacion")
        );

        // Cargamos los teléfonos usando la conexión existente para evitar el error "Connection closed"
        String sqlTelefonos = "SELECT telefono FROM agencia_telefonos WHERE zona_agencia = ?";
        try (PreparedStatement psTel = con.prepareStatement(sqlTelefonos)) {
            psTel.setString(1, agencia.getZonaActuacion());
            try (ResultSet rsTel = psTel.executeQuery()) {
                while (rsTel.next()) {
                    agencia.agregarTelefono(rsTel.getString("telefono"));
                }
            }
        }
        return agencia;
    }
}