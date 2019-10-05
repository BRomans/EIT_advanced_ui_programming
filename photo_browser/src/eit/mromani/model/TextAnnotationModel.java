package eit.mromani.model;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Hashtable;

public class TextAnnotationModel implements AnnotationModel {

    private int _coordinateX;
    private int _coordinateY;
    private ArrayList<String> _annotationText = new ArrayList<>();
    private String _currentLine = " ";
    private Color _lineColor;
    private static final
    Hashtable<TextAttribute, Object> map =
            new Hashtable<TextAttribute, Object>();

    static {
        map.put(TextAttribute.FAMILY, "Serif");
        map.put(TextAttribute.SIZE, new Float(18.0));
    }

    private AttributedString _currentLineIterator = new AttributedString(
            _currentLine,
            map);

    public TextAnnotationModel(MouseEvent mouseEvent) {
        setLineColor(Color.black);
        setCoordinateX(mouseEvent.getX());
        setCoordinateY(mouseEvent.getY());
    }

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

    @Override
    public void setLineColor(Color color) {
        this._lineColor = color;
    }


    public ArrayList<String> getAnnotationText() {
        return this._annotationText;
    }

    @Override
    public void setCoordinateX(int coordinateX) {
        this._coordinateX = coordinateX;
    }

    @Override
    public void setCoordinateY(int coordinateY) {
        this._coordinateY = coordinateY;
    }

    public AttributedString getCurrentLineIterator() {
        return this._currentLineIterator;
    }

    public void processNewCharacter(Character character) {
        if(character == '\b'){
            _currentLine = removeLastChar(_currentLine);
        } else {
            _currentLine = addCharacter(_currentLine, character);
        }
        _currentLineIterator = new AttributedString(_currentLine, map);
    }

    private String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    private String addCharacter(String str, char character) {
        return str + String.valueOf(character);
    }

    @Override
    public String toString() {
        return "TextAnnotationModel{" +
                "_coordinateX=" + _coordinateX +
                ", _coordinateY=" + _coordinateY +
                ", _annotationText='" + _annotationText + '\'' +
                ", _lineColor=" + _lineColor +
                '}';
    }

}
