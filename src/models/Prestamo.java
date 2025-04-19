package models;

import java.time.LocalDate;

public class Prestamo {
    private static int contadorPrestamos = 1;
    private final Usuario usuario;

    private int id;
    private int diasPrestamo;
    private RecursoDigital recurso;
    private LocalDate fechaPrestamo;
    public LocalDate fechaDevolucion;
    private boolean devuelto;

    public Prestamo(RecursoDigital recurso, Usuario usuario) {
        this.id = contadorPrestamos++;
        this.recurso = recurso;
        this.usuario = usuario;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = null;
        this.devuelto = false;
        this.diasPrestamo = 7;
    }

    public int getId() {
        return id;
    }

    public RecursoDigital getRecurso() {
        return recurso;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(){
        this.fechaDevolucion = LocalDate.now().plusDays(diasPrestamo);
    }



    public boolean isDevuelto() {
        return devuelto;
    }

    public void marcarComoDevuelto() {
        this.devuelto = true;
        this.fechaDevolucion = LocalDate.now();
    }

    public void renovar() {
        if (fechaDevolucion == null) {
            setFechaDevolucion();
        } else {
            this.fechaDevolucion = this.fechaDevolucion.plusDays(diasPrestamo);
        }
    }

    @Override
    public String toString() {
        return "Préstamo #" + id +
                " | Recurso: " + recurso.getTitulo() +
                " | Fecha préstamo: " + fechaPrestamo +
                (devuelto ? " | Devuelto el: " + fechaDevolucion : " | No devuelto aún");
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
