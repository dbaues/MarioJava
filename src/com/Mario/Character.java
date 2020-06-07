package com.Mario;

import java.awt.*;

/**
 * Character attributes.
 * Original: 5/26/18.
 * Updated: 6/6/20.
 * @author Dan Bauer.
 */
public class Character
{
    public static final int WIDTH = 16 * Block.SCALE;
    public static final int HEIGHT = 16 * Block.SCALE;

    private final Mario mario;
    public boolean big;
    private int x, y;
    private final String name;

    /**
     * Constructor for objects of class Character.
     * @param name String of Player.
     * @param x Coordinate.
     * @param y Coordinate.
     * @param m Main Game instance.
     *          Allows access to attributes of M.
     */
    public Character(String name, int x, int y, Mario m)
    {
        this.name = name;
        this.x = x;
        this.y = y;
        this.mario = m;
    }

    /**
     * Resets the Character location.
     * Resets the stage after death.
     * @param x Coordinate.
     * @param y Coordinate.
     */
    public void setLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
        //mario.pf.reset();
    }

    /**
     * Executes on every "Tick"
     */
    public void tick()
    {
        // Checks Player Collisions.
        this.checkCollision();

        // Basic UP/DOWN motions.
        if(mario.up){ y-= Mario.jspeed; }
        if(mario.down){ y += Mario.jspeed; }

        // Previous Code.
        //if(mario.left){ x -= mario.speed; }
        //if(mario.right){ x += mario.speed; }
        /*
        //checkCollision();
        if(mario.jump){
            if(mario.up){ // if player presses the up arrow
                if(obsolete > 0){ // limits jump
                    y -= mario.jspeed;
                    obsolete--;
                    if(obsolete == 14)
                        mario.playSound(GameSound.JUMP_SOUND);
                }
                else{
                    obsolete = 15;
                    mario.up = false;
                    mario.jump = false;
                }
            }
            else
                obsolete = 15;
        }
        */
        /*if(mario.down && big) // if player presses the down arrow
        //big = false; //player ducks
        else if(big)
        big = true;*/

        // Character exits screen right upon Game ending.
        if(mario.finish)
            x += Mario.speed;
    }

    /**
     * Gets the Players X Coordinate.
     * @return x Coordinate.
     */
    public int getX() { return x; }

    /**
     * Gets the Players Y Coordinate.
     * @return y Coordinate.
     */
    public int getY() { return y; }

    /**
     * Draws the Character.
     * Uses Sprites static renders.
     * @param g Graphics object.
     */
    public void render(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        // Character Border.
        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, Character.WIDTH, Character.HEIGHT);

        if(this.name.toUpperCase().equals("ALTERNATE"))
            Sprites.renderAlt(x, y, big, g);
        else{
            Sprites.renderMario(x, y, big, "IDK YET", g);
        }
    }

    /**
     * New Player Collision Check.
     */
    private void checkCollision()
    {

    }
}
