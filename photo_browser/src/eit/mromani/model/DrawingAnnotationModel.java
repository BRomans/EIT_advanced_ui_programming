package eit.mromani.model;

import java.awt.*;

/**
 *
 * @author BRomans
 *
 * This is the model for free-hand Drawing annotations
 */
public class DrawingAnnotationModel implements AnnotationModel {

    // annotation fields
    private int _coordinateX;
    private int _coordinateY;
    private int _endCoordinateX;
    private int _endCoordinateY;
    private Color _lineColor;
    private int _lineSize;
    private String _shape;

    /*Getters and setters*/

    public int getEndCoordinateX() {
        return _endCoordinateX;
    }

    public void setEndCoordinateX(int endCoordinateX) {
        this._endCoordinateX = endCoordinateX;
    }

    public int getEndCoordinateY() {
        return _endCoordinateY;
    }

    public void setEndCoordinateY(int endCoordinateY) {
        this._endCoordinateY = endCoordinateY;
    }

    public DrawingAnnotationModel() {
        _lineColor = Color.BLACK;
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

    @Override
    public void setCoordinateX(int coordinateX) {
        this._coordinateX = coordinateX;
    }

    @Override
    public void setCoordinateY(int coordinateY) {
        this._coordinateY = coordinateY;
    }

    public int getLineSize() {
        return _lineSize;
    }

    public void setLineSize(int _lineSize) {
        this._lineSize = _lineSize;
    }

    public String getShape() {
        return _shape;
    }

    public void setShape(String _shape) {
        this._shape = _shape;
    }

    @Override
    public String toString() {
        return "DrawingAnnotationModel{" +
                "_coordinateX=" + _coordinateX +
                ", _coordinateY=" + _coordinateY +
                ", _endCoordinateX=" + _endCoordinateX +
                ", _endCoordinateY=" + _endCoordinateY +
                ", _lineColor=" + _lineColor +
                '}';
    }
}
