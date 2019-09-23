package eit.mromani.controllers;

import eit.mromani.model.PhotoComponentModel;
import eit.mromani.views.PhotoComponentView;

import javax.swing.*;

/**
 *
 * @author BRomans
 *
 * This is the controller for PhotoComponent
 */
public class PhotoComponent extends JComponent {


    private static int DEFAULT_HEIGHT = 50;
    private static int DEFAULT_WIDTH = 50;
    private static int DEFAULT_PREFERRED_HEIGHT = 50;
    private static int DEFAULT_PREFERRED_WIDTH = 50;

    private PhotoComponentModel model;
    private PhotoComponentView view;

    public PhotoComponent() {
        setModel(new PhotoComponentModel());
        setView(new PhotoComponentView(this));
    }

    public void paintComponent() {

    }

    public void flip() {
        if(!model.isFlipped()) {
            flipToAnnotation();
        } else {
            flipToPhoto();
        }
    }

    public void flipToAnnotation() {
        model.flipPhoto(true);
    }

    public void flipToPhoto() {
        model.flipPhoto(false);
    }

    private void setModel(PhotoComponentModel model) {
        this.model = model;
        model.addActionListener(event -> repaint());
    }

    private void setView(PhotoComponentView view) {
        this.view = view;
    }
}
