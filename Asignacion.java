// package servicios;
// import java.util.ArrayList;
// import java.util.Hashtable;

// public class Asignacion {
//     private static final int MAXCRITICAS = 2;
//     private ArrayList<Procesador> procesadores;
//     private ArrayList<Tarea> tareas;
//     private Hashtable<Procesador, ArrayList<Tarea>> mejorDistribucion;
//     private int tiempoMaxRefrigerados;
//     private int tiempoParcial, mejorTiempo;

//     public Asignacion(ArrayList<Procesador> procesadores, ArrayList<Tarea> tareas, int tiempoMaxRefrigerados) {
//         this.procesadores = procesadores;
//         this.tareas = tareas;
//         this.tiempoMaxRefrigerados = tiempoMaxRefrigerados;
//         this.tiempoParcial = 0;
//         this.mejorTiempo = 0;
//     }

//     public Hashtable<Procesador, ArrayList<Tarea>> distribuirTareas() {
//         Hashtable<Procesador, ArrayList<Tarea>> parcial = new Hashtable<>();
//         int punteroTareas = 0;

//         distribuirTareas(parcial, punteroTareas);

//         return this.mejorDistribucion;
//     }

//     private void distribuirTareas(Hashtable<Procesador, ArrayList<Tarea>> parcial, int punteroTareas) {
//         if (punteroTareas == parcial.size()) {
//             if (this.mejorDistribucion == null || this.tiempoParcial < this.mejorTiempo) {
//                 this.copiarMejorSolucion(parcial);
//             }
//         } else {
//             Tarea tarea = this.tareas.get(punteroTareas);

//             for (Procesador proc : this.procesadores) {
//                 if (!parcial.containsKey(proc)) {
//                     parcial.put(proc, new ArrayList<Tarea>());

//                     if (this.esApto(parcial.get(proc), tarea, proc)) 
//                         parcial.get(proc).add(tarea);

//                     proc.aumentarTiempoFinalEjecucion(tarea.getTiempoEjecucion()); //actualizo el tiempofinal del procesador
//                     if (proc.getTiempoEjecucion() > this.tiempoParcial) {
//                         this.tiempoParcial = proc.getTiempoEjecucion();
//                     }

//                     distribuirTareas(parcial, punteroTareas+1);
//                     parcial.get(proc).remove(tarea);
//                 }
//             }
//         }
//     }

//     private void copiarMejorSolucion(Hashtable<Procesador, ArrayList<Tarea>> parcial) {
//         for (Procesador p : parcial.keySet()) {
//             ArrayList<Tarea> tareasDeP = new ArrayList<>();
//             for (Tarea t : parcial.get(p)) 
//                 tareasDeP.add(t);

//             this.mejorDistribucion.put(p, tareasDeP);
//         }
//     }

//     private boolean esApto(ArrayList<Tarea> tareasProc, Tarea tarea_a_asignar, Procesador proc) {
//         int criticas = 0;
//         if (tarea_a_asignar.isEs_critica()) { 
//             for (int i = 0; i < tareasProc.size(); i++) { //sólo verifico si la que voy a asignar es crítica, sino no me importa
//                 if (tareasProc.get(i).isEs_critica())
//                     criticas++;
//             }
//         }

//         if (criticas < MAXCRITICAS && !proc.estaRefrigerado() && proc.getTiempoEjecucion() < this.tiempoMaxRefrigerados)
//             return true;

//         return false;
//     }
// }
