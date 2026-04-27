package modelo;

/**
 * Especialización de Inmueble para representar viviendas tipo Piso.
 * Incluye detalles sobre suministros y distribución interna.
 * @author Manuel Jesus Jimenez Perez, modificado por Carlos Martin Martin
 */
public class Piso extends Inmueble {
    private int numHabitaciones;
    private int numBanios;
    private String tipoGas;
    private boolean esExterior;


    public Piso() {
        super();
    }

    /**
     * Constructor completo para un Piso.
     */
    public Piso(String codigo, String propietario, String direccion, double superficie,
                int numHabitaciones, int numBanios, String tipoGas, boolean esExterior) {
        super(codigo, propietario, direccion, superficie);
        this.numHabitaciones = numHabitaciones;
        this.numBanios = numBanios;
        this.tipoGas = tipoGas;
        this.esExterior = esExterior;
    }

    // --- Getters y Setters  ---

    public int getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public int getNumBanios() {
        return numBanios;
    }

    public void setNumBanios(int numBanios) {
        this.numBanios = numBanios;
    }

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

    /**
     * Genera una cadena de texto con la información específica del piso.
     *
     * @return Representación textual que incluye los datos de la clase base.
     */
    @Override
    public String toString() {
        return "Piso{" +
                "numHabitaciones=" + numHabitaciones +
                ", numBanios=" + numBanios +
                ", tipoGas='" + tipoGas + '\'' +
                ", esExterior=" + esExterior +
                '}';
    }
}