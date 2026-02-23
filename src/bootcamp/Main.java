package bootcamp;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    
    /**
     * Scanner para leer la entrada del usuario desde la consola. Se utiliza el charset "cp850" 
     * en el scanner y en System.out para asegurar la correcta visualización de caracteres acentuados en la consola.
     * 
     * Resultado de las pruebas en Windows 10:
     * Símbolo del Sistema      ✓
     * Windows PowerShell       ✓
     * PowerShell               ✓
     * Git Bash                 ✗
     * 
     * Resultado de las pruebas en Windows 11:
     * Símbolo del Sistema      ✓
     * Windows PowerShell       ✓
     * PowerShell               ✓
     * Git Bash                 ✓
     * 
     */
    static Scanner scanner = new Scanner(System.in, "cp850");

    public static void main(String[] args) {

        try {
            System.setOut(new PrintStream(System.out, true, "cp850"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        boolean salir = false;
        String opcion;
        GestorDeTareas gestor = new GestorDeTareas();

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

                gestor.agregarTarea(descripcion, prioridad);
                System.out.println("Tarea agregada");
                opcion = leerLinea().toLowerCase();

            }
            // Mostrar tareas pendientes
            else if (opcion.equals("p")) {
                if(gestor.getListaDeTareas().stream().filter( tarea -> !tarea.isCompletada() ).count() == 0) {
                    System.out.println("No hay tareas pendientes.");
                    opcion = leerLinea().toLowerCase();
                    continue;
                }

                System.out.println("\nTareas pendientes:");

                for (Tarea tarea : gestor.getListaDeTareas()) {
                    if (!tarea.isCompletada()) {
                        System.out.println(tarea.getId() + ") " + tarea.getDescripcion() + " [" + tarea.getPrioridad() + "]");
                    }
                }
                opcion = leerLinea().toLowerCase();

            } 
            // Mostrar tareas completas
            else if (opcion.equals("c")) {
                if(gestor.getListaDeTareas().stream().filter( tarea -> tarea.isCompletada() ).count() == 0) {
                    System.out.println("No hay tareas completas aún.");
                    opcion = leerLinea().toLowerCase();
                    continue;
                }

                System.out.println("\nTareas completas:");
                for (Tarea tarea : gestor.getListaDeTareas()) {
                    if (tarea.isCompletada()) {
                        System.out.println(tarea.getId() + ") " + tarea.getDescripcion() + " [" + tarea.getPrioridad() + "]");
                    }
                }
                opcion = leerLinea().toLowerCase();

            }
            // Marcar tarea como completa
            else if (opcion.equals("m")) {
                if(gestor.getListaDeTareas().stream().filter( tarea -> !tarea.isCompletada() ).count() == 0) {
                    System.out.println("No hay tareas pendientes.");
                    opcion = leerLinea().toLowerCase();
                    continue;
                }

                System.out.print("Ingrese el número de la tarea ");
                try {
                    int id = Integer.parseInt(leerLinea());

                    if (gestor.marcarTareaComoCompleta(id)) {
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
                if(gestor.getListaDeTareas().isEmpty()) {
                    System.out.println("No hay tareas para eliminar.");
                    opcion = leerLinea().toLowerCase();
                    continue;
                }

                System.out.print("Ingrese el número de la tarea a eliminar ");
                try {
                    int id = Integer.parseInt(leerLinea());
                    
                    if (gestor.eliminarTarea(id)) {
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
