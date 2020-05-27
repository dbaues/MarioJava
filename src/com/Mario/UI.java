package com.Mario;
/**
 * User-Interface elements
 *
 * @Daniel Bauer
 * @5/26/18
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.*;

public class UI
{
    Mario mario;
    //private int x, y;
    private String name;
    private Color fade;
    private int a;
    /**
     * Constructor for objects of class UI
     */
    public UI(Mario m)
    {
        mario = m;
        a = 0;
        //fade = new Color(0, 0, 0, a);
    }

    /**
     * @return the a value
     */
    public int getA()
    {
        return a;
    }

    /**
     * used to reset the a value and resets the color
     * @param sets a equal to a
     */
    public void setA(int a)
    {
        this.a = a;
        fade = new Color(0, 0, 0, a);
    }

    /**
     * runs every frame
     */
    public void tick()
    {
        if(mario.finish || mario.gameover){
            if(a <= 255){
                fade = new Color(0, 0, 0, a);
                a += 1;
            }
        }

        if(mario.died){
            if(a <= 255){
                fade = new Color(0, 0, 0, a);
                a += 5;
            }
        }
    }

    /**
     * draws the UI elements
     */
    public void render(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.drawString(mario.pf.getX() + " " + mario.ch.getY(), 30, 30);
        g2.drawString(mario.timer + "", 30, 60);
        g2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));

        if(mario.finish || mario.died || mario.gameover){
            g2.setColor(fade);
            g2.fillRect(0, 0, mario.getWidth(), mario.getHeight());
            if(mario.died && a >= 255 && !mario.gameover){
                g2.setColor(Color.WHITE);
                g2.drawString("X" + mario.lives, mario.getWidth()/2, mario.getHeight()/2);
            }
            if(mario.gameover && a >= 255){
                g2.setColor(Color.WHITE);
                g2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 45));
                g2.drawString("GAMEOVER", mario.getWidth()/2 - 100, mario.getHeight()/2);
            }
        }
        g2.setColor(Color.WHITE);
        g2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        g2.drawString(mario.name, mario.getWidth()/5 - 50, 50);
        g2.drawString("WORLD", mario.getWidth()/5 * 3 - 45, 50);
        g2.drawString("TIME", mario.getWidth()/5 * 4 - 35, 50);
        g2.drawString(mario.score + "", mario.getWidth()/5 - 50, 75);
        g2.drawString("x" + mario.coins, mario.getWidth()/5 * 2 - 25, 75);
        g2.drawString("1-1", mario.getWidth()/5 * 3 - 25, 75);
        g2.drawString(mario.timer + "", mario.getWidth()/5 * 4 - 25, 75);

        g2.setColor(Color.YELLOW);
        g2.fillRect(mario.getWidth()/5 * 2 - 45, 55, 8, 20);
        g2.fillRect(mario.getWidth()/5 * 2 - 49, 59, 16, 12);
        g2.setColor(new Color(244, 215, 66));
        g2.fillRect(mario.getWidth()/5 * 2 - 37, 59, 4, 12);
        g2.fillRect(mario.getWidth()/5 * 2 - 45, 71, 8, 4);
        g2.setColor(new Color(244, 191, 0));
        g2.fillRect(mario.getWidth()/5 * 2 - 45, 59, 8, 12);

    }
}
