package main.java.projectmanagers.trackers;

import main.java.projectmanagers.logic.GameStatuses.ColorStatus;
import main.java.projectmanagers.logic.Player;

import java.util.HashMap;
import java.util.Map;

import static main.java.projectmanagers.logic.GameStatuses.ColorStatus.*;

public class PlayerTracking {
    final public static Player RED_PLAYER = new Player(RED);
    final public static Player BLUE_PLAYER = new Player(BLUE);

    final public static Map<ColorStatus, Player> PLAYER_LOOKUP = new HashMap<>();

    static {
        PLAYER_LOOKUP.put(RED, RED_PLAYER);
        PLAYER_LOOKUP.put(BLUE, BLUE_PLAYER);
    }
}
