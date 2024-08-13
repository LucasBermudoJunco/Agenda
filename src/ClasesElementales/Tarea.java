package ClasesElementales;

import java.io.Serializable;

public class Tarea implements Comparable<Tarea> {

/// Atributo(s)
    private int codigoTarea;
    private int codigoHora;
    private String contenidoTarea;

/// Constructor(es)
    @SuppressWarnings("unused")
    public Tarea() {}

    @SuppressWarnings("unused")
    public Tarea(int codigoTarea){
        this.codigoTarea = codigoTarea;
    }

    @SuppressWarnings("unused")
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

/// Otros métodos predeterminados de la clase
    @Override
    public String toString() {
        return "Codigo de la tarea:  " + codigoTarea +
               "\nHora:  " + codigoHora +
               "\nContenido de la tarea:  ´´" + contenidoTarea + "``";
    }

    @Override
    public int compareTo(Tarea tarea){
        return this.codigoHora - tarea.codigoHora;
    }

}
