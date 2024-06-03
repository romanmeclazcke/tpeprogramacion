class Procesadores {
    private String id_procesador;
    private String codigo_procesador;
    private boolean esta_regriferado;
    private int año_funcionamiento;
    private int tiempo_ejecucion;

    public Procesadores(String id_procesador, String codigo_procesador, boolean esta_regriferado, int año_funcionamiento){
        this.id_procesador=id_procesador;
        this.codigo_procesador= codigo_procesador;
        this.esta_regriferado=esta_regriferado;
        this.año_funcionamiento= año_funcionamiento;
    }

    public int getTiempo_ejecucion() {
        return tiempo_ejecucion;
    }

    public void setTiempo_ejecucion(int tiempo_ejecucion) {
        this.tiempo_ejecucion = tiempo_ejecucion;
    }

    public String getId_procesador() {
        return id_procesador;
    }

    public void setId_procesador(String id_procesador) {
        this.id_procesador = id_procesador;
    }

    public String getCodigo_procesador() {
        return codigo_procesador;
    }

    public void setCodigo_procesador(String codigo_procesador) {
        this.codigo_procesador = codigo_procesador;
    }

    public boolean getEsta_regriferado() {
        return esta_regriferado;
    }

    public void setEsta_regriferado(boolean esta_regriferado) {
        this.esta_regriferado = esta_regriferado;
    }

    public int getAño_funcionamiento() {
        return año_funcionamiento;
    }

    public void setAño_funcionamiento(int año_funcionamiento) {
        this.año_funcionamiento = año_funcionamiento;
    }

    @Override
    public String toString(){
        return  this.id_procesador;
    }

    @Override
    public int hashCode(){
        return this.id_procesador.hashCode();
    }
    
}
