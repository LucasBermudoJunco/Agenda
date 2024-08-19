package InterfazGrafica;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FondoPanel extends JPanel{

/// Atributo(s)
    private Image image;
    
/// Constructor(es)
    public FondoPanel(String rutaImagen) {
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource(rutaImagen))).getImage();
    }
    
/// Mét0do(s) predeterminado(s) de la clase
    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        
        int paddingX = (int)(getWidth() * 0.1);
        int paddingY = (int)(getHeight() * 0.1);
        
        int imgWidth = panelWidth - 2 * paddingX;
        int imgHeight = panelHeight - 2 * paddingY;
        
        // Establecimiento de la imagen en t0do el espacio disponible con el padding señalado
        graphics.drawImage(image, paddingX, paddingY, imgWidth, imgHeight, this);
    }
    
}
