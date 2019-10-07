package eit.mromani.model;

import eit.mromani.util.HelperMethods;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.Hashtable;

/**
 *
 * @author BRomans
 *
 * This is the model for Text annotations
 */
public class TextAnnotationModel implements AnnotationModel {

    // annotation fields
    private int _coordinateX;
    private int _coordinateY;
    private Color _lineColor;

    private final Hashtable<TextAttribute, Object> map = new Hashtable<TextAttribute, Object>();

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
