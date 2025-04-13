package console;
import java.util.Scanner;


public class Consola {
    public static void mostrarMenu() {
        // Aquí muestras las opciones de menú
        System.out.println("1. Ver recursos");
        System.out.println("2. Prestar recurso");
        System.out.println("3. Salir");
    }

    public static void ejecutarOpcion(int opcion){
        switch (opcion){
            case 1:
                System.out.println("Mostrando recursos...");
                break;
            case 2:
                System.out.println("Prestando recurso...");
                break;
            case 3:
                System.out.println("Salir");
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    public static void inciarConsola() {
        boolean ejecutar = true;
        mostrarMenu();
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        while (ejecutar){
            if (opcion == 3){
                ejecutar = false;
            }
            else {
                ejecutarOpcion(opcion);
                ejecutar = false; // esto es para evitar que entre en un bucle
            }
            }
        }
    }
