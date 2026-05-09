package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import modelo.Vendedor;
import modelo.Agencia;

/**
 * Implementación del DAO para Vendedor.

 */
public class VendedorDAOImpl implements VendedorDAO {

    private static List<Vendedor> listaVendedores = new ArrayList<>();

    @Override
    public boolean insertar(Vendedor vendedor) {
        if (vendedor == null || buscarPorCodigo(vendedor.getCodEmpleado()) != null) {
            return false;
        }
        return listaVendedores.add(vendedor);
    }

    @Override
    public boolean actualizar(Vendedor vendedor) {
        if (vendedor == null) return false;
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
        if (codEmpleado == null) return false;
        return listaVendedores.removeIf(v -> v.getCodEmpleado().equalsIgnoreCase(codEmpleado));
    }

    @Override
    public Vendedor buscarPorCodigo(String codEmpleado) {
        if (codEmpleado == null) return null;
        return listaVendedores.stream()
                .filter(v -> v.getCodEmpleado().equalsIgnoreCase(codEmpleado))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Vendedor> listarTodos() {
        return new ArrayList<>(listaVendedores);
    }

    @Override
    public List<Vendedor> buscarPorAgencia(String nombreAgencia) {
        return List.of();
    }

    /**
     * Filtra vendedores comparando directamente el objeto Agencia.
     * Esto evita llamar a métodos internos de Agencia que den problemas.
     */
    @Override
    public List<Vendedor> buscarPorAgencia(Agencia agencia) {
        if (agencia == null) return new ArrayList<>();
        return listaVendedores.stream()
                .filter(v -> v.getAgencia() != null && v.getAgencia().equals(agencia))
                .collect(Collectors.toList());
    }
}