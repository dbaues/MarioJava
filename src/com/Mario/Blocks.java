package com.Mario;

import java.awt.*;

/**
 * A Block object.
 * Contains attributes of a Block and renders a Block.
 * @author Dan Bauer.
 */
public class Blocks
{
    public static final int SCALE = 3;
    public static final int WIDTH = 16 * Blocks.SCALE;
    public static final int HEIGHT = 16 * Blocks.SCALE;

    // Block ID's
    public static final int MYSTERY = 0;
    public static final int BRICK = 1;
    public static final int GROUND = 2;
    public static final int BLOCK = 3;
    public static final int STONE = 4;
    public static final int PIPE_L = 5;
    public static final int PIPE_R = 6;
    public static final int PIPE_TOP_L = 7;
    public static final int PIPE_TOP_R = 8;
    public static final int TEST = 99;

    private String type;
    private int x, y;
    private int coins, score, typeID;
    public int hits = 8;

    // Default Brown color for blocks.
    public static Color brown = new Color(130, 76, 40);
    public static Color off_white = new Color(240, 231, 221);

    /**
     * Constructor for objects of class Blocks
     * @param type of Block.
     * @param x Integer Coordinate.
     * @param y Integer Coordinate.
     */
    public Blocks(String type, int x, int y)
    {
        this.type = type.toUpperCase();
        this.x = x;
        this.y = y;
    }

    // Return methods.
    public int getHits() { return hits; }

    public int getX() { return x; }

    public int getY() { return y; }

    public String getType() { return type; }

    public int getCoins() { return coins; }

    public int getScore() { return score; }

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
        int scale = Blocks.SCALE;

        // Draws Background per block.
        g2.setColor(Color.BLACK);
        if((type.equals("PIPE_L"))||(type.equals("PIPE_R")))
            g2.fillRect(0,0,0,0);
        else
            g2.fillRect(x, y, 50, 50);

        // Draws an Individual Block.
        // Draws proper Original Super Mario Bros Blocks.
        switch(typeID){
            case Blocks.MYSTERY -> {// Draws the "?" (MYSTERY) Block.
                Color mystery = new Color(247, 194, 37);
                Color alternate = new Color(235, 128, 7);

                g2.setColor(Color.BLACK);
                g2.fillRect(x, y, 16*scale, 16*scale);

                g2.setColor(mystery);
                g2.fillRect(x+(scale), y+(scale), 14*scale, 14*scale);

                g2.setColor(Color.BLACK);
                g2.fillRect(x+(5*scale), y+(4*scale), 2*scale, 4*scale);
                g2.fillRect(x+(6*scale), y+(4*scale), 3*scale, scale);
                g2.fillRect(x+(10*scale), y+(5*scale), 2*scale, 4*scale);
                g2.fillRect(x+(8*scale), y+(8*scale), 2*scale, 3*scale);
                g2.fillRect(x+(8*scale), y+(12*scale), 2*scale, 2*scale);
                g2.fillRect(x+(2*scale), y+(2*scale), scale, scale);
                g2.fillRect(x+(13*scale), y+(2*scale), scale, scale);
                g2.fillRect(x+(2*scale), y+(13*scale), scale, scale);
                g2.fillRect(x+(13*scale), y+(13*scale), scale, scale);

                g2.setColor(alternate);
                g2.fillRect(x+(scale), y, 14*scale, scale);
                g2.fillRect(x, y+(scale), scale, 14*scale);
                g2.fillRect(x+(4*scale), y+(4*scale), 2*scale, 3*scale);
                g2.fillRect(x+(5*scale), y+(3*scale), 5*scale, scale);
                g2.fillRect(x+(9*scale), y+(4*scale), 2*scale, 4*scale);
                g2.fillRect(x+(8*scale), y+(7*scale), scale, scale);
                g2.fillRect(x+(7*scale), y+(8*scale), 2*scale, 2*scale);
                g2.fillRect(x+(7*scale), y+(11*scale), 2*scale, 2*scale);
            }
            case Blocks.BRICK -> { // Draws the "BRICK" Block.
                g2.setColor(Color.BLACK);
                g2.fillRect(x, y, 16*scale, 16*scale);

                g2.setColor(Blocks.brown);
                g2.fillRect(x, y, 7*scale, 3*scale);
                g2.fillRect(x+(8*scale), y, 7*scale, 3*scale);
                g2.fillRect(x, y+(4*scale), 3*scale, 3*scale);
                g2.fillRect(x+(4*scale), y+(4*scale), 7*scale, 3*scale);
                g2.fillRect(x+(12*scale), y+(4*scale), 4*scale, 3*scale);
                g2.fillRect(x, y+(8*scale), 7*scale, 3*scale);
                g2.fillRect(x+(8*scale), y+(8*scale), 7*scale, 3*scale);
                g2.fillRect(x, y+(12*scale), 3*scale, 3*scale);
                g2.fillRect(x+(4*scale), y+(12*scale), 7*scale, 3*scale);
                g2.fillRect(x+(12*scale), y+(12*scale), 4*scale, 3*scale);

                g2.setColor(new Color(245, 218, 188)); // Off-White v2.
                g2.fillRect(x, y, 16*scale, scale);
            }
            case Blocks.GROUND -> { // Draws the "GROUND" Block.
                g2.setColor(Blocks.brown);
                g2.fillRect(x, y, 16*scale, 16*scale);

                g2.setColor(Blocks.off_white);
                g2.fillRect(x, y+(scale), scale, 9*scale);
                g2.fillRect(x, y+(11*scale), scale, 4*scale);
                g2.fillRect(x+(scale), y, 8*scale, scale);
                g2.fillRect(x+(11*scale), y, 4*scale, scale);
                g2.fillRect(x+(15*scale), y+(scale), scale, scale*4);
                g2.fillRect(x, y+(11*scale), 2*scale, scale);
                g2.fillRect(x+(2*scale), y+(12*scale), 2*scale, scale);
                g2.fillRect(x+(4*scale), y+(13*scale), 3*scale, scale);
                g2.fillRect(x+(8*scale), y+(12*scale), scale, 3*scale);
                g2.fillRect(x+(9*scale), y+(10*scale), scale, 2*scale);
                g2.fillRect(x+(10*scale), y+(6*scale), scale, 4*scale);
                g2.fillRect(x+(10*scale), y+(scale), scale, 4*scale);
                g2.fillRect(x+(11*scale), y+(6*scale), 4*scale, scale);

                g2.setColor(Color.BLACK);
                g2.fillRect(x+(scale), y+(15*scale), 6*scale, scale);
                g2.fillRect(x+(8*scale), y+(15*scale), 7*scale, scale);
                g2.fillRect(x+(14*scale), y+(14*scale), scale, scale);
                g2.fillRect(x+(15*scale), y+(6*scale), scale, 9*scale);
                g2.fillRect(x, y+(10*scale), 2*scale, scale);
                g2.fillRect(x+(2*scale), y+(11*scale), 2*scale, scale);
                g2.fillRect(x+(4*scale), y+(12*scale), 4*scale, scale);
                g2.fillRect(x+(7*scale), y+(12*scale), scale, 3*scale);
                g2.fillRect(x+(8*scale), y+(10*scale), scale, 2*scale);
                g2.fillRect(x+(9*scale), y, scale, 10*scale);
                g2.fillRect(x+(11*scale), y+(4*scale), scale, 2*scale);
                g2.fillRect(x+(11*scale), y+(5*scale), 4*scale, scale);
            }
            case Blocks.BLOCK -> { // Draws Generic Block.
                g2.setColor(Color.BLACK);
                g2.fillRect(x, y, 16*scale, 16*scale);

                g2.setColor(Blocks.brown);
                g2.fillRect(x+(scale), y+(scale), 14*scale, 14*scale);

                g2.setColor(Color.BLACK);
                g2.fillRect(x+(2*scale), y+(2*scale), scale, scale);
                g2.fillRect(x+(13*scale), y+(13*scale), scale, scale);
                g2.fillRect(x+(2*scale), y+(13*scale), scale, scale);
                g2.fillRect(x+(13*scale), y+(2*scale), scale, scale);
            }
            case Blocks.STONE -> { // Draws the "STONE" Block.
                g2.setColor(Blocks.brown);
                g2.fillRect(x, y, 16*scale, 16*scale);

                g2.setColor(new Color(245, 218, 188)); // Off-White v2.
                g2.fillRect(x, y+(scale), scale, 14*scale);
                g2.fillRect(x+(scale), y+(2*scale), scale, 12*scale);
                g2.fillRect(x+(2*scale), y+(3*scale), scale, 10*scale);
                g2.fillRect(x+(3*scale), y+(8*scale), scale, 8*scale);
                g2.fillRect(x+(scale), y, 14*scale, scale);
                g2.fillRect(x+(2*scale), y+(scale), 12*scale, scale);
                g2.fillRect(x+(3*scale), y+(2*scale), 10*scale, scale);
                g2.fillRect(x+(4*scale), y+(3*scale), 8*scale, scale);

                g2.setColor(Color.BLACK);
                g2.fillRect(x, y+(15*scale), 15*scale, scale);
                g2.fillRect(x+(scale), y+(14*scale), 13*scale, scale);
                g2.fillRect(x+(2*scale), y+(13*scale), 11*scale, scale);
                g2.fillRect(x+(3*scale), y+(12*scale), 9*scale, scale);
                g2.fillRect(x+(12*scale), y+(3*scale), scale, 9*scale);
                g2.fillRect(x+(13*scale), y+(2*scale), scale, 11*scale);
                g2.fillRect(x+(14*scale), y+(scale), scale, 13*scale);
                g2.fillRect(x+(15*scale), y, scale, 15*scale);
            }
            case Blocks.PIPE_L -> { // Draws the Left side of the Pipe.
                g2.setColor(Color.BLACK);
                g2.fillRect(x+(3*scale), y, scale, 16*scale);

                g2.setColor(Color.GREEN);
                g2.fillRect(x+(4*scale), y, 13*scale, 16*scale);

                g2.setColor(new Color(26, 125, 28)); // Darker green.S
                g2.fillRect(x+(7*scale), y, 2*scale, 16*scale);
                g2.fillRect(x+(12*scale), y, scale, 16*scale);
                //g2.fillRect(x+(15*scale), y, scale, 16*scale);
            }
            case Blocks.PIPE_R -> { // Draws the Right side of the Pipe.
                g2.setColor(Color.BLACK);
                g2.fillRect(x+(13*scale), y, scale, 16*scale);

                g2.setColor(Color.GREEN);
                g2.fillRect(x+(7*scale), y, 5*scale, 16*scale);

                g2.setColor(new Color(26, 125, 28));
                g2.fillRect(x, y, 7*scale, 16*scale);
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j < 8; j++)
                        g2.fillRect(x+((6+i)*scale), y+(((j*2)+(i%2))*scale), scale, scale);
                }
            }
            case Blocks.PIPE_TOP_L -> { // Draws the Left Top side of the Pipe.
                g2.setColor(Color.BLACK);
                g2.fillRect(x+(scale), y, 15*scale, 15*scale);
                g2.fillRect(x+(4*scale), y+(15*scale), 13*scale, scale);

                g2.setColor(Color.GREEN);
                g2.fillRect(x+(2*scale), y+(scale), 15*scale, 13*scale);

                g2.setColor(new Color(26, 125, 28)); // Darker green.
                g2.fillRect(x+(2*scale), y+(2*scale), 3*scale, scale);
                g2.fillRect(x+(5*scale), y+(2*scale), 2*scale, 12*scale);
                g2.fillRect(x+(12*scale), y+(2*scale), scale, 12*scale);
                g2.fillRect(x+(12*scale), y+(2*scale), 3*scale, scale);
                g2.fillRect(x+(15*scale), y+(2*scale), scale, 12*scale);
            }
            case Blocks.PIPE_TOP_R -> {
                g2.setColor(Color.BLACK);
                g2.fillRect(x, y, 15*scale, 15*scale);
                g2.fillRect(x, y+(15*scale), 13*scale, scale);

                g2.setColor(Color.GREEN);
                g2.fillRect(x, y+(scale), 14*scale, 13*scale);

                g2.setColor(new Color(26, 125, 28));
                g2.fillRect(x, y+(2*scale), 8*scale, 12*scale);
                for(int i = 0; i < 5; i++){
                    for(int j = 0; j < 6; j++)
                        g2.fillRect(x+((7+i)*scale), y+(((j*2)+(i%2)+2)*scale), scale, scale);
                }
            }
        }




        if(type.equals("MYSTERY")){ // Draws the "?" (MYSTERY) Block.
            // Proper Orginal Mystery Block graphic.
            Color mystery = new Color(247, 194, 37);
            Color alternate = new Color(235, 128, 7);//Blocks.brown;
            g2.setColor(Color.BLACK);
            g2.fillRect(x, y, 16*scale, 16*scale);
            g2.setColor(mystery);
            g2.fillRect(x+(1*scale), y+(1*scale), 14*scale, 14*scale);
            g2.setColor(Color.BLACK);
            g2.fillRect(x+(5*scale), y+(4*scale), 2*scale, 4*scale);
            g2.fillRect(x+(6*scale), y+(4*scale), 3*scale, 1*scale);
            g2.fillRect(x+(10*scale), y+(5*scale), 2*scale, 4*scale);
            g2.fillRect(x+(8*scale), y+(8*scale), 2*scale, 3*scale);
            g2.fillRect(x+(8*scale), y+(12*scale), 2*scale, 2*scale);
            g2.fillRect(x+(2*scale), y+(2*scale), 1*scale, 1*scale);
            g2.fillRect(x+(13*scale), y+(2*scale), 1*scale, 1*scale);
            g2.fillRect(x+(2*scale), y+(13*scale), 1*scale, 1*scale);
            g2.fillRect(x+(13*scale), y+(13*scale), 1*scale, 1*scale);
            g2.setColor(alternate);
            g2.fillRect(x+(1*scale), y, 14*scale, 1*scale);
            g2.fillRect(x, y+(1*scale), scale, 14*scale);
            g2.fillRect(x+(4*scale), y+(4*scale), 2*scale, 3*scale);
            g2.fillRect(x+(5*scale), y+(3*scale), 5*scale, 1*scale);
            g2.fillRect(x+(9*scale), y+(4*scale), 2*scale, 4*scale);
            g2.fillRect(x+(8*scale), y+(7*scale), 1*scale, 1*scale);
            g2.fillRect(x+(7*scale), y+(8*scale), 2*scale, 2*scale);
            g2.fillRect(x+(7*scale), y+(11*scale), 2*scale, 2*scale);
        }
        else if(type.equals("BRICK")){  // Draws the "BRICK" Block.
            // Proper Original SUPER MARIO BROS. Brick Graphics.
            g2.setColor(Color.BLACK);
            g2.fillRect(x, y, 16*scale, 16*scale);
            g2.setColor(Blocks.brown);
            g2.fillRect(x, y, 7*scale, 3*scale);
            g2.fillRect(x+(8*scale), y, 7*scale, 3*scale);
            g2.fillRect(x, y+(4*scale), 3*scale, 3*scale);
            g2.fillRect(x+(4*scale), y+(4*scale), 7*scale, 3*scale);
            g2.fillRect(x+(12*scale), y+(4*scale), 4*scale, 3*scale);
            g2.fillRect(x, y+(8*scale), 7*scale, 3*scale);
            g2.fillRect(x+(8*scale), y+(8*scale), 7*scale, 3*scale);
            g2.fillRect(x, y+(12*scale), 3*scale, 3*scale);
            g2.fillRect(x+(4*scale), y+(12*scale), 7*scale, 3*scale);
            g2.fillRect(x+(12*scale), y+(12*scale), 4*scale, 3*scale);
            g2.setColor(new Color(245, 218, 188));
            g2.fillRect(x, y, 16*scale, scale);

        }
        else if(type.equals("GROUND")){ // Draws the "GROUND" Block.
            g2.setColor(Blocks.brown);
            g2.fillRect(x, y, 16*scale, 16*scale);
            g2.setColor(Blocks.off_white);
            g2.fillRect(x, y+(scale), scale, 9*scale);
            g2.fillRect(x, y+(11*scale), scale, 4*scale);
            g2.fillRect(x+(scale), y, 8*scale, scale);
            g2.fillRect(x+(11*scale), y, 4*scale, scale);
            g2.fillRect(x+(15*scale), y+(scale), scale, scale*4);
            g2.fillRect(x, y+(11*scale), 2*scale, scale);
            g2.fillRect(x+(2*scale), y+(12*scale), 2*scale, scale);
            g2.fillRect(x+(4*scale), y+(13*scale), 3*scale, scale);
            g2.fillRect(x+(8*scale), y+(12*scale), scale, 3*scale);
            g2.fillRect(x+(9*scale), y+(10*scale), scale, 2*scale);
            g2.fillRect(x+(10*scale), y+(6*scale), scale, 4*scale);
            g2.fillRect(x+(10*scale), y+(scale), scale, 4*scale);
            g2.fillRect(x+(11*scale), y+(6*scale), 4*scale, scale);
            g2.setColor(Color.BLACK);
            g2.fillRect(x+(scale), y+(15*scale), 6*scale, scale);
            g2.fillRect(x+(8*scale), y+(15*scale), 7*scale, scale);
            g2.fillRect(x+(14*scale), y+(14*scale), scale, scale);
            g2.fillRect(x+(15*scale), y+(6*scale), scale, 9*scale);
            g2.fillRect(x, y+(10*scale), 2*scale, scale);
            g2.fillRect(x+(2*scale), y+(11*scale), 2*scale, scale);
            g2.fillRect(x+(4*scale), y+(12*scale), 4*scale, scale);
            g2.fillRect(x+(7*scale), y+(12*scale), scale, 3*scale);
            g2.fillRect(x+(8*scale), y+(10*scale), scale, 2*scale);
            g2.fillRect(x+(9*scale), y, scale, 10*scale);
            g2.fillRect(x+(11*scale), y+(4*scale), scale, 2*scale);
            g2.fillRect(x+(11*scale), y+(5*scale), 4*scale, scale);
        }
        else if(type.equals("BLOCK")){  // Draws Generic Block.
            g2.setColor(Color.BLACK);
            g2.fillRect(x, y, 16*scale, 16*scale);
            g2.setColor(Blocks.brown);
            g2.fillRect(x+(scale), y+(scale), 14*scale, 14*scale);
            g2.setColor(Color.BLACK);
            g2.fillRect(x+(2*scale), y+(2*scale), scale, scale);
            g2.fillRect(x+(13*scale), y+(13*scale), scale, scale);
            g2.fillRect(x+(2*scale), y+(13*scale), scale, scale);
            g2.fillRect(x+(13*scale), y+(2*scale), scale, scale);
        }
        else if(type.equals("PIPE_TOP_L")){ //Draws a part of the "PIPE".
            g2.setColor(Color.BLACK);
            g2.fillRect(x+(scale), y, 15*scale, 15*scale);
            g2.fillRect(x+(4*scale), y+(15*scale), 13*scale, scale);

            g2.setColor(Color.GREEN);
            g2.fillRect(x+(2*scale), y+(scale), 15*scale, 13*scale);

            g2.setColor(new Color(26, 160, 28)); // Darker green.
            g2.fillRect(x+(2*scale), y+(2*scale), 3*scale, scale);
            g2.fillRect(x+(5*scale), y+(2*scale), 2*scale, 12*scale);
            g2.fillRect(x+(12*scale), y+(2*scale), scale, 12*scale);
            g2.fillRect(x+(12*scale), y+(2*scale), 3*scale, scale);
            g2.fillRect(x+(15*scale), y+(2*scale), scale, 12*scale);
        }
        else if(type.equals("PIPE_TOP_R")){ //Draws a part of the "PIPE".
            g2.setColor(Color.BLACK);
            g2.fillRect(x, y, 15*scale, 15*scale);
            g2.fillRect(x, y+(15*scale), 13*scale, scale);

            g2.setColor(Color.GREEN);
            g2.fillRect(x, y+(scale), 14*scale, 13*scale);

            g2.setColor(new Color(26, 160, 28));
            g2.fillRect(x, y+(2*scale), 8*scale, 12*scale);
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 6; j++)
                    g2.fillRect(x+((7+i)*scale), y+(((j*2)+(i%2)+2)*scale), scale, scale);
            }
        }
        else if(type.equals("PIPE_L")){ // Draws a part of the "PIPE".
            g2.setColor(Color.BLACK);
            g2.fillRect(x+(3*scale), y, scale, 16*scale);

            g2.setColor(Color.GREEN);
            g2.fillRect(x+(4*scale), y, 13*scale, 16*scale);

            g2.setColor(new Color(26, 160, 28)); // Darker green.S
            g2.fillRect(x+(7*scale), y, 2*scale, 16*scale);
            g2.fillRect(x+(12*scale), y, scale, 16*scale);
        }
        else if(type.equals("PIPE_R")){ // Draws a part of the "PIPE".
            g2.setColor(Color.BLACK);
            g2.fillRect(x+(13*scale), y, scale, 16*scale);

            g2.setColor(Color.GREEN);
            g2.fillRect(x+(7*scale), y, 5*scale, 16*scale);

            g2.setColor(new Color(26, 160, 28));
            g2.fillRect(x, y, 7*scale, 16*scale);
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 8; j++)
                    g2.fillRect(x+((6+i)*scale), y+(((j*2)+(i%2))*scale), scale, scale);
            }
        }
        else if(type.equals("STONE")){  // Draws the "STONE" Block.
            g2.setColor(Blocks.brown);
            g2.fillRect(x, y, 16*scale, 16*scale);
            g2.setColor(new Color(245, 218, 188));
            g2.fillRect(x, y+(scale), scale, 14*scale);
            g2.fillRect(x+(scale), y+(2*scale), scale, 12*scale);
            g2.fillRect(x+(2*scale), y+(3*scale), scale, 10*scale);
            g2.fillRect(x+(3*scale), y+(8*scale), scale, 8*scale);
            g2.fillRect(x+(scale), y, 14*scale, scale);
            g2.fillRect(x+(2*scale), y+(scale), 12*scale, scale);
            g2.fillRect(x+(3*scale), y+(2*scale), 10*scale, scale);
            g2.fillRect(x+(4*scale), y+(3*scale), 8*scale, scale);
            g2.setColor(Color.BLACK);
            g2.fillRect(x, y+(15*scale), 15*scale, scale);
            g2.fillRect(x+(scale), y+(14*scale), 13*scale, scale);
            g2.fillRect(x+(2*scale), y+(13*scale), 11*scale, scale);
            g2.fillRect(x+(3*scale), y+(12*scale), 9*scale, scale);
            g2.fillRect(x+(12*scale), y+(3*scale), scale, 9*scale);
            g2.fillRect(x+(13*scale), y+(2*scale), scale, 11*scale);
            g2.fillRect(x+(14*scale), y+(scale), scale, 13*scale);
            g2.fillRect(x+(15*scale), y, scale, 15*scale);
        }
        else if(type.equals("TEST")){   // Draws the "TEST" Block.
            g2.setColor(Color.ORANGE);
            g2.setColor(Blocks.brown);
            g2.fillRect(x + 2, y + 2, 46, 46);
        }
    }

    public void checkCollision(int px, int py)
    {
        Rectangle bounds = new Rectangle();




        if(px > x - 40 && px < x + 50){
            if(py > y + 20 && py <= y + 50){
                action();
            }
        }
    }

    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 50, 50);
    }

}

