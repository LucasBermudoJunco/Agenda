package ClasesElementales;

/*import DAO.DAOGeneral;*/
import DAO.HoraDAO;
import DAO.TareaDAO;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Semana {

/// Atributo(s)
    private int codigoSemana;
    private List<Hora> horas;
    private List<Tarea> tareas;
    /*DAOGeneral daoGeneral = new DAOGeneral();*/
    TareaDAO tareaDAO = new TareaDAO();
    HoraDAO horaDAO = new HoraDAO();

/// Constructor(es)
    @SuppressWarnings("unused")
    public Semana() {}

    @SuppressWarnings("unused")
    public Semana(int codigoSemana) {}

    @SuppressWarnings("unused")
    public Semana(int codigoSemana, List<Hora> horas) {
        this.codigoSemana = codigoSemana;
        this.horas = horas;
    }

/// Getters y Setters
    @SuppressWarnings("unused")
    public int getCodigoSemana() {
        return codigoSemana;
    }

    @SuppressWarnings("unused")
    public List<Hora> getHoras() {
        return horas;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    /// Método(s) específico(s)
    public void obtenerHorasDeLaBaseDeDatos() {
        horas = horaDAO.obtenerHoras();
    }

    public void obtenerTareasDeLaBaseDeDatos() {
        tareas = tareaDAO.obtenerTareas();
    }

    public Hora obtenerHora(int codigoHora) {
        return horaDAO.read(codigoHora);
    }

    public Tarea obtenerTarea(int codigoTarea) {
        return tareaDAO.read(codigoTarea);
    }

    public int obtenerCodigoTareaAleatorio() {
        Random random = new Random();
        int codigoAleatorioTarea;

        do{
            codigoAleatorioTarea = random.nextInt(99999) + 1;
        } while(!tareaDAO.estaLibreEsteCodigoTarea(codigoAleatorioTarea));

        return codigoAleatorioTarea;
    }

    public void crearTareaYActualizarHora(Tarea tarea){
        tarea.setCodigoTarea(obtenerCodigoTareaAleatorio());
        tareaDAO.create(tarea);

        horas = horaDAO.obtenerHoras();
        Iterator<Hora> horasIterator = horas.iterator();
        while(horasIterator.hasNext()){
            Hora hora = horasIterator.next();

            if(hora.getCodigoHora() == tarea.getCodigoHora()){
                hora.setCodigoTarea(tarea.getCodigoTarea());

                horaDAO.update(hora.getCodigoHora(), hora.getCodigoTarea());
            }
        }
    }

    public void cambiarTareaYActualizarHora(Hora hora, Tarea tareaNueva) {
        tareaNueva.setCodigoTarea(obtenerCodigoTareaAleatorio());
        boolean tareaUHoraYaEncontrada = false;

        // Borrar la tarea antigua
        List<Tarea> tareas = tareaDAO.obtenerTareas();
        Iterator<Tarea> tareasIterator = tareas.iterator();
        while (tareasIterator.hasNext() && !tareaUHoraYaEncontrada){
            Tarea estaTarea = tareasIterator.next();

            if(estaTarea.getCodigoTarea() == hora.getCodigoTarea()){
                tareaUHoraYaEncontrada = true;

                tareasIterator.remove();

                tareaDAO.delete(estaTarea.getCodigoTarea());
            }
        }

        // Crear la tarea nueva
        tareaDAO.create(tareaNueva);

        // Establecer la tarea nueva en la misma hora
        tareaUHoraYaEncontrada = false;
        horas = horaDAO.obtenerHoras();
        Iterator<Hora> horasIterator = horas.iterator();
        while(horasIterator.hasNext() && !tareaUHoraYaEncontrada){
            Hora estaHora = horasIterator.next();

            if(estaHora.getCodigoHora() == tareaNueva.getCodigoHora()){
                tareaUHoraYaEncontrada = true;

                estaHora.setCodigoTarea(tareaNueva.getCodigoTarea());

                horaDAO.update(estaHora.getCodigoHora(), tareaNueva.getCodigoTarea());
            }
        }
    }

    public void eliminarTarea(int codigoTareaAEliminar) {
        tareaDAO.delete(codigoTareaAEliminar);
    }

    public void eliminarLaTareaDeEstaHora(int codigoHora){
        horaDAO.eliminarSuTarea(codigoHora);
    }

    public void ordenarTareasPorHoras() {
        Collections.sort(tareas);
    }
}
