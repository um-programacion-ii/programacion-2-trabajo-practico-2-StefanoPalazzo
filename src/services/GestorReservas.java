package services;

import interfaces.IServicioNotificaciones;
import interfaces.Prestable;
import models.Prestamo;
import models.RecursoDigital;
import models.Reserva;
import models.Usuario;

import java.util.concurrent.PriorityBlockingQueue;

import exceptions.RecursoNoDisponibleException;

public class GestorReservas {
    private static final PriorityBlockingQueue<Reserva> colaReservas = new PriorityBlockingQueue<>();
    private static IServicioNotificaciones servicioNotificaciones;

    public GestorReservas(IServicioNotificaciones servicioNotificaciones) {
        this.servicioNotificaciones = servicioNotificaciones;
    }

    public static void agregarReserva(int idRecurso, int idUsuario) throws RecursoNoDisponibleException {
        RecursoDigital recurso = GestorRecursos.buscarRecursoPorId(idRecurso);
        Usuario usuario;

        if (!(recurso instanceof Prestable recursoPrestable)) {
            throw new RecursoNoDisponibleException("Este tipo de recurso no puede ser prestado.");
        }

        try {
            usuario = GestorUsuarios.buscarUsuarioPorId(idUsuario);
        } catch (Exception e) {
            System.out.println("Error al buscar el usuario: " + e.getMessage());
            return;
        }
        Reserva reserva = new Reserva(usuario, recurso);
        colaReservas.add(reserva);
        System.out.println("Reserva agregada a la cola: " + reserva);
    }

    public static Reserva obtenerProximaReservaDeRecurso(int idRecurso) {
        for (Reserva reserva : colaReservas) {
            if (reserva.getRecurso().getId() == idRecurso) {
                return reserva;
            }
        }
        return null;

    }

    public static Reserva procesarProximaReservaDeRecurso(int idRecurso) throws exceptions.UsuarioNoEncontradoException, exceptions.RecursoNoDisponibleException {
        Reserva reserva = obtenerProximaReservaDeRecurso(idRecurso);
        if (reserva != null) {
            colaReservas.remove(reserva);
            System.out.println("Reserva procesada: " + reserva);
            Usuario usuario = reserva.getUsuario();
            int usuarioId = usuario.getId();
            RecursoDigital recurso = reserva.getRecurso();
            GestorPrestamos.prestarRecurso(recurso.getId(), usuario.getId());
            servicioNotificaciones.enviarNotificacion("RecursoDigital " + recurso.getTitulo() +
                    " ( " + recurso.getId() + " ) " + " prestado a " +
                    usuario.getNombre() + " " + usuario.getApellido() + " ( " + usuario.getId() + " ) ");
            return reserva;
        } else {
            System.out.println("No hay reservas pendientes para el recurso con ID " + idRecurso);
            return null;
        }
    }

    public static void mostrarReservas() {
        if (colaReservas.isEmpty()) {
            System.out.println("No hay reservas en la cola.");
            return;
        }

        System.out.println("Reservas pendientes:");
        for (Reserva reserva : colaReservas) {
            System.out.println(reserva.toString());
        }
    }

    public static boolean hayReservasPendientes() {
        return !colaReservas.isEmpty();
    }

    public static Reserva buscarReservaPorId(int id) {
        for (Reserva reserva : colaReservas) {
            if (reserva.getId() == id) {
                return reserva;
            }
        }
        return null;
    }
}
