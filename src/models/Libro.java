package models;

import interfaces.Prestable;
import interfaces.Renovable;

public class Libro extends RecursoDigital implements Prestable, Renovable {
    private boolean prestado;
    private int ISBN;
    private String autor;
    private String editorial;
    private int anio;
    private int cantPaginas;


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
        prestado = true;
    }

    @Override
    public void devolver() {
        prestado = false;
    }

    @Override
    public boolean estaPrestado() {
        return prestado;
    }

    @Override
    public void renovar() {
        System.out.println("El libro ha sido renovado.");
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
    }
}