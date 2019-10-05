package eit.mromani.model;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Hashtable;

public class TextAnnotationModel implements AnnotationModel {

    private int _coordinateX;
    private int _coordinateY;
    /*    private ArrayList<ActionListener> _actionListeners = new ArrayList<>();
        private ArrayList<ChangeListener> _changeListeners = new ArrayList<>();*/
    private ArrayList<String> _annotationText = new ArrayList<>();
    private String _currentLine = " ";
    private int _lineIndex = 0;
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
        String emptyLine = "";
        addLine(emptyLine);
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

    public String getLastLine() {
        return this._annotationText.get(_lineIndex);
    }

    public AttributedString getCurrentLineIterator() {
        return this._currentLineIterator;
    }

    public void processNewCharacter(Character character) {
        //String lastLine = getLastLine();
        String currentline = getLastLine();
        if(character == '\b'){
            _currentLine = removeLastChar(_currentLine);
        } else {
            _currentLine = addCharacter(_currentLine, character);
        }
        _currentLineIterator = new AttributedString(_currentLine, map);

  /*      if (character != '\n') {
            //this._annotationText.add(_annotationText.size()-1, lastLine);
            //addLine(_lineIndex, currentline);
        } else {
            String newLine = "";
            _lineIndex++;
            addLine(_lineIndex, newLine);
            //lastLine = String.valueOf(character);
        }*/
    }

/*
    public void addActionListener(ActionListener listener) {
        this._actionListeners.add(listener);
    }

    public void addChangeListener(ChangeListener listener) {
        this._changeListeners.add(listener);
    }

*/

    private String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    private String addCharacter(String str, char character) {
        return str + String.valueOf(character);
    }

    public void setAnnotationText(ArrayList<String> annotationText) {
        this._annotationText = annotationText;
    }

    public void addLine(String line) {
        this._annotationText.add(line);
    }

    public void addLine(int index, String line) {
        this._annotationText.add(index, line);
    }
/*
    private void fireChangeListener() {
        for (ChangeListener listener : _changeListeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }

    private void fireDrawLine() {
        for (ActionListener listener : _actionListeners) {
            listener.actionPerformed(new ActionEvent(this, (int) System.currentTimeMillis(), "drawCharacter"));
        }
    }*/

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
