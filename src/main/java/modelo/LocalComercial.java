package modelo;

public class LocalComercial extends Inmueble {
    private boolean licenciaApertura;

    public LocalComercial() { super(); }

    // Getter y Setter
    public boolean isLicenciaApertura() { return licenciaApertura; }
    public void setLicenciaApertura(boolean licenciaApertura) { this.licenciaApertura = licenciaApertura; }
}