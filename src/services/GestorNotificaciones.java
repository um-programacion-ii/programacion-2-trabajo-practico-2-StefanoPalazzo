package services;

import interfaces.IServicioNotificaciones;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GestorNotificaciones {
    private final ExecutorService executor;
    private final IServicioNotificaciones canal;

    public GestorNotificaciones(IServicioNotificaciones canal) {
        this.executor = Executors.newFixedThreadPool(2); // O un valor mayor si esperás muchas notificaciones
        this.canal = canal;
    }

    public void notificar(String mensaje) {
        executor.submit(() -> canal.enviarNotificacion(mensaje));
    }

    public void cerrar() {
        executor.shutdown();
    }
}
