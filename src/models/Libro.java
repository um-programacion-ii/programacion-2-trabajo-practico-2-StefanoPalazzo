package models;

import interfaces.Prestable;
import interfaces.Renovable;
import java.time.LocalDate;
import exceptions.RecursoNoDisponibleException;


public class Libro extends RecursoDigital implements Prestable, Renovable {
    private String estado;
    private int ISBN;
    private String autor;
    private String editorial;
    private int anio;
    private int cantPaginas;
    private int diasPrestamo = 14;
    private int renovacionesHechas = 0;
    private final int MAX_RENOVACIONES = 2;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;


    public Libro(int id, String titulo, String descripcion, String categoria, String ISBN,String autor, String editorial, int anio, int cantPaginas) {
        super(id, titulo, descripcion, CategoriaRecurso.desdeString(categoria));
        this.ISBN = Integer.parseInt(ISBN);
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
        this.cantPaginas = cantPaginas;
    }

    public Libro(int id, String titulo, String descripcion, String categoria, String isbn, String autor, String editorial, int anio) {
        super(id, titulo, descripcion, CategoriaRecurso.desdeString(categoria));
        this.ISBN = ISBN;
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
        estado = "Disponible";

    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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

    public int getCantPaginas() {
        return cantPaginas;
    }

    public void setCantPaginas(int cantPaginas) {
        this.cantPaginas = cantPaginas;
    }

    @Override
    public void prestar() {
        estado = "Prestado";
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
            throw new RecursoNoDisponibleException("No se puede renovar más veces este libro.");
        }
        fechaDevolucion = fechaDevolucion.plusDays(diasPrestamo);
        renovacionesHechas++;
        System.out.println("El libro ha sido renovado. Nueva fecha de devolución: " + fechaDevolucion);
    }


    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("--Libro--");
        System.out.println("Titulo: " + getTitulo());
        System.out.println("Descripcion: " + getDescripcion());
        System.out.println("Autor: " + getAutor());
        System.out.println("Editorial: " + getEditorial());
        System.out.println("Categoria: " + getCategoria());
        System.out.println("Anio: " + getAnio());
        System.out.println("CantPaginas: " + getCantPaginas());
        if (estaPrestado()) {
            System.out.println("Estado: Prestado");
            System.out.println("Fecha de prestamo: " + fechaPrestamo);
            System.out.println("Fecha de devolucion: " + fechaDevolucion);
            System.out.println("Renovaciones: " + renovacionesHechas);
        } else {
            System.out.println("Estado: " + estado);
        }
    }
}