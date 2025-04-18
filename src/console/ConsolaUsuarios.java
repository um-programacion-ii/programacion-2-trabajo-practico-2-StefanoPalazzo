package console;

import models.Usuario;

import java.util.Scanner;

public class ConsolaUsuarios {
    public static void MenuUsuarios() {
        mostrarMenuUsuarios();
        opcionesUsuarios();
    }

    public static void mostrarMenuUsuarios() {
        System.out.println("--- Gestor Usuarios ---");
        System.out.println("1. Listar Usuarios");
        System.out.println("2. Agregar Usuario");
        System.out.println("3. Eliminar Usuario");
        System.out.println("4. Buscar usuario por nombre");
        System.out.println("5. Buscar usuario por ID");
        System.out.println("6. Volver");
    }

    public static void opcionesUsuarios() {
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion) {
            case 1:
                Consola.gestorUsuarios.listarUsuarios();
                break;
            case 2:
                System.out.println("-Agregar Usuario-");
                System.out.println("Ingrese DNI");
                int dni = sc.nextInt();
                sc.nextLine();
                System.out.println("Ingrese Nombre");
                String nombre = sc.nextLine();
                System.out.println("Ingrese apellido");
                String apellido = sc.nextLine();
                System.out.println("Ingrese correo");
                String correo = sc.nextLine();
                Usuario u = new Usuario(dni, nombre, apellido, correo);
                Consola.gestorUsuarios.agregarUsuario(u);
                break;
            case 3:
                System.out.println("-Eliminar Usuario-");
                System.out.println("Ingrese dni de usuario a eliminar");
                int dniAEliminar = sc.nextInt();
                sc.nextLine();
                Consola.gestorUsuarios.eliminarUsuario(dniAEliminar);
                break;
            case 4:
                System.out.println("Ingresar nombre del usuario");
                String nombreBuscado = sc.nextLine();
                Consola.gestorUsuarios.buscarUsuarioPorNombreOApellido(nombreBuscado);
                break;
            case 5:
                System.out.println("Ingresar ID del usuario");
                int idUsuario = sc.nextInt();
                Consola.gestorUsuarios.buscarUsuarioPorId(idUsuario);
            case 6:
                break;
            default:
                System.out.println("Opción no válida");
                opcionesUsuarios();
                break;
        }
    }

}
