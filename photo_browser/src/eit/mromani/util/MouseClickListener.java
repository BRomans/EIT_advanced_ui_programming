package eit.mromani.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author BRomans
 * <p>
 * This is a functional interface that allows MouseClicked events to be used with a lambda expression.
 * Since lambdas accepts one and only one function, we implement with empty {} the parent methods
 * to be skipped and we comment (or non-implement) the method that we want to recognize.
 */
public interface MouseClickListener extends MouseListener
{
    /**
     * This is the method that we want to be recognized.
     * @param e the mouse event
     *
    @Override
    default void mouseClicked(MouseEvent e) {} */

    @Override
    public default void mouseEntered(MouseEvent e) {}

    @Override
    public default void mouseExited(MouseEvent e) {}

    @Override
    public default void mousePressed(MouseEvent e) {}

    @Override
    public default void mouseReleased(MouseEvent e) {}

}