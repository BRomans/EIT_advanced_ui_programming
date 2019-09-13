package eit.mromani.ui;

import javax.swing.*;
import javax.swing.plaf.basic.DefaultMenuLayout;
import java.awt.*;

/**
 *
 * @author BRomans
 *
 * This is the class that draws the main interface
 */
public class PhotoBrowser extends JFrame {

    public static int MINIMUM_WIDTH_PX = 1000;
    public static int MINIMUM_HEIGHT_PX = 1000;

    private JPanel mainContent;
    private JPanel menuContent;
    private JPanel statusBarContent;

    private JMenuBar mainMenuBar;
    private JMenu fileMenu;
    private JMenuItem importOption;
    private JMenuItem deleteOption;
    private JMenuItem quitOption;

    private JLabel statusBarMessage;

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

        buildMenuBar();

        buildWindow();

    }

    /**
     * This function setups the main panels of the window
     */
    private void setupPanels() {
        mainContent = new JPanel();
        menuContent = new JPanel();
        statusBarContent = new JPanel();
    }

    /**
     * This function setups the menu bar and the child elements
     */
    private void setupMenuBar() {
        mainMenuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        importOption = new JMenuItem("Import");
        deleteOption = new JMenuItem("Delete");
        quitOption = new JMenuItem("Quit");
    }

    /**
     * This function builds the menu bar with the proper hierarchy
     */
    private void buildMenuBar() {
        menuContent.add(mainMenuBar);
        mainMenuBar.add(fileMenu);
        fileMenu.add(importOption);
        fileMenu.add(deleteOption);
        fileMenu.add(quitOption);
    }

    /**
     * This function builds the window elements with the proper hierarchy
     */
    private void buildWindow() {
        this.getContentPane().add(menuContent, BorderLayout.NORTH);
        this.getContentPane().add(mainContent, BorderLayout.CENTER);
        this.getContentPane().add(statusBarContent, BorderLayout.SOUTH);
        this.pack();
    }
}
