package main.java.projectmanagers.logic;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.RED;
import static org.junit.Assert.assertEquals;

public class PlayerTest {
    private Player mockPlayer = new Player(RED);

    @Before
    public void setUp() {
        Board.reset();
    }

    @Test
    public void addPlacedPiece_correctInputs() {
        mockPlayer.addPlacedPiece(0, 0);
        assertEquals(Collections.singletonList(new Pair<>(0, 0)), mockPlayer.getPlacedPieces());
    }

    @Test
    public void removePlacedPiece_removeContainedPiece() {
        mockPlayer.addPlacedPiece(0, 0);
        mockPlayer.removePlacedPiece(0, 0);
        assertEquals(Collections.emptyList(), mockPlayer.getPlacedPieces());
    }
}
