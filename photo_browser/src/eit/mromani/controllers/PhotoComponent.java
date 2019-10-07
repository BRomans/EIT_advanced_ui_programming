package eit.mromani.controllers;

import eit.mromani.model.*;
import eit.mromani.model.AnnotationModel;
import eit.mromani.model.DrawingAnnotationModel;
import eit.mromani.views.PhotoComponentView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;


/**
 *
 * @author BRomans
 *
 * This is the controller for PhotoComponent
 */
public class PhotoComponent extends JComponent {

    private PhotoComponentModel _model;
    private PhotoComponentView _view;
    private BufferedImage _image;
    private StatusBar _statusBar;

    public PhotoComponent(StatusBar statusBar) {
        setModel(new PhotoComponentModel());
        setView(new PhotoComponentView(this));
        this.setSize(_view.getSize());
        this.setPreferredSize(_view.getPreferredSize());
        this._statusBar = statusBar;
    }

    /**
     * Loads an image into the application
     * @param path The path to the image on the filesystem
     */
    void renderImage(String path) {
        try {
            _image = ImageIO.read(new File(path));
        } catch (Exception exception) {
            System.out.println("There was an error loading your image");
            _statusBar.showStatusMessage("There was an error loading your image");
            exception.printStackTrace();
        }
    }

    /**
     * Flips the image from annotation mode to image mode and viceversa.
     */
    public void flip() {
        if(!_model.isFlipped()) {
            flipToAnnotation();
            _statusBar.showStatusMessage("Annotation Mode: ON");
        } else {
            flipToPhoto();
            _statusBar.showStatusMessage("Annotation Mode: OFF");
        }
    }

    private void flipToAnnotation() {
        _model.flipPhoto(true);
    }

    private void flipToPhoto() {
        _model.flipPhoto(false);
    }

    /**
     * Saves a new annotation in memory
     * @param annotationModel the annotation
     */
    public void addAnnotationModel(AnnotationModel annotationModel) {
        if(annotationModel instanceof DrawingAnnotationModel) {
            _model.addDrawingPoint(annotationModel);
        } else if (annotationModel instanceof TextAnnotationModel) {
            _model.addTextPoint(annotationModel);
        }
    }

    /**
     * Cover method to be called by inner components for displaying messages in the status bar
     * @param message The message to be displayed
     */
    public void sendMessageToStatusBar(String message) {
        _statusBar.showStatusMessage(message);
    }

    /* Getters and setters*/

    private void setModel(PhotoComponentModel model) {
        this._model = model;
        model.addActionListener(event -> repaint());
        model.addChangeListener(event -> repaint());
    }

    public List<AnnotationModel> getDrawingPoints() {
        return _model.getDrawingPoints();
    }

    public List<AnnotationModel> getTextPoints() {
        return _model.getTextPoints();
    }

    public int getPhotoComponentX() {
        return this.getX();
    }

    public int getPhotoComponentY() {
        return this.getY();
    }


    private void setView(PhotoComponentView view) {
        this._view = view;
    }

    public PhotoComponentModel getModel() {
        return this._model;
    }

    public BufferedImage getImage() {
        return this._image;
    }

    public boolean getFlipState() {
        return _model.isFlipped();
    }

    @Override
    public Dimension getPreferredSize() {
        return _view.getPreferredSize();
    }

    /**
     * Functions that trigger the painting of the component
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        System.out.println("Repaint triggered!");
        super.paintComponent(g);
        _view.paint(g, this);
    }
}
