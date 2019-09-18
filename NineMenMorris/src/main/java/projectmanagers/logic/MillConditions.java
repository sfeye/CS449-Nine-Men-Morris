package main.java.projectmanagers.logic;

public class MillConditions {
    Position position1;
    Position position2;
    Position position3;

    MillConditions(Position position1, Position position2, Position position3) {
        this.position1 = position1;
        this.position2 = position2;
        this.position3 = position3;
    }

    boolean determineMill() {
        if (position1.get_status() == GameStatuses.ColorStatus.EMPTY){
            return false;
        }
        return (position1.get_status() == position2.get_status()) && (position2.get_status() == position3.get_status());
    }
}
