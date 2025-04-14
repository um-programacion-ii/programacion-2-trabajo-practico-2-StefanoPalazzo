package models;

import interfaces.IRecursoDigital;

public abstract class RecursoDigital implements IRecursoDigital {
    private int id;
    private String titulo;
    private String descripcion;

    public RecursoDigital(int id, String titulo, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
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

    @Override
    public abstract void mostrarInformacion();
}
