package main.java.projectmanagers.CPUPlayer;

import javafx.util.Pair;
import main.java.projectmanagers.logic.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static main.java.projectmanagers.CPUPlayer.AI.NO_PLACE;
import static main.java.projectmanagers.logic.GameStatuses.ColorStatus;
import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.EMPTY;
import static main.java.projectmanagers.trackers.PlayerTracking.PLAYER_LOOKUP;

public class DetermineMove {
    static Pair<Integer, Integer> placementMills(ColorStatus color) {
        List<Pair<Integer, Integer>> positions = Board.getEmptyPieces();
        if (positions.isEmpty()) {
            return NO_PLACE;
        }

        List<Pair<Integer, Integer>> closeToMill = new ArrayList<>();

        for (Pair<Integer, Integer> position : positions) {
            if ((Board.isPositionCloseToMilled(position.getKey(), position.getValue())).equals(color)) {
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
        List<Pair<Integer, Integer>> positions = PLAYER_LOOKUP.get(color).getPlacedPieces();

        for (Pair<Integer, Integer> position : positions) {
            List<Pair<Integer, Integer>> adjacents = Board.adjacentPieces(position.getKey(), position.getValue());

            for (Pair<Integer, Integer> adjacent : adjacents) {
                if ((Board.isPositionCloseToMilled(adjacent.getKey(), adjacent.getValue())).equals(color)) {
                    if (!(Board.isPositionCloseToMilled(position.getKey(), position.getValue())).equals(EMPTY)) {
                        return new Pair<>(position, adjacent);
                    }
                }
            }
        }
        return new Pair<>(NO_PLACE, NO_PLACE);
    }

}
