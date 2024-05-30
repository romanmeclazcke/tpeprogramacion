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
    }

    public Hashtable<Procesadores, ArrayList<Tarea>> getBestDistribution(int x){
        Hashtable<Procesadores, ArrayList<Tarea>> caminoActual = new Hashtable<>();
        this.limiteTiempoRefrigerados=x;
        getBestDistribution(caminoActual, 0);
        System.out.println("tiempo final" + this.tiempoMejor);
        return this.mejor;
    }



    private void getBestDistribution(Hashtable<Procesadores, ArrayList<Tarea>> caminoActual, int indexTarea){
        if (indexTarea==this.tareas.size()) {//no tengo mas tareas por asignar
            if (this.tiempoActual<this.tiempoMejor) {//comparo con la mejor solucion
                this.mejor=this.getCopiaMejor(caminoActual);
                this.tiempoMejor = this.tiempoActual;
            }
        }else{
            Tarea t= this.tareas.get(indexTarea);
            for (Procesadores  procesador : this.procesadores) {
                if (caminoActual.get(procesador)==null) {
                    caminoActual.put(procesador, new ArrayList<Tarea>());
                }
                if (sePuede(procesador,t, caminoActual)) {    //si la tarea se puede asignar al procesador
                    if (caminoActual.containsKey(procesador)) {
                        caminoActual.get(procesador).add(t); //asigno la tarea al procesador
                        procesador.setTiempo_ejecucion(procesador.getTiempo_ejecucion()+t.getTiempo_ejecucion()); //actualizo el tiempo de ejecucion del procesador
                        if (procesador.getTiempo_ejecucion()> this.tiempoActual) {
                            System.out.println(procesador.getTiempo_ejecucion());
                            System.out.println(this.tiempoActual + "actual");
                            this.tiempoActual=procesador.getTiempo_ejecucion();
                    }
                }
                getBestDistribution(caminoActual,indexTarea+1); //llamo recursivamente actualizando el indice de la tarea
                
                if (caminoActual.get(procesador)!=null) {//si la lista de tareas del procesador tiene tareas
                    caminoActual.get(procesador).remove(t);  //elimino la ultima
                    procesador.setTiempo_ejecucion(procesador.getTiempo_ejecucion()-t.getTiempo_ejecucion());//desactualizo el tiempo de ejecucion del procesador
                    this.tiempoActual = tiempoActual - t.getTiempo_ejecucion();
                }
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
