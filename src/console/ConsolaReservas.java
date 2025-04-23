package console;

import models.Reserva;
import services.AlertaDisponibilidad;
import services.GestorRecursos;
import services.GestorReservas;

import java.util.Scanner;

import static console.Consola.alertasVencimiento;
import static console.Consola.alertasDisponibilidad;

public class ConsolaReservas {
    public static void MenuReservas() {
        mostrarMenuReservas();
        opcionesReservas();
    }
    public static void mostrarMenuReservas() {
        System.out.println("=== MENÚ RESERVAS ===");
        System.out.println("1. Agregar Reserva");
        System.out.println("2. Procesar Reserva de Recurso");
        System.out.println("3. Mostrar Reserva de Recurso");
        System.out.println("4. Mostrar Reservas Activas");
        System.out.println("5. Verificar disponibilidad de recurso");
        System.out.println("6. Mostrar recursos disponibles");
        System.out.println("7. Salir");
    }

    public static void opcionesReservas() {
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion) {
            case 1:
                System.out.println("-- Agregar Reserva --");
                System.out.println("ID del recurso a reservar: ");
                int idRecurso = Integer.parseInt(sc.nextLine());
                System.out.println("ID del usuario: ");
                int idUsuario = Integer.parseInt(sc.nextLine());
                try {
                    GestorReservas.agregarReserva(idRecurso, idUsuario);
                    System.out.println("Reserva agregada con éxito.");
                } catch (Exception e) {
                    System.out.println("Error al agregar la reserva: " + e.getMessage());
                }
                break;
            case 2:
                System.out.println("-- Procesar Reserva de Recurso --");
                System.out.println("Mostrar Reserva de recurso");
                System.out.println("ID de recurso: ");
                int idRecursoPrestamo = Integer.parseInt(sc.nextLine());
                try {
                    Reserva reserva = GestorReservas.procesarProximaReservaDeRecurso(idRecursoPrestamo);
                    if (reserva != null) {
                        System.out.println("Reserva procesada: " + reserva);
                    } else {
                        System.out.println("No hay reservas pendientes para el recurso con ID " + idRecursoPrestamo);
                    }
                } catch (Exception e) {
                    System.out.println("Error al procesar la reserva: " + e.getMessage());
                }
                break;
            case 3:
                System.out.println("-- Obtener Reserva --");
                System.out.println("ID de recurso: ");
                int idRecursoReserva = Integer.parseInt(sc.nextLine());
                try {
                    Reserva reserva = GestorReservas.obtenerProximaReservaDeRecurso(idRecursoReserva);
                    if (reserva != null) {
                        System.out.println("Reserva obtenida: " + reserva);
                    } else {
                        System.out.println("No hay reservas pendientes para el recurso con ID " + idRecursoReserva);
                    }
                } catch (Exception e) {
                    System.out.println("Error al obtener la reserva: " + e.getMessage());
                }
                break;
            case 4:
                System.out.println("-- Mostrar Reservas --");
                try {
                    GestorReservas.mostrarReservas();
                } catch (Exception e) {
                    System.out.println("Error al mostrar las reservas: " + e.getMessage());
                }
                break;
            case 5:
                System.out.println("-- Verificar disponibilidad de Recurso --");
                System.out.println("ID de recurso: ");
                int idRecursoAVerificar = Integer.parseInt(sc.nextLine());
                alertasDisponibilidad.verificarDisponibilidad(idRecursoAVerificar);
                break;
            case 6:
                System.out.println("-- Listado de recursos disponibles --");
                alertasDisponibilidad.mostrarRecursosDisponibles();
                break;
            case 7:
                break;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
        }
    }
}
