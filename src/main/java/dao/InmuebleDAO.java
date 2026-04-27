package dao;

import modelo.Inmueble;
import java.util.List;

/**
 * Realizado por Carlos Martin Martin
 */
public interface InmuebleDAO {
    void crearTabla();
    void insertar(Inmueble inmueble);
    Inmueble buscarPorCodigo(String codigo);
    List<Inmueble> obtenerTodos();
    void actualizar(Inmueble inmueble);
    void eliminar(String codigo);
}