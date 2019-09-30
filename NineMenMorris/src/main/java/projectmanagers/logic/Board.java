package main.java.projectmanagers.logic;


public class Board {

    //GameStatuses.ColorStatus boardArrayTEST[] = new GameStatuses.ColorStatus[24];

    static public GameStatuses.ColorStatus[][] boardArray = new GameStatuses.ColorStatus[7][7];
    static public GameStatuses.ColorStatus[][] boardMills = new GameStatuses.ColorStatus[16][3];

    public Board() {
        startingBoard();
        //millAllocation();
    }

    private void startingBoard() {

        //row,column
        boardArray[0][0] = GameStatuses.ColorStatus.EMPTY;
        boardArray[0][3] = GameStatuses.ColorStatus.EMPTY;
        boardArray[0][6] = GameStatuses.ColorStatus.EMPTY;

        boardArray[1][1] = GameStatuses.ColorStatus.EMPTY;
        boardArray[1][3] = GameStatuses.ColorStatus.EMPTY;
        boardArray[1][5] = GameStatuses.ColorStatus.EMPTY;

        boardArray[2][2] = GameStatuses.ColorStatus.EMPTY;
        boardArray[2][3] = GameStatuses.ColorStatus.EMPTY;
        boardArray[2][4] = GameStatuses.ColorStatus.EMPTY;

        boardArray[3][0] = GameStatuses.ColorStatus.EMPTY;
        boardArray[3][1] = GameStatuses.ColorStatus.EMPTY;
        boardArray[3][2] = GameStatuses.ColorStatus.EMPTY;
        boardArray[3][4] = GameStatuses.ColorStatus.EMPTY;
        boardArray[3][5] = GameStatuses.ColorStatus.EMPTY;
        boardArray[3][6] = GameStatuses.ColorStatus.EMPTY;

        boardArray[4][2] = GameStatuses.ColorStatus.EMPTY;
        boardArray[4][3] = GameStatuses.ColorStatus.EMPTY;
        boardArray[4][4] = GameStatuses.ColorStatus.EMPTY;

        boardArray[5][1] = GameStatuses.ColorStatus.EMPTY;
        boardArray[5][3] = GameStatuses.ColorStatus.EMPTY;
        boardArray[5][5] = GameStatuses.ColorStatus.EMPTY;

        boardArray[6][0] = GameStatuses.ColorStatus.EMPTY;
        boardArray[6][3] = GameStatuses.ColorStatus.EMPTY;
        boardArray[6][6] = GameStatuses.ColorStatus.EMPTY;

        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                if (boardArray[i][j] != GameStatuses.ColorStatus.EMPTY)
                    boardArray[i][j] = GameStatuses.ColorStatus.INVALID;
            }
        }
    }

    private void millAllocation() {
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
}
