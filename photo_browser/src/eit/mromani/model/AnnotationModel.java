package eit.mromani.model;

import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author BRomans
 *
 * This is the model for generic annotation
 */
public interface AnnotationModel {

    void addChangeListener(ChangeListener listener);

    int getCoordinateX();

    int getCoordinateY();

    Color getLineColor();

    void setLineColor(Color color);

    void setCoordinateX(int coordinateX);

    void setCoordinateY(int coordinateY);

    void drawAnnotation(Graphics2D graphics2D,  int imageWidth, int centerX, int imageHeight, int centerY, float scale);

    boolean isIntoBoundaries();

    boolean showBoundaries();

    Rectangle2D getBoundaries();
}
