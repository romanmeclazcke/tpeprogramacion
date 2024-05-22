import java.util.ArrayList;
import java.util.List;

import utils.Tarea;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("./datasets/Procesadores.csv", "./datasets/Tareas.csv");
	
		// Tarea t1 = servicios.servicio1("T2");
		
		// System.out.println(t1.getId_tarea());



		// List<Tarea> aux = servicios.servicio2(false);
		// for (Tarea t : aux) {
		// 	System.out.println(t.getEs_critica() + "" + t.getId_tarea());
		// }



		List<Tarea> aux= servicios.servicio3(10,50);
		for (Tarea t : aux) {
			System.out.println(t.getEs_critica() + " "+ t.getId_tarea() + " "+ t.getPrioridad());
		}
		
	}
}