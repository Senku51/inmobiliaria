package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import modelo.Piso;

/**
 * Implementación en memoria del DAO para la clase Piso.
 * Corregido y completado para asegurar compatibilidad con la interfaz.
 * * @author Daniel Lagares Paz (Corrección de estructura)
 * @author Carlos Martin Martin
 */
public class PisoDAOImpl implements PisoDAO {

    // Almacenamiento temporal en memoria
    private static List<Piso> listaPisos;

    /**
     * Simula la creación de la tabla en una base de datos.
     * En este caso, inicializa la lista en memoria si no existe.
     */
    @Override
    public void crearTabla() {
        if (listaPisos == null) {
            listaPisos = new ArrayList<>();
            System.out.println("[Sistema] Estructura de 'Pisos' inicializada correctamente.");
        } else {
            System.out.println("[Sistema] La estructura de 'Pisos' ya existe.");
        }
    }

    @Override
    public boolean insertar(Piso piso) {
        // Aseguramos que la "tabla" exista antes de insertar
        if (listaPisos == null) crearTabla();

        if (buscarPorCodigo(piso.getCodigo()) != null) {
            return false; // Evita códigos duplicados
        }
        return listaPisos.add(piso);
    }

    @Override
    public boolean actualizar(Piso piso) {
        if (listaPisos == null) return false;

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
        if (listaPisos == null) return false;
        return listaPisos.removeIf(p -> p.getCodigo().equalsIgnoreCase(codigo));
    }

    @Override
    public Piso buscarPorCodigo(String codigo) {
        if (listaPisos == null) return null;

        return listaPisos.stream()
                .filter(p -> p.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Piso> listarTodos() {
        if (listaPisos == null) return new ArrayList<>();
        return new ArrayList<>(listaPisos);
    }

    /**
     * Filtra los pisos que son exteriores.
     */
    @Override
    public List<Piso> listarExteriores() {
        if (listaPisos == null) return new ArrayList<>();

        return listaPisos.stream()
                .filter(Piso::isEsExterior)
                .collect(Collectors.toList());
    }
}