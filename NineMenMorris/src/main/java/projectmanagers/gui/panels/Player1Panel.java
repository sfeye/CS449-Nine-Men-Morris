package main.java.projectmanagers.gui.panels;
import main.java.projectmanagers.gui.components.PlayerPieces;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player1Panel extends JPanel {
    private Color bgc = new Color(116,101,72);
    private static ArrayList<PlayerPieces> pieces;
    public JLabel player1Txt;
    public static int turns = 8;
    private GridBagConstraints gbc;

    public Player1Panel () {
        pieces = new ArrayList<>(turns);
        gbc = new GridBagConstraints();
        player1Txt = new JLabel("Player 1");
        buildPanel();
    }
    public void buildPanel () {
        gbc.weighty = 1;    gbc.gridy = 0;   gbc.gridx = 0;
        setLayout(new GridBagLayout());
        setBackground(bgc);
        setPreferredSize(new Dimension(75,600));
        player1Txt.setHorizontalAlignment(SwingConstants.CENTER);
        add(player1Txt, gbc);
        trackTurns();
    }
    public void trackTurns () {
        for (int i = 0; i <= turns; i++) {
            gbc.gridy = i + 1;
            pieces.add(new PlayerPieces(Color.red, Color.black));
            add(pieces.get(i), gbc);
        }
    }
    public void decrementTurns() {
        if (turns >= 0) {
            pieces.get(turns).setBg(bgc);
            pieces.get(turns).setOL(bgc);
            turns--;
            repaint();
        }
    }
    public static boolean hasTurn() {
        if (turns >= 0)
            return true;
        return false;
    }
}

