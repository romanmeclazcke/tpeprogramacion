import java.util.ArrayList;
import java.util.Hashtable;

public class ServicioMejorDistribucion {
    private  ArrayList<Tarea> tareas;
    private ArrayList<Procesadores> procesadores;

    private int limiteTiempoRefrigerados;
    Hashtable<Procesadores, ArrayList<Tarea>> mejor;
    private int tiempoMejor;
    private int tiempoActual;

    public ServicioMejorDistribucion(ArrayList<Tarea> tareas, ArrayList<Procesadores> procesadores) {
        this.tareas = tareas;
        this.procesadores = procesadores;
        this.tiempoMejor=Integer.MAX_VALUE;
        this.tiempoActual=0;
        this.mejor= new  Hashtable<Procesadores, ArrayList<Tarea>>();
    }

    public Hashtable<Procesadores, ArrayList<Tarea>> getBestDistribution(int x){
        Hashtable<Procesadores, ArrayList<Tarea>> caminoActual = new Hashtable<>();
        this.limiteTiempoRefrigerados=x;
        getBestDistribution(caminoActual, 0);
        System.out.println(this.tiempoMejor);
        return this.mejor;
    }



    private void getBestDistribution(Hashtable<Procesadores, ArrayList<Tarea>> caminoActual, int indexTarea) {
        if (indexTarea == this.tareas.size()) { //si asigne todas las tareas
            if (this.tiempoActual < this.tiempoMejor) { //pregunto si el tiempo del camino actual mejora mi solucion
                this.mejor = getCopiaMejor(caminoActual); //si cumple creo una COPIA para no propagar modificaciones
                this.tiempoMejor = this.tiempoActual;
            }
        } else {
            Tarea t = this.tareas.get(indexTarea);
            for (Procesadores procesador : this.procesadores) {
                if (caminoActual.get(procesador) == null) {
                    caminoActual.put(procesador, new ArrayList<Tarea>());
                }
    
                if (sePuede(procesador, t, caminoActual)) { //si puedo asignar la tarea al procesador
                    caminoActual.get(procesador).add(t); //asigno
                    procesador.setTiempo_ejecucion(procesador.getTiempo_ejecucion() + t.getTiempo_ejecucion()); //aumento el tiempo del procesador
                    int tiempoPrevio = this.tiempoActual; //guardo el tiempo actual antes de la recursion
    
                    if (procesador.getTiempo_ejecucion() > this.tiempoActual) { //pregunto si el tiempio del procesador empeora mi solucion actual (llevo el t del peor procesador)
                        this.tiempoActual = procesador.getTiempo_ejecucion();
                    }
    
                    getBestDistribution(caminoActual, indexTarea + 1); //recusion acutalizando la tarea

                    caminoActual.get(procesador).remove(t); //reviero los cambios al volver de la recusion
                    procesador.setTiempo_ejecucion(procesador.getTiempo_ejecucion() - t.getTiempo_ejecucion());
                    this.tiempoActual = tiempoPrevio; // restauro el tiempo actual luego de volver del llamado recursivo
                }
            }
        }
    }
    
        

    public boolean sePuede(Procesadores p, Tarea t, Hashtable<Procesadores, ArrayList<Tarea>> caminoActual){ //funcion que retorna la posibilidad de insetar una tarea en un procesador dadas ciertas condiciens
            ArrayList<Tarea> tareasProcesador = caminoActual.get(p);  
            int contador=0;
            for (Tarea tarea : tareasProcesador) {
                if (tarea.getEs_critica()) {
                    contador++;
                }
            }

            int contadortiempo=0;
            if (p.getEsta_regriferado()) {
                for (Tarea tarea : tareasProcesador) {
                    contadortiempo+=tarea.getTiempo_ejecucion();
                }
            }

            if(contadortiempo+t.getTiempo_ejecucion()<this.limiteTiempoRefrigerados &&contador<2){
                return true;
            }else{
                return false;
            }
    }


    public Hashtable<Procesadores, ArrayList<Tarea>> getCopiaMejor(Hashtable<Procesadores, ArrayList<Tarea>> camino) {
        Hashtable<Procesadores, ArrayList<Tarea>> copiaMejor = new Hashtable<>();
        for (Procesadores p : camino.keySet()) {
            ArrayList<Tarea> copiaTareas = new ArrayList<>();;
            for (Tarea t : camino.get(p)) {
                copiaTareas.add(t);
            }
            copiaMejor.put(p, copiaTareas);
        }
        return copiaMejor;
    }
}
