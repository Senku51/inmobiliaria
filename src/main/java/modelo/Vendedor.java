package modelo;

import java.time.LocalDate;
import java.time.Period;

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
    private Agencia agencia;

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
     * @param agencia Agencia a la que está asignado el vendedor.
     */
    public Vendedor(String codEmpleado, String nombre, String telefono,
                    LocalDate fechaNacimiento, double porcentajeComision, Agencia agencia) {
        setCodEmpleado(codEmpleado);
        setNombre(nombre);
        this.telefono = telefono;
        setFechaNacimiento(fechaNacimiento);
        setPorcentajeComision(porcentajeComision);
        this.agencia = agencia;
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
    public void setCodEmpleado(String codEmpleado) {
        if (codEmpleado == null || codEmpleado.isBlank()) {
            throw new IllegalArgumentException("El código de empleado no puede ser nulo o vacío");
        }
        this.codEmpleado = codEmpleado;
    }

    /**
     * @return muestra el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del vendedor.
     * @param nombre Nombre completo (mínimo 2 caracteres).
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.length() < 2) {
            throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres");
        }
        this.nombre = nombre;
    }

    /**
     * @return muestra telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono cambia telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return muestra la fecha de nacimiento
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Cambia la fecha de nacimiento validando que sea mayor de edad.
     * @param fechaNacimiento Fecha de nacimiento.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
        }
        LocalDate hoy = LocalDate.now();
        if (fechaNacimiento.isAfter(hoy)) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura");
        }
        int edad = Period.between(fechaNacimiento, hoy).getYears();
        if (edad < 18) {
            throw new IllegalArgumentException("El vendedor debe ser mayor de edad (mínimo 18 años). Edad actual: " + edad);
        }
        if (edad > 100) {
            throw new IllegalArgumentException("La edad introducida no es coherente (máximo 100 años). Edad actual: " + edad);
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return muestra porcentaje comision
     */
    public double getPorcentajeComision() {
        return porcentajeComision;
    }

    /**
     * Cambia el porcentaje de comisión validando que sea positivo.
     * @param porcentajeComision Porcentaje entre 0 y 100.
     */
    public void setPorcentajeComision(double porcentajeComision) {
        if (porcentajeComision < 0 || porcentajeComision > 100) {
            throw new IllegalArgumentException("El porcentaje de comisión debe estar entre 0 y 100");
        }
        this.porcentajeComision = porcentajeComision;
    }

    /**
     * @return la agencia a la que pertenece el vendedor
     */
    public Agencia getAgencia() {
        return agencia;
    }

    /**
     * @param agencia modifica la agencia asignada al vendedor
     */
    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    /**
     * Devuelve una representación en cadena del vendedor.
     * @return String con los detalles del objeto.
     */
    @Override
    public String toString() {
        return String.format("Vendedor [Código: %s, Nombre: %s, Teléfono: %s, Fecha Nac.: %s, Comisión: %.2f%%, Agencia: %s]",
                codEmpleado, nombre, telefono, fechaNacimiento, porcentajeComision,
                agencia != null ? agencia.getZonaActuacion() : "Sin asignar");
    }
}
