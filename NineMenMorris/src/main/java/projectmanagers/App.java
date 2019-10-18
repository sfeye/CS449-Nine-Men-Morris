package main.java.projectmanagers;

import main.java.projectmanagers.gui.GameBoardGui;
import main.java.projectmanagers.logic.Board;

public class App 
{
    public static void main( String[] args )
    {

        Board board = new Board();

        System.out.println(board.adjacent(3,2));

        //GameBoardGui.start();
    }
}
