package console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Usuario;
import models.RecursoDigital;
import services.*;


public class Consola {
    public static GestorUsuarios gestorUsuarios;
    public static GestorRecursos gestorRecursos;
    public static GestorPrestamos gestorPrestamos;
    public static GestorReservas gestorReservas;
    public static GestorNotificaciones gestorNotificaciones;
    public static AlertaVencimiento alertasVencimiento;
    public static AlertaDisponibilidad alertasDisponibilidad;
    public static boolean ejecutando = true;


    public static void main(String[] args) {
        ServicioNotificacionesEmail servicioNotificacionesEmail = new ServicioNotificacionesEmail();
        gestorNotificaciones = new GestorNotificaciones(servicioNotificacionesEmail);
        gestorUsuarios = new GestorUsuarios(servicioNotificacionesEmail);
        gestorRecursos = new GestorRecursos(servicioNotificacionesEmail);
        gestorPrestamos = new GestorPrestamos(servicioNotificacionesEmail);
        gestorReservas = new GestorReservas(servicioNotificacionesEmail);
        alertasDisponibilidad = new AlertaDisponibilidad(GestorRecursos.getRecursos());
        inciarConsola();
    }

    public static void inicializar() {
        ServicioNotificacionesEmail servicioNotificacionesEmail = new ServicioNotificacionesEmail();
        gestorNotificaciones = new GestorNotificaciones(servicioNotificacionesEmail);
        gestorUsuarios = new GestorUsuarios(servicioNotificacionesEmail);
        gestorRecursos = new GestorRecursos(servicioNotificacionesEmail);
        gestorPrestamos = new GestorPrestamos(servicioNotificacionesEmail);
        gestorReservas = new GestorReservas(servicioNotificacionesEmail);
        alertasDisponibilidad = new AlertaDisponibilidad(gestorRecursos.getRecursos());
    }


    public static void inciarConsola() {
        MenuPrincipal();
    }

    public static void MenuPrincipal() {
        while (ejecutando){
        mostrarMenuPrincipal();
        opcionesMenuPrincipal();
        }
        gestorNotificaciones.cerrar();
    }

    public static void mostrarMenuPrincipal() {
        System.out.println("=== MENÚ PRINCIPAL ===");
        System.out.println("1. Gestor Usuarios");
        System.out.println("2. Gestor Recursos");
        System.out.println("3. Gestor Prestamos");
        System.out.println("4. Gestor Reservas");
        System.out.println("5. Reportes");
        System.out.println("6. Alertar Vencimientos");
        System.out.println("7. Salir");
    }

    public static void opcionesMenuPrincipal() {
        Scanner sc = new Scanner(System.in);
        try {
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    ConsolaUsuarios.MenuUsuarios();
                    break;
                case 2:
                    ConsolaRecursos.MenuRecursos();
                    break;
                case 3:
                    ConsolaPrestamos.MenuPrestamos();
                    break;
                case 4:
                    ConsolaReservas.MenuReservas();
                    break;
                case 5:
                    ConsolaReportes.MenuReportes();
                    break;
                case 6:
                    System.out.println("=== ALERTAS DE VENCIMIENTO ===");
                    if (gestorPrestamos.getPrestamosActivos().isEmpty()) {
                        System.out.println("No hay préstamos activos para verificar alertas.");
                        break;
                    }

                    alertasVencimiento = new AlertaVencimiento(gestorPrestamos.getPrestamosActivos(), gestorNotificaciones);
                    alertasVencimiento.verificarAlertas();
                    break;

                case 7:
                    System.out.println("Saliendo...");
                    ejecutando = false;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            sc.nextLine();
        }
    }

}
