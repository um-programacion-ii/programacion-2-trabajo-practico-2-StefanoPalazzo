package services;

import interfaces.IServicioNotificaciones;

public class ServicioNotificacionesSMS  implements IServicioNotificaciones {
    public void enviarNotificacion(String mensaje){
        System.out.println("[SMS] " + mensaje);
    }
}
