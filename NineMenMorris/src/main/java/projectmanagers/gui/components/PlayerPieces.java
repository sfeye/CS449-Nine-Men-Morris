package main.java.projectmanagers.gui.components;

import main.java.projectmanagers.logic.MillConditions;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayerPieces extends JButton {
    private Color bg;
    private Color outline;
    private int diameter;
    private int x, y;
    public static boolean isSelected = false;
    public static boolean canSelect = false;
    private boolean mouseClicked = false, mouseOver = false, mousePressed = false;

    public PlayerPieces(Color bg, Color outline) {
        super();
        this.bg = bg;
        this.outline = outline;
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
    public void setXCoordinate(int x){ this.x = x; }
    public void setYCoordinate(int y){ this.y = y; }
    public int getXCoordinate(){ return x; }
    public int getYCoordinate(){ return y; }
    public void setBg (Color bg) { this.bg = bg; }
    public void setOL (Color outline)
    {
        this.outline = outline;
    }
    public int getDiameter() {
        diameter = Math.min(getWidth(), getHeight());
        return diameter;
    }
    @Override
    public boolean contains (int x, int y) {
        int radius = diameter / 2;
        return Point2D.distance(x, y, getWidth() / 2, getHeight() / 2) < radius;
    }
    @Override
    public void paintComponent(Graphics g) {
        diameter = getDiameter();
        int radius = diameter / 2;
        g.setColor(outline);
        g.drawOval((getWidth() / 2) - radius, (getHeight() / 2) - radius, diameter, diameter);
        g.setColor(bg);
        g.fillOval((getWidth() / 2) - radius, (getHeight() / 2) - radius, diameter, diameter);
        if(isSelected == true) {
            g.setColor(Color.yellow);
            g.drawOval((getWidth() / 2) - radius, (getHeight() / 2) - radius, diameter, diameter);
        }
    }
}

