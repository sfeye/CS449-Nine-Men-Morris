package main.java.projectmanagers.gui.components;

import main.java.projectmanagers.gui.GameBoardGui;
import main.java.projectmanagers.gui.panels.*;
import main.java.projectmanagers.logic.GameStatuses;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardPieces extends JButton{
    private int x, y;
    private boolean mouseClicked = false, mouseOver = false, mousePressed = false;
    private int diameter;
    private Color outline = Color.black;
    public boolean willMove = false;

    public BoardPieces(int x, int y) {
        this.x = x;
        this.y = y;
        //Mouse actions to make the circle have button attributes
        MouseAdapter mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent me) {
                if(contains(me.getX(), me.getY())) {
                    mouseClicked = true;
                    repaint();
                }
            }
            @Override
            public void mousePressed(MouseEvent me) {
                if(contains(me.getX(), me.getY())) {
                    mousePressed = true;
                    repaint();
                }
            }
            @Override
            public void mouseReleased(MouseEvent me) {
                mousePressed = false;
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent me) {
                mouseOver = false;
                mousePressed = false;
                repaint();
            }
            @Override
            public void mouseMoved(MouseEvent me) {
                mouseOver = contains(me.getX(), me.getY());
                repaint();
            }
        };
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }
    // accessor methods for board piece coordinates
    public int getXCoordinate () { return x; }
    public int getYCoordinate () { return y; }
    public void setOl(Color ol) {this.outline = ol;}
    public void beforeMove() { willMove = true; }
    public void afterMove() { willMove = false; }

    @Override
    public Dimension getPreferredSize(){
        FontMetrics metrics = getGraphics().getFontMetrics(getFont());
        int minDiameter = 10 + Math.max(metrics.stringWidth(getText()), metrics.getHeight());
        return new Dimension(minDiameter, minDiameter);
    }
    private int getDiameter() {
        diameter = Math.min(getWidth(), getHeight());
        return (diameter / 2) - 5;
    }
    @Override
    public boolean contains (int x, int y) {
        int radius = getDiameter()/2;
        return Point2D.distance(x, y, (getWidth() / 2), (getHeight() / 2)) < radius;
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        diameter = getDiameter();
        int radius = diameter / 2;

        //Clickable display for current board position
        if (mousePressed)
            g2.setColor(Color.darkGray);

        else
            g2.setColor(Color.black);
        //fill oval will change the color of the inside of the circle
        g2.fillOval(getWidth() / 2 - radius, getHeight() / 2 - radius, diameter, diameter);
        //Highlight the current board selection
        if (mouseOver && g.getColor() == Color.black && (Player1Panel.hasTurn() || Player2Panel.hasTurn()
                || (PlayerPieces.isSelected && (GameStatuses.turn.equals(GameStatuses.TurnsEnum.PLAYER1) && GameBoardGui.gameType.equals(GameStatuses.GameType.SINGLE_PLAYER) || GameBoardGui.gameType.equals(GameStatuses.GameType.TWO_PLAYER))))
                && !(GameBoardGui.P1hasMill || GameBoardGui.P2hasMill))
            setOl(Color.yellow);
        else if (willMove) {
            setOl(Color.yellow);
        }
        else
            setOl(Color.black);
        //Draw oval only changes the outline of the circle
        g2.setStroke(new BasicStroke(2));
        g2.setColor(outline);
        g2.drawOval((getWidth() / 2) - radius, (getHeight() / 2) - radius, diameter, diameter);
        g2.setColor(Color.black);
    }
}

