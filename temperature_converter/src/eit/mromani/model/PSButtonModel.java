package eit.mromani.model;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PSButtonModel {

    private boolean isPressed = false;
    private ArrayList<ActionListener> actionListeners = new ArrayList<>();
    private ArrayList<ChangeListener> changeListeners = new ArrayList<>();

    public void addActionListener(ActionListener listener) {
        this.actionListeners.add(listener);
    }

    public void addChangeListener(ChangeListener listener) {
        this.changeListeners.add(listener);
    }

    public void setPressed(boolean isPressed) {
        if(isPressed != this.isPressed) {
            if(!isPressed) {
                fireButton();
            }
            this.isPressed = isPressed;
            fireChangeListener();
        }

    }

    private void fireChangeListener() {
        for (ChangeListener listener : changeListeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }

    private void fireButton() {
        for (ActionListener listener : actionListeners) {
            listener.actionPerformed(new ActionEvent(this, (int) System.currentTimeMillis(), "fire"));
        }
    }
    public boolean isPressed() {
        return this.isPressed;
    }

}
