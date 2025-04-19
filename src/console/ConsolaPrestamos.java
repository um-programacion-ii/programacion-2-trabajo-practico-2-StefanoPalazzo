package console;

import java.util.Scanner;

public class ConsolaPrestamos {
    public static void MenuPrestamos() {
        mostrarMenuPrestamos();
        opcionesPrestamos();
    }

    public static void mostrarMenuPrestamos() {
        System.out.println("=== MENÚ PRESTAMOS ===");
        System.out.println("1. Prestar recurso");
        System.out.println("2. Devolver prestamo");
        System.out.println("3. Renovar prestamo");
        System.out.println("4. Ver prestamos activos");
        System.out.println("5. Salir");
    }

    public static void opcionesPrestamos() {
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion) {
            case 1:
                System.out.println("- Prestar Recurso -");
                System.out.println("ID del recurso a prestar: ");
                int idRecurso = Integer.parseInt(sc.nextLine());
                System.out.println("ID del usuario: ");
                int idUsuario = Integer.parseInt(sc.nextLine());
                try {
                    Consola.gestorPrestamos.prestarRecurso(idRecurso, idUsuario);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 2:
                System.out.println("- Devolver Prestamo -");
                System.out.println("ID del prestamo a devolver: ");
                int idPrestamo = Integer.parseInt(sc.nextLine());
                try {
                    Consola.gestorPrestamos.devolverPrestamo(idPrestamo);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 3:
                System.out.println("- Renovar Prestamo -");
                System.out.println("ID del prestamo a renovar: ");
                int idPrestamoRenovar = Integer.parseInt(sc.nextLine());
                try {
                    Consola.gestorPrestamos.renovarPrestamo(idPrestamoRenovar);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 4:
                System.out.println("- Listado de Prestamos Activos -");
                Consola.gestorPrestamos.listarPrestamosActivos();
                break;
            case 5:
                System.out.println("Saliendo del menú de préstamos...");
                break;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
        }
    }
}
