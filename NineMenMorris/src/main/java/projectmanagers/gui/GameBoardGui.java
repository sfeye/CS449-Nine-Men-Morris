package main.java.projectmanagers.gui;
import main.java.projectmanagers.gui.panels.*;
import main.java.projectmanagers.logic.Board;
import main.java.projectmanagers.logic.GameStatuses;
import static main.java.projectmanagers.trackers.PlayerTracking.BLUE_PLAYER;
import static main.java.projectmanagers.trackers.PlayerTracking.RED_PLAYER;

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
    public static boolean P1hasMill = false;
    public static boolean P2hasMill = false;
    private GameStatuses.PlayerPlay player1Play = GameStatuses.PlayerPlay.DESELECTED;
    private GameStatuses.PlayerPlay player2Play = GameStatuses.PlayerPlay.DESELECTED;
    private GameStatuses.GamePlay gamePlay = GameStatuses.getGamePlay();
    private GameStatuses.GameType gameType = GameStatuses.GameType.MENU;

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
        JButton reset = new JButton("Reset");
        reset.setEnabled(false);
        buttonPanel.add(onePlay);
        buttonPanel.add(twoPlay);
        //buttonPanel.add(reset);
        onePlay.addActionListener(actionEvent -> {
            JOptionPane.showMessageDialog(null, "CPU doesn't exist...", "ERROR", JOptionPane.ERROR_MESSAGE);
            gameType = GameStatuses.GameType.SINGLE_PLAYER;
            onePlay.setEnabled(false);
            twoPlay.setEnabled(true);
        });
        twoPlay.addActionListener(actionEvent -> {
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
                GameStatuses.turn = GameStatuses.TurnsEnum.PLAYER1;
            else
                GameStatuses.turn = GameStatuses.TurnsEnum.PLAYER2;
            showTurn();
            gameType = GameStatuses.GameType.TWO_PLAYER;
            onePlay.setEnabled(false);
            twoPlay.setEnabled(false);
            reset.setEnabled(true);
        });
        reset.addActionListener(actionEvent -> {
            dispose();
            start();
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
        if(GameStatuses.turn.equals(GameStatuses.TurnsEnum.PLAYER1)) {
            player1Panel.player1Txt.setBorder(BorderFactory.createLineBorder(Color.yellow));
            player2Panel.player2Txt.setBorder(BorderFactory.createEmptyBorder());
            player1Panel.player1Txt.setForeground(Color.red);
            player2Panel.player2Txt.setForeground(Color.black);
            player1Panel.player1Txt.setFont(new Font("Serif", Font.BOLD, 18));
            player2Panel.player2Txt.setFont(new Font("Serif", Font.PLAIN, 18));
        }
        else if (GameStatuses.turn.equals(GameStatuses.TurnsEnum.PLAYER2)){
            player1Panel.player1Txt.setBorder(BorderFactory.createEmptyBorder());
            player2Panel.player2Txt.setBorder(BorderFactory.createLineBorder(Color.yellow));
            player1Panel.player1Txt.setForeground(Color.black);
            player2Panel.player2Txt.setForeground(Color.blue);
            player1Panel.player1Txt.setFont(new Font("Serif", Font.PLAIN, 18));
            player2Panel.player2Txt.setFont(new Font("Serif", Font.BOLD, 18));
        }
        if(GameStatuses.getGamePlay() == GameStatuses.GamePlay.END){
            switch (GameStatuses.turn) {
                case PLAYER1:
                    JOptionPane.showMessageDialog(null, "Player 2 Wins, Game Over");
                    break;
                case PLAYER2:
                    JOptionPane.showMessageDialog(null, "Player 1 Wins, Game Over");
                    break;
            }
        }
    }
    // Incorporates additional board piece mouse action listeners
    private void boardPieceActions () {
        for (int i = 0; i < GamePanel.boardPieces.size(); i ++) {
            final int temp = i;
            GamePanel.boardPieces.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    gamePlay = GameStatuses.getGamePlay();
                    if(!P1hasMill && !P2hasMill) {
                        switch (gameType) {
                            case TWO_PLAYER:
                                switch (gamePlay) {
                                    case BEGINNING:
                                        switch (GameStatuses.turn) {
                                            case PLAYER1:
                                                gamePanel.addPlayer1Piece(GamePanel.boardPieces.get(temp));
                                                player1Panel.decrementTurns();
                                                if(!(Board.isPositionMilled(GamePanel.boardPieces.get(temp).getXCoordinate(), GamePanel.boardPieces.get(temp).getYCoordinate())))
                                                    GameStatuses.changeTurn();
                                                break;
                                            case PLAYER2:
                                                gamePanel.addPlayer2Piece(GamePanel.boardPieces.get(temp));
                                                player2Panel.decrementTurns();
                                                if(!(Board.isPositionMilled(GamePanel.boardPieces.get(temp).getXCoordinate(), GamePanel.boardPieces.get(temp).getYCoordinate())))
                                                    GameStatuses.changeTurn();
                                                break;
                                        }
                                        break;
                                    case MIDDLE:
                                        switch (GameStatuses.turn) {
                                            case PLAYER1:
                                                //FLY
                                                if (player1Play.equals(GameStatuses.PlayerPlay.SELECTED) && RED_PLAYER.getPieces() == 3) {
                                                    gamePanel.swapPlayerPiece(GamePanel.boardPieces.get(temp), gamePanel.getSelectedPlayer1Piece());
                                                    if(Board.isPositionMilled(gamePanel.getSelectedPlayer1Piece().getXCoordinate(), gamePanel.getSelectedPlayer1Piece().getYCoordinate())) {
                                                        player2Play = GameStatuses.PlayerPlay.MILLABLE;
                                                        P1hasMill = true;
                                                    }
                                                    player1Play = GameStatuses.PlayerPlay.DESELECTED;
                                                    if(!P1hasMill)
                                                        GameStatuses.changeTurn();
                                                }
                                                //SLIDE
                                                else if (player1Play.equals(GameStatuses.PlayerPlay.SELECTED) && gamePanel.canSlide(GamePanel.boardPieces.get(temp), gamePanel.getSelectedPlayer1Piece())) {
                                                    gamePanel.swapPlayerPiece(GamePanel.boardPieces.get(temp), gamePanel.getSelectedPlayer1Piece());
                                                    if(Board.isPositionMilled(gamePanel.getSelectedPlayer1Piece().getXCoordinate(), gamePanel.getSelectedPlayer1Piece().getYCoordinate())) {
                                                        player2Play = GameStatuses.PlayerPlay.MILLABLE;
                                                        P1hasMill = true;
                                                    }
                                                    player1Play = GameStatuses.PlayerPlay.DESELECTED;
                                                    if(!P1hasMill)
                                                        GameStatuses.changeTurn();
                                                }
                                                break;
                                            case PLAYER2:
                                                //FLY
                                                if (player2Play.equals(GameStatuses.PlayerPlay.SELECTED) && BLUE_PLAYER.getPieces() == 3) {
                                                    gamePanel.swapPlayerPiece(GamePanel.boardPieces.get(temp), gamePanel.getSelectedPlayer2Piece());
                                                    player2Play = GameStatuses.PlayerPlay.DESELECTED;
                                                    if(Board.isPositionMilled(gamePanel.getSelectedPlayer2Piece().getXCoordinate(), gamePanel.getSelectedPlayer2Piece().getYCoordinate())) {
                                                        player1Play = GameStatuses.PlayerPlay.MILLABLE;
                                                        P2hasMill = true;
                                                    }
                                                    player2Play = GameStatuses.PlayerPlay.DESELECTED;
                                                    if(!P2hasMill)
                                                        GameStatuses.changeTurn();
                                                }
                                                //SLIDE
                                                else if (player2Play.equals(GameStatuses.PlayerPlay.SELECTED) && gamePanel.canSlide(GamePanel.boardPieces.get(temp), gamePanel.getSelectedPlayer2Piece())) {
                                                    gamePanel.swapPlayerPiece(GamePanel.boardPieces.get(temp), gamePanel.getSelectedPlayer2Piece());
                                                    if(Board.isPositionMilled(gamePanel.getSelectedPlayer2Piece().getXCoordinate(), gamePanel.getSelectedPlayer2Piece().getYCoordinate())) {
                                                        player1Play = GameStatuses.PlayerPlay.MILLABLE;
                                                        P2hasMill = true;
                                                    }
                                                    player2Play = GameStatuses.PlayerPlay.DESELECTED;
                                                    if(!P2hasMill)
                                                        GameStatuses.changeTurn();
                                                }
                                                break;
                                        }
                                        break;
                                }
                            case SINGLE_PLAYER:
                                break;
                        }
                    }
                    if(Board.isPositionMilled(GamePanel.boardPieces.get(temp).getXCoordinate(), GamePanel.boardPieces.get(temp).getYCoordinate())) {
                        if(GameStatuses.turn.equals(GameStatuses.TurnsEnum.PLAYER1)) {
                            player2Play = GameStatuses.PlayerPlay.MILLABLE;
                            P1hasMill = true;
                        }
                        else{
                            player1Play = GameStatuses.PlayerPlay.MILLABLE;
                            P2hasMill = true;
                        }
                    }
                    showTurn();
                    gamePanel.showMills();
                }
            });
        }
    }
    // Incorporates additional player piece mouse action listeners
    private void player1PieceActions () {
        for(int i = 0; i < GamePanel.player1Pieces.size(); i++) {
            final int temp = i;
            GamePanel.player1Pieces.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    gamePlay = GameStatuses.getGamePlay();
                    switch (gamePlay) {
                        case BEGINNING:
                            if(player1Play.equals(GameStatuses.PlayerPlay.MILLABLE) && GamePanel.canMill(GamePanel.player1Pieces.get(temp))) {
                                player1Play = GameStatuses.PlayerPlay.DESELECTED;
                                gamePanel.millPlayer1Remove(GamePanel.player1Pieces.get(temp));
                                GameStatuses.changeTurn();
                                P2hasMill = false;
                                break;
                            }
                            break;
                        case MIDDLE:
                            switch(player1Play){
                                case MILLABLE:
                                    if(GamePanel.canMill(GamePanel.player1Pieces.get(temp))) {
                                        player1Play = GameStatuses.PlayerPlay.DESELECTED;
                                        gamePanel.millPlayer1Remove(GamePanel.player1Pieces.get(temp));
                                        GameStatuses.changeTurn();
                                        P2hasMill = false;
                                    }
                                    break;
                                case SELECTED:
                                    if(GameStatuses.turn.equals(GameStatuses.TurnsEnum.PLAYER1)) {
                                        player1Play = GameStatuses.PlayerPlay.DESELECTED;
                                        gamePanel.deselectPiece();
                                    }
                                    break;
                                case DESELECTED:
                                    if(GameStatuses.turn.equals(GameStatuses.TurnsEnum.PLAYER1)) {
                                        player1Play = GameStatuses.PlayerPlay.SELECTED;
                                        gamePanel.setSelectedPiece(GamePanel.player1Pieces.get(temp));
                                    }
                                    break;
                            }
                    }
                    showTurn();
                    gamePanel.showMills();
                }
            });
        }
    }
    private void player2PieceActions () {
        for(int i = 0; i < GamePanel.player2Pieces.size(); i++) {
            final int temp = i;
            GamePanel.player2Pieces.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    gamePlay = GameStatuses.getGamePlay();
                    switch (gamePlay) {
                        case BEGINNING:
                            if(player2Play.equals(GameStatuses.PlayerPlay.MILLABLE) && GamePanel.canMill(GamePanel.player2Pieces.get(temp))) {
                                player2Play = GameStatuses.PlayerPlay.DESELECTED;
                                gamePanel.millPlayer2Remove(GamePanel.player2Pieces.get(temp));
                                GameStatuses.changeTurn();
                                P1hasMill = false;
                                break;
                            }
                            break;
                        case MIDDLE:
                            switch(player2Play){
                                case MILLABLE:
                                    if(GamePanel.canMill(GamePanel.player2Pieces.get(temp))) {
                                        player2Play = GameStatuses.PlayerPlay.DESELECTED;
                                        gamePanel.millPlayer2Remove(GamePanel.player2Pieces.get(temp));
                                        GameStatuses.changeTurn();
                                        P1hasMill = false;
                                    }
                                    break;
                                case SELECTED:
                                    if(GameStatuses.turn.equals(GameStatuses.TurnsEnum.PLAYER2)) {
                                        player2Play = GameStatuses.PlayerPlay.DESELECTED;
                                        gamePanel.deselectPiece();
                                    }
                                    break;
                                case DESELECTED:
                                    if(GameStatuses.turn.equals(GameStatuses.TurnsEnum.PLAYER2)) {
                                        player2Play = GameStatuses.PlayerPlay.SELECTED;
                                        gamePanel.setSelectedPiece(GamePanel.player2Pieces.get(temp));
                                    }
                                    break;
                            }
                        }
                    showTurn();
                    gamePanel.showMills();
                }
            });
        }
    }
}
