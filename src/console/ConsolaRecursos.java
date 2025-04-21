package console;

import models.*;

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
        System.out.println("4. Buscar recursos");
        System.out.println("5. Buscar por ID");
        System.out.println("6. Cargar Recursos de Ejemplo");
        System.out.println("7. Volver");
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
                System.out.println("4. Enciclopedia");
                System.out.println("5. Volver");
                opcion = sc.nextInt();
                sc.nextLine();
                menuAgregarRecurso(sc, opcion);
                break;
            case 3:
                System.out.println("- Eliminar Recurso -");
                System.out.println("ID del recurso a eliminar: ");
                int idRecurso = Integer.parseInt(sc.nextLine());
                RecursoDigital recurso = Consola.gestorRecursos.buscarRecursoPorId(idRecurso);
                if (recurso != null) {
                    Consola.gestorRecursos.eliminarRecurso(recurso);
                    System.out.println("Recurso eliminado exitosamente.");
                } else {
                    System.out.println("No se encontró un recurso con ID " + idRecurso);
                }
                break;
            case 4:
                ConsolaUtils.menuBusquedaYOrden(sc, Consola.gestorRecursos);
                break;
            case 5:
                System.out.println("ID del recurso a buscar: ");
                int id = Integer.parseInt(sc.nextLine());
                RecursoDigital recursoBuscado = Consola.gestorRecursos.buscarRecursoPorId(id);
            case 6:
                System.out.println("Cargando recursos de ejemplo...");

                Libro libro1 = new Libro(1, "Cien Años de Soledad", "Una novela de realismo mágico de Gabriel García Márquez.", "Libro", "978-3-16-148410-0", "Gabriel García Márquez", "Editorial XYZ", 1967);
                Consola.gestorRecursos.agregarRecurso(libro1);

                Libro libro2 = new Libro(2, "1984", "Una novela distópica de George Orwell.", "Libro", "978-0-452-28423-4", "George Orwell", "Editorial ABC", 1949);
                Consola.gestorRecursos.agregarRecurso(libro2);

                Audiolibro audiolibro1 = new Audiolibro(3, "El Hobbit - Audiolibro", "Narración de la famosa novela de J.R.R. Tolkien.", "Audiolibro", "J.R.R. Tolkien", 300, "Narrador 1");
                Consola.gestorRecursos.agregarRecurso(audiolibro1);

                Revista revista1 = new Revista(4, "National Geographic - Especial Ciencia", "Revista de divulgación científica.", "Revista", "National Geographic", 122, LocalDate.of(2024, 4, 10));
                Consola.gestorRecursos.agregarRecurso(revista1);

                Enciclopedia enciclopedia1 = new Enciclopedia(5, "Enciclopedia de Ciencias", "Una enciclopedia completa sobre ciencias naturales.", "Enciclopedia", "Dr. Juan Pérez", "Editorial Alfa", 2020, 1);
                Enciclopedia enciclopedia2 = new Enciclopedia(6, "Enciclopedia de Historia Universal", "Explora los eventos más importantes de la historia.", "Enciclopedia", "Dra. María López", "Editorial Beta", 2018, 2);
                Enciclopedia enciclopedia3 = new Enciclopedia(7, "Enciclopedia de Tecnología", "Información detallada sobre avances tecnológicos.", "Enciclopedia", "Ing. Carlos Gómez", "Editorial Gamma", 2022, 3);
                Consola.gestorRecursos.agregarRecurso(enciclopedia1);
                Consola.gestorRecursos.agregarRecurso(enciclopedia2);
                Consola.gestorRecursos.agregarRecurso(enciclopedia3);

                break;
            case 7:
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

                categoria = "Libro";

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

                categoria = "Audiolibro";

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

                categoria = "Revista";

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

            case 4: // Enciclopedia
                System.out.println("- Enciclopedia -");
                System.out.print("ID: ");
                int idEnciclopedia = Integer.parseInt(sc.nextLine());

                System.out.print("Título: ");
                String tituloEnciclopedia = sc.nextLine();

                System.out.print("Descripción: ");
                String descEnciclopedia = sc.nextLine();

                categoria = "Enciclopedia";

                System.out.print("Autor General: ");
                autor = sc.nextLine();

                System.out.print("Editorial: ");
                editorial = sc.nextLine();

                System.out.print("Año: ");
                int anioEnciclopedia = Integer.parseInt(sc.nextLine());

                System.out.print("Volumen: ");
                int volumen = Integer.parseInt(sc.nextLine());

                System.out.print("Tomo: ");
                int tomo = Integer.parseInt(sc.nextLine());

                Enciclopedia enciclopedia = new Enciclopedia(idEnciclopedia, tituloEnciclopedia, descEnciclopedia, categoria, autor, editorial, anioEnciclopedia, volumen);
                Consola.gestorRecursos.agregarRecurso(enciclopedia);
                System.out.println("Enciclopedia agregada exitosamente.");
                break;

            default:
                System.out.println("Opción inválida.");
                break;
        }
    }
}
