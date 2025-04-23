package services;

import interfaces.IServicioNotificaciones;
import models.NivelUrgencia;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GestorNotificaciones {
    private final IServicioNotificaciones canal;
    private final List<String> historial;
    private final ExecutorService executor;
    private final ScheduledExecutorService scheduler;
    private static Set<NivelUrgencia> nivelesPermitidos;

    public GestorNotificaciones(IServicioNotificaciones canal) {
        this.executor = Executors.newFixedThreadPool(2);
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.canal = canal;
        this.historial = new ArrayList<>();
        this.nivelesPermitidos = EnumSet.allOf(NivelUrgencia.class);
    }

    public void notificar(String mensaje, NivelUrgencia nivel) {
        if (!nivelesPermitidos.contains(nivel)) return;

        String mensajeFormateado = "[" + nivel.name() + "] " + mensaje;
        historial.add(mensajeFormateado);
        executor.submit(() -> canal.enviarNotificacion(mensajeFormateado));
    }

    public void mostrarHistorial() {
        System.out.println("📋 Historial de Alertas:");
        historial.forEach(System.out::println);
    }

    public void configurarPreferencias(Set<NivelUrgencia> niveles) {
        this.nivelesPermitidos = niveles;
    }

    public void iniciarRecordatoriosPeriodicos(Runnable tarea, long delaySegundos) {
        scheduler.scheduleAtFixedRate(tarea, 0, delaySegundos, TimeUnit.SECONDS);
    }

    public void cerrar() {
        executor.shutdown();
        scheduler.shutdown();
    }
}
