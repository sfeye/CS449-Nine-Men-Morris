package main.java.projectmanagers.logic;

import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.EMPTY;
import static main.java.projectmanagers.logic.GameStatuses.ColorStatus;
import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.INVALID;

// Class containing X Y information about pieces contained within a mill
public class MillConditions {
    private Integer x1, y1, x2, y2, x3, y3;
    private ColorStatus pos1, pos2, pos3;
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
        update();
        if (milled || pos1 == EMPTY || pos2 == EMPTY || pos3 == EMPTY) {
            return false;
        } else if ((pos1 == pos2) && (pos2 == pos3)) {
            milled = true;
        }

        return milled;
    }

    // Sets milled to false
    void unsetMillStatus() {
        update();
        milled = false;
    }

    // returns milled
    public boolean isMilled() {
        return milled;
    }

    public ColorStatus closeToMilled() {
        update();
        if (milled) {
            return INVALID;
        } else if ((pos1.equals(pos2) && !pos1.equals(EMPTY) && pos3.equals(EMPTY))) {
            return pos1;
        } else if ((pos2.equals(pos3) && !pos2.equals(EMPTY) && pos1.equals(EMPTY))) {
            return pos2;
        }else if ((pos1.equals(pos3) && !pos1.equals(EMPTY) && pos2.equals(EMPTY))) {
            return pos1;
        } else {
            return INVALID;
        }
    }

    private void update() {
        pos1 = Board.position(x1, y1);
        pos2 = Board.position(x2, y2);
        pos3 = Board.position(x3, y3);

    }
}
