package main.java.projectmanagers.gui.panels;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {
    private JLabel title;

    public TitlePanel () {
        super();
        title = new JLabel("Nine Men's Morris");
        buildPanel();
    }
    private void buildPanel () {
        setBackground(new Color(116,101,72));
        setPreferredSize(new Dimension(800, 50));
        title.setFont(new Font (title.getName(), Font.BOLD, 25));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);
    }
}
