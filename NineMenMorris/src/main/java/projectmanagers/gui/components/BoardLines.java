package main.java.projectmanagers.gui.components;

import javax.swing.*;
import java.awt.*;

public class BoardLines extends JComponent {
    private static final int TOP_VERTICAL = 0;
    private static final int LEFT_HORIZONTAL = 1;
    private static final int HORIZONTAL = 2;
    private static final int VERTICAL = 3;
    private static final int RIGHT_HORIZONTAL = 5;
    private static final int BOTTOM_VERTICAL = 6;
    private int type;

    public BoardLines (int type) {
        super();
        this.type = type;
    }

    @Override
    public void paintComponent (Graphics g) {
        switch(type) {
            case (HORIZONTAL):
                g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
                break;
            case (VERTICAL):
                g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
                break;
            case (LEFT_HORIZONTAL):
                g.drawLine(getWidth() / 2, getHeight() / 2, getWidth(), getHeight() / 2);
                break;
            case (RIGHT_HORIZONTAL):
                g.drawLine(0, getHeight() / 2, getWidth() / 2, getHeight() / 2);
                break;
            case (TOP_VERTICAL):
                g.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight());
                break;
            case (BOTTOM_VERTICAL):
                g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight() / 2);
                break;
        }
    }
}
