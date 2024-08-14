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
                        String nuevaTarea = "";

                        System.out.print("\nIntroduce la nueva tarea:  ");

                        do {
                            nuevaTarea = sc.nextLine();

                            if (!nuevaTarea.isBlank()) {
                                Tarea tarea = new Tarea(nuevaTarea, horaDeLaTarea);

                                semana.crearTareaYActualizarHora(tarea);

                                System.out.println("\nLa tarea ha sido creada para la hora " + horaDeLaTarea + " con el código de tarea nº " + tarea.getCodigoTarea() + "\n");
                            } else {
                                System.out.print("\nLa tarea no puede estar en blanco. Vuelve a introducirla:  \n");
                            }
                        } while(nuevaTarea.isBlank());
                    } else{
                        String sustituirONo = "";

                        System.out.print("\nEsta hora ya tiene una tarea asignada. ¿Quieres sustituirla por una nueva tarea?  ");

                        do {
                            sustituirONo = sc.nextLine();

                            if (sustituirONo.equalsIgnoreCase("sí") || sustituirONo.equalsIgnoreCase("si")) {
                                String nuevaTarea = "";

                                System.out.print("\nIntroduce la nueva tarea:  ");

                                do {
                                    nuevaTarea = sc.nextLine();

                                    if (!nuevaTarea.isBlank()) {
                                        Tarea tarea = new Tarea(nuevaTarea, horaDeLaTarea);

                                        semana.cambiarTareaYActualizarHora(estaHora, tarea);

                                        System.out.println("\nLa tarea ha sido actualizada para la hora " + horaDeLaTarea + " con el código de tarea nº " + tarea.getCodigoTarea() + "\n");
                                    } else {
                                        System.out.println("\nLa tarea no puede estar en blanco.");
                                    }
                                } while(nuevaTarea.isBlank());
                            } else if(sustituirONo.equalsIgnoreCase("no")){
                                System.out.println("\nLa tarea de esta hora no ha sido sustituida.\n");
                            } else{
                                System.out.print("\nLa respuesta tiene que ser sí o no. Por favor, introduce la respuesta de nuevo. "
                                            + "Esta hora ya tiene una tarea asignada. ¿Quieres sustituirla por una nueva tarea?  ");
                            }
                        } while(!sustituirONo.equalsIgnoreCase("sí") && !sustituirONo.equalsIgnoreCase("si")
                            && !sustituirONo.equalsIgnoreCase("no"));
                    }
                } else{
                    System.out.println("\nLa hora introducida no se encuentra dentro de las horas de una semana "
                            + "(que van desde la hora 1 a la hora 168 ambas incluidas).\n");
                }

            // Consultar la tarea de una hora concreta
            } else if (opcion == 2) {
                semana.obtenerTareasDeLaBaseDeDatos();
                List<Tarea> tareas = semana.getTareas();

                if(!tareas.isEmpty()) {
                    System.out.print("\nIntroduce la hora:  ");
                    int horaAConocer = sc.nextInt();

                    Hora hora = semana.obtenerHora(horaAConocer);
                    if (hora != null) {
                        Integer codigoTarea = hora.getCodigoTarea();

                        if (codigoTarea != null) {
                            Tarea tarea = semana.obtenerTarea((int) codigoTarea);

                            System.out.println("\nLa hora nº " + horaAConocer + " tiene asignada la tarea de código "
                                    + tarea.getCodigoTarea() + " cuyo contenido es:\n"
                                    + "´´" + tarea.getContenidoTarea() + "``\n\n");
                        } else {
                            System.out.println("\nLa hora nº " + horaAConocer + " no tiene ninguna tarea asignada.\n");
                        }
                    } else {
                        System.out.println("\nLa hora introducida no se encuentra dentro de las horas de una semana "
                                + "(que van desde la hora 1 hasta la hora 168 ambas incluidas).\n");
                    }
                } else{
                    System.out.println("\nNo hay ninguna tarea en este momento.\n");
                }

            // Consultar una tarea concreta
            } else if (opcion == 3) {
                semana.obtenerTareasDeLaBaseDeDatos();
                List<Tarea> tareas = semana.getTareas();

                if(!tareas.isEmpty()) {
                    System.out.print("\nIntroduce el código de la tarea que desees consultar:  ");
                    int codigoTarea = sc.nextInt();

                    Tarea tarea = semana.obtenerTarea(codigoTarea);

                    if (tarea != null) {
                        System.out.println("\nInformación sobre la tarea:\n" + tarea + "\n\n");
                    } else {
                        System.out.println("\nNo existe ninguna tarea con ese código.\n");
                    }
                } else{
                    System.out.println("\nNo existe ninguna tarea en este momento.\n");
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
                sc.nextLine();

                semana.obtenerTareasDeLaBaseDeDatos();
                List<Tarea> tareas = semana.getTareas();

                if(!tareas.isEmpty()) {
                    String horaOCodigoTarea = "";

                    do {
                        System.out.print("\nPara eliminar dicha tarea, ¿quieres introducir la hora de dicha tarea o el código de la tarea? "
                                + "Responde ´´hora`` o ´´codigotarea``:  ");
                        horaOCodigoTarea = sc.nextLine();

                        if (horaOCodigoTarea.equalsIgnoreCase("hora")) {
                            System.out.print("\nIntroduce la hora de la cual quieras eliminar su tarea:  ");
                            int codigoHora = sc.nextInt();

                            if (semana.obtenerHora(codigoHora) != null) {
                                Integer codigoTarea = semana.obtenerHora(codigoHora).getCodigoTarea();

                                if (codigoTarea != null) {
                                    semana.eliminarLaTareaDeEstaHora(codigoHora);
                                    semana.eliminarTarea(codigoTarea);

                                    System.out.println("\nLa tarea de la hora " + codigoHora + " ha sido eliminada.\n");
                                } else {
                                    System.out.println("\nLa hora " + codigoHora + " no tenía ninguna tarea asignada.\n");
                                }
                            } else {
                                System.out.println("\nLa hora introducida no se encuentra dentro de las horas de una semana "
                                        + "(que van desde la hora 1 hasta la hora 168 ambas incluidas).\n");
                            }
                        } else if (horaOCodigoTarea.equalsIgnoreCase("codigo") || horaOCodigoTarea.equalsIgnoreCase("codigoTarea")
                                || horaOCodigoTarea.equalsIgnoreCase("codigo de la tarea")) {
                            System.out.print("\nIntroduce el código de la tarea que desees eliminar:  ");
                            int codigoTarea = sc.nextInt();

                            if (semana.obtenerTarea(codigoTarea) != null) {
                                semana.eliminarTarea(codigoTarea);

                                System.out.println("\nLa tarea de código " + codigoTarea + " ha sido eliminada.\n");
                            } else {
                                System.out.println("\nNo existe ninguna tarea con ese código.\n");
                            }
                        } else {
                            System.out.println("\nRespuesta no válida. La respuesta tiene que ser ´´hora`` o ´´codigotarea``");
                        }
                    } while (!horaOCodigoTarea.equalsIgnoreCase("hora") && !horaOCodigoTarea.equalsIgnoreCase("codigo")
                            && !horaOCodigoTarea.equalsIgnoreCase("codigotarea") && !horaOCodigoTarea.equalsIgnoreCase("codigo de la tarea"));
                } else{
                    System.out.println("\nNo existe ninguna tarea en este momento.\n");
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
