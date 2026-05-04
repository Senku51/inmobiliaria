package dao;
import modelo.Titular;
import java.util.List;

/**
 * Proyecto:
 *
 * @author Daniel Lagares Paz
 * @since 21/04/2026
 */
public interface TitularDAO {

    // 3.2 CREATE: Registrar y asociar a agencia
    void crearTabla();
    void insertar(Titular titular, String zonaAgencia);

    // 3.2 READ: Consultar y filtrar
    Titular buscarPorCodigo(String codigoEmpleado);
    List<Titular> buscarPorNombre(String nombre);
    Titular buscarPorAgencia(String zonaAgencia);
    List<Titular> obtenerTodos();

    // 3.2 UPDATE: Modificar datos
    void actualizar(Titular titular);

    // 3.2 DELETE: Eliminar del sistema
    void eliminar(String codigoEmpleado);
}