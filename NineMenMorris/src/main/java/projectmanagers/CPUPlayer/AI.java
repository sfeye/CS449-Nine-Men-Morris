package main.java.projectmanagers.CPUPlayer;

import javafx.util.Pair;
import main.java.projectmanagers.logic.Board;
import main.java.projectmanagers.logic.GameStatuses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.BLUE;
import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.EMPTY;
import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.INVALID;
import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.RED;
import static main.java.projectmanagers.trackers.PlayerTracking.BLUE_PLAYER;

public class AI {

    static private Integer randomX;
    static private Integer randomY;

    static public Pair<Integer, Integer> AIPlacePiece() {


        if(GameStatuses.turnCounter == 1)
            return new Pair<>(0, 0);
        else if (GameStatuses.turnCounter == 2) {
            if (Board.position(0, 0) == RED && Board.position(6, 6) == EMPTY)
                return new Pair<>(6, 6);
            else if (Board.position(6, 0) == RED && Board.position(0, 6) == EMPTY)
                return new Pair<>(0, 6);
            else if (Board.position(6, 6) == RED && Board.position(0, 0) == EMPTY)
                return new Pair<>(0, 0);
            else if (Board.position(0, 6) == RED && Board.position(6, 0) == EMPTY)
                return new Pair<>(6, 0);
        }


        List<Pair<Integer, Integer>> myPieces = new ArrayList<>();

//        for (int i = 0; i < 7; i++) {
//            for (int j = 0; i < 7; j++) {
//                if (Board.boardArray.get(i).get(j).getStatus() == BLUE)
//                    myPieces.add(new Pair<>(i, j));
//            }
//        }


        //else if I have a potential mill, add piece to the free space

        //else if opponent has a potential mill, add piece to the free space

        //else if I have the potential to set up  a mill, place adjacent to placed piece
        /*
        for (int i = 0; i < myPieces.size(); i++) {
            Pair<Integer, Integer> temp = myPieces.get(i);
            List<Pair<Integer, Integer>> adjacent = Board.adjacentPieces(temp.getKey(), temp.getValue());

            for (int j = 0; j < adjacent.size(); j++) {

                if (Board.boardArray.get(adjacent.get(j).getKey()).get(adjacent.get(j).getValue()).getStatus() == EMPTY) {

                    Pair<Integer, Integer> temp2 = adjacent.get(j);
                    List<Pair<Integer, Integer>> adjacent2 = Board.adjacentPieces(temp2.getKey(), temp2.getValue());

                    if (Board.boardArray.get(adjacent2.get(j).getKey()).get(adjacent2.get(j).getValue()).getStatus() == EMPTY)
                        return adjacent.get(i);
                }
            }
        }
        */

        //if none are applicable, place a random piece
        while (true) {
            cycleRandom();
            if (Board.position(randomX, randomY) == EMPTY) {
                return new Pair<>(randomX, randomY);
            }
        }
    }

    static public Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> AIMovePiece() {

        List<Pair<Integer, Integer>> swapPieces = new ArrayList<>();


        //if I have potential mill, move piece to make the mill

        //else if my opponent has a potential mill, move piece to the free space

        //else if I am one move away from setting up a mill, move piece to the free space
        List<Pair<Integer, Integer>> myPieces = new ArrayList<>();
        /*
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (Board.boardArray.get(i).get(j).getStatus() == BLUE)
                    myPieces.add(new Pair<>(i, j));
            }
        }

        for (int i = 0; i < myPieces.size(); i++) {
            Pair<Integer, Integer> temp = myPieces.get(i);
            List<Pair<Integer, Integer>> adjacent = Board.adjacentPieces(temp.getKey(), temp.getValue());

            for (int j = 0; j < adjacent.size(); j++) {

                if (Board.boardArray.get(adjacent.get(j).getKey()).get(adjacent.get(j).getValue()).getStatus() == EMPTY) {

                    Pair<Integer, Integer> temp2 = adjacent.get(j);
                    List<Pair<Integer, Integer>> adjacent2 = Board.adjacentPieces(temp2.getKey(), temp2.getValue());

                    if (Board.boardArray.get(adjacent2.get(j).getKey()).get(adjacent2.get(j).getValue()).getStatus() == EMPTY) {
                        swapPieces.add(temp);
                        swapPieces.add(adjacent.get(i));
                        return swapPieces;
                    }
                }
            }
        }
        */

        //if none are applicable, move random piece
        Pair<Integer, Integer> currentPosition = null;
        Pair<Integer, Integer> newPosition = null;
        List<Pair<Integer, Integer>> adjacent;

        // Get Current Piece
        while (currentPosition == null) {
            cycleRandom();
            adjacent = Board.adjacentPieces(randomX, randomY);

            if (BLUE_PLAYER.getPieces() > 3 && Board.position(randomX, randomY) == BLUE) {
                for (Pair<Integer, Integer> pair : adjacent) {
                    if (Board.position(pair.getKey(), pair.getValue()) == EMPTY) {
                        currentPosition = new Pair<>(randomX, randomY);
                    }
                }
            } else if (BLUE_PLAYER.getPieces() <= 3 && Board.position(randomX, randomY) == BLUE) {
                currentPosition = new Pair<>(randomX, randomY);
            }

        }

        // Get new space
        if (BLUE_PLAYER.getPieces() <= 3) {
            while(newPosition == null) {
                cycleRandom();

                if (Board.position(randomX, randomY) == EMPTY) {
                    newPosition = new Pair<>(randomX, randomY);
                }
            }
        }
        else {
            adjacent = Board.adjacentPieces(currentPosition.getKey(), currentPosition.getValue());

            for (Pair pair : adjacent) {
                if (Board.position((Integer) pair.getKey(), (Integer) pair.getValue()) == EMPTY) {
                    newPosition = pair;
                    break;
                }
            }
        }
        return new Pair<>(currentPosition, newPosition);
    }

    static public Pair<Integer, Integer> AIRemovePiece() {

        //If my opponent has a potential mill, remove that piece

        //MAYBE: else if my opponent is two moves away from a potential mill, remove that piece

        //else, remove a random piece of my opponents
        while (true) {
            if (Board.position(randomX, randomY) == RED) {
                return new Pair<>(randomX, randomY);
            }
            else {
                cycleRandom();
            }
        }
    }

    static private void cycleRandom() {
        Random random = new Random();
        boolean cycler = true;

        while(cycler) {
            randomX = random.nextInt(6);
            randomY = random.nextInt(6);
            if (!(Board.position(randomX, randomY) == INVALID)){
                cycler = false;
            }
        }
    }

}
