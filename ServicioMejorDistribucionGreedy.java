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

    public Hashtable<Procesadores, ArrayList<Tarea>> getBestDistribution(int x) {
        Hashtable<Procesadores, ArrayList<Tarea>> caminoActual = new Hashtable<>();
        for (Procesadores procesador : this.procesadores) {
            caminoActual.put(procesador, new ArrayList<>());
        }
        this.limiteTiempoRefrigerados = x;
        Hashtable<Procesadores, ArrayList<Tarea>> result= this.getCopiaMejor(getBestDistribution(caminoActual, this.tareas));
        System.out.println(tiempoActual);
        System.out.println(cantidadEstados* this.procesadores.size());
        return result;
    }

    private Hashtable<Procesadores, ArrayList<Tarea>> getBestDistribution(Hashtable<Procesadores, ArrayList<Tarea>> caminoActual, ArrayList<Tarea> tareas) {
        for (Tarea t : tareas) {
            this.cantidadEstados++;
            Procesadores p = this.obtenerMejorProcesador(t, caminoActual);
            if (p != null) {
                caminoActual.get(p).add(t);
                p.setTiempo_ejecucion(p.getTiempo_ejecucion() + t.getTiempo_ejecucion());
                if (p.getTiempo_ejecucion() > this.tiempoActual) {
                    this.tiempoActual = p.getTiempo_ejecucion();
                }
            } else {
                caminoActual.clear();
            }
        }
        return caminoActual;
    }

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
            if (p.getEsta_regriferado()) {
                for (Tarea tarea : tareasProcesador) {
                    contadortiempo += tarea.getTiempo_ejecucion();
                }
            }

            if (contadortiempo + t.getTiempo_ejecucion() < this.limiteTiempoRefrigerados && contador < 2) {
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

        return aux.get(indiceProcesadorMinimo);
    }

    private Hashtable<Procesadores, ArrayList<Tarea>> getCopiaMejor(Hashtable<Procesadores, ArrayList<Tarea>> camino) {
        Hashtable<Procesadores, ArrayList<Tarea>> copiaMejor = new Hashtable<>();
        for (Procesadores p : camino.keySet()) {
            ArrayList<Tarea> copiaTareas = new ArrayList<>();
            ;
            for (Tarea t : camino.get(p)) {
                copiaTareas.add(t);
            }
            copiaMejor.put(p, copiaTareas);
        }
        return copiaMejor;
    }
}
