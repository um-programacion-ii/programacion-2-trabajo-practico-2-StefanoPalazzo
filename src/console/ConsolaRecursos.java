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
                opcion = sc.nextInt();
                sc.nextLine();
                menuAgregarRecurso(sc, opcion);
                break;
            case 3:
                System.out.println("Eliminar Recurso");
                break;
            case 4:
                ConsolaUtils.menuBusquedaYOrden(sc, Consola.gestorRecursos);
                break;
            case 5:
                break;
            case 6:
                System.out.println("Cargando recursos de ejemplo...");

                Libro libro1 = new Libro(1, "Cien Años de Soledad", "Una novela de realismo mágico de Gabriel García Márquez.", "Libro", "978-3-16-148410-0", "Gabriel García Márquez", "Editorial XYZ", 1967);
                Consola.gestorRecursos.agregarRecurso(libro1);
                System.out.println("Ejemplo de Libro agregado.");

                Audiolibro audiolibro1 = new Audiolibro(2, "El Hobbit - Audiolibro", "Narración de la famosa novela de J.R.R. Tolkien.", "Audiolibro", "J.R.R. Tolkien", 300, "Narrador 1");
                Consola.gestorRecursos.agregarRecurso(audiolibro1);
                System.out.println("Ejemplo de Audiolibro agregado.");

                Revista revista1 = new Revista(3, "National Geographic - Especial Ciencia", "Revista de divulgación científica.", "Revista", "National Geographic", 122, LocalDate.of(2024, 4, 10));
                Consola.gestorRecursos.agregarRecurso(revista1);
                System.out.println("Ejemplo de Revista agregado.");
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

            default:
                System.out.println("Opción inválida.");
                break;
        }
    }
}
