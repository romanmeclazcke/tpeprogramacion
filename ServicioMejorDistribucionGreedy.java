import java.util.ArrayList;
import java.util.Hashtable;

public class ServicioMejorDistribucionGreedy {
    private ArrayList<Tarea> tareas;
    private ArrayList<Procesadores> procesadores;

    private int limiteTiempoRefrigerados;
    private int tiempoActual;
    private int cantidadEstados;

    public ServicioMejorDistribucionGreedy(ArrayList<Tarea> tareas, ArrayList<Procesadores> procesadores) {
        this.tareas = tareas;
        this.procesadores = procesadores;
        this.tiempoActual = 0;
        this.cantidadEstados = 0;
    }

    //en el método público, para realizar la funcionalidad usando una estrategia greedy en primer lugar creamos un hashtable
    // de procesadores y tareas al cuál llamamos camino actual y lo cargamos con todos los procesadores del sistem.
    //Luego asignamos al atributo de la clase el limite de tiempo enviado por el usuario, creamos un hashtable resultado, al cual
    // le asignamos un hashtable proveniente de la copia de la mejor distribución de tareas en procesadores, y finalmnete 
    // retornamos un objeto de tipo resultado con todos los datos asociados a la resolución para mantener el encapsulamiento.
    public Resultado getBestDistribution(int x) {
        Hashtable<Procesadores, ArrayList<Tarea>> caminoActual = new Hashtable<>();
        for (Procesadores procesador : this.procesadores) {
            caminoActual.put(procesador, new ArrayList<>());
        }

        this.limiteTiempoRefrigerados = x;
        Hashtable<Procesadores, ArrayList<Tarea>> result= this.getCopiaMejor(getBestDistribution(caminoActual, this.tareas));

        return new Resultado(result, this.tiempoActual, this.cantidadEstados*this.procesadores.size());
        
    }

    // usando la estrategia de greedy planteamos la distribución de forma que iterando cada tarea creábamos nuevos estados candidatos
    // y planteamos un algoritmo que dada una tarea se recorren todos procesadores y se analiza, teniendo en cuenta la distribución 
    // formada hasta el momento, cuál es el procesador que quedaría con menor tiempo si se le asigna dicha tarea.
    private Hashtable<Procesadores, ArrayList<Tarea>> getBestDistribution(Hashtable<Procesadores, ArrayList<Tarea>> caminoActual, ArrayList<Tarea> tareas) {
        for (Tarea t : tareas) {
            this.cantidadEstados++;
            Procesadores p = this.obtenerMejorProcesador(t, caminoActual);
            if (p != null) {
                caminoActual.get(p).add(t);
                p.setTiempo_ejecucion(p.getTiempo_ejecucion() + t.getTiempo_ejecucion()); // actualizamos el tiempo de ejecución
                if (p.getTiempo_ejecucion() > this.tiempoActual) {
                    this.tiempoActual = p.getTiempo_ejecucion(); // si el nuevo tiempo es mayor, actualizamos el atributo
                }                                               // que contenía el tiempo más alto del momento
            }else{
                caminoActual.clear();
                return caminoActual;
            }
        }
        return caminoActual;
    }

    // en este método por cada procesador corroboramos si cumple las condiciones para procesar la tarea especificada y a los 
    // posibles candidatos los guardamos en una lista auxiliar, para luego compararlos entre todos y devolver aquel procesador
    // que acumula menos tiempo total en procesar sus tareas.
    private Procesadores obtenerMejorProcesador(Tarea t, Hashtable<Procesadores, ArrayList<Tarea>> caminoActual) {
        ArrayList<Procesadores> aux = new ArrayList<>();
        for (Procesadores p : this.procesadores) {
            ArrayList<Tarea> tareasProcesador = caminoActual.get(p);
            int contador = 0;

            for (Tarea tarea : tareasProcesador) {
                if (tarea.getEs_critica()) {
                    contador++;
                }
            }

            int contadortiempo = 0;
            if (!p.getEsta_regriferado()) {
                    contador=p.getTiempo_ejecucion();
                    if (contadortiempo + t.getTiempo_ejecucion() < this.limiteTiempoRefrigerados && contador < 2) {
                        aux.add(p);
                    }
            }else if(contador<2){
                aux.add(p);
            }
        }

        int tiempoMinimo = Integer.MAX_VALUE;
        int indiceProcesadorMinimo = 0;
        for (int i = 0; i < aux.size(); i++) {
            if (aux.get(i).getTiempo_ejecucion() < tiempoMinimo) {
                indiceProcesadorMinimo = i;
                tiempoMinimo = aux.get(i).getTiempo_ejecucion();
            }
        }
        if(!aux.isEmpty()){
            return aux.get(indiceProcesadorMinimo);
        }
        return null;

    }

    // este método cumple la misma funcionalidad que el método homónimo del servicio con backtracking, mantener el encapsulamiento
    // de los datos devolviendo una nueva copia de los mismos.
    private Hashtable<Procesadores, ArrayList<Tarea>> getCopiaMejor(Hashtable<Procesadores, ArrayList<Tarea>> camino) {
        Hashtable<Procesadores, ArrayList<Tarea>> copiaMejor = new Hashtable<>();
        for (Procesadores p : camino.keySet()) {
            ArrayList<Tarea> copiaTareas = new ArrayList<>();
            for (Tarea t : camino.get(p)) {
                copiaTareas.add(t);
            }
            copiaMejor.put(p, copiaTareas);
        }
        return copiaMejor;
    }
}
