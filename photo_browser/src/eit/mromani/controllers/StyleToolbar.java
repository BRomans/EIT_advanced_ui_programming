package eit.mromani.controllers;

import eit.mromani.util.MouseClickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class StyleToolbar extends JToolBar {

    private Color _selectedColor;
    private Shape _selectedShape;
    private String _selectedFont;
    private int _fontSize;
    private int _strokeSize;

    private JButton _colorButton;
    private JComboBox _fontList;
    private JComboBox _fontSizeList;
    private JComboBox _strokeSizeList;
    private JRadioButton _shapeChooser;
    private JPanel _toolsPanel;


    public StyleToolbar(int orientation) {
        super(orientation);
        this.setMaximumSize(new Dimension(50, 100));
        setupInterface();
    }

    private void setupInterface() {
        _toolsPanel = new JPanel();
        this.add(_toolsPanel);
        setupColorButton();
        setupFontList();
        setupSizeLists();
        setupListeners();
    }

    private void setupListeners() {
        this._colorButton.addMouseListener((MouseClickListener) e -> changeColor());
        this._fontList.addMouseListener((MouseClickListener) this::changeFont);
        this._fontSizeList.addMouseListener((MouseClickListener) this::changeFontSize);
        this._strokeSizeList.addMouseListener((MouseClickListener) this::changeStrokeSize);

    }

    private void setupColorButton() {
        this._selectedColor = Color.black;
        this._colorButton = new JButton();
        this._colorButton.setBackground(_selectedColor);
        _toolsPanel.add(_colorButton);
    }

    private void setupFontList() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] allFonts = ge.getAllFonts();
        String[] fontNames = new String[allFonts.length];
        for(int i=0; i<fontNames.length; i++) {
            fontNames[i] = allFonts[i].getName();
        }
        _fontList = new JComboBox(fontNames);
        _selectedFont = (String)_fontList.getSelectedItem();
        _toolsPanel.add(_fontList);
    }

    private void setupSizeLists() {
        String[] fontSizeList = new String[20];
        String[] strokeSizeList = new String[10];
        int minFontSize = 6;
        for(int i=0; i<fontSizeList.length; i++) {
            fontSizeList[i] = String.valueOf(minFontSize + 2);
            minFontSize += 2;
        }
        for(int i=0; i<strokeSizeList.length; i++) {
            strokeSizeList[i] = String.valueOf(i+1);
        }
        JLabel fontLabel = new JLabel("Font");
        JLabel strokeLabel = new JLabel("Stroke");
        _fontSizeList = new JComboBox(fontSizeList);
        _strokeSizeList = new JComboBox(strokeSizeList);
        _fontSize = Integer.valueOf((String)_fontSizeList.getSelectedItem());
        _strokeSize = Integer.valueOf((String)_strokeSizeList.getSelectedItem());
        _toolsPanel.add(fontLabel);
        _toolsPanel.add(_fontSizeList);
        _toolsPanel.add(strokeLabel);
        _toolsPanel.add(_strokeSizeList);

    }

    private void setupShapeChooser() {

    }

    private void changeColor() {
        this._selectedColor = JColorChooser.showDialog(this,"Choose a background",Color.BLACK);
        this._colorButton.setBackground(_selectedColor);
    }

    private void changeFont(MouseEvent event) {
        JComboBox source = (JComboBox) event.getSource();
        this._selectedFont = (String) source.getSelectedItem();
    }

    private void changeFontSize(MouseEvent event) {
        JComboBox source = (JComboBox) event.getSource();
        this._fontSize = Integer.valueOf((String) source.getSelectedItem());
    }

    private void changeStrokeSize(MouseEvent event) {
        JComboBox source = (JComboBox) event.getSource();
        this._strokeSize = Integer.valueOf((String) source.getSelectedItem());
    }

    /* Getters */

    public Color getSelectedColor() {
        return _selectedColor;
    }

    public Shape getSelectedShape() {
        return _selectedShape;
    }

    public String getSelectedFont() {
        return _selectedFont;
    }

    public int getFontSize() {
        return _fontSize;
    }

    public int getStrokeSize() {
        return _strokeSize;
    }
}
