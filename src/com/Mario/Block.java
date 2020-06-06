package com.Mario;

import java.awt.*;

/**
 * A Block object.
 * Contains attributes of a Block and renders a Block.
 * @author Dan Bauer.
 */
public class Block
{
    public static final int SCALE = 3;
    public static final int WIDTH = 16 * Block.SCALE;
    public static final int HEIGHT = 16 * Block.SCALE;

    // Block ID's.
    // Basic Block Block ID's.
    public static final int MYSTERY = 0;
    public static final int BRICK = 1;
    public static final int GROUND = 2;
    public static final int BLOCK = 3;
    public static final int STONE = 4;
    public static final int PIPE_L = 5;
    public static final int PIPE_R = 6;
    public static final int PIPE_TOP_L = 7;
    public static final int PIPE_TOP_R = 8;
    // Mystery Block Types.
    public static final int MYSTERY_MUSHROOM = 11;
    public static final int MYSTERY_1UP = 12;
    public static final int MYSTERY_COIN = 13;
    public static final int MYSTERY_FIRE = 14;
    // Brick Block Types.
    public static final int BRICK_COIN = 21;
    public static final int BRICK_COINS = 22;
    public static final int BRICK_STAR = 23;
    // Special Block Types.
    public static final int FLAGPOLE = 97;
    public static final int TRANSPARENT = 98;
    //public static final int TEST = 99;

    // Block Fields.
    private int x, typeID;
    private final int y;
    private Items item = null;
    private final Mario mario;

    // Default colors for Block.
    public static Color brown = new Color(130, 76, 40);
    public static Color off_white = new Color(240, 210, 215);

    /**
     * Constructs a Block on the Stage.
     * @param typeID Identifier of the Block.
     * @param x Coordinate.
     * @param y Coordinate.
     * @param m Main Game Instance.
     */
    public Block(int typeID, int x, int y, Mario m)
    {
        this.typeID = typeID;
        this.x = x;
        this.y = y;
        this.mario = m;
    }

    // Return methods.
    /**
     * @return The Block Instance's X Coordinate.
     */
    public int getX() { return x; }

    /**
     * @return The Block Instance's Y Coordinate.
     */
    public int getY() { return y; }

    // Class Methods.
    /**
     * Performs some action when a Block is hit.
     */
    public void action()
    {
        switch(typeID){
            case Block.MYSTERY -> {
                typeID = Block.BLOCK;
                mario.coins++;
                mario.score += 100;
            }
            case Block.MYSTERY_MUSHROOM -> {
                typeID = Block.BLOCK;
                item = new Items(x, y-Block.HEIGHT, "Mushroom", mario);
            }
        }
    }

    /**
     * Updates the Block location.
     * Also moves any Items spawned.
     * @param direction true = moving forward.
     *                  false = moving backwards.
     */
    public void tick(boolean direction)
    {
        if(direction){ this.x += Mario.speed; }
        else{ this.x -= Mario.speed; }

        if(item != null){ item.tick(); }
    }

    /**
     * Draws the block based on type
     */
    public void render(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        int scale = Block.SCALE;
        int tmpID = typeID;

        if(tmpID >= 10 && tmpID < 20)
            tmpID = (tmpID / 10) - 1;

        // Draws an Individual Block.
        // Draws proper Original Super Mario Bros Block.
        switch(tmpID){
            case Block.MYSTERY -> {// Draws the "?" (MYSTERY) Block.
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
            case Block.BRICK -> { // Draws the "BRICK" Block.
                g2.setColor(Color.BLACK);
                g2.fillRect(x, y, 16*scale, 16*scale);

                g2.setColor(Block.brown);
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
            case Block.GROUND -> { // Draws the "GROUND" Block.
                g2.setColor(Block.brown);
                g2.fillRect(x, y, 16*scale, 16*scale);

                g2.setColor(Block.off_white);
                g2.fillRect(x, y+(scale), scale, 9*scale);
                g2.fillRect(x, y+(11*scale), scale, 4*scale);
                g2.fillRect(x+(scale), y, 8*scale, scale);
                g2.fillRect(x+(11*scale), y, 4*scale, scale);

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
                g2.fillRect(x+(15*scale), y+(scale), scale, scale*4);
            }
            case Block.BLOCK -> { // Draws Generic Block.
                g2.setColor(Color.BLACK);
                g2.fillRect(x, y, 16*scale, 16*scale);

                g2.setColor(Block.brown);
                g2.fillRect(x+(scale), y+(scale), 14*scale, 14*scale);

                g2.setColor(Color.BLACK);
                g2.fillRect(x+(2*scale), y+(2*scale), scale, scale);
                g2.fillRect(x+(13*scale), y+(13*scale), scale, scale);
                g2.fillRect(x+(2*scale), y+(13*scale), scale, scale);
                g2.fillRect(x+(13*scale), y+(2*scale), scale, scale);
            }
            case Block.STONE -> { // Draws the "STONE" Block.
                g2.setColor(Block.brown);
                g2.fillRect(x, y, 16*scale, 16*scale);

                g2.setColor(new Color(245, 218, 188)); // Off-White v2.
                g2.fillRect(x, y+(scale), scale, 14*scale);
                g2.fillRect(x+(scale), y+(2*scale), scale, 12*scale);
                g2.fillRect(x+(2*scale), y+(3*scale), scale, 10*scale);
                g2.fillRect(x+(3*scale), y+(4*scale), scale, 8*scale);
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
            case Block.PIPE_L -> { // Draws the Left side of the Pipe.
                g2.setColor(Color.BLACK);
                g2.fillRect(x+(3*scale), y, scale, 16*scale);

                g2.setColor(Color.GREEN);
                g2.fillRect(x+(4*scale), y, 12*scale, 16*scale);

                g2.setColor(new Color(26, 160, 28)); // Darker green.S
                g2.fillRect(x+(7*scale), y, 2*scale, 16*scale);
                g2.fillRect(x+(13*scale), y, scale, 16*scale);
            }
            case Block.PIPE_R -> { // Draws the Right side of the Pipe.
                g2.setColor(Color.BLACK);
                g2.fillRect(x+(12*scale), y, scale, 16*scale);

                g2.setColor(Color.GREEN);
                g2.fillRect(x+(7*scale), y, 5*scale, 16*scale);

                g2.setColor(new Color(26, 160, 28));
                g2.fillRect(x, y, 7*scale, 16*scale);
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j < 8; j++)
                        g2.fillRect(x+((6+i)*scale), y+(((j*2)+(i%2))*scale), scale, scale);
                }
            }
            case Block.PIPE_TOP_L -> { // Draws the Left Top side of the Pipe.
                g2.setColor(Color.BLACK);
                g2.fillRect(x+(scale), y, 15*scale, 15*scale);
                g2.fillRect(x+(3*scale), y+(15*scale), 13*scale, scale);

                g2.setColor(Color.GREEN);
                g2.fillRect(x+(2*scale), y+(scale), 15*scale, 13*scale);

                g2.setColor(new Color(26, 160, 28)); // Darker green.
                g2.fillRect(x+(2*scale), y+(2*scale), 3*scale, scale);
                g2.fillRect(x+(5*scale), y+(2*scale), 2*scale, 12*scale);
                g2.fillRect(x+(12*scale), y+(2*scale), scale, 12*scale);
                g2.fillRect(x+(12*scale), y+(2*scale), 3*scale, scale);
                g2.fillRect(x+(15*scale), y+(2*scale), scale, 12*scale);
            }
            case Block.PIPE_TOP_R -> {
                g2.setColor(Color.BLACK);
                g2.fillRect(x, y, 15*scale, 15*scale);
                g2.fillRect(x, y+(15*scale), 13*scale, scale);

                g2.setColor(Color.GREEN);
                g2.fillRect(x, y+(scale), 14*scale, 13*scale);

                g2.setColor(new Color(26, 160, 28));
                g2.fillRect(x, y+(2*scale), 8*scale, 12*scale);
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j < 6; j++)
                        g2.fillRect(x+((8+i)*scale), y+(((j*2)+(i%2)+2)*scale), scale, scale);
                }
                g2.fillRect(x+(8*scale), y+(2*scale), 6*scale, scale);
            }
            case Block.TRANSPARENT -> {
                g2.setColor(new Color(255, 255, 255, 10));
                g2.fillRect(x, y, Block.WIDTH, Block.HEIGHT);
            }
            default -> { // Default (Error) block.
                g2.setColor(Color.MAGENTA);
                g2.fillRect(x, y, Block.WIDTH, Block.HEIGHT);
                g2.setColor(Color.BLACK);
                g2.drawString("ERROR:", x, y+16);
            }
        }

        // Draws any spawned Items.
        if(item != null)
            item.render(g);
    }

    /**
     * Checks the Collision with each individual Block.
     * @param px Coordinate (Player X Coordinate).
     * @param py Coordinate (Player Y Coordinate).
     */
    public void checkCollision(int px, int py)
    {
        Rectangle bounds = new Rectangle(x - 48, y - 48, Block.WIDTH + 48, Block.HEIGHT + 48);
        if(bounds.contains(px, py)) { action(); }

        /*
        if(px > x - 40 && px < x + 50){
            if(py > y + 20 && py <= y + 50){
                action();
            }
        }

         */

        // Collision Check for Spawned Item.
        if(item != null)
            item.checkCollision();
    }

    /**
     * Adds the necessary Block for a Pipe.
     * @param x Coordinate.
     * @param y Coordinate.
     * @param height The Height of the Pipe (h >= 1).
     * @param entrance Determines if this is a Warp Pipe.
     *                 Add implementation.
     * @return An Array of Block containing Pipe Block.
     */
    public static Block[] addPipe(int x, int y, int height, boolean entrance, Mario m)
    {
        Block[] pipe = new Block[height * 2];
        // Top of Pipe. Necessary for all Above Ground Pipes.
        pipe[0] = new Block(Block.PIPE_TOP_L, x, y, m);
        pipe[1] = new Block(Block.PIPE_TOP_R, x+(Block.WIDTH), y, m);
        // Adds rest of Pipe.
        for(int i = 2; i < height * 2; i += 2)
        {
            pipe[i] = new Block(Block.PIPE_L, x, y+((i/2)*Block.HEIGHT), m);
            pipe[i+1] = new Block(Block.PIPE_R, x+(Block.WIDTH), y+((i/2)*Block.HEIGHT), m);
        }
        return pipe;
    }

}

