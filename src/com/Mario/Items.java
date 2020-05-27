package com.Mario;

/**
 * Write a description of class Items here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.*;

public class Items
{
    Mario mario;
    private int x, y;
    private boolean used;
    /**
     * Constructor for objects of class Items
     */
    public Items(int x, int y, Mario m)
    {
        mario = m;
        this.x = x;
        this.y = y;
        used = false;
    }

    public void tick()
    {
        if(mario.left) // if the player presses left
            x += mario.speed; //player goes left
        if(mario.right) // if the player press right
            x -= mario.speed; //player goes right
    }

    public void checkCollision()
    {
        if(!used){
            int chX = mario.ch.getX();
            int chY = mario.ch.getY();
            if(chX > x - 40 && chX < x + 50){
                if(chY > y - 40 && chY < y){
                    mario.ch.big = true;
                    used = true;
                    mario.playSound("PUP");
                    mario.score += 1000;
                }
            }
        }
    }

    public void render(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        if(!used)
            Sprites.renderMushroom(x, y, g);
    }
}

