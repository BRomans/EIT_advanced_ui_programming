package eit.mromani.controllers;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author BRomans
 * <p>
 * This class implements a JPanel  with status bar functionalities
 */
public class StatusBar extends JPanel {

    private JLabel _statusLabel;
    private String _statusPlaceholder = "#################";
    private boolean _timerOver;
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public StatusBar() {
        initFields();
    }

    private void initFields() {
        _statusLabel = new JLabel(_statusPlaceholder);
        this.add(_statusLabel, BorderLayout.CENTER);
    }

    /**
     * This function prepares a non-implemented message
     *
     * @param componentName contains the name of the component to be implemented
     */
    public void displayNotImplemented(String componentName) {
        String message = componentName + " has not been implemented yet!";
        showStatusMessage(message);
    }


    /**
     * This function displays a message in the status bar for 5 seconds
     *
     * @param message the text to be displayed
     */
    public void showStatusMessage(String message) {
        _statusLabel.setText(message);

        ScheduledFuture timer = scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("Clear status message");
                _statusLabel.setText(_statusPlaceholder);
                _timerOver = true;
            }
        }, 10, TimeUnit.SECONDS);


    }
}


