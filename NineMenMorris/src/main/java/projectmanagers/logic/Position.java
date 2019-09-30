package main.java.projectmanagers.logic;

import main.java.projectmanagers.logic.GameStatuses.ColorStatus;

public class Position {
    //private final MillConditions[] millConditions;
    private ColorStatus status;

    public Position() {
        //this.millConditions = millConditions;
        this.status = ColorStatus.EMPTY;
    }

    public boolean placePiece(Player player, int row, int column)
    {
        if (Board.boardArray[row][column] == ColorStatus.EMPTY && Board.boardArray[row][column] != ColorStatus.INVALID)
        {
            Board.boardArray[row][column] = player.get_color();
            player.pieceAdded();
            determineMills();
            return true;
        }
        else
            {
            return false;
        }
    }

    public ColorStatus get_status() {
        return status;
    }

    private void determineMills() {
        // TODO: Sprint 2
    }

    public void remove(int row, int column) {
        Board.boardArray[row][column] = ColorStatus.EMPTY;
    }
}
