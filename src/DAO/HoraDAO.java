package DAO;

import ClasesElementales.Hora;
import com.google.gson.Gson;
/*import ClasesElementales.Tarea;*/

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CallToPrintStackTrace")
public class HoraDAO {
/// Atributo(s)
    private final DAOGeneral daoGeneral = new DAOGeneral();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    private BufferedReader lector;
    private BufferedWriter escritor;
    private Gson gson;

/// Métodos comunes
    public boolean read(String rutaFichero){
        boolean horaConsultadaCorrectamente = false;
        gson = new Gson();
        int codigoHora = 0;

        // Lectura del fichero ´´Hora.json``
        // e incorporación de su contenido en la variable ´´codigoHora``
        try{
            lector = new BufferedReader(new FileReader(rutaFichero));
            
            codigoHora = Integer.parseInt(lector.readLine());
            
            lector.close();
        } catch(IOException e){
            e.printStackTrace();
        }

        // Consulta a la base de datos de la hora con dicho ´´codigoHora``
        try{
            con = daoGeneral.connect();
            sql = "select * from Hora where codigoHora = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoHora);
            rs = ps.executeQuery();

            if(rs.next()){
                int codigoTarea = rs.getInt("codigoTarea");

                if(!rs.wasNull()) {
                    Hora horaConsultada = new Hora(codigoHora, codigoTarea);

                    try{
                        escritor = new BufferedWriter(new FileWriter(rutaFichero));

                        String horaEnString = gson.toJson(horaConsultada);

                        escritor.write(horaEnString);

                        escritor.close();

                        horaConsultadaCorrectamente = true;
                    } catch(IOException e){
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

        return horaConsultadaCorrectamente;
    }

    public void update(int codigoHora,int codigoNuevoTarea){
        try{
            con = daoGeneral.connect();
            sql = "update Hora set codigoTarea = ? where codigoHora = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoNuevoTarea);
            ps.setInt(2, codigoHora);
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

/// Método(s) específico(s)
    public List<Hora> obtenerHoras(){
        List<Hora> horas = new ArrayList<>();

        try{
            con = daoGeneral.connect();
            sql = "SELECT * FROM Hora";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                Hora hora;
                int codigoHora = rs.getInt("codigoHora");
                int codigoTarea = rs.getInt("codigoTarea");
                if(!rs.wasNull()) {
                    hora = new Hora(codigoHora, codigoTarea);
                } else {
                    hora = new Hora(codigoHora);
                }

                horas.add(hora);
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

        return horas;
    }
    
    public boolean estaHoraExiste(String rutaFichero){
        boolean estaHoraExiste = false;
        int codigoHora = 0;
        
        try{
            lector = new BufferedReader(new FileReader(rutaFichero));
            
            String linea = lector.readLine();
            
            try{
                codigoHora = Integer.parseInt(linea);
            } catch(NumberFormatException e){
                e.printStackTrace();
            }
            
            lector.close();
        } catch(IOException e){
            e.printStackTrace();
        }
        
        try{
            con = daoGeneral.connect();
            sql = "select * from Hora where codigoHora = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoHora);
            rs = ps.executeQuery();
            if(rs.next()){
                estaHoraExiste = true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        return estaHoraExiste;
    }

    public void eliminarSuTarea(int codigoHora) {
        try{
            con = daoGeneral.connect();
            sql = "update Hora set codigoTarea = ? where codigoHora = ?";
            ps = con.prepareStatement(sql);
            ps.setNull(1, java.sql.Types.INTEGER);
            ps.setInt(2, codigoHora);
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
