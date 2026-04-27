package modelo;

public abstract class Inmueble {
    private int codigo;
    private String propietario;
    private String direccion;
    private double superficie;
    // Atributos para alquiler/venta
    private double precioAlquiler;
    private double fianza;
    private double precioVenta;
    private boolean hipotecado;

    // Getters y Setters de todos los campos
}