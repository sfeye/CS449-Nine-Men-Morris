package main.java.projectmanagers.logic;

import main.java.projectmanagers.logic.GameStatuses.ColorStatus;
public class Player {
    final private ColorStatus color;
    private int pieces;
    private int turns;

    public Player(ColorStatus color) {
        this.color = color;
        this.pieces = 0;
        this.turns = 8;
    }

    public ColorStatus getColor() {
        return color;
    }

    public int getPieces() {
        return pieces;
    }

    public int getTurns() {
        return turns;
    }

    public void decrementTurns(){
        turns--;
    }

    public void incrementPieces() {
        this.pieces++;
    }

    public void decrementPieces(){
        this.pieces--;
    }

    public void setInitialVariables() {
        this.pieces = 0;
        this.turns = 8;
    }
}
