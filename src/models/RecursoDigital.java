package models;

import interfaces.IRecursoDigital;

import java.util.ArrayList;
import java.util.List;

public abstract class RecursoDigital implements IRecursoDigital {
    private int id;
    private String titulo;
    private String descripcion;
    private CategoriaRecurso categoria;

    public RecursoDigital(int id, String titulo, String descripcion, CategoriaRecurso categoria) {
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

    public CategoriaRecurso getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaRecurso categoria) {
        this.categoria = categoria;
    }

    public void asignarCategoria(String categoriaString) {
        this.setCategoria(CategoriaRecurso.desdeString(categoriaString));
    }

    public List<RecursoDigital> buscarPorCategoria(CategoriaRecurso categoria, List<RecursoDigital> recursos) {
        List<RecursoDigital> resultado = new ArrayList<>();
        for (RecursoDigital recurso : recursos) {
            if (recurso.getCategoria() == categoria) {
                resultado.add(recurso);
            }
        }
        return resultado;
    }


    @Override
    public abstract void mostrarInformacion();
}
