package app;

import dao.*;
import modelo.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Aplicación de gestión inmobiliaria por terminal.
 * @author Tu Nombre
 */
public class app {

    private static Scanner sc = new Scanner(System.in);
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Debemos sustituir "null" por las instancias reales de las implementaciones DAO
    private static AgenciaDAO agenciaDAO = new  AgenciaDAOImpl();
    private static InmuebleDAO inmuebleDAO = new InmuebleDAOImpl();
    private static PisoDAO pisoDAO = new PisoDAOImpl();
    private static LocalComercialDAO localDAO = new LocalComercialDAOImpl();
    private static TitularDAO titularDAO = new TitularDAOImpl();
    private static VendedorDAO vendedorDAO = new VendedorDAOImpl();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- SISTEMA DE GESTIÓN INMOBILIARIA ---");
            System.out.println("1. Gestión de Agencias");
            System.out.println("2. Gestión de Titulares (Jefes)");
            System.out.println("3. Gestión de Vendedores");
            System.out.println("4. Gestión de Inmuebles (Pisos y Locales)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> menuAgencias();
                case 2 -> menuTitulares();
                case 3 -> menuVendedores();
                case 4 -> menuInmuebles();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    //MENÚS ESPECÍFICOS

    private static void menuAgencias() {
        System.out.println("\n-- GESTIÓN DE AGENCIAS --");
        System.out.println("1. Alta de Agencia");
        System.out.println("2. Listar todas");
        System.out.println("3. Buscar por Zona");
        System.out.println("4. Eliminar Agencia");
        System.out.print("Opción: ");
        int opt = Integer.parseInt(sc.nextLine());

        switch (opt) {
            case 1 -> {
                System.out.print("Dirección: "); String dir = sc.nextLine();
                System.out.print("Fax: "); String fax = sc.nextLine();
                System.out.print("Zona Actuación: "); String zona = sc.nextLine();
                Agencia a = new Agencia(dir, fax, zona);
                agenciaDAO.insertar(a);
                System.out.println("Agencia insertada.");
            }
            case 2 -> agenciaDAO.obtenerTodas().forEach(System.out::println);
            case 3 -> {
                System.out.print("Zona a buscar: ");
                String z = sc.nextLine();
                System.out.println(agenciaDAO.buscarPorZona(z));
            }
            case 4 -> {
                System.out.print("Zona de la agencia a eliminar: ");
                agenciaDAO.eliminar(sc.nextLine());
            }
        }
    }

    private static void menuTitulares() {
        System.out.println("\n-- GESTIÓN DE TITULARES --");
        System.out.println("1. Registrar Titular y asociar a Agencia");
        System.out.println("2. Listar Titulares");
        System.out.print("Opción: ");
        int opt = Integer.parseInt(sc.nextLine());

        if (opt == 1) {
            System.out.print("Código Empleado: "); String cod = sc.nextLine();
            System.out.print("Nombre: "); String nom = sc.nextLine();
            System.out.print("Teléfono: "); String tel = sc.nextLine();
            System.out.print("Fecha Nacimiento (dd/mm/aaaa): ");
            LocalDate fecha = LocalDate.parse(sc.nextLine(), dtf);
            System.out.print("Titulación: "); String titu = sc.nextLine();
            System.out.print("Zona de Agencia a la que pertenece: "); String zona = sc.nextLine();

            Titular t = new Titular(cod, nom, tel, fecha, titu);
            titularDAO.insertar(t, zona);
        } else if (opt == 2) {
            titularDAO.obtenerTodos().forEach(System.out::println);
        }
    }

    private static void menuVendedores() {
        System.out.println("\n-- GESTIÓN DE VENDEDORES --");
        System.out.println("1. Alta de Vendedor");
        System.out.println("2. Listar Vendedores");
        System.out.print("Opción: ");
        int opt = Integer.parseInt(sc.nextLine());

        if (opt == 1) {
            System.out.print("Código Empleado: "); String cod = sc.nextLine();
            System.out.print("Nombre: "); String nom = sc.nextLine();
            System.out.print("Teléfono: "); String tel = sc.nextLine();
            System.out.print("Fecha Nacimiento (dd/mm/aaaa): ");
            LocalDate fecha = LocalDate.parse(sc.nextLine(), dtf);
            System.out.print("Comisión (%): "); double com = Double.parseDouble(sc.nextLine());
            System.out.print("Zona de su Agencia: "); String zona = sc.nextLine();

            // Buscamos la agencia para la relación de objeto
            Agencia ag = agenciaDAO.buscarPorZona(zona);
            if (ag != null) {
                Vendedor v = new Vendedor(cod, nom, tel, fecha, com, ag);
                vendedorDAO.insertar(v);
            } else {
                System.out.println("Error: La agencia no existe.");
            }
        } else if (opt == 2) {
            vendedorDAO.listarTodos().forEach(System.out::println);
        }
    }

    private static void menuInmuebles() {
        System.out.println("\n-- GESTIÓN DE INMUEBLES --");
        System.out.println("1. Alta de Piso");
        System.out.println("2. Alta de Local Comercial");
        System.out.println("3. Listar todos los Inmuebles");
        System.out.print("Opción: ");
        int opt = Integer.parseInt(sc.nextLine());

        switch (opt) {
            case 1 -> {
                Piso p = new Piso();
                pedirDatosBasicos(p);
                System.out.print("Nº Habitaciones: "); p.setNumHabitaciones(Integer.parseInt(sc.nextLine()));
                System.out.print("¿Es exterior? (s/n): "); p.setEsExterior(sc.nextLine().equalsIgnoreCase("s"));
                pisoDAO.insertar(p);
            }
            case 2 -> {
                LocalComercial l = new LocalComercial();
                pedirDatosBasicos(l);
                System.out.print("¿Tiene licencia de apertura? (s/n): ");
                l.setLicenciaApertura(sc.nextLine().equalsIgnoreCase("s"));
                localDAO.insertar(l);
            }
            case 3 -> inmuebleDAO.obtenerTodos().forEach(System.out::println);
        }
    }

    /**
     * Método auxiliar para no repetir código al pedir datos de Inmueble
     */
    private static void pedirDatosBasicos(Inmueble i) {
        System.out.print("Código: "); i.setCodigo(sc.nextLine());
        System.out.print("Propietario: "); i.setPropietario(sc.nextLine());
        System.out.print("Dirección: "); i.setDireccion(sc.nextLine());
        System.out.print("Superficie (m2): "); i.setSuperficie(Double.parseDouble(sc.nextLine()));
        System.out.print("¿En venta? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            i.setEnVenta(true);
            System.out.print("Precio Venta: "); i.setPrecioVenta(Double.parseDouble(sc.nextLine()));
        }
        System.out.print("¿En alquiler? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            i.setEnAlquiler(true);
            System.out.print("Precio Alquiler: "); i.setPrecioAlquiler(Double.parseDouble(sc.nextLine()));
        }
    }
}