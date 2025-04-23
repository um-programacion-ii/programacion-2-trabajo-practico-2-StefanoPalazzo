package services;

import interfaces.IServicioNotificaciones;
import interfaces.Prestable;
import models.*;

import java.util.concurrent.PriorityBlockingQueue;

import exceptions.RecursoNoDisponibleException;

public class GestorReservas {
    private static final PriorityBlockingQueue<Reserva> colaReservas = new PriorityBlockingQueue<>();
    private static GestorNotificaciones gestorNotificaciones;

    public GestorReservas(GestorNotificaciones gestorNotificaciones) {
        this.gestorNotificaciones = gestorNotificaciones;
    }

    public synchronized static void agregarReserva(int idRecurso, int idUsuario) throws RecursoNoDisponibleException {
        System.out.println(Thread.currentThread().getName() + " - Intentando agregar reserva para recurso ID: " + idRecurso + " y usuario ID: " + idUsuario);
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
        System.out.println(Thread.currentThread().getName() + " - Reserva agregada con éxito: " + reserva);
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
            gestorNotificaciones.notificar("RecursoDigital " + recurso.getTitulo() +
                    " ( " + recurso.getId() + " ) " + " prestado a " +
                    usuario.getNombre() + " " + usuario.getApellido() + " ( " + usuario.getId() + " ) ", NivelUrgencia.INFO);
            return reserva;
        } else {
            gestorNotificaciones.notificar("No hay reservas pendientes para el recurso con ID " + idRecurso, NivelUrgencia.ERROR);
            return null;
        }
    }

    public static void mostrarReservas() {
        if (colaReservas.isEmpty()) {
            gestorNotificaciones.notificar("No hay reservas en la cola.", NivelUrgencia.INFO);
            return;
        }

        System.out.println("Reservas pendientes:");
        for (Reserva reserva : colaReservas) {
            System.out.println(reserva.toString());
        }
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
