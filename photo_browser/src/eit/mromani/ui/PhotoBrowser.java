package eit.mromani.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author BRomans
 *
 * This is the class that draws the main interface
 */
public class PhotoBrowser extends JFrame {

    public static int MINIMUM_WIDTH_PX = 450;
    public static int MINIMUM_HEIGHT_PX = 450;
    public static int PREFERRED_WIDTH_PX = 1000;
    public static int PREFERRED_HEIGHT_PX = 1000;
    public static int MAXIMUM_WIDTH_PX = 1600;
    public static int MAXIMUM_HEIGHT_PX = 1600;

    // main components
    private JPanel _mainContent;
    private JPanel _menuContent;
    private StatusBar _statusBarContent;
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

        setupPanels();

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
    private void setupPanels() {
        _mainContent = new JPanel();

        _menuContent = new JPanel(new FlowLayout(FlowLayout.LEFT));

        _statusBarContent = new StatusBar();

        _toolbarButtons = new JToolBar(SwingConstants.VERTICAL);

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

        _mainMenuBar.add(_fileMenu);
        _mainMenuBar.add(_viewMenu);

        _fileMenu.add(_importOption);
        _fileMenu.add(_deleteOption);
        _fileMenu.add(_quitOption);

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
        _importOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser photoPicker = new JFileChooser();
            }
        });

        _deleteOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _statusBarContent.displayNotImplemented("DELETE");
            }
        });

        _quitOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        _photoViewerOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _statusBarContent.displayNotImplemented("VIEW PHOTO");
            }
        });

        _browserOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _statusBarContent.displayNotImplemented("BROWSE");
            }
        });
    }

    /**
     * This function init listeners for the toolbar buttons
     */
    private void initToolbarListeners() {
        _peopleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _statusBarContent.displayNotImplemented("TOOLBAR");
            }
        });
        _placesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _statusBarContent.displayNotImplemented("TOOLBAR");
            }
        });
        _schoolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _statusBarContent.displayNotImplemented("TOOLBAR");
            }
        });
    }

    /**
     * This function builds the window elements with the proper hierarchy
     */
    private void buildWindow() {
        this.getContentPane().add(_menuContent, BorderLayout.NORTH);
        this.getContentPane().add(_mainContent, BorderLayout.CENTER);
        this.getContentPane().add(_statusBarContent, BorderLayout.SOUTH);
        this.getContentPane().add(_toolbarButtons, BorderLayout.WEST);
        this.pack();
    }
}
