package interfaces;

import models.CategoriaRecurso;

public interface IRecursoDigital {
    void mostrarInformacion();
    int getId();
    String getTitulo();
    String getDescripcion();
    CategoriaRecurso getCategoria();
}
