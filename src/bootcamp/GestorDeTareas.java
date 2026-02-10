package bootcamp;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Clase principal del programa. En esta clase ocurre la gestión de las tareas, incluyendo la presentación del 
 * menú de opciones al usuario y la ejecución de las acciones correspondientes según la opción seleccionada.
 */
public class GestorDeTareas {

    static String opcion;
    static Scanner scanner;

    /**
     * Método principal del programa. Realiza la gestión de las tareas. El usuario puede agregar tareas, mostrar 
     * tareas pendientes, mostrar tareas completas, marcar tareas como completas, eliminar tareas o salir del programa.
     * @param args Argumentos de línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        boolean salir = false;

        ArrayList<Tarea> listaDeTareas = new ArrayList<>();
        listaDeTareas.add(new Tarea("Ir al supermercado", "alta", 1));
        listaDeTareas.add(new Tarea("Lavar el auto", "media", 2));
        listaDeTareas.add(new Tarea("Pagar las cuentas", "media", 3));
        listaDeTareas.add(new Tarea("Completar las tareas de la plataforma","media", 4));

        limpiarPantalla();
        mostrarMenu();

        while (!salir) {
            // Agregar tarea
            if (opcion.equals("a")) {
                System.out.print("Ingresa la tarea: ");
                String descripcion = scanner.nextLine().trim();
                
                System.out.print("Ingresa la prioridad (alta | media): ");
                String prioridad = scanner.nextLine().trim().toLowerCase();
                
                if (!prioridad.equals("alta") && !prioridad.equals("media")) {
                    limpiarPantalla();
                    System.out.println("Prioridad no válida. La prioridad puede ser 'alta' o 'media'.");
                    mostrarMenu();
                    continue;
                }

                Tarea ultimaTarea = listaDeTareas.stream()
                        .max((t1, t2) -> Integer.compare(t1.id, t2.id))
                        .orElse(null);
                int nuevoId = ultimaTarea != null ? ultimaTarea.id + 1 : 1;

                listaDeTareas.add(new Tarea(descripcion, prioridad, nuevoId));

                limpiarPantalla();
                System.out.println("Tarea agregada");
                mostrarMenu();

            }
            // Mostrar tareas pendientes
            else if (opcion.equals("p")) {
                if(listaDeTareas.stream().filter(tarea -> !tarea.completada).count() == 0) {
                    limpiarPantalla();
                    System.out.println("No hay tareas pendientes.");
                    mostrarMenu();
                    continue;
                }

                limpiarPantalla();
                for (Tarea tarea : listaDeTareas) {
                    if (!tarea.completada) {
                        System.out.println(tarea.id + ") " + tarea.descripcion + " [" + tarea.prioridad + "]");
                    }
                }
                mostrarMenu();

            } 
            // Mostrar tareas completas
            else if (opcion.equals("c")) {
                if(listaDeTareas.stream().filter(tarea -> tarea.completada).count() == 0) {
                    limpiarPantalla();
                    System.out.println("No hay tareas completas aún.");
                    mostrarMenu();
                    continue;
                }

                limpiarPantalla();
                for (Tarea tarea : listaDeTareas) {
                    if (tarea.completada) {
                        System.out.println(tarea.id + ") " + tarea.descripcion + " [" + tarea.prioridad + "]");
                    }
                }
                mostrarMenu();

            }
            // Completar tarea
            else if (opcion.equals("m")) {
                if(listaDeTareas.stream().filter(tarea -> !tarea.completada).count() == 0) {
                    limpiarPantalla();
                    System.out.println("No hay tareas pendientes.");
                    mostrarMenu();
                    continue;
                }

                System.out.print("Ingrese el número de la tarea: ");
                try {
                    int id = Integer.parseInt(scanner.nextLine());

                    Tarea tareaAMarcar = listaDeTareas.stream()
                            .filter(tarea -> !tarea.completada)
                            .filter(tarea -> tarea.id == id)
                            .findFirst()
                            .orElse(null);
                    
                    if (tareaAMarcar != null) {
                        tareaAMarcar.marcarComoCompletada();
                        limpiarPantalla();
                        System.out.println("La tarea " + id + " ha sido marcada como completada.");
                    } else {
                        limpiarPantalla();
                        System.out.println("No se encontró una tarea pendiente con el número " + id + ".");
                    }
                    mostrarMenu();

                } catch (NumberFormatException e) {
                    System.out.println("Error: debes ingresar un número.");
                    mostrarMenu();
                }

            }
            // Eliminar tarea
            else if (opcion.equals("e")) {
                if(listaDeTareas.isEmpty()) {
                    limpiarPantalla();
                    System.out.println("No hay tareas para eliminar.");
                    mostrarMenu();
                    continue;
                }

                System.out.print("Ingrese el número de la tarea a eliminar: ");
                try {
                    int id = Integer.parseInt(scanner.nextLine());
                    boolean tareaEncontrada = listaDeTareas.removeIf(tarea -> tarea.id == id);
                    limpiarPantalla();
                    if (tareaEncontrada) {
                        System.out.println("La tarea " + id + " ha sido eliminada.");
                    } else {
                        System.out.println("No se encontró una tarea con el número " + id + ".");
                    }
                    mostrarMenu();
                } catch (NumberFormatException e) {
                    System.out.println("Error: debes ingresar un número.");
                    mostrarMenu();
                }
            } 
            // Salir
            else if (opcion.equals("s")) {
                salir = true;
            } 
            // Opción no válida
            else {
                System.out.println("Opción no válida. Intente de nuevo.");
                mostrarMenu();
            }
        }
        scanner.close();
    }

    /**
     * Método para limpiar la pantalla de la consola. 
     * Para cmd, PowerShell y Windows PowerShell en sus terminales nativas 
     * usa comandos propios de cada intérprete de comandos para limpiar la pantalla. 
     * Si ejecuta cmd, PowerShell o Windows PowerShell dentro de Windows Terminal 
     * usa secuencias ANSI, ya que Windows Terminal las soporta.
     * Para Git Bash también usa secuencias ANSI.
     */
    private static void limpiarPantalla() {
        try {
            String wtSession = System.getenv("WT_SESSION"); // Windows Terminal
            String comspec = System.getenv("ComSpec"); // cmd.exe
            String psModulePath = System.getenv("PSModulePath"); // PowerShell
            String shell = System.getenv("SHELL"); // bash

            if (wtSession == null) { // No es Windows Terminal
                if (comspec != null && shell == null) {
                    // cmd clásico → usar comando cls
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else if (psModulePath != null && shell == null) {
                    // Windows PowerShell clásico, PowerShell clásico → usar comando Clear-Host
                    new ProcessBuilder("powershell", "-Command", "Clear-Host").inheritIO().start().waitFor();
                } else {
                    // Git Bash y otros intérpretes de comandos → usar secuencia ANSI
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

    /**
     * Presenta al usuario el menú de opciones y lee su elección. Las opciones para gestionar las tareas son:
     * agregar una tarea, mostrar tareas pendientes, mostrar tareas completas, marcar una tarea como completa, 
     * eliminar una tarea y salir del programa. 
     */
    private static void mostrarMenu() {
        System.out.println("┌──────────────┬───────────┬──────────────────────┬─────────────────────┬────────────────────────┬────────────┬─────────┐");
        System.out.println("│  Menú tareas │ (A)gregar │ Mostrar (p)endientes │ Mostrar (c)ompletas │ (M)arcar como completa │ (E)liminar │ (S)alir │"); 
        System.out.println("└──────────────┴───────────┴──────────────────────┴─────────────────────┴────────────────────────┴────────────┴─────────┘");
        opcion = scanner.nextLine().trim().toLowerCase();
    }
}
