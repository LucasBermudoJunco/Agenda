package DAO;

import ClasesElementales.Tarea;

import java.sql.*;

public class TareaDAO {

/// Atributo(s)
    private DAOGeneral daoGeneral = new DAOGeneral();
    /*private HoraDAO horaDAO = new HoraDAO();*/
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

/// Métodos comunes
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

                tarea = new Tarea();
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return tarea;
    }

/// Método(s) específico(s)

}
