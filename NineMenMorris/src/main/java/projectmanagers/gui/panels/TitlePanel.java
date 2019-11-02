package main.java.projectmanagers.gui.panels;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {
    private JLabel title;
    public JLabel rAlert;
    public JLabel lAlert;

    public TitlePanel () {
        super();
        title = new JLabel("Nine Men's Morris");
        rAlert = new JLabel("                          ");
        lAlert = new JLabel("                          ");
        buildPanel();
    }
    // sets up the title panel
    private void buildPanel () {
        setLayout(new BorderLayout());
        setBackground(new Color(116,101,72));
        setPreferredSize(new Dimension(800, 50));
        title.setFont(new Font (title.getName(), Font.BOLD, 25));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.CENTER);
        add(rAlert, BorderLayout.EAST);
        add(lAlert, BorderLayout.WEST);
    }
}
