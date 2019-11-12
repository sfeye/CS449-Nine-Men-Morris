package main.java.projectmanagers.logic;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static main.java.projectmanagers.logic.GameStatuses.*;
import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.*;
import static main.java.projectmanagers.logic.GameStatuses.TurnsEnum.PLAYER1;
import static main.java.projectmanagers.trackers.PlayerTracking.*;

// Contains the logic and data for the Board values
public class Board {

    static public List<List<Position>> boardArray = new ArrayList<>();
    static public List<MillConditions> boardMills = new ArrayList<>();

    public Board() {
        startingBoard();
    }

    public static void reset() {
        boardArray.clear();
        startingBoard();
    }
    // Constructs the data structure for the initial board
    // Constructs the data structure for the initial board
    static void startingBoard() {

        for (int i = 0; i < 7; i++){
            boardArray.add(new ArrayList<>());
            for (int j = 0; j < 7; j++){
                boardArray.get(i).add(new Position());
            }
        }

        boardMills.add(0, new MillConditions(0, 0, 0, 3, 0, 6));
        boardMills.add(1, new MillConditions(1, 1, 1, 3, 1, 5));
        boardMills.add(2, new MillConditions(2, 2, 2, 3, 2, 4));
        boardMills.add(3, new MillConditions(3, 0, 3, 1, 3, 2));
        boardMills.add(4, new MillConditions(3, 4, 3, 5, 3, 6));
        boardMills.add(5, new MillConditions(4, 2, 4, 3, 4, 4));
        boardMills.add(6, new MillConditions(5, 1, 5, 3, 5, 5));
        boardMills.add(7, new MillConditions(6, 0, 6, 3, 6, 6));

        boardMills.add(8, new MillConditions(0, 0, 3, 0, 6, 0));
        boardMills.add(9, new MillConditions(1, 1, 3, 1, 5, 1));
        boardMills.add(10, new MillConditions(2, 2, 3, 2, 4, 2));
        boardMills.add(11, new MillConditions(0, 3, 1, 3, 2, 3));
        boardMills.add(12, new MillConditions(4, 3, 5, 3, 6, 3));
        boardMills.add(13, new MillConditions(2, 4, 3, 4, 4, 4));
        boardMills.add(14, new MillConditions(1, 5, 3, 5, 5, 5));
        boardMills.add(15, new MillConditions(0, 6, 3, 6, 6, 6));

        // xpos, ypos
        boardArray.get(0).get(0).initialize(boardMills.get(0), boardMills.get(8));
        boardArray.get(0).get(3).initialize(boardMills.get(0), boardMills.get(11));
        boardArray.get(0).get(6).initialize(boardMills.get(0), boardMills.get(15));

        boardArray.get(1).get(1).initialize(boardMills.get(1), boardMills.get(9));
        boardArray.get(1).get(3).initialize(boardMills.get(1), boardMills.get(11));
        boardArray.get(1).get(5).initialize(boardMills.get(1), boardMills.get(14));

        boardArray.get(2).get(2).initialize(boardMills.get(2), boardMills.get(10));
        boardArray.get(2).get(3).initialize(boardMills.get(2), boardMills.get(11));
        boardArray.get(2).get(4).initialize(boardMills.get(2), boardMills.get(13));

        boardArray.get(3).get(0).initialize(boardMills.get(3), boardMills.get(8));
        boardArray.get(3).get(1).initialize(boardMills.get(3), boardMills.get(9));
        boardArray.get(3).get(2).initialize(boardMills.get(3), boardMills.get(10));
        boardArray.get(3).get(4).initialize(boardMills.get(4), boardMills.get(13));
        boardArray.get(3).get(5).initialize(boardMills.get(4), boardMills.get(14));
        boardArray.get(3).get(6).initialize(boardMills.get(4), boardMills.get(15));

        boardArray.get(4).get(2).initialize(boardMills.get(5), boardMills.get(10));
        boardArray.get(4).get(3).initialize(boardMills.get(5), boardMills.get(12));
        boardArray.get(4).get(4).initialize(boardMills.get(5), boardMills.get(13));

        boardArray.get(5).get(1).initialize(boardMills.get(6), boardMills.get(9));
        boardArray.get(5).get(3).initialize(boardMills.get(6), boardMills.get(12));
        boardArray.get(5).get(5).initialize(boardMills.get(6), boardMills.get(14));

        boardArray.get(6).get(0).initialize(boardMills.get(7), boardMills.get(8));
        boardArray.get(6).get(3).initialize(boardMills.get(7), boardMills.get(12));
        boardArray.get(6).get(6).initialize(boardMills.get(7), boardMills.get(15));

    }

    // Places a colored piece on the given xpos and ypos based on the current turn
    static public boolean placePiece(int xpos, int ypos) {
        ColorStatus updateColor;
        if (turn == PLAYER1) {
            updateColor = RED;
        } else {
            updateColor = BLUE;
        }

        if ((boardArray.get(xpos).get(ypos).getStatus() == EMPTY) && (boardArray.get(xpos).get(ypos).getStatus() != INVALID)) {
            boardArray.get(xpos).get(ypos).setStatus(updateColor);
            PLAYER_LOOKUP.get(updateColor).incrementPieces();

            return boardArray.get(xpos).get(ypos).determineMills();
        } else {
            return false;
        }
    }

    // Removes the given piece and replaces it with EMPTY
    static public boolean remove(int xpos, int ypos) {
        if (boardArray.get(xpos).get(ypos).getStatus() != EMPTY && boardArray.get(xpos).get(ypos).getStatus() != INVALID) {
            PLAYER_LOOKUP.get(boardArray.get(xpos).get(ypos).getStatus()).decrementPieces();
            boardArray.get(xpos).get(ypos).removePiece();
            return true;
        } else {
            return false;
        }
    }

    // Returns the ColorStatus of the given xpos ypos
    static public ColorStatus position(int xpos, int ypos) {
        return boardArray.get(xpos).get(ypos).getStatus();
    }

    // Returns the isMilled status of the given xpos ypos
    static public boolean isPositionMilled(int xpos, int ypos) {
        return boardArray.get(xpos).get(ypos).isMilled();
    }

    // Returns whether or not any positions of the turn color have an open adjacent position
    static public boolean noEmptyAdjacentPositions() {
        ColorStatus playerTurn;
        int emptySpaces = 0;
        if (turn == PLAYER1) {
            playerTurn = RED;
        } else {
            playerTurn = BLUE;
        }

        for (int xpos = 0; xpos < 7; xpos++) {
            for (int ypos = 0; ypos < 7; ypos++) {
                List<Pair<Integer, Integer>> adjacent = adjacentPieces(xpos, ypos);
                for (Pair<Integer, Integer> pair : adjacent){
                    if ((Board.position(xpos, ypos) == playerTurn) && Board.position(pair.getKey(), pair.getValue()) == EMPTY){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Returns the adjacent positions to a given xpos ypos
    static public List<Pair<Integer, Integer>> adjacentPieces(int xpos, int ypos) {

        List<Pair<Integer, Integer>> adjacentPieces = new ArrayList<>();

        switch (ypos) {
            case 0:
                if (xpos == 0) {
                    adjacentPieces.add(new Pair<>(3, 0));
                    adjacentPieces.add(new Pair<>(0, 3));
                }
                else if (xpos == 3) {
                    adjacentPieces.add(new Pair<>(0, 0));
                    adjacentPieces.add(new Pair<>(6, 0));
                    adjacentPieces.add(new Pair<>(3, 1));
                }
                else if (xpos == 6) {
                    adjacentPieces.add(new Pair<>(3, 0));
                    adjacentPieces.add(new Pair<>(6, 3));
                }
                break;
            case 1:
                if (xpos == 1) {
                    adjacentPieces.add(new Pair<>(1, 3));
                    adjacentPieces.add(new Pair<>(3, 1));
                }
                else if (xpos == 3) {
                    adjacentPieces.add(new Pair<>(3, 0));
                    adjacentPieces.add(new Pair<>(1, 1));
                    adjacentPieces.add(new Pair<>(5, 1));
                    adjacentPieces.add(new Pair<>(3, 2));
                }
                else if (xpos == 5) {
                    adjacentPieces.add(new Pair<>(3, 1));
                    adjacentPieces.add(new Pair<>(5, 3));
                }
                break;
            case 2:
                if (xpos == 2) {
                    adjacentPieces.add(new Pair<>(2, 3));
                    adjacentPieces.add(new Pair<>(3, 2));
                }
                else if (xpos == 3) {
                    adjacentPieces.add(new Pair<>(2, 2));
                    adjacentPieces.add(new Pair<>(3, 1));
                    adjacentPieces.add(new Pair<>(4, 2));
                }
                else if (xpos == 4) {
                    adjacentPieces.add(new Pair<>(3, 2));
                    adjacentPieces.add(new Pair<>(4, 3));
                }
                break;
            case 3:
                if (xpos == 0) {
                    adjacentPieces.add(new Pair<>(0, 0));
                    adjacentPieces.add(new Pair<>(0, 6));
                    adjacentPieces.add(new Pair<>(1, 3));
                }
                else if (xpos == 1) {
                    adjacentPieces.add(new Pair<>(0, 3));
                    adjacentPieces.add(new Pair<>(1, 1));
                    adjacentPieces.add(new Pair<>(1, 5));
                    adjacentPieces.add(new Pair<>(2, 3));
                }
                else if (xpos == 2) {
                    adjacentPieces.add(new Pair<>(1, 3));
                    adjacentPieces.add(new Pair<>(2, 2));
                    adjacentPieces.add(new Pair<>(2, 4));
                }
                else if (xpos == 4) {
                    adjacentPieces.add(new Pair<>(5, 3));
                    adjacentPieces.add(new Pair<>(4, 2));
                    adjacentPieces.add(new Pair<>(4, 4));
                }
                else if (xpos == 5) {
                    adjacentPieces.add(new Pair<>(4, 3));
                    adjacentPieces.add(new Pair<>(5, 1));
                    adjacentPieces.add(new Pair<>(5, 5));
                    adjacentPieces.add(new Pair<>(6, 3));
                }
                else if (xpos == 6) {
                    adjacentPieces.add(new Pair<>(5, 3));
                    adjacentPieces.add(new Pair<>(6, 0));
                    adjacentPieces.add(new Pair<>(6, 6));
                }
                break;
            case 4:
                if (xpos == 2) {
                    adjacentPieces.add(new Pair<>(2, 3));
                    adjacentPieces.add(new Pair<>(3, 4));
                }
                else if (xpos == 3) {
                    adjacentPieces.add(new Pair<>(2, 4));
                    adjacentPieces.add(new Pair<>(3, 5));
                    adjacentPieces.add(new Pair<>(4, 4));
                }
                else if (xpos == 4) {
                    adjacentPieces.add(new Pair<>(3, 4));
                    adjacentPieces.add(new Pair<>(4, 3));
                }
                break;
            case 5:
                if (xpos == 1) {
                    adjacentPieces.add(new Pair<>(1, 3));
                    adjacentPieces.add(new Pair<>(3, 5));
                }
                else if (xpos == 3) {
                    adjacentPieces.add(new Pair<>(3, 6));
                    adjacentPieces.add(new Pair<>(1, 5));
                    adjacentPieces.add(new Pair<>(5, 5));
                    adjacentPieces.add(new Pair<>(3, 4));
                }
                else if (xpos == 5) {
                    adjacentPieces.add(new Pair<>(3, 5));
                    adjacentPieces.add(new Pair<>(5, 3));
                }
                break;
            case 6:
                if (xpos == 0) {
                    adjacentPieces.add(new Pair<>(3, 6));
                    adjacentPieces.add(new Pair<>(0, 3));
                }
                else if (xpos == 3) {
                    adjacentPieces.add(new Pair<>(0, 6));
                    adjacentPieces.add(new Pair<>(6, 6));
                    adjacentPieces.add(new Pair<>(3, 5));
                }
                else if (xpos == 6) {
                    adjacentPieces.add(new Pair<>(3, 6));
                    adjacentPieces.add(new Pair<>(6, 3));
                }
                break;
            default:
                adjacentPieces = Collections.emptyList();
        }
        return adjacentPieces;
    }

    // Returns all pieces in a milled position on the board
    static public List<Pair<Integer, Integer>> getMilledPieces() {
        List<Pair<Integer, Integer>> mills = new ArrayList<>();
        for (int xpos = 0; xpos < 7; xpos++) {
            for (int ypos = 0; ypos < 7; ypos++) {
                if (Board.boardArray.get(xpos).get(ypos).isMilled()){
                    mills.add(new Pair<>(xpos, ypos));
                }
            }
        }
        return mills;
    }
}
