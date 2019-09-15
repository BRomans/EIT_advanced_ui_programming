package eit.mromani.ui;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class StatusBar extends JPanel {

    private JLabel _statusLabel;
    private JLabel _statusBarMessage;
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public StatusBar() {
    }

    private void initFields() {
        _statusLabel = new JLabel("#################");
        this.add(_statusLabel, BorderLayout.CENTER);
    }

    /**
     * This function prepares a non-implemented message
     *
     * @param componentName
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
                showStatusMessage("");
            }
        }, 5, TimeUnit.SECONDS);

        while (!timer.isDone()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }

        }
    }
}


