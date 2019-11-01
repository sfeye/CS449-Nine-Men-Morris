package main.java.projectmanagers.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MillConditionsTest {
    private Board testBoard = new Board();

    @Before
    public void setUp() {
        Board testBoard = new Board();
        Board.startingBoard();
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
}
