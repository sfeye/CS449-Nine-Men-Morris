package main.java.projectmanagers.logic;

import org.junit.Before;
import org.junit.Test;

import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.*;
import static main.java.projectmanagers.trackers.PlayerTracking.BLUE_PLAYER;
import static main.java.projectmanagers.trackers.PlayerTracking.RED_PLAYER;
import static org.junit.Assert.*;

public class BoardTest {

    @Before
    public void setUp() {
        RED_PLAYER.setInitialVariables();
        BLUE_PLAYER.setInitialVariables();
        Board.startingBoard();
    }

    @Test
    public void placePiece_redOnEmpty() {
        assertTrue(Board.placePiece(RED_PLAYER, 0, 0));
        assertEquals(1, RED_PLAYER.getPieces());
        assertEquals(RED, Board.position(0,0));
    }

    @Test
    public void placePiece_redOnBlue() {
        Board.placePiece(BLUE_PLAYER, 0, 0);

        assertFalse(Board.placePiece(RED_PLAYER, 0, 0));
        assertEquals(0, RED_PLAYER.getPieces());
        assertEquals(BLUE, Board.position(0,0));
    }

    @Test
    public void placePiece_redOnInvalid() {
        assertFalse(Board.placePiece(RED_PLAYER, 0, 1));

        assertEquals(0, RED_PLAYER.getPieces());
    }

    @Test
    public void placePiece_blueOnEmpty() {
        assertTrue(Board.placePiece(BLUE_PLAYER, 0, 0));
        assertEquals(1, BLUE_PLAYER.getPieces());
        assertEquals(BLUE, Board.position(0,0));
    }

    @Test
    public void placePiece_blueOnRed() {
        Board.placePiece(RED_PLAYER, 0, 0);

        assertFalse(Board.placePiece(BLUE_PLAYER, 0, 0));
        assertEquals(0, BLUE_PLAYER.getPieces());
        assertEquals(RED, Board.position(0,0));
    }

    @Test
    public void placePiece_blueOnInvalid() {
        assertFalse(Board.placePiece(BLUE_PLAYER, 0, 1));

        assertEquals(0, BLUE_PLAYER.getPieces());
    }

    @Test
    public void remove_redPiece() {
        Board.placePiece(RED_PLAYER, 0, 0);

        assertTrue(Board.remove(0, 0));

        assertEquals(0, RED_PLAYER.getPieces());
    }

    @Test
    public void remove_bluePiece() {
        Board.placePiece(BLUE_PLAYER, 0, 0);

        assertTrue(Board.remove(0, 0));

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
}
