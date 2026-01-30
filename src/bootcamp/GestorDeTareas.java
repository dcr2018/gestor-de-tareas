package bootcamp;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class GestorDeTareas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        ArrayList<Tarea> listaDeTareas = new ArrayList<>();
        listaDeTareas.add(new Tarea("Comprar alimentos"));
        listaDeTareas.get(0).establecerId(1);
        listaDeTareas.add(new Tarea("Lavar el auto"));
        listaDeTareas.get(1).establecerId(2);
        listaDeTareas.add(new Tarea("Pagar las cuentas"));
        listaDeTareas.get(2).establecerId(3);
        listaDeTareas.add(new Tarea("Completar las tareas de la plataforma"));
        listaDeTareas.get(3).establecerId(4);

        System.out.print("\033[H\033[2J"); 
        System.out.flush();
        System.out.println("(A)gregar una tarea, (M)ostrar tareas pendientes, (C)ompletar una tarea, (S)alir");
        String opcion = scanner.nextLine().trim().toLowerCase();

        while(!salir) {
            if(opcion.equals("a")) {

                // Agregar tarea

                System.out.print("Ingresa la tarea: ");
                String descripcion = scanner.nextLine().trim();
                listaDeTareas.add(new Tarea(descripcion));
                listaDeTareas.get(listaDeTareas.size() - 1).establecerId(listaDeTareas.size());
                System.out.print("\033[H\033[2J"); 
                System.out.flush();
                System.out.println("Tarea agregada");
                System.out.println("\n(A)gregar una tarea, (M)ostrar tareas pendientes, (C)ompletar una tarea, (S)alir");
                opcion = scanner.nextLine().trim().toLowerCase();

            } else if(opcion.equals("m")) {
                
                // Mostrar tareas pendientes
                
                System.out.print("\033[H\033[2J"); 
                System.out.flush();
                for(Tarea tarea : listaDeTareas) {
                    if(!tarea.completada) {
                        System.out.println(tarea.id + ") " + tarea.descripcion);
                    }
                }
                // System.out.println("──────────────────────────────────────────────────────────────────────");
                System.out.println("\n(A)gregar una tarea, (M)ostrar tareas pendientes, (C)ompletar una tarea, (S)alir");
                opcion = scanner.nextLine().trim().toLowerCase();

            } else if(opcion.equals("c")) {

                // Completar tarea

                System.out.print("Ingrese el número de la tarea a completar: ");
                int id = Integer.parseInt(scanner.nextLine());
                for(Tarea tarea : listaDeTareas) {
                    if(tarea.id == id) {
                        tarea.marcarComoCompletada();
                        break;
                    }
                }

                System.out.print("\033[H\033[2J"); 
                System.out.flush();
                System.out.println("La tarea " + id + " ha sido marcada como completada.");
                System.out.println("\n(A)gregar una tarea, (M)ostrar tareas pendientes, (C)ompletar una tarea, (S)alir");
                opcion = scanner.nextLine().trim().toLowerCase();

            } else if(opcion.equals("s")) {

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
}
