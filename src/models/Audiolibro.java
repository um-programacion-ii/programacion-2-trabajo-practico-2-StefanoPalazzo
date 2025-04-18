package models;

public class Audiolibro extends RecursoDigital {
    private String autor;
    private int duracion;
    private String narrador;

    public Audiolibro(int id, String titulo, String descripcion, String categoria, String autor, int duracion, String narrador) {
        super(id, titulo, descripcion, CategoriaRecurso.desdeString(categoria));
        this.autor = autor;
        this.duracion = duracion;
        this.narrador = narrador;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getNarrador() {
        return narrador;
    }

    public void setNarrador(String narrador) {
        this.narrador = narrador;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("--Audiolibro--");
        System.out.println("Título: " + getTitulo());
        System.out.println("Descripción: " + getDescripcion());
        System.out.println("Autor: " + getAutor());
        System.out.println("Duración: " + getDuracion() + " minutos");
        System.out.println("Narrador: " + getNarrador());
        System.out.println("Categoria: " + getCategoria());
    }
}
