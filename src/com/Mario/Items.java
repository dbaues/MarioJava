package com.Mario;

import java.awt.*;

/**
 * Contains attributes of an Item (Mushroom).
 *
 * @author Dan Bauer
 * @version 2.0
 */
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

    /**
     * Allows the item to move with the Stage.
     */
    public void tick()
    {
        if(mario.left) // When Left is pressed.
            x += Mario.speed; // "Player" goes left. In Actuality stage moves.
        if(mario.right) // When Right is pressed.
            x -= Mario.speed; // "Player" goes right. In Actuality stage moves.
    }

    /**
     * Checks if Player has contacted this Item (Mushroom).
     */
    public void checkCollision()
    {
        if(!used){  // If this Item has not been used previously.
            int chX = mario.ch.getX();  // Gets the players coordinates.
            int chY = mario.ch.getY();

            if(chX > x - 40 && chX < x + 50){   // Checks if player collided with Item Hit Box.
                if(chY > y - 40 && chY < y){
                    mario.ch.big = true;
                    used = true;
                    mario.playSound(GameSound.POWERUP_SOUND);
                    mario.score += 1000;
                }
            }
        }
    }

    /**
     * Draws the Item (Mushroom).
     * @param g A Graphics object.
     */
    public void render(Graphics g)
    {
        //Graphics2D g2 = (Graphics2D) g;
        if(!used)
            Sprites.renderMushroom(x, y, g);
    }
}

