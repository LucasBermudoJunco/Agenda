package InterfazGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

@SuppressWarnings("CallToPrintStackTrace")
public class MenuPrincipal extends JFrame {
    
    /// Atributo(s)
    private JDesktopPane desktopPane;
    private JPanel contentPane;
    @SuppressWarnings("FieldCanBeLocal")
    private final FondoPanel fondoPanel = new FondoPanel("/Imagenes/imagenAgenda.png");
    private TareaCreacion tareaCreacion;
    private TareaConsulta tareaConsulta;
    private TareaEliminacion tareaEliminacion;
    private CalendarioConsulta calendarioConsulta;
    private CalendarioBuscarTareaPorHora calendarioBuscarTareaPorHora;
    
    /*
    private JPanel panelPrincipal = new JPanel();
    private JLabel tarea = new JLabel();
    private JTextField celdaTarea = new JTextField();
    private JButton btnClick = new JButton("Pulsar");
     */
    
    /// Ejecución de la clase
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @SuppressWarnings("unused")
            public void run() {
                try{
                    MenuPrincipal frame = new MenuPrincipal();
                    frame.setVisible(true);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    
    /// Constructor
    public MenuPrincipal() {
        
        /// Sets
        
        // Sets del Comportamiento
        setType(Type.POPUP);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        setBounds(100, 100, 1500, 800);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Agenda V2.1");
        setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/Imagenes/iconoAgenda.png")));
        //setContentPane(fondoPanel);
        
        desktopPane = new JDesktopPane();
        desktopPane.setBorder(null);
        desktopPane.setBackground(new Color(255,255,255));
        getContentPane().add(desktopPane, BorderLayout.CENTER);
        
        /// Barra Superior de la aplicación
        
        // Barra Superior en sí misma
        JMenuBar menuBar = new JMenuBar();
        /*menuBar.setForeground(Color.BLUE);*/
        menuBar.setBackground(new Color(73, 130, 231));
        menuBar.setMargin(new Insets(20, 20,20, 0));
        setJMenuBar(menuBar);
        
        // Menú ´´Tareas`` dentro de la barra Superior
        JMenu menuTarea = new JMenu("Tareas     ");
        menuTarea.setIcon(new ImageIcon(Objects.requireNonNull(MenuPrincipal.class.getResource("/Imagenes/iconoTarea.png"))));
        menuTarea.setForeground(Color.WHITE);
        menuTarea.setSize(10,10);
        menuBar.add(menuTarea);
        
        // Menú ´´Añadir Tarea`` dentro del Menú ´´Tareas``
        JMenuItem menuAnyadirTarea =  new JMenuItem("Añadir Tarea");
        menuAnyadirTarea.setBackground(new Color(73, 165, 231, 255));
        menuAnyadirTarea.setForeground(Color.WHITE);
        menuAnyadirTarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(tareaCreacion == null || tareaCreacion.isClosed()) {
                    tareaCreacion = new TareaCreacion(desktopPane);
                    desktopPane.add(tareaCreacion);
                    tareaCreacion.setLocation(100,100);
                    tareaCreacion.setVisible(true);
                } else {
                    tareaCreacion.moveToFront();
                    // Recolocación de la ventana en su posición original por si estaba fuera de vista y,
                    // debido a ello, el usuario pensaba que estaba cerrada
                    tareaCreacion.setLocation(100,100);
                }
                
                
            }
        });
        menuTarea.add(menuAnyadirTarea);
        
        // Menú ´´Consultar Tarea`` dentro del Menú ´´Tareas``
        JMenuItem menuConsultarTarea =  new JMenuItem("Consultar Tarea");
        menuConsultarTarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(tareaConsulta == null || tareaConsulta.isClosed()) {
                    tareaConsulta = new TareaConsulta(desktopPane);
                    desktopPane.add(tareaConsulta);
                    tareaConsulta.setLocation(180,180);
                    tareaConsulta.setVisible(true);
                } else {
                    tareaConsulta.moveToFront();
                    // Recolocación de la ventana en su posición original por si estaba fuera de vista y,
                    // debido a ello, el usuario pensaba que estaba cerrada
                    tareaConsulta.setLocation(180,180);
                }
                
                try {
                    tareaConsulta.setSelected(true);
                } catch(java.beans.PropertyVetoException ex) {
                    ex.printStackTrace();
                }
            }
        });
        menuConsultarTarea.setBackground(new Color(73, 165, 231, 255));
        menuConsultarTarea.setForeground(Color.WHITE);
        menuTarea.add(menuConsultarTarea);
        
        // Menú ´´Eliminar Tarea`` dentro del Menú ´´Tareas``
        JMenuItem menuEliminarTarea =  new JMenuItem("Eliminar Tarea");
        menuEliminarTarea.setBackground(new Color(73, 165, 231, 255));
        menuEliminarTarea.setForeground(Color.WHITE);
        menuEliminarTarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(tareaEliminacion == null || tareaEliminacion.isClosed()) {
                    tareaEliminacion = new TareaEliminacion(desktopPane);
                    desktopPane.add(tareaEliminacion);
                    tareaEliminacion.setLocation(260,260);
                    tareaEliminacion.setVisible(true);
                } else {
                    tareaEliminacion.moveToFront();
                    // Recolocación de la ventana en su posición original por si estaba fuera de vista y,
                    // debido a ello, el usuario pensaba que estaba cerrada
                    tareaEliminacion.setLocation(260,260);
                }
                
            }
        });
        menuTarea.add(menuEliminarTarea);
        
        // Menú ´´Calendario`` dentro de la Barra Superior
        JMenu menuCalendario = new JMenu("Calendario    ");
        menuCalendario.setIcon(new ImageIcon(Objects.requireNonNull(MenuPrincipal.class.getResource("/Imagenes/iconoCalendario.png"))));
        menuCalendario.setForeground(Color.WHITE);
        menuCalendario.setSize(10,10);
        menuBar.add(menuCalendario);
        
        // Menú ´´Mostrar Calendario`` dentro del Menú ´´Calendario``
        JMenuItem menuMostrarCalendario =  new JMenuItem("Mostrar Calendario");
        menuMostrarCalendario.setBackground(new Color(73, 165, 231, 255));
        menuMostrarCalendario.setForeground(Color.WHITE);
        menuMostrarCalendario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                calendarioConsulta = new CalendarioConsulta();
                
            }
        });
        menuCalendario.add(menuMostrarCalendario);
        
        // Menú ´´Buscar Tarea por Calendario`` dentro del Menú ´´Calendario``
        JMenuItem menuBuscarTareaPorHora =  new JMenuItem("Buscar Tarea por Hora");
        menuBuscarTareaPorHora.setBackground(new Color(73, 165, 231, 255));
        menuBuscarTareaPorHora.setForeground(Color.WHITE);
        menuBuscarTareaPorHora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                calendarioBuscarTareaPorHora = new CalendarioBuscarTareaPorHora();
                
            }
        });
        menuCalendario.add(menuBuscarTareaPorHora);
        
        
    }
}
