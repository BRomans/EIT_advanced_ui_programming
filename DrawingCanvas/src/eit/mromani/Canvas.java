package eit.mromani;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Canvas extends JPanel {

    public void paintComponent(Graphics graphicsContext) {
        //view.paint(graphics, this);
        Graphics2D graphics2D = (Graphics2D) graphicsContext;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(Color.red);
        graphics2D.fillRect(0,0, getWidth(), getHeight());
        graphics2D.setColor(Color.blue);
        graphics2D.fillRect(0, 0, getWidth()-1, getHeight()-1);
    }
}
