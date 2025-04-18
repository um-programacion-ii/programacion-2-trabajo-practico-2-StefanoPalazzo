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
    public static GestorUsuarios gestorUsuarios;
    public static GestorRecursos gestorRecursos;
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
                ConsolaUsuarios.MenuUsuarios();
                break;
            case 2:
                ConsolaRecursos.MenuRecursos();
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

}
