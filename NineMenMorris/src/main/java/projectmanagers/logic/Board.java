package main.java.projectmanagers.logic;



import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

import static main.java.projectmanagers.logic.GameStatuses.ColorStatus;
import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.EMPTY;
import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.INVALID;
import static main.java.projectmanagers.trackers.PlayerTracking.PLAYER_LOOKUP;

public class Board {

    static private ColorStatus[][] boardArray = new ColorStatus[7][7];
    static public ColorStatus[][] boardMills = new ColorStatus[16][3];

    public Board() {
        startingBoard();
    }

    static void startingBoard() {

        // xpos, ypos
        boardArray[0][0] = EMPTY;
        boardArray[0][3] = EMPTY;
        boardArray[0][6] = EMPTY;

        boardArray[1][1] = EMPTY;
        boardArray[1][3] = EMPTY;
        boardArray[1][5] = EMPTY;

        boardArray[2][2] = EMPTY;
        boardArray[2][3] = EMPTY;
        boardArray[2][4] = EMPTY;

        boardArray[3][0] = EMPTY;
        boardArray[3][1] = EMPTY;
        boardArray[3][2] = EMPTY;
        boardArray[3][4] = EMPTY;
        boardArray[3][5] = EMPTY;
        boardArray[3][6] = EMPTY;

        boardArray[4][2] = EMPTY;
        boardArray[4][3] = EMPTY;
        boardArray[4][4] = EMPTY;

        boardArray[5][1] = EMPTY;
        boardArray[5][3] = EMPTY;
        boardArray[5][5] = EMPTY;

        boardArray[6][0] = EMPTY;
        boardArray[6][3] = EMPTY;
        boardArray[6][6] = EMPTY;

        for (int xpos = 0; xpos < 7; xpos++) {
            for (int ypos = 0; ypos < 7; ypos++) {
                if (boardArray[xpos][ypos] != EMPTY)
                    boardArray[xpos][ypos] = INVALID;
            }
        }
    }

    static public boolean placePiece(Player player, int xpos, int ypos) {
        if ((Board.boardArray[xpos][ypos] == EMPTY) && (Board.boardArray[xpos][ypos] != INVALID)) {
            Board.boardArray[xpos][ypos] = player.getColor();
            player.incrementPieces();
            determineMills();
            return true;
        } else {
            return false;
        }
    }

    static public boolean remove(int xpos, int ypos) {
        if (boardArray[xpos][ypos] != EMPTY && boardArray[xpos][ypos] != INVALID) {
            PLAYER_LOOKUP.get(boardArray[xpos][ypos]).decrementPieces();
            boardArray[xpos][ypos] = EMPTY;
            return true;
        } else {
            return false;
        }
    }

    static public ColorStatus position(int xpos, int ypos) {
        return boardArray[xpos][ypos];
    }

    static private void determineMills() {
        //TODO: Sprint 2
    }

    static private void millAllocation() {
        // TODO: Sprint 2


        /*
        //will always be EMPTY enum
        boardMills[0][0] = boardArray[0][0];
        boardMills[0][1] = boardArray[0][3];
        boardMills[0][2] = boardArray[0][6];

        boardMills[1][0] = boardArray[1][1];
        boardMills[1][1] = boardArray[1][3];
        boardMills[1][2] = boardArray[1][5];

        boardMills[2][0] = boardArray[2][2];
        boardMills[2][1] = boardArray[2][3];
        boardMills[2][2] = boardArray[2][4];

        boardMills[3][0] = boardArray[3][0];
        boardMills[3][1] = boardArray[3][1];
        boardMills[3][2] = boardArray[3][2];

        boardMills[4][0] = boardArray[3][4];
        boardMills[4][1] = boardArray[3][5];
        boardMills[4][2] = boardArray[3][6];

        boardMills[5][0] = boardArray[4][2];
        boardMills[5][1] = boardArray[4][3];
        boardMills[5][2] = boardArray[4][4];

        boardMills[6][0] = boardArray[5][1];
        boardMills[6][1] = boardArray[5][3];
        boardMills[6][2] = boardArray[5][5];

        boardMills[7][0] = boardArray[6][0];
        boardMills[7][1] = boardArray[6][3];
        boardMills[7][2] = boardArray[6][6];

        boardMills[8][0] = ;
        boardMills[8][1] = ;
        boardMills[8][2] = ;

        boardMills[9][0] = ;
        boardMills[9][1] = ;
        boardMills[9][2] = ;

        boardMills[10][0] = ;
        boardMills[10][1] = ;
        boardMills[10][2] = ;

        boardMills[11][0] = ;
        boardMills[11][1] = ;
        boardMills[11][2] = ;

        boardMills[12][0] = ;
        boardMills[12][1] = ;
        boardMills[12][2] = ;

        boardMills[13][0] = ;
        boardMills[13][1] = ;
        boardMills[13][2] = ;

        boardMills[14][0] = ;
        boardMills[14][1] = ;
        boardMills[14][2] = ;

        boardMills[15][0] = ;
        boardMills[15][1] = ;
        boardMills[15][2] = ;
        */

    }

    static public List<Pair<Integer, Integer>> adjacent(int xpos, int ypos) {

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
                    adjacentPieces.add(new Pair<>(2, 4));
                }
                else if (xpos == 3) {
                    adjacentPieces.add(new Pair<>(2, 4));
                    adjacentPieces.add(new Pair<>(3, 5));
                    adjacentPieces.add(new Pair<>(4, 4));
                }
                else if (xpos == 4) {
                    adjacentPieces.add(new Pair<>(2, 4));
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
        }

        return adjacentPieces;

    }


}
