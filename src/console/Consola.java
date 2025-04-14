package console;
import java.util.Scanner;


public class Consola {
    public static void MenuPrincipal() {
        mostrarMenuPrincipal();
        opcionesMenuPrincipal();
    }

    public static void mostrarMenuPrincipal() {
        System.out.println("=== MENÚ PRINCIPAL ===");
        System.out.println("1. Gestor Usuarios");
        System.out.println("2. Gestor Recursos");
        System.out.println("3. Salir");
    }

    public static void opcionesMenuPrincipal() {
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                MenuUsuarios();
                break;
            case 2:
                MenuRecursos();
                break;
            case 3:
                System.out.println("Salir");
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    public static void MenuUsuarios() {
        mostrarMenuUsuarios();
        opcionesUsuarios();
    }

    public static void mostrarMenuUsuarios() {
        System.out.println("--- Gestor Usuarios ---");
        System.out.println("1. Listar Usuarios");
        System.out.println("2. Agregar Usuario");
        System.out.println("3. Eliminar Usuario");
        System.out.println("4. Volver");
    }

    public static void opcionesUsuarios() {
        mostrarMenuUsuarios();
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                System.out.println("Listando Usuarios");
                break;
            case 2:
                System.out.println("Agregar Usuario");
                break;
            case 3:
                System.out.println("Eliminar Usuario");
                break;
            case 4:
                MenuPrincipal();
                break;
            default:
                System.out.println("Opcion no valida");
                MenuPrincipal();
                break;
        }
    }

    public static void MenuRecursos() {
        mostrarMenuRecursos();
        opcionesRecursos();
    }

    public static void mostrarMenuRecursos() {
        System.out.println("--- Gestor Recursos ---");
        System.out.println("1. Listar Recursos");
        System.out.println("2. Agregar Recurso");
        System.out.println("3. Eliminar Recurso");
        System.out.println("4. Volver");
    }

    public static void opcionesRecursos() {
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                System.out.println("Listando Recurso");
                break;
            case 2:
                System.out.println("Agregar Recurso");
                break;
            case 3:
                System.out.println("Eliminar Recurso");
                break;
            case 4:
                MenuPrincipal();
                break;
            default:
                System.out.println("Opcion no valida");
                MenuPrincipal();
                break;
        }
    }


    public static void inciarConsola() {
        MenuPrincipal();
    }
}