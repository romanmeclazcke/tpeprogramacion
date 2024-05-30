import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("./datasets/Procesadores.csv", "./datasets/Tareas.csv");
	

		ServicioMejorDistribucion serviciomejor= new ServicioMejorDistribucion(servicios.getTareas(), servicios.getProcesadores());

		
		System.out.println(serviciomejor.getBestDistribution(300));
		
	}
} 