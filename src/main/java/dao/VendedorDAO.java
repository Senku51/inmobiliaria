package dao;

import java.util.List;

import modelo.Agencia;
import modelo.Vendedor;

/**
 * Interfaz para las operaciones de persistencia de la clase Vendedor.
 */
public interface VendedorDAO {
    void crearTabla();
    boolean insertar(Vendedor vendedor);
    boolean actualizar(Vendedor vendedor);
    boolean eliminar(String codEmpleado);
    Vendedor buscarPorCodigo(String codEmpleado);
    List<Vendedor> listarTodos();
    List<Vendedor> buscarPorAgencia(String nombreAgencia);

    List<Vendedor> buscarPorAgencia(Agencia agencia);
}