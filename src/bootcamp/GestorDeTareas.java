package bootcamp;

import java.util.ArrayList;

public class GestorDeTareas implements TareaServicio {

    private ArrayList<Tarea> listaDeTareas = new ArrayList<>();

    public GestorDeTareas() {
        listaDeTareas.add(new Tarea("Ir al supermercado", "alta", 1));
        listaDeTareas.add(new Tarea("Lavar el auto", "media", 2));
        listaDeTareas.add(new Tarea("Pagar las cuentas", "media", 3));
        listaDeTareas.add(new Tarea("Completar las tareas de la plataforma","media", 4));
        listaDeTareas.add(new Tarea("Ir a comprar atún","media", 5));
    }
    
    public ArrayList<Tarea> getListaDeTareas() {
        return listaDeTareas;
    }

    public void agregarTarea(String descripcion, String prioridad) {
        Tarea ultimaTarea = listaDeTareas.stream()
                .max((t1, t2) -> Integer.compare(t1.id, t2.id))
                .orElse(null);
        int nuevoId = ultimaTarea != null ? ultimaTarea.id + 1 : 1;

        listaDeTareas.add(new Tarea(descripcion, prioridad, nuevoId));
    }

    public boolean marcarTareaComoCompleta(int id) {
        Tarea tareaAMarcar = listaDeTareas.stream()
                .filter(tarea -> !tarea.isCompletada())
                .filter(tarea -> tarea.getId() == id)
                .findFirst()
                .orElse(null);

        if (tareaAMarcar != null) {
            tareaAMarcar.setCompletada(true);
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminarTarea(int id) {
        return listaDeTareas.removeIf(tarea -> tarea.getId() == id);
    }
    
}
