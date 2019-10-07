package eit.mromani.views;

import eit.mromani.controllers.PhotoComponent;
import eit.mromani.model.*;
import eit.mromani.model.AnnotationModel;
import eit.mromani.model.DrawingAnnotationModel;
import eit.mromani.util.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
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
    private int SCALE = 1;

    // last mouse event coordinates
    private int _start_position_x;
    private int _start_position_y;

    // image size and coordinates
    private int _scaledWidth;
    private int _scaledHeight;
    private int _centerX;
    private int _centerY;

    // The LineBreakMeasurer used to line-break the paragraph.
    private LineBreakMeasurer _lineMeasurer;

    // index of the first character in the paragraph.
    private int _paragraphStart;

    // index of the first character after the end of the paragraph.
    private int _paragraphEnd;

    // controller of the component
    private PhotoComponent _controller;

    // current editable annotation
    private TextAnnotationModel _currentAnnotation;

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
     * @param mouseEvent
     */
    private void saveMouseCoordinates(MouseEvent mouseEvent) {
        _start_position_x = mouseEvent.getX();
        _start_position_y = mouseEvent.getY();
    }

    /**
     * Evaluates the number of click events and then processes them
     * @param mouseEvent
     */
    private void evaluateMouseClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 1) {
            System.out.println("single clicked");
            if (_controller.getFlipState()) {
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
     * @param mouseEvent
     */
    private void evaluateMouseDrag(MouseEvent mouseEvent) {
        if (_controller.getFlipState()) {
            System.out.println("Dragging the mouse");
            drawAndSaveLine(_start_position_x, _start_position_y, mouseEvent.getX(), mouseEvent.getY());
            _start_position_x = mouseEvent.getX();
            _start_position_y = mouseEvent.getY();
        }
    }

    /**
     * Processes text input and adds it to the last annotation point in focus
     * @param event
     */
    private void evaluateKeyTyped(KeyEvent event) {
        if (_controller.getFlipState()) {
            Character keyValue = event.getKeyChar();
            System.out.println("Key pressed: " + keyValue);
            _currentAnnotation.processNewCharacter(keyValue);
            _controller.repaint();
        }
    }

    /**
     * This method processes the drawing of the component and the annotations saved in memory
     * @param graphics
     * @param photoComponent
     */
    public void paint(Graphics graphics, PhotoComponent photoComponent) {
        PhotoComponentModel model = photoComponent.getModel();
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Apply scaling and centering to the component
        this.SCALE = HelperMethods.adjustScaling(_controller.getImage().getWidth(), _controller.getImage().getHeight());
        this._scaledWidth = _controller.getImage().getWidth() / SCALE;
        this._scaledHeight = _controller.getImage().getHeight() / SCALE;
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
                drawTextAnnotation(graphics2D, (TextAnnotationModel) annotationModel);
            }
            for (AnnotationModel annotationModel : drawingPoints) {
                drawStrokeLine(graphics2D, (DrawingAnnotationModel) annotationModel);
            }
        }

        // draw the border
        graphics2D.setColor(Color.black);
        graphics2D.drawRect(_centerX, _centerY, _scaledWidth, _scaledHeight);
    }

    /**
     * Draws a single line between the starting point coordinates and the ending point coordinates
     * @param startX starting point X coordinate
     * @param startY starting point Y coordinate
     * @param endX ending point X coordinate
     * @param endY ending point Y coordinate
     */
    private void drawAndSaveLine(int startX, int startY, int endX, int endY) {
        DrawingAnnotationModel annotationModel = new DrawingAnnotationModel();
        Graphics2D graphics2D = (Graphics2D) _controller.getGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        annotationModel.setCoordinateX(startX);
        annotationModel.setCoordinateY(startY);
        annotationModel.setEndCoordinateX(endX);
        annotationModel.setEndCoordinateY(endY);
        drawStrokeLine(graphics2D, annotationModel);
        _controller.addAnnotationModel(annotationModel);
        _controller.sendMessageToStatusBar("Drawing annotation successfully created!");
    }

    /**
     * Support function that actually performs the drawing if the starting point and ending points are
     * within valid boundaries
     * @param graphics2D
     * @param annotationModel the drawing annotation
     */
    private void drawStrokeLine(Graphics2D graphics2D, DrawingAnnotationModel annotationModel) {
        boolean startPointValid = HelperMethods.isOnThePicture(annotationModel.getCoordinateX(), annotationModel.getCoordinateY(),
                _controller.getImage().getWidth(), _centerX, _controller.getImage().getHeight(), _centerY, SCALE);

        boolean endPointValid = HelperMethods.isOnThePicture(annotationModel.getEndCoordinateX(), annotationModel.getEndCoordinateY(),
                _controller.getImage().getWidth(), _centerX, _controller.getImage().getHeight(), _centerY, SCALE);

        if (startPointValid && endPointValid) {
            graphics2D.setColor(annotationModel.getLineColor());
            graphics2D.drawLine(annotationModel.getCoordinateX(),
                    annotationModel.getCoordinateY(),
                    annotationModel.getEndCoordinateX(),
                    annotationModel.getEndCoordinateY());
        }
    }

    /**
     * Init the text annotation model and request the focus for the text-box
     * @param mouseEvent
     */
    private void initTextAnnotation(MouseEvent mouseEvent) {
        _start_position_x = mouseEvent.getX();
        _start_position_y = mouseEvent.getY();
        _controller.requestFocus();
        TextAnnotationModel annotationModel = new TextAnnotationModel(mouseEvent);
        saveTextAnnotation(annotationModel);
        _currentAnnotation = annotationModel;
    }

    private void saveTextAnnotation(TextAnnotationModel annotationModel) {
        _controller.addAnnotationModel(annotationModel);
        _controller.sendMessageToStatusBar("Text annotation successfully created!");
    }

    /**
     * Processes the text annotation and calculates boundaries for proper formatting.
     * Custom implementation of the javadoc sample that can be found here:
     * https://docs.oracle.com/javase/tutorial/2d/text/drawmulstring.html
     * @param graphics2D the graphics
     * @param annotationPoint the text annotation
     */
    private void drawTextAnnotation(Graphics2D graphics2D, TextAnnotationModel annotationPoint) {
        boolean startPointValid = HelperMethods.isOnThePicture(annotationPoint.getCoordinateX(), annotationPoint.getCoordinateY(),
                _controller.getImage().getWidth(), _centerX, _controller.getImage().getHeight(), _centerY, SCALE);

        if (startPointValid) {
            graphics2D.setColor(annotationPoint.getLineColor());

            AttributedCharacterIterator paragraph = annotationPoint.getCurrentLineIterator().getIterator();
            _paragraphStart = paragraph.getBeginIndex();
            _paragraphEnd = paragraph.getEndIndex();
            FontRenderContext frc = graphics2D.getFontRenderContext();
            _lineMeasurer = new LineBreakMeasurer(paragraph, frc);

            // we calculate the X position with the photo width and the click X coordinate
            float breakWidth = (float) (_controller.getImage().getWidth() / SCALE - annotationPoint.getCoordinateX() + _centerX);
            float drawPosY = annotationPoint.getCoordinateY();
            _lineMeasurer.setPosition(_paragraphStart);
            try {
                while (_lineMeasurer.getPosition() < _paragraphEnd) {

                    TextLayout layout = _lineMeasurer.nextLayout(breakWidth);

                    // Compute pen x position. If the paragraph is right-to-left we
                    // will align the TextLayouts to the right edge of the panel.
                    float drawPosX = layout.isLeftToRight()
                            ? annotationPoint.getCoordinateX() : breakWidth - layout.getAdvance();

                    // Move y-coordinate by the ascent of the layout.
                    drawPosY += layout.getAscent();

                    // Draw the TextLayout at (drawPosX, drawPosY).
                    layout.draw(graphics2D, drawPosX, drawPosY);

                    // Move y-coordinate in preparation for next layout.
                    drawPosY += layout.getDescent() + layout.getLeading();
                }
            } catch (Exception exception) {
                System.out.println("There was a problem while rendering the annotation text");
                _controller.sendMessageToStatusBar("There was a problem while rendering the annotation text, check log for more info.");
                exception.printStackTrace();
            }
        }
    }

    public Dimension getSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_PREFERRED_WIDTH, DEFAULT_PREFERRED_HEIGHT);
    }

}
