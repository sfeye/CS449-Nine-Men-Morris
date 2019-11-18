package main.java.projectmanagers.logic;

import org.junit.Before;
import org.junit.Test;

import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.RED;
import static org.junit.Assert.*;

public class MillConditionsTest {

    @Before
    public void setUp() {
        Board.reset();
    }

    @Test
    public void millsCorrect() {
        Board.placePiece( 0, 0);
        Board.placePiece( 0, 3);
        Board.placePiece( 0, 6);

        assertTrue(Board.isPositionMilled(0, 3));
    }

    @Test
    public void millsCorrectOnRemove() {
        Board.placePiece( 0, 0);
        Board.placePiece( 0, 3);
        Board.placePiece( 0, 6);

        Board.remove(0, 6);
        assertFalse(Board.isPositionMilled(0, 3));
    }

    @Test
    public void closeToMill_twoPiecesPlacedOnSameAxis() {
        Board.placePiece( 0, 0);
        Board.placePiece( 0, 3);

        assertEquals(RED, Board.isPositionCloseToMilled(0, 6));
    }

    @Test
    public void closeToMill_twoPiecesPlacedOnOppositeAxis() {
        Board.placePiece( 3, 0);
        Board.placePiece( 0, 3);

        assertEquals(RED, Board.isPositionCloseToMilled(0, 6));
    }
}
