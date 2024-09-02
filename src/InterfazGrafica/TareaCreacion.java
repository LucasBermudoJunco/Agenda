package InterfazGrafica;

import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.*;

public class TareaCreacion extends JInternalFrame {
    
    /// Atributo(s)
    private static final long serialVersionUID = 1L;
    private JDesktopPane desktopPane;
    private JPanel panelTareaCreacion;
    private JLabel lblTareaCreacionCabecera;
    private JLabel lblTareaCreacionHoraDeLaTarea;
    private JLabel lblTareaCreacionContenidoDeLaTarea;
    private JTextField textFieldTareaCreacionHoraDeLaTarea;
    private JTextArea textAreaTareaCreacionContenidoDeLaTarea;
    
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
        lblTareaCreacionHoraDeLaTarea.setBounds(69, 72, 46, 14);
        getContentPane().add(lblTareaCreacionHoraDeLaTarea);
        
        lblTareaCreacionContenidoDeLaTarea = new JLabel("Contenido:");
        lblTareaCreacionContenidoDeLaTarea.setBounds(69, 97, 70, 14);
        getContentPane().add(lblTareaCreacionContenidoDeLaTarea);
        
        textFieldTareaCreacionHoraDeLaTarea = new JTextField();
        textFieldTareaCreacionHoraDeLaTarea.setBounds(148, 69, 86, 20);
        getContentPane().add(textFieldTareaCreacionHoraDeLaTarea);
        textFieldTareaCreacionHoraDeLaTarea.setColumns(10);
        
        textAreaTareaCreacionContenidoDeLaTarea = new JTextArea();
        textAreaTareaCreacionContenidoDeLaTarea.setBounds(149, 97, 164, 100);
        Border bordeDelTextAreaTareaCreacionContenidoDeLaTarea = BorderFactory.createLineBorder(Color.BLACK,1);
        textAreaTareaCreacionContenidoDeLaTarea.setBorder(bordeDelTextAreaTareaCreacionContenidoDeLaTarea);
        getContentPane().add(textAreaTareaCreacionContenidoDeLaTarea);
        
        JButton btnTareaCreacionCrearTarea = new JButton("Crear Tarea");
        btnTareaCreacionCrearTarea.setBounds(148, 208, 106, 23);
        getContentPane().add(btnTareaCreacionCrearTarea);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(84, 253, 277, 116);
        getContentPane().add(scrollPane);
    }
}

