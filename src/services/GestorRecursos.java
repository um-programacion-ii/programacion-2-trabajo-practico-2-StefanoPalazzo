package services;

import interfaces.IServicioNotificaciones;
import models.RecursoDigital;

import java.util.ArrayList;
import java.util.List;

public class GestorRecursos {
    private ArrayList<RecursoDigital> recursos = new ArrayList<>();
    private IServicioNotificaciones servicioNotificaciones;

    public GestorRecursos(IServicioNotificaciones servicioNotificaciones) {
        this.servicioNotificaciones = servicioNotificaciones;
        this.recursos = new ArrayList<>();

    }

    public void listarRecursos() {
        System.out.println("Lista de Recursos:");
        for (RecursoDigital r : recursos) {
            System.out.println("- " + r.getTitulo());
        }
    }

    public void agregarRecurso(RecursoDigital r) {
        recursos.add(r);
        System.out.println("✅ Recurso agregado: " + r.getTitulo());
        servicioNotificaciones.enviarNotificacion("RecursoDigital " + r.getTitulo() + " agregado con éxito.");
    }

    public void eliminarRecurso(RecursoDigital r) {
        servicioNotificaciones.enviarNotificacion("RecursoDigital " + r.getTitulo() + " eliminado con éxito.");
    }

    public void prestarRecurso(RecursoDigital r) {
        servicioNotificaciones.enviarNotificacion("RecursoDigital " + r.getTitulo() + " prestado con éxito.");
    }

    public void devolverRecurso(RecursoDigital r) {
        servicioNotificaciones.enviarNotificacion("RecursoDigital " + r.getTitulo() + " devuelto con éxito.");
    }

    public void renovarRecurso(RecursoDigital r) {
        servicioNotificaciones.enviarNotificacion("RecursoDigital " + r.getTitulo() + " renovado con éxito.");
    }

    public ArrayList<RecursoDigital> buscarRecursoPorTitulo(String titulo) {
        ArrayList<RecursoDigital> encontrados = new ArrayList<>();
        int index = 1;

        for (RecursoDigital recurso : recursos) {
            if (recurso.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                System.out.println(index + " - " + recurso.getTitulo() + " (" + recurso.getId() + ")");
                encontrados.add(recurso);
                index++;
            }
        }

        if (encontrados.isEmpty()) {
            System.out.println("No se encontraron recursos con el título ingresado.");
        }

        return encontrados;
    }


    public RecursoDigital buscarRecursoPorId(int idBuscado) {
        for (int i = 0; i < recursos.size(); i++) {
            RecursoDigital recurso = recursos.get(i);
            if (recurso.getId() == idBuscado) {
                System.out.println("Recurso encontrado:");
                System.out.println("1 - " + recurso.getTitulo() + " (" + recurso.getId() + ")");
                return recurso;
            }
        }

        System.out.println("No se encontró ningún recurso con el ID ingresado.");
        return null;
    }



}
