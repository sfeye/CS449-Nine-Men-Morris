package main.java.projectmanagers.gui.panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import main.java.projectmanagers.gui.components.*;
import main.java.projectmanagers.logic.Board;
import main.java.projectmanagers.logic.GameStatuses;

import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.EMPTY;
import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.INVALID;
import static main.java.projectmanagers.trackers.PlayerTracking.BLUE_PLAYER;
import static main.java.projectmanagers.trackers.PlayerTracking.RED_PLAYER;

public class GamePanel extends JPanel {
    private GridBagConstraints gbc;
    public static ArrayList<BoardPieces> boardPieces;
    public static ArrayList<PlayerPieces> player1Pieces;
    public static ArrayList<PlayerPieces> player2Pieces;
    private static PlayerPieces selectedPiece;

    public GamePanel () {
        super();
        boardPieces = new ArrayList<>(24);
        player1Pieces = new ArrayList<>(9);
        player2Pieces = new ArrayList<>(9);
        buildBoard();
    }
    // Method to remove a players piece if they are selected in a mill
    public void millRemove(PlayerPieces piece) {
        remove(piece);
        gbc.gridx = piece.getXCoordinate(); gbc.gridy = piece.getYCoordinate();
        add(getOrigin(piece), gbc);
        Board.remove(piece.getXCoordinate(), piece.getYCoordinate());
        piece.setXCoordinate(8); piece.setYCoordinate(8);
        revalidate();
        repaint();
    }
    // method gets the original board piece with the specified coordinates
    private BoardPieces getOrigin (PlayerPieces playerPiece) {
        for (BoardPieces blackPiece : boardPieces) {
            if (blackPiece.getXCoordinate() == playerPiece.getXCoordinate() && blackPiece.getYCoordinate() == playerPiece.getYCoordinate())
                return blackPiece;
        }
        return (new BoardPieces(playerPiece.getXCoordinate(), playerPiece.getYCoordinate()));
    }
    // if a piece is in a mill then the ol is changed to green
    public void showMills() {
        for(PlayerPieces red : player1Pieces) {
            if(inMill(red) && !red.isSelected())
                red.setOL(Color.green);
            else if (!red.isSelected())
                red.setOL(Color.black);
        }
        for(PlayerPieces blue : player2Pieces) {
            if(inMill(blue) && !blue.isSelected())
                blue.setOL(Color.green);
            else if (!blue.isSelected())
                blue.setOL(Color.black);
        }
    }
    // checks if players have any pieces that are not in a mill
    public static boolean noRemainingMillable() {
        if(GameStatuses.turn.equals(GameStatuses.TurnsEnum.PLAYER2)) {
            for (PlayerPieces red : player1Pieces) {
                if (!inMill(red) && red.getXCoordinate() != 8)
                    return false;
            }
        }
        else {
            for(PlayerPieces blue : player2Pieces) {
                if(!inMill(blue) && blue.getXCoordinate() != 8)
                    return false;
            }
        }
        return true;
    }
    // if pieces are in a mill then return true
    public static boolean inMill(PlayerPieces playerPiece) {
        Pair pairToMill = new Pair<>(playerPiece.getXCoordinate(), playerPiece.getYCoordinate());
        List<Pair<Integer, Integer>> pairsInMills = Board.getMilledPieces();
        return (pairsInMills.contains(pairToMill));
    }
    // checks if the player piece has an adjacent board piece open
    public boolean canSlide (BoardPieces blackPiece, PlayerPieces playerPiece) {
        Pair blackPair = new Pair<>(blackPiece.getXCoordinate(), blackPiece.getYCoordinate());
        List<Pair<Integer, Integer>> adjacentPieces = Board.adjacentPieces(playerPiece.getXCoordinate(), playerPiece.getYCoordinate());
        return (adjacentPieces.contains(blackPair));
    }
    // Method to swap player piece locations with a board location
    public void swapPlayerPiece(BoardPieces blackPiece, PlayerPieces playerPiece){
        remove(playerPiece);
        remove(blackPiece);
        // pre-remove update logic
        Board.remove(playerPiece.getXCoordinate(), playerPiece.getYCoordinate());
        // place pieces on board, with opposite coordinates
        gbc.gridx = blackPiece.getXCoordinate(); gbc.gridy = blackPiece.getYCoordinate();
        add(playerPiece, gbc);
        gbc.gridx = playerPiece.getXCoordinate(); gbc.gridy = playerPiece.getYCoordinate();
        add(getOrigin(playerPiece), gbc);
        // update playerPiece coordinates
        playerPiece.setXCoordinate(blackPiece.getXCoordinate());    playerPiece.setYCoordinate(blackPiece.getYCoordinate());
        // post-place update logic
        Board.placePiece(playerPiece.getXCoordinate(), playerPiece.getYCoordinate());
        deselectPiece();
        revalidate();
        repaint();
    }
    // selects a place piece and reserves a selected coordinate location
    public void setSelectedPiece(PlayerPieces piece) {
        piece.selectPiece();
        selectedPiece = piece;
        selectedPiece.setXCoordinate(piece.getXCoordinate());
        selectedPiece.setYCoordinate(piece.getYCoordinate());
    }
    // deselects all pieces and changes ol to black
    public void deselectPiece() {
        for(PlayerPieces piece : player1Pieces)
            piece.deselectPiece();
        for(PlayerPieces piece : player2Pieces)
            piece.deselectPiece();
    }
    // Methods determine which piece is selected in the player pieces arrays
    public PlayerPieces getSelectedPiece() {
        if(GameStatuses.turn.equals(GameStatuses.TurnsEnum.PLAYER1)) {
            for (PlayerPieces red : player1Pieces) {
                if (red.getXCoordinate() == selectedPiece.getXCoordinate() && red.getYCoordinate() == selectedPiece.getYCoordinate())
                    return red;
            }
        }
        else {
            for (PlayerPieces blue : player2Pieces) {
                if (blue.getXCoordinate() == selectedPiece.getXCoordinate() && blue.getYCoordinate() == selectedPiece.getYCoordinate())
                    return blue;
            }
        }
        return null;
    }
    // Adds a new player piece in the beginning stage of a game
    public void addPlayer1Piece(BoardPieces piece) {
        remove(piece);
        gbc.gridx = piece.getXCoordinate(); gbc.gridy = piece.getYCoordinate();
        player1Pieces.get(RED_PLAYER.getTurns()).setXCoordinate(piece.getXCoordinate());
        player1Pieces.get(RED_PLAYER.getTurns()).setYCoordinate(piece.getYCoordinate());

        add(player1Pieces.get(RED_PLAYER.getTurns()), gbc);
        Board.placePiece(piece.getXCoordinate(), piece.getYCoordinate());
        revalidate();
        repaint();
    }
    public void addPlayer2Piece(BoardPieces piece) {
        remove(piece);
        gbc.gridx = piece.getXCoordinate(); gbc.gridy = piece.getYCoordinate();
        player2Pieces.get(BLUE_PLAYER.getTurns()).setXCoordinate(piece.getXCoordinate());
        player2Pieces.get(BLUE_PLAYER.getTurns()).setYCoordinate(piece.getYCoordinate());

        add(player2Pieces.get(BLUE_PLAYER.getTurns()), gbc);
        Board.placePiece(piece.getXCoordinate(), piece.getYCoordinate());
        revalidate();
        repaint();
    }
    // method for resetting the gamePanel
    public void resetPanel() {
        removeAll();
        buildBoard();
        RED_PLAYER.setInitialVariables();
        BLUE_PLAYER.setInitialVariables();
        revalidate();
        repaint();
    }
    // Builds arrays, lines, and lays out the board
    private void buildBoard () {
        buildArrays();
        gbc = new GridBagConstraints();
        gbc.weighty = 0.1; gbc.weightx = 0.1;
        setLayout(new GridBagLayout());
        setBackground(new Color(153,133,97));
        drawBoardLines();
        drawBoardPieces();
    }
    private void buildArrays () {
        boardPieces.clear();
        player1Pieces.clear();
        player2Pieces.clear();
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 7; j++) {
                if(Board.position(i, j) == EMPTY)
                    boardPieces.add(new BoardPieces(i, j));
            }
        }
        for (int i = 0; i < 9; i++)
        {
            player1Pieces.add(new PlayerPieces(Color.red, Color.black, false));
            player2Pieces.add(new PlayerPieces(Color.blue, Color.black, false));
        }
    }
    private void drawBoardPieces () {
        int count = 0;
        for (int i = 0; i < 7 ; i ++) {
            for (int j = 0; j < 7; j++) {
                if(Board.position(i, j) == EMPTY){
                    gbc.gridx = i; gbc.gridy = j;
                    add(boardPieces.get(count), gbc);
                    count++;
                }
            }
        }
    }
    private void drawBoardLines() {
        for (int i = 0; i < 7 ; i ++) {
            for (int j = 0; j < 7; j++) {
                if(Board.position(i, j) == INVALID){
                    gbc.gridx = i; gbc.gridy = j;
                    if ((j == 0 || j == 1 || j == 5 || j == 6) && (i != 0 && i != 6)) {
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        add(new BoardLines(2), gbc);
                    }
                    else if (j != 3 && i != 3){
                        gbc.fill = GridBagConstraints.VERTICAL;
                        add(new BoardLines(3), gbc);
                    }
                }
                else {
                    gbc.gridx = i; gbc.gridy = j;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    if (((i == 1 || i == 5) && j == 3) || (i == 3 && j != 3))
                        add(new BoardLines(7), gbc);
                    else if ( (i == 0 || i == 1 || (i == 4 && j == 3) || (i == 2 && j != 3)))
                        add(new BoardLines(1), gbc);
                    else if ( (i == 5 || i == 6 || (i == 2 && j == 3) || (i == 4 && j != 3)))
                        add(new BoardLines(5), gbc);
                    gbc.fill = GridBagConstraints.VERTICAL;
                    if ((j == 1 || j == 2) && i != 3 || (j == 4 && i == 3) || (j == 0 && (i == 0 || i == 3 || i == 6)))
                        add(new BoardLines(0), gbc);
                    else if ((( j == 5 || j == 4) && i != 3) || (j == 2 && i == 3) || (j == 6 && (i == 0 || i == 3 || i == 6)))
                        add(new BoardLines(6), gbc);
                    else if ((j == 5 || j == 1) && i == 3 || j == 3)
                        add(new BoardLines(8), gbc);
                }
            }
        }
        gbc.fill = GridBagConstraints.NONE;
    }
}
