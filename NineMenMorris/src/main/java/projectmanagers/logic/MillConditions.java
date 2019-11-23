package main.java.projectmanagers.logic;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.EMPTY;
import static main.java.projectmanagers.logic.GameStatuses.ColorStatus;
import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.INVALID;
import static main.java.projectmanagers.logic.GameStatuses.NO_PLACE;

// Class containing X Y information about pieces contained within a mill
public class MillConditions {
    private Pair<Integer, Integer> coord1, coord2, coord3;
    private boolean milled;

    // Sets a valid mill with each position's given X Y
    MillConditions(int x1, int y1, int x2, int y2, int x3, int y3) {
        this.coord1 = new Pair<>(x1, y1);
        this.coord2 = new Pair<>(x2, y2);
        this.coord3 = new Pair<>(x3, y3);
        this.milled = false;
    }

    // Logic to determine whether a mill exists
    boolean determineMill() {
        ColorStatus pos1 = Board.position(coord1);
        ColorStatus pos2 = Board.position(coord2);
        ColorStatus pos3 = Board.position(coord3);
        if (milled || pos1 == EMPTY || pos2 == EMPTY || pos3 == EMPTY) {
            return false;
        } else if ((pos1 == pos2) && (pos2 == pos3)) {
            milled = true;
        }

        return milled;
    }

    // Sets milled to false
    void unsetMillStatus() {
        milled = false;
    }

    // returns milled
    public boolean isMilled() {
        return milled;
    }

    public Pair<ColorStatus, List<Pair<Integer, Integer>>> closeToMilled() {
        ColorStatus pos1 = Board.position(coord1);
        ColorStatus pos2 = Board.position(coord2);
        ColorStatus pos3 = Board.position(coord3);

        List<Pair<Integer, Integer>> coordsList = new ArrayList<>();
        coordsList.add(coord1);
        coordsList.add(coord2);
        coordsList.add(coord3);

        if (milled) {
            return new Pair<>(INVALID, Collections.singletonList(NO_PLACE));
        } else if ((pos1.equals(pos2) && !pos1.equals(EMPTY) && pos3.equals(EMPTY))) {
            return new Pair<>(pos1, coordsList);
        } else if ((pos2.equals(pos3) && !pos2.equals(EMPTY) && pos1.equals(EMPTY))) {
            return new Pair<>(pos2, coordsList);
        }else if ((pos1.equals(pos3) && !pos1.equals(EMPTY) && pos2.equals(EMPTY))) {
            return new Pair<>(pos1, coordsList);
        } else {
            return new Pair<>(INVALID, Collections.singletonList(NO_PLACE));
        }
    }

}
