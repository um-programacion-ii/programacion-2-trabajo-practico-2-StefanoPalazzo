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
            System.out.println("- " + u.getNombre() + " " + u.getApellido() + "(" + u.getId() + ")");
        }
        servicioNotificaciones.enviarNotificacion("Listado de usuarios mostrado correctamente.");
    }

    public void agregarUsuario(Usuario usuario) {
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

    public Usuario buscarUsuarioPorId(int idBuscado) throws exceptions.UsuarioNoEncontradoException {
        Usuario usuario = usuarios.get(idBuscado);
        if (usuario != null) {
            System.out.println("Usuario encontrado:");
            System.out.println("1 - " + usuario.getNombre() + " " + usuario.getApellido() + " (" + usuario.getId() + ")");
            return usuario;
        } else {
            throw new exceptions.UsuarioNoEncontradoException("No se encontró un recurso con ID " + idBuscado);
        }
    }



}
