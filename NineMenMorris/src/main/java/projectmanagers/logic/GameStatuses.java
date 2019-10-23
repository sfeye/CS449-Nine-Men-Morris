package main.java.projectmanagers.logic;

import main.java.projectmanagers.gui.panels.GamePanel;
import main.java.projectmanagers.gui.panels.Player1Panel;
import main.java.projectmanagers.gui.panels.Player2Panel;

public class GameStatuses {
    public enum ColorStatus {EMPTY, BLUE, RED, INVALID}
    public enum GameType {SINGLE_PLAYER, TWO_PLAYER}
    public enum GamePlay {BEGINNING, MIDDLE, END}
    public enum PlayerPlay {MILL, SELECTED, FLY}

    public static GamePlay getGamePlay () {
        if (Player1Panel.hasTurn() || Player2Panel.hasTurn())
            return GamePlay.BEGINNING;
        else if (GamePanel.player1Pieces.size() > 2 && GamePanel.player2Pieces.size() > 2)
            return GamePlay.MIDDLE;
        else
            return GamePlay.END;
    }


}
