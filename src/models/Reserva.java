package models;

import java.time.LocalDateTime;

public class Reserva implements Comparable<Reserva> {
    private static int contadorReservas = 1;

    private int id;
    private Usuario usuario;
    private RecursoDigital recurso;
    private LocalDateTime fechaHoraReserva;

    public Reserva(Usuario usuario, RecursoDigital recurso) {
        this.id = contadorReservas++;
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaHoraReserva = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoDigital getRecurso() {
        return recurso;
    }

    public LocalDateTime getFechaHoraReserva() {
        return fechaHoraReserva;
    }

    @Override
    public int compareTo(Reserva otra) {
        // Prioridad por fecha y hora (más antigua = más prioridad)
        return this.fechaHoraReserva.compareTo(otra.fechaHoraReserva);
    }

    @Override
    public String toString() {
        return "Reserva #" + id +
                " | Usuario: " + usuario.getNombre() +
                " | Recurso: " + recurso.getTitulo() +
                " | Fecha y hora: " + fechaHoraReserva;
    }
}
