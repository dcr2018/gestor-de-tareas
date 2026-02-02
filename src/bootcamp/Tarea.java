package bootcamp;

public class Tarea {

    public int id;
    public String descripcion;
    public boolean completada;

    public Tarea(String descripcion, int id) {
        this.descripcion = descripcion;
        this.id = id;
        this.completada = false;
    }

    public void marcarComoCompletada() {
        this.completada = true;
    }
    
}
