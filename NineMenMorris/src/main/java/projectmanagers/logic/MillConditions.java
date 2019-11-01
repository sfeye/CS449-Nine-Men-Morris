package main.java.projectmanagers.logic;

import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.EMPTY;

// Class containing X Y information about pieces contained within a mill
public class MillConditions {
    private Integer x1, y1, x2, y2, x3, y3;
    private boolean milled;

    // Sets a valid mill with each position's given X Y
    MillConditions(int x1, int y1, int x2, int y2, int x3, int y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.milled = false;
    }

    // Logic to determine whether a mill exists
    boolean determineMill() {
        if (milled) {
            return false;
        } else if (Board.position(x1, y1) == EMPTY || Board.position(x2, y2) == EMPTY || Board.position(x3, y3) == EMPTY) {
            return false;
        }

        if ((Board.position(x1, y1) == Board.position(x2, y2)) && (Board.position(x2, y2) == Board.position(x3, y3))) {
            milled = true;
        } else {
            milled = false;
        }
        return milled;
    }

    // Sets milled to false
    void unsetMillStatus() {
        milled = false;
    }

    // returns milled
    boolean isMilled() {
        return milled;
    }
}
