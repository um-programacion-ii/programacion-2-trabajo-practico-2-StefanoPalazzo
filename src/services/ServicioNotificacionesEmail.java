package services;

import interfaces.IServicioNotificaciones;

public class ServicioNotificacionesEmail implements IServicioNotificaciones {
    public void enviarNotificacion(String mensaje){
        System.out.println("[EMAIL] " + mensaje);
    }
}
