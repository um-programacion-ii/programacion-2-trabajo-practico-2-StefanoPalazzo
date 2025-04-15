package console;

import java.util.Scanner;
import models.Usuario;
import models.RecursoDigital;
import services.GestorUsuarios;
import services.GestorRecursos;
import services.ServicioNotificacionesEmail;

public class Consola {
    private static GestorUsuarios gestorUsuarios;
    private static GestorRecursos gestorRecursos;

    public static void main(String[] args) {
        // Inicializar los servicios de notificación
        ServicioNotificacionesEmail servicioNotificacionesEmail = new ServicioNotificacionesEmail();

        // Inicializar los gestores
        gestorUsuarios = new GestorUsuarios(servicioNotificacionesEmail);
        gestorRecursos = new GestorRecursos(servicioNotificacionesEmail);

        // Iniciar la consola
        inciarConsola();
    }

    public static void inciarConsola() {
        MenuPrincipal();
    }

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
                System.out.println("Opción no válida");
                opcionesMenuPrincipal();
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
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                gestorUsuarios.listarUsuarios();
                break;
            case 2:
                System.out.println("Agregar Usuario");;
                break;
            case 3:
                System.out.println("Eliminar Usuario");;
                break;
            case 4:
                MenuPrincipal();
                break;
            default:
                System.out.println("Opción no válida");
                opcionesUsuarios();
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
        System.out.println("4. Prestar Recurso");
        System.out.println("5. Devolver Recurso");
        System.out.println("6. Renovar Recurso");
        System.out.println("7. Volver");
    }

    public static void opcionesRecursos() {
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                gestorRecursos.listarRecursos();
                break;
            case 2:
                System.out.println("Agregar Recurso");
                break;
            case 3:
                System.out.println("Eliminar Recurso");
                break;
            case 4:
                System.out.println("Prestar Recurso");
                break;
            case 5:
                System.out.println("Devolver Recurso");
                break;
            case 6:
                System.out.println("Renovar Recurso");
                break;
            case 7:
                MenuPrincipal();
                break;
            default:
                System.out.println("Opción no válida");
                opcionesRecursos();
                break;
        }
    }

}
