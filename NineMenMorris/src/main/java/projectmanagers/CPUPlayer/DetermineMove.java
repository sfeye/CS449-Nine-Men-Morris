package main.java.projectmanagers.CPUPlayer;

import javafx.util.Pair;
import main.java.projectmanagers.logic.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static main.java.projectmanagers.logic.GameStatuses.ColorStatus;
import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.BLUE;
import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.EMPTY;
import static main.java.projectmanagers.logic.GameStatuses.NO_PLACE;

public class DetermineMove {
    static Pair<Integer, Integer> placementMills(ColorStatus color) {
        List<Pair<Integer, Integer>> positions = Board.getEmptyPieces();
        if (positions.isEmpty()) {
            return NO_PLACE;
        }

        List<Pair<Integer, Integer>> closeToMill = new ArrayList<>();

        for (Pair<Integer, Integer> position : positions) {
            if ((Board.isPositionCloseToMilled(position)).getKey().equals(color)) {
                closeToMill.add(position);
            }
        }

        if (closeToMill.isEmpty()) {
            return NO_PLACE;
        } else {
            Random rand = new Random();
            return closeToMill.get(rand.nextInt(closeToMill.size()));
        }
    }

    static Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> movementMills(ColorStatus color) {
        List<Pair<Integer, Integer>> positions = Board.getEmptyPieces();
        Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> result = new Pair<>(NO_PLACE, NO_PLACE);

        for (Pair<Integer, Integer> position : positions) {
            Pair<ColorStatus, List<Pair<Integer, Integer>>> millInfo = Board.isPositionCloseToMilled(position);

            if (millInfo.getKey().equals(color)) {
                List<Pair<Integer, Integer>> adjacents = Board.adjacentPieces(position.getKey(), position.getValue());

                for (Pair<Integer, Integer> adjacent : adjacents) {
                    if (Board.position(adjacent).equals(BLUE) && !millInfo.getValue().contains(adjacent)) {
                        return new Pair<>(adjacent, position);
                    } else if (Board.position(adjacent).equals(EMPTY)){
                        for (Pair<Integer, Integer> pair : Board.adjacentPieces(adjacent.getKey(), adjacent.getValue())){
                            if (Board.position(pair).equals(BLUE)) {
                                result = new Pair<>(pair, adjacent);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

}
