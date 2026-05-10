package dao;

import java.util.List;
import modelo.LocalComercial;

/**
 * Interfaz que define las operaciones de persistencia para Locales Comerciales.
 * Realizado por Carlos Martin Martin
 */
public interface LocalComercialDAO {
    void crearTabla();
    boolean insertar(LocalComercial local);
    boolean actualizar(LocalComercial local);
    boolean eliminar(String codigo);
    LocalComercial buscarPorCodigo(String codigo);
    List<LocalComercial> listarTodos();
}