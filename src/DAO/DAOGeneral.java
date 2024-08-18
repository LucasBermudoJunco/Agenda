package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("CallToPrintStackTrace")
public class DAOGeneral {

/// Atributos
    private Connection con = null;
    @SuppressWarnings("FieldCanBeLocal")
    private final String url = "jdbc:mysql://localhost:3306/agenda";
    @SuppressWarnings("FieldCanBeLocal")
    private final String user = "root";
    @SuppressWarnings("FieldCanBeLocal")
    private final String password = "password";

/// Métodos
    public Connection connect() {

        try{
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e){
            System.out.println("Conexión al SGBD fallida");
            e.printStackTrace();
        }

        return con;
    }

}
