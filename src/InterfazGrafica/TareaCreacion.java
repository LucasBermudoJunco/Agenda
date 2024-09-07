package InterfazGrafica;

import ClasesElementales.Hora;
import ClasesElementales.Tarea;
import Controlador.Controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serial;
import javax.swing.border.Border;
import javax.swing.*;

public class TareaCreacion extends JInternalFrame {
    
    /// Atributo(s)
    @Serial
    private static final long serialVersionUID = 1L;
    private final JDesktopPane desktopPane;
    private final JLabel lblTareaCreacionCabecera;
    private final JLabel lblTareaCreacionHoraDeLaTarea;
    private final JLabel lblTareaCreacionContenidoDeLaTarea;
    private final JTextField textFieldTareaCreacionHoraDeLaTarea;
    private final JTextArea textAreaTareaCreacionContenidoDeLaTarea;
    private final JButton btnTareaCreacionCrearTarea;
    private Controlador controlador;
    
    /// Constructor
    public TareaCreacion(JDesktopPane desktopPane) {
        
        setIconifiable(true);
        getContentPane().setBackground(new Color(245, 245, 245));
        setClosable(true);
        setTitle("Creación de Tareas");
        setBounds(100, 100, 450, 316);
        setForeground(Color.WHITE);
        getContentPane().setLayout(null);
        
        this.desktopPane = desktopPane;
        
        lblTareaCreacionCabecera = new JLabel("Introduce los datos de la tarea nueva");
        lblTareaCreacionCabecera.setBounds(121, 28, 212, 14);
        getContentPane().add(lblTareaCreacionCabecera);
        
        lblTareaCreacionHoraDeLaTarea = new JLabel("Hora:");
        lblTareaCreacionHoraDeLaTarea.setBounds(55, 52, 46, 14);
        getContentPane().add(lblTareaCreacionHoraDeLaTarea);
        
        lblTareaCreacionContenidoDeLaTarea = new JLabel("Contenido:");
        lblTareaCreacionContenidoDeLaTarea.setBounds(55, 101, 72, 14);
        getContentPane().add(lblTareaCreacionContenidoDeLaTarea);
        
        textFieldTareaCreacionHoraDeLaTarea = new JTextField();
        textFieldTareaCreacionHoraDeLaTarea.setBounds(55, 70, 86, 20);
        getContentPane().add(textFieldTareaCreacionHoraDeLaTarea);
        textFieldTareaCreacionHoraDeLaTarea.setColumns(10);
        
        textAreaTareaCreacionContenidoDeLaTarea = new JTextArea();
        textAreaTareaCreacionContenidoDeLaTarea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_TAB) {
                    btnTareaCreacionCrearTarea.requestFocus();
                    e.consume();
                }
            }
        });
        textAreaTareaCreacionContenidoDeLaTarea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_TAB && e.isShiftDown()) {
                    textFieldTareaCreacionHoraDeLaTarea.requestFocus();
                }
            }
        });
        textAreaTareaCreacionContenidoDeLaTarea.setBounds(55, 119, 352, 100);
        Border bordeDelTextAreaTareaCreacionContenidoDeLaTarea = BorderFactory.createLineBorder(Color.BLACK,1);
        textAreaTareaCreacionContenidoDeLaTarea.setBorder(bordeDelTextAreaTareaCreacionContenidoDeLaTarea);
        getContentPane().add(textAreaTareaCreacionContenidoDeLaTarea);
        
        btnTareaCreacionCrearTarea = new JButton("Crear Tarea");
        btnTareaCreacionCrearTarea.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                crearTarea();
            }
        });
        btnTareaCreacionCrearTarea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    crearTarea();
                } else if(e.getKeyCode() == KeyEvent.VK_TAB && e.isShiftDown()) {
                    textFieldTareaCreacionHoraDeLaTarea.requestFocus();
                }
            }
        });
        btnTareaCreacionCrearTarea.setBounds(55, 232, 106, 23);
        getContentPane().add(btnTareaCreacionCrearTarea);
    }
    
    /// Método(s) específico(s)
    public void crearTarea() {
        
        controlador = new Controlador();
        
        String horaDeLaTarea = textFieldTareaCreacionHoraDeLaTarea.getText();
        String contenidoDeLaTarea = textAreaTareaCreacionContenidoDeLaTarea.getText();
        
        if(!horaDeLaTarea.isBlank() && !contenidoDeLaTarea.isBlank()){
            boolean horaIntroducidaCorrectamente = true;
            
            try{
                if(Integer.parseInt(horaDeLaTarea) <= 0){
                    horaIntroducidaCorrectamente = false;
                }
            } catch(NumberFormatException e){
                horaIntroducidaCorrectamente = false;
            }
            
            if(horaIntroducidaCorrectamente) {
                if(controlador.horaIntrodValida(Integer.parseInt(horaDeLaTarea))){
                    Hora horaConsultada = controlador.horaConsultada(Integer.parseInt(horaDeLaTarea));

                    boolean introducirNuevaTarea = false;
                    boolean sustituirTarea = false;

                    if(horaConsultada.getCodigoTarea() == null){
                        introducirNuevaTarea = true;

                        System.out.println("Hora sin tarea todavía");
                    } else{
                        String mensaje = "La hora " + horaDeLaTarea + " ya contiene la tarea ´´" +
                                "--OBTENER CONTENIDO DE LA TAREA--" +
                                "``\n¿Quieres sustituirla por la tarea nueva que has introducido?";
                        String titulo = "La Tarea ya existe";
                        String[] opciones = {"Mantener tarea antigua","Sustituir"};
                        
                        int opcionElegida = JOptionPane.showOptionDialog(this,
                                mensaje,
                                titulo,
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                opciones,
                                opciones[0]);

                        if (opcionElegida == 0) {
                            JOptionPane.showMessageDialog(this, "La tarea de la hora " +
                                    horaDeLaTarea + " no ha sido modificada");
                        } else{
                            introducirNuevaTarea = true;
                            sustituirTarea = true;
                        }
                    }

                    if(introducirNuevaTarea){
                        // Eliminación de la tarea que había en esa hora en caso de que la hubiera
                        if(sustituirTarea){
                            controlador.eliminarTareaDeEstaHora(horaConsultada.getCodigoTarea());
                        }

                        // Creación de la nueva tarea en esa hora
                        int codigoNuevoTarea = controlador.obtenerCodigoTareaAleatorio();

                        controlador.crearTarea(new Tarea(codigoNuevoTarea,Integer.parseInt(horaDeLaTarea),contenidoDeLaTarea));

                        JOptionPane.showMessageDialog(this,"La tarea se ha creado correctamente con el código " + codigoNuevoTarea);
                    }
                } else{
                    JOptionPane.showMessageDialog(this,"La hora " + horaDeLaTarea + " no existe en nuestro calendario.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "La hora introducida tiene que ser un número entero positivo (sin decimales)");
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "La hora y el contenido de la tarea " +
                    "tienen que estar rellenados");
        }
        
    }
}
