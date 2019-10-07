package eit.mromani.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface KeyboardTypeListener extends KeyListener
{
    @Override
    default void keyPressed(KeyEvent e) {}

    @Override
    default void keyReleased(KeyEvent e) {}
}