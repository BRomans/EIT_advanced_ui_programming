package eit.mromani.model;

import eit.mromani.controllers.PhotoComponent;
import eit.mromani.util.HelperMethods;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.Hashtable;

/**
 *
 * @author BRomans
 *
 * This is the model for Text annotations
 */
public class TextAnnotationModel extends BaseAnnotationModel {

    // annotation fields
    private int _coordinateX;
    private int _coordinateY;
    private Color _lineColor;

    private final Hashtable<TextAttribute, Object> map = new Hashtable<TextAttribute, Object>();

    // The LineBreakMeasurer used to line-break the paragraph.
    private LineBreakMeasurer _lineMeasurer;

    // index of the first character in the paragraph.
    private int _paragraphStart;

    // index of the first character after the end of the paragraph.
    private int _paragraphEnd;

    //init the current line with an empty character that allows the AttributedString creation
    //FIXME this character is nor correctly rendered on some fonts
    private String _currentLine = "\0";
    private AttributedString _currentLineIterator = new AttributedString(
            _currentLine,
            map);

    public TextAnnotationModel(MouseEvent mouseEvent) {
        setLineColor(Color.black);
        setCoordinateX(mouseEvent.getX());
        setCoordinateY(mouseEvent.getY());
        map.put(TextAttribute.FAMILY, "Serif");
        map.put(TextAttribute.SIZE, new Float(18.0));
    }

    /*Getters and setters*/

    @Override
    public int getCoordinateX() {
        return this._coordinateX;
    }

    @Override
    public int getCoordinateY() {
        return this._coordinateY;
    }

    @Override
    public Color getLineColor() {
        return this._lineColor;
    }

    public AttributedString getCurrentLineIterator() {
        return this._currentLineIterator;
    }

    @Override
    public void setLineColor(Color color) {
        this._lineColor = color;
    }

    @Override
    public void setCoordinateX(int coordinateX) {
        this._coordinateX = coordinateX;
    }

    @Override
    public void setCoordinateY(int coordinateY) {
        this._coordinateY = coordinateY;
    }


    /**
     * Processes the text annotation and calculates boundaries for proper formatting.
     * Custom implementation of the javadoc sample that can be found here:
     * https://docs.oracle.com/javase/tutorial/2d/text/drawmulstring.html
     *
     * @param graphics2D      the graphics
     */
    @Override
    public void drawAnnotation(Graphics2D graphics2D, int imageWidth, int centerX, int imageHeight, int centerY, float scale) {
        //TODO Refactor drawing method
        boolean startPointValid = HelperMethods.isOnThePicture(getCoordinateX(), getCoordinateY(),
                imageWidth, centerX, imageHeight, centerY, scale);

        if (startPointValid) {
            graphics2D.setColor(getLineColor());

            AttributedCharacterIterator paragraph = getCurrentLineIterator().getIterator();
            _paragraphStart = paragraph.getBeginIndex();
            _paragraphEnd = paragraph.getEndIndex();
            FontRenderContext frc = graphics2D.getFontRenderContext();
            _lineMeasurer = new LineBreakMeasurer(paragraph, frc);

            // we calculate the X position with the photo width and the click X coordinate
            float breakWidth = (float) (imageWidth / scale - getCoordinateX() + centerX);
            float drawPosY = getCoordinateY();
            _lineMeasurer.setPosition(_paragraphStart);
            try {
                while (_lineMeasurer.getPosition() < _paragraphEnd) {

                    TextLayout layout = _lineMeasurer.nextLayout(breakWidth);

                    // Compute pen x position. If the paragraph is right-to-left we
                    // will align the TextLayouts to the right edge of the panel.
                    float drawPosX = layout.isLeftToRight()
                            ? getCoordinateX() : breakWidth - layout.getAdvance();

                    // Move y-coordinate by the ascent of the layout.
                    drawPosY += layout.getAscent();


                    boolean validPoint = HelperMethods.isOnThePicture(Math.round(drawPosX), Math.round(drawPosY),
                            imageWidth, centerX, imageHeight, centerY, scale);

                    // Draw the TextLayout at (drawPosX, drawPosY).
                    if(validPoint) {
                        layout.draw(graphics2D, drawPosX, drawPosY);
                    }

                    // Move y-coordinate in preparation for next layout.
                    drawPosY += layout.getDescent() + layout.getLeading();
                }
            } catch (Exception exception) {
                System.out.println("There was a problem while rendering the annotation text");
                //.sendMessageToStatusBar("There was a problem while rendering the annotation text, check log for more info.");
                exception.printStackTrace();
            }
        }
    }

    public void setFontFamily(String fontFamily) {
        map.put(TextAttribute.FAMILY, fontFamily);
    }

    public void setFontSize(float fontSize) {
        map.put(TextAttribute.BACKGROUND.SIZE, fontSize);
    }

    /**
     * This function evaluates the last character and updates the annotation text
     * @param character the last character typed
     */
    public void processNewCharacter(Character character) {
        if(character == '\b'){
            _currentLine = HelperMethods.removeLastChar(_currentLine);
        } else {
            _currentLine = HelperMethods.addCharacter(_currentLine, character);
        }
        _currentLineIterator = new AttributedString(_currentLine, map);
        fireChangeListener();
    }

    @Override
    public String toString() {
        return "TextAnnotationModel{" +
                "_coordinateX=" + _coordinateX +
                ", _coordinateY=" + _coordinateY +
                ", _lineColor=" + _lineColor +
                ", _currentLineIterator=" + _currentLineIterator +
                '}';
    }
}
