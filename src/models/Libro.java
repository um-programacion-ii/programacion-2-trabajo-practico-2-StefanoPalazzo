package models;

public class Libro extends RecursoDigital{
    private int ISBN;
    private String autor;
    private String editorial;
    private String genero;
    private int anio;
    private int cantPaginas;


    public Libro(int id, String titulo, String descripcion, int ISBN,String autor, String editorial, String genero, int anio, int cantPaginas) {
        super(id,titulo,descripcion);
        this.ISBN = ISBN;
        this.autor = autor;
        this.editorial = editorial;
        this.genero = genero;
        this.anio = anio;
        this.cantPaginas = cantPaginas;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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
    public void mostrarInformacion() {
        System.out.println("--Libro--");
        System.out.println("Titulo: " + getTitulo());
        System.out.println("Descripcion: " + getDescripcion());
        System.out.println("Autor: " + getAutor());
        System.out.println("Editorial: " + getEditorial());
        System.out.println("Genero: " + getGenero());
        System.out.println("Anio: " + getAnio());
        System.out.println("CantPaginas: " + getCantPaginas());
    }
}