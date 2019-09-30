package main.java.projectmanagers.gui.panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import main.java.projectmanagers.gui.components.*;
import main.java.projectmanagers.logic.Board;
import main.java.projectmanagers.logic.GameStatuses;

public class GamePanel extends JPanel {
    private int player1Count;
    private int player2Count;
    public GridBagConstraints gbc;
    public static ArrayList<BoardPieces> boardPieces;
    public static ArrayList<PlayerPieces> player1Pieces;
    public static ArrayList<PlayerPieces> player2Pieces;

    public GamePanel () {
        super();
        boardPieces = new ArrayList<>(24);
        player1Pieces = new ArrayList<>(9);
        player2Pieces = new ArrayList<>(9);
        buildBoard();
    }
    //TODO: Sprint Two mill and remove piece
    public void millPlayer1Remove(PlayerPieces piece){
        remove(piece);
        gbc.gridx = piece.getXCoordinate(); gbc.gridy = piece.getYCoordinate();
        add(new BoardPieces(piece.getXCoordinate(), piece.getYCoordinate()), gbc);
        player1Count--;
        revalidate();
        repaint();
    }
    public void millPlayer2Remove(PlayerPieces piece){
        remove(piece);
        gbc.gridx = piece.getXCoordinate(); gbc.gridy = piece.getYCoordinate();
        add(new BoardPieces(piece.getXCoordinate(), piece.getYCoordinate()), gbc);
        player2Count--;
        revalidate();
        repaint();
    }
    //TODO: Sprint two slide piece
    public void slidePiece(PlayerPieces piece){

    }
    public void addPlayer1Piece(BoardPieces piece){
        remove(piece);
        gbc.gridx = piece.getXCoordinate(); gbc.gridy = piece.getYCoordinate();
        player1Pieces.get(player1Count).setX(piece.getXCoordinate());   player1Pieces.get(player1Count).setY(piece.getYCoordinate());
        add(player1Pieces.get(player1Count), gbc);
        player1Count++;
        revalidate();
        repaint();
        Board.boardArray[piece.getXCoordinate()][piece.getYCoordinate()] = GameStatuses.ColorStatus.BLACK;
    }
    public void addPlayer2Piece(BoardPieces piece){
        remove(piece);
        gbc.gridx = piece.getXCoordinate(); gbc.gridy = piece.getYCoordinate();
        player2Pieces.get(player2Count).setX(piece.getXCoordinate());   player2Pieces.get(player2Count).setY(piece.getYCoordinate());
        add(player2Pieces.get(player2Count), gbc);
        player2Count++;
        revalidate();
        repaint();
        Board.boardArray[piece.getXCoordinate()][piece.getYCoordinate()] = GameStatuses.ColorStatus.WHITE;
    }

    public void buildBoard () {
        buildArrays();
        gbc = new GridBagConstraints();
        gbc.weighty = 0.1; gbc.weightx = 0.1;
        setLayout(new GridBagLayout());
        setBackground(new Color(153,133,97));
        drawBoardPieces();
        drawBoardLines();
    }
    public void buildArrays (){
        boardPieces.clear();
        player1Pieces.clear();
        player2Pieces.clear();
        player1Count = 0;
        player2Count = 0;
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 7; j++) {
                if(Board.boardArray[i][j] == GameStatuses.ColorStatus.EMPTY)
                    boardPieces.add(new BoardPieces(i, j));
            }
        }
        for (int i = 0; i < 9; i++)
        {
            player1Pieces.add(new PlayerPieces(Color.red, Color.black));
            player2Pieces.add(new PlayerPieces(Color.blue, Color.black));
        }
    }
    public void drawBoardPieces () {
        int count = 0;
        for (int i = 0; i < 7 ; i ++) {
            for (int j = 0; j < 7; j++) {
                if(Board.boardArray[i][j] == GameStatuses.ColorStatus.EMPTY){
                    gbc.gridx = i; gbc.gridy = j;
                    add(boardPieces.get(count), gbc);
                    count++;
                }
            }
        }
    }
    public void drawBoardLines() {
        for (int i = 0; i < 7 ; i ++) {
            for (int j = 0; j < 7; j++) {
                if(Board.boardArray[i][j] == GameStatuses.ColorStatus.INVALID){
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
                        add(new BoardLines(2), gbc);
                    else if ( (i == 0 || i == 1 || (i == 4 && j == 3) || (i == 2 && j != 3)))
                        add(new BoardLines(1), gbc);
                    else if ( (i == 5 || i == 6 || (i == 2 && j == 3) || (i == 4 && j != 3)))
                        add(new BoardLines(5), gbc);
                    gbc.fill = GridBagConstraints.VERTICAL;
                    if ((j == 1 || j == 2) && i != 3 || (j == 4 && i == 3))
                        add(new BoardLines(0), gbc);
                    else if ((( j == 5 || j == 4) && i != 3) || (j == 2 && i == 3) )
                        add(new BoardLines(6), gbc);
                    else if ((j == 5 || j == 1) && i == 3)
                        add(new BoardLines(3), gbc);
                    add(new BoardLines(j), gbc);
                }
            }
        }
        gbc.fill = GridBagConstraints.NONE;
    }
}
