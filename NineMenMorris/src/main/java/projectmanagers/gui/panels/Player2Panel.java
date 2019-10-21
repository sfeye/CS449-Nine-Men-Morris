package main.java.projectmanagers.gui.panels;
import main.java.projectmanagers.gui.components.PlayerPieces;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static main.java.projectmanagers.trackers.PlayerTracking.BLUE_PLAYER;

public class Player2Panel extends JPanel {
    Color bgc = new Color(116,101,72);
    private ArrayList<PlayerPieces> pieces;
    public JLabel player2Txt;
    private GridBagConstraints gbc;

    public Player2Panel () {
        pieces = new ArrayList<>(BLUE_PLAYER.getTurns());
        gbc = new GridBagConstraints();
        player2Txt = new JLabel("Player 2");
        player2Txt.setFont(new Font("Serif", Font.PLAIN, 18));
        buildPanel();
    }
    public void buildPanel () {
        gbc.weighty = 1;    gbc.gridy = 0;   gbc.gridx = 0;
        setLayout(new GridBagLayout());
        setBackground(bgc);
        setPreferredSize(new Dimension(75,600));
        player2Txt.setHorizontalAlignment(SwingConstants.CENTER);
        add(player2Txt, gbc);
        trackTurns();
    }
    public void trackTurns () {
        for (int i = 0; i <= BLUE_PLAYER.getTurns(); i++) {
            gbc.gridy = i + 1;
            pieces.add(new PlayerPieces(Color.blue, Color.black));
            add(pieces.get(i), gbc);
        }
    }
    public void decrementTurns () {
        if (BLUE_PLAYER.getTurns() >= 0) {
            pieces.get(BLUE_PLAYER.getTurns()).setBg(bgc);
            pieces.get(BLUE_PLAYER.getTurns()).setOL(bgc);
            BLUE_PLAYER.decrementTurns();
            repaint();
        }
    }
    public static boolean hasTurn () {
        if (BLUE_PLAYER.getTurns() >= 0)
            return true;
        return false;
    }
}

