
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */

public class Servicios {

	private Hashtable<String, Tarea> criticas;
	private Hashtable<String, Tarea> noCriticas;
	private ArrayList<Tarea> tareas;
	private ArrayList<Procesadores> procesadores;

	/*
	 * Expresar la complejidad temporal del constructor.
	 */

	public Servicios(String pathProcesadores, String pathTareas) {
		this.criticas = new Hashtable<>();
		this.noCriticas = new Hashtable<>();
		this.tareas = new ArrayList<>();
		this.procesadores = new ArrayList<>();
		this.readTasks(pathTareas);
		this.readProcessors(pathProcesadores);
	}

	/*
	 * Expresar la complejidad temporal del servicio 1.
	 * 
	 * Complejidad: O(1) debido a que accedemos a laS HashTable mediante
	 * el método containsKey() a partir del ID de la tarea y la complejidad
	 * del mismo es O(1)
	 */
	public Tarea servicio1(String ID) {
		if (this.criticas.containsKey(ID)) {
			return this.criticas.get(ID);
		} else if (this.noCriticas.containsKey(ID)) {
			return this.noCriticas.get(ID);
		}
		return null;
	}

	/*
	 * Complejidad: O(1), debido a que al tener las tareas separadas según un
	 * criterio de
	 * si las mismas son críticas o no, lo único que debemos hacer es instanciar un
	 * arraylist
	 * y agregar las tareas correspondientes según el criterio de búsqueda.
	 */
	public List<Tarea> servicio2(boolean esCritica) {
		if (esCritica) {
			return new ArrayList<Tarea>(this.criticas.values());
		} else {
			return new ArrayList<Tarea>(this.noCriticas.values());
		}
	}

	/*
	 * Complejidad: O(n), debido a que estando todas las tareas almacenadas en un
	 * arraylist
	 * debemos recorrerlo por completo y por cada tarea consultar si su prioridad se
	 * encuentra entre el rango determinado.
	 */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		List<Tarea> aux = new ArrayList<Tarea>();
		for (Tarea t : this.tareas) {
			if (t.getPrioridad() > prioridadInferior && t.getPrioridad() < prioridadSuperior) {
				aux.add(t);
			}
		}
		return aux;
	}

	// reader

	public void readTasks(String taskPath) {

		// Obtengo una lista con las lineas del archivo
		// lines.get(0) tiene la primer linea del archivo
		// lines.get(1) tiene la segunda linea del archivo... y así
		ArrayList<String[]> lines = this.readContent(taskPath);

		for (String[] line : lines) {
			// Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
			String id = line[0].trim();
			String nombre = line[1].trim();
			Integer tiempo = Integer.parseInt(line[2].trim());
			Boolean critica = Boolean.parseBoolean(line[3].trim());
			Integer prioridad = Integer.parseInt(line[4].trim());
			
			Tarea t = new Tarea(id, nombre, tiempo, critica, prioridad);

			if (critica) {
				this.criticas.put(id, t);
			} else {
				this.noCriticas.put(id, t);
			}
			this.tareas.add(t);
		}

	}

	public void readProcessors(String processorPath) {

		// Obtengo una lista con las lineas del archivo
		// lines.get(0) tiene la primer linea del archivo
		// lines.get(1) tiene la segunda linea del archivo... y así
		ArrayList<String[]> lines = this.readContent(processorPath);

		for (String[] line : lines) {
			// Cada linea es un arreglo de Strings, donde cada posicion guarda un

			String id = line[0].trim();
			String codigo = line[1].trim();
			Boolean refrigerado = Boolean.parseBoolean(line[2].trim());
			Integer anio = Integer.parseInt(line[3].trim());
			Procesadores procesador = new Procesadores(id, codigo, refrigerado, anio);
			this.procesadores.add(procesador);
			// Aca instanciar lo que necesiten en base a los datos leidos

		}

	}

	private ArrayList<String[]> readContent(String path) {
		ArrayList<String[]> lines = new ArrayList<String[]>();

		File file = new File(path);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				lines.add(line.split(";"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}

		return lines;
	}

	public ArrayList<Tarea> getTareas() {
		return this.tareas;
	}

	public ArrayList<Procesadores> getProcesadores() {
		return this.procesadores;
	}

}