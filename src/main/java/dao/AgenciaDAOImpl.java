package dao;

import conexion.ConexionBD;
import modelo.Agencia;
import modelo.Titular;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Proyecto:
 *
 * @author Daniel Lagares Paz
 * @since 21/04/2026
 */
public class AgenciaDAOImpl implements AgenciaDAO {

    // Consultas SQL basadas en tu modelo
    private static final String INSERT_AGENCIA = "INSERT INTO agencia (direccion, fax, zona_actuacion) VALUES (?, ?, ?)";
    private static final String INSERT_TELEFONO = "INSERT INTO agencia_telefonos (zona_agencia, telefono) VALUES (?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM agencia";
    private static final String SELECT_BY_DIR = "SELECT * FROM agencia WHERE direccion ILIKE ?";
    private static final String SELECT_BY_ZONA = "SELECT * FROM agencia WHERE zona_actuacion = ?";
    private static final String SELECT_TELS = "SELECT telefono FROM agencia_telefonos WHERE zona_agencia = ?";
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
            System.out.println("Tablas de Agencia y Teléfonos verificadas/creadas.");

        } catch (SQLException e) {
            System.out.println("Error al crear las tablas de Agencia: " + e.getMessage());
        }
    }

    @Override
    public void insertar(Agencia agencia) {
        try (Connection con = ConexionBD.getConnection()) {
            // Insertamos los datos básicos
            PreparedStatement ps = con.prepareStatement(INSERT_AGENCIA);
            ps.setString(1, agencia.getDireccion());
            ps.setString(2, agencia.getFax());
            ps.setString(3, agencia.getZonaActuacion());
            ps.executeUpdate();

            // Insertamos su lista inicial de teléfonos si existen
            for (String tel : agencia.getTelefono()) {
                agregarTelefono(agencia.getZonaActuacion(), tel);
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar agencia: " + e.getMessage());
        }
    }

    @Override
    public List<Agencia> obtenerTodas() {
        List<Agencia> agencias = new ArrayList<>();
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement ps = con.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                agencias.add(mapearAgencia(rs, con));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todas las agencias: " + e.getMessage());
        }
        return agencias;
    }

    @Override
    public List<Agencia> buscarPorDireccion(String direccion) {
        List<Agencia> agencias = new ArrayList<>();
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement ps = con.prepareStatement(SELECT_BY_DIR);
            ps.setString(1, "%" + direccion + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                agencias.add(mapearAgencia(rs, con));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar por dirección: " + e.getMessage());
        }
        return agencias;
    }

    @Override
    public Agencia buscarPorZona(String zona) {
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement ps = con.prepareStatement(SELECT_BY_ZONA);
            ps.setString(1, zona);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapearAgencia(rs, con);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar por zona: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(Agencia agencia) {
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement ps = con.prepareStatement(UPDATE_AGENCIA);
            ps.setString(1, agencia.getDireccion());
            ps.setString(2, agencia.getFax());
            ps.setString(3, agencia.getZonaActuacion());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar agencia: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String zonaActuacion) {
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement ps = con.prepareStatement(DELETE_AGENCIA);
            ps.setString(1, zonaActuacion);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar agencia (compruebe integridad): " + e.getMessage());
        }
    }

    @Override
    public void agregarTelefono(String zonaActuacion, String telefono) {
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement ps = con.prepareStatement(INSERT_TELEFONO);
            ps.setString(1, zonaActuacion);
            ps.setString(2, telefono);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar teléfono: " + e.getMessage());
        }
    }

    @Override
    public void eliminarTelefono(String zonaActuacion, String telefono) {
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement ps = con.prepareStatement(DELETE_TEL_ESPECIFICO);
            ps.setString(1, zonaActuacion);
            ps.setString(2, telefono);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar teléfono: " + e.getMessage());
        }
    }

    private Agencia mapearAgencia(ResultSet rs, Connection con) throws SQLException {
        // 1. Instanciamos la Agencia con los datos básicos que vienen en el ResultSet
        Agencia agencia = new Agencia(
                rs.getString("direccion"),
                rs.getString("fax"),
                rs.getString("zona_actuacion")
        );

        // 2. Cargamos el Titular asociado
        TitularDAO titularDao = new TitularDAOImpl();
        Titular titularAsociado = titularDao.buscarPorAgencia(agencia.getZonaActuacion());

        agencia.setTitular(titularAsociado);

        // 3. Cargamos la lista de teléfonos desde la tabla auxiliar
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