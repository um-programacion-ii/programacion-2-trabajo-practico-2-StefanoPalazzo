package services;

import interfaces.IServicioNotificaciones;
import interfaces.Prestable;
import interfaces.Renovable;
import models.CategoriaRecurso;
import models.NivelUrgencia;
import models.Prestamo;
import models.RecursoDigital;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GestorRecursos {
    private static List<RecursoDigital> recursos = Collections.synchronizedList(new ArrayList<>());

    private GestorNotificaciones gestorNotificaciones;

    public GestorRecursos(GestorNotificaciones gestorNotificaciones) {
        this.gestorNotificaciones = gestorNotificaciones;
        this.recursos = new ArrayList<>();


    }

    public void listarRecursos() {
        System.out.println("Lista de Recursos:");
        for (RecursoDigital r : recursos) {
            System.out.println(r.getId() + ". " + r.getTitulo());
        }
    }

    public static List<RecursoDigital> getRecursos() {
        return recursos;
    }

    public synchronized void agregarRecurso(RecursoDigital r) {
        System.out.println(Thread.currentThread().getName() + " - Intentando agregar recurso: " + r.getTitulo());
        recursos.add(r);
        gestorNotificaciones.notificar("RecursoDigital " + r.getTitulo() + " agregado con éxito.", NivelUrgencia.INFO);
        System.out.println(Thread.currentThread().getName() + " - Recurso agregado: " + r.getTitulo());
    }

    public synchronized void eliminarRecursoPorId(int id) {
        System.out.println(Thread.currentThread().getName() + " - Intentando eliminar recurso con ID: " + id);
        for (int i = 0; i < recursos.size(); i++) {
            RecursoDigital r = recursos.get(i);
            if (r.getId() == id) {
                recursos.remove(i);
                System.out.println(Thread.currentThread().getName() + " - Recurso eliminado: " + r.getTitulo());
                gestorNotificaciones.notificar("RecursoDigital " + r.getTitulo() + " eliminado con éxito.", NivelUrgencia.INFO);
                return;
            }
        }
        System.out.println(Thread.currentThread().getName() + " - No se encontró ningún recurso con el ID ingresado.");
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



    public static RecursoDigital buscarRecursoPorId(int idBuscado) {
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

    public RecursoDigital buscarYMostrarRecursoPorID(int idBuscado) {
        for (int i = 0; i < recursos.size(); i++) {
            RecursoDigital recurso = recursos.get(i);
            if (recurso.getId() == idBuscado) {
                System.out.println("Recurso encontrado:");
                recurso.mostrarInformacion();
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
