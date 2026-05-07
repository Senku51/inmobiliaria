package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import modelo.Piso;

/**
 * Implementación en memoria del DAO para la clase Piso.
 * Realizado por Carlos Martin Martin
 */
public class PisoDAOImpl implements IPisoDAO {

    // Almacenamiento temporal en memoria
    private static List<Piso> listaPisos = new ArrayList<>();

    @Override
    public boolean insertar(Piso piso) {
        if (buscarPorCodigo(piso.getCodigo()) != null) {
            return false; // Evita códigos duplicados
        }
        return listaPisos.add(piso);
    }

    @Override
    public boolean actualizar(Piso piso) {
        for (int i = 0; i < listaPisos.size(); i++) {
            if (listaPisos.get(i).getCodigo().equalsIgnoreCase(piso.getCodigo())) {
                listaPisos.set(i, piso);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(String codigo) {
        return listaPisos.removeIf(p -> p.getCodigo().equalsIgnoreCase(codigo));
    }

    @Override
    public Piso buscarPorCodigo(String codigo) {
        return listaPisos.stream()
                .filter(p -> p.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Piso> listarTodos() {
        return new ArrayList<>(listaPisos);
    }

    /**
     * Ejemplo de método de filtrado útil para la lógica de una inmobiliaria.
     */
    @Override
    public List<Piso> listarExteriores() {
        return listaPisos.stream()
                .filter(Piso::isEsExterior)
                .collect(Collectors.toList());
    }
}