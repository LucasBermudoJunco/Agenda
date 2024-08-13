package EjecucionDelPrograma;

import DAO.DAOGeneral;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EstablecimientoDeLaBaseDeDatos {

/// Atributo(s)
    private static DAOGeneral daoGeneral = new DAOGeneral();
    private static Connection con;
    private static PreparedStatement ps;
    private static String sql;

/// Ejecución de la clase
    public static void main(String[] args) {
        crearLasHorasVacias();
    }

/// Método(s)
    public static void crearLasHorasVacias() {

        sql = "insert into hora(codigoHora) values(?)";

        try{
            con = daoGeneral.connect();
            ps = con.prepareStatement(sql);
            for(int i=1; i<=168; i++) {
                ps.setInt(1, i);

                ps.executeUpdate();
            }

        } catch(SQLException excep){
            excep.printStackTrace();
        }

    }

}
