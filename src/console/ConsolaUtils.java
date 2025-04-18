package console;

import models.RecursoDigital;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import services.GestorRecursos;


public class ConsolaUtils {
    GestorRecursos gestorRecursos = Consola.gestorRecursos;

    public static void menuBusquedaYOrden(Scanner sc, GestorRecursos gestorRecursos) {
        boolean enBusqueda = true;
        while (enBusqueda) {
            System.out.println("\n--- BÚSQUEDAS Y ORDENAMIENTOS ---");
            System.out.println("1. Buscar por Título");
            System.out.println("2. Filtrar por Categoría");
            System.out.println("3. Ordenar por Título");
            System.out.println("4. Ordenar por ID");
            System.out.println("5. Mostrar resultados");
            System.out.print("Opción: ");
            int opcion = Integer.parseInt(sc.nextLine());

            List<RecursoDigital> resultados = Collections.emptyList();
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese texto a buscar en título: ");
                    String txt = sc.nextLine();
                    resultados = gestorRecursos.buscarRecursoPorTitulo(txt);
                    System.out.println("Se encontraron " + resultados.size() + " resultados.");
                    break;
                case 2:
                    System.out.print("Ingrese categoría (Libro / Revista / Audiolibro): ");
                    String cat = sc.nextLine();
                    resultados = gestorRecursos.filtrarPorCategoria(cat);
                    break;
                case 3:
                    resultados = gestorRecursos.ordenarPorTitulo();
                    break;
                case 4:
                    resultados = gestorRecursos.ordenarPorId();
                    break;
                case 5:
                    enBusqueda = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    continue;
            }
            if (resultados.isEmpty()) {
                System.out.println("→ No se encontraron recursos.");
            } else {
                System.out.println("→ Resultados:");
                for (RecursoDigital r : resultados) {
                    System.out.println("   • " + r.getTitulo() + " (ID: " + r.getId() + ")");
                }
            }
        }
    }
}
