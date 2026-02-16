package bootcamp;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Clase principal del programa. En esta clase ocurre la gestión de las tareas, incluyendo la presentación del 
 * menú de opciones al usuario y la ejecución de las acciones correspondientes según la opción seleccionada.
 */
public class GestorDeTareas {

    // static String opcion;
    static Scanner scanner = new Scanner(System.in);

    /**
     * Método principal del programa. Realiza la gestión de las tareas. El usuario puede agregar tareas, mostrar 
     * tareas pendientes, mostrar tareas completas, marcar tareas como completas, eliminar tareas o salir del programa.
     * @param args Argumentos de línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {
        boolean salir = false;
        String opcion;

        ArrayList<Tarea> listaDeTareas = new ArrayList<>();
        listaDeTareas.add(new Tarea("Ir al supermercado", "alta", 1));
        listaDeTareas.add(new Tarea("Lavar el auto", "media", 2));
        listaDeTareas.add(new Tarea("Pagar las cuentas", "media", 3));
        listaDeTareas.add(new Tarea("Completar las tareas de la plataforma","media", 4));

        String bienvenida = "Bienvenida/o al Gestor de Tareas. Ingrese i para ver las instrucciones.";
        System.out.println(bienvenida);
        opcion = leerLinea().toLowerCase();

        while (!salir) {
            // Agregar tarea
            if (opcion.equals("a")) {
                System.out.print("Ingresa la tarea ");
                String descripcion = leerLinea();
                
                System.out.print("Ingresa la prioridad (alta | media) ");
                String prioridad = leerLinea().toLowerCase();
                
                if (!prioridad.equals("alta") && !prioridad.equals("media")) {
                    System.out.println("Prioridad no válida. La prioridad puede ser 'alta' o 'media'.");
                    opcion = leerLinea().toLowerCase();
                    continue;
                }

                Tarea ultimaTarea = listaDeTareas.stream()
                        .max((t1, t2) -> Integer.compare(t1.id, t2.id))
                        .orElse(null);
                int nuevoId = ultimaTarea != null ? ultimaTarea.id + 1 : 1;

                listaDeTareas.add(new Tarea(descripcion, prioridad, nuevoId));

                System.out.println("Tarea agregada");
                opcion = leerLinea().toLowerCase();

            }
            // Mostrar tareas pendientes
            else if (opcion.equals("p")) {
                if(listaDeTareas.stream().filter(tarea -> !tarea.completada).count() == 0) {
                    System.out.println("No hay tareas pendientes.");
                    opcion = leerLinea().toLowerCase();
                    continue;
                }

                System.out.println("\nTareas pendientes:");

                for (Tarea tarea : listaDeTareas) {
                    if (!tarea.completada) {
                        System.out.println(tarea.id + ") " + tarea.descripcion + " [" + tarea.prioridad + "]");
                    }
                }
                opcion = leerLinea().toLowerCase();

            } 
            // Mostrar tareas completas
            else if (opcion.equals("c")) {
                if(listaDeTareas.stream().filter(tarea -> tarea.completada).count() == 0) {
                    System.out.println("No hay tareas completas aún.");
                    opcion = leerLinea().toLowerCase();
                    continue;
                }

                System.out.println("\nTareas completas:");
                for (Tarea tarea : listaDeTareas) {
                    if (tarea.completada) {
                        System.out.println(tarea.id + ") " + tarea.descripcion + " [" + tarea.prioridad + "]");
                    }
                }
                opcion = leerLinea().toLowerCase();

            }
            // Marcar tarea como completa
            else if (opcion.equals("m")) {
                if(listaDeTareas.stream().filter(tarea -> !tarea.completada).count() == 0) {
                    System.out.println("No hay tareas pendientes.");
                    opcion = leerLinea().toLowerCase();
                    continue;
                }

                System.out.print("Ingrese el número de la tarea ");
                try {
                    int id = Integer.parseInt(leerLinea());

                    Tarea tareaAMarcar = listaDeTareas.stream()
                            .filter(tarea -> !tarea.completada)
                            .filter(tarea -> tarea.id == id)
                            .findFirst()
                            .orElse(null);
                    
                    if (tareaAMarcar != null) {
                        tareaAMarcar.marcarComoCompletada();
                        System.out.println("La tarea " + id + " ha sido marcada como completada.");
                    } else {
                        System.out.println("No se encontró una tarea pendiente con el número " + id + ".");
                    }
                    opcion = leerLinea().toLowerCase();

                } catch (NumberFormatException e) {
                    System.out.println("Error: debes ingresar un número.");
                    opcion = leerLinea().toLowerCase();
                }

            }
            // Eliminar tarea
            else if (opcion.equals("e")) {
                if(listaDeTareas.isEmpty()) {
                    System.out.println("No hay tareas para eliminar.");
                    opcion = leerLinea().toLowerCase();
                    continue;
                }

                System.out.print("Ingrese el número de la tarea a eliminar ");
                try {
                    int id = Integer.parseInt(leerLinea());
                    boolean tareaEncontrada = listaDeTareas.removeIf(tarea -> tarea.id == id);
                    if (tareaEncontrada) {
                        System.out.println("La tarea " + id + " ha sido eliminada.");
                    } else {
                        System.out.println("No se encontró una tarea con el número " + id + ".");
                    }
                    opcion = leerLinea().toLowerCase();
                } catch (NumberFormatException e) {
                    System.out.println("Error: debes ingresar un número.");
                    opcion = leerLinea().toLowerCase();
                }
            } 
            else if (opcion.equals("i")) {
                mostrarMenu();
                opcion = leerLinea().toLowerCase();
            }
            // Salir
            else if (opcion.equals("s")) {
                salir = true;
            } 
            // Opción no válida
            else {
                System.out.println("Opción no válida. Las opciones disponibles son:\n");
                mostrarMenu();
                opcion = leerLinea().toLowerCase();
            }
        }
        scanner.close();
    }


    /**
     * Presenta al usuario el menú de opciones. Las opciones para gestionar las tareas son:
     * agregar una tarea, 
     * mostrar tareas pendientes, 
     * mostrar tareas completas, 
     * marcar una tarea como completa, 
     * eliminar una tarea y 
     * salir del programa. 
     */
    private static void mostrarMenu() {
        String mensaje = "Menú de tareas \n\n (A)gregar \n Mostrar (p)endientes \n Mostrar (c)ompletas \n" +
            " (M)arcar como completa \n (E)liminar \n (S)alir ";
        System.out.println(mensaje); 
    }

    private static String leerLinea() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }
}
