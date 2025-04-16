package console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Usuario;
import models.RecursoDigital;
import services.GestorUsuarios;
import services.GestorRecursos;
import services.ServicioNotificacionesEmail;

public class Consola {
    private static GestorUsuarios gestorUsuarios;
    private static GestorRecursos gestorRecursos;
    public static boolean ejecutando = true;

    public static void main(String[] args) {
        ServicioNotificacionesEmail servicioNotificacionesEmail = new ServicioNotificacionesEmail();
        gestorUsuarios = new GestorUsuarios(servicioNotificacionesEmail);
        gestorRecursos = new GestorRecursos(servicioNotificacionesEmail);
        inciarConsola();
    }

    public static void inciarConsola() {
        MenuPrincipal();
    }

    public static void MenuPrincipal() {
        while (ejecutando){
        mostrarMenuPrincipal();
        opcionesMenuPrincipal();
        }
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
                ejecutando = false;
                break;
            default:
                System.out.println("Opción no válida");
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
        System.out.println("4. Buscar usuario por nombre");
        System.out.println("5. Buscar usuario por ID");
        System.out.println("6. Volver");
    }

    public static void opcionesUsuarios() {
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion) {
            case 1:
                gestorUsuarios.listarUsuarios();
                break;
            case 2:
                System.out.println("Agregar Usuario");
                break;
            case 3:
                System.out.println("Eliminar Usuario");
                break;
            case 4:
                System.out.println("Ingresar nombre del usuario");
                String nombreBuscado = sc.nextLine();
                gestorUsuarios.buscarUsuarioPorNombreOApellido(nombreBuscado);
                break;
            case 5:
                System.out.println("Ingresar ID del usuario");
                int idUsuario = sc.nextInt();
                gestorUsuarios.buscarUsuarioPorId(idUsuario);
            case 6:
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
            System.out.println("7. Buscar por Título");
            System.out.println("8. Buscar por ID");
            System.out.println("9. Volver");
        }

    public static void opcionesRecursos() {
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        sc.nextLine();
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
                System.out.print("Ingresar parte del título: ");
                String titulo = sc.nextLine();
                gestorRecursos.buscarRecursoPorTitulo(titulo);
                break;
            case 8:
                System.out.print("Ingresar ID del recurso: ");
                int idBuscado = sc.nextInt();
                gestorRecursos.buscarRecursoPorId(idBuscado);
                break;
            case 9:
                break;
            default:
                System.out.println("Opción no válida");
                opcionesRecursos();
                break;
        }
    }

}
