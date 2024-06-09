public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("./datasets/Procesadores.csv", "./datasets/Tareas.csv");
	

		ServicioMejorDistribucionBacktracking servicioBacktracking= new ServicioMejorDistribucionBacktracking(servicios.getTareas(), servicios.getProcesadores());

		ServicioMejorDistribucionGreedy sericioGreedy= new ServicioMejorDistribucionGreedy(servicios.getTareas(), servicios.getProcesadores());

		Resultado resultBacktracking = servicioBacktracking.getBestDistribution(200);

		Resultado resultGreedy = sericioGreedy.getBestDistribution(200);

		System.out.println(resultBacktracking.getCantidadEstados());
		System.out.println(resultBacktracking.getResultado());
		System.out.println(resultBacktracking.getTiempo());
		

		
		System.out.println(resultGreedy.getCantidadEstados());
		System.out.println(resultGreedy.getResultado());
		System.out.println(resultGreedy.getTiempo());
	}
} 