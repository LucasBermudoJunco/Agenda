package ClasesElementales;

public class Tarea {

/// Atributo(s)
    private int codigoTarea;
    private int codigoHora;
    private String contenidoTarea;

/// Constructor(es)
    public Tarea() {}

    public Tarea(int codigoTarea){
        this.codigoTarea = codigoTarea;
    }

    public Tarea(int codigoTarea, String contenidoTarea){
        this.codigoTarea = codigoTarea;
        this.contenidoTarea = contenidoTarea;
    }

    public Tarea(String contenidoTarea, int codigoHora){
        this.contenidoTarea = contenidoTarea;
        this.codigoHora = codigoHora;
    }

    public Tarea(int codigoTarea, int codigoHora, String contenidoTarea){
        this.codigoTarea = codigoTarea;
        this.codigoHora = codigoHora;
        this.contenidoTarea = contenidoTarea;
    }

/// Getters y Setters
    public int getCodigoTarea() {
        return codigoTarea;
    }

    public void setCodigoTarea(int codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

    public int getCodigoHora() {
        return codigoHora;
    }

    public String getContenidoTarea() {
        return contenidoTarea;
    }

}
