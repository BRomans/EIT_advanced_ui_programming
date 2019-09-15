package eit.mromani.ui;

import javax.swing.*;

public class MenuBar extends  JMenuBar{

    private JMenu _fileMenu;
    private JMenuItem _importOption;
    private JMenuItem _deleteOption;
    private JMenuItem _quitOption;

    private JMenu _viewMenu;
    private JMenuItem _photoViewerOption;
    private JMenuItem _browserOption;

    public MenuBar() {
        initFields();
        buildMenuBar();
    }

    private void initFields() {
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
        this.add(_fileMenu);
        this.add(_viewMenu);

        _fileMenu.add(_importOption);
        _fileMenu.add(_deleteOption);
        _fileMenu.add(_quitOption);

        _viewMenu.add(_photoViewerOption);
        _viewMenu.add(_browserOption);

    }
}
