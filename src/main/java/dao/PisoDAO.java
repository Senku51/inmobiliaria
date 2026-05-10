package dao;

import java.util.List;
import modelo.Piso;

/**
 * Interfaz para las operaciones de persistencia de la clase Piso.
 * Realizado por Carlos Martin Martin
 */
public interface PisoDAO {
    void crearTabla();
    boolean insertar(Piso piso);
    boolean actualizar(Piso piso);
    boolean eliminar(String codigo);
    Piso buscarPorCodigo(String codigo);
    List<Piso> listarTodos();
    List<Piso> listarExteriores(); // Ejemplo de filtro específico
}