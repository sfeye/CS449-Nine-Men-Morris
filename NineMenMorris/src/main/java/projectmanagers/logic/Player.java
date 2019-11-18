package main.java.projectmanagers.logic;

import javafx.util.Pair;
import main.java.projectmanagers.logic.GameStatuses.ColorStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Class used to track player statuses
public class Player {
    final private ColorStatus color;
    private int pieces;
    private int turns;
    private List<Pair<Integer, Integer>> placedPieces;

    // Constructor to set turns and color
    public Player(ColorStatus color) {
        this.color = color;
        this.pieces = 0;
        this.turns = 8;
        this.placedPieces = new ArrayList<>();
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
    public void decrementTurns() {
        turns--;
    }

    // Increments the piece counter
    public void incrementPieces() {
        this.pieces++;
    }

    // Decrements the piece counter
    public void decrementPieces() {
        this.pieces--;
    }

    // Sets initial variables for testing
    public void setInitialVariables() {
        this.pieces = 0;
        this.turns = 8;
    }

    //This returns a new list in order to safeguard against mutation
    public List<Pair<Integer, Integer>> getPlacedPieces() {
        return new ArrayList<>(placedPieces);
    }

    public void addPlacedPiece(int xpos, int ypos) {
        placedPieces.add(new Pair<>(xpos, ypos));
    }

    public void removePlacedPiece(int xpos, int ypos) {
        placedPieces.remove(new Pair<>(xpos, ypos));
    }

    public Pair<Integer, Integer> getRandomPiece() {
        if (placedPieces.isEmpty()){
            return new Pair<>(-1, -1);
        }
        Random rand = new Random();
        return placedPieces.get(rand.nextInt(placedPieces.size()));
    }
}
