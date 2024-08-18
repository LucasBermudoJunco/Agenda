package InterfazGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

@SuppressWarnings("CallToPrintStackTrace")
public class MenuPrincipal extends JFrame {
    
/// Atributo(s)
    TareaCreacion tareaCreacion;
    TareaConsulta tareaConsulta;
    
    
    
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
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    
/// Constructor
    public MenuPrincipal() {
    
    /// Sets
        setType(Type.POPUP);
        setResizable(true);
        setVisible(true);
        setBounds(100, 100, 1500, 800);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Agenda V2.1");
        setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/Imagenes/iconoAgenda.png")));
        /*setSize(800,600);*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    /// Barra Superior de la aplicación
        
        // Barra Superior en sí misma
        JMenuBar menuBar = new JMenuBar();
        menuBar.setForeground(Color.BLUE);
        menuBar.setBackground(Color.BLUE);
        menuBar.setMargin(new Insets(10, 10, 10, 0));
        setJMenuBar(menuBar);
        
        // Menú ´´Tareas`` dentro de la barra Superior
        JMenu menuTarea = new JMenu("Tareas     ");
        menuTarea.setIcon(new ImageIcon(Objects.requireNonNull(MenuPrincipal.class.getResource("/Imagenes/iconoTarea.png"))));
        menuTarea.setForeground(Color.WHITE);
        menuTarea.setSize(10,10);
        menuBar.add(menuTarea);
        
        // Menú ´´Añadir Tarea`` dentro del Menú ´´Tareas``
        JMenuItem menunAnyadirTarea =  new JMenuItem("Añadir Tarea");
        menuTarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                tareaCreacion = new TareaCreacion();
                
            }
        });
        menuTarea.add(menunAnyadirTarea);
        
        
        
        /*
        // Click con el ratón al botón de confirmar tarea
        btnClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(btnClick,"¡Tarea guardada con éxito!");
            }
        });
        
        // Presionado de la tecla ´´Enter`` cuando la celda de texto ´´celdaTarea`` tiene el foco
        celdaTarea.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"),"pressEnter");
        celdaTarea.getActionMap().put("pressEnter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnClick.doClick();
            }
        });
         */
        
        
    }
}