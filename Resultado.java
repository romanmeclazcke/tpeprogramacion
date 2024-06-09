import java.util.ArrayList;
import java.util.Hashtable;

public class Resultado {
   private  Hashtable<Procesadores, ArrayList<Tarea>> resultado;
   private int tiempo;
   private int cantidadEstados;
   
   public Resultado(Hashtable<Procesadores, ArrayList<Tarea>> resultado , int tiempo, int cantidadEstados){
    this.resultado=resultado;
    this.tiempo=tiempo;
    this.cantidadEstados=cantidadEstados;
   }

public Hashtable<Procesadores, ArrayList<Tarea>> getResultado() {
    return resultado;
}

public void setResultado(Hashtable<Procesadores, ArrayList<Tarea>> resultado) {
    this.resultado = resultado;
}

public int getTiempo() {
    return tiempo;
}

public void setTiempo(int tiempo) {
    this.tiempo = tiempo;
}

public int getCantidadEstados() {
    return cantidadEstados;
}

public void setCantidadEstados(int cantidadEstados) {
    this.cantidadEstados = cantidadEstados;
}



   
}
