package eit.mromani.util;

/**
 * @author BRomans
 * <p>
 * This class provides utility methods for the PhotoComponent class.
 */
public abstract class HelperMethods {

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
    public static boolean isOnThePicture(int coordinateX, int coordinateY, int imageWidth, int centerX, int imageHeight, int centerY, float scale) {
        boolean horizontalValid = false;
        boolean verticalValid = false;
        if (coordinateX < Math.round(imageWidth / scale + centerX)
                && coordinateX > centerX) {
            horizontalValid = true;
        }
        if (coordinateY < Math.round(imageHeight / scale + centerY)
                && coordinateY > centerY) {
            verticalValid = true;
        }
        return horizontalValid && verticalValid;
    }

    /**
     * This functions cut the last character of a string. If there are no characters in the string, a null string is returned
     * @param str the string to be cut
     * @return the initial string without the last character or a null string
     */
    public static String removeLastChar(String str) {
        if(str.length() > 1) {
            return str.substring(0, str.length() - 1);
        } else
            return "\0";
    }


    /**
     * This function appends a character to the given string
     * @param str the string under editing
     * @param character a new character to add
     * @return a new string containing the added character
     */
    public static String addCharacter(String str, char character) {
        return str + String.valueOf(character);
    }
}
