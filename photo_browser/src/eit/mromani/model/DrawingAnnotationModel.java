package eit.mromani.model;

import eit.mromani.util.HelperMethods;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BRomans
 *
 * This is the model for free-hand Drawing annotations
 */
public class DrawingAnnotationModel extends BaseAnnotationModel {

    // annotation fields
    private int _coordinateX;
    private int _coordinateY;
    private int _endCoordinateX;
    private int _endCoordinateY;
    private Color _lineColor;
    private int _lineSize;
    private String _shape;

    // TODO refactor the way drawings are saved
    private List points = new ArrayList<Point>();

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

    /**
     * Support function that actually performs the drawing if the starting point and ending points are
     * within valid boundaries
     *
     * @param graphics2D
     */
    @Override
    public void drawAnnotation(Graphics2D graphics2D, int imageWidth, int centerX, int imageHeight, int centerY, float scale) {
        boolean startPointValid = HelperMethods.isOnThePicture(getCoordinateX(), getCoordinateY(),
                imageWidth, centerX, imageHeight, centerY, scale);

        boolean endPointValid = HelperMethods.isOnThePicture(getEndCoordinateX(), getEndCoordinateY(),
                imageWidth, centerX, imageHeight, centerY, scale);

        if (startPointValid && endPointValid) {
            graphics2D.setColor(getLineColor());
            graphics2D.setStroke(new BasicStroke(getLineSize()));
            if (getShape().equals("stroke")) {
                graphics2D.drawLine(getCoordinateX(), getCoordinateY(), getEndCoordinateX(), getEndCoordinateY());
            }
            if (getShape().equals("square")) {
                //TODO implement square drawing
            }
            if (getShape().equals("circle")) {
                //TODO implement circle drawing
            }

        }
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
