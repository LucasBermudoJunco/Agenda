package DAO;

import ClasesElementales.Tarea;
import com.google.gson.Gson;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("CallToPrintStackTrace")
public class TareaDAO implements DAOInterfazDeJavaNoGrafica{

/// Atributo(s)
    private final DAOGeneral daoGeneral = new DAOGeneral();
    private final HoraDAO horaDAO = new HoraDAO();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    private BufferedReader lector;
    private BufferedWriter escritor;
    private Gson gson;

/// Métodos comunes
    public boolean create(String rutaFichero) {
        boolean tareaCreadaCorrectamente = false;
        Tarea tareaNueva;
        gson = new Gson();
        String contenidoDelFichero = "";

        // Lectura de la tarea del fichero JSON
        try{
            lector = new BufferedReader(new FileReader(rutaFichero));

            String lineaDelFichero;
            while((lineaDelFichero = lector.readLine()) != null){
                contenidoDelFichero += lineaDelFichero;
            }
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            try {
                lector.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        tareaNueva = gson.fromJson(contenidoDelFichero, Tarea.class);

        // Inserción de la tarea nueva en la base de datos
        try{
            con = daoGeneral.connect();
            sql = "insert into tarea values(?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1,tareaNueva.getCodigoTarea());
            ps.setInt(2,tareaNueva.getCodigoHora());
            ps.setString(3, tareaNueva.getContenidoTarea());
            ps.executeUpdate();

            tareaCreadaCorrectamente = true;
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tareaCreadaCorrectamente;
    }

    public boolean read(String rutaFichero){
        boolean tareaConsultadaCorrectamente = false;
        gson = new Gson();
        int codigoTarea = 0;

        // Lectura del fichero ´´Tarea.json`` y extracción del ´´códigoTarea``
        try{
            lector = new BufferedReader(new FileReader(rutaFichero));

            codigoTarea = Integer.parseInt(lector.readLine());

            lector.close();
        } catch(IOException e){
            e.printStackTrace();
        }

        try {
            con = daoGeneral.connect();
            sql = "SELECT * FROM Tarea WHERE codigoTarea = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoTarea);
            rs = ps.executeQuery();

            if(rs.next()){
                int codigoHora = rs.getInt("codigoHora");

                String contenidoTarea = rs.getString("contenidoTarea");

                Tarea tareaConsultada = new Tarea(codigoTarea, codigoHora, contenidoTarea);

                try{
                    escritor = new BufferedWriter(new FileWriter(rutaFichero));

                    String tareaEnString = gson.toJson(tareaConsultada);

                    escritor.write(tareaEnString);

                    escritor.close();

                    tareaConsultadaCorrectamente = true;
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

        } catch(SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tareaConsultadaCorrectamente;
    }

    public boolean update(String rutaFichero){
        boolean tareaActualizadaCorrectamente = false;



        return tareaActualizadaCorrectamente;
    }

    public boolean delete(String rutaFichero) {
        boolean eliminadoCorrectamente = false;
        int codigoTareaAEliminar = 0;

        // Lectura del fichero ´´Tarea.json`` y extracción del ´´códigoTarea``
        try{
            lector = new BufferedReader(new FileReader(rutaFichero));

            codigoTareaAEliminar = Integer.parseInt(lector.readLine());

            lector.close();
        } catch(IOException e){
            e.printStackTrace();
        }

//        // Extracción de la tarea con dicho ´´códigoTarea``
//        // para conocer su ´´codigoHora`` y poder eliminar la tarea de dicha hora
//        Tarea tareaAEliminar = read(fichero);
//
//        // Escritura del fichero ´´Hora.json`` para eliminar la tarea de dicha hora
//        try{
//            escritor = new BufferedWriter(new FileWriter(fichero));
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//
//        horaDAO.eliminarSuTarea(tareaAEliminar.getCodigoHora());

        try{
            con = daoGeneral.connect();
            sql = "DELETE FROM tarea WHERE codigoTarea = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codigoTareaAEliminar);
            ps.executeUpdate();

            eliminadoCorrectamente = true;
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return eliminadoCorrectamente;
    }

/// Método(s) específico(s)
    public boolean consultarTareaPorSuHora(String rutaFichero){
        boolean tieneTarea = false;
        int codigoHora = 0;
        gson = new Gson();

        // Lectura del fichero ´´Tarea.json`` que contiene el ´´codigoHora``
        try{
            lector = new BufferedReader(new FileReader(rutaFichero));

            codigoHora = Integer.parseInt(lector.readLine());

            lector.close();
        } catch(IOException e){
            e.printStackTrace();
        }

        // Consulta en la base de datos de la tarea con codigoHora = ´´codigoHora``
        // y escritura en ´´Tarea.json`` en caso de que la consulta sí haya obtenido resultado
        try{
            con = daoGeneral.connect();
            sql = "SELECT * FROM Tarea WHERE codigoHora = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoHora);
            rs = ps.executeQuery();
            if(rs.next()){
                tieneTarea = true;

                Tarea tareaConsultada = new Tarea(rs.getInt("codigoTarea"), codigoHora, rs.getString("contenidoTarea"));

                // Escritura en ´´Tarea.json`` de la tarea consultada
                try{
                    escritor = new BufferedWriter(new FileWriter(rutaFichero));

                    String tareaEnString = gson.toJson(tareaConsultada);

                    escritor.write(tareaEnString);

                    escritor.close();
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return tieneTarea;
    }

    public boolean obtenerTodasLasTareas(String rutaFichero){
        boolean listaConsultadaCorrectamente = false;

        try{
            con = daoGeneral.connect();
            sql = "SELECT * FROM Tarea";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if(rs.next()) {
                List<Tarea> tareas = new ArrayList<>();

                while (rs.next()) {
                    int codigoTarea = rs.getInt("codigoTarea");
                    int codigoHora = rs.getInt("codigoHora");
                    String contenidoTarea = rs.getString("contenidoTarea");
                    Tarea tareaNuevaLeida = new Tarea(codigoTarea, codigoHora, contenidoTarea);

                    tareas.add(tareaNuevaLeida);
                }

                listaConsultadaCorrectamente = true;

                try{
                    escritor = new BufferedWriter(new FileWriter(rutaFichero));

                    String tareaEnString = gson.toJson(tareas);

                    escritor.write(tareaEnString);
                } catch (IOException e){
                    e.printStackTrace();
                } finally {
                    try {
                        escritor.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listaConsultadaCorrectamente;
    }

    public int obtenerCodigoTareaAleatorio() {

        boolean estaLibreEsteCodigoTarea;

        int codigoAleatorioTarea = 0;
        Random random = new Random();

        do {
            estaLibreEsteCodigoTarea = true;

            codigoAleatorioTarea = random.nextInt(99999) + 1;

            try {
                con = daoGeneral.connect();
                sql = "SELECT * FROM Tarea WHERE codigoTarea = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, codigoAleatorioTarea);
                rs = ps.executeQuery();

                if (rs.next() && rs.getInt("codigoTarea") == codigoAleatorioTarea) {
                    estaLibreEsteCodigoTarea = false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } while(!estaLibreEsteCodigoTarea);

        return codigoAleatorioTarea;
    }

}
