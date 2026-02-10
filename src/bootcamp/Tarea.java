package bootcamp;

/**
 * Representa una tarea en el gestor de tareas.
 */
public class Tarea {

    public int id;
    public String descripcion;
    public String prioridad;
    public boolean completada;

    /**
     * Construye una nueva tarea con los parámetros especificados.
     *
     * @param descripcion la descripción de la tarea
     * @param prioridad   el nivel de prioridad de la tarea ("alta" o "media")
     * @param id          el identificador único de la tarea
     */
    public Tarea(String descripcion, String prioridad, int id) {
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.id = id;
        this.completada = false;
    }

    public void marcarComoCompletada() {
        this.completada = true;
    }
    
}
