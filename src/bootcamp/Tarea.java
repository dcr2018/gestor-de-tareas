package bootcamp;

public class Tarea {

    public int id;
    public String descripcion;
    public boolean completada;

    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.completada = false;
    }

    public void marcarComoCompletada() {
        this.completada = true;
    }

    public void establecerId(int id) {
        this.id = id;
    }
}
