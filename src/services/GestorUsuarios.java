package services;

import interfaces.IServicioNotificaciones;
import models.Usuario;

public class GestorUsuarios {
    private IServicioNotificaciones servicioNotificaciones;

    public GestorUsuarios(IServicioNotificaciones servicioNotificaciones) {
        this.servicioNotificaciones = servicioNotificaciones;
    }

    public void listarUsuarios() {
        servicioNotificaciones.enviarNotificacion("Listado de usuarios mostrado correctamente.");
    }

    public void agregarUsuario(Usuario u) {
        servicioNotificaciones.enviarNotificacion("Usuario " + u.getNombre() + " agregado con éxito.");
    }

    public void eliminarUsuario(Usuario u) {
        servicioNotificaciones.enviarNotificacion("Usuario " + u.getNombre() + " eliminado con éxito.");
    }
}
