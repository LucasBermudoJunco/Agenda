package InterfazGrafica;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class TareaConsulta extends JInternalFrame {
    
    /// Atributo(s)
    private static final long serialVersionUID = 1L;
    private JDesktopPane desktopPane;
    private JPanel panelTareaConsulta;
    private JLabel lblTareaConsultaCabecera;
    private JLabel lblTareaConsultaBuscarTareaPorHora;
    private JLabel lblTareaConsultaBuscarTareaSuCodigo;
    private JTextField textFieldTareaConsultaSegunSuHora;
    private JTextField textFieldTareaConsultaSegunSuCodigo;
    private JButton btnTareaConsultaSegunSuhora;
    private JButton btnTareaConsultaSegunSuCodigo;
    private JScrollPane scrollPaneTareaConsulta;
    
    
    
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
        textFieldTareaConsultaSegunSuHora.setBounds(170, 58, 86, 20);
        getContentPane().add(textFieldTareaConsultaSegunSuHora);
        textFieldTareaConsultaSegunSuHora.setColumns(10);
        
        textFieldTareaConsultaSegunSuCodigo = new JTextField();
        textFieldTareaConsultaSegunSuCodigo.setBounds(170, 83, 86, 20);
        getContentPane().add(textFieldTareaConsultaSegunSuCodigo);
        textFieldTareaConsultaSegunSuCodigo.setColumns(10);
        
        btnTareaConsultaSegunSuhora = new JButton("Consultar");
        btnTareaConsultaSegunSuhora.setBounds(279, 57, 89, 23);
        getContentPane().add(btnTareaConsultaSegunSuhora);
        
        btnTareaConsultaSegunSuCodigo = new JButton("Consultar");
        btnTareaConsultaSegunSuCodigo.setBounds(279, 82, 89, 23);
        getContentPane().add(btnTareaConsultaSegunSuCodigo);
        
        scrollPaneTareaConsulta = new JScrollPane();
        scrollPaneTareaConsulta.setBounds(58, 145, 310, 199);
        getContentPane().add(scrollPaneTareaConsulta);
    }
}