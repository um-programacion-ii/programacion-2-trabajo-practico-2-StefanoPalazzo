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
        System.out.println("Lista de recursos disponibles:");
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
        System.out.println("Lista de recursos disponibles:");
        for (RecursoDigital recurso : recursos) {
            if (recurso instanceof Prestable prestable && !prestable.estaPrestado()) {
                System.out.println(contador + ". Recurso disponible: " + recurso.getTitulo() + " (ID: " + recurso.getId() + ")");
                contador++;
            }
        }
    }


    public void prestarRecurso(RecursoDigital recurso, Usuario usuario) {
        if (recurso instanceof Prestable prestable) {
            if (!prestable.estaPrestado()) {
                prestable.prestar();
                Prestamo prestamo = new Prestamo(recurso, usuario);
                System.out.println("Préstamo exitoso: " + recurso.getTitulo() + " a " + usuario.getNombre());
            } else {
                System.out.println("El recurso ya está prestado.");
            }
        } else {
            System.out.println("El recurso no es prestable.");
        }
    }
}
