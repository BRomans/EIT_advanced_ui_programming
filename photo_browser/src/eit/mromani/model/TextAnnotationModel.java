package eit.mromani.model;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TextAnnotationModel implements AnnotationModel {

    private int _coordinateX;
    private int _coordinateY;
    private ArrayList<ActionListener> _actionListeners = new ArrayList<>();
    private ArrayList<ChangeListener> _changeListeners = new ArrayList<>();
    private ArrayList<String> _annotationText = new ArrayList<>();
    private String _currentWord;
    private Color _lineColor;

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

    public String getCurrentWord() { return this._currentWord; }

    public void addCharacterToWord(Character character) {
        if(character != ' ') {
            this._currentWord = this._currentWord + character;
        } else {
            addLine(this._currentWord);
            this._currentWord = "";
        }
    }

    public void addActionListener(ActionListener listener) {
        this._actionListeners.add(listener);
    }

    public void addChangeListener(ChangeListener listener) {
        this._changeListeners.add(listener);
    }

    public void setAnnotationText(ArrayList<String> annotationText) {
        this._annotationText = annotationText;
    }

    public void addLine(String line) {
        this._annotationText.add(line);
    }

    private void fireChangeListener() {
        for (ChangeListener listener : _changeListeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }

    private void fireDrawLine() {
        for (ActionListener listener : _actionListeners) {
            listener.actionPerformed(new ActionEvent(this, (int) System.currentTimeMillis(), "drawLine"));
        }
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
