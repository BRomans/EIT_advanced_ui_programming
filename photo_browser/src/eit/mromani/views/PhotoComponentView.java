package eit.mromani.views;

import eit.mromani.controllers.PhotoComponent;
import eit.mromani.model.AnnotationPoint;
import eit.mromani.model.DrawingAnnotationPoint;
import eit.mromani.model.PhotoComponentModel;
import eit.mromani.model.TextAnnotationPoint;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * @author BRomans
 * <p>
 * This is the view for PhotoComponent
 */
public class PhotoComponentView {

    private static int DEFAULT_HEIGHT = 600;
    private static int DEFAULT_WIDTH = 600;
    private static int DEFAULT_PREFERRED_HEIGHT = 800;
    private static int DEFAULT_PREFERRED_WIDTH = 800;

    int mouse_position_x;
    int mouse_position_y;

    private PhotoComponent _controller;

    public PhotoComponentView(PhotoComponent controller) {
        this._controller = controller;
        setupListeners();
    }

    private void setupListeners() {
        _controller.addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                mouse_position_x = mouseEvent.getX();
                mouse_position_y = mouseEvent.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 1) {
                    System.out.println("single clicked");

                    if (_controller.getFlipState()) {
                        mouse_position_x = mouseEvent.getX();
                        mouse_position_y = mouseEvent.getY();
                        _controller.requestFocus();
                    }
                }
                if (mouseEvent.getClickCount() == 2) {
                    System.out.println("double clicked");
                    _controller.flip();
                }
            }
        });

        _controller.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent event) {
                if (_controller.getFlipState()) {
                    System.out.println("Dragging the mouse");
                    drawAndSaveLine(mouse_position_x, mouse_position_y, event.getX(), event.getY());
                    mouse_position_x = event.getX();
                    mouse_position_y = event.getY();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        _controller.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent event) {
                Character keyValue = event.getKeyChar();
                System.out.println("Key pressed: " + keyValue);
                drawAnnotationLetter((Graphics2D)_controller.getGraphics(), keyValue, mouse_position_x, mouse_position_y);
                mouse_position_x += 6;
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void paint(Graphics graphics, PhotoComponent photoComponent) {
        PhotoComponentModel model = photoComponent.getModel();
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (!model.isFlipped()) {
            graphics2D.drawImage(_controller.getImage(), photoComponent.getX(), photoComponent.getY(), null);
        } else {
            graphics2D.setColor(Color.white);
            graphics2D.fillRect(photoComponent.getX(), photoComponent.getY(), _controller.getImage().getWidth(), _controller.getImage().getHeight());
            List<AnnotationPoint> drawingPoints = _controller.getDrawingPoints();
            List<AnnotationPoint> textPoints = _controller.getTextPoints();
            for (AnnotationPoint annotationPoint : textPoints) {
                drawAnnotation(graphics2D, (TextAnnotationPoint) annotationPoint);
            }
            for (AnnotationPoint annotationPoint : drawingPoints) {
                drawStrokeLine(graphics2D, (DrawingAnnotationPoint) annotationPoint);
            }
        }
        graphics2D.setColor(Color.black);
        graphics2D.drawRect(photoComponent.getX(), photoComponent.getY(), photoComponent.getWidth(), photoComponent.getHeight());
    }

    public void drawAndSaveLine(int startX, int startY, int endX, int endY) {
        DrawingAnnotationPoint annotationPoint = new DrawingAnnotationPoint();
        Graphics2D graphics2D = (Graphics2D) _controller.getGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        annotationPoint.setCoordinateX(startX);
        annotationPoint.setCoordinateY(startY);
        annotationPoint.setEndCoordinateX(endX);
        annotationPoint.setEndCoordinateY(endY);
        drawStrokeLine(graphics2D, annotationPoint);
        _controller.addPoint(annotationPoint);
        //System.out.println("Saved element: " + annotationPoint.toString());
    }

    public void drawStrokeLine(Graphics2D graphics2D, DrawingAnnotationPoint annotationPoint) {
        graphics2D.setColor(annotationPoint.getLineColor());
        graphics2D.drawLine(annotationPoint.getCoordinateX(),
                        annotationPoint.getCoordinateY(),
                        annotationPoint.getEndCoordinateX(),
                        annotationPoint.getEndCoordinateY());
    }

    public TextAnnotationPoint initAnnotation(MouseEvent mouseEvent) {
        TextAnnotationPoint currentAnnotation= new TextAnnotationPoint();
        currentAnnotation.setLineColor(Color.black);
        currentAnnotation.setCoordinateX(mouseEvent.getX());
        currentAnnotation.setCoordinateY(mouseEvent.getY());
        return currentAnnotation;
    }

    public void drawAnnotation(Graphics2D graphics2D, TextAnnotationPoint annotationPoint) {
        graphics2D.setColor(annotationPoint.getLineColor());
        StringBuilder text = new StringBuilder();
        for(Character character : annotationPoint.getAnnotationText()) {
            text.append(character);
        }
        graphics2D.drawString(String.valueOf(text),
                    annotationPoint.getCoordinateX(),
                    annotationPoint.getCoordinateY());
    }

    public void drawAnnotationLetter(Graphics2D graphics2D, Character character, int startX, int startY) {
        graphics2D.setColor(Color.black);
        graphics2D.drawString(String.valueOf(character),
                startX,
                startY);
    }

    public Dimension getSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_PREFERRED_WIDTH, DEFAULT_PREFERRED_HEIGHT);
    }
}
