package dao;

import conexion.ConexionBD;
import modelo.Inmueble;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la clase Inmueble.
 * Maneja la persistencia de los datos comunes de todos los inmuebles.
 * Realizado por Carlos Martin Martin
 */
public class InmuebleDAOImpl implements InmuebleDAO {

    // Consultas SQL
    private static final String INSERT_INMUEBLE = "INSERT INTO inmueble (codigo, propietario, direccion, superficie, en_alquiler, precio_alquiler, fianza, en_venta, precio_venta, hipotecado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM inmueble";
    private static final String SELECT_BY_CODIGO = "SELECT * FROM inmueble WHERE codigo = ?";
    private static final String UPDATE_INMUEBLE = "UPDATE inmueble SET propietario = ?, direccion = ?, superficie = ?, en_alquiler = ?, precio_alquiler = ?, fianza = ?, en_venta = ?, precio_venta = ?, hipotecado = ? WHERE codigo = ?";
    private static final String DELETE_INMUEBLE = "DELETE FROM inmueble WHERE codigo = ?";

    @Override
    public void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS inmueble (" +
                "codigo VARCHAR(50) PRIMARY KEY, " +
                "propietario VARCHAR(150) NOT NULL, " +
                "direccion VARCHAR(255), " +
                "superficie DOUBLE PRECISION, " +
                "en_alquiler BOOLEAN, " +
                "precio_alquiler DOUBLE PRECISION, " +
                "fianza DOUBLE PRECISION, " +
                "en_venta BOOLEAN, " +
                "precio_venta DOUBLE PRECISION, " +
                "hipotecado BOOLEAN)";

        try (Connection con = ConexionBD.getConnection();
             Statement st = con.createStatement()) {
            st.execute(sql);
            System.out.println("Tabla Inmueble verificada/creada.");
        } catch (SQLException e) {
            System.err.println("Error al crear tabla inmueble: " + e.getMessage());
        }
    }

    @Override
    public void insertar(Inmueble inmueble) {
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_INMUEBLE)) {

            ps.setString(1, inmueble.getCodigo());
            ps.setString(2, inmueble.getPropietario());
            ps.setString(3, inmueble.getDireccion());
            ps.setDouble(4, inmueble.getSuperficie());
            ps.setBoolean(5, inmueble.isEnAlquiler());
            ps.setDouble(6, inmueble.getPrecioAlquiler());
            ps.setDouble(7, inmueble.getFianza());
            ps.setBoolean(8, inmueble.isEnVenta());
            ps.setDouble(9, inmueble.getPrecioVenta());
            ps.setBoolean(10, inmueble.isHipotecado());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar inmueble: " + e.getMessage());
        }
    }

    @Override
    public Inmueble buscarPorCodigo(String codigo) {
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_CODIGO)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    return null;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar inmueble: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Inmueble> obtenerTodos() {
        List<Inmueble> lista = new ArrayList<>();
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // Lógica para añadir a la lista según el tipo
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los inmuebles: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Inmueble inmueble) {
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_INMUEBLE)) {

            ps.setString(1, inmueble.getPropietario());
            ps.setString(2, inmueble.getDireccion());
            ps.setDouble(3, inmueble.getSuperficie());
            ps.setBoolean(4, inmueble.isEnAlquiler());
            ps.setDouble(5, inmueble.getPrecioAlquiler());
            ps.setDouble(6, inmueble.getFianza());
            ps.setBoolean(7, inmueble.isEnVenta());
            ps.setDouble(8, inmueble.getPrecioVenta());
            ps.setBoolean(9, inmueble.isHipotecado());
            ps.setString(10, inmueble.getCodigo());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar inmueble: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String codigo) {
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_INMUEBLE)) {
            ps.setString(1, codigo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar inmueble: " + e.getMessage());
        }
    }
}