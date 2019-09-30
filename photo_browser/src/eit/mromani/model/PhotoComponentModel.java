package eit.mromani.model;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BRomans
 *
 * This is the model for PhotoComponent
 */
public class PhotoComponentModel {

    private ArrayList<ActionListener> _actionListeners = new ArrayList<>();
    private ArrayList<ChangeListener> _changeListeners = new ArrayList<>();
    private ArrayList<AnnotationPoint> _drawingPoints = new ArrayList<>();
    private ArrayList<AnnotationPoint> _textPoints = new ArrayList<>();

    private boolean _isFlipped;


    public void addActionListener(ActionListener listener) {
        this._actionListeners.add(listener);
    }

    public void addChangeListener(ChangeListener listener) {
        this._changeListeners.add(listener);
    }

    public void addDrawingPoint(AnnotationPoint annotationPoint) { this._drawingPoints.add(annotationPoint);}

    public void addTextPoint(AnnotationPoint annotationPoint) { this._textPoints.add(annotationPoint);}

    public List<AnnotationPoint> getDrawingPoints() { return this._drawingPoints; }

    public List<AnnotationPoint> getTextPoints() { return this._textPoints; }

    public void flipPhoto(boolean isFlipped) {
        if(isFlipped != this._isFlipped) {
            if(!isFlipped) {
                fireFlip();
            }
            this._isFlipped = isFlipped;
            fireChangeListener();
        }

    }

    private void fireChangeListener() {
        for (ChangeListener listener : _changeListeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }

    private void fireFlip() {
        for (ActionListener listener : _actionListeners) {
            listener.actionPerformed(new ActionEvent(this, (int) System.currentTimeMillis(), "flip"));
        }
    }

    public boolean isFlipped() {
        return this._isFlipped;
    }
}
