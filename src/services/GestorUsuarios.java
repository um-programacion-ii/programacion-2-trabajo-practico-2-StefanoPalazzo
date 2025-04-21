package services;

import console.ConsolaUsuarios;
import interfaces.IServicioNotificaciones;
import models.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorUsuarios {
    private IServicioNotificaciones servicioNotificaciones;
    private static Map<Integer, Usuario> usuarios = new HashMap<>();

    public GestorUsuarios(IServicioNotificaciones servicioNotificaciones) {
        this.servicioNotificaciones = servicioNotificaciones;
    }

    public void listarUsuarios() {
        System.out.println("Lista de Usuarios:");
        for (Usuario u : usuarios.values()) {
            System.out.println("- " + u.getNombre() + " " + u.getApellido() + "(" + u.getId() + ")");
        }
        servicioNotificaciones.enviarNotificacion("Listado de usuarios mostrado correctamente.");
    }

    public static void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
        System.out.println("Usuario agregado: "+ usuario.getNombre() + " " + usuario.getApellido() + "(" + usuario.getId() + ")");
    }

    public void eliminarUsuario(int id) {
        if (usuarios.containsKey(id)){
            Usuario u = usuarios.get(id);
            String nombre = u.getNombre() + " " + u.getApellido() + "(" + u.getId() + ")";
            usuarios.remove(id);
            servicioNotificaciones.enviarNotificacion("Usuario " + nombre + " eliminado con éxito.");
        } else {
            servicioNotificaciones.enviarNotificacion("Usuario no encontrado.");
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

        System.out.println("Usuarios de prueba creados y registrados exitosamente.");
    }


}
