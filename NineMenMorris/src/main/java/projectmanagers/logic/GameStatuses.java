package main.java.projectmanagers.logic;

import main.java.projectmanagers.gui.panels.GamePanel;
import main.java.projectmanagers.gui.panels.Player1Panel;
import main.java.projectmanagers.gui.panels.Player2Panel;

public class GameStatuses {
    public enum ColorStatus {EMPTY, BLUE, RED, INVALID}
    public enum GameType {SINGLE_PLAYER, TWO_PLAYER}
    public enum GamePlay {BEGINNING, MIDDLE, END}
    public enum PlayerPlay {MILL, SELECTED, DESELECTED, WIN, LOSE}
    public enum Turns {PLAYER1, PLAYER2}

    public static GamePlay getGamePlay () {
        if (Player1Panel.hasTurn() || Player2Panel.hasTurn())
            return GamePlay.BEGINNING;
        else if (GamePanel.player1Pieces.size() > 2 && GamePanel.player2Pieces.size() > 2)
            return GamePlay.MIDDLE;
        else
            return GamePlay.END;
    }
    public static Turns changeTurn (Turns currTurn) {
        if (currTurn.equals(Turns.PLAYER1))
            return Turns.PLAYER2;
        else
             return Turns.PLAYER1;
    }
    public static PlayerPlay getWinner (Turns currTurn) {
        if (currTurn.equals(Turns.PLAYER1))
            return PlayerPlay.WIN;
        else
            return PlayerPlay.LOSE;
    }
}
