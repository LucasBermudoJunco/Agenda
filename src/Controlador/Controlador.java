package Controlador;

import ClasesElementales.Hora;
import ClasesElementales.Tarea;
import DAO.HoraDAO;
import DAO.SemanaDAO;
import DAO.TareaDAO;
import InterfazGrafica.MenuPrincipal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
    // Creación fuera del jar de los archivos que se necesitan para leer y escribir información
    public static void copiarArchivoDesdeJar(String nombreArchivoInterno, String rutaArchivoExterno){
        InputStream inputStream = MenuPrincipal.class.getResourceAsStream("/" + nombreArchivoInterno);
        if(inputStream == null){
            System.err.println("\nNo se encontró el archivo " + nombreArchivoInterno + "\n");
        }

        File archivoExterno = new File(rutaArchivoExterno);
        archivoExterno.getParentFile().mkdirs();

        try (FileOutputStream outputStream = new FileOutputStream(archivoExterno)){
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

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
        Hora horaConsultada = null;
        gson = new Gson();
        String rutaFichero = "src//Ficheros//Hora.json";

        // Escritura de la hora introducida en el fichero
        try{
            escritor = new BufferedWriter(new FileWriter(rutaFichero));

            escritor.write(String.valueOf(horaIntrod));

            escritor.close();
        } catch(IOException e){
            e.printStackTrace();
        }

        // Llamada al méto.do ´´read`` de la clase ´´HoraDAO``
        // y lectura del fichero ´´Hora.json`` en caso de que el método
        // devuelva true al haber resultado positiva la consulta
        // de dicha hora en la base de datos
        if(horaDAO.read(rutaFichero)) {
            // Lectura del fichero que ya contiene el objeto hora
            try {
                lector = new BufferedReader(new FileReader(rutaFichero));

                StringBuilder contenidoFichero = new StringBuilder();
                String lineaFichero;
                while ((lineaFichero = lector.readLine()) != null) {
                    contenidoFichero.append(lineaFichero);
                }

                horaConsultada = gson.fromJson(String.valueOf(contenidoFichero), Hora.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            horaConsultada = new Hora(horaIntrod);
        }

        return horaConsultada;
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
        String rutaFichero = "src//Ficheros//Tarea.json";
        boolean tieneTareaOExisteEstaTarea;

        // Escritura en ´´Tarea.json`` de la hora o del codigoTarea
        // (según el tipo de consulta que sea)
        try{
            escritor = new BufferedWriter(new FileWriter(rutaFichero));

            escritor.write(String.valueOf(horaOCodigoDeLaTarea));

            escritor.close();
        } catch(IOException e){
            e.printStackTrace();
        }

        // Llamada al méto.do de la clase ´´TareaDAO`` correspondiente
        // (´´consultarTareaPorSuHora`` o ´´read``) en función de si la consulta
        // es por la hora o por el codigoTarea
        if(tipoDeConsulta.equalsIgnoreCase("hora")){
            tieneTareaOExisteEstaTarea  = tareaDAO.consultarTareaPorSuHora(rutaFichero);;
        } else{
            tieneTareaOExisteEstaTarea = tareaDAO.read(rutaFichero);
        }

        // Lectura de ´´Tarea.json`` e incorporación de su contenido al objeto tareaConsultada
        // en caso de que la consulta a la base de datos sí haya obtenido resultado
        if(tieneTareaOExisteEstaTarea) {
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

        return tareaConsultada;
    }

    public List<Tarea> todasLasTareas(){
        List<Tarea> todasLasTareas = new ArrayList<Tarea>();
        String rutaFicheroTarea = "src//Ficheros//Tarea.json";
        tareaDAO = new TareaDAO();
        gson = new Gson();

        boolean listaTieneTareas = tareaDAO.obtenerTodasLasTareas(rutaFicheroTarea);

        if(listaTieneTareas) {
            try {
                lector = new BufferedReader(new FileReader(rutaFicheroTarea));

                StringBuilder contenidoFichero = new StringBuilder();
                String lineaFichero;
                while ((lineaFichero = lector.readLine()) != null) {
                    contenidoFichero.append(lineaFichero);
                }

                // Adaptación del tipo de lista (List<Tarea>) al Gson
                // para que sepa de qué tipo de lista se trata
                Type tipoDeLista = new TypeToken<List<Tarea>>() {
                }.getType();
                todasLasTareas = gson.fromJson(String.valueOf(contenidoFichero), tipoDeLista);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    lector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return todasLasTareas;
    }

}
