package ClasesElementales;

import DAO.HoraDAO;
import DAO.TareaDAO;

import java.util.ArrayList;
import java.util.List;

public class Semana {

/// Atributo(s)
    private int codigoSemana;
    private List<Hora> horas;
    TareaDAO tareaDAO = new TareaDAO();
    HoraDAO horaDAO = new HoraDAO();

/// Constructor(es)
    public Semana() {}

    public Semana(int codigoSemana) {}

    public Semana(int codigoSemana, List<Hora> horas) {
        this.codigoSemana = codigoSemana;
        this.horas = horas;
    }

/// Getters y Setters
    public int getCodigoSemana() {
        return codigoSemana;
    }

    public List<Hora> getHoras() {
        return horas;
    }

/// Método(s) específico(s)
    public void obtenerHorasDeLaBaseDeDatos() {
        horas = horaDAO.obtenerHoras();
    }

    public Hora obtenerHora(int codigoHora) {
        return horaDAO.read(codigoHora);
    }

    public Tarea obtenerTarea(int codigoTarea) {
        return tareaDAO.read(codigoTarea);
    }

}
