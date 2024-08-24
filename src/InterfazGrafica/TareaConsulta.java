package InterfazGrafica;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class TareaConsulta extends JInternalFrame {

/// Atributo(s)
    private JLabel lblTareaConsultaCabecera;
    private JLabel lblTareaConsultaBuscarTareaPorHora;
    private JTextField textFieldTareaConsultaBuscarTareaPorSuCodigo;
    private JTextField textFieldTareaConsultaBuscarTareaPorHora;
    
    
    /// Constructor
    public TareaConsulta() {
        setForeground(Color.WHITE);
        setBorder(new TitledBorder(null,"Consulta",TitledBorder.LEADING,TitledBorder.TOP, null,null));
    }


}
