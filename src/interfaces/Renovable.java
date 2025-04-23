package interfaces;

import exceptions.RecursoNoDisponibleException;

public interface Renovable {
    void renovar() throws RecursoNoDisponibleException;
}
