package Controlador;

import ClasesElementales.Hora;
import DAO.HoraDAO;
import DAO.SemanaDAO;
import DAO.TareaDAO;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Controlador {

/// Atributo(s)
    private SemanaDAO semanaDAO;
    private HoraDAO horaDAO;
    private TareaDAO tareaDAO;
    BufferedReader lector;
    BufferedWriter escritor;
    Gson gson;
    
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

}
