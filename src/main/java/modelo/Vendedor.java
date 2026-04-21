package modelo;

import java.time.LocalDate;

/**
 * Representa a un vendedor de la red de agencias inmobiliarias.
 * Gestiona la información personal, laboral y su vinculación con una agencia.
 * @author Manuel Jesus Jimenez Perez
 * @version 1.0
 */
public class Vendedor {
    private String codEmpleado;
    private String nombre;
    private String telefono;
    private LocalDate fechaNacimiento;
    private double porcentajeComision;
    private int idAgencia;

    /**
     * Constructor por defecto.
     */
    public Vendedor() {}

    /**
     * Constructor con todos los atributos del vendedor.
     * @param codEmpleado Código único identificativo del empleado.
     * @param nombre Nombre completo del vendedor.
     * @param telefono Teléfono de contacto.
     * @param fechaNacimiento Fecha de nacimiento para control de edad.
     * @param porcentajeComision Porcentaje de ganancia por venta (ej. 5.5).
     * @param idAgencia Identificador de la agencia a la que está asignado.
     */
    public Vendedor(String codEmpleado, String nombre, String telefono,
                    LocalDate fechaNacimiento, double porcentajeComision, int idAgencia) {
        this.codEmpleado = codEmpleado;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.porcentajeComision = porcentajeComision;
        this.idAgencia = idAgencia;
    }

    /**
     * Obtiene el código del empleado.
     * @return String con el código identificador.
     */
    public String getCodEmpleado() { return codEmpleado; }

    /**
     * Define el código del empleado.
     * @param codEmpleado Código alfanumérico único.
     */
    public void setCodEmpleado(String codEmpleado) { this.codEmpleado = codEmpleado; }

    // ... Repetir estructura similar para el resto de getters y setters ...

    /**
     * Devuelve una representación en cadena del vendedor.
     * @return String con los detalles del objeto.
     */
    @Override
    public String toString() {
        return "Vendedor [" + codEmpleado + "]: " + nombre;
    }
}