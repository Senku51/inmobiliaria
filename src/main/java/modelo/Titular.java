package modelo;
import java.time.LocalDate;
import java.time.Period;
/**
 * Proyecto:
 *
 * @author Daniel Lagares Paz
 * @since 21/04/2026
 */
public class Titular {
    private String codigoEmpleado;
    private String nombre;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String titulacion;

    public Titular(String codigoEmpleado, String nombre, String telefono, LocalDate fechaNacimiento, String titulacion) {
        setCodigoEmpleado(codigoEmpleado);
        setNombre(nombre);
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        setTitulacion(titulacion);
    }

    //VALIDACIONES CON SETTERS
    public void setCodigoEmpleado(String codigoEmpleado) {
        if (codigoEmpleado == null || codigoEmpleado.isBlank()) {
            throw new IllegalArgumentException("El código de empleado no puede estar vacío");
        }
        this.codigoEmpleado = codigoEmpleado;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.length() < 2) {
            throw new IllegalArgumentException("El nombre debe ser válido");
        }
        this.nombre = nombre;
    }

    public void setTitulacion(String titulacion) {
        if (titulacion == null || titulacion.isEmpty()) {
            throw new IllegalArgumentException("La titulación es obligatoria");
        }
        this.titulacion = titulacion;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
        }
        LocalDate hoy = LocalDate.now();
        // 1. Validar que no sea una fecha futura
        if (fechaNacimiento.isAfter(hoy)) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura");
        }
        // 2. Calcular la edad (usando la clase Period)
        int edad = Period.between(fechaNacimiento, hoy).getYears();
        // 3. Validar rango de edad (ejemplo: entre 18 y 100 años)
        if (edad < 18)
            throw new IllegalArgumentException("El empleado debe ser mayor de edad (mínimo 18 años). Edad actual: " + edad);
        if (edad > 100)
            throw new IllegalArgumentException("La edad introducida no es coherente (máximo 100 años). Edad actual: " + edad);
        this.fechaNacimiento = fechaNacimiento;
    }

    //GETTERS
    public String getCodigoEmpleado() { return codigoEmpleado; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public String getTitulacion() { return titulacion; }

    //METODO TOSTRING
    @Override
    public String toString() {
        return String.format("Titular [Código: %s, Nombre: %s, Teléfono: %s, Fecha Nac.: %s, Titulación: %s]",
                codigoEmpleado, nombre, telefono, fechaNacimiento, titulacion);
    }
}