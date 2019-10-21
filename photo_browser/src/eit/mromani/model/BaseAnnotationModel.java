package eit.mromani.model;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public abstract class BaseAnnotationModel implements AnnotationModel {

    private ArrayList<ChangeListener> _changeListeners = new ArrayList<>();

    public void addChangeListener(ChangeListener listener) {
        this._changeListeners.add(listener);
    }

    public void removeChangeListener(ChangeListener listener) {
        this._changeListeners.remove(listener);
    }

    private void fireChangeListener() {
        for (ChangeListener listener : _changeListeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }

}
