package dao;

import modelo.LocalComercial;
import modelo.Piso;

import java.util.List;

public interface InmuebleDAO {
    List<Piso> filtrarPisos(int habitaciones, String gas, boolean exterior);
    List<LocalComercial> filtrarLocales(boolean tieneLicencia);
}
