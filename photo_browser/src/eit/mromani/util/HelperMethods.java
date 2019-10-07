package eit.mromani.util;

/**
 * @author BRomans
 * <p>
 * This class provides utility methods for the PhotoComponent class.
 */
public class HelperMethods {

    /**
     * This function returns a scaling value based on the size of the picture
     * @param width the width of the image
     * @param height the height of the image
     * @return the scaling value
     */
    public static int adjustScaling(int width, int height) {
        if (width > 4500 || height > 4500) {
            return 5;
        }
        if (width > 3500 || height > 3500) {
            return 4;
        }
        if (width > 2500 || height  > 2500 ) {
            return 3;
        }
        if (width > 1500 || height > 1500) {
           return 2;
        }
        return 1;
    }

    /**
     *
     * @param coordinateX starting X coordinate of the annotation
     * @param coordinateY starting Y coordinate of the annotation
     * @param imageWidth width of the image
     * @param centerX center X coordinate of the image
     * @param imageHeight height of the image
     * @param centerY center Y coordinate of the image
     * @param scale scaling value
     * @return true if the annotation is within the boundaries of the image, else false.
     */
    public static boolean isOnThePicture(int coordinateX, int coordinateY, int imageWidth, int centerX, int imageHeight, int centerY, int scale) {
        boolean horizontalValid = false;
        boolean verticalValid = false;
        if (coordinateX < imageWidth / scale + centerX
                && coordinateX > centerX) {
            horizontalValid = true;
        }
        if (coordinateY < imageHeight / scale + centerY
                && coordinateY > centerY) {
            verticalValid = true;
        }
        return horizontalValid && verticalValid;
    }
}
