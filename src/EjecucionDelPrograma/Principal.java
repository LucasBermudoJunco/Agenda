package EjecucionDelPrograma;

import ClasesElementales.Hora;
import ClasesElementales.Semana;
import ClasesElementales.Tarea;
import DAO.HoraDAO;
import DAO.TareaDAO;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

    /// Scanner
        Scanner sc = new Scanner(System.in);

    /// Variables de la Base de Datos
        Semana semana = new Semana(1);
        /*HoraDAO horaDAO = new HoraDAO();*/
        /*TareaDAO tareaDAO = new TareaDAO();*/

    /// Variables del programa
        int opcion = 0, cantHoras = 0;

    /// Obtención de las horas de la base de datos
        semana.obtenerHorasDeLaBaseDeDatos();

    /// Menú de la agenda
        do {
            System.out.print("Elige una opción:" +
                    "\n\t1 para añadir una tarea." +
                    "\n\t2 para consultar la tarea de una hora concreta." +
                    "\n\t3 para consultar todas las tareas de todas las horas." +
                    "\n\t4 para consultar una tarea concreta." +
                    "\n\n\t0 para salir del programa." +
                    "\n\nOpción elegida:  ");
            opcion = sc.nextInt();

            // Añadir una tarea
            if (opcion == 1) {


            // Consultar la tarea de una hora concreta
            } else if (opcion == 2) {
                System.out.print("\nIntroduce la hora:  ");
                int horaAConocer = sc.nextInt();

                Hora hora = semana.obtenerHora(horaAConocer);
                if (hora != null) {
                    Integer codigoTarea = hora.getCodigoTarea();

                    if (codigoTarea != null) {
                        Tarea tarea = semana.obtenerTarea((int) codigoTarea);

                        System.out.println("\nLa hora nº " + horaAConocer + " tiene asignada la tarea de código "
                                + tarea.getCodigoTarea() + " y cuyo contenido es:\n\n"
                                + "´´" + tarea.getContenidoTarea() + "``");
                    } else {
                        System.out.println("\nLa hora nº " + horaAConocer + " no tiene ninguna tarea asignada.\n");
                    }
                } else{
                    System.out.println("\nLa hora introducida no se encuentra dentro de las horas de una semana "
                                + "(que van desde la hora 1 a la hora 168 ambas incluidas).\n");
                }
            // Consultar todas las tareas de todas las horas
            } else if (opcion == 3) {


            // Consultar una tarea concreta
            } else if (opcion == 4) {


            // Salir del programa
            } else if(opcion == 0) {
                System.out.println("\nPrograma finalizado.");
            } else{
                System.out.println("\nOpcion no valida. Vuelva a elegir.\n\n");
            }
        } while(opcion != 0);



    }

}
