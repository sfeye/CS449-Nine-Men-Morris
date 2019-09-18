package main.java.projectmanagers.logic;

import main.java.projectmanagers.logic.GameStatuses.ColorStatus;

public class Position {
    private final MillConditions[] millConditions;
    private ColorStatus status;

    public Position(MillConditions[] millConditions) {
        this.millConditions = millConditions;
        this.status = ColorStatus.EMPTY;
    }

    public boolean placePiece(Player player) {
        if (status == ColorStatus.EMPTY){
            status = player.get_color();
            determineMills();
            return true;
        } else {
            return false;
        }
    }

    public ColorStatus get_status() {
        return status;
    }

    private void determineMills() {
        // TODO: Sprint 2
    }

    public void remove() {
        status = ColorStatus.EMPTY;
    }
}
