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

    public static void renderMushroom(int x, int y, Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.ORANGE);
        g2.fillRect(x, y, 40, 40);
    }

    public static void renderGoomba(int x, int y, Graphics g)
    {
    }

    public static void renderKoopa(int x, int y, Graphics g)
    {
    }
}
