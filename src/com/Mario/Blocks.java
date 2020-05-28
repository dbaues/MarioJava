package com.Mario;

import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.awt.geom.*;

/**
 * A Block object.
 * Contains attributes of a Block and renders a Block.
 * Dan Bauer.
 */
public class Blocks
{
    private String type;
    private int x, y;
    private int coins, score;
    //Graphics g;
    public int hits = 8;

    // Default Brown color for blocks.
    public static Color brown = new Color(130, 76, 40);

    /* Second Constructor. UNUSED. Set for Removal.
    public Blocks(String type, int x, int y, Graphics g)
    {
        this.type = type.toUpperCase();
        this.x = x;
        this.y = y;
        this.g = g;
    }
    */

    /**
     * Constructor for objects of class Blocks
     * @param type of block, x and y cordinates, and g
     */
    public Blocks(String type, int x, int y)
    {
        this.type = type.toUpperCase();
        this.x = x;
        this.y = y;
    }

    public int getHits()
    {
        return hits;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public String getType()
    {
        return type;
    }

    public int getCoins()
    {
        return coins;
    }

    public int getScore()
    {
        return score;
    }

    public void action()
    {
        if(type.equals("MYSTERY")){
            type = "BLOCK";
        }
        else if(type.equals("BRICK")){
            type = "BLOCK";
        }
    }

    /**
     * Draws the block based on type
     */
    public void render(int x, Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        // Draws Background per block.
        g2.setColor(Color.BLACK);
        if((type.equals("PIPE_L"))||(type.equals("PIPE_R")))
            g2.fillRect(0,0,0,0);
        else
            g2.fillRect(x, y, 50, 50);

        // Draws an Individual Block.
        if(type.equals("MYSTERY")){ // Draws the "?" Block.
            Color mystery = new Color(247, 194, 37);
            Color alternate = Blocks.brown;
            g2.setColor(mystery);
            g2.fillRect(x + 3, y + 3, 44, 44);
            g2.fillRect(x + 4, y + 2, 42, 46);
            g2.fillRect(x + 2, y + 4, 46, 42);
            g2.setColor(Color.BLACK);
            g2.fillRect(x + 5, y + 5, 2, 2);
            g2.fillRect(x + 43, y + 5, 2, 2);
            g2.fillRect(x + 5, y + 43, 2, 2);
            g2.fillRect(x + 43, y + 43, 2, 2);
            g2.setColor(alternate);
            g2.fillRect(x + 13, y + 11, 6, 9);
            g2.fillRect(x + 16, y + 8, 15, 6);
            g2.fillRect(x + 28, y + 11, 6, 15);
            g2.fillRect(x + 22, y + 21, 6, 11);
            g2.fillRect(x + 22, y + 35, 6, 6);
        }
        else if(type.equals("BRICK")){  // Draws the "BRICK" Block.
            g2.setColor(Blocks.brown);
            g2.fillRect(x + 2, y + 2, 22, 10);
            g2.fillRect(x + 26, y + 2, 22, 10);
            g2.fillRect(x + 2, y + 14, 10, 10);
            g2.fillRect(x + 14, y + 14, 22, 10);
            g2.fillRect(x + 38, y + 14, 10, 10);
            g2.fillRect(x + 2, y + 26, 22, 10);
            g2.fillRect(x + 26, y + 26, 22, 10);
            g2.fillRect(x + 2, y + 38, 10, 10);
            g2.fillRect(x + 14, y + 38, 22, 10);
            g2.fillRect(x + 38, y + 38, 10, 10);
        }
        else if(type.equals("GROUND")){ // Draws the "GROUND" Block.
            g2.setColor(Blocks.brown);
            g2.fillRect(x + 2, y + 2, 30, 34);
            g2.fillRect(x + 33, y + 2, 15, 24);
            g2.fillRect(x + 2, y + 38, 25, 10);
            g2.setColor(Color.BLACK);
            g2.fillRect(x + 27, y + 27, 22, 22);
            g2.setColor(Blocks.brown);
            g2.fillRect(x + 28, y + 28, 21, 20);
        }
        else if(type.equals("BLOCK")){  // Draws Generic Block.
            g2.setColor(Blocks.brown);
            g2.fillRect(x + 3, y + 3, 44, 44);
            g2.fillRect(x + 4, y + 2, 42, 46);
            g2.fillRect(x + 2, y + 4, 46, 42);
            g2.setColor(Color.BLACK);
            g2.fillRect(x + 5, y + 5, 2, 2);
            g2.fillRect(x + 43, y + 5, 2, 2);
            g2.fillRect(x + 5, y + 43, 2, 2);
            g2.fillRect(x + 43, y + 43, 2, 2);
        }
        else if(type.equals("PIPE_TOP_L")){ //Draws a part of the "PIPE".
            g2.setColor(Color.GREEN);
            g2.fillRect(x + 2, y + 2, 48, 46);
        }
        else if(type.equals("PIPE_TOP_R")){ //Draws a part of the "PIPE".
            g2.setColor(Color.GREEN);
            g2.fillRect(x, y + 2, 48, 46);
        }
        else if(type.equals("PIPE_L")){ // Draws a part of the "PIPE".
            g2.setColor(Color.BLACK);
            g2.fillRect(x + 8, y, 42, 50);
            g2.setColor(Color.GREEN);
            g2.fillRect(x + 10, y, 40, 50);
        }
        else if(type.equals("PIPE_R")){ // Draws a part of the "PIPE".
            g2.setColor(Color.BLACK);
            g2.fillRect(x, y, 42, 50);
            g2.setColor(Color.GREEN);
            g2.fillRect(x, y, 40, 50);
        }
        else if(type.equals("STONE")){  // Draws the "STONE" Block.
            // Create Graphics here.
        }
        else if(type.equals("TEST")){   // Draws the "TEST" Block.
            g2.setColor(Color.ORANGE);
            g2.setColor(Blocks.brown);
            g2.fillRect(x + 2, y + 2, 46, 46);
        }
    }

    public void checkCollision(int px, int py)
    {
        if(px > x - 40 && px < x + 50){
            if(py > y + 20 && py <= y + 50){
                action();
            }
        }
    }
}

