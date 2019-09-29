package eit.mromani.views;

import eit.mromani.controllers.PhotoComponent;
import eit.mromani.model.PhotoComponentModel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author BRomans
 *
 * This is the view for PhotoComponent
 */
public class PhotoComponentView {

    private static int DEFAULT_HEIGHT = 600;
    private static int DEFAULT_WIDTH = 600;
    private static int DEFAULT_PREFERRED_HEIGHT = 800;
    private static int DEFAULT_PREFERRED_WIDTH = 800;


    private PhotoComponent _controller;


    public PhotoComponentView(PhotoComponent controller) {
        this._controller = controller;
        setupListeners();
    }

        private void setupListeners() {
        _controller.addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override public void mouseEntered(MouseEvent e) { }
            @Override public void mouseExited(MouseEvent e) { }
            public void mouseClicked(MouseEvent event)
            {
                if (event.getClickCount() == 2) {
                    System.out.println("double clicked");
                    _controller.flip();
                }
            }
        });
    }

    public void paint(Graphics graphics, PhotoComponent photoComponent) {
        PhotoComponentModel model = photoComponent.getModel();

        if(!model.isFlipped()) {
            //graphics.setColor(Color.red);
            graphics.drawImage(_controller.getImage(),photoComponent.getX(), photoComponent.getY(), null);
        } else {
            graphics.setColor(Color.white);
            graphics.fillRect(photoComponent.getX(), photoComponent.getY(), photoComponent.getWidth(), photoComponent.getHeight());
        }
        graphics.setColor(Color.black);
        graphics.drawRect(photoComponent.getX(), photoComponent.getY(), photoComponent.getWidth(), photoComponent.getHeight());
    }

    public Dimension getSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_PREFERRED_WIDTH, DEFAULT_PREFERRED_HEIGHT);
    }
}
