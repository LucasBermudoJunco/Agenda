package Controlador;

import ClasesElementales.Hora;
import ClasesElementales.Tarea;
import DAO.HoraDAO;
import DAO.SemanaDAO;
import DAO.TareaDAO;
import com.google.gson.Gson;

import java.io.*;

public class Controlador {

/// Atributo(s)
    private SemanaDAO semanaDAO;
    private HoraDAO horaDAO;
    private TareaDAO tareaDAO;
    private BufferedReader lector;
    private BufferedWriter escritor;
    private Gson gson;
    
/// Constructor(es)
    public Controlador() {}
    
/// Método(s) específico(s)
    public boolean horaIntrodValida(int horaIntrod) {
        horaDAO = new HoraDAO();
        String rutaFichero = "src//Ficheros//Hora.json";
        
        try {
            escritor = new BufferedWriter(new FileWriter(rutaFichero));
            
            escritor.write(String.valueOf(horaIntrod));
            
            escritor.close();
        } catch(IOException e){
            e.printStackTrace();
        }
        
        return horaDAO.estaHoraExiste(rutaFichero);
    }
    
    public Hora horaConsultada(int horaIntrod) {
        horaDAO = new HoraDAO();
        String rutaFichero = "src//Ficheros//Hora.json";
        
        try{
            escritor = new BufferedWriter(new FileWriter(rutaFichero));
            
            escritor.write(String.valueOf(horaIntrod));
            
            escritor.close();
        } catch(IOException e){
            e.printStackTrace();
        }
        
        return horaDAO.read(rutaFichero);
    }

    public void eliminarTareaDeEstaHora(int codigoTarea) {
        tareaDAO = new TareaDAO();
        String fichero = "src//Ficheros//Tarea.json";

        // Escritura del fichero ´´Tarea.json``
        try{
            escritor = new BufferedWriter(new FileWriter(fichero));

            escritor.write(String.valueOf(codigoTarea));

            escritor.close();
        } catch(IOException e){
            e.printStackTrace();
        }

        // Llamada al método de la clase Tarea ´´delete``
        tareaDAO.delete(fichero);
    }

    public int obtenerCodigoTareaAleatorio(){
        tareaDAO = new TareaDAO();

        return tareaDAO.obtenerCodigoTareaAleatorio();
    }

    public void crearTarea(Tarea tareaIntrod){
        tareaDAO = new TareaDAO();
        gson = new Gson();
        String fichero = "src//Ficheros//Tarea.json";
        String tareaEnString = gson.toJson(tareaIntrod);

        try{
            escritor = new BufferedWriter(new FileWriter(fichero));

            escritor.write(tareaEnString);

            escritor.close();
        } catch(IOException e){
            e.printStackTrace();
        }

        tareaDAO.create(fichero);
    }

    public Tarea tareaConsultada(int horaOCodigoDeLaTarea, String tipoDeConsulta){
        Tarea tareaConsultada = null;
        tareaDAO = new TareaDAO();
        gson = new Gson();
        String rutaFichero = "";

        try{
            if(tipoDeConsulta.equalsIgnoreCase("hora")) {
                rutaFichero = "src//Ficheros//Hora.json";
            } else{
                rutaFichero = "src//Ficheros//Tarea.json";
            }

            escritor = new BufferedWriter(new FileWriter(rutaFichero));

            escritor.write(String.valueOf(horaOCodigoDeLaTarea));

            escritor.close();
        } catch(IOException e){
            e.printStackTrace();
        }

        if(tipoDeConsulta.equalsIgnoreCase("hora")){
//            boolean tieneTarea  = tareaDAO.consultarTareaPorSuCodigoHora(rutaFichero);;
//
//            if(tieneTarea) {
//
//            }
        } else{
            boolean existeEstaTarea = tareaDAO.read(rutaFichero);

            if(existeEstaTarea) {
                try {
                    lector = new BufferedReader(new FileReader(rutaFichero));

                    StringBuilder contenidoDelFichero = new StringBuilder();
                    String lineaDelFichero;
                    while ((lineaDelFichero = lector.readLine()) != null) {
                        contenidoDelFichero.append(lineaDelFichero);
                    }

                    tareaConsultada = gson.fromJson(String.valueOf(contenidoDelFichero), Tarea.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return tareaConsultada;
    }

}
