package eit.mromani.model;

import java.awt.*;

public class TextAnnotationPoint implements AnnotationPoint {

    private int _coordinateX;
    private int _coordinateY;
    private String _annotationText;
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


    public String getAnnotationText() {
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

    public void setAnnotationText(String annotationText) {
        this._annotationText = annotationText;
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
