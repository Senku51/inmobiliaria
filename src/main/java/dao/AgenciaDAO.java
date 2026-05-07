package dao;
import modelo.Agencia;
import java.util.List;

/**
 * Proyecto:
 *
 * @author Daniel Lagares Paz
 * @since 21/04/2026
 */
public interface AgenciaDAO {

    // CREATE: Dar de alta una nueva agencia
    void crearTabla();
    void insertar(Agencia agencia);

    // READ: Consultar listado completo
    List<Agencia> obtenerTodas();

    // READ: Buscar por dirección o zona de actuación
    List<Agencia> buscarPorDireccion(String direccion);
    Agencia buscarPorZona(String zona); // Zona es única según tu modelo

    // UPDATE: Modificar datos existentes
    void actualizar(Agencia agencia);

    // DELETE: Eliminar (La implementación deberá validar si tiene empleados/inmuebles)
    void eliminar(String zonaActuacion);

    // Métodos adicionales para los teléfonos
    void agregarTelefono(String zonaActuacion, String telefono);
    void eliminarTelefono(String zonaActuacion, String telefono);
}
