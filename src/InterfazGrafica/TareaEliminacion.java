package InterfazGrafica;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Controlador.Controlador;

public class TareaEliminacion extends JInternalFrame{
    
    /// Atributo(s)
    private static final long serialVersionUID = 1L;
    private final JDesktopPane desktopPane;
    private final JLabel lblTareaEliminacionCabecera;
    private final JLabel lblTareaEliminacionBuscarTareaPorHora;
    private final JLabel lblTareaEliminacionBuscarTareaSuCodigo;
    private final JTextField textFieldTareaEliminacionSegunSuHora;
    private final JTextField textFieldTareaEliminacionSegunSuCodigo;
    private final JButton btnTareaEliminacionSegunSuhora;
    private final JButton btnTareaEliminacionSegunSuCodigo;
    private Controlador controlador;
    
    /// Constructor
    public TareaEliminacion(JDesktopPane desktopPane) {
        
        /// Sets
        setIconifiable(true);
        getContentPane().setBackground(new Color(245, 245, 245));
        setClosable(true);
        //setFrameIcon(new ImageIcon(TareaConsulta.class.getResource("/Images/cliente16px.png")));
        setTitle("Eliminación de Tareas");
        setBounds(100, 100, 450, 192);
        getContentPane().setLayout(null);
        setForeground(Color.WHITE);
        //setBorder(new TitledBorder(null,"Consulta",TitledBorder.LEADING,TitledBorder.TOP, null,null));
        getContentPane().setLayout(null);
        
        this.desktopPane = desktopPane;
        
        lblTareaEliminacionCabecera = new JLabel("Elimina la tarea según su hora o según su código");
        lblTareaEliminacionCabecera.setBounds(71, 22, 297, 14);
        getContentPane().add(lblTareaEliminacionCabecera);
        
        lblTareaEliminacionBuscarTareaPorHora = new JLabel("Según su hora:");
        lblTareaEliminacionBuscarTareaPorHora.setBounds(58, 61, 102, 14);
        getContentPane().add(lblTareaEliminacionBuscarTareaPorHora);
        
        lblTareaEliminacionBuscarTareaSuCodigo = new JLabel("Según su código:");
        lblTareaEliminacionBuscarTareaSuCodigo.setBounds(58, 86, 102, 14);
        getContentPane().add(lblTareaEliminacionBuscarTareaSuCodigo);
        
        textFieldTareaEliminacionSegunSuHora = new JTextField();
        textFieldTareaEliminacionSegunSuHora.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    eliminarTarea("hora");
                }
            }
        });
        textFieldTareaEliminacionSegunSuHora.setBounds(170, 58, 86, 20);
        getContentPane().add(textFieldTareaEliminacionSegunSuHora);
        textFieldTareaEliminacionSegunSuHora.setColumns(10);
        
        textFieldTareaEliminacionSegunSuCodigo = new JTextField();
        textFieldTareaEliminacionSegunSuCodigo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    eliminarTarea("codigo");
                }
            }
        });
        textFieldTareaEliminacionSegunSuCodigo.setBounds(170, 83, 86, 20);
        getContentPane().add(textFieldTareaEliminacionSegunSuCodigo);
        textFieldTareaEliminacionSegunSuCodigo.setColumns(10);
        
        btnTareaEliminacionSegunSuhora = new JButton("Eliminar");
        btnTareaEliminacionSegunSuhora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarTarea("hora");
            }
        });
        btnTareaEliminacionSegunSuhora.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    eliminarTarea("hora");
                }
            }
        });
        btnTareaEliminacionSegunSuhora.setBounds(279, 57, 89, 23);
        getContentPane().add(btnTareaEliminacionSegunSuhora);
        
        btnTareaEliminacionSegunSuCodigo = new JButton("Eliminar");
        btnTareaEliminacionSegunSuCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarTarea("codigo");
            }
        });
        btnTareaEliminacionSegunSuCodigo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    eliminarTarea("codigo");
                }
            }
        });
        btnTareaEliminacionSegunSuCodigo.setBounds(279, 82, 89, 23);
        getContentPane().add(btnTareaEliminacionSegunSuCodigo);
    }
    
    /// Método(s) específico(s)
    public void eliminarTarea(String horaOCodigo) {
        
        controlador = new Controlador();
        
        boolean horaOCodigoIntroducidosCorrectamente = true;
        boolean horaOCodigoRellenados = true;
        
        if(horaOCodigo.equalsIgnoreCase("hora")) {
            
            String horaDeLaTarea = textFieldTareaEliminacionSegunSuHora.getText();
            
            try {
                if(horaDeLaTarea.isBlank()) {
                    horaOCodigoRellenados = false;
                    horaOCodigoIntroducidosCorrectamente = false;
                } else if(Integer.parseInt(horaDeLaTarea) <= 0) {
                    horaOCodigoIntroducidosCorrectamente = false;
                }
            } catch(NumberFormatException e) {
                horaOCodigoIntroducidosCorrectamente = false;
            }
            
        } else {
            
            String codigoDeLaTarea = textFieldTareaEliminacionSegunSuCodigo.getText();
            
            try {
                if(codigoDeLaTarea.isBlank()) {
                    horaOCodigoRellenados = false;
                    horaOCodigoIntroducidosCorrectamente = false;
                } else if(Integer.parseInt(codigoDeLaTarea) <= 0) {
                    horaOCodigoIntroducidosCorrectamente = false;
                }
            } catch(NumberFormatException e) {
                horaOCodigoIntroducidosCorrectamente = false;
            }
            
        }
        
        if(!horaOCodigoRellenados) {
            JOptionPane.showMessageDialog(this, "La celda no puede estar vacía");
        } else if(!horaOCodigoIntroducidosCorrectamente) {
            JOptionPane.showMessageDialog(this, "La hora y el código introducidos tienen que ser números enteros positivos (sin decimales)");
        }
        
    }
    
}
