package eit.mromani.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface MousePressListener extends MouseListener
{
    @Override
    public default void mouseEntered(MouseEvent e) {}

    @Override
    public default void mouseExited(MouseEvent e) {}

    @Override
    public default void mouseClicked(MouseEvent e) {}

    @Override
    public default void mouseReleased(MouseEvent e) {}

}