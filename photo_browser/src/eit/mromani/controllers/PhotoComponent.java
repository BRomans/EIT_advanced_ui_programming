package eit.mromani.controllers;

import eit.mromani.model.PhotoComponentModel;
import eit.mromani.views.PhotoComponentView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

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

    public PhotoComponent() {
        setModel(new PhotoComponentModel());
        setView(new PhotoComponentView(this));
        this.setSize(_view.getSize());
        this.setPreferredSize(_view.getPreferredSize());
       // initPanels();
    }

    //FIXME: display the _image properly
    public void displayImage(String path) {
        try {
            /*ImageIcon imageFile = new ImageIcon(path);
            JLabel imageLabel = new JLabel(imageFile);
            _image = imageFile.getImage();*/
            _image = ImageIO.read(new File(path));

        } catch (Exception exception) {
            System.out.println("There was an error loading your image");
            exception.printStackTrace();
        }
    }

    public void flip() {
        if(!_model.isFlipped()) {
            flipToAnnotation();
        } else {
            flipToPhoto();
        }
    }

    public void flipToAnnotation() {
        _model.flipPhoto(true);
    }

    public void flipToPhoto() {
        _model.flipPhoto(false);
    }

    private void setModel(PhotoComponentModel model) {
        this._model = model;
        model.addActionListener(event -> repaint());
        model.addChangeListener(event -> repaint());
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

    @Override
    public Dimension getPreferredSize() {
        return _view.getPreferredSize();
    }

    @Override
    public void paintComponent(Graphics g) {
        _view.paint(g, this);
    }
}
