package eit.mromani.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * @author BRomans
 * <p>
 * This is a functional interface that allows MouseDragged events to be used with a lambda expression.
 * Since lambdas accepts one and only one function, we implement with empty {} the parent methods
 * to be skipped and we comment (or non-implement) the method that we want to recognize.
 */
public interface MouseDragListener extends MouseMotionListener
{

    /**
     * This is the method that we want to be recognized.
     * @param e the mouse event
     *
    @Override
    default void mouseDragged(MouseEvent e) {}*/

    @Override
    default void mouseMoved(MouseEvent e) {}
}