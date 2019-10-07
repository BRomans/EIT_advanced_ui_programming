package eit.mromani.controllers;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author BRomans
 *
 * This is the class that draws the main interface using JFrame
 */
public class PhotoBrowser extends JFrame {

    public static int MINIMUM_WIDTH_PX = 450;
    public static int MINIMUM_HEIGHT_PX = 450;
    public static int PREFERRED_WIDTH_PX = 1000;
    public static int PREFERRED_HEIGHT_PX = 1000;
    public static int MAXIMUM_WIDTH_PX = 1600;
    public static int MAXIMUM_HEIGHT_PX = 1600;
  
    // main components
    private JPanel _menuContent;
    private JScrollPane _photoContainer;
    private StatusBar _bottomStatusBar;
    private MenuBar _mainMenuBar;

    // file menu components
    private JMenu _fileMenu;
    private JMenuItem _importOption;
    private JMenuItem _deleteOption;
    private JMenuItem _quitOption;

    // view menu components
    private JMenu _viewMenu;
    private JMenuItem _photoViewerOption;
    private JMenuItem _browserOption;

    // toolbar components
    private JToolBar _toolbarButtons;
    private StyleToolbar _drawingToolbar;
    private JButton _peopleButton;
    private JButton _placesButton;
    private JButton _schoolButton;

    public PhotoBrowser() {
        super("Photo Browser App");
        Dimension minimumDimension = new Dimension(MINIMUM_WIDTH_PX, MINIMUM_HEIGHT_PX);
        Dimension preferredDimension = new Dimension(PREFERRED_WIDTH_PX, PREFERRED_HEIGHT_PX);
        Dimension maximumDimension = new Dimension(MAXIMUM_WIDTH_PX, MAXIMUM_HEIGHT_PX);
        this.setMinimumSize(minimumDimension);
        this.setPreferredSize(preferredDimension);
        this.setMaximumSize(maximumDimension);
        setupInterface();
    }


    /**
     * Setup method for elements hierarchy
     */
    private void setupInterface() {

        initPanels();

        initMenuBar();

        initToolbar();

        buildMenuBar();

        buildToolbar();

        initMenuListeners();

        initToolbarListeners();

        buildWindow();

    }

    /**
     * This function setups the main panels of the window
     */
    private void initPanels() {

        _menuContent = new JPanel(new FlowLayout(FlowLayout.LEFT));

        _photoContainer = new JScrollPane();
        _photoContainer.setBackground(Color.BLUE);
        _photoContainer.getViewport().setBackground(Color.CYAN);
        _photoContainer.setOpaque(true);
        _photoContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        _photoContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        _bottomStatusBar = new StatusBar();

        _toolbarButtons = new JToolBar(SwingConstants.VERTICAL);
        _drawingToolbar = new StyleToolbar(SwingConstants.HORIZONTAL);

    }

    /**
     * This function init the menu bar and the child elements
     */
    private void initMenuBar() {
        _fileMenu = new JMenu("File");
        _importOption = new JMenuItem("Import");
        _deleteOption = new JMenuItem("Delete");
        _quitOption = new JMenuItem("Quit");
        _viewMenu = new JMenu("View");
        _photoViewerOption = new JMenuItem("Photo Viewer");
        _browserOption = new JMenuItem("Browse");
        _mainMenuBar = new MenuBar();
    }

    /**
     * This function init the menu bar and the child elements
     */
    private void initToolbar() {
        _peopleButton = new JButton("People");
        _placesButton = new JButton("Places");
        _schoolButton = new JButton("School");
    }

    /**
     * This function builds the menu bar with the proper hierarchy
     */
    private void  buildMenuBar(){
        _menuContent.add(_mainMenuBar);

        // add file menu and view menu to menu bar
        _mainMenuBar.add(_fileMenu);
        _mainMenuBar.add(_viewMenu);

        // add child options to file menu
        _fileMenu.add(_importOption);
        _fileMenu.add(_deleteOption);
        _fileMenu.add(_quitOption);

        // add child options to view menu
        _viewMenu.add(_photoViewerOption);
        _viewMenu.add(_browserOption);

    }

    /**
     * This function builds the toolbar with the proper hierarchy
     */
    private void buildToolbar() {
        _toolbarButtons.add(_peopleButton);
        _toolbarButtons.addSeparator();
        _toolbarButtons.add(_placesButton);
        _toolbarButtons.addSeparator();
        _toolbarButtons.add(_schoolButton);
    }

    /**
     * This functions init listeners for menu items
     *
     */
    private void initMenuListeners() {
        _importOption.addActionListener(e -> importImage());

        _deleteOption.addActionListener(e -> removeImage());

        _quitOption.addActionListener(e -> System.exit(0));

        _photoViewerOption.addActionListener(e -> _bottomStatusBar.displayNotImplemented("PHOTO VIEWER"));

        _browserOption.addActionListener(e -> _bottomStatusBar.displayNotImplemented("BROWSE"));
    }

    private void importImage() {
        String filePath = _mainMenuBar.loadPicture();
        if (filePath != null && !filePath.equals("")) {
            _bottomStatusBar.showStatusMessage("Successfully loaded file: " + filePath);
            PhotoComponent photoComponent = new PhotoComponent(_bottomStatusBar, _drawingToolbar);
            photoComponent.renderImage(filePath);
            _photoContainer.getViewport().add(photoComponent);
        } else {
            _bottomStatusBar.showStatusMessage("No file could be loaded");

        }
    }

    private void removeImage() {
        _bottomStatusBar.showStatusMessage("Removing current image...");
        _photoContainer.getViewport().removeAll();
        super.repaint();
    }

    /**
     * This function init listeners for the toolbar buttons
     */
    private void initToolbarListeners() {
        _peopleButton.addActionListener(e -> _bottomStatusBar.displayNotImplemented("TOOLBAR"));

        _placesButton.addActionListener(e -> _bottomStatusBar.displayNotImplemented("TOOLBAR"));

        _schoolButton.addActionListener(e -> _bottomStatusBar.displayNotImplemented("TOOLBAR"));
    }

    /**
     * This function builds the window elements with the proper hierarchy
     */
    private void buildWindow() {
        JPanel topPanel = new JPanel(new GridLayout());
        topPanel.add(_menuContent);
        topPanel.add(_drawingToolbar);
        this.getContentPane().add(topPanel, BorderLayout.NORTH);
        this.getContentPane().add(_photoContainer, BorderLayout.CENTER);
        this.getContentPane().add(_bottomStatusBar, BorderLayout.SOUTH);
        this.getContentPane().add(_toolbarButtons, BorderLayout.WEST);
        this.pack();
    }
}
