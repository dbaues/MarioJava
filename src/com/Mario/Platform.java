package com.Mario;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class representing the Game Stage.
 * Renders Stage and Moves Stage.
 * @author Dan Bauer
 */
public class Platform
{
    //Static Fields.
    private static final int FLAG_SPEED = 10;

    // Class Fields.
    private final Mario mario;
    private int x, flagY;
    private final int y;
    private final ArrayList<Block> Blocks;

    /**
     * Constructs a Stage Instance.
     * @param m Main Game Instance.
     */
    public Platform(Mario m)
    {
        this.mario = m;
        this.x = 0;
        this.y = -50;
        this.flagY = y + 150;

        // Creates Stage.
        Blocks = new ArrayList<>();
        createStage(x, y);
    }

    /**
     * resets to beginning
     */
    public void reset()
    {
        x = 0;
    }

    /**
     * @return Platform X Coordinate.
     */
    public int getX() { return x; }

    /**
     * @return Platform Y Coordinate.
     */
    public int getY() { return y; }

    /**
     * Renders Platform Elements.
     */
    public void render(Graphics g)
    {
        //Graphics2D g2 = (Graphics2D) g;

        // Renders Background Elements.
        this.renderBack(g);

        // Renders each Block.
        for(Block block : Blocks){
            // Only renders elements on Screen.
            if(block.getX() < -50 || block.getX() > 1300) { continue; }
            block.render(g);
        }
        /*
        // Renders End of level flag and flag pole.
        g2.setColor(Color.BLACK);
        g2.fillRect(x + b + 2920, y + 150, 10, 450);
        g2.setColor(Color.GRAY);
        g2.fillRect(x + b + 2922, y + 150, 6, 450);
        g2.setColor(Color.BLACK);
        g2.fillOval(x + b + 2910, y + 120, 30, 30);
        g2.setColor(Color.GREEN);
        g2.fillOval(x + b + 2912, y + 122, 26, 26);

        g2.setColor(Color.WHITE);
        int[] xs = {x + b + 2845, x + b + 2920, x + b + 2920};
        int[] ys = {flagY, flagY, flagY + 50};
        Polygon flag = new Polygon(xs, ys, 3);
        g2.fill(flag);

        g2.setColor(Color.BLACK);
        g2.drawString(x + "", 50, 200);

         */
    }

    /**
     * Loads the Block into the stage List.
     * TODO - Change this to a File Format.
     * @param x Default Coordinate.
     * @param y Default Coordinate.
     */
    private void createStage(int x, int y)
    {
        Block[] pipe;
        int i, j;
        int blockHeight1 = 9 * Block.HEIGHT;
        int blockHeight2 = 5 * Block.HEIGHT;

        // Creates the Ground.
        for(i = 0; i < (67 + 15 + 64 + 62 + 7); i++){
            if(i >= 69 && i <= 70){ continue; } // First hole.
            else if(i >= 86 && i <= 88){ continue; } // Second hole.
            else if(i >= 153 && i <= 154){ continue; } // Third hole.

            Blocks.add(new Block(Block.GROUND, x+(i*Block.WIDTH), y+(13*Block.HEIGHT), mario));
            Blocks.add(new Block(Block.GROUND, x+(i*Block.WIDTH), y+(14*Block.HEIGHT), mario));
        }

        // First set of Mystery and Bricks.
        Blocks.add(new Block(Block.MYSTERY, x+(16*Block.WIDTH), y+(blockHeight1), mario));
        Blocks.add(new Block(Block.BRICK, x+(20*Block.WIDTH), y+(blockHeight1), mario));
        Blocks.add(new Block(Block.MYSTERY_MUSHROOM, x+(21*Block.WIDTH), y+(blockHeight1), mario));
        Blocks.add(new Block(Block.BRICK, x+(22*Block.WIDTH), y+(blockHeight1), mario));
        Blocks.add(new Block(Block.MYSTERY, x+(22*Block.WIDTH), y+(blockHeight2), mario));
        Blocks.add(new Block(Block.MYSTERY, x+(23*Block.WIDTH), y+(blockHeight1), mario));
        Blocks.add(new Block(Block.BRICK, x+(24*Block.WIDTH), y+(blockHeight1), mario));

        // Pipe 1.
        pipe = Block.addPipe(x+(28*Block.WIDTH), y+(11*Block.HEIGHT), 2, false, mario);
        Blocks.addAll(Arrays.asList(pipe));

        // Pipe 2.
        pipe = Block.addPipe(x+(38*Block.WIDTH), y+(10*Block.HEIGHT), 3, false, mario);
        Blocks.addAll(Arrays.asList(pipe));

        // Pipe 3.
        pipe = Block.addPipe(x+(46*Block.WIDTH), y+(blockHeight1), 4, false, mario);
        Blocks.addAll(Arrays.asList(pipe));

        // Pipe 4. Warp Pipe to Underground Coin Room.
        pipe = Block.addPipe(x+(57*Block.WIDTH), y+(blockHeight1), 4, true, mario);
        Blocks.addAll(Arrays.asList(pipe));

        // Hidden 1-Up.
        Blocks.add(new Block(Block.TRANSPARENT, x+(64*Block.WIDTH), y+(8*Block.HEIGHT), mario));

        // Next Group of Bricks and Mystery Boxes.
        Blocks.add(new Block(Block.BRICK, x+(77*Block.WIDTH), y+(blockHeight1), mario));
        Blocks.add(new Block(Block.MYSTERY_MUSHROOM, x+(78*Block.WIDTH), y+(blockHeight1), mario));
        Blocks.add(new Block(Block.BRICK, x+(79*Block.WIDTH), y+(blockHeight1), mario));
        for(i = 0; i < 8; i++)
            Blocks.add(new Block(Block.BRICK, x+((80+i)*Block.WIDTH), y+(blockHeight2), mario));

        for(i = 0; i < 3; i++)
            Blocks.add(new Block(Block.BRICK, x+((91+i)*Block.WIDTH), y+(blockHeight2), mario));
        Blocks.add(new Block(Block.MYSTERY, x+(94*Block.WIDTH), y+(blockHeight2), mario));
        Blocks.add(new Block(Block.BRICK_COIN, x+(94*Block.WIDTH), y+(blockHeight1), mario));

        Blocks.add(new Block(Block.BRICK, x+(100*Block.WIDTH), y+(blockHeight1), mario));
        Blocks.add(new Block(Block.BRICK_STAR, x+(101*Block.WIDTH), y+(blockHeight1), mario));

        // Set of 4 Mystery Boxes.
        Blocks.add(new Block(Block.MYSTERY, x+(106*Block.WIDTH), y+(blockHeight1), mario));
        Blocks.add(new Block(Block.MYSTERY, x+(109*Block.WIDTH), y+(blockHeight1), mario));
        Blocks.add(new Block(Block.MYSTERY_MUSHROOM, x+(109*Block.WIDTH), y+(blockHeight2), mario));
        Blocks.add(new Block(Block.MYSTERY, x+(112*Block.WIDTH), y+(blockHeight1), mario));

        // Next Groupings of Bricks and Mystery Boxes.
        Blocks.add(new Block(Block.BRICK, x+(118*Block.WIDTH), y+(blockHeight1), mario));
        for(i = 0; i < 3; i++)
            Blocks.add(new Block(Block.BRICK, x+((121+i)*Block.WIDTH), y+(blockHeight2), mario));

        Blocks.add(new Block(Block.BRICK, x+(128*Block.WIDTH), y+(blockHeight2), mario));
        Blocks.add(new Block(Block.MYSTERY, x+(129*Block.WIDTH), y+(blockHeight2), mario));
        Blocks.add(new Block(Block.MYSTERY, x+(130*Block.WIDTH), y+(blockHeight2), mario));
        Blocks.add(new Block(Block.BRICK, x+(131*Block.WIDTH), y+(blockHeight2), mario));
        Blocks.add(new Block(Block.BRICK, x+(129*Block.WIDTH), y+(blockHeight1), mario));
        Blocks.add(new Block(Block.BRICK, x+(130*Block.WIDTH), y+(blockHeight1), mario));

        // Stone Staircases.
        for(i = 1; i <= 4; i++){
            for(j = 0; j < i; j++)
                Blocks.add(new Block(Block.STONE, x+((133+i)*Block.WIDTH), y+((12-j)*Block.HEIGHT), mario));
        }
        for(i = 4; i >= 1; i--){
            for(j = i; j > 0; j--)
                Blocks.add(new Block(Block.STONE, x+((144-i)*Block.WIDTH), y+(13-j)*Block.HEIGHT, mario));
        }
        for(i = 1; i <= 4; i++){
            for(j = 0; j < i; j++)
                Blocks.add(new Block(Block.STONE, x+((147+i)*Block.WIDTH), y+((12-j)*Block.HEIGHT), mario));
        }
        for(i = 0; i < 4; i++)
            Blocks.add(new Block(Block.STONE, x+(152*Block.WIDTH), y+(blockHeight1+(i*Block.HEIGHT)), mario));
        for(i = 4; i >= 1; i--){
            for(j = i; j > 0; j--)
                Blocks.add(new Block(Block.STONE, x+((159-i)*Block.WIDTH), y+(13-j)*Block.HEIGHT, mario));
        }

        // Pipe 5. Exit Pipe.
        pipe = Block.addPipe(x+(163*Block.WIDTH), y+(11*Block.HEIGHT), 2, false, mario);
        Blocks.addAll(Arrays.asList(pipe));

        // Last Brick and Mystery Box Grouping.
        Blocks.add(new Block(Block.BRICK, x+(168*Block.WIDTH), y+(blockHeight1), mario));
        Blocks.add(new Block(Block.BRICK, x+(169*Block.WIDTH), y+(blockHeight1), mario));
        Blocks.add(new Block(Block.MYSTERY, x+(170*Block.WIDTH), y+(blockHeight1), mario));
        Blocks.add(new Block(Block.BRICK, x+(171*Block.WIDTH), y+(blockHeight1), mario));

        // Pipe 6. Final Pipe.
        pipe = Block.addPipe(x+(179*Block.WIDTH), y+(11*Block.HEIGHT), 2, false, mario);
        Blocks.addAll(Arrays.asList(pipe));

        // Final Stone Staircase.
        for(i = 1; i <= 8; i++){
            for(j = 0; j < i; j++)
                Blocks.add(new Block(Block.STONE, x+((180+i)*Block.WIDTH), y+((12-j)*Block.HEIGHT), mario));
        }
        for(i = 0; i < 8; i++)
            Blocks.add(new Block(Block.STONE, x+(189*Block.WIDTH), y+(blockHeight2+(i*Block.HEIGHT)), mario));

        // Flag pole base.
        Blocks.add(new Block(Block.STONE, x+(198*Block.WIDTH), y+(12*Block.HEIGHT), mario));
    }

    /**
     * Renders Background and Landscape.
     * TODO - Add Clouds and Bushes.
     */
    private void renderBack(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        // Draws Background.
        g2.setColor(new Color(100, 160, 255)); // Background Color.
        g2.fillRect(0,0,mario.getWidth(),mario.getHeight());

        // Renders Landscape Elements.
        { // Bushes.
            Platform.renderBushTYPE1(x, y + (11 * Block.HEIGHT), 2, g);
            Platform.renderBushTYPE2(x + (11 * Block.WIDTH), y + (12 * Block.HEIGHT), 3, g);
            Platform.renderBushTYPE1(x + (16 * Block.WIDTH), y + (12 * Block.HEIGHT), 1, g);
            Platform.renderBushTYPE2(x + (23 * Block.WIDTH), y + (12 * Block.HEIGHT), 1, g);
            Platform.renderBushTYPE2(x + (41 * Block.WIDTH), y + (12 * Block.HEIGHT), 2, g);
            Platform.renderBushTYPE1(x + (48 * Block.WIDTH), y + (11 * Block.HEIGHT), 2, g);
            Platform.renderBushTYPE2(x + (59 * Block.WIDTH), y + (12 * Block.HEIGHT), 3, g);
            Platform.renderBushTYPE1(x + (64 * Block.WIDTH), y + (12 * Block.HEIGHT), 1, g);
            Platform.renderBushTYPE2(x + (71 * Block.WIDTH), y + (12 * Block.HEIGHT), 1, g);
            Platform.renderBushTYPE2(x + (89 * Block.WIDTH), y + (12 * Block.HEIGHT), 2, g);
            Platform.renderBushTYPE1(x + (96 * Block.WIDTH), y + (11 * Block.HEIGHT), 2, g);
            Platform.renderBushTYPE2(x + (107 * Block.WIDTH), y + (12 * Block.HEIGHT), 3, g);
            Platform.renderBushTYPE1(x + (112 * Block.WIDTH), y + (12 * Block.HEIGHT), 1, g);
            Platform.renderBushTYPE2(x + (119 * Block.WIDTH), y + (12 * Block.HEIGHT), 1, g);
            Platform.renderBushTYPE2(x + (137 * Block.WIDTH), y + (12 * Block.HEIGHT), 2, g);
            Platform.renderBushTYPE1(x + (144 * Block.WIDTH), y + (11 * Block.HEIGHT), 2, g);
            Platform.renderBushTYPE2(x + (157 * Block.WIDTH), y + (12 * Block.HEIGHT), 1, g);
            Platform.renderBushTYPE1(x + (160 * Block.WIDTH), y + (12 * Block.HEIGHT), 1, g);
            Platform.renderBushTYPE2(x + (167 * Block.WIDTH), y + (12 * Block.HEIGHT), 1, g);
            Platform.renderBushTYPE1(x + (192 * Block.WIDTH), y + (12 * Block.HEIGHT), 2, g);
        }
        { // Clouds.

        }
    }

    /**
     * Checks Collisions.
     */
    public void checkCollision()
    {
        // Players Coordinates.
        int chX = mario.ch.getX();
        int chY = mario.ch.getY();

        // Checks Block collisions.
        for(Block block :  Blocks)
            block.checkCollision(chX, chY);

        // Temporary Gameover.
        if(chY < 10){ mario.finish = true; }
    }

    /**
     * Executes on every tick.
     * Moves the stage to simulate lateral player movement.
     */
    public void tick()
    {
        // Checks the Platform collisions.
        this.checkCollision();

        if(!mario.finish){
            // When the Player pressed LEFT or A.
            if(mario.left && x < 0) {
                x += Mario.speed; // Stage moves Left (Actually right).
                // Updates Block.
                for(Block block : Blocks)
                    block.tick(true);
            }
            // When the Player presses RIGHT or D.
            if(mario.right) {
                x -= Mario.speed; // Stage moves Right (Actually left).
                // Updates Block.
                for(Block block : Blocks)
                    block.tick(false);
            }
        }



        // Lowers Flag on ending.
        if(mario.finish && flagY < y + 550)
            flagY += Platform.FLAG_SPEED;
    }

    /**
     * Renders a Bush TYPE 1.
     * Type 1 is the Darker Simpler designed Bush.
     * @param x Coordinate.
     * @param y Coordinate.
     * @param height Height of the bush
     * @param g Graphics Instance.
     */
    private static void renderBushTYPE1(int x, int y, int height, Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        //int i, j, k;
        g2.setColor(Color.MAGENTA);
        g2.fillRect(x, y, (2*height+1)*Block.WIDTH, height*Block.HEIGHT);
        g2.setColor(Color.BLACK);
        g2.drawString("Bush Type 1", x, y);
    }

    /**
     * Renders a Bush TYPE 2.
     * Lighter Bush based on CLoud.
     * @param x Coordinate.
     * @param y Coordinate.
     * @param length of Bush.
     * @param g Graphics Instance.
     */
    private static void renderBushTYPE2(int x, int y, int length, Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.MAGENTA);
        g2.fillRect(x, y, (length+2)*Block.WIDTH, 2*Block.HEIGHT);
        g2.setColor(Color.BLACK);
        g2.drawString("Bush Type 2", x, y);
    }

}
