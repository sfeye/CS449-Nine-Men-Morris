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
        pieceActions();

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
        buttonPanel.add(reset);
        onePlay.addActionListener(actionEvent -> {
            JLabel label = new JLabel("Select difficulty: ");
            JRadioButton easy = new JRadioButton("Easy");
            easy.setSelected(true);
            JRadioButton medium = new JRadioButton("Medium");
            JRadioButton hard = new JRadioButton("Hard");
            JPanel choice = new JPanel();
            ButtonGroup group = new ButtonGroup();
            group.add(easy);     group.add(medium);     group.add(hard);
            choice.add(label);  choice.add(easy);    choice.add(medium);    choice.add(hard);
            JOptionPane.showMessageDialog(null, choice, "Difficulty", JOptionPane.QUESTION_MESSAGE);
            gameType = GameStatuses.GameType.SINGLE_PLAYER;
            onePlay.setEnabled(false);
            twoPlay.setEnabled(false);
            reset.setEnabled(true);
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
            Board.reset();
            gamePanel.resetPanel();
            player1Panel.reset();
            player2Panel.reset();
            pieceActions();
            gameType = GameStatuses.GameType.MENU;
            GameStatuses.turn = GameStatuses.TurnsEnum.MENU;
            showTurn();
            onePlay.setEnabled(true);
            twoPlay.setEnabled(true);
            reset.setEnabled(false);
            revalidate();
            repaint();
        });
    }
    // alerting messages, only alerts for mills at the moment. possibility for tutorial version
    public void alertMessages() {
        if(player2Play.equals(GameStatuses.PlayerPlay.MILLABLE))
            titlePanel.lAlert.setText("   Player 1 has a mill!");
        else if(player1Play.equals(GameStatuses.PlayerPlay.MILLABLE))
            titlePanel.rAlert.setText("Player 2 has a mill!   ");
        else {
            titlePanel.lAlert.setText("                          ");
            titlePanel.rAlert.setText("                          ");
        }
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
        switch (GameStatuses.turn) {
            case PLAYER1:
                player1Panel.player1Txt.setBorder(BorderFactory.createLineBorder(Color.yellow));
                player2Panel.player2Txt.setBorder(BorderFactory.createEmptyBorder());
                player1Panel.player1Txt.setForeground(Color.red);
                player2Panel.player2Txt.setForeground(Color.black);
                player1Panel.player1Txt.setFont(new Font("Serif", Font.BOLD, 18));
                player2Panel.player2Txt.setFont(new Font("Serif", Font.PLAIN, 18));
                break;
            case PLAYER2:
                player1Panel.player1Txt.setBorder(BorderFactory.createEmptyBorder());
                player2Panel.player2Txt.setBorder(BorderFactory.createLineBorder(Color.yellow));
                player1Panel.player1Txt.setForeground(Color.black);
                player2Panel.player2Txt.setForeground(Color.blue);
                player1Panel.player1Txt.setFont(new Font("Serif", Font.PLAIN, 18));
                player2Panel.player2Txt.setFont(new Font("Serif", Font.BOLD, 18));
                break;
            default:
                player1Panel.player1Txt.setBorder(BorderFactory.createEmptyBorder());
                player2Panel.player2Txt.setBorder(BorderFactory.createEmptyBorder());
                player1Panel.player1Txt.setForeground(Color.black);
                player2Panel.player2Txt.setForeground(Color.black);
                player1Panel.player1Txt.setFont(new Font("Serif", Font.PLAIN, 18));
                player2Panel.player2Txt.setFont(new Font("Serif", Font.PLAIN, 18));
                break;
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
                    int x = GamePanel.boardPieces.get(temp).getXCoordinate();
                    int y = GamePanel.boardPieces.get(temp).getYCoordinate();
                    if(!P1hasMill && !P2hasMill) {
                        switch (gameType) {
                            case TWO_PLAYER:
                                switch (gamePlay) {
                                    case BEGINNING:
                                        switch (GameStatuses.turn) {
                                            case PLAYER1:
                                                gamePanel.addPlayer1Piece(GamePanel.boardPieces.get(temp));
                                                player1Panel.decrementTurns();
                                                if(!(Board.isPositionMilled(x, y)))
                                                    GameStatuses.changeTurn();
                                                gamePanel.showMills();
                                                break;
                                            case PLAYER2:
                                                gamePanel.addPlayer2Piece(GamePanel.boardPieces.get(temp));
                                                player2Panel.decrementTurns();
                                                if(!(Board.isPositionMilled(x, y)))
                                                    GameStatuses.changeTurn();
                                                gamePanel.showMills();
                                                break;
                                        }
                                        break;
                                    case MIDDLE:
                                        switch (GameStatuses.turn) {
                                            case PLAYER1:
                                                if (player1Play.equals(GameStatuses.PlayerPlay.SELECTED) && (gamePanel.canSlide(GamePanel.boardPieces.get(temp), gamePanel.getSelectedPiece()) || RED_PLAYER.getPieces() == 3)) {
                                                    gamePanel.swapPlayerPiece(GamePanel.boardPieces.get(temp), gamePanel.getSelectedPiece());
                                                    if(Board.isPositionMilled(gamePanel.getSelectedPiece().getXCoordinate(), gamePanel.getSelectedPiece().getYCoordinate())) {
                                                        player2Play = GameStatuses.PlayerPlay.MILLABLE;
                                                        P1hasMill = true;
                                                        gamePanel.showMills();
                                                    }
                                                    player1Play = GameStatuses.PlayerPlay.DESELECTED;
                                                    if(!P1hasMill)
                                                        GameStatuses.changeTurn();
                                                }
                                                break;
                                            case PLAYER2:
                                                if (player2Play.equals(GameStatuses.PlayerPlay.SELECTED) && (gamePanel.canSlide(GamePanel.boardPieces.get(temp), gamePanel.getSelectedPiece()) || BLUE_PLAYER.getPieces() == 3)) {
                                                    gamePanel.swapPlayerPiece(GamePanel.boardPieces.get(temp), gamePanel.getSelectedPiece());
                                                    if(Board.isPositionMilled(gamePanel.getSelectedPiece().getXCoordinate(), gamePanel.getSelectedPiece().getYCoordinate())) {
                                                        player1Play = GameStatuses.PlayerPlay.MILLABLE;
                                                        P2hasMill = true;
                                                        gamePanel.showMills();
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
                    if(Board.isPositionMilled(x, y)) {
                        if(GameStatuses.turn.equals(GameStatuses.TurnsEnum.PLAYER1)) {
                            player2Play = GameStatuses.PlayerPlay.MILLABLE;
                            P1hasMill = true;
                        }
                        else {
                            player1Play = GameStatuses.PlayerPlay.MILLABLE;
                            P2hasMill = true;
                        }
                    }
                    showTurn();
                    alertMessages();
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
                            if(player1Play.equals(GameStatuses.PlayerPlay.MILLABLE) && (!GamePanel.inMill(GamePanel.player1Pieces.get(temp)) || GamePanel.noRemainingMillable())) {
                                player1Play = GameStatuses.PlayerPlay.DESELECTED;
                                gamePanel.millRemove(GamePanel.player1Pieces.get(temp));
                                GameStatuses.changeTurn();
                                P2hasMill = false;
                                gamePanel.showMills();
                                break;
                            }
                            break;
                        case MIDDLE:
                            switch(player1Play) {
                                case MILLABLE:
                                    if(!GamePanel.inMill(GamePanel.player1Pieces.get(temp)) || GamePanel.noRemainingMillable()) {
                                        player1Play = GameStatuses.PlayerPlay.DESELECTED;
                                        gamePanel.millRemove(GamePanel.player1Pieces.get(temp));
                                        GameStatuses.changeTurn();
                                        P2hasMill = false;
                                        gamePanel.showMills();
                                    }
                                    break;
                                case SELECTED:
                                    if(GameStatuses.turn.equals(GameStatuses.TurnsEnum.PLAYER1)) {
                                        player1Play = GameStatuses.PlayerPlay.DESELECTED;
                                        gamePanel.deselectPiece();
                                    }
                                    break;
                                case DESELECTED:
                                    if(GameStatuses.turn.equals(GameStatuses.TurnsEnum.PLAYER1) && !P1hasMill) {
                                        player1Play = GameStatuses.PlayerPlay.SELECTED;
                                        gamePanel.setSelectedPiece(GamePanel.player1Pieces.get(temp));
                                    }
                                    break;
                            }
                    }
                    showTurn();
                    alertMessages();
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
                            if(player2Play.equals(GameStatuses.PlayerPlay.MILLABLE) && (!GamePanel.inMill(GamePanel.player2Pieces.get(temp)) || GamePanel.noRemainingMillable())) {
                                player2Play = GameStatuses.PlayerPlay.DESELECTED;
                                gamePanel.millRemove(GamePanel.player2Pieces.get(temp));
                                GameStatuses.changeTurn();
                                P1hasMill = false;
                                gamePanel.showMills();
                                break;
                            }
                            break;
                        case MIDDLE:
                            switch(player2Play) {
                                case MILLABLE:
                                    if(!GamePanel.inMill(GamePanel.player2Pieces.get(temp)) || GamePanel.noRemainingMillable()) {
                                        player2Play = GameStatuses.PlayerPlay.DESELECTED;
                                        gamePanel.millRemove(GamePanel.player2Pieces.get(temp));
                                        GameStatuses.changeTurn();
                                        P1hasMill = false;
                                        gamePanel.showMills();
                                    }
                                    break;
                                case SELECTED:
                                    if(GameStatuses.turn.equals(GameStatuses.TurnsEnum.PLAYER2)) {
                                        player2Play = GameStatuses.PlayerPlay.DESELECTED;
                                        gamePanel.deselectPiece();
                                    }
                                    break;
                                case DESELECTED:
                                    if(GameStatuses.turn.equals(GameStatuses.TurnsEnum.PLAYER2) && !P2hasMill) {
                                        player2Play = GameStatuses.PlayerPlay.SELECTED;
                                        gamePanel.setSelectedPiece(GamePanel.player2Pieces.get(temp));
                                    }
                                    break;
                            }
                        }
                    showTurn();
                    alertMessages();
                }
            });
        }
    }
}
