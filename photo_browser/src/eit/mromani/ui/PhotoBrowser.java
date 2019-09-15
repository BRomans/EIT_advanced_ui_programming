package eit.mromani.ui;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

/**
 *
 * @author BRomans
 *
 * This is the class that draws the main interface
 */
public class PhotoBrowser extends JFrame {

    public static int MINIMUM_WIDTH_PX = 1000;
    public static int MINIMUM_HEIGHT_PX = 1000;

    private JPanel _mainContent;
    private JPanel _menuContent;
    private StatusBar _statusBarContent;
    private MenuBar _mainMenuBar;


    public PhotoBrowser() {
        super("Photo Browser App");
        Dimension minimumDimension = new Dimension(MINIMUM_WIDTH_PX, MINIMUM_HEIGHT_PX);
        this.setPreferredSize(minimumDimension);
        setupInterface();
    }


    /**
     * Setup method for elements hierarchy
     */
    private void setupInterface() {

        setupPanels();

        setupMenuBar();

        buildWindow();

    }

    /**
     * This function setups the main panels of the window
     */
    private void setupPanels() {
        _mainContent = new JPanel();
        _menuContent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        _statusBarContent = new StatusBar();
    }

    /**
     * This function setups the menu bar and the child elements
     */
    private void setupMenuBar() {
        _mainMenuBar = new MenuBar();
        _menuContent.add(_mainMenuBar);
    }

    /**
     * This function builds the window elements with the proper hierarchy
     */
    private void buildWindow() {
        this.getContentPane().add(_menuContent, BorderLayout.NORTH);
        this.getContentPane().add(_mainContent, BorderLayout.CENTER);
        this.getContentPane().add(_statusBarContent, BorderLayout.SOUTH);
        this.pack();
    }
}
