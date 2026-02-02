package bootcamp;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class GestorDeTareas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        ArrayList<Tarea> listaDeTareas = new ArrayList<>();
        listaDeTareas.add(new Tarea("Ir al supermercado", 1));
        listaDeTareas.add(new Tarea("Lavar el auto", 2));
        listaDeTareas.add(new Tarea("Pagar las cuentas", 3));
        listaDeTareas.add(new Tarea("Completar las tareas de la plataforma", 4));

        limpiarPantalla();
        System.out.println("(A)gregar una tarea, (M)ostrar tareas pendientes, (C)ompletar una tarea, (S)alir");
        String opcion = scanner.nextLine().trim().toLowerCase();

        while (!salir) {
            if (opcion.equals("a")) {
                // Agregar tarea

                System.out.print("Ingresa la tarea: ");
                String descripcion = scanner.nextLine().trim();

                Tarea ultimaTarea = listaDeTareas.stream()
                        .max((t1, t2) -> Integer.compare(t1.id, t2.id))
                        .orElse(null);
                int nuevoId = ultimaTarea != null ? ultimaTarea.id + 1 : 1;
                                
                listaDeTareas.add(new Tarea(descripcion, nuevoId));
                
                limpiarPantalla();
                System.out.println("Tarea agregada");
                System.out
                        .println("\n(A)gregar una tarea, (M)ostrar tareas pendientes, (C)ompletar una tarea, (S)alir");
                opcion = scanner.nextLine().trim().toLowerCase();

            } else if (opcion.equals("m")) {
                // Mostrar tareas pendientes

                limpiarPantalla();
                for (Tarea tarea : listaDeTareas) {
                    if (!tarea.completada) {
                        System.out.println(tarea.id + ") " + tarea.descripcion);
                    }
                }

                System.out
                        .println("\n(A)gregar una tarea, (M)ostrar tareas pendientes, (C)ompletar una tarea, (S)alir");
                opcion = scanner.nextLine().trim().toLowerCase();

            } else if (opcion.equals("c")) {
                // Completar tarea

                System.out.print("Ingrese el número de la tarea a completar: ");
                try {
                    int id = Integer.parseInt(scanner.nextLine());
                    listaDeTareas.stream()
                            .filter(tarea -> tarea.completada == false)
                            .filter(tarea -> tarea.id == id)
                            .findFirst()
                            .ifPresentOrElse(
                                    tarea -> {
                                        tarea.marcarComoCompletada();
                                        limpiarPantalla();
                                        System.out.println("La tarea " + id + " ha sido marcada como completada.");
                                    }, () -> {
                                        limpiarPantalla();
                                        System.out.println("No se encontró una tarea con el número " + id + ".");
                                    });

                    System.out.println(
                            "\n(A)gregar una tarea, (M)ostrar tareas pendientes, (C)ompletar una tarea, (S)alir");
                    opcion = scanner.nextLine().trim().toLowerCase();
                } catch (NumberFormatException e) {
                    System.out.println("Error: debes ingresar un número.");
                    System.out.println(
                            "\n(A)gregar una tarea, (M)ostrar tareas pendientes, (C)ompletar una tarea, (S)alir");
                    opcion = scanner.nextLine().trim().toLowerCase();
                }

            } else if (opcion.equals("s")) {
                // Salir

                salir = true;
            } else {
                // Opción no válida

                System.out.println("Opción no válida. Intente de nuevo.");
                System.out.println("(A)gregar una tarea, (M)ostrar tareas pendientes, (C)ompletar una tarea, (S)alir");
                opcion = scanner.nextLine().trim().toLowerCase();
            }
        }
        scanner.close();
    }

    private static void limpiarPantalla() {
        try {
            String wtSession = System.getenv("WT_SESSION"); // Windows Terminal
            String comspec = System.getenv("ComSpec"); // cmd.exe
            String psModulePath = System.getenv("PSModulePath"); // PowerShell

            if (wtSession == null) { // No es Windows Terminal
                if (comspec != null) {
                    // cmd clásico → usar comando cls
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else if (psModulePath != null) {
                    // Windows PowerShell clásico, PowerShell clásico → usar comando Clear-Host
                    new ProcessBuilder("powershell", "-Command", "Clear-Host").inheritIO().start().waitFor();
                } else {
                    // Otros intérpretes de comandos → usar secuencia ANSI
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
            } else {
                // Windows Terminal soporta ANSI → usar secuencia
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("No se pudo limpiar la pantalla.\nError: " + e.getMessage());
        }
    }
}
