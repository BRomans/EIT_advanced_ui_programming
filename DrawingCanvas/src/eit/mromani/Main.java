package eit.mromani;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public static void main(String ...args) {
        new Main();
    }

    public Main() {
        super("Canvas");
        this.setPreferredSize(new Dimension(1500, 1500));
        Canvas canvas = new Canvas();
        canvas.setVisible(true);
        this.getContentPane().add(canvas, BorderLayout.CENTER);
        this.pack();
    }
}
