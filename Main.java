public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("./datasets/Procesadores.csv", "./datasets/Tareas.csv");
	

		ServicioMejorDistribucionBacktracking serviciomejor= new ServicioMejorDistribucionBacktracking(servicios.getTareas(), servicios.getProcesadores());

		ServicioMejorDistribucionGreedy dist= new ServicioMejorDistribucionGreedy(servicios.getTareas(), servicios.getProcesadores());

		System.out.println(serviciomejor.getBestDistribution(200));
		
	}
} 