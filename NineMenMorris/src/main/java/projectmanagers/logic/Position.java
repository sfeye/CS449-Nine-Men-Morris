package main.java.projectmanagers.logic;

import javafx.util.Pair;
import main.java.projectmanagers.logic.GameStatuses.ColorStatus;

import java.util.List;

import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.EMPTY;
import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.INVALID;
import static main.java.projectmanagers.logic.GameStatuses.NO_PLACE;

// Class to contain board position information relating to Color and Mills
public class Position {
    //Each position is part of 2 mills, denoted here by X and Y within the variable names
    private ColorStatus colorStatus;
    private MillConditions millConditionsX;
    private MillConditions millConditionsY;

    // Default Constructor setting an invalid piece
    public Position() {
        this.colorStatus = INVALID;
    }

    // Calls the determineMill method on each owned Mill
    public boolean determineMills() {
        boolean millY = millConditionsY.determineMill();
        boolean millX = millConditionsX.determineMill();

        return (millX || millY);
    }

    // Removes the piece and unsets the mills related
    public void removePiece() {
        colorStatus = EMPTY;
        millConditionsX.unsetMillStatus();
        millConditionsY.unsetMillStatus();
    }

    // Returns the ColorStatus of this position
    public ColorStatus getStatus() {
        return colorStatus;
    }

    // Initializes the position as EMPTY and sets the mills that this position owns
    public void initialize(MillConditions millConditionsX, MillConditions millConditionsY) {
        this.colorStatus = EMPTY;
        this.millConditionsX = millConditionsX;
        this.millConditionsY = millConditionsY;
    }

    // Sets the status to a passed-in ColorStatus
    public void setStatus(ColorStatus colorStatus) {
        this.colorStatus = colorStatus;
    }

    // Returns whether the piece is in a mill
    public boolean isMilled() {
        if (colorStatus == INVALID) {
            return false;
        }

        boolean mill1 = millConditionsX.isMilled();
        boolean mill2 = millConditionsY.isMilled();
        return (mill1 || mill2);
    }

    // Returns a color if the position is EMPTY and almost a mill
    // Returns INVALID if neither mill is valid and the piece is empty
    // Returns EMPTY if the piece is already placed but is also in a 2/3 mill
    public Pair<ColorStatus, List<Pair<Integer, Integer>>> closeToMilled() {
        Pair<ColorStatus, List<Pair<Integer, Integer>>> mill1 = millConditionsX.closeToMilled();
        Pair<ColorStatus, List<Pair<Integer, Integer>>> mill2 = millConditionsY.closeToMilled();

        if (mill1.getKey().equals(INVALID)) {
                return mill2;
        } else {
                return mill1;
        }
    }
}