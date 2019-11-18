package main.java.projectmanagers.logic;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.*;
import static main.java.projectmanagers.logic.GameStatuses.TurnsEnum.PLAYER1;
import static main.java.projectmanagers.logic.GameStatuses.TurnsEnum.PLAYER2;
import static main.java.projectmanagers.logic.GameStatuses.changeTurn;
import static main.java.projectmanagers.trackers.PlayerTracking.BLUE_PLAYER;
import static main.java.projectmanagers.trackers.PlayerTracking.RED_PLAYER;
import static org.junit.Assert.*;

public class BoardTest {

    @Before
    public void setUp() {
        RED_PLAYER.setInitialVariables();
        BLUE_PLAYER.setInitialVariables();
        Board.reset();
        GameStatuses.turn = PLAYER1;
    }

    @Test
    public void placePiece_redOnEmpty() {
        Board.placePiece(0, 0);
        assertEquals(1, RED_PLAYER.getPieces());
        assertEquals(RED, Board.position(0,0));
        assertFalse(Board.getEmptyPieces().contains(new Pair<>(0, 0)));
    }

    @Test
    public void placePiece_redOnBlue() {
        GameStatuses.turn = PLAYER2;
        Board.placePiece( 0, 0);

        assertFalse(Board.placePiece(0, 0));
        assertEquals(0, RED_PLAYER.getPieces());
        assertEquals(BLUE, Board.position(0,0));
    }

    @Test
    public void placePiece_redOnInvalid() {
        assertFalse(Board.placePiece(0, 1));

        assertEquals(0, RED_PLAYER.getPieces());
    }

    @Test
    public void placePiece_blueOnEmpty() {
        GameStatuses.turn = PLAYER2;
        Board.placePiece(0, 0);
        assertEquals(1, BLUE_PLAYER.getPieces());
        assertEquals(BLUE, Board.position(0,0));
        assertFalse(Board.getEmptyPieces().contains(new Pair<>(0, 0)));
    }

    @Test
    public void placePiece_blueOnRed() {
        Board.placePiece(0, 0);
        changeTurn();
        Board.placePiece(0, 0);
        assertEquals(0, BLUE_PLAYER.getPieces());
        assertEquals(RED, Board.position(0,0));
    }

    @Test
    public void placePiece_blueOnInvalid() {
        assertFalse(Board.placePiece(0, 1));

        assertEquals(0, BLUE_PLAYER.getPieces());
    }

    @Test
    public void remove_redPiece() {
        Board.placePiece(0, 0);

        assertTrue(Board.remove(0, 0));
        assertTrue(Board.getEmptyPieces().contains(new Pair<>(0, 0)));
        assertEquals(0, RED_PLAYER.getPieces());
    }

    @Test
    public void remove_bluePiece() {
        Board.placePiece(0, 0);

        assertTrue(Board.remove(0, 0));
        assertTrue(Board.getEmptyPieces().contains(new Pair<>(0, 0)));
        assertEquals(0, BLUE_PLAYER.getPieces());
    }

    @Test
    public void remove_emptySpace() {
        assertFalse(Board.remove(0, 0));
    }

    @Test
    public void remove_invalidSpace() {
        assertFalse(Board.remove(0, 1));
    }

    @Test
    public void determineMills_returnsTrueOnMill() {
        assertFalse(Board.placePiece( 0, 0));
        assertFalse(Board.placePiece(0, 3));
        assertTrue(Board.placePiece( 0, 6));
    }

    @Test
    public void isPositionMilled_returnsTrueOnMilledPieces() {
        Board.placePiece(0, 0);
        Board.placePiece(0, 3);
        Board.placePiece(0, 6);

        assertTrue(Board.isPositionMilled(0, 0));
        assertTrue(Board.isPositionMilled(0, 3));
        assertTrue(Board.isPositionMilled(0, 6));
    }

    @Test
    public void isPositionMilled_updatesCorrectly() {
        assertFalse(Board.isPositionMilled(0, 0));

        Board.placePiece(0, 0);
        Board.placePiece(0, 3);
        Board.placePiece(0, 6);

        assertFalse(Board.isPositionMilled(3, 0));
    }

    @Test
    public void getMilledPositions_returnsMilledPieces() {
        Board.placePiece(0, 0);
        Board.placePiece(0, 3);
        Board.placePiece(0, 6);

        List<Pair<Integer, Integer>> output = Board.getMilledPieces();

        // Set up expected output
        List<Pair<Integer, Integer>> expectedOutput = new ArrayList<>();
        expectedOutput.add(new Pair<>(0, 0));
        expectedOutput.add(new Pair<>(0, 3));
        expectedOutput.add(new Pair<>(0, 6));

        assertEquals(expectedOutput, output);
    }

    @Test
    public void noAdjacentEmptyPositions_fullBoard() {
        Board.placePiece(0, 0);
        changeTurn();
        Board.placePiece(0 ,3);
        Board.placePiece(3, 0);
        changeTurn();
        assertTrue(Board.noEmptyAdjacentPositions());
    }
    @Test

    public void noAdjacentEmptyPositions_emptyAdjacent() {
        Board.placePiece(0, 0);

        assertFalse(Board.noEmptyAdjacentPositions());

    }

    @Test
    public void position_returnsInvalidOnBadInput() {
        assertEquals(INVALID, Board.position(1, 0));
    }

    @Test
    public void adjacentPieces_returnsEmptyListOnBadInput() {
        assertEquals(Collections.emptyList(), Board.adjacentPieces(1, 0));
    }
}
