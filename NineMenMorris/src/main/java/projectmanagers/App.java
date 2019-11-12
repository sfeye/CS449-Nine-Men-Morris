package main.java.projectmanagers;

import main.java.projectmanagers.gui.GameBoardGui;
import main.java.projectmanagers.logic.AI;
import main.java.projectmanagers.logic.Board;

public class App 
{
    public static void main( String[] args )
    {
        Board board = new Board();
        GameBoardGui.start();

    }
}
