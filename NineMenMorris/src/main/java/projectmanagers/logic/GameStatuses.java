package main.java.projectmanagers.logic;

import main.java.projectmanagers.gui.panels.Player1Panel;
import main.java.projectmanagers.gui.panels.Player2Panel;

import static main.java.projectmanagers.trackers.PlayerTracking.BLUE_PLAYER;
import static main.java.projectmanagers.trackers.PlayerTracking.RED_PLAYER;

// Class to hold enums for game tracking
public class GameStatuses {
    public enum ColorStatus {EMPTY, BLUE, RED, INVALID}

    public enum GameType {SINGLE_PLAYER, TWO_PLAYER, MENU}

    public enum GamePlay {BEGINNING, MIDDLE, END}

    public enum PlayerPlay {MILLABLE, SELECTED, DESELECTED}

    public enum TurnsEnum {PLAYER1, PLAYER2, MENU}

    public static TurnsEnum turn = TurnsEnum.MENU;
    public static int turnCounter = 1;

    // Returns the stage of the game
    public static GamePlay getGamePlay() {
        if (Player1Panel.hasTurn() || Player2Panel.hasTurn())
            return GamePlay.BEGINNING;
        else if (!Board.noEmptyAdjacentPositions() && RED_PLAYER.getPieces() >= 3 && BLUE_PLAYER.getPieces() >= 3)
            return GamePlay.MIDDLE;
        else
            return GamePlay.END;
    }

    // Cycles the turn between players
    public static void changeTurn() {
        if (turn.equals(TurnsEnum.PLAYER1))
            turn = TurnsEnum.PLAYER2;
        else
            turn = TurnsEnum.PLAYER1;
    }
}
