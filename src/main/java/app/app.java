package app;

import dao.*;
import modelo.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Aplicación de gestión inmobiliaria por terminal.
 * @author Daniel Lagares Paz (Versión Final con CRUD completo)
 */
public class app {

    private static Scanner sc = new Scanner(System.in);
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static AgenciaDAO agenciaDAO = new AgenciaDAOImpl();
    private static InmuebleDAO inmuebleDAO = new InmuebleDAOImpl();
    private static PisoDAO pisoDAO = new PisoDAOImpl();
    private static LocalComercialDAO localDAO = new LocalComercialDAOImpl();
    private static TitularDAO titularDAO = new TitularDAOImpl();
    private static VendedorDAO vendedorDAO = new VendedorDAOImpl();

    public static void main(String[] args) {
        System.out.println("Sincronizando base de datos...");
        agenciaDAO.crearTabla();
        inmuebleDAO.crearTabla();
        pisoDAO.crearTabla();
        localDAO.crearTabla();
        titularDAO.crearTabla();
        vendedorDAO.crearTabla();

        int opcion = -1;
        do {
            System.out.println("\n========================================");
            System.out.println("   SISTEMA DE GESTIÓN INMOBILIARIA");
            System.out.println("========================================");
            System.out.println("1. Gestión de Agencias");
            System.out.println("2. Gestión de Titulares (Jefes)");
            System.out.println("3. Gestión de Vendedores");
            System.out.println("4. Gestión de Inmuebles (Pisos y Locales)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntradaInt();

            switch (opcion) {
                case 1 -> menuAgencias();
                case 2 -> menuTitulares();
                case 3 -> menuVendedores();
                case 4 -> menuInmuebles();
                case 0 -> System.out.println("Cerrando sistema. ¡Hasta pronto!");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void menuAgencias() {
        int opt;
        do {
            System.out.println("\n-- GESTIÓN DE AGENCIAS --");
            System.out.println("1. Alta de Agencia");
            System.out.println("2. Listar todas");
            System.out.println("3. Buscar por Zona");
            System.out.println("4. Añadir teléfono");
            System.out.println("5. Eliminar Agencia");
            System.out.println("0. Volver atrás");
            System.out.print("Opción: ");
            opt = leerEntradaInt();

            switch (opt) {
                case 1 -> {
                    System.out.print("Dirección: "); String dir = sc.nextLine();
                    System.out.print("Fax: "); String fax = sc.nextLine();
                    System.out.print("Zona Actuación: "); String zona = sc.nextLine();
                    agenciaDAO.insertar(new Agencia(dir, fax, zona));
                    System.out.println(">>> Agencia registrada.");
                }
                case 2 -> agenciaDAO.obtenerTodas().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Zona: ");
                    Agencia a = agenciaDAO.buscarPorZona(sc.nextLine());
                    System.out.println(a != null ? a : "No existe.");
                }
                case 4 -> {
                    System.out.print("Zona: "); String z = sc.nextLine();
                    System.out.print("Teléfono: "); String t = sc.nextLine();
                    agenciaDAO.agregarTelefono(z, t);
                }
                case 5 -> {
                    System.out.print("Zona de la agencia a eliminar: ");
                    agenciaDAO.eliminar(sc.nextLine());
                }
            }
        } while (opt != 0);
    }

    private static void menuTitulares() {
        int opt;
        do {
            System.out.println("\n-- GESTIÓN DE TITULARES --");
            System.out.println("1. Registrar Titular");
            System.out.println("2. Listar Titulares");
            System.out.println("3. Eliminar Titular");
            System.out.println("0. Volver atrás");
            System.out.print("Opción: ");
            opt = leerEntradaInt();

            switch (opt) {
                case 1 -> {
                    try {
                        System.out.print("Código: "); String cod = sc.nextLine();
                        System.out.print("Nombre: "); String nom = sc.nextLine();
                        System.out.print("Tel: "); String tel = sc.nextLine();
                        System.out.print("Nacimiento (dd/mm/aaaa): ");
                        LocalDate f = LocalDate.parse(sc.nextLine(), dtf);
                        System.out.print("Titulación: "); String tit = sc.nextLine();
                        System.out.print("Zona Agencia: "); String zona = sc.nextLine();
                        titularDAO.insertar(new Titular(cod, nom, tel, f, tit), zona);
                        System.out.println(">>> Titular guardado.");
                    } catch (Exception e) { System.out.println("Error en datos."); }
                }
                case 2 -> titularDAO.obtenerTodos().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Código de empleado a eliminar: ");
                    titularDAO.eliminar(sc.nextLine());
                    System.out.println(">>> Operación realizada.");
                }
            }
        } while (opt != 0);
    }

    private static void menuVendedores() {
        int opt;
        do {
            System.out.println("\n-- GESTIÓN DE VENDEDORES --");
            System.out.println("1. Alta de Vendedor");
            System.out.println("2. Listar Vendedores");
            System.out.println("3. Eliminar Vendedor");
            System.out.println("0. Volver atrás");
            System.out.print("Opción: ");
            opt = leerEntradaInt();

            switch (opt) {
                case 1 -> {
                    System.out.print("Código: "); String cod = sc.nextLine();
                    System.out.print("Nombre: "); String nom = sc.nextLine();
                    System.out.print("Tel: "); String tel = sc.nextLine();
                    System.out.print("Nacimiento (dd/mm/aaaa): ");
                    LocalDate f = LocalDate.parse(sc.nextLine(), dtf);
                    System.out.print("Comisión: "); double com = Double.parseDouble(sc.nextLine());
                    System.out.print("Zona Agencia: "); String zona = sc.nextLine();
                    Agencia ag = agenciaDAO.buscarPorZona(zona);
                    if (ag != null) {
                        vendedorDAO.insertar(new Vendedor(cod, nom, tel, f, com, ag));
                        System.out.println(">>> Vendedor registrado.");
                    } else System.out.println("La agencia no existe.");
                }
                case 2 -> vendedorDAO.listarTodos().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Código de vendedor a eliminar: ");
                    vendedorDAO.eliminar(sc.nextLine());
                    System.out.println(">>> Operación realizada.");
                }
            }
        } while (opt != 0);
    }

    private static void menuInmuebles() {
        int opt;
        do {
            System.out.println("\n-- GESTIÓN DE INMUEBLES --");
            System.out.println("1. Alta de Piso");
            System.out.println("2. Alta de Local Comercial");
            System.out.println("3. Listar todos");
            System.out.println("4. Eliminar Inmueble");
            System.out.println("0. Volver atrás");
            System.out.print("Opción: ");
            opt = leerEntradaInt();

            switch (opt) {
                case 1 -> {
                    Piso p = new Piso();
                    pedirDatosBasicos(p);
                    System.out.print("Habitaciones: "); p.setNumHabitaciones(leerEntradaInt());
                    System.out.print("¿Exterior? (s/n): "); p.setEsExterior(sc.nextLine().equalsIgnoreCase("s"));
                    pisoDAO.insertar(p);
                }
                case 2 -> {
                    LocalComercial l = new LocalComercial();
                    pedirDatosBasicos(l);
                    System.out.print("¿Licencia? (s/n): "); l.setLicenciaApertura(sc.nextLine().equalsIgnoreCase("s"));
                    localDAO.insertar(l);
                }
                case 3 -> inmuebleDAO.obtenerTodos().forEach(System.out::println);
                case 4 -> {
                    System.out.print("Código del inmueble (Piso o Local) a eliminar: ");
                    String cod = sc.nextLine();
                    inmuebleDAO.eliminar(cod); // Elimina de la tabla base
                    pisoDAO.eliminar(cod);     // Limpia también la memoria si es un piso
                    localDAO.eliminar(cod);    // Limpia también la memoria si es un local
                    System.out.println(">>> Inmueble eliminado de todos los registros.");
                }
            }
        } while (opt != 0);
    }

    private static void pedirDatosBasicos(Inmueble i) {
        System.out.print("Código: "); i.setCodigo(sc.nextLine());
        System.out.print("Propietario: "); i.setPropietario(sc.nextLine());
        System.out.print("Dirección: "); i.setDireccion(sc.nextLine());
        System.out.print("Superficie: "); i.setSuperficie(Double.parseDouble(sc.nextLine()));
        System.out.print("¿Venta? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            i.setEnVenta(true);
            System.out.print("Precio: "); i.setPrecioVenta(Double.parseDouble(sc.nextLine()));
        }
        System.out.print("¿Alquiler? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            i.setEnAlquiler(true);
            System.out.print("Precio: "); i.setPrecioAlquiler(Double.parseDouble(sc.nextLine()));
        }
    }

    private static int leerEntradaInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (Exception e) { return -1; }
    }
}