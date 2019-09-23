package eit.mromani.model;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author BRomans
 *
 * This is the model for PhotoComponent
 */
public class PhotoComponentModel {

    private ArrayList<ActionListener> actionListeners = new ArrayList<>();
    private ArrayList<ChangeListener> changeListeners = new ArrayList<>();
    private boolean isFlipped;


    public void addActionListener(ActionListener listener) {
        this.actionListeners.add(listener);
    }

    public void addChangeListener(ChangeListener listener) {
        this.changeListeners.add(listener);
    }

    public void flipPhoto(boolean isFlipped) {
        if(isFlipped != this.isFlipped) {
            if(!isFlipped) {
                //fireButton();
            }
            this.isFlipped = isFlipped;
            fireChangeListener();
        }

    }

    private void fireChangeListener() {
        for (ChangeListener listener : changeListeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }

    public boolean isFlipped() {
        return this.isFlipped;
    }
}
