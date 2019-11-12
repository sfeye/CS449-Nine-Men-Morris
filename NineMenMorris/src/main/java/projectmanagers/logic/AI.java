package main.java.projectmanagers.logic;

import javafx.util.Pair;
import main.java.projectmanagers.trackers.PlayerTracking.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static main.java.projectmanagers.trackers.PlayerTracking.BLUE_PLAYER;

public class AI {


    static public Pair<Integer, Integer> AIPlacePiece() {


        if(GameStatuses.turnCounter == 0)
            return new Pair<>(0, 0);
        else if (GameStatuses.turnCounter == 2) {
            if (Board.boardArray.get(0).get(0).getStatus() == GameStatuses.ColorStatus.RED && Board.boardArray.get(6).get(6).getStatus() == GameStatuses.ColorStatus.EMPTY)
                return new Pair<>(6, 6);
            else if (Board.boardArray.get(6).get(0).getStatus() == GameStatuses.ColorStatus.RED && Board.boardArray.get(0).get(6).getStatus() == GameStatuses.ColorStatus.EMPTY)
                return new Pair<>(0, 6);
            else if (Board.boardArray.get(6).get(6).getStatus() == GameStatuses.ColorStatus.RED && Board.boardArray.get(0).get(0).getStatus() == GameStatuses.ColorStatus.EMPTY)
                return new Pair<>(0, 0);
            else if (Board.boardArray.get(0).get(6).getStatus() == GameStatuses.ColorStatus.RED && Board.boardArray.get(6).get(0).getStatus() == GameStatuses.ColorStatus.EMPTY)
                return new Pair<>(6, 0);
        }


        List<Pair<Integer, Integer>> myPieces = new ArrayList<>();

//        for (int i = 0; i < 7; i++) {
//            for (int j = 0; i < 7; j++) {
//                if (Board.boardArray.get(i).get(j).getStatus() == GameStatuses.ColorStatus.BLUE)
//                    myPieces.add(new Pair<>(i, j));
//            }
//        }


        //else if I have a potential mill, add piece to the free space

        //else if opponent has a potential mill, add piece to the free space

        //else if I have the potential to set up  a mill, place adjacent to placed piece
        /*
        for (int i = 0; i < myPieces.size(); i++) {
            Pair<Integer, Integer> temp = myPieces.get(i);
            List<Pair<Integer, Integer>> adjacent = Board.adjacentPieces(temp.getValue(), temp.getValue());

            if () {



            }

        }
        */

        //if none are applicable, place a random piece
        return randomFree("place").get(0);
    }

    static public List<Pair<Integer, Integer>> AIMovePiece(Boolean fly) {

        List<Pair<Integer, Integer>> swapPieces = new ArrayList<>();


        //if I have potential mill, move piece to the free space

        //else if my opponent has a potential mill, move piece to the free space

        //else if I am one move away from having a potential mill, move piece to the free space

        //MAYBE: else if I am two moves away from having a potential mill, move piece to the free space

        //if none are applicable, move random piece
        if(fly)
            return randomFree("move");
        else
            return randomFree("move");

    }

    static public Pair<Integer, Integer> AIRemovePiece() {

        //If my opponent has a potential mill, remove that piece

        //MAYBE: else if my opponent is two moves away from a potential mill, remove that piece

        //else, remove a random piece of my opponents
        return randomFree("remove").get(0);

    }

    static private List<Pair<Integer, Integer>> randomFree(String mode) {

        Random random = new Random();

        List<Pair<Integer, Integer>> pieces = new ArrayList<>();

        Integer randomX = random.nextInt(6);
        Integer randomY = random.nextInt(6);

        if (mode.equals("place")) {
            while (true) {
                if (Board.boardArray.get(randomX).get(randomY).getStatus() == GameStatuses.ColorStatus.EMPTY) {
                    pieces.add(new Pair<>(randomX, randomY));
                    return pieces;
                }
                else {
                    randomX = random.nextInt(6);
                    randomY = random.nextInt(6);
                }
            }
        }
        else if (mode.equals("remove")) {
            while (true) {
                if (Board.boardArray.get(randomX).get(randomY).getStatus() == GameStatuses.ColorStatus.RED) {
                    pieces.add(new Pair<>(randomX, randomY));
                    return pieces;
                }
                else {
                    randomX = random.nextInt(6);
                    randomY = random.nextInt(6);
                }
            }
        }
        else if (mode.equals("move")){

            boolean myPiece = false;

            while (!myPiece) {
                if (Board.boardArray.get(randomX).get(randomY).getStatus() == GameStatuses.ColorStatus.BLUE) {
                    pieces.add(new Pair<>(randomX, randomY));
                    myPiece = true;
                }
                else {
                    randomX = random.nextInt(6);
                    randomY = random.nextInt(6);
                }
            }

            if (BLUE_PLAYER.getPieces() <= 3) {
                while(true) {

                    randomX = random.nextInt(6);
                    randomY = random.nextInt(6);

                    if (Board.boardArray.get(randomX).get(randomY).getStatus() == GameStatuses.ColorStatus.EMPTY) {
                        pieces.add(new Pair<>(randomX, randomY));
                        return pieces;
                    }
                }
            }
            else {

                List<Pair<Integer, Integer>> adjacent = Board.adjacentPieces(randomX, randomY);

                for (int i = 0; i < adjacent.size(); i++) {

                    Pair temp = adjacent.get(i);

                    if (Board.boardArray.get((Integer) temp.getValue()).get((Integer) temp.getKey()).getStatus() == GameStatuses.ColorStatus.EMPTY) {
                        pieces.add(temp);
                        return pieces;
                    }
                }
            }
        }

        return null;
    }

}
