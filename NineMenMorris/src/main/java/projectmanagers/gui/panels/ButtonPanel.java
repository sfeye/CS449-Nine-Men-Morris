package main.java.projectmanagers.gui.panels;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {

    public ButtonPanel() {
        super();
        buildPanel();
    }
    public void buildPanel() {
        setBackground(new Color(116, 101, 72));
        setPreferredSize(new Dimension(800, 50));
    }
}
