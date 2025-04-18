package models;

import interfaces.IRecursoDigital;

public abstract class RecursoDigital implements IRecursoDigital {
    private int id;
    private String titulo;
    private String descripcion;
    private String categoria;

    public RecursoDigital(int id, String titulo, String descripcion, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }
    @Override
    public int getId() {
        return id;
    }
    @Override
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public abstract void mostrarInformacion();
}
