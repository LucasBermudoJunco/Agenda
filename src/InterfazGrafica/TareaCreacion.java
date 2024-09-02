package InterfazGrafica;

import Controlador.Controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.InputMismatchException;
import javax.swing.border.Border;
import javax.swing.*;

public class TareaCreacion extends JInternalFrame {
    
    /// Atributo(s)
    private static final long serialVersionUID = 1L;
    private final JDesktopPane desktopPane;
    //private final JPanel panelTareaCreacion;
    private final JLabel lblTareaCreacionCabecera;
    private final JLabel lblTareaCreacionHoraDeLaTarea;
    private final JLabel lblTareaCreacionContenidoDeLaTarea;
    private final JTextField textFieldTareaCreacionHoraDeLaTarea;
    private final JTextArea textAreaTareaCreacionContenidoDeLaTarea;
    private final JButton btnTareaCreacionCrearTarea;
    private final JScrollPane scrollPaneTareaCreacion;
    private Controlador controlador;
    
    /// Constructor
    public TareaCreacion(JDesktopPane desktopPane) {
        
        setIconifiable(true);
        getContentPane().setBackground(new Color(245, 245, 245));
        setClosable(true);
        setTitle("Creación de Tarea");
        setBounds(100, 100, 450, 423);
        setForeground(Color.WHITE);
        getContentPane().setLayout(null);
        
        this.desktopPane = desktopPane;
        
        lblTareaCreacionCabecera = new JLabel("Introduce los datos de la tarea");
        lblTareaCreacionCabecera.setBounds(121, 28, 192, 14);
        getContentPane().add(lblTareaCreacionCabecera);
        
        lblTareaCreacionHoraDeLaTarea = new JLabel("Hora:");
        lblTareaCreacionHoraDeLaTarea.setBounds(29, 72, 46, 14);
        getContentPane().add(lblTareaCreacionHoraDeLaTarea);
        
        lblTareaCreacionContenidoDeLaTarea = new JLabel("Contenido:");
        lblTareaCreacionContenidoDeLaTarea.setBounds(29, 101, 72, 14);
        getContentPane().add(lblTareaCreacionContenidoDeLaTarea);
        
        textFieldTareaCreacionHoraDeLaTarea = new JTextField();
        textFieldTareaCreacionHoraDeLaTarea.setBounds(111, 69, 86, 20);
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
        textAreaTareaCreacionContenidoDeLaTarea.setBounds(111, 97, 296, 100);
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
        btnTareaCreacionCrearTarea.setBounds(111, 208, 106, 23);
        getContentPane().add(btnTareaCreacionCrearTarea);
        
        scrollPaneTareaCreacion = new JScrollPane();
        scrollPaneTareaCreacion.setBounds(111, 254, 296, 116);
        getContentPane().add(scrollPaneTareaCreacion);
    }
    
    /// Método(s)
    public void crearTarea() {
        
        controlador = new Controlador();
        
        String horaDeLaTarea = textFieldTareaCreacionHoraDeLaTarea.getText();
        String contenidoDeLaTarea = textAreaTareaCreacionContenidoDeLaTarea.getText();
        
        if(horaDeLaTarea.isBlank() || contenidoDeLaTarea.isBlank()){
            JOptionPane.showMessageDialog(this, "La hora y el contenido de la tarea " +
                    "tienen que estar rellenados");
        } else{
            boolean horaIntroducidaCorrectamente = true;
            
            try{
                if(Integer.parseInt(horaDeLaTarea) < 0){
                    horaIntroducidaCorrectamente = false;
                }
            } catch(NumberFormatException e){
                horaIntroducidaCorrectamente = false;
            }
            
            if(!horaIntroducidaCorrectamente) {
                JOptionPane.showMessageDialog(this, "La hora introducida tiene que ser un número entero positivo (sin decimales)");
            }
        }
        
    }
}
