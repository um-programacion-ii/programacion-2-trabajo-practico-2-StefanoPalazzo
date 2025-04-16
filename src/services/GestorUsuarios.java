package services;

import interfaces.IServicioNotificaciones;
import models.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorUsuarios {
    private IServicioNotificaciones servicioNotificaciones;
    private Map<Integer, Usuario> usuarios = new HashMap<>();

    public GestorUsuarios(IServicioNotificaciones servicioNotificaciones) {
        this.servicioNotificaciones = servicioNotificaciones;
    }

    public void listarUsuarios() {
        System.out.println("Lista de Usuarios:");
        for (Usuario u : usuarios.values()) {
            System.out.println("- " + u.getNombre() + " " + u.getApellido());
        }
        servicioNotificaciones.enviarNotificacion("Listado de usuarios mostrado correctamente.");
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
        System.out.println("👤 Usuario agregado: " + usuario.getNombre());
    }

    public void eliminarUsuario(Usuario u) {
        servicioNotificaciones.enviarNotificacion("Usuario " + u.getNombre() + " eliminado con éxito.");
    }

    public List<Usuario> buscarUsuarioPorNombreOApellido(String textoBuscado) {
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
            System.out.println("No se encontraron usuarios con ese nombre o apellido.");
        }

        return encontrados;
    }

    public Usuario buscarUsuarioPorId(int idBuscado) {
        Usuario usuario = usuarios.get(idBuscado);
        if (usuario != null) {
            System.out.println("Usuario encontrado:");
            System.out.println("1 - " + usuario.getNombre() + " " + usuario.getApellido() + " (" + usuario.getId() + ")");
            return usuario;
        } else {
            System.out.println("No se encontró ningún usuario con el ID ingresado.");
            return null;
        }
    }



}
