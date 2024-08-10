package EjecucionDelPrograma;

import ClasesElementales.Semana;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Semana semana = new Semana();
        int opcion = 0, cantHoras = 0;

        do{
            System.out.print("Elige una opción:" +
                    "\n\t1 para añadir una tarea." +
                    "\n\t2 para consultar la tarea de una hora concreta." +
                    "\n\t3 para consultar todas las tareas de todas las horas." +
                    "\n\n\t0 para salir del programa." +
                    "\n\nOpción elegida:  ");
            opcion = sc.nextInt();

            if(opcion == 1) {
                do{
                    System.out.print("\nElige cuántas horas quieres que dure la tarea:  ");
                } while(cantHoras < 1);
            } else if(opcion == 2){

            } else if(opcion == 3){

            } else {
                System.out.println("\nPrograma finalizado.");
            }
        } while(opcion != 0);

        Connection con;
        Statement sentencia;
        String sql = "insert into tarea values(1,2)";

        String url = "jdbc:mysql://localhost:3306/agenda";

        try{
            con = DriverManager.getConnection(url, "root", "password");
            sentencia = con.prepareStatement(sql);
            sentencia.executeUpdate(sql);


        } catch(SQLException excep){

        }

    }
}
