package services;

import interfaces.IServicioNotificaciones;
import interfaces.Prestable;
import models.CategoriaRecurso;
import models.RecursoDigital;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public void prestarRecursoPorId(String idStr) throws exceptions.RecursoNoDisponibleException {
        try {
            int id = Integer.parseInt(idStr);
            RecursoDigital recurso = buscarRecursoPorId(id);

            if (recurso == null) {
                throw new exceptions.RecursoNoDisponibleException("No se encontró un recurso con ID " + id);
            }

            if (!(recurso instanceof Prestable recursoPrestable)) {
                throw new exceptions.RecursoNoDisponibleException("Este tipo de recurso no puede ser prestado.");
            }

            if (recursoPrestable.estaPrestado()) {
                throw new exceptions.RecursoNoDisponibleException("El recurso ya está prestado.");
            }

            recursoPrestable.prestar();
            servicioNotificaciones.enviarNotificacion("RecursoDigital " + recurso.getTitulo() + " prestado con éxito.");
            System.out.println("✅ Recurso prestado con éxito: " + recurso.getTitulo());

        } catch (NumberFormatException e) {
            System.out.println("El ID ingresado no es un número válido.");
        }
    }

    public void devolverRecurso(RecursoDigital r) {
        servicioNotificaciones.enviarNotificacion("RecursoDigital " + r.getTitulo() + " devuelto con éxito.");
    }

    public void renovarRecurso(RecursoDigital r) {
        servicioNotificaciones.enviarNotificacion("RecursoDigital " + r.getTitulo() + " renovado con éxito.");
    }

    public List<RecursoDigital> buscarRecursoPorTitulo(String texto) {
        return recursos.stream()
                .filter(r -> r.getTitulo().toLowerCase().contains(texto.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<RecursoDigital> filtrarPorCategoria(String categoria) {
        CategoriaRecurso categoriaEnum = CategoriaRecurso.desdeString(categoria); // Conversión del string a CategoriaRecurso
        return recursos.stream()
                .filter(r -> r.getCategoria() == categoriaEnum)
                .collect(Collectors.toList());
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

    public List<RecursoDigital> ordenarPorTitulo() {
        return recursos.stream()
                .sorted(Comparator.comparing(RecursoDigital::getTitulo))
                .collect(Collectors.toList());
    }

    public List<RecursoDigital> ordenarPorId() {
        return recursos.stream()
                .sorted(Comparator.comparingInt(RecursoDigital::getId))
                .collect(Collectors.toList());
    }



}
