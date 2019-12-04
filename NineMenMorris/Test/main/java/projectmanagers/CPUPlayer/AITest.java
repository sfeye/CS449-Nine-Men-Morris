package main.java.projectmanagers.CPUPlayer;

import javafx.util.Pair;
import main.java.projectmanagers.logic.Board;
import main.java.projectmanagers.logic.GameStatuses;
import org.junit.Before;
import org.junit.Test;

import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.BLUE;
import static org.junit.Assert.assertEquals;

public class AITest {

    @Before
    public void setUp() {
        Board.reset();
        GameStatuses.turn = GameStatuses.TurnsEnum.PLAYER1;
    }

    @Test
    public void AIPlacePiece_blocksEnemyMill() {
        Board.placePiece(0, 0);
        Board.placePiece(0, 3);

        assertEquals(new Pair<>(0, 6), AI.AIPlacePiece());
    }

    @Test
    public void AIPlacePiece_completesMill() {
        GameStatuses.turn = GameStatuses.TurnsEnum.PLAYER2;
        Board.placePiece(0, 0);
        Board.placePiece(0, 3);

        assertEquals(new Pair<>(0, 6), AI.AIPlacePiece());
    }

    @Test
    public void AIPlacePiece_completesMillOverBlock() {
        Board.placePiece(1, 1);
        Board.placePiece(1, 3);

        GameStatuses.turn = GameStatuses.TurnsEnum.PLAYER2;
        Board.placePiece(0, 0);
        Board.placePiece(0, 3);

        assertEquals(new Pair<>(0, 6), AI.AIPlacePiece());
    }

    @Test
    public void AIRemovePiece_removesPieceNearMill() {
        Board.placePiece(0, 0);
        Board.placePiece(0, 3);
        Board.placePiece(3, 6);
        assertEquals(new Pair<>(3, 6), AI.AIRemovePiece());
    }

    @Test
    public void AIMovePiece_movesIntoMill() {
        GameStatuses.turn = GameStatuses.TurnsEnum.PLAYER2;
        Board.placePiece(0, 0);
        Board.placePiece(0, 3);
        Board.placePiece(3, 6);
        Board.placePiece(1, 5);

        assertEquals(new Pair<>(new Pair<>(3, 6), new Pair<>(0, 6)), AI.AIMovePiece());
    }

    @Test
    public void AIMovePiece_movesNearMill() {
        GameStatuses.turn = GameStatuses.TurnsEnum.PLAYER2;
        Board.placePiece(0, 0);
        Board.placePiece(0, 3);
        Board.placePiece(6, 6);
        Board.placePiece(1, 5);

        assertEquals(new Pair<>(new Pair<>(6, 6), new Pair<>(3, 6)), AI.AIMovePiece());
    }
}
