package eit.mromani.controllers;

import eit.mromani.model.PhotoComponentModel;
import eit.mromani.views.PhotoComponentView;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author BRomans
 *
 * This is the controller for PhotoComponent
 */
public class PhotoComponent extends JComponent {

    private PhotoComponentModel model;
    private PhotoComponentView view;
    private JPanel photo;
    private JPanel canvas;
    private JLabel image;

    public PhotoComponent() {
        setModel(new PhotoComponentModel());
        setView(new PhotoComponentView(this));
        this.setSize(view.getSize());
        this.setPreferredSize(view.getPreferredSize());
        initPanels();
    }

    //FIXME: display the image properly
    public void displayImage(String path) {
        try {
            ImageIcon imageFile = new ImageIcon(path);
            image = new JLabel(imageFile);
            photo.add(image);
        } catch (Exception exception) {
            System.out.println("There was an error loading your image");
            exception.printStackTrace();
        }
    }

    public void initPanels() {
        photo = new JPanel(new BorderLayout());
        canvas = new JPanel(new BorderLayout());
        photo.setBackground(Color.CYAN);
        photo.setOpaque(true);
        this.add(photo);
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

    public PhotoComponentModel getModel() {
        return this.model;
    }

    @Override
    public Dimension getPreferredSize() {
        return view.getPreferredSize();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent( g );
        view.paint(g, this);
    }
}
