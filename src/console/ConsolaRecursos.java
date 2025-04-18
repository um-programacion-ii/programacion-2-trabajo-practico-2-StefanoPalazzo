package console;

import models.Audiolibro;
import models.Libro;
import models.Revista;
import models.Usuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class ConsolaRecursos {
    public static int opcion;
    public static String categoria;
    public static String autor;
    public static String editorial;
    public ConsolaRecursos() {
    }

    public static void MenuRecursos() {
        mostrarMenuRecursos();
        opcionesRecursos();
    }


    public static void mostrarMenuRecursos() {
        System.out.println("--- Gestor Recursos ---");
        System.out.println("1. Listar Recursos");
        System.out.println("2. Agregar Recurso");
        System.out.println("3. Eliminar Recurso");
        System.out.println("4. Prestar Recurso");
        System.out.println("5. Devolver Recurso");
        System.out.println("6. Renovar Recurso");
        System.out.println("7. Buscar recursos");
        System.out.println("8. Buscar por ID");
        System.out.println("9. Volver");
    }

    public static void opcionesRecursos() {
        Scanner sc = new Scanner(System.in);
        opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion) {
            case 1:
                Consola.gestorRecursos.listarRecursos();
                break;
            case 2:
                System.out.println("-Agregar Recurso-");
                System.out.println("Elija tipo de recurso");
                System.out.println("1. Libro");
                System.out.println("2. Audiolibro");
                System.out.println("3. Revista");
                opcion = sc.nextInt();
                sc.nextLine();
                menuAgregarRecurso(sc, opcion);
                break;
            case 3:
                System.out.println("Eliminar Recurso");
                break;
            case 4:
                System.out.println("Prestar Recurso");
                break;
            case 5:
                System.out.println("Devolver Recurso");
                break;
            case 6:
                System.out.println("Renovar Recurso");
                break;
            case 7:
                ConsolaUtils.menuBusquedaYOrden(sc, Consola.gestorRecursos);
                break;
            case 8:
                System.out.print("Ingresar ID del recurso: ");
                int idBuscado = sc.nextInt();
                Consola.gestorRecursos.buscarRecursoPorId(idBuscado);
                break;
            case 9:
                break;
            default:
                System.out.println("Opción no válida");
                opcionesRecursos();
                break;
        }
    }

    public static void menuAgregarRecurso(Scanner sc, int opcion) {
        switch (opcion) {
            case 1: // Libro
                System.out.println("- Libro -");
                System.out.print("ID: ");
                int idLibro = Integer.parseInt(sc.nextLine());

                System.out.print("Título: ");
                String tituloLibro = sc.nextLine();

                System.out.print("Descripción: ");
                String descLibro = sc.nextLine();

                System.out.print("Categoría: ");
                categoria = sc.nextLine();

                System.out.print("ISBN: ");
                String isbn = sc.nextLine();

                System.out.print("Autor: ");
                autor = sc.nextLine();

                System.out.print("Editorial: ");
                editorial = sc.nextLine();

                System.out.print("Año: ");
                int anio = Integer.parseInt(sc.nextLine());

                Libro libro = new Libro(idLibro, tituloLibro, descLibro, categoria, isbn, autor, editorial, anio);
                Consola.gestorRecursos.agregarRecurso(libro);
                System.out.println("Libro agregado exitosamente.");
                break;

            case 2: // Audiolibro
                System.out.println("- Audiolibro -");
                System.out.print("ID: ");
                int idAudio = Integer.parseInt(sc.nextLine());

                System.out.print("Título: ");
                String tituloAudio = sc.nextLine();

                System.out.print("Descripción: ");
                String descAudio = sc.nextLine();

                System.out.print("Categoría: ");
                categoria = sc.nextLine();

                System.out.print("Autor: ");
                autor = sc.nextLine();


                System.out.print("Duración (en minutos): ");
                int duracion = Integer.parseInt(sc.nextLine());

                System.out.print("Narrador: ");
                String narrador = sc.nextLine();

                Audiolibro audiolibro = new Audiolibro(idAudio, tituloAudio, descAudio, categoria, autor,duracion, narrador);
                Consola.gestorRecursos.agregarRecurso(audiolibro);
                System.out.println("Audiolibro agregado exitosamente.");
                break;

            case 3: // Revista
                System.out.println("- Revista -");
                System.out.print("ID: ");
                int idRevista = Integer.parseInt(sc.nextLine());

                System.out.print("Título: ");
                String tituloRevista = sc.nextLine();

                System.out.print("Descripción: ");
                String descRevista = sc.nextLine();

                System.out.print("Categoría: ");
                categoria = sc.nextLine();

                System.out.print("Editorial: ");
                editorial = sc.nextLine();

                System.out.print("Número de edición: ");
                int edicion = Integer.parseInt(sc.nextLine());

                System.out.print("Fecha de publicación (ej. 2024-04-17): ");
                LocalDate fechaPublicacion = LocalDate.parse(sc.nextLine());

                Revista revista = new Revista(idRevista, tituloRevista, descRevista, categoria, editorial, edicion, fechaPublicacion);
                Consola.gestorRecursos.agregarRecurso(revista);
                System.out.println("Revista agregada exitosamente.");
                break;

            default:
                System.out.println("Opción inválida.");
                break;
        }
    }
}
