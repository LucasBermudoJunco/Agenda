package ClasesElementales;

public class Hora {

/// Atributo(s)
    private int codigoHora;
    private Integer codigoTarea;

/// Constructor(es)
    @SuppressWarnings("unused")
    public Hora(){}

    public Hora(int codigoHora){
        this.codigoHora = codigoHora;
    }

    public Hora(int codigoHora, int codigoTarea){
        this.codigoHora = codigoHora;
        this.codigoTarea = codigoTarea;
    }

/// Getters y Setters
    public int getCodigoHora() {
        return codigoHora;
    }

    public Integer getCodigoTarea() {
        return codigoTarea;
    }

    public void setCodigoTarea(int codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

/// Otros métodos predeterminados de la clase
    @Override
    public String toString(){
        String horaEnTexto = "";

        horaEnTexto += "Código de la hora: " + this.codigoHora;
        horaEnTexto += "\nCódigo de su tarea: " + this.codigoTarea;

        return horaEnTexto;
    }
}
