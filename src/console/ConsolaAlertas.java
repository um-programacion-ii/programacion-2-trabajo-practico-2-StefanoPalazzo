package console;

import services.AlertaVencimiento;

import java.util.Scanner;

import static console.Consola.*;

public class ConsolaAlertas {
    public static void MenuAlertas() {
        mostrarMenuAlertas();
        opcionesAlertas();
    }

    public static void mostrarMenuAlertas() {
        System.out.println("=== MENÚ ALERTAS ===");
        System.out.println("1. Ver alertas de vencimiento");
        System.out.println("2. Historial de alertas");
        System.out.println("3. Configurar alertas");
        System.out.println("4. Volver");
    }

    public static void opcionesAlertas() {
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion) {
            case 1:
                System.out.println("=== ALERTAS DE VENCIMIENTO ===");
                if (gestorPrestamos.getPrestamosActivos().isEmpty()) {
                    System.out.println("No hay préstamos activos para verificar alertas.");
                    break;
                }
                alertasVencimiento = new AlertaVencimiento(gestorPrestamos.getPrestamosActivos(), gestorNotificaciones);
                alertasVencimiento.verificarAlertas();
                break;
            case 2:
                if (gestorNotificaciones.getHistorial().isEmpty()) {
                    System.out.println("No hay alertas en el historial.");
                    break;
                }else {
                    System.out.println(gestorNotificaciones.getHistorial());
                }
                break;
            case 3:
                gestorNotificaciones.menuConfiguracionAlertas();
                break;

            case 4:
                System.out.println("Volviendo al menú principal...");
                break;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
        }
    }
}
