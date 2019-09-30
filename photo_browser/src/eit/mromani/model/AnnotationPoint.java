package eit.mromani.model;

import java.awt.*;

public interface AnnotationPoint {


    int getCoordinateX();

    int getCoordinateY();

    Color getLineColor();

    void setLineColor(Color color);

    void setCoordinateX(int coordinateX);

    void setCoordinateY(int coordinateY);
}
