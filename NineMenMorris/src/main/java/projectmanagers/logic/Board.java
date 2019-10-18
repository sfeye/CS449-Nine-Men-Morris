package main.java.projectmanagers.logic;

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

    static public void startingBoard() {

        //row,column
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

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (boardArray[i][j] != EMPTY)
                    boardArray[i][j] = INVALID;
            }
        }
    }

    static public boolean placePiece(Player player, int row, int column) {
        if ((Board.boardArray[row][column] == EMPTY) && (Board.boardArray[row][column] != INVALID)) {
            Board.boardArray[row][column] = player.getColor();
            player.incrementPieces();
            determineMills();
            return true;
        } else {
            return false;
        }
    }

    static public boolean remove(int row, int column) {
        if (boardArray[row][column] != EMPTY && boardArray[row][column] != INVALID) {
            PLAYER_LOOKUP.get(boardArray[row][column]).decrementPieces();
            boardArray[row][column] = EMPTY;
            return true;
        } else {
            return false;
        }
    }

    static public ColorStatus position(int row, int column) {
        return boardArray[row][column];
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

    static public int[] adjacent(int row, int column) {

        int [] aPieces = new int[] {};

        if (row == 0)
        {
            if (column == 0)
                aPieces = new int[]{03, 30};
            else if (column == 3)
                aPieces = new int[]{00, 06, 13};
            else if (column == 6)
                aPieces = new int[]{03, 36};
        }
        else if (row == 1)
        {
            if (column == 1)
                aPieces = new int[]{31, 13};
            else if (column == 3)
                aPieces = new int[]{03, 11, 15, 23};
            else if (column == 5)
                aPieces = new int[]{13, 35};
        }
        else if (row == 2)
        {
            if (column == 2)
                aPieces = new int[]{32, 23};
            else if (column == 3)
                aPieces = new int[]{22, 13, 24};
            else if (column == 4)
                aPieces = new int[]{23, 34};
        }
        else if (row == 3)
        {
            if (column == 0)
                aPieces = new int[]{00, 60, 31};
            else if (column == 1)
                aPieces = new int[]{30, 11, 51, 32};
            else if (column == 2)
                aPieces = new int[]{31, 22, 42};
            else if (column == 4)
                aPieces = new int[]{35, 24, 44};
            else if (column == 5)
                aPieces = new int[]{34, 15, 55, 36};
            else if (column == 6)
                aPieces = new int[]{35, 06, 66};
        }
        else if (row == 4)
        {
            if (column == 2)
                aPieces = new int[]{32, 42};
            else if (column == 3)
                aPieces = new int[]{42, 53, 44};
            else if (column == 4)
                aPieces = new int[]{42, 34};
        }
        else if (row == 5)
        {
            if (column == 1)
                aPieces = new int[]{31, 53};
            else if (column == 3)
                aPieces = new int[]{63, 51, 55, 43};
            else if (column == 5)
                aPieces = new int[]{53, 35};
        }
        else if (row == 6)
        {
            if (column == 0)
                aPieces = new int[]{63, 30};
            else if (column == 3)
                aPieces = new int[]{60, 66 ,53};
            else if (column == 6)
                aPieces = new int[]{63, 36};
        }

        return aPieces;

    }


}
