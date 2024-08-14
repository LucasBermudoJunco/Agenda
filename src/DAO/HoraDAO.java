package DAO;

import ClasesElementales.Hora;
import ClasesElementales.Tarea;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoraDAO {
/// Atributo(s)
    private DAOGeneral daoGeneral = new DAOGeneral();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

/// Métodos comunes
    public Hora read(int codigoHora){
        Hora hora = null;

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
        List<Hora> horas = new ArrayList<Hora>();

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
