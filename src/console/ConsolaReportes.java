package console;

import java.util.Scanner;
import java.util.concurrent.*;

public class ConsolaReportes {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(4);  // Pool de 4 hilos.

    public static void MenuReportes() {
        mostrarMenuReportes();
        opcionesReportes();
    }

    public static void mostrarMenuReportes() {
        System.out.println("=== MENÚ REPORTES ===");
        System.out.println("1. Recursos más prestados");
        System.out.println("2. Usuarios más activos");
        System.out.println("3. Uso por categoría");
        System.out.println("4. Volver");
    }

    public static void opcionesReportes() {
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion) {
            case 1:
                ejecutarReporte(() -> Consola.gestorPrestamos.reporteRecursosMasPrestados());
                break;
            case 2:
                ejecutarReporte(() -> Consola.gestorPrestamos.reporteUsuariosMasActivos());
                break;
            case 3:
                ejecutarReporte(() -> Consola.gestorPrestamos.estadisticasPorCategoria());
                break;
            case 4:
                System.out.println("Volviendo al menú principal...");
                break;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
        }
    }

    private static void ejecutarReporte(Runnable reporte) {
        executorService.submit(() -> {
            try {
                String threadName = Thread.currentThread().getName();
                System.out.println("Thread " + threadName + " - Generando reporte en segundo plano...");
                reporte.run();
                System.out.println("Thread " + threadName + " - Reporte generado con éxito.");
            } catch (Exception e) {
                System.err.println("Error generando el reporte: " + e.getMessage());
            }
        });
    }

}
