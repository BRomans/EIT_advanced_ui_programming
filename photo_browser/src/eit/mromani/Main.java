package eit.mromani;

import eit.mromani.controllers.PhotoBrowser;

/**
 *
 * @author BRomans
 *
 * This is the main class that launches the application
 */
public class Main {


    public static void main(String ...args){
        PhotoBrowser browserWindow = new PhotoBrowser();
        browserWindow.setVisible(true);
    }
}
