
public class Tarea {
    private String id_tarea;
    private String nombre_tarea;
    private int tiempo_ejecucion;
    private boolean es_critica;
    private int nivel_prioridad;

    public Tarea(String id_tarea, String nombre_tarea, int tiempo_ejecucion, boolean es_critica, int nivel_proridad){
        this.id_tarea = id_tarea;
        this.nombre_tarea=nombre_tarea;
        this.tiempo_ejecucion=tiempo_ejecucion;
        this.es_critica=es_critica;
        this.nivel_prioridad=nivel_proridad;
    }

    public String getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(String id_tarea) {
        this.id_tarea = id_tarea;
    }

    public String getNombre_tarea() {
        return nombre_tarea;
    }

    public void setNombre_tarea(String nombre_tarea) {
        this.nombre_tarea = nombre_tarea;
    }

    public int getTiempo_ejecucion() {
        return tiempo_ejecucion;
    }

    public void setTiempo_ejecucion(int tiempo_ejecucion) {
        this.tiempo_ejecucion = tiempo_ejecucion;
    }

    public boolean getEs_critica() {
        return es_critica;
    }

    public void setEs_critica(boolean es_critica) {
        this.es_critica = es_critica;
    }

    public int getPrioridad() {
        return nivel_prioridad;
    }

    public void setPrioridad(int nivel_prioridad) {
        this.nivel_prioridad = nivel_prioridad;
    }
}
