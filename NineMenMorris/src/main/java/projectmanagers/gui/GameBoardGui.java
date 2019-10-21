package main.java.projectmanagers.gui;
import main.java.projectmanagers.gui.components.PlayerPieces;
import main.java.projectmanagers.gui.panels.*;

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
    private boolean twoPlayerGame = false;

    private int MAX_HEIGHT = 600;
    private int MAX_WIDTH = 800;

    public GameBoardGui () {
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

    public void pieceActions () {
        boardPieceActions();
        player1PieceActions();
        player2PieceActions();
    }
    public void buildBoard() {
        masterPanel.setBackground(new Color(153,133,97));
        masterPanel.setLayout(new BorderLayout());
        masterPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        masterPanel.setPreferredSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));
        addPanels();
        buttonActions();

    }
    public void addPanels() {
        masterPanel.add(gamePanel, BorderLayout.CENTER);
        masterPanel.add(titlePanel, BorderLayout.NORTH);
        masterPanel.add(buttonPanel, BorderLayout.SOUTH);
        masterPanel.add(player1Panel, BorderLayout.WEST);
        masterPanel.add(player2Panel, BorderLayout.EAST);
        this.add(masterPanel);
    }
    public void buttonActions() {
        JButton onePlay = new JButton("Single Player");
        JButton twoPlay = new JButton("Two Player");
        buttonPanel.add(onePlay);
        buttonPanel.add(twoPlay);
        // TODO: addActionListeners here are some examples
        onePlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "CPU doesn't exist...", "ERROR", JOptionPane.ERROR_MESSAGE);
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
                twoPlayerGame = true;
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
    public void showTurn () {
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
    public void boardPieceActions () {
        for (int i = 0; i < 24; i ++) {
            final int temp = i;
            GamePanel.boardPieces.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    if(twoPlayerGame && (player1Panel.hasTurn() || player2Panel.hasTurn())) {
                        if (aTurn) {
                            gamePanel.addPlayer1Piece(GamePanel.boardPieces.get(temp));
                            player1Panel.decrementTurns();
                            aTurn = !aTurn;
                        } else{
                            gamePanel.addPlayer2Piece(GamePanel.boardPieces.get(temp));
                            player2Panel.decrementTurns();
                            aTurn = !aTurn;
                        }
                    }
                    else if(twoPlayerGame && PlayerPieces.isSelected && aTurn){
                        gamePanel.swapPlayerPiece(GamePanel.boardPieces.get(temp), gamePanel.getSelectedPlayer1Piece());
                        aTurn = !aTurn;
                    }
                    else if(twoPlayerGame && PlayerPieces.isSelected && !aTurn){
                        gamePanel.swapPlayerPiece(GamePanel.boardPieces.get(temp), gamePanel.getSelectedPlayer2Piece());
                        aTurn = !aTurn;
                    }
                    showTurn();
                }
            });
        }
    }
    // Incorporates additional player piece mouse action listeners
    public void player1PieceActions () {
        for(int i = 0; i < GamePanel.player1Pieces.size(); i++) {
            final int temp = i;
            gamePanel.player1Pieces.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    if(!player1Panel.hasTurn() && !player2Panel.hasTurn()) {
                        if(isMill)
                            gamePanel.millPlayer1Remove(gamePanel.player1Pieces.get(temp));
                        else if (aTurn && !gamePanel.player1Pieces.get(temp).isSelected) {
                            gamePanel.setSelectedPiece(gamePanel.player1Pieces.get(temp));
                        }
                        else if (aTurn && gamePanel.player1Pieces.get(temp).isSelected)
                            gamePanel.deselectPiece();
                    }
                }
            });
        }
    }
    public void player2PieceActions () {
        for(int i = 0; i < GamePanel.player1Pieces.size(); i++) {
            final int temp = i;
            gamePanel.player2Pieces.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    if(!player1Panel.hasTurn() && !player2Panel.hasTurn()) {
                        if(isMill)
                            gamePanel.millPlayer2Remove(gamePanel.player2Pieces.get(temp));
                        else if (!aTurn && !gamePanel.player2Pieces.get(temp).isSelected) {
                            gamePanel.setSelectedPiece(gamePanel.player2Pieces.get(temp));
                        }
                        else if (!aTurn && gamePanel.player2Pieces.get(temp).isSelected)
                            gamePanel.deselectPiece();
                    }
                }
            });
        }
    }
}
