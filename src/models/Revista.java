package models;

import interfaces.Renovable;

import java.time.LocalDate;

public class Revista extends RecursoDigital implements Renovable {
    private String editorial;
    private int numero;
    private LocalDate fechaPublicacion;

    public Revista(int id, String titulo, String descripcion, String editorial, int numero, LocalDate fechaPublicacion) {
        super(id, titulo, descripcion);
        this.editorial = editorial;
        this.numero = numero;
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    @Override
    public void renovar() {
        System.out.println("La revista ha sido renovada.");
    }


    @Override
    public void mostrarInformacion() {
        System.out.println("--Revista--");
        System.out.println("Título: " + getTitulo());
        System.out.println("Descripción: " + getDescripcion());
        System.out.println("Editorial: " + getEditorial());
        System.out.println("Número: " + getNumero());
        System.out.println("Fecha de Publicación: " + getFechaPublicacion());
    }
}
