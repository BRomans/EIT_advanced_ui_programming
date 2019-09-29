package eit.mromani;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame {

    private double _currentTemp;
    private JTextField _tempField;
    JTextField _finalTemperatureField;
    private JLabel _tempLabel;
    private JPanel _tempPanel;

    public TemperatureConverter() {
        super("Temperature Converter");
        this.setPreferredSize(new Dimension(1500, 1500));

        setupUI();

    }

    private void setupUI() {
        _tempPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        JPanel finalTempPanel = new JPanel();

        _tempLabel = new JLabel("Temperature: ");
        _tempField = new JTextField(String.valueOf(_currentTemp));
        _finalTemperatureField = new JTextField();
        _tempField.setPreferredSize(new Dimension (100, 20));
        _finalTemperatureField.setPreferredSize(new Dimension (100, 20));

        JLabel finalTemperature = new JLabel("Final Temperature: ");
        _finalTemperatureField = new JTextField();
        JButton convertToFarheneitButton = new JButton("Convert to F");
        JButton convertToCelsiusButton = new JButton("Convert to C");

        _tempPanel.add(_tempLabel);
        _tempPanel.add(_tempField, BorderLayout.CENTER);

        buttonsPanel.add(convertToCelsiusButton, BorderLayout.NORTH);
        buttonsPanel.add(convertToFarheneitButton, BorderLayout.SOUTH);

        finalTempPanel.add(finalTemperature);
        finalTemperature.add(_finalTemperatureField, BorderLayout.CENTER);


        convertToFarheneitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _finalTemperatureField.setText(String.valueOf(
                        convertTemperatureToFarheneit(_currentTemp)));
            }
        });

        convertToCelsiusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _finalTemperatureField.setText(String.valueOf(
                        convertTemperatureToCelsius(_currentTemp)));
            }
        });

        this.getContentPane().add(_tempPanel, BorderLayout.NORTH);
        this.getContentPane().add(finalTempPanel, BorderLayout.CENTER);
        this.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        this.pack();
    }

    private double convertTemperatureToCelsius(double temperature) {
        double celsiusTemp = (temperature - 32) * (5.0/9.0);
        return celsiusTemp;
    }

    private double convertTemperatureToFarheneit(double temperature) {
        double farheneitTemp = (temperature * (9.0/5.0)) + 32;
        return farheneitTemp;
    }

}
