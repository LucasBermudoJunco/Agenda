package DAO;

import ClasesElementales.Tarea;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TareaDAO {

/// Atributo(s)
    private DAOGeneral daoGeneral = new DAOGeneral();
    private HoraDAO horaDAO = new HoraDAO();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

/// Métodos comunes
    public void create(Tarea tarea) {
        try{
            con = daoGeneral.connect();
            sql = "insert into tarea values(?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1,tarea.getCodigoTarea());
            ps.setInt(2,tarea.getCodigoHora());
            ps.setString(3, tarea.getContenidoTarea());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Tarea read(int codigoTarea){
        Tarea tarea = null;

        try {
            con = daoGeneral.connect();
            sql = "SELECT * FROM Tarea WHERE codigoTarea = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoTarea);
            rs = ps.executeQuery();

            if(rs.next()){
                int codigoHora = rs.getInt("codigoHora");

                String contenidoTarea = rs.getString("contenidoTarea");

                tarea = new Tarea(codigoTarea, codigoHora, contenidoTarea);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return tarea;
    }

    public void delete(int codigoTareaAEliminar) {
        Tarea tareaAEliminar = read(codigoTareaAEliminar);

        horaDAO.eliminarSuTarea(tareaAEliminar.getCodigoHora());

        try{
            con = daoGeneral.connect();
            sql = "DELETE FROM tarea WHERE codigoTarea = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codigoTareaAEliminar);
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

/// Método(s) específico(s)
    public List<Tarea> obtenerTareas(){
        List<Tarea> tareas = new ArrayList<Tarea>();

        try{
            con = daoGeneral.connect();
            sql = "SELECT * FROM Tarea";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                Tarea tarea;
                int codigoTarea = rs.getInt("codigoTarea");
                int codigoHora = rs.getInt("codigoHora");
                String contenidoTarea = rs.getString("contenidoTarea");
                tarea = new Tarea(codigoTarea, codigoHora, contenidoTarea);

                tareas.add(tarea);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return tareas;
    }

    public boolean estaLibreEsteCodigoTarea(int codigoIntrodTarea) {
        boolean estaLibreEsteCodigoTarea = true;

        try {
            con = daoGeneral.connect();
            sql = "SELECT * FROM Tarea WHERE codigoTarea = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoIntrodTarea);
            rs = ps.executeQuery();

            if(rs.next() && rs.getInt("codigoTarea") == codigoIntrodTarea){
                estaLibreEsteCodigoTarea = false;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return estaLibreEsteCodigoTarea;
    }

}
