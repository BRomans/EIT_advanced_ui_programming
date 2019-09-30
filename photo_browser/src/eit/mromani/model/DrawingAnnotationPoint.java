package eit.mromani.model;

import java.awt.*;

public class DrawingAnnotationPoint implements AnnotationPoint {

    private int coordinateX;
    private int coordinateY;

    public int getEndCoordinateX() {
        return endCoordinateX;
    }

    public void setEndCoordinateX(int endCoordinateX) {
        this.endCoordinateX = endCoordinateX;
    }

    public int getEndCoordinateY() {
        return endCoordinateY;
    }

    public void setEndCoordinateY(int endCoordinateY) {
        this.endCoordinateY = endCoordinateY;
    }

    private int endCoordinateX;
    private int endCoordinateY;
    private Color lineColor;

    public DrawingAnnotationPoint() {
        lineColor = Color.BLACK;
    }

    @Override
    public int getCoordinateX() {
        return this.coordinateX;
    }

    @Override
    public int getCoordinateY() {
        return this.coordinateY;
    }

    public Color getLineColor() {
        return this.lineColor;
    }

    @Override
    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    @Override
    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public void setLineColor(Color color) {
        this.lineColor = color;
    }

    @Override
    public String toString() {
        return "DrawingAnnotationPoint{" +
                "coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY +
                ", endCoordinateX=" + endCoordinateX +
                ", endCoordinateY=" + endCoordinateY +
                ", lineColor=" + lineColor +
                '}';
    }
}
