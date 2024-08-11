package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOGeneral {

/// Atributos
    private Connection con = null;
    private String url = "jdbc:mysql://localhost:3306/agenda";
    private String user = "root";
    private String password = "password";

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
