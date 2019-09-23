package eit.mromani.views;

import eit.mromani.controllers.PhotoComponent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author BRomans
 *
 * This is the view for PhotoComponent
 */
public class PhotoComponentView {

    private PhotoComponent controller;

    public PhotoComponentView(PhotoComponent controller) {
        this.controller = controller;
    }

        private void setupListeners() {
        controller.addMouseListener(new MouseListener() {
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
                    controller.flip();
                }
            }
        });
    }

}
