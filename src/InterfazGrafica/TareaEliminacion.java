package InterfazGrafica;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class TareaEliminacion extends JInternalFrame{
    
    /// Atributo(s)
    private static final long serialVersionUID = 1L;
    private JDesktopPane desktopPane;
    private JPanel panelTareaEliminacion;
    private JLabel lblTareaEliminacionCabecera;
    private JLabel lblTareaEliminacionBuscarTareaPorHora;
    private JLabel lblTareaEliminacionBuscarTareaSuCodigo;
    private JTextField textFieldTareaEliminacionSegunSuHora;
    private JTextField textFieldTareaEliminacionSegunSuCodigo;
    private JButton btnTareaEliminacionSegunSuhora;
    private JButton btnTareaEliminacionSegunSuCodigo;
    private JScrollPane scrollPaneTareaEliminacion;
    
    /// Constructor
    public TareaEliminacion(JDesktopPane desktopPane) {
        
        /// Sets
        setIconifiable(true);
        getContentPane().setBackground(new Color(245, 245, 245));
        setClosable(true);
        //setFrameIcon(new ImageIcon(TareaConsulta.class.getResource("/Images/cliente16px.png")));
        setTitle("Eliminación de Tareas");
        setBounds(100, 100, 450, 423);
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
        textFieldTareaEliminacionSegunSuHora.setBounds(170, 58, 86, 20);
        getContentPane().add(textFieldTareaEliminacionSegunSuHora);
        textFieldTareaEliminacionSegunSuHora.setColumns(10);
        
        textFieldTareaEliminacionSegunSuCodigo = new JTextField();
        textFieldTareaEliminacionSegunSuCodigo.setBounds(170, 83, 86, 20);
        getContentPane().add(textFieldTareaEliminacionSegunSuCodigo);
        textFieldTareaEliminacionSegunSuCodigo.setColumns(10);
        
        btnTareaEliminacionSegunSuhora = new JButton("Eliminar");
        btnTareaEliminacionSegunSuhora.setBounds(279, 57, 89, 23);
        getContentPane().add(btnTareaEliminacionSegunSuhora);
        
        btnTareaEliminacionSegunSuCodigo = new JButton("Eliminar");
        btnTareaEliminacionSegunSuCodigo.setBounds(279, 82, 89, 23);
        getContentPane().add(btnTareaEliminacionSegunSuCodigo);
        
        scrollPaneTareaEliminacion = new JScrollPane();
        scrollPaneTareaEliminacion.setBounds(58, 145, 310, 199);
        getContentPane().add(scrollPaneTareaEliminacion);
    }
    
}
