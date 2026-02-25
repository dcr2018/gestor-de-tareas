package bootcamp;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GestorDeTareasTest {

	@Test
	public void testAgregarTarea() {
		GestorDeTareas gestor = new GestorDeTareas();
		ArrayList<Tarea> tareas = gestor.getListaDeTareas();
		int cantidadInicial = tareas.size();
		
		gestor.agregarTarea("Hacer ejercicio", "alta");
		
		assertEquals(cantidadInicial + 1, tareas.size());
		assertEquals("Hacer ejercicio", tareas.get(cantidadInicial).getDescripcion());
	}
	
	@Test
	public void testMarcarTareaComoCompleta() {
		GestorDeTareas gestor = new GestorDeTareas();
		ArrayList<Tarea> tareas = gestor.getListaDeTareas();
		
		boolean resultado = gestor.marcarTareaComoCompleta(1);
		
		assertTrue(resultado);
		assertTrue(tareas.get(0).isCompletada());
	}

	@Test
	public void testEliminarTarea() {
		GestorDeTareas gestor = new GestorDeTareas();
		ArrayList<Tarea> tareas = gestor.getListaDeTareas();
		int cantidadInicial = tareas.size();
		
		boolean resultado = gestor.eliminarTarea(1);
		
		assertTrue(resultado);
		assertEquals(cantidadInicial - 1, tareas.size());
	}
}
