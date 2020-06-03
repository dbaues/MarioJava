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
    // Static Type strings.
    public static final String MUSHROOM = "Mushroom";
    public static final String FIRE_FLOWER = "Fire Flower";
    public static final String _1UP = "1-Up";

    // Class Fields.
    Mario mario;
    private int x, y;
    private boolean used;
    private final String itemType;

    /**
     * Constructor for objects of class Items
     */
    public Items(int x, int y, String type, Mario m)
    {
        this.mario = m; // Main Game instance.
        this.x = x; // X Coordinates.
        this.y = y; // Y Coordinates.
        this.itemType = type; // Item type (Mushroom, 1UP, Flowers).
        this.used = false; // Determines whether an Item has been used.
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

            // Checks to see if Player has entered the Item Hit box.
            Rectangle bounds = new Rectangle(x, y, 90, 50); // Item Hit Box.
            if(bounds.contains(chX, chY))
                action();
        }
    }

    /**
     * Draws the Item (Mushroom).
     * @param g A Graphics object.
     */
    public void render(Graphics g)
    {
        if(!used) { // If this Item has not been used previously.
            switch (itemType) {
                case Items.MUSHROOM -> Sprites.renderMushroom(x, y, g);
                case Items.FIRE_FLOWER -> Sprites.renderFireFlower(x, y, g);
                case Items._1UP -> Sprites.render1Up(x, y, g);
                default -> Sprites.renderDefaultItem(x, y, g);
            }
        }
    }

    /**
     * Performs the Items action when Player hits it.
     */
    private void action()
    {
        used = true;
        mario.playSound(GameSound.POWERUP_SOUND_ID);
        mario.score += 1000;
        switch (itemType) {
            case Items.MUSHROOM -> mario.ch.big = true;
            case Items.FIRE_FLOWER -> mario.ch.big = false; // Add Implementation Later.
            case Items._1UP -> mario.lives++;
        }
    }
}