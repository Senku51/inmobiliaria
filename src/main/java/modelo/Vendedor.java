package modelo;

import java.time.LocalDate;
import java.time.Period;

/**
 * Representa a un vendedor de la red de agencias inmobiliarias.
 * Corregida con validaciones robustas y relación de objeto con Agencia.
 * * @author Manuel Jesus Jimenez Perez
 * @author Carlos Martin Martin
 * @version 1.1
 */
public class Vendedor {
    private String codEmpleado;
    private String nombre;
    private String telefono;
    private LocalDate fechaNacimiento;
    private double porcentajeComision;
    private Agencia agencia; // Referencia a objeto (Orientación a Objetos correcta)

    /**
     * Constructor por defecto.
     */
    public Vendedor() {
    }

    /**
     * Constructor completo. Usa los setters para asegurar que las
     * validaciones se apliquen desde el momento de la creación.
     */
    public Vendedor(String codEmpleado, String nombre, String telefono,
                    LocalDate fechaNacimiento, double porcentajeComision, Agencia agencia) {
        setCodEmpleado(codEmpleado);
        setNombre(nombre);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
        setPorcentajeComision(porcentajeComision);
        this.agencia = agencia;
    }

    // --- Getters y Setters con Validaciones ---

    public String getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(String codEmpleado) {
        if (codEmpleado == null || codEmpleado.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de empleado no puede estar vacío");
        }
        this.codEmpleado = codEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().length() < 2) {
            throw new IllegalArgumentException("El nombre es obligatorio y debe tener al menos 2 caracteres");
        }
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        // Validación básica de formato (ej. 9 dígitos)
        if (telefono == null || !telefono.matches("\\d{9,11}")) {
            throw new IllegalArgumentException("El teléfono debe ser un número válido de entre 9 y 11 dígitos");
        }
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
        }

        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();

        if (edad < 18 || edad > 100) {
            throw new IllegalArgumentException("El vendedor debe ser mayor de edad y tener una edad coherente. Edad actual: " + edad);
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    public double getPorcentajeComision() {
        return porcentajeComision;
    }

    public void setPorcentajeComision(double porcentajeComision) {
        if (porcentajeComision < 0 || porcentajeComision > 100) {
            throw new IllegalArgumentException("La comisión debe ser un porcentaje entre 0 y 100");
        }
        this.porcentajeComision = porcentajeComision;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    /**
     * toString mejorado para mostrar información completa del vendedor
     * y datos clave de su agencia asociada.
     */

    @Override
    public String toString() {
        return "Vendedor{" +
                "codEmpleado='" + codEmpleado + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", porcentajeComision=" + porcentajeComision +
                ", agencia=" + agencia +
                '}';
    }
}
