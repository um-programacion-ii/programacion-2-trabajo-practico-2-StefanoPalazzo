package models;

public abstract class RecursoDigital {
    private String titulo;
    private String descripcion;

    public RecursoDigital(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public abstract void mostrarInformacion();

}
