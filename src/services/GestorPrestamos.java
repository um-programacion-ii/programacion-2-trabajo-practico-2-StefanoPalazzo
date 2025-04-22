package services;

import exceptions.RecursoNoDisponibleException;
import exceptions.UsuarioNoEncontradoException;
import interfaces.IServicioNotificaciones;
import interfaces.Prestable;
import models.CategoriaRecurso;
import models.Prestamo;
import models.RecursoDigital;
import models.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class GestorPrestamos {
    private static List<Prestamo> prestamosTotales = Collections.synchronizedList(new ArrayList<>());
    private static List<Prestamo> prestamosActivos = Collections.synchronizedList(new ArrayList<>());

    private static IServicioNotificaciones servicioNotificaciones;

    public GestorPrestamos(IServicioNotificaciones servicioNotificaciones){
        this.servicioNotificaciones = servicioNotificaciones;
        this.prestamosTotales = new ArrayList<>();
        this.prestamosActivos = new ArrayList<>();

    }

    public List<Prestamo> getPrestamosTotales() {
        return prestamosTotales;
    }

    public List<Prestamo> getPrestamosActivos() {
        return prestamosActivos;
    }

    public synchronized static Prestamo prestarRecurso(int idRecurso, int idUsuario) throws RecursoNoDisponibleException, UsuarioNoEncontradoException {
        System.out.println(Thread.currentThread().getName() + " - Intentando prestar recurso ID: " + idRecurso);
        RecursoDigital recurso = GestorRecursos.buscarRecursoPorId(idRecurso);
        Usuario usuario = GestorUsuarios.buscarUsuarioPorId(idUsuario);
        System.out.println(Thread.currentThread().getName() + " - Dentro de la sección sincronizada.");
        if (recurso == null) {
            throw new RecursoNoDisponibleException("Recurso no válido.");
        }

        if (!(recurso instanceof Prestable recursoPrestable)) {
            throw new exceptions.RecursoNoDisponibleException("Este tipo de recurso no puede ser prestado.");
        }


        Prestamo nuevoPrestamo;
        synchronized (recursoPrestable) {
            if (recursoPrestable.estaPrestado()) {
                throw new RecursoNoDisponibleException("El recurso ya está prestado.");
            }
            nuevoPrestamo = new Prestamo(recurso, usuario);
            recursoPrestable.prestar();
        }

        nuevoPrestamo.setFechaDevolucion();
        prestamosTotales.add(nuevoPrestamo);
        prestamosActivos.add(nuevoPrestamo);
        servicioNotificaciones.enviarNotificacion("RecursoDigital " + recurso.getTitulo() +
                " ( " + recurso.getId() + " ) " + " prestado a " +
                usuario.getNombre() + " " + usuario.getApellido() + " ( " + usuario.getId() + " ) ");
        System.out.println(Thread.currentThread().getName() + " - Préstamo realizado con éxito.");
        return nuevoPrestamo;
    }

    public synchronized void devolverPrestamo(int idPrestamo) throws RecursoNoDisponibleException {
        System.out.println(Thread.currentThread().getName() + " - Intentando devolver préstamo ID: " + idPrestamo);

        Prestamo prestamo = prestamosActivos.stream()
                .filter(p -> p.getId() == idPrestamo && !p.isDevuelto())
                .findFirst()
                .orElse(null);

        if (prestamo == null) {
            throw new RecursoNoDisponibleException("No se encontró un préstamo activo con ID " + idPrestamo);
        }

        RecursoDigital recurso = prestamo.getRecurso();

        if (!(recurso instanceof Prestable recursoPrestable)) {
            throw new RecursoNoDisponibleException("Este tipo de recurso no puede ser devuelto.");
        }

        if (!recursoPrestable.estaPrestado()) {
            throw new RecursoNoDisponibleException("El recurso no está marcado como prestado.");
        }

        prestamo.marcarComoDevuelto();
        recursoPrestable.devolver();
        prestamosActivos.remove(prestamo);

        Usuario usuario = prestamo.getUsuario();

        servicioNotificaciones.enviarNotificacion(
                "RecursoDigital " + recurso.getTitulo() + " devuelto con éxito por " +
                        usuario.getNombre() + " " + usuario.getApellido());

        System.out.println(Thread.currentThread().getName() + " - Préstamo devuelto con éxito: " + recurso.getTitulo());

    }



    public synchronized void renovarPrestamo(int idPrestamo) throws RecursoNoDisponibleException {
        Prestamo prestamo = prestamosActivos.stream()
                .filter(p -> p.getId() == idPrestamo && !p.isDevuelto())
                .findFirst()
                .orElse(null);

        if (prestamo == null) {
            throw new RecursoNoDisponibleException("No se encontró un préstamo activo con ID " + idPrestamo);
        }

        prestamo.renovar();

        servicioNotificaciones.enviarNotificacion(
                "Préstamo del recurso \"" + prestamo.getRecurso().getTitulo() + "\" renovado. Nueva fecha de devolución: " +
                        prestamo.getFechaDevolucion());

        System.out.println("Préstamo renovado con éxito. Nueva fecha de devolución: " + prestamo.getFechaDevolucion());
    }


    private Prestamo buscarPrestamoActivo(RecursoDigital recurso) {
        for (Prestamo p : prestamosActivos) {
            if (p.getRecurso().equals(recurso) && !p.isDevuelto()) {
                return p;
            }
        }
        return null;
    }

    public void listarPrestamosActivos() {
        for (Prestamo prestamo : prestamosActivos) {
            System.out.println(prestamo.getId() + " - " + prestamo.getRecurso().getTitulo() + " (" + prestamo.getRecurso().getId() + ") | Usuario: "
                    + prestamo.getUsuario().getNombre() + " " + prestamo.getUsuario().getApellido() + " (DNI: " + prestamo.getUsuario().getId() + ") | Fecha de devolución: "
                    + (prestamo.getFechaDevolucion() != null ? prestamo.getFechaDevolucion() : "No devuelto aún"));
        }
    }
    public void reporteRecursosMasPrestados() {
        Map<RecursoDigital, Long> conteo = prestamosTotales.stream()
                .collect(Collectors.groupingBy(Prestamo::getRecurso, Collectors.counting()));

        System.out.println("📚 Recursos más prestados:");
        AtomicInteger contador = new AtomicInteger(1);
        conteo.entrySet().stream()
                .sorted(Map.Entry.<RecursoDigital, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> System.out.println(
                        contador.getAndIncrement() + ". " + entry.getKey().getTitulo() + " - " + entry.getValue() + " préstamos"));
    }

    public void reporteUsuariosMasActivos() {
        Map<Usuario, Long> conteo = prestamosTotales.stream()
                .collect(Collectors.groupingBy(Prestamo::getUsuario, Collectors.counting()));

        System.out.println("👤 Usuarios más activos:");
        AtomicInteger contador = new AtomicInteger(1);
        conteo.entrySet().stream()
                .sorted(Map.Entry.<Usuario, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> System.out.println(
                        contador.getAndIncrement() + ". " + entry.getKey().getNombre() + " " + entry.getKey().getApellido()
                                + " - " + entry.getValue() + " préstamos"));
    }


    public void estadisticasPorCategoria() {
        Map<CategoriaRecurso, Long> conteoPorCategoria = prestamosTotales.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getRecurso().getCategoria(), Collectors.counting()
                ));

        System.out.println("📊 Estadísticas por categoría:");
        conteoPorCategoria.forEach((categoria, cantidad) -> {
            System.out.println(categoria + ": " + cantidad + " préstamos");
        });
    }


}
