public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("./datasets/Procesadores.csv", "./datasets/Tareas.csv");
	
		ServicioMejorDistribucionBacktracking servicioBacktracking= new ServicioMejorDistribucionBacktracking(servicios.getTareas(), servicios.getProcesadores());

		ServicioMejorDistribucionGreedy sericioGreedy= new ServicioMejorDistribucionGreedy(servicios.getTareas(), servicios.getProcesadores());

		Resultado resultBacktracking = servicioBacktracking.getBestDistribution(200);

		Resultado resultGreedy = sericioGreedy.getBestDistribution(100);
		
		System.out.println(resultGreedy);
		System.out.println(resultBacktracking);

		

	}
} 