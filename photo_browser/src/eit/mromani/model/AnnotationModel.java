package eit.mromani.model;

import java.awt.*;

/**
 *
 * @author BRomans
 *
 * This is the model for generic annotation
 */
public interface AnnotationModel {


    int getCoordinateX();

    int getCoordinateY();

    Color getLineColor();

    void setLineColor(Color color);

    void setCoordinateX(int coordinateX);

    void setCoordinateY(int coordinateY);

    void drawAnnotation(Graphics2D graphics2D,  int imageWidth, int centerX, int imageHeight, int centerY, float scale);
}
