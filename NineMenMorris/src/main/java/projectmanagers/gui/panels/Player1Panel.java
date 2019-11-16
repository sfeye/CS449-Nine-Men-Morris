package main.java.projectmanagers.gui.panels;
import main.java.projectmanagers.gui.components.PlayerPieces;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static main.java.projectmanagers.trackers.PlayerTracking.RED_PLAYER;

public class Player1Panel extends JPanel {
    private Color bgc = new Color(116,101,72);
    private static ArrayList<PlayerPieces> pieces;
    public JLabel player1Txt;
    private GridBagConstraints gbc;

    public Player1Panel () {
        pieces = new ArrayList<>(RED_PLAYER.getTurns());
        gbc = new GridBagConstraints();
        player1Txt = new JLabel(" Player 1 ");
        player1Txt.setFont(new Font("Serif", Font.PLAIN, 18));
        buildPanel();
    }
    // setups the side panels to show number of pieces to place and title of player number
    private void buildPanel () {
        gbc.weighty = 1;    gbc.gridy = 0;   gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        setLayout(new GridBagLayout());
        setBackground(bgc);
        setPreferredSize(new Dimension(80,600));
        player1Txt.setHorizontalAlignment(SwingConstants.CENTER);
        add(player1Txt, gbc);
        trackTurns();
    }
    // keeps track of the number of player pieces they can place still
    private void trackTurns () {
        for (int i = 0; i <= RED_PLAYER.getTurns(); i++) {
            gbc.gridy = i + 1;
            pieces.add(new PlayerPieces(Color.red, Color.black, false));
            add(pieces.get(i), gbc);
        }
    }
    // decrements the number of turns that a player can place
    public void decrementTurns() {
        if (RED_PLAYER.getTurns() >= 0) {
            pieces.get(RED_PLAYER.getTurns()).setBg(bgc);
            pieces.get(RED_PLAYER.getTurns()).setOL(bgc);
            RED_PLAYER.decrementTurns();
            repaint();
        }
    }
    // checks to see if a place has any remaining pieces to place
    public static boolean hasTurn() {
        return RED_PLAYER.getTurns() >= 0;
    }
    public void reset() {
        for(PlayerPieces piece : pieces) {
            piece.setBg(Color.red);
            piece.setOL(Color.black);
        }
        revalidate();
        repaint();
    }
}

