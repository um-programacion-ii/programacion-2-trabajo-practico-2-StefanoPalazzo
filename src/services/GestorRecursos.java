package services;

import interfaces.IServicioNotificaciones;
import models.RecursoDigital;

public class GestorRecursos {
    private IServicioNotificaciones servicioNotificaciones;

    public GestorRecursos(IServicioNotificaciones servicioNotificaciones) {
        this.servicioNotificaciones = servicioNotificaciones;
    }

    public void listarRecursos() {
        servicioNotificaciones.enviarNotificacion("Listado de recursos mostrado correctamente.");
    }

    public void agregarRecurso(RecursoDigital r) {
        servicioNotificaciones.enviarNotificacion("RecursoDigital " + r.getTitulo() + " agregado con éxito.");
    }

    public void eliminarRecurso(RecursoDigital r) {
        servicioNotificaciones.enviarNotificacion("RecursoDigital " + r.getTitulo() + " eliminado con éxito.");
    }

    public void prestarRecurso(RecursoDigital r) {
        servicioNotificaciones.enviarNotificacion("RecursoDigital " + r.getTitulo() + " prestado con éxito.");
    }

    public void devolverRecurso(RecursoDigital r) {
        servicioNotificaciones.enviarNotificacion("RecursoDigital " + r.getTitulo() + " devuelto con éxito.");
    }

    public void renovarRecurso(RecursoDigital r) {
        servicioNotificaciones.enviarNotificacion("RecursoDigital " + r.getTitulo() + " renovado con éxito.");
    }
}
