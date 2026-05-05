package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.LocalComercial;

/**
 * Implementación del DAO para LocalComercial.
 * Gestiona la persistencia de los datos (en este caso, en memoria).
 * Realizado por Carlos Martin Martin
 */
public class LocalComercialDAOImpl implements ILocalComercialDAO {

    // Simulamos una base de datos con una lista estática
    private static List<LocalComercial> listaLocales = new ArrayList<>();

    @Override
    public boolean insertar(LocalComercial local) {
        if (buscarPorCodigo(local.getCodigo()) != null) {
            return false; // El código ya existe
        }
        return listaLocales.add(local);
    }

    @Override
    public boolean actualizar(LocalComercial local) {
        for (int i = 0; i < listaLocales.size(); i++) {
            if (listaLocales.get(i).getCodigo().equalsIgnoreCase(local.getCodigo())) {
                listaLocales.set(i, local);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(String codigo) {
        return listaLocales.removeIf(l -> l.getCodigo().equalsIgnoreCase(codigo));
    }

    @Override
    public LocalComercial buscarPorCodigo(String codigo) {
        return listaLocales.stream()
                .filter(l -> l.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<LocalComercial> listarTodos() {
        return new ArrayList<>(listaLocales);
    }
}