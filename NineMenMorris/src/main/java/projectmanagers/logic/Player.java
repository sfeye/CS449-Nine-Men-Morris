package main.java.projectmanagers.logic;

import main.java.projectmanagers.logic.GameStatuses.ColorStatus;
public class Player {
    final private ColorStatus color;
    private int totalPieces;
    private int placedPieces;

    public Player(ColorStatus color) {
        this.color = color;
        this.totalPieces = 9;
        this.placedPieces = 0;
    }

    public ColorStatus get_color() {
        return color;
    }

    public int get_totalPieces() {
        return totalPieces;
    }

    public int get_placedPieces() {
        return placedPieces;
    }

    public void pieceAdded() {
        this.totalPieces -= 1;
        this.placedPieces += 1;
    }
}
