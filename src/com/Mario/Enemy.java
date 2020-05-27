package com.Mario;

/**
 * Write a description of class Enemies here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.*;

public class Enemy
{
    private String type;
    private int x, y;
    private int left, right;
    Mario mario;
    private boolean toggle;
    /**
     * Constructor for objects of class Enemies
     */
    public Enemy(String type, int x, int y, Mario m)
    {
        mario = m;
        this.type = type.toUpperCase();
        this.x = x;
        this.y = y;
        toggle = true;
    }

    /**
     * runs every frame;
     */
    public void tick()
    {
        if(!mario.finish){
            if(mario.left){ // if the player presses left
                if(x < 0)
                    x += mario.speed; //player goes left
            }
            if(mario.right) // if the player press right
                x -= mario.speed; //player goes right
        }
        if(toggle && x > left){
            x -= mario.speed;
        }
        if(!toggle && x < right){
            x += mario.speed;
        }
    }

    /**
     * sets the motion bounds
     */
    public void setBounds(int left, int right)
    {
        this.left = left;
        this.right = right;
    }

    /**
     * checks for collisions
     */
    public void checkCollision()
    {
        if(x <= left)
            toggle = false;
        else if(x >= right)
            toggle = true;
    }

    /**
     * draws the enemy elements
     */
    public void render(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        if(type.equals("GOOMBA")){
            g2.setColor(Color.ORANGE);
            g2.fillOval(x, y, 60, 60);
        }
        else if(type.equals("KOOPA")){
            g2.setColor(Color.GREEN);
            g2.fillOval(x, y, 60, 60);
        }
    }
}
