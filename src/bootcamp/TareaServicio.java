package bootcamp;

import java.util.ArrayList;

public interface TareaServicio {

	ArrayList<Tarea> getListaDeTareas();
	void agregarTarea(String descripcion, String prioridad);
	boolean marcarTareaComoCompleta(int id);
	boolean eliminarTarea(int id);
}
