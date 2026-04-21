package modelo;
import java.util.ArrayList;
import java.util.List;
/**
 * Proyecto:
 *
 * @author Daniel Lagares Paz
 * @since 21/04/2026
 */
public class Agencia {
    //ATRIBUTOS
    private String direccion;
    private List<String> telefonos; // Cambiado a String y plural
    private String fax;
    private String zonaActuacion;
    private Titular titular;

    //CONSTRUCTORES
    public Agencia(String direccion, String fax, String zonaActuacion, String titular) {
        setDireccion(direccion);
        setFax(fax);
        setZonaActuacion(zonaActuacion);
        setTitular(titular);
        this.telefonos = new ArrayList<>();
    }

    public void agregarTelefono(String tel) {
        if (tel != null && !tel.isBlank()) {
            this.telefonos.add(tel);
        }
    }

    public void eliminarTelefono(String tel) {
        this.telefonos.remove(tel);
    }

    //GETTERS Y SETTERS
    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {
        if (direccion.length() < 3) {
            throw new IllegalArgumentException("La dirección debe tener al menos 3 caracteres");
        }
        this.direccion = direccion;
    }
    public List<String> getTelefono() {return telefonos;}
    public void setTelefono(List<String> telefono) {this.telefonos = telefono;}
    public String getFax() {return fax;}
    public void setFax(String fax) {
        if (fax.length() < 5) {
            throw new IllegalArgumentException("El fax debe tener al menos 5 caracteres");
        }
        this.fax = fax;
    }
    public String getZonaActuacion() {return zonaActuacion;}
    public void setZonaActuacion(String zonaActuacion) {
        if (zonaActuacion.length() < 5) {
            throw new IllegalArgumentException("El nombre debe tener al menos 5 caracteres");
        }
        this.zonaActuacion = zonaActuacion;
    }
    public Titular getTitular() {return titular;}
    public void setTitular(Titular titular) {this.titular = titular;}

    //TOSTRING
    @Override
    public String toString() {
        return String.format("Agencia [Zona: %s, Dirección: %s, Teléfonos: %s, Fax: %s]",
                zonaActuacion, direccion, telefonos, fax);
    }
}