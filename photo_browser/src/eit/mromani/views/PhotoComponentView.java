package eit.mromani.views;

import eit.mromani.controllers.PhotoComponent;
import eit.mromani.model.*;
import eit.mromani.model.AnnotationModel;
import eit.mromani.model.DrawingAnnotationModel;

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

    private int start_position_x;
    private int start_position_y;
    private int end_position_x;
    private int end_position_y;

    private PhotoComponent _controller;

    private TextAnnotationModel _currentAnnotation;

    public PhotoComponentView(PhotoComponent controller) {
        this._controller = controller;
        setupListeners();
    }

    private void setupListeners() {
        _controller.addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                start_position_x = mouseEvent.getX();
                start_position_y = mouseEvent.getY();
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
                        start_position_x = mouseEvent.getX();
                        start_position_y = mouseEvent.getY();
                        _controller.requestFocus();
                        initAnnotation(mouseEvent);
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
                    drawAndSaveLine(start_position_x, start_position_y, event.getX(), event.getY());
                    start_position_x = event.getX();
                    start_position_y = event.getY();
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
                _currentAnnotation.addCharacterToWord(keyValue);
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
            List<AnnotationModel> drawingPoints = _controller.getDrawingPoints();
            List<AnnotationModel> textPoints = _controller.getTextPoints();
            for (AnnotationModel annotationModel : textPoints) {
                drawAnnotation(graphics2D, (TextAnnotationModel) annotationModel);
            }
            for (AnnotationModel annotationModel : drawingPoints) {
                drawStrokeLine(graphics2D, (DrawingAnnotationModel) annotationModel);
            }
        }
        graphics2D.setColor(Color.black);
        graphics2D.drawRect(photoComponent.getX(), photoComponent.getY(), photoComponent.getWidth(), photoComponent.getHeight());
    }

    public boolean isOnThePicture(int coordinateX, int coordinateY) {
        boolean horizontalValid = false;
        boolean verticalValid = false;
        if(coordinateX < _controller.getImage().getWidth() + _controller.getX()) {
            horizontalValid = true;
        }
        if(coordinateY < _controller.getImage().getHeight() + _controller.getY()) {
            verticalValid = true;
        }
        return horizontalValid && verticalValid;
    }

    public void drawAndSaveLine(int startX, int startY, int endX, int endY) {
        DrawingAnnotationModel annotationPoint = new DrawingAnnotationModel();
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

    public void drawStrokeLine(Graphics2D graphics2D, DrawingAnnotationModel annotationPoint) {
        boolean startPointValid = isOnThePicture(annotationPoint.getCoordinateX(),
                annotationPoint.getCoordinateY());
        boolean endPointValid = isOnThePicture(annotationPoint.getEndCoordinateX(),
                annotationPoint.getEndCoordinateY());
        if(startPointValid && endPointValid) {
            graphics2D.setColor(annotationPoint.getLineColor());
            graphics2D.drawLine(annotationPoint.getCoordinateX(),
                    annotationPoint.getCoordinateY(),
                    annotationPoint.getEndCoordinateX(),
                    annotationPoint.getEndCoordinateY());
        }
    }

    //TODO evaluate refactoring and removing this function
    public void initAnnotation(MouseEvent mouseEvent) {
        _currentAnnotation = new TextAnnotationModel();
        _currentAnnotation.setLineColor(Color.black);
        _currentAnnotation.setCoordinateX(mouseEvent.getX());
        _currentAnnotation.setCoordinateY(mouseEvent.getY());

    }

    //FIXME rewrite draw function
    public void drawAnnotation(Graphics2D graphics2D, TextAnnotationModel annotationPoint) {
        graphics2D.setColor(annotationPoint.getLineColor());
        graphics2D.drawString("",
                    annotationPoint.getCoordinateX(),
                    annotationPoint.getCoordinateY());
    }

    //TODO remove obsolete function
/*    public void drawAnnotationLetter(Graphics2D graphics2D, Character character, int startX, int startY) {
        graphics2D.setColor(Color.black);
        graphics2D.drawString(String.valueOf(character),
                startX,
                startY);
    }*/

    public Dimension getSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_PREFERRED_WIDTH, DEFAULT_PREFERRED_HEIGHT);
    }
}
