package eit.mromani.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface MouseDragListener extends MouseMotionListener
{

    @Override
    default void mouseMoved(MouseEvent e) {}
}