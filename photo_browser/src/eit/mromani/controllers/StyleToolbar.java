package eit.mromani.controllers;

import eit.mromani.util.MouseClickListener;

import javax.swing.*;
import java.awt.*;

public class StyleToolbar extends JToolBar {

    private Color _selectedColor;
    private Shape _selectedShape;
    private Font _selectedFont;
    private int _fontSize;
    private int _strokeSize;

    private JButton _colorButton;
    private JList _fontList;
    private JList _fontSizeList;
    private JList _strokeSizeList;
    private JRadioButton _shapeChooser;


    public StyleToolbar(int orientation) {
        super(orientation);
        setupColorButton();
        setupListeners();
    }

    private void setupInterface() {

    }

    private void setupListeners() {
        this._colorButton.addMouseListener((MouseClickListener) e -> changeColor());

    }

    private void setupColorButton() {
        this._selectedColor = Color.black;
        this._colorButton = new JButton();
        this._colorButton.setBackground(_selectedColor);
        this.add(_colorButton);
    }

    private void setupFontList() {

    }

    private void setupSizeLists() {

    }

    private void setupShapeChooser() {

    }

    private void changeColor() {
        this._selectedColor = JColorChooser.showDialog(this,"Choose a background",Color.BLACK);
        this._colorButton.setBackground(_selectedColor);
    }
}
