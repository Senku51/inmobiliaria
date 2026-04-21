package app;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conexion = ConexionDB.getConexion();
    }
}
