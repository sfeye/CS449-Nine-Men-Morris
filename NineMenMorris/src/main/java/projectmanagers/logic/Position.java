package projectmanagers.logic;

import projectmanagers.logic.GameStatuses.ColorStatus;

public class Position {
    private final MillConditions[] millConditions;
    private ColorStatus status;

    public Position(MillConditions[] millConditions) {
        this.millConditions = millConditions;
        this.status = ColorStatus.EMPTY;
    }

    public void onClick(Player player) {
            status = player.get_color();
            determineMills();
    }

    public ColorStatus get_status() {
        return status;
    }

    private void determineMills() {
    }

    public void remove() {
        status = ColorStatus.EMPTY;
    }
}
