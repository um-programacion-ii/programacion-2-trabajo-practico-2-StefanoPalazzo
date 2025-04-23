package models;

import interfaces.Prestable;
import interfaces.Renovable;
import java.time.LocalDate;
import exceptions.RecursoNoDisponibleException;

public class Enciclopedia extends RecursoDigital implements Prestable, Renovable {
    private String estado;
    private String autorGeneral;
    private String editorial;
    private int anio;
    private int volumen;
    private int diasPrestamo = 10;
    private int renovacionesHechas = 0;
    private final int MAX_RENOVACIONES = 1;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Enciclopedia(int id, String titulo, String descripcion, String categoria, String autorGeneral, String editorial, int anio, int volumen) {
        super(id, titulo, descripcion, CategoriaRecurso.desdeString(categoria));
        this.autorGeneral = autorGeneral;
        this.editorial = editorial;
        this.anio = anio;
        this.volumen = volumen;
        this.estado = "Disponible";
    }

    public String getAutorGeneral() {
        return autorGeneral;
    }

    public void setAutorGeneral(String autorGeneral) {
        this.autorGeneral = autorGeneral;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    @Override
    public void prestar() {
        estado = "Prestado";
        fechaPrestamo = LocalDate.now();
        fechaDevolucion = fechaPrestamo.plusDays(diasPrestamo);
    }

    @Override
    public void devolver() {
        estado = "Disponible";
    }

    @Override
    public boolean estaPrestado() {
        return estado.equalsIgnoreCase("Prestado");
    }

    @Override
    public void renovar() throws RecursoNoDisponibleException {
        if (renovacionesHechas >= MAX_RENOVACIONES) {
            throw new RecursoNoDisponibleException("No se puede renovar más veces esta enciclopedia.");
        }
        fechaDevolucion = fechaDevolucion.plusDays(diasPrestamo);
        renovacionesHechas++;
        System.out.println("La enciclopedia ha sido renovada. Nueva fecha de devolución: " + fechaDevolucion);
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("--Enciclopedia--");
        System.out.println("Título: " + getTitulo());
        System.out.println("Descripción: " + getDescripcion());
        System.out.println("Autor General: " + autorGeneral);
        System.out.println("Editorial: " + editorial);
        System.out.println("Año: " + anio);
        System.out.println("Volumen: " + volumen);
        System.out.println("Categoría: " + getCategoria());

        if (estaPrestado()) {
            System.out.println("Estado: Prestado");
            System.out.println("Fecha de préstamo: " + fechaPrestamo);
            System.out.println("Fecha de devolución: " + fechaDevolucion);
            System.out.println("Renovaciones: " + renovacionesHechas);
        } else {
            System.out.println("Estado: " + estado);
        }
    }
}
