package eit.mromani.model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TextAnnotationPoint extends JComponent implements AnnotationPoint {

    private int _coordinateX;
    private int _coordinateY;
    private ArrayList<Character> _annotationText = new ArrayList<>();
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


    public ArrayList<Character> getAnnotationText() {
        return this._annotationText;
    }

    public void setAnnotationText(ArrayList<Character> annotationText) {
        this._annotationText = annotationText;
    }

    public void addCharachter(Character character) {
        this._annotationText.add(character);
    }


    @Override
    public void setCoordinateX(int coordinateX) {
        this._coordinateX = coordinateX;
    }

    @Override
    public void setCoordinateY(int coordinateY) {
        this._coordinateY = coordinateY;
    }


    @Override
    public String toString() {
        return "TextAnnotationPoint{" +
                "_coordinateX=" + _coordinateX +
                ", _coordinateY=" + _coordinateY +
                ", _annotationText='" + _annotationText + '\'' +
                ", _lineColor=" + _lineColor +
                '}';
    }

}
