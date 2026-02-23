package bootcamp;

public class TareaUrgente extends Tarea {

    /**
     * Construye una tarea urgente con los parámetros especificados.
     *
     * @param descripcion la descripción de la tarea
     * @param id          el identificador único de la tarea
     */
    public TareaUrgente(String descripcion, int id) {
        super(descripcion, "alta", id);
    }
    
}
