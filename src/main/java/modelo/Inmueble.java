package modelo;

/**
 * Clase abstracta que define los atributos comunes de cualquier inmueble.
 * Soporta las modalidades de alquiler, venta o ambas simultáneamente.
 *
 * @see Piso
 * @see LocalComercial
 * @author Manuel Jesus Jimenez Perez
 */
public abstract class Inmueble {
    private String codigo;
    private String propietario;
    private String direccion;
    private double superficie;

    // Atributos
    private boolean enAlquiler;
    private double precioAlquiler;
    private double fianza;

    private boolean enVenta;
    private double precioVenta;
    private boolean hipotecado;

    /**
     * Constructor para inicializar los datos básicos de un inmueble.
     * * @param codigo Identificador único del inmueble.
     * @param propietario Nombre del dueño del inmueble.
     * @param direccion Ubicación física completa.
     * @param superficie Tamaño en metros cuadrados (m²).
     */
    public Inmueble(String codigo, String propietario, String direccion, double superficie) {
        this.codigo = codigo;
        this.propietario = propietario;
        this.direccion = direccion;
        this.superficie = superficie;
    }
    //constructor vacio para permitir herencia
    public Inmueble() {

    }

    /**
     * Indica si el inmueble está disponible para alquiler.
     * @return true si está en alquiler, false en caso contrario.
     */
    public boolean isEnAlquiler() { return enAlquiler; }

    /**
     * Configura la disponibilidad de alquiler.
     * @param enAlquiler booleano de estado.
     */
    public void setEnAlquiler(boolean enAlquiler) { this.enAlquiler = enAlquiler; }

    /**
     * Obtiene el precio de venta del inmueble.
     * @return Precio en la moneda local.
     */
    public double getPrecioVenta() { return precioVenta; }


}