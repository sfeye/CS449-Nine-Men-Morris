package main.java.projectmanagers.logic;

import main.java.projectmanagers.logic.GameStatuses.ColorStatus;

// Class used to track player statuses
public class Player {
    final private ColorStatus color;
    private int pieces;
    private int turns;

    // Constructor to set turns and color
    public Player(ColorStatus color) {
        this.color = color;
        this.pieces = 0;
        this.turns = 8;
    }

    // Returns the player's color
    public ColorStatus getColor() {
        return color;
    }

    // Returns the players' board piece count
    public int getPieces() {
        return pieces;
    }

    // Returns how many turns until the placement phase is over
    public int getTurns() {
        return turns;
    }

    // Decrements the turn counter
    public void decrementTurns(){
        turns--;
    }

    // Increments the piece counter
    public void incrementPieces() {
        this.pieces++;
    }

    // Decrements the piece counter
    public void decrementPieces(){
        this.pieces--;
    }

    // Sets initial variables for testing
    public void setInitialVariables() {
        this.pieces = 0;
        this.turns = 8;
    }
}
