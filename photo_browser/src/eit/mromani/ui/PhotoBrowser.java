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

    private JPanel _mainContent;
    private JPanel _menuContent;
    private JPanel _statusBarContent;

    private JMenuBar _mainMenuBar;

    private JMenu _fileMenu;
    private JMenuItem _importOption;
    private JMenuItem _deleteOption;
    private JMenuItem _quitOption;

    private JMenu _viewMenu;
    private JMenuItem _photoViewerOption;
    private JMenuItem _browserOption;

    JLabel _statusLabel;



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
        _mainContent = new JPanel();
        _menuContent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        _statusBarContent = new JPanel();
    }

    /**
     * This function setups the menu bar and the child elements
     */
    private void setupMenuBar() {
        _mainMenuBar = new JMenuBar();

        _fileMenu = new JMenu("File");
        _importOption = new JMenuItem("Import");
        _deleteOption = new JMenuItem("Delete");
        _quitOption = new JMenuItem("Quit");

        _viewMenu = new JMenu("View");
        _photoViewerOption = new JMenuItem("Photo Viewer");
        _browserOption = new JMenuItem("Browse");

    }

    /**
     * This function builds the menu bar with the proper hierarchy
     */
    private void buildMenuBar() {
        _menuContent.add(_mainMenuBar);
        _mainMenuBar.add(_fileMenu);
        _mainMenuBar.add(_viewMenu);

        _fileMenu.add(_importOption);
        _fileMenu.add(_deleteOption);
        _fileMenu.add(_quitOption);

        _viewMenu.add(_photoViewerOption);
        _viewMenu.add(_browserOption);

    }

    /**
     * This function attaches a listener to every menu option
     */
    private void setupMenuListeners() {

    }

    private void showStatusMessage(String message){
        _statusLabel.setText(message);

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
