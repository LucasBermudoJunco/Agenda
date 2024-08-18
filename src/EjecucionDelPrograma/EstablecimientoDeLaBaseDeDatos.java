package EjecucionDelPrograma;

import DAO.DAOGeneral;

import java.sql.Connection;
/*import java.sql.DriverManager;*/
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SuppressWarnings("CallToPrintStackTrace")
public class EstablecimientoDeLaBaseDeDatos {

/// Atributo(s)
    private final static DAOGeneral daoGeneral = new DAOGeneral();
    @SuppressWarnings("FieldCanBeLocal")
    private static Connection con;
    @SuppressWarnings("FieldCanBeLocal")
    private static PreparedStatement ps;
    @SuppressWarnings("FieldCanBeLocal")
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
