package services;

import console.ConsolaUsuarios;
import interfaces.IServicioNotificaciones;
import models.NivelUrgencia;
import models.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorUsuarios {
    private IServicioNotificaciones servicioNotificaciones;
    private static GestorNotificaciones gestorNotificaciones;
    public static Map<Integer, Usuario> usuarios = new HashMap<>();

    public GestorUsuarios(GestorNotificaciones gestorNotificaciones) {
        this.gestorNotificaciones = gestorNotificaciones;
    }

    public void listarUsuarios() {
        System.out.println("Lista de Usuarios:");
        for (Usuario u : usuarios.values()) {
            System.out.println("- " + u.getNombre() + " " + u.getApellido() + "(" + u.getId() + ")");
        }
        gestorNotificaciones.notificar("Listado de usuarios mostrado correctamente.", NivelUrgencia.INFO);
    }


    public static synchronized void agregarUsuario(Usuario usuario) {
        System.out.println(Thread.currentThread().getName() + " - Intentando agregar usuario: " + usuario.getNombre() + " " + usuario.getApellido());
        usuarios.put(usuario.getId(), usuario);
        System.out.println(Thread.currentThread().getName() + " - Usuario agregado: "+ usuario.getNombre() + " " + usuario.getApellido() + "(" + usuario.getId() + ")");
    }

    public synchronized void eliminarUsuario(int id) {
        System.out.println(Thread.currentThread().getName() + " - Intentando eliminar usuario con ID: " + id);
        if (usuarios.containsKey(id)){
            Usuario u = usuarios.get(id);
            String nombre = u.getNombre() + " " + u.getApellido() + "(" + u.getId() + ")";
            usuarios.remove(id);
            gestorNotificaciones.notificar("Usuario " + nombre + " eliminado con éxito.", NivelUrgencia.INFO);
            System.out.println(Thread.currentThread().getName() + " - Usuario eliminado: " + nombre);
        } else {
            System.out.println(Thread.currentThread().getName() + " - No se encontró ningún usuario con el ID ingresado.");
            gestorNotificaciones.notificar("Usuario no encontrado.", NivelUrgencia.ERROR);
        }
    }

    public List<Usuario> buscarUsuarioPorNombreOApellido(String textoBuscado) throws exceptions.UsuarioNoEncontradoException {
        List<Usuario> encontrados = new ArrayList<>();
        int index = 1;

        for (Usuario usuario : usuarios.values()) {
            String nombreCompleto = usuario.getNombre() + " " + usuario.getApellido();
            if (nombreCompleto.toLowerCase().contains(textoBuscado.toLowerCase())) {
                System.out.println(index + " - " + nombreCompleto + " (" + usuario.getId() + ")");
                encontrados.add(usuario);
                index++;
            }
        }

        if (encontrados.isEmpty()) {
            gestorNotificaciones.notificar("No se encontraron usuarios con ese nombre o apellido.", NivelUrgencia.ERROR);
            throw new exceptions.UsuarioNoEncontradoException("No se encontraron usuarios con ese nombre o apellido.");
        }

        return encontrados;
    }

    public static Usuario buscarUsuarioPorId(int idBuscado) throws exceptions.UsuarioNoEncontradoException {
        Usuario usuario = usuarios.get(idBuscado);
        if (usuario != null) {
            System.out.println("Usuario encontrado:");
            System.out.println("1 - " + usuario.getNombre() + " " + usuario.getApellido() + " (" + usuario.getId() + ")");
            return usuario;
        } else {
            gestorNotificaciones.notificar("No se encontró un usuario con ID " + idBuscado, NivelUrgencia.ERROR);
            throw new exceptions.UsuarioNoEncontradoException("No se encontró un usuario con ID " + idBuscado);
        }
    }

    public static void crearUsuariosDePrueba() {
        List<Usuario> usuariosDePrueba = List.of(
            new Usuario(1, "Juan", "Pérez", "juan.perez@example.com"),
            new Usuario(2, "María", "Gómez", "maria.gomez@example.com"),
            new Usuario(3, "Carlos", "López", "carlos.lopez@example.com"),
            new Usuario(4, "Ana", "Martínez", "ana.martinez@example.com"),
            new Usuario(5, "Luis", "Fernández", "luis.fernandez@example.com")
        );

        for (Usuario usuario : usuariosDePrueba) {
            GestorUsuarios.agregarUsuario(usuario);
        }
        gestorNotificaciones.notificar("Usuarios de prueba creados y registrados exitosamente.", NivelUrgencia.INFO);
    }


}
