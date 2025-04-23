package services;

import interfaces.Prestable;
import models.RecursoDigital;
import models.Usuario;
import models.Prestamo;

import java.util.List;

public class AlertaDisponibilidad {
    private List<RecursoDigital> recursos;

    public AlertaDisponibilidad(List<RecursoDigital> recursos) {
        this.recursos = recursos;
    }

    public void verificarDisponibilidad(int idRecurso) {
        RecursoDigital recurso = recursos.stream()
                .filter(r -> r.getId() == idRecurso)
                .findFirst()
                .orElse(null);
        if (recurso instanceof Prestable prestable && !prestable.estaPrestado()) {
            System.out.println("Recurso disponible: " + recurso.getTitulo() + " (ID: " + recurso.getId() + ")");
        }else {
            System.out.println("Recurso no disponible.");
        }
    }

    public void mostrarRecursosDisponibles() {
        int contador = 1;
        for (RecursoDigital recurso : recursos) {
            if (recurso instanceof Prestable prestable && !prestable.estaPrestado()) {
                System.out.println(contador + ". " + recurso.getTitulo() + " (ID: " + recurso.getId() + ")");
                contador++;
            }
        }
        if (contador == 1) {
            System.out.println("No hay recursos disponibles.");
        }
    }

}
