package modelo;

/**
 * Especialización de Inmueble para representar locales comerciales.
 * Esta clase gestiona la información específica sobre la viabilidad de
 * actividades económicas en el inmueble, como la licencia de apertura.
 * @author Manuel Jesus Jimenez Perez
 * @version 1.0
 * @see Inmueble
 */
public class LocalComercial extends Inmueble {

    /**
     * Indica si el local cuenta con los permisos legales para iniciar
     * una actividad comercial de forma inmediata.
     */
    private boolean licenciaApertura;

    /**
     * Constructor por defecto para la creación de instancias vacias.
     */
    public LocalComercial() {
        super();
    }

    /**
     * Constructor completo para registrar un Local Comercial con todos sus atributos.
     * * @param codigo Identificador único del inmueble.
     * @param propietario Nombre completo del titular del inmueble.
     * @param direccion Ubicación física del local.
     * @param superficie Tamaño total en metros cuadrados (m²).
     * @param licenciaApertura Booleano que indica si posee licencia (true) o no (false).
     */
    public LocalComercial(String codigo, String propietario, String direccion, double superficie,
                          boolean licenciaApertura) {
        super(codigo, propietario, direccion, superficie);
        this.licenciaApertura = licenciaApertura;
    }

    /**
     * Consulta el estado de la licencia de apertura del local.
     * @return true si dispone de licencia, false si requiere tramitación.
     */
    public boolean isLicenciaApertura() {
        return licenciaApertura;
    }

    /**
     * Actualiza el estado legal de la licencia de apertura.
     * @param licenciaApertura Nuevo estado de la licencia.
     */
    public void setLicenciaApertura(boolean licenciaApertura) {
        this.licenciaApertura = licenciaApertura;
    }

    /**
     * Genera una cadena de texto con la información específica del local.
     * @return Representación textual detallada del Local Comercial.
     */
    @Override
    public String toString() {
        return "LocalComercial [" + super.toString() +
                ", Licencia de apertura=" + (licenciaApertura ? "Sí" : "No") + "]";
    }
}