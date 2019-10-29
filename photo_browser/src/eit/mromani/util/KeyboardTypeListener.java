package eit.mromani.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author BRomans
 * <p>
 * This is a functional interface that allows KeyTyped events to be used with a lambda expression.
 * Since lambdas accepts one and only one function, we implement with empty {} the parent methods
 * to be skipped and we comment (or non-implement) the method that we want to recognize.
 */
public interface KeyboardTypeListener extends KeyListener
{
    /**
     * This is the method that we want to be recognized.
     * @param e the keyboard event
     *
    @Override
    default void keyTyped(KeyEvent e) {}*/

    @Override
    default void keyPressed(KeyEvent e) {}

    @Override
    default void keyReleased(KeyEvent e) {}
}