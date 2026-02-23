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
        setCompletada(false);
        setDescripcion(descripcion);
        setPrioridad(prioridad);
        setId(id);
        
    }

    public boolean isCompletada() {
		return completada;
	}

	public void setCompletada(boolean completada) {
		this.completada = completada;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	
    
}

