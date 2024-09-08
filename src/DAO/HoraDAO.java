package DAO;

import ClasesElementales.Hora;
/*import ClasesElementales.Tarea;*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
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

/// Métodos comunes
    public Hora read(String rutaFichero){
        Hora hora = null;
        
        int codigoHora = 0;
        
        try{
            lector = new BufferedReader(new FileReader(rutaFichero));
            
            codigoHora = Integer.parseInt(lector.readLine());
            
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
                int codigoTarea = rs.getInt("codigoTarea");

                if(!rs.wasNull()) {
                    hora = new Hora(codigoHora, codigoTarea);
                } else {
                    hora = new Hora(codigoHora);
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

        return hora;
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
