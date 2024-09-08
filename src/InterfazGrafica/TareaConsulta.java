package InterfazGrafica;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import ClasesElementales.Tarea;
import Controlador.Controlador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TareaConsulta extends JInternalFrame {
    
/// Atributo(s)
    private static final long serialVersionUID = 1L;
    private final JDesktopPane desktopPane;
    private final JLabel lblTareaConsultaCabecera;
    private final JLabel lblTareaConsultaBuscarTareaPorHora;
    private final JLabel lblTareaConsultaBuscarTareaSuCodigo;
    private final JTextField textFieldTareaConsultaSegunSuHora;
    private final JTextField textFieldTareaConsultaSegunSuCodigo;
    private final JButton btnTareaConsultaSegunSuHora;
    private final JButton btnTareaConsultaSegunSuCodigo;
    private final JButton btnTareaConsultaTodasLasTareas;
    private final JScrollPane scrollPaneTareaConsulta;
    private Controlador controlador;
    
    
    
/// Constructor
    public TareaConsulta(JDesktopPane desktopPane) {
        
        /// Sets
        setIconifiable(true);
        getContentPane().setBackground(new Color(245, 245, 245));
        setClosable(true);
        //setFrameIcon(new ImageIcon(TareaConsulta.class.getResource("/Images/cliente16px.png")));
        setTitle("Consulta de Tareas");
        setBounds(100, 100, 450, 423);
        getContentPane().setLayout(null);
        setForeground(Color.WHITE);
        //setBorder(new TitledBorder(null,"Consulta",TitledBorder.LEADING,TitledBorder.TOP, null,null));
        getContentPane().setLayout(null);
        
        this.desktopPane = desktopPane;
        
        lblTareaConsultaCabecera = new JLabel("Consulta la tarea según su hora o según su código");
        lblTareaConsultaCabecera.setBounds(71, 22, 297, 14);
        getContentPane().add(lblTareaConsultaCabecera);
        
        lblTareaConsultaBuscarTareaPorHora = new JLabel("Según su hora:");
        lblTareaConsultaBuscarTareaPorHora.setBounds(58, 61, 102, 14);
        getContentPane().add(lblTareaConsultaBuscarTareaPorHora);
        
        lblTareaConsultaBuscarTareaSuCodigo = new JLabel("Según su código:");
        lblTareaConsultaBuscarTareaSuCodigo.setBounds(58, 86, 102, 14);
        getContentPane().add(lblTareaConsultaBuscarTareaSuCodigo);
        
        textFieldTareaConsultaSegunSuHora = new JTextField();
        textFieldTareaConsultaSegunSuHora.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    consultarTarea("hora");
                }
            }
        });
        textFieldTareaConsultaSegunSuHora.setBounds(170, 58, 86, 20);
        getContentPane().add(textFieldTareaConsultaSegunSuHora);
        textFieldTareaConsultaSegunSuHora.setColumns(10);
        
        textFieldTareaConsultaSegunSuCodigo = new JTextField();
        textFieldTareaConsultaSegunSuCodigo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    consultarTarea("codigoTarea");
                }
            }
        });
        textFieldTareaConsultaSegunSuCodigo.setBounds(170, 83, 86, 20);
        getContentPane().add(textFieldTareaConsultaSegunSuCodigo);
        textFieldTareaConsultaSegunSuCodigo.setColumns(10);
        
        btnTareaConsultaSegunSuHora = new JButton("Consultar");
        btnTareaConsultaSegunSuHora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarTarea("hora");
            }
        });
        btnTareaConsultaSegunSuHora.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    consultarTarea("hora");
                }
            }
        });
        btnTareaConsultaSegunSuHora.setBounds(279, 57, 89, 23);
        getContentPane().add(btnTareaConsultaSegunSuHora);
        
        btnTareaConsultaSegunSuCodigo = new JButton("Consultar");
        btnTareaConsultaSegunSuCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarTarea("codigoTarea");
            }
        });
        btnTareaConsultaSegunSuCodigo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    consultarTarea("codigoTarea");
                }
            }
        });
        btnTareaConsultaSegunSuCodigo.setBounds(279, 82, 89, 23);
        getContentPane().add(btnTareaConsultaSegunSuCodigo);
        
        btnTareaConsultaTodasLasTareas = new JButton("Consultar todas las tareas");
        btnTareaConsultaTodasLasTareas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarTarea("todasLasTareas");
            }
        });
        btnTareaConsultaTodasLasTareas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    consultarTarea("todasLasTareas");
                }
            }
        });
        btnTareaConsultaTodasLasTareas.setBounds(114, 120, 189, 23);
        getContentPane().add(btnTareaConsultaTodasLasTareas);
        
        scrollPaneTareaConsulta = new JScrollPane();
        scrollPaneTareaConsulta.setBounds(58, 159, 310, 199);
        getContentPane().add(scrollPaneTareaConsulta);
    }

/// Método(s) específico(s)
    public void consultarTarea(String tipoDeConsulta) {
        
        controlador = new Controlador();
        
        boolean horaOCodigoIntroducidosCorrectamente = true;
        boolean horaOCodigoRellenados = true;
        
        if(tipoDeConsulta.equalsIgnoreCase("hora")) {
            String horaDeLaTarea = textFieldTareaConsultaSegunSuHora.getText();
            
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
            
            if(horaOCodigoIntroducidosCorrectamente) {
                Tarea tareaConsultada = controlador.tareaConsultada(Integer.valueOf(horaDeLaTarea),"hora");

                if(tareaConsultada != null) {
                    // Mensaje para mostrar por ahora hasta tener desarrollada esta parte
                    // (la de mostrar el contenido de la tarea en el JScrollPane)
                    JOptionPane.showMessageDialog(this,"Sí hay una tarea asignada a la hora " + horaDeLaTarea);
                } else{
                    JOptionPane.showMessageDialog(this,"No hay ninguna tarea asignada a la hora " + horaDeLaTarea);
                }
            }
        } else if(tipoDeConsulta.equalsIgnoreCase("codigoTarea")){
            String codigoDeLaTarea = textFieldTareaConsultaSegunSuCodigo.getText();
            
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
            
            if(horaOCodigoIntroducidosCorrectamente) {
                Tarea tareaConsultada = controlador.tareaConsultada(Integer.valueOf(codigoDeLaTarea),"codigoTarea");

                if(tareaConsultada != null) {
                    // Mensaje para mostrar por ahora hasta tener desarrollada esta parte
                    // (la de mostrar el contenido de la tarea en el JScrollPane)
                    JOptionPane.showMessageDialog(this,"Sí hay una tarea asignada a la hora " + codigoDeLaTarea);
                } else{
                    JOptionPane.showMessageDialog(this,"No hay ninguna tarea asignada a la hora " + codigoDeLaTarea);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "botón ´´Consultar todas las tareas`` pulsado correctamente");
        }
        
        if(!horaOCodigoRellenados) {
            JOptionPane.showMessageDialog(this, "La celda no puede estar vacía");
        } else if(!horaOCodigoIntroducidosCorrectamente) {
            JOptionPane.showMessageDialog(this, "La hora y el código introducidos tienen que ser números enteros positivos (sin decimales)");
        }
        
    }
}
