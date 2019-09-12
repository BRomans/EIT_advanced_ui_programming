package eit.mromani.ui;

import javax.swing.*;
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

    JPanel mainContent;

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

        // Setup panels
        mainContent = new JPanel();
        JPanel menuContent = new JPanel();

        // Setup menu bar and items
        JMenuBar mainMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem importOption = new JMenuItem("Import");
        JMenuItem deleteOption = new JMenuItem("Delete");
        JMenuItem quitOption = new JMenuItem("Quit");

        // Pack menu bar and items
        menuContent.add(mainMenuBar);
        mainMenuBar.add(fileMenu);
        fileMenu.add(importOption);
        fileMenu.add(deleteOption);
        fileMenu.add(quitOption);

        // pack panels and window
        this.getContentPane().add(menuContent, BorderLayout.NORTH);
        this.getContentPane().add(mainContent, BorderLayout.CENTER);
        this.pack();

    }
}
