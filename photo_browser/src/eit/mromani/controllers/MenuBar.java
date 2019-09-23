package eit.mromani.controllers;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

/**
 *
 * @author BRomans
 *
 * This class will implement specific funtionalities of the Menu Bar
 */
public class MenuBar extends  JMenuBar{

    public MenuBar() {

    }

    /**
     * THis function open a dialog window that allows to load a file into the application.
     * Must be updated to avoid returning a null value if nothing is picked.
     * @return the chosen file the caller
     */
    public File loadPicture() {
        JFileChooser photoPicker = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int pickerChoice = photoPicker.showOpenDialog(null);
        File selectedFile = null;
        if (pickerChoice == JFileChooser.APPROVE_OPTION) {
            selectedFile = photoPicker.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            return selectedFile;
        } else {
            return selectedFile;
        }
    }
}
