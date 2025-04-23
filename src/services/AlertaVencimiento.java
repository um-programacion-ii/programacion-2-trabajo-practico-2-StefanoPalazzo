package services;

import models.NivelUrgencia;
import models.Prestamo;
import models.RecursoDigital;
import interfaces.Renovable;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AlertaVencimiento {

    private final List<Prestamo> prestamosActivos;
    private final GestorNotificaciones gestorNotificaciones;

    public AlertaVencimiento(List<Prestamo> prestamosActivos, GestorNotificaciones gestorNotificaciones) {
        this.prestamosActivos = prestamosActivos;
        this.gestorNotificaciones = gestorNotificaciones;
    }

    public void verificarAlertas() {
        LocalDate hoy = LocalDate.now();
        Scanner scanner = new Scanner(System.in);

        for (Prestamo prestamo : prestamosActivos) {
            LocalDate fechaDevolucion = prestamo.getFechaDevolucion();
            long diasRestantes = hoy.until(fechaDevolucion).getDays();
            RecursoDigital recurso = prestamo.getRecurso();

            if (diasRestantes == 1) {
                gestorNotificaciones.notificar("El recurso '" + recurso.getTitulo()
                        + "' vence mañana (" + fechaDevolucion + ").", NivelUrgencia.WARNING);
            } else if (diasRestantes == 0) {
                gestorNotificaciones.notificar("HOY VENCE prestamo N°" + prestamo.getId() + "(" + recurso.getTitulo() + ")" + "(" + fechaDevolucion + ")" + "!", NivelUrgencia.WARNING);

                if (recurso instanceof Renovable) {
                    System.out.print("¿Desea renovar el prestamo N°" + prestamo.getId() + "(" + recurso.getTitulo() + ")" + "? (s/n): ");
                    String opcion = scanner.nextLine();
                    if (opcion.equalsIgnoreCase("s")) {
                        try {
                            ((Renovable) recurso).renovar();
                            gestorNotificaciones.notificar("El prestamo N°" + prestamo.getId() + "(" + recurso.getTitulo() + ")" + " fue renovado correctamente.", NivelUrgencia.INFO);
                        } catch (Exception e) {
                            gestorNotificaciones.notificar("Error al renovar prestamo N°" + prestamo.getId() + "(" + recurso.getTitulo() + ")" + " : " + e.getMessage(), NivelUrgencia.ERROR);
                        }
                    }
                }
            } else {
                gestorNotificaciones.notificar("No hay vencimientos próximos.", NivelUrgencia.INFO);
            }
        }
    }
}
