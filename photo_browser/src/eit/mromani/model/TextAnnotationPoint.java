package eit.mromani.model;

public class TextAnnotationPoint implements AnnotationPoint {

    private int coordinateX;
    private int coordinateY;
    private String annotationText;

    @Override
    public int getCoordinateX() {
        return this.coordinateX;
    }

    @Override
    public int getCoordinateY() {
        return this.coordinateY;
    }

    public String getAnnotationText() {
        return this.annotationText;
    }

    @Override
    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    @Override
    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public void setAnnotationText(String annotationText) {
        this.annotationText = annotationText;
    }

}
