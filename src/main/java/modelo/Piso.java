package modelo;

public class Piso extends Inmueble {
    private int numHabitaciones;
    private int numBaños;
    private String tipoGas; // "natural", "ciudad" o "butano"
    private boolean esExterior; // true = exterior, false = interior

    public Piso() {
        super();
    }

    // Getters y Setters
    public int getNumHabitaciones() {
        return numHabitaciones;
    }
    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones; }

    public String getTipoGas() {
        return tipoGas;
    }
    public void setTipoGas(String tipoGas) {
        this.tipoGas = tipoGas;
    }

    public boolean isEsExterior() {
        return esExterior;
    }
    public void setEsExterior(boolean esExterior) {
        this.esExterior = esExterior;
    }
}