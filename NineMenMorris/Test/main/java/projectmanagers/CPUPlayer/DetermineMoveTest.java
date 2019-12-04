package main.java.projectmanagers.CPUPlayer;

import javafx.util.Pair;
import main.java.projectmanagers.logic.Board;
import main.java.projectmanagers.logic.GameStatuses;
import org.junit.Before;
import org.junit.Test;

import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.BLUE;
import static main.java.projectmanagers.logic.GameStatuses.NO_PLACE;
import static org.junit.Assert.assertEquals;

public class DetermineMoveTest {

    @Before
    public void setUp() {
        Board.reset();
        GameStatuses.turn = GameStatuses.TurnsEnum.PLAYER1;
    }

    @Test
    public void placementMills_noMill() {
        assertEquals(NO_PLACE, DetermineMove.placementMills(BLUE));
    }

    @Test
    public void placementMills_Mill() {
        GameStatuses.turn = GameStatuses.TurnsEnum.PLAYER2;
        Board.placePiece(0, 0);
        Board.placePiece(0, 3);
        assertEquals(new Pair<>(0, 6), DetermineMove.placementMills(BLUE));
    }

    @Test
    public void movementMills_noMill() {
        GameStatuses.turn = GameStatuses.TurnsEnum.PLAYER2;
        Board.placePiece(0, 3);

        assertEquals(new Pair<>(NO_PLACE, NO_PLACE), DetermineMove.movementMills(BLUE));
    }

    @Test
    public void movementMills_Mill() {
        GameStatuses.turn = GameStatuses.TurnsEnum.PLAYER2;
        Board.placePiece(0, 3);
        Board.placePiece(0, 6);
        Board.placePiece(3, 0);

        assertEquals(new Pair<>(new Pair<>(3, 0), new Pair<>(0, 0)), DetermineMove.movementMills(BLUE));
    }

    @Test
    public void movementMills_MillStepAway() {
        GameStatuses.turn = GameStatuses.TurnsEnum.PLAYER2;
        Board.placePiece(0, 3);
        Board.placePiece(0, 6);
        Board.placePiece(6, 0);

        assertEquals(new Pair<>(new Pair<>(6, 0), new Pair<>(3, 0)), DetermineMove.movementMills(BLUE));
    }

}
