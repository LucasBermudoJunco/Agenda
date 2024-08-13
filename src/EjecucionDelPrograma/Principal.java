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
        int opcion = 0;

    /// Obtención de las horas de la base de datos
        semana.obtenerHorasDeLaBaseDeDatos();

    /// Menú de la agenda
        do {
            System.out.print("Elige una opción:" +
                    "\n\t1 para añadir una tarea." +
                    "\n\t2 para consultar la tarea de una hora concreta." +
                    "\n\t3 para consultar una tarea concreta." +
                    "\n\t4 para consultar todas las tareas." +
                    "\n\t5 para eliminar una tarea." +
                    "\n\n\t0 para salir del programa." +
                    "\n\nOpción elegida:  ");
            opcion = sc.nextInt();

            // Añadir una tarea
            if (opcion == 1) {
                System.out.print("\nIntroduce la hora de la tarea:  ");
                int horaDeLaTarea = sc.nextInt();
                sc.nextLine();
                Hora estaHora = semana.obtenerHora(horaDeLaTarea);

                if(estaHora != null){
                    if(estaHora.getCodigoTarea() == null){
                        System.out.print("\nIntroduce la nueva tarea:  ");
                        String nuevaTarea = sc.nextLine();

                        if(!nuevaTarea.isBlank()){
                            Tarea tarea = new Tarea(nuevaTarea,horaDeLaTarea);

                            semana.crearTareaYActualizarHora(tarea);

                            System.out.println();
                        } else{
                            System.out.println("\nLa tarea no puede estar en blanco.\n");
                        }
                    } else{
                        System.out.print("\nEsta hora ya tiene una tarea asignada. ¿Quieres sustituirla por una nueva tarea?  ");
                        String sustituirONo = sc.nextLine();

                        if(sustituirONo.equalsIgnoreCase("sí") || sustituirONo.equalsIgnoreCase("si")){
                            System.out.print("\nIntroduce la nueva tarea:  ");
                            String nuevaTarea = sc.nextLine();

                            if(!nuevaTarea.isBlank()){
                                Tarea tarea = new Tarea(nuevaTarea,horaDeLaTarea);

                                semana.cambiarTareaYActualizarHora(estaHora,tarea);
                            } else{
                                System.out.println("\nLa tarea no puede estar en blanco.");
                            }
                        } else{
                            System.out.println("\nLa tarea de esta hora no ha sido sustituida.\n");
                        }
                    }
                } else{
                    System.out.println("\nLa hora introducida no se encuentra dentro de las horas de una semana "
                            + "(que van desde la hora 1 a la hora 168 ambas incluidas).\n");
                }

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
                                + tarea.getCodigoTarea() + " y cuyo contenido es:\n"
                                + "´´" + tarea.getContenidoTarea() + "``\n\n");
                    } else {
                        System.out.println("\nLa hora nº " + horaAConocer + " no tiene ninguna tarea asignada.\n");
                    }
                } else{
                    System.out.println("\nLa hora introducida no se encuentra dentro de las horas de una semana "
                                + "(que van desde la hora 1 a la hora 168 ambas incluidas).\n");
                }

            // Consultar una tarea concreta
            } else if (opcion == 3) {
                System.out.print("\nIntroduce el código de la tarea que desees consultar:  ");
                int codigoTarea = sc.nextInt();

                Tarea tarea = semana.obtenerTarea(codigoTarea);

                if(tarea != null){
                    System.out.println("\nInformación sobre la tarea:\n" + tarea + "\n\n");
                } else{
                    System.out.println("\nNo existe ninguna tarea con ese código.\n");
                }

            // Consultar todas las tareas
            } else if (opcion == 4) {
                semana.obtenerTareasDeLaBaseDeDatos();
                List<Tarea> tareas = semana.getTareas();

                if(!tareas.isEmpty()){
                    semana.ordenarTareasPorHoras();

                    System.out.println("\nLas tareas que existen en este momento son:\n");

                    Iterator<Tarea> iterator = tareas.iterator();
                    while(iterator.hasNext()){
                        Tarea tarea = iterator.next();

                        System.out.println(tarea + "\n");
                    }
                } else{
                    System.out.println("\nNo hay ninguna tarea en este momento.\n");
                }

            // Eliminar una tarea
            } else if(opcion == 5){
                System.out.print("\nIntroduce el código de la tarea que desees eliminar:  ");
                int codigoTarea = sc.nextInt();

                if(semana.obtenerTarea(codigoTarea) != null){
                    semana.eliminarTarea(codigoTarea);

                    System.out.println("\n");
                } else{
                    System.out.println("\nNo existe ninguna tarea con ese código.\n");
                }

            // Salir del programa
            } else if(opcion == 0) {
                System.out.println("\nPrograma finalizado.");
            } else{
                System.out.println("\nOpcion no valida. Vuelva a elegir.\n\n");
            }
        } while(opcion != 0);



    }

}
