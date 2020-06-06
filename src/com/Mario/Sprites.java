package com.Mario;
/**
 * Write a description of class Sprites here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.*;

public class Sprites
{
    private static final int SCALE = 3;

    public static void renderAlt(int x, int y, boolean big, Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        if(big){
            Color p = new Color(155, 68, 168);
            Color lb = new Color(0, 204, 255);
            Color db = new Color(0, 0, 102);
            Color r = new Color(255, 0, 0);
            g2.setColor(p);
            g2.fillRect(x+14, y-40+28, 12, 24);
            g2.setColor(lb);
            g2.fillRect(x+10, y-40+20, 6, 12);
            g2.fillRect(x+10, y-40+48, 6, 12);
            g2.fillRect(x+24, y-40+20, 6, 12);
            g2.fillRect(x+24, y-40+48, 6, 12);
            g2.setColor(db);
            g2.fillRect(x+6, y-40+12, 8, 16);
            g2.fillRect(x+6, y-40+52, 8, 16);
            g2.fillRect(x+26, y-40+12, 8, 16);
            g2.fillRect(x+26, y-40+52, 8, 16);
            g2.setColor(r);
            g2.fillRect(x, y-40, 12, 24);
            g2.fillRect(x, y-40+56, 12, 24);
            g2.fillRect(x+28, y-40, 12, 24);
            g2.fillRect(x+28, y-40+56, 12, 24);
            g2.fillRect(x+8, y-40+36, 24, 8);
            g2.fillRect(x+18, y-40+16, 4, 48);
            g2.setColor(lb);
            g2.fillRect(x+2, y-40+4, 8, 16);
            g2.fillRect(x+30, y-40+4, 8, 16);
            g2.fillRect(x+2, y-40+60, 8, 16);
            g2.fillRect(x+30, y-40+60, 8, 16);
            g2.fillRect(x+16, y-40+36, 8, 8);
            g2.fillRect(x+18, y-40+32, 4, 16);
            g2.setColor(db);
            g2.fillRect(x+4, y-40+8, 4, 8);
            g2.fillRect(x+4, y-40+64, 4, 8);
            g2.fillRect(x+32, y-40+8, 4, 8);
            g2.fillRect(x+32, y-40+64, 4, 8);
            g2.fillRect(x+18, y-40+36, 4, 8);
            g2.fillRect(x+6, y-40+36, 2, 8);
            g2.fillRect(x+32, y-40+36, 2, 8);
            g2.fillRect(x+18, y-40+12, 4, 4);
            g2.fillRect(x+18, y-40+64, 4, 4);
        }
        else{
            Color p = new Color(155, 68, 168);
            Color lb = new Color(0, 204, 255);
            Color db = new Color(0, 0, 102);
            Color r = new Color(255, 0, 0);
            g2.setColor(p);
            g2.fillRect(x+14, y+14, 12, 12);
            g2.setColor(lb);
            g2.fillRect(x+10, y+10, 6, 6);
            g2.fillRect(x+10, y+24, 6, 6);
            g2.fillRect(x+24, y+10, 6, 6);
            g2.fillRect(x+24, y+24, 6, 6);
            g2.setColor(db);
            g2.fillRect(x+6, y+6, 8, 8);
            g2.fillRect(x+6, y+26, 8, 8);
            g2.fillRect(x+26, y+6, 8, 8);
            g2.fillRect(x+26, y+26, 8, 8);
            g2.setColor(r);
            g2.fillRect(x, y, 12, 12);
            g2.fillRect(x, y+28, 12, 12);
            g2.fillRect(x+28, y, 12, 12);
            g2.fillRect(x+28, y+28, 12, 12);
            g2.fillRect(x+8, y+18, 24, 4);
            g2.fillRect(x+18, y+8, 4, 24);
            g2.setColor(lb);
            g2.fillRect(x+2, y+2, 8, 8);
            g2.fillRect(x+30, y+2, 8, 8);
            g2.fillRect(x+2, y+30, 8, 8);
            g2.fillRect(x+30, y+30, 8, 8);
            g2.fillRect(x+16, y+18, 8, 4);
            g2.fillRect(x+18, y+16, 4, 8);
            g2.setColor(db);
            g2.fillRect(x+4, y+4, 4, 4);
            g2.fillRect(x+4, y+32, 4, 4);
            g2.fillRect(x+32, y+4, 4, 4);
            g2.fillRect(x+32, y+32, 4, 4);
            g2.fillRect(x+18, y+18, 4, 4);
            g2.fillRect(x+6, y+18, 2, 4);
            g2.fillRect(x+32, y+18, 2, 4);
            g2.fillRect(x+18, y+6, 4, 2);
            g2.fillRect(x+18, y+32, 4, 2);
        }
    }

    public static void renderMario(int x, int y, boolean big, String type, Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        Sprites.renderAlt(x, y, big, g);
        /*if(big){
            g2.fillRect(x, y-40, 40, 80);
        }
        else{
            g2.fillRect(x, y, 40, 40);
        }*/
    }

    /**
     * Renders Default Item.
     * @param x Coordinate.
     * @param y Coordinate.
     * @param g Graphics instance.
     */
    public static void renderDefaultItem(int x, int y, Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(x, y, 40, 40);
    }

    /**
     * Renders Mushroom Item.
     * @param x Coordinate.
     * @param y Coordinate.
     * @param g Graphics Instance.
     */
    public static void renderMushroom(int x, int y, Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        // Draws Red Mushroom.
        Sprites.renderMushroom(x, y, g2, Color.RED);
    }

    /**
     * Renders 1-Up Mushroom Item.
     * @param x Coordinate.
     * @param y Coordinate.
     * @param g Graphics Instance.
     */
    public static void render1Up(int x, int y, Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        // Draws Green Mushroom.
        Sprites.renderMushroom(x, y, g2, new Color(68, 168, 50));
    }

    /**
     * Renders a colored Mushroom Item.
     * @param x Coordinate.
     * @param y Coordinate.
     * @param g2 Graphics object.
     * @param color The Color of the Mushroom.
     *              (Red for Power up).
     *              (Green for 1-Up).
     */
    private static void renderMushroom(int x, int y, Graphics2D g2, Color color)
    {
        int scale = Sprites.SCALE;

        g2.setColor(Color.WHITE);
        g2.fillRect(x+(5*scale), y+(11*scale), 6*scale, 5*scale);
        g2.fillRect(x+(4*scale), y+(12*scale), 8*scale, 3*scale);

        g2.setColor(Color.ORANGE);
        g2.fillRect(x+(6*scale), y, 4*scale, scale);
        g2.fillRect(x+(5*scale), y+(scale), 4*scale, scale);
        g2.fillRect(x+(4*scale), y+(2*scale), 4*scale, scale);
        g2.fillRect(x+(3*scale), y+(3*scale), 5*scale, scale);
        g2.fillRect(x+(2*scale), y+(4*scale), 12*scale, scale);
        g2.fillRect(x+(scale), y+(5*scale), 14*scale, 2*scale);
        g2.fillRect(x, y+(7*scale), 16*scale, 4*scale);
        g2.fillRect(x+(scale), y+(11*scale), scale, scale);
        g2.fillRect(x+(14*scale), y+(11*scale), scale, scale);
        g2.fillRect(x+(10*scale), y+(13*scale), scale, 2*scale);
        g2.fillRect(x+(9*scale), y+(15*scale), scale, scale);

        g2.setColor(color);
        g2.fillRect(x+(9*scale), y+(scale), 2*scale, scale);
        g2.fillRect(x+(8*scale), y+(2*scale), 4*scale, scale);
        g2.fillRect(x+(8*scale), y+(3*scale), 5*scale, scale);
        g2.fillRect(x+(9*scale), y+(4*scale), 4*scale, scale);
        g2.fillRect(x+(3*scale), y+(5*scale), 3*scale, 5*scale);
        g2.fillRect(x+(2*scale), y+(6*scale), 5*scale, 3*scale);
        g2.fillRect(x+(12*scale), y+(7*scale), 2*scale, 2*scale);
        g2.fillRect(x+(13*scale), y+(8*scale), 2*scale, 2*scale);
        g2.fillRect(x+(2*scale), y+(11*scale), 3*scale, scale);
        g2.fillRect(x+(11*scale), y+(11*scale), 3*scale, scale);
    }

    /**
     * Renders Fire Flower Item.
     * @param x Coordinate.
     * @param y Coordinate.
     * @param g Graphics Instance.
     */
    public static void renderFireFlower(int x, int y, Graphics g)
    {
        //Graphics2D g2 = (Graphics2D) g;

        Sprites.renderDefaultItem(x, y, g);
    }

    public static void renderGoomba(int x, int y, Graphics g)
    {
    }

    public static void renderKoopa(int x, int y, Graphics g)
    {
    }
}
