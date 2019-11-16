package main.java.projectmanagers;

import main.java.projectmanagers.gui.GameBoardGui;
import main.java.projectmanagers.logic.Board;

import javax.swing.*;

public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        Board board = new Board();
        GameBoardGui.start();

    }
}
