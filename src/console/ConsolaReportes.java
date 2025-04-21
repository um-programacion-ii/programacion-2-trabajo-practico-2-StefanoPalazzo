package console;

import java.util.Scanner;

public class ConsolaReportes {
    public static void MenuReportes() {
        mostrarMenuReportes();
        opcionesReportes();
    }

    public static void mostrarMenuReportes() {
        System.out.println("=== MENÚ REPORTES ===");
        System.out.println("1. Recursos más prestados");
        System.out.println("2. Usuarios más activos");
        System.out.println("3. Uso por categoría");
    }

    public static void opcionesReportes() {
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion) {
            case 1:
                Consola.gestorPrestamos.reporteRecursosMasPrestados();
                break;
            case 2:
                Consola.gestorPrestamos.reporteUsuariosMasActivos();
                break;
            case 3:
                Consola.gestorPrestamos.estadisticasPorCategoria();
                break;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
        }
    }
}
