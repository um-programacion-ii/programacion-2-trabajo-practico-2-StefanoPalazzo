package services;

import interfaces.IServicioNotificaciones;
import models.NivelUrgencia;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static console.Consola.gestorNotificaciones;

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

    public List<String> getHistorial() {
        System.out.println("=== Configuración de Alertas ===");
        return historial;
    }

    public static void menuConfiguracionAlertas() {
        Scanner sc = new Scanner(System.in);
        Set<NivelUrgencia> preferencias = EnumSet.noneOf(NivelUrgencia.class);

        System.out.println("=== Configuración de Alertas ===");
        for (NivelUrgencia nivel : NivelUrgencia.values()) {
            System.out.print("¿Desea activar notificaciones para " + nivel.name() + "? (s/n): ");
            String respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                preferencias.add(nivel);
            }
        }

        gestorNotificaciones.configurarPreferencias(preferencias);
        System.out.println("Preferencias actualizadas: " + preferencias);
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
