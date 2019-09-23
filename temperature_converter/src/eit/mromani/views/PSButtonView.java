package eit.mromani.views;

import eit.mromani.PSButton;
import eit.mromani.model.PSButtonModel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PSButtonView {
    String label;
    PSButton controller;

    public PSButtonView(String label, PSButton controller) {
        this.label = label;
        this.controller = controller;
        setupListeners();
    }

    private void setupListeners() {
        controller.addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                controller.press();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                controller.release();
            }

            @Override public void mouseEntered(MouseEvent e) { }
            @Override public void mouseExited(MouseEvent e) { }
            @Override public void mouseClicked(MouseEvent e) { }
        });
    }


    public void paint(Graphics graphics, PSButton psButton) {
        PSButtonModel model = psButton.getModel();

        if(model.isPressed()) {
            graphics.setColor(Color.red);
        } else {
            graphics.setColor(Color.white);
        }

        graphics.fillRect(psButton.getX(), psButton.getY(), psButton.getWidth(), psButton.getHeight());
        graphics.setColor(Color.black);
        graphics.drawRect(psButton.getX(), psButton.getY(), psButton.getWidth(), psButton.getHeight());
    }

    public Dimension getPreferredSize() {
        return new Dimension(100, 32); // FIXME: really ought to base on size of label
    }

}
