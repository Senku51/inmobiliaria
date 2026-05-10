package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import modelo.Vendedor;
import modelo.Agencia;

/**
 * Implementación del DAO para Vendedor.
 * Corregido y completado con inicialización de "tablas" y búsquedas por agencia.
 * @author Carlos Martin Martin (Base)
 * @author Daniel Lagares Paz (Corrección y completado)
 */
public class VendedorDAOImpl implements VendedorDAO {

    private static List<Vendedor> listaVendedores;

    /**
     * Inicializa la lista de vendedores en memoria.
     */
    @Override
    public void crearTabla() {
        if (listaVendedores == null) {
            listaVendedores = new ArrayList<>();
            System.out.println("[Sistema] Estructura de 'Vendedores' inicializada.");
        } else {
            System.out.println("[Sistema] La estructura de 'Vendedores' ya existe.");
        }
    }

    @Override
    public boolean insertar(Vendedor vendedor) {
        if (listaVendedores == null) crearTabla();

        if (vendedor == null || buscarPorCodigo(vendedor.getCodEmpleado()) != null) {
            return false;
        }
        return listaVendedores.add(vendedor);
    }

    @Override
    public boolean actualizar(Vendedor vendedor) {
        if (vendedor == null || listaVendedores == null) return false;

        for (int i = 0; i < listaVendedores.size(); i++) {
            if (listaVendedores.get(i).getCodEmpleado().equalsIgnoreCase(vendedor.getCodEmpleado())) {
                listaVendedores.set(i, vendedor);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(String codEmpleado) {
        if (codEmpleado == null || listaVendedores == null) return false;
        return listaVendedores.removeIf(v -> v.getCodEmpleado().equalsIgnoreCase(codEmpleado));
    }

    @Override
    public Vendedor buscarPorCodigo(String codEmpleado) {
        if (codEmpleado == null || listaVendedores == null) return null;
        return listaVendedores.stream()
                .filter(v -> v.getCodEmpleado().equalsIgnoreCase(codEmpleado))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Vendedor> listarTodos() {
        if (listaVendedores == null) return new ArrayList<>();
        return new ArrayList<>(listaVendedores);
    }

    /**
     * Implementación de búsqueda por nombre/zona de agencia.
     */
    @Override
    public List<Vendedor> buscarPorAgencia(String nombreAgencia) {
        if (nombreAgencia == null || listaVendedores == null) return new ArrayList<>();

        return listaVendedores.stream()
                .filter(v -> v.getAgencia() != null &&
                        v.getAgencia().getZonaActuacion().equalsIgnoreCase(nombreAgencia))
                .collect(Collectors.toList());
    }

    /**
     * Filtra vendedores comparando directamente el objeto Agencia.
     */
    @Override
    public List<Vendedor> buscarPorAgencia(Agencia agencia) {
        if (agencia == null || listaVendedores == null) return new ArrayList<>();

        return listaVendedores.stream()
                .filter(v -> v.getAgencia() != null && v.getAgencia().equals(agencia))
                .collect(Collectors.toList());
    }
}