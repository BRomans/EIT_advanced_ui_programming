package eit.mromani.controllers;

import eit.mromani.util.MouseClickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author BRomans
 *
 * This class implements a Style Toolbar with customization tools for annotations
 */
public class StyleToolbar extends JToolBar {

    private Color _selectedColor;
    private String _selectedShape;
    private String _selectedFont;
    private int _fontSize;
    private int _strokeSize;

    private JButton _colorButton;
    private JComboBox _fontList;
    private JComboBox _fontSizeList;
    private JComboBox _strokeSizeList;
    private JComboBox _shapeChooser;
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
        setupShapeChooser();
        setupListeners();
    }

    /**
     * Setups listeners using functional interfaces
     */
    private void setupListeners() {
        this._colorButton.addMouseListener((MouseClickListener) e -> changeColor());
        this._fontList.addActionListener((ActionListener) this::changeFont);
        this._fontSizeList.addActionListener((ActionListener) this::changeFontSize);
        this._strokeSizeList.addActionListener((ActionListener) this::changeStrokeSize);
        this._shapeChooser.addActionListener((ActionListener)this::changeShape);

    }

    /**
     * Setups a color picker for text and strokes
     */
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
        _fontList.setSelectedItem("Serif");
        _selectedFont = "Serif";
        _toolsPanel.add(_fontList);
    }

    /**
     * Setups font and stroke sizes
     */
    private void setupSizeLists() {
        String[] fontSizeList = new String[20];
        String[] strokeSizeList = new String[10];
        int minFontSize = 8;
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

    /**
     * Setups a shape chooser. Currently NOT IMPLEMENTED
     */
    private void setupShapeChooser() {
        String stroke = "stroke";
        String circle = "circle";
        String square = "square";
        String[] shapes = {stroke, circle, square};
        _shapeChooser = new JComboBox(shapes);
        _shapeChooser.setSelectedItem("stroke");
        this._selectedShape = "stroke";
        //TODO add shape selector to toolbar
        // _toolsPanel.add(_shapeChooser);

    }

    /* Listeners functions */

    private void changeColor() {
        this._selectedColor = JColorChooser.showDialog(this,"Choose a background",Color.BLACK);
        this._colorButton.setBackground(_selectedColor);
    }

    private void changeFont(ActionEvent event) {
        this._selectedFont = (String) _fontList.getSelectedItem();
    }

    private void changeFontSize(ActionEvent event) {
        this._fontSize = Integer.valueOf((String) _fontSizeList.getSelectedItem());
    }

    private void changeStrokeSize(ActionEvent event) {
        this._strokeSize = Integer.valueOf((String) _strokeSizeList.getSelectedItem());
    }

    private void changeShape(ActionEvent event) {
        this._selectedShape = (String) _shapeChooser.getSelectedItem();
    }

    /* Getters */

    public Color getSelectedColor() {
        return _selectedColor;
    }

    public String getSelectedShape() {
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
