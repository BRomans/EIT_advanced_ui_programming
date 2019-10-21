package eit.mromani.views;

import eit.mromani.controllers.PhotoComponent;
import eit.mromani.model.*;
import eit.mromani.model.AnnotationModel;
import eit.mromani.model.DrawingAnnotationModel;
import eit.mromani.util.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * @author BRomans
 * <p>
 * This is the view for PhotoComponent. Event listeners and drawing methods are implemented in this class.
 */
public class PhotoComponentView {

    // component default values
    private static int DEFAULT_HEIGHT = 600;
    private static int DEFAULT_WIDTH = 600;
    private static int DEFAULT_PREFERRED_HEIGHT = 800;
    private static int DEFAULT_PREFERRED_WIDTH = 800;
    private float SCALE = 1;

    // last mouse event coordinates
    private int _start_position_x;
    private int _start_position_y;

    // image size and coordinates
    private int _scaledWidth;
    private int _scaledHeight;
    private int _centerX;
    private int _centerY;

    // controller of the component
    private PhotoComponent _controller;

    // current editable annotations
    private TextAnnotationModel _currentTextAnnotation;
    private DrawingAnnotationModel _currentDrawingAnnotation;

    public PhotoComponentView(PhotoComponent controller) {
        this._controller = controller;
        setupListeners();

    }

    /**
     * Setups listeners with functional interfaces
     */
    private void setupListeners() {

        _controller.addMouseListener((MousePressListener) this::saveMouseCoordinates);

        _controller.addMouseListener((MouseClickListener) this::evaluateMouseClick);

        _controller.addMouseMotionListener((MouseDragListener) this::evaluateMouseDrag);

        _controller.addKeyListener((KeyboardTypeListener) this::evaluateKeyTyped);
    }

    /**
     * Saves last mouse event coordinates
     *
     * @param mouseEvent
     */
    private void saveMouseCoordinates(MouseEvent mouseEvent) {
        _start_position_x = mouseEvent.getX();
        _start_position_y = mouseEvent.getY();
    }

    /**
     * Evaluates the number of click events and then processes them
     *
     * @param mouseEvent
     */
    private void evaluateMouseClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 1) {
            System.out.println("single clicked");
            if (_controller.getFlipState()) {
                initDrawingAnnotation(_start_position_x, _start_position_y);
                initTextAnnotation(mouseEvent);
            }
        }
        if (mouseEvent.getClickCount() == 2) {
            System.out.println("double clicked");
            _controller.flip();
        }
    }

    /**
     * If the image is in annotation mode, starts processing drawing annotations
     *
     * @param mouseEvent
     */
    private void evaluateMouseDrag(MouseEvent mouseEvent) {
        if (_controller.getFlipState()) {
            System.out.println("Dragging the mouse");
            if(_currentDrawingAnnotation != null) {
                addPoint(_start_position_x, _start_position_y, mouseEvent.getX(), mouseEvent.getY());
            } else {
                initDrawingAnnotation(_start_position_x, _start_position_y);
            }
            _start_position_x = mouseEvent.getX();
            _start_position_y = mouseEvent.getY();
        }
    }

    /**
     * Processes text input and adds it to the last annotation point in focus
     *
     * @param event
     */
    private void evaluateKeyTyped(KeyEvent event) {
        if (_controller.getFlipState()) {
            Character keyValue = event.getKeyChar();
            System.out.println("Key pressed: " + keyValue);
            _currentTextAnnotation.processNewCharacter(keyValue);
            _controller.repaint();
        }
    }

    /**
     * This method processes the drawing of the component and the annotations saved in memory
     *
     * @param graphics
     * @param photoComponent
     */
    public void paint(Graphics graphics, PhotoComponent photoComponent) {
        PhotoComponentModel model = photoComponent.getModel();
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Apply scaling and centering to the component

        this.SCALE = HelperMethods.adjustScaling(_controller.getImage().getWidth(), _controller.getImage().getHeight());
        this._scaledWidth = Math.round(_controller.getImage().getWidth() / SCALE);
        this._scaledHeight = Math.round(_controller.getImage().getHeight() / SCALE);
        this._centerX = (photoComponent.getX() + photoComponent.getWidth() - this._scaledWidth) / 2;
        this._centerY = (photoComponent.getY() + photoComponent.getHeight() - this._scaledHeight) / 2;

        if (!model.isFlipped()) {

            // draw the image
            Image image = _controller.getImage();
            image = image.getScaledInstance(_scaledWidth, _scaledHeight, Image.SCALE_DEFAULT);
            graphics2D.drawImage(image, _centerX, _centerY, null);
        } else {

            // draw the canvas and the annotations
            graphics2D.setColor(Color.white);
            graphics2D.fillRect(_centerX, _centerY, _scaledWidth, _scaledHeight);
            List<AnnotationModel> drawingPoints = _controller.getDrawingPoints();
            List<AnnotationModel> textPoints = _controller.getTextPoints();
            for (AnnotationModel annotationModel : textPoints) {
                annotationModel.drawAnnotation(graphics2D, _controller.getImage().getWidth(), _centerX, _controller.getImage().getHeight(), _centerY, SCALE);
            }
            for (AnnotationModel annotationModel : drawingPoints) {
                annotationModel.drawAnnotation(graphics2D, _controller.getImage().getWidth(), _centerX, _controller.getImage().getHeight(), _centerY, SCALE);
            }
        }

        // draw the border
        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.setColor(Color.black);
        graphics2D.drawRect(_centerX, _centerY, _scaledWidth, _scaledHeight);
    }

    /**
     * Draws a single line between the starting point coordinates and the ending point coordinates
     *
     * @param startX starting point X coordinate
     * @param startY starting point Y coordinate
     */
    private void addPoint(int startX, int startY, int endX, int endY) {
        Graphics2D graphics2D = (Graphics2D) _controller.getGraphics();
        _currentDrawingAnnotation.addPoint(startX, startY);
        _currentDrawingAnnotation.addPoint(endX, endY);
        _currentDrawingAnnotation.drawAnnotation(graphics2D, _controller.getImage().getWidth(), _centerX, _controller.getImage().getHeight(), _centerY, SCALE);

    }

    private void initDrawingAnnotation(int startX, int startY) {
        DrawingAnnotationModel annotationModel = new DrawingAnnotationModel();
        annotationModel.addPoint(startX, startY);
        annotationModel.setLineColor(_controller.getStyleToolbar().getSelectedColor());
        annotationModel.setLineSize(_controller.getStyleToolbar().getStrokeSize());
        annotationModel.setShape(_controller.getStyleToolbar().getSelectedShape());
        _controller.addAnnotationModel(annotationModel);
        _currentDrawingAnnotation = annotationModel;
        _controller.sendMessageToStatusBar("Drawing annotation successfully created!");
    }

    /**
     * Init the text annotation model and request the focus for the text-box
     *
     * @param mouseEvent
     */
    private void initTextAnnotation(MouseEvent mouseEvent) {
        _start_position_x = mouseEvent.getX();
        _start_position_y = mouseEvent.getY();
        _controller.requestFocus();
        TextAnnotationModel annotationModel = new TextAnnotationModel(mouseEvent);
        annotationModel.setFontFamily(_controller.getStyleToolbar().getSelectedFont());
        annotationModel.setFontSize(_controller.getStyleToolbar().getFontSize());
        annotationModel.setLineColor(_controller.getStyleToolbar().getSelectedColor());
        saveTextAnnotation(annotationModel);
        _currentTextAnnotation = annotationModel;
    }

    private void saveTextAnnotation(TextAnnotationModel annotationModel) {
        _controller.addAnnotationModel(annotationModel);
        _controller.sendMessageToStatusBar("Text annotation successfully created!");
    }

    public Dimension getSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_PREFERRED_WIDTH, DEFAULT_PREFERRED_HEIGHT);
    }

}
