package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.LocalComercial;

/**
 * Implementación en memoria del DAO para LocalComercial.
 * Corregido para incluir persistencia simulada y gestión de errores.
 * @author Carlos Martin Martin (Base)
 * @author Daniel Lagares Paz (Corrección y estructura)
 */
public class LocalComercialDAOImpl implements LocalComercialDAO {

    // Simulamos una base de datos con una lista estática
    private static List<LocalComercial> listaLocales;

    /**
     * Inicializa la estructura de almacenamiento para Locales Comerciales.
     */
    @Override
    public void crearTabla() {
        if (listaLocales == null) {
            listaLocales = new ArrayList<>();
            System.out.println("[Sistema] Estructura de 'Locales Comerciales' inicializada.");
        } else {
            System.out.println("[Sistema] La estructura de 'Locales Comerciales' ya estaba lista.");
        }
    }

    @Override
    public boolean insertar(LocalComercial local) {
        // Autocreate si se nos olvida llamar a crearTabla en el Main
        if (listaLocales == null) crearTabla();

        if (buscarPorCodigo(local.getCodigo()) != null) {
            return false; // El código ya existe, no se puede duplicar
        }
        return listaLocales.add(local);
    }

    @Override
    public boolean actualizar(LocalComercial local) {
        if (listaLocales == null) return false;

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
        if (listaLocales == null) return false;
        return listaLocales.removeIf(l -> l.getCodigo().equalsIgnoreCase(codigo));
    }

    @Override
    public LocalComercial buscarPorCodigo(String codigo) {
        if (listaLocales == null) return null;

        return listaLocales.stream()
                .filter(l -> l.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<LocalComercial> listarTodos() {
        if (listaLocales == null) return new ArrayList<>();
        return new ArrayList<>(listaLocales);
    }
}