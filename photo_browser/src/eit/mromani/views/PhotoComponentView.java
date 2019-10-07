package eit.mromani.views;

import eit.mromani.controllers.PhotoComponent;
import eit.mromani.model.*;
import eit.mromani.model.AnnotationModel;
import eit.mromani.model.DrawingAnnotationModel;
import eit.mromani.util.KeyboardTypeListener;
import eit.mromani.util.MouseClickListener;
import eit.mromani.util.MouseDragListener;
import eit.mromani.util.MousePressListener;

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
 * This is the view for PhotoComponent
 */
public class PhotoComponentView {

    private static int DEFAULT_HEIGHT = 600;
    private static int DEFAULT_WIDTH = 600;
    private static int DEFAULT_PREFERRED_HEIGHT = 800;
    private static int DEFAULT_PREFERRED_WIDTH = 800;
    private int SCALE = 1;

    private int _start_position_x;
    private int _start_position_y;
    private int _end_position_x;
    private int _end_position_y;
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

    private PhotoComponent _controller;

    private TextAnnotationModel _currentAnnotation;

    public PhotoComponentView(PhotoComponent controller) {
        this._controller = controller;
        setupListeners();

    }

    private void setupListeners() {

        _controller.addMouseListener((MouseClickListener) this::evaluateMouseClick);

        _controller.addMouseListener((MousePressListener) this:: saveMouseCoordinates);

        _controller.addMouseMotionListener((MouseDragListener) this::evaluateMouseDrag );

        _controller.addKeyListener((KeyboardTypeListener) this::evaluateKeyTyped);
    }

    private void saveMouseCoordinates(MouseEvent mouseEvent) {
        _start_position_x = mouseEvent.getX();
        _start_position_y = mouseEvent.getY();
    }

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

    private void evaluateMouseDrag(MouseEvent mouseEvent) {
        if (_controller.getFlipState()) {
            System.out.println("Dragging the mouse");
            drawAndSaveLine(_start_position_x, _start_position_y, mouseEvent.getX(), mouseEvent.getY());
            _start_position_x = mouseEvent.getX();
            _start_position_y = mouseEvent.getY();
        }
    }

    private void evaluateKeyTyped(KeyEvent event) {
        if (_controller.getFlipState()) {
            Character keyValue = event.getKeyChar();
            System.out.println("Key pressed: " + keyValue);
            _currentAnnotation.processNewCharacter(keyValue);
            _controller.repaint();
        }
    }

    public void paint(Graphics graphics, PhotoComponent photoComponent) {
        PhotoComponentModel model = photoComponent.getModel();
        adjustScaling();
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        _scaledWidth = _controller.getImage().getWidth() / SCALE;
        _scaledHeight = _controller.getImage().getHeight() / SCALE;
        Image image = _controller.getImage();
        image = image.getScaledInstance(_scaledWidth, _scaledHeight,  Image.SCALE_DEFAULT);
        _centerX = (photoComponent.getX() + photoComponent.getWidth() - _scaledWidth) / 2;
        _centerY = (photoComponent.getY() + photoComponent.getHeight() - _scaledHeight) / 2;
        if (!model.isFlipped()) {
            graphics2D.drawImage(image, _centerX, _centerY, null);
        } else {
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
        graphics2D.setColor(Color.black);
        graphics2D.drawRect(_centerX, _centerY, _scaledWidth, _scaledHeight);
    }

    private boolean isOnThePicture(int coordinateX, int coordinateY) {
        boolean horizontalValid = false;
        boolean verticalValid = false;
        if (coordinateX < _controller.getImage().getWidth() / SCALE + _centerX
            && coordinateX > _centerX) {
            horizontalValid = true;
        }
        if (coordinateY < _controller.getImage().getHeight() / SCALE + _centerY
            && coordinateY > _centerY) {
            verticalValid = true;
        }
        return horizontalValid && verticalValid;
    }

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
    }

    public void drawStrokeLine(Graphics2D graphics2D, DrawingAnnotationModel annotationModel) {
        boolean startPointValid = isOnThePicture(annotationModel.getCoordinateX(),
                annotationModel.getCoordinateY());
        boolean endPointValid = isOnThePicture(annotationModel.getEndCoordinateX(),
                annotationModel.getEndCoordinateY());
        if (startPointValid && endPointValid) {
            graphics2D.setColor(annotationModel.getLineColor());
            graphics2D.drawLine(annotationModel.getCoordinateX(),
                    annotationModel.getCoordinateY(),
                    annotationModel.getEndCoordinateX(),
                    annotationModel.getEndCoordinateY());
        }
    }

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
    }

    private void drawTextAnnotation(Graphics2D graphics2D, TextAnnotationModel annotationPoint) {
        boolean startPointValid = isOnThePicture(annotationPoint.getCoordinateX(),
                annotationPoint.getCoordinateY());
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
            }catch (Exception exception) {
                System.out.println("There was a problem while rendering the annotation text");
                exception.printStackTrace();
            }
        }
    }

    private void adjustScaling() {
        if (_controller.getImage().getWidth() > 1500 || _controller.getImage().getHeight() > 1500) {
            this.SCALE = 2;
        } else if (_controller.getImage().getWidth() > 2500 || _controller.getImage().getHeight() > 2500) {
            this.SCALE = 3;
        } else if (_controller.getImage().getWidth() > 3500 || _controller.getImage().getHeight() > 3500) {
            this.SCALE = 4;
        } else if (_controller.getImage().getWidth() > 4500 || _controller.getImage().getHeight() > 4500) {
            this.SCALE = 5;
        } else {
            this.SCALE = 1;
        }
    }

    public Dimension getSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_PREFERRED_WIDTH, DEFAULT_PREFERRED_HEIGHT);
    }

}
