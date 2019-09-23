package eit.mromani;

import eit.mromani.model.PSButtonModel;
import eit.mromani.views.PSButtonView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PSButton extends JComponent {

    PSButtonModel model;
    PSButtonView view;

    public PSButton(String label) {
        setModel(new PSButtonModel());
        setView(new PSButtonView(label, this));
    }

    private void setModel(PSButtonModel model) {
        this.model = model;
        model.addActionListener(event -> repaint());
    }

    private void setView(PSButtonView view) {
        this.view = view;
    }

    public PSButtonModel getModel() {
        return this.model;
    }

    public void press() {
        model.setPressed(true);
    }

    public void release() {
        model.setPressed(false);
    }

    public void addActionListener(ActionListener listener) {
        model.addActionListener(listener);
    }

    @Override
    public Dimension getPreferredSize() {
        return view.getPreferredSize();
    }

    @Override
    public void paintComponent(Graphics g) {
        view.paint(g, this);
    }
}
