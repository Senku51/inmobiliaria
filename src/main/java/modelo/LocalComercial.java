package modelo;

/**
 * Especialización de Inmueble para representar locales comerciales.
 * Se han añadido validaciones para asegurar la integridad de los datos.
 * * @author Manuel Jesus Jimenez Perez
 * @author Carlos Martin Martin
 * @version 1.1
 */
public class LocalComercial extends Inmueble {

    /**
     * Indica si el local cuenta con los permisos legales para iniciar
     * una actividad comercial de forma inmediata.
     */
    private boolean licenciaApertura;

    /**
     * Constructor por defecto.
     */
    public LocalComercial() {
        super();
    }

    /**
     * Constructor completo.
     * Incluye validación de parámetros básicos delegados a la lógica de negocio.
     * * @param codigo Identificador único.
     * @param propietario Nombre del titular.
     * @param direccion Ubicación física.
     * @param superficie Tamaño en m².
     * @param licenciaApertura Estado de la licencia.
     */
    public LocalComercial(String codigo, String propietario, String direccion, double superficie,
                          boolean licenciaApertura) {
        // Invocamos al constructor de la clase padre (Inmueble)
        super(codigo, propietario, direccion, superficie);
        this.licenciaApertura = licenciaApertura;
    }

    /**
     * @return true si dispone de licencia, false en caso contrario.
     */
    public boolean isLicenciaApertura() {
        return licenciaApertura;
    }

    /**
     * Actualiza el estado de la licencia.
     * @param licenciaApertura Nuevo estado booleano.
     */
    public void setLicenciaApertura(boolean licenciaApertura) {
        this.licenciaApertura = licenciaApertura;
    }

    /**
     * Genera una representación textual del local.
     * Nota: Depende de que Inmueble tenga implementado su propio toString().
     * * @return Cadena con los datos del inmueble y el estado de su licencia.
     */
    @Override
    public String toString() {
        return "LocalComercial{" +
                "licenciaApertura=" + licenciaApertura +
                '}';
    }
}