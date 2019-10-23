package main.java.projectmanagers.gui;
import main.java.projectmanagers.gui.components.PlayerPieces;
import main.java.projectmanagers.gui.panels.*;
import main.java.projectmanagers.logic.GameStatuses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoardGui extends JFrame {
    private JPanel masterPanel;
    private GamePanel gamePanel;
    private TitlePanel titlePanel;
    private ButtonPanel buttonPanel;
    private Player1Panel player1Panel;
    private Player2Panel player2Panel;
    private boolean aTurn = true;
    private boolean isMill = false;
    private GameStatuses.PlayerPlay player1Play = GameStatuses.PlayerPlay.DESELECTED;
    private GameStatuses.PlayerPlay player2Play = GameStatuses.PlayerPlay.DESELECTED;
    private GameStatuses.GamePlay gamePlay;
    private GameStatuses.GameType gameType;

    private final int MAX_HEIGHT = 600;
    private final int MAX_WIDTH = 800;

    private GameBoardGui () {
        super();
        masterPanel = new JPanel();
        gamePanel = new GamePanel();
        titlePanel = new TitlePanel();
        buttonPanel = new ButtonPanel();
        player1Panel = new Player1Panel();
        player2Panel = new Player2Panel();
        buildBoard();
        pieceActions();
    }

    private void pieceActions () {
        boardPieceActions();
        player1PieceActions();
        player2PieceActions();
    }
    private void buildBoard() {
        masterPanel.setBackground(new Color(153,133,97));
        masterPanel.setLayout(new BorderLayout());
        masterPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        masterPanel.setPreferredSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));
        addPanels();
        buttonActions();

    }
    // Adding panels to master panel
    private void addPanels() {
        masterPanel.add(gamePanel, BorderLayout.CENTER);
        masterPanel.add(titlePanel, BorderLayout.NORTH);
        masterPanel.add(buttonPanel, BorderLayout.SOUTH);
        masterPanel.add(player1Panel, BorderLayout.WEST);
        masterPanel.add(player2Panel, BorderLayout.EAST);
        this.add(masterPanel);
    }
    // Action listeners for buttons on South button panel
    private void buttonActions() {
        JButton onePlay = new JButton("Single Player");
        JButton twoPlay = new JButton("Two Player");
        buttonPanel.add(onePlay);
        buttonPanel.add(twoPlay);
        // TODO: addActionListeners here are some examples
        onePlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "CPU doesn't exist...", "ERROR", JOptionPane.ERROR_MESSAGE);
                gameType = GameStatuses.GameType.SINGLE_PLAYER;
            }
        });
        twoPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JLabel label = new JLabel("Who goes first?");
                JRadioButton red = new JRadioButton("Player 1");
                red.setSelected(true);
                JRadioButton blue = new JRadioButton("Player 2");
                JPanel choice = new JPanel();
                ButtonGroup group = new ButtonGroup();
                group.add(red);     group.add(blue);
                choice.add(label);  choice.add(red);    choice.add(blue);
                JOptionPane.showMessageDialog(null, choice, "Two player game", JOptionPane.QUESTION_MESSAGE);
                if(red.isSelected())
                    aTurn = true;
                else
                    aTurn = false;
                showTurn();
                gameType = GameStatuses.GameType.TWO_PLAYER;
            }
        });
    }
    // Method initializes the board JFrame and sets up default GUI
    public static void start() {
        JFrame frame = new JFrame("CS 449 Project");
        frame.setResizable(true);
        frame.setContentPane(new GameBoardGui().masterPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
    // Visually displays who has the current turn
    private void showTurn () {
        if(aTurn) {
            player1Panel.player1Txt.setBorder(BorderFactory.createLineBorder(Color.yellow));
            player2Panel.player2Txt.setBorder(BorderFactory.createEmptyBorder());
            player1Panel.player1Txt.setForeground(Color.red);
            player2Panel.player2Txt.setForeground(Color.black);
            player1Panel.player1Txt.setFont(new Font("Serif", Font.BOLD, 18));
            player2Panel.player2Txt.setFont(new Font("Serif", Font.PLAIN, 18));
        }
        else {
            player1Panel.player1Txt.setBorder(BorderFactory.createEmptyBorder());
            player2Panel.player2Txt.setBorder(BorderFactory.createLineBorder(Color.yellow));
            player1Panel.player1Txt.setForeground(Color.black);
            player2Panel.player2Txt.setForeground(Color.blue);
            player1Panel.player1Txt.setFont(new Font("Serif", Font.PLAIN, 18));
            player2Panel.player2Txt.setFont(new Font("Serif", Font.BOLD, 18));
        }
    }
    // Incorporates additional board piece mouse action listeners
    private void boardPieceActions () {
        for (int i = 0; i < 24; i ++) {
            final int temp = i;
            GamePanel.boardPieces.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    gamePlay = GameStatuses.getGamePlay();
                    switch (gameType) {
                        case TWO_PLAYER:
                            switch (gamePlay) {
                                case BEGINNING:
                                    if (aTurn) {
                                        gamePanel.addPlayer1Piece(GamePanel.boardPieces.get(temp));
                                        player1Panel.decrementTurns();
                                        aTurn = !aTurn;
                                    } else {
                                        gamePanel.addPlayer2Piece(GamePanel.boardPieces.get(temp));
                                        player2Panel.decrementTurns();
                                        aTurn = !aTurn;
                                    }
                                    break;
                                case MIDDLE:
                                    if (PlayerPieces.isSelected && aTurn && gamePanel.canSlide(GamePanel.boardPieces.get(temp), gamePanel.getSelectedPlayer1Piece())) {
                                        gamePanel.swapPlayerPiece(GamePanel.boardPieces.get(temp), gamePanel.getSelectedPlayer1Piece());
                                        player1Play = GameStatuses.PlayerPlay.DESELECTED;
                                        aTurn = !aTurn;
                                    } else if (PlayerPieces.isSelected && !aTurn && gamePanel.canSlide(GamePanel.boardPieces.get(temp), gamePanel.getSelectedPlayer2Piece())) {
                                        gamePanel.swapPlayerPiece(GamePanel.boardPieces.get(temp), gamePanel.getSelectedPlayer2Piece());
                                        player1Play = GameStatuses.PlayerPlay.DESELECTED;
                                        aTurn = !aTurn;
                                    }
                                    break;
                                case END:
                                    JOptionPane.showMessageDialog(null, "Game Over");
                                    break;
                            }
                            break;
                        case SINGLE_PLAYER:
                            break;
                    }
                    showTurn();
                }
            });
        }
    }
    // Incorporates additional player piece mouse action listeners
    private void player1PieceActions () {
        for(int i = 0; i < GamePanel.player1Pieces.size(); i++) {
            final int temp = i;
            gamePanel.player1Pieces.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    switch(gamePlay) {
                        case MIDDLE:
                            switch(player1Play){
                                case MILL:
                                    player1Play = GameStatuses.PlayerPlay.DESELECTED;
                                    gamePanel.millPlayer1Remove(GamePanel.player1Pieces.get(temp));
                                    break;
                                case SELECTED:
                                    if(aTurn) {
                                        player1Play = GameStatuses.PlayerPlay.DESELECTED;
                                        gamePanel.deselectPiece();
                                    }
                                    break;
                                case DESELECTED:
                                    if(aTurn) {
                                        player1Play = GameStatuses.PlayerPlay.SELECTED;
                                        gamePanel.setSelectedPiece(GamePanel.player1Pieces.get(temp));
                                    }
                                    break;
                            }
                    }
                }
            });
        }
    }
    private void player2PieceActions () {
        for(int i = 0; i < GamePanel.player2Pieces.size(); i++) {
            final int temp = i;
            gamePanel.player2Pieces.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    switch(gamePlay) {
                        case MIDDLE:
                            switch(player2Play){
                                case MILL:
                                    player2Play = GameStatuses.PlayerPlay.DESELECTED;
                                    gamePanel.millPlayer2Remove(GamePanel.player2Pieces.get(temp));
                                    break;
                                case SELECTED:
                                    if(!aTurn) {
                                        player2Play = GameStatuses.PlayerPlay.DESELECTED;
                                        gamePanel.deselectPiece();
                                    }
                                    break;
                                case DESELECTED:
                                    if(!aTurn) {
                                        player2Play = GameStatuses.PlayerPlay.SELECTED;
                                        gamePanel.setSelectedPiece(GamePanel.player2Pieces.get(temp));
                                    }
                                    break;
                            }
                    }
                }
            });
        }
    }
}
