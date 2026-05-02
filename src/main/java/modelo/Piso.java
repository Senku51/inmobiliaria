package modelo;

/**
 * Especialización de Inmueble para representar viviendas tipo Piso.
 * Incluye detalles sobre suministros y distribución interna.
 * @author Manuel Jesus Jimenez Perez
 */
public class Piso extends Inmueble {
    private int numHabitaciones;
    private int numBanios;
    private String tipoGas;
    private boolean esExterior;

    /**
     * Constructor completo para un Piso.
     * @param codigo Identificador único.
     * @param propietario Nombre del dueño.
     * @param direccion Dirección exacta.
     * @param superficie Metros cuadrados.
     * @param numHabitaciones Total de estancias incluyendo el salón.
     * @param numBanios Cantidad de cuartos de baño.
     * @param tipoGas Tipo de suministro (Natural, Ciudad o Butano).
     * @param esExterior True si da a la calle, False si es interior.
     */
    public Piso(String codigo, String propietario, String direccion, double superficie,
                int numHabitaciones, int numBanios, String tipoGas, boolean esExterior) {
        super(codigo, propietario, direccion, superficie);
        this.numHabitaciones = numHabitaciones;
        this.numBanios = numBanios;
        this.tipoGas = tipoGas;
        this.esExterior = esExterior;
    }

    /**
     * Obtiene el tipo de gas del piso.
     * @return String con el tipo (Natural/Ciudad/Butano).
     */
    public String getTipoGas() { return tipoGas; }
}