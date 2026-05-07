package dao;

/**
 * Clase abstracta que define los atributos comunes de cualquier inmueble.
 * Soporta las modalidades de alquiler, venta o ambas simultáneamente.
 *
 * @see Piso
 * @see LocalComercial
 * @author Manuel Jesus Jimenez Perez,modificado por Carlos Martin Martin
 */
public abstract class Inmueble {
    private String codigo;
    private String propietario;
    private String direccion;
    private double superficie;

    // Atributos de estado
    private boolean enAlquiler;
    private double precioAlquiler;
    private double fianza;

    private boolean enVenta;
    private double precioVenta;
    private boolean hipotecado;

    /**
     * Constructor para inicializar los datos básicos de un inmueble.
     *
     * @param codigo      Identificador único del inmueble.
     * @param propietario Nombre del dueño del inmueble.
     * @param direccion   Ubicación física completa.
     * @param superficie  Tamaño en metros cuadrados (m²).
     */
    public Inmueble(String codigo, String propietario, String direccion, double superficie) {
        this.codigo = codigo;
        this.propietario = propietario;
        this.direccion = direccion;
        this.superficie = superficie;
    }

    // Constructor vacio
    public Inmueble() {
        this.codigo = "";
        this.propietario = "";
        this.direccion = "";
        this.superficie = 0;
        this.enAlquiler = false;
        this.precioAlquiler = 0;
        this.fianza = 0;
        this.enVenta = false;
        this.precioVenta = 0;
        this.hipotecado = false;

    }

    // --- GETTERS Y SETTERS  ---

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public boolean isEnAlquiler() {
        return enAlquiler;
    }

    public void setEnAlquiler(boolean enAlquiler) {
        this.enAlquiler = enAlquiler;
    }

    public double getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(double precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    public double getFianza() {
        return fianza;
    }

    public void setFianza(double fianza) {
        this.fianza = fianza;
    }

    public boolean isEnVenta() {
        return enVenta;
    }

    public void setEnVenta(boolean enVenta) {
        this.enVenta = enVenta;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public boolean isHipotecado() {
        return hipotecado;
    }

    public void setHipotecado(boolean hipotecado) {
        this.hipotecado = hipotecado;
    }

    /**
     * Método toString para representar el inmueble.
     */
    @Override
    public String toString() {
        return "Inmueble{" +
                "Codigo='" + codigo + '\'' +
                ", Propietario='" + propietario + '\'' +
                ", Direccion='" + direccion + '\'' +
                ", Superficie=" + superficie +
                ", enAlquiler=" + enAlquiler +
                ", PrecioAlquiler=" + precioAlquiler +
                ", Fianza=" + fianza +
                ", EnVenta=" + enVenta +
                ", PrecioVenta=" + precioVenta +
                ", Hipotecado=" + hipotecado +
                '}';
    }
}