package services;

import exceptions.RecursoNoDisponibleException;
import exceptions.UsuarioNoEncontradoException;
import interfaces.IServicioNotificaciones;
import interfaces.Prestable;
import models.*;

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

    private static GestorNotificaciones gestorNotificaciones;

    public GestorPrestamos(GestorNotificaciones gestorNotificaciones){
        this.gestorNotificaciones = gestorNotificaciones;
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
            gestorNotificaciones.notificar("Recurso no válido.", NivelUrgencia.ERROR);
            throw new RecursoNoDisponibleException("Recurso no válido.");
        }

        if (!(recurso instanceof Prestable recursoPrestable)) {
            gestorNotificaciones.notificar("Este tipo de recurso no puede ser prestado.", NivelUrgencia.ERROR);
            throw new exceptions.RecursoNoDisponibleException("Este tipo de recurso no puede ser prestado.");
        }


        Prestamo nuevoPrestamo;
        synchronized (recursoPrestable) {
            if (recursoPrestable.estaPrestado()) {
                gestorNotificaciones.notificar("El recurso ya está prestado.", NivelUrgencia.ERROR);
                throw new RecursoNoDisponibleException("El recurso ya está prestado.");
            }
            nuevoPrestamo = new Prestamo(recurso, usuario);
            recursoPrestable.prestar();
        }

        nuevoPrestamo.setFechaDevolucion();
        prestamosTotales.add(nuevoPrestamo);
        prestamosActivos.add(nuevoPrestamo);
        gestorNotificaciones.notificar(Thread.currentThread().getName() + "- RecursoDigital " + recurso.getTitulo() +
                " ( " + recurso.getId() + " ) " + " prestado a " +
                usuario.getNombre() + " " + usuario.getApellido() + " ( " + usuario.getId() + " ) ", NivelUrgencia.INFO);
        return nuevoPrestamo;
    }

    public synchronized void devolverPrestamo(int idPrestamo) throws RecursoNoDisponibleException {
        gestorNotificaciones.notificar(Thread.currentThread().getName() + " - Intentando devolver préstamo ID: " + idPrestamo, NivelUrgencia.INFO);
        Prestamo prestamo = prestamosActivos.stream()
                .filter(p -> p.getId() == idPrestamo && !p.isDevuelto())
                .findFirst()
                .orElse(null);

        if (prestamo == null) {
            gestorNotificaciones.notificar(Thread.currentThread().getName() +" - No se encontró un préstamo activo con ID: " + idPrestamo, NivelUrgencia.ERROR);
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

        gestorNotificaciones.notificar(
                "RecursoDigital " + recurso.getTitulo() + " devuelto con éxito por " +
                        usuario.getNombre() + " " + usuario.getApellido(), NivelUrgencia.INFO);

        System.out.println(Thread.currentThread().getName() + " - Préstamo devuelto con éxito: " + recurso.getTitulo());
        verificarReservas(recurso.getId());

    }

    public void verificarReservas(int idRecurso) {
            RecursoDigital recurso = GestorRecursos.buscarRecursoPorId(idRecurso);
            Reserva reserva = GestorReservas.obtenerProximaReservaDeRecurso(idRecurso);
            if (reserva != null) {
                System.out.println("El recurso tiene reservas pendientes. Desea procesar la reserva de usuario: "+ reserva.getUsuario() +" ? (S/N)");
                try {
                    int respuesta = System.in.read();
                    if (respuesta == 'S' || respuesta == 's') {
                        GestorReservas.procesarProximaReservaDeRecurso(idRecurso);
                    } else {
                        System.out.println("Reserva no procesada.");
                    }
                } catch (Exception e) {
                    System.out.println("Error al procesar la reserva: " + e.getMessage());
                }
            }
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

        gestorNotificaciones.notificar(
                "Préstamo del recurso \"" + prestamo.getRecurso().getTitulo() + "\" renovado. Nueva fecha de devolución: " +
                        prestamo.getFechaDevolucion(), NivelUrgencia.INFO);

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
        // Crear el mapa de conteo de recursos
        Map<RecursoDigital, Long> conteo = prestamosTotales.stream()
                .collect(Collectors.groupingBy(Prestamo::getRecurso, Collectors.counting()));

        System.out.println("📚 Recursos más prestados:");

        // Inicializar contador para los recursos y obtener el total de recursos
        AtomicInteger contador = new AtomicInteger(1);
        int totalRecursos = conteo.size();

        // Mostrar mensaje de generación de reporte
        System.out.println("Generando reporte...");

        // Mostrar progreso hasta el 100%
        for (int i = 0; i < totalRecursos; i++) {
            mostrarProgreso(i + 1, totalRecursos);
            try {
                // Espera para que el progreso se vea en la consola
                Thread.sleep(700); // Esto es solo para simular el progreso (puedes ajustar el tiempo de espera)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(" ");

        // Imprimir los resultados después de que el progreso haya llegado al 100%
        conteo.entrySet().stream()
                .sorted(Map.Entry.<RecursoDigital, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> {
                    // Imprimir el recurso en una nueva línea
                    System.out.println(contador.get() + ". " + entry.getKey().getTitulo() + " - " + entry.getValue() + " préstamos");
                    contador.getAndIncrement();
                });

        // Finalizar el progreso al 100% después de procesar todos los recursos
//        mostrarProgreso(totalRecursos, totalRecursos);

        // Imprimir el mensaje final
        System.out.println("\n¡Reporte completo!");
    }

    private void mostrarProgreso(int actual, int total) {
        // Evitar que el progreso exceda el 100%
        int porcentaje = Math.min((int) ((double) actual / total * 100), 100);

        // Mostrar el progreso en la misma línea y sobrescribir la línea anterior
        System.out.print("\rProgreso: " + porcentaje + "%");
    }




    public synchronized void reporteUsuariosMasActivos() {
        Map<Usuario, Long> conteo = prestamosTotales.stream()
                .collect(Collectors.groupingBy(Prestamo::getUsuario, Collectors.counting()));

        System.out.println("👤 Usuarios más activos:");
        System.out.println("Generando reporte...");

        AtomicInteger contador = new AtomicInteger(1);
        int totalUsuarios = conteo.size();

        for (int i = 0; i < totalUsuarios; i++) {
            mostrarProgreso(i + 1, totalUsuarios);
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(" ");

        conteo.entrySet().stream()
                .sorted(Map.Entry.<Usuario, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> System.out.println(
                        contador.getAndIncrement() + ". " + entry.getKey().getNombre() + " " + entry.getKey().getApellido()
                                + " - " + entry.getValue() + " préstamos"));

//        mostrarProgreso(totalUsuarios, totalUsuarios);
        System.out.println("\n¡Reporte completo!");
    }


    public synchronized void estadisticasPorCategoria() {
        Map<CategoriaRecurso, Long> conteoPorCategoria = prestamosTotales.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getRecurso().getCategoria(), Collectors.counting()
                ));

        System.out.println("📊 Estadísticas por categoría:");
        System.out.println("Generando reporte...");

        int totalCategorias = conteoPorCategoria.size();
        for (int i = 0; i < totalCategorias; i++) {
            mostrarProgreso(i + 1, totalCategorias);
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(" ");

        conteoPorCategoria.forEach((categoria, cantidad) -> {
            System.out.println(categoria + ": " + cantidad + " préstamos");
        });

//        mostrarProgreso(totalCategorias, totalCategorias);
        System.out.println("\n¡Reporte completo!");
    }



}
