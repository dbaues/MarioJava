package com.Mario;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class representing the Game Stage.
 * Renders Stage and Moves Stage.
 * @author Dan Bauer
 */
public class Platform
{
    Mario mario;
    private int x, y, a, b;
    private int flagY;
    private int turn;
    private static final double FLAG_SPEED = 10;
    ArrayList<Blocks> blocks;
    ArrayList<Blocks> layerB, layerT, mystery;

    /**
     * Constructor for objects of class Platform
     */
    public Platform(Mario m)
    {
        mario = m;
        x = 0;
        y = -50;
        a = 3250;
        b = a + 3750;
        flagY = y + 150;
        blocks = new ArrayList<Blocks>();
        layerT = new ArrayList<Blocks>();
        layerB = new ArrayList<Blocks>();
        mystery = new ArrayList<Blocks>();
        createStage(x, y);
        sort();
    }

    /**
     * resets to beginning
     */
    public void reset()
    {
        x = 0;
        y = -50;
    }

    /**
     * @return x cordinate
     */
    public int getX()
    {
        return x;
    }

    /**
     * @return y cordinate
     */
    public int getY()
    {
        return y;
    }

    /**
     * performs a blocks action
     */
    public void action(Blocks block, String function)
    {
        //block.action();
        int turns = 10;
        if(block.getType().equals("MYSTERY")){
            if(function.toUpperCase().equals("COIN")){
                mario.playSound(GameSound.COIN_SOUND_ID);
                mario.coins++;
                mario.score += 100;
            }
            else if(function.equals("COIN+")){
                mario.playSound(GameSound.COIN_SOUND_ID);
                mario.coins++;
                mario.score += 100;
                if(block.hits > 1){
                    block.hits--;
                }
                else{
                    block.hits = 8;
                    block.action();
                }
            }
            else if(function.toUpperCase().equals("MUSHROOM")){
                // Creates Mushroom Items.
                if(mario.shroom == null)
                    mario.items.add(new Items(x + block.getX() + 5, block.getY() - 40, Items.MUSHROOM, mario));
                else if(mario.shroom2 == null)
                    mario.items.add(new Items(x + block.getX() + 5, block.getY() - 40, Items.MUSHROOM, mario));
            }
        }
        else if(block.getType().equals("BRICK")){
            //mario.clipMain.stop();
            //mario.playSound("EXTRA");
            mario.score += 1000;
        }
        if(block.hits != 1){
            block.action();
        }
    }

    /**
     * Renders Platform Elements.
     */
    public void render(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        // Renders back and each Block.
        this.renderBack(g);
        for(Blocks block : blocks){
            block.render(x + block.getX(), g);
        }

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
    }

    /**
     * Loads the Blocks into the stage List.
     * SOON - Change this to a File Format.
     * @param x Default Coordinate.
     * @param y Default Coordinate.
     */
    public void createStage(int x, int y)
    {
        for(int i = 0; i < 67; i++){
            blocks.add(new Blocks("Ground", x + i * 50, y + 650));
            blocks.add(new Blocks("Ground", x + i * 50, y + 700));
        }
        for(int i = 0; i < 16; i++){
            blocks.add(new Blocks("Ground", x + 3450 + i * 50, y + 650));
            blocks.add(new Blocks("Ground", x + 3450 + i * 50, y + 700));
        }
        for(int i = 0; i < 64; i++){
            blocks.add(new Blocks("Ground", x + 4400 + i * 50, y + 650));
            blocks.add(new Blocks("Ground", x + 4400 + i * 50, y + 700));
        }
        for(int i = 0; i < 62; i++) {
            blocks.add(new Blocks("Ground", x + 7700 + i * 50, y + 650));
            blocks.add(new Blocks("Ground", x + 7700 + i * 50, y + 700));
        }

        blocks.add(new Blocks("Mystery", x + 700, y + 450));
        blocks.add(new Blocks("Brick", x + 900, y + 450));
        blocks.add(new Blocks("Mystery", x + 950, y + 450));
        blocks.add(new Blocks("Brick", x + 1000, y + 450));
        blocks.add(new Blocks("Mystery", x + 1000, y + 250));
        blocks.add(new Blocks("Mystery", x + 1050, y + 450));
        blocks.add(new Blocks("Brick", x + 1100, y + 450));

        blocks.add(new Blocks("Pipe_top_l", x + 1300, y + 550));
        blocks.add(new Blocks("Pipe_top_r", x + 1350, y + 550));
        blocks.add(new Blocks("Pipe_l", x + 1300, y + 600));
        blocks.add(new Blocks("Pipe_r", x + 1350, y + 600));

        blocks.add(new Blocks("Pipe_top_l", x + 1800, y + 500));
        blocks.add(new Blocks("Pipe_top_r", x + 1850, y + 500));
        blocks.add(new Blocks("Pipe_l", x + 1800, y + 550));
        blocks.add(new Blocks("Pipe_r", x + 1850, y + 550));
        blocks.add(new Blocks("Pipe_l", x + 1800, y + 600));
        blocks.add(new Blocks("Pipe_r", x + 1850, y + 600));

        blocks.add(new Blocks("Pipe_top_l", x + 2200, y + 450));
        blocks.add(new Blocks("Pipe_top_r", x + 2250, y + 450));
        blocks.add(new Blocks("Pipe_l", x + 2200, y + 500));
        blocks.add(new Blocks("Pipe_r", x + 2250, y + 500));
        blocks.add(new Blocks("Pipe_l", x + 2200, y + 550));
        blocks.add(new Blocks("Pipe_r", x + 2250, y + 550));
        blocks.add(new Blocks("Pipe_l", x + 2200, y + 600));
        blocks.add(new Blocks("Pipe_r", x + 2250, y + 600));

        blocks.add(new Blocks("Pipe_top_l", x + 2750, y + 450));
        blocks.add(new Blocks("Pipe_top_r", x + 2800, y + 450));
        blocks.add(new Blocks("Pipe_l", x + 2750, y + 500));
        blocks.add(new Blocks("Pipe_r", x + 2800, y + 500));
        blocks.add(new Blocks("Pipe_l", x + 2750, y + 550));
        blocks.add(new Blocks("Pipe_r", x + 2800, y + 550));
        blocks.add(new Blocks("Pipe_l", x + 2750, y + 600));
        blocks.add(new Blocks("Pipe_r", x + 2800, y + 600));

        //add a
        blocks.add(new Blocks("Brick", x + a + 550, y + 450));
        blocks.add(new Blocks("Mystery", x + a + 600, y + 450));
        blocks.add(new Blocks("Brick", x + a + 650, y + 450));
        for(int i = 0; i < 8; i++){
            blocks.add(new Blocks("Brick", x + a + 700 + (i * 50), y + 250));
        }
        blocks.add(new Blocks("Brick", x + a + 1250, y + 250));
        blocks.add(new Blocks("Brick", x + a + 1300, y + 250));
        blocks.add(new Blocks("Brick", x + a + 1350, y + 250));
        blocks.add(new Blocks("Mystery", x + a + 1400, y + 250));
        blocks.add(new Blocks("Brick", x + a + 1400, y + 450));
        blocks.add(new Blocks("Brick", x + a + 1700, y + 450));
        blocks.add(new Blocks("Brick", x + a + 1750, y + 450));
        blocks.add(new Blocks("Mystery", x + a + 2000, y + 450));
        blocks.add(new Blocks("Mystery", x + a + 2150, y + 450));
        blocks.add(new Blocks("Mystery", x + a + 2150, y + 250));
        blocks.add(new Blocks("Mystery", x + a + 2300, y + 450));

        blocks.add(new Blocks("Brick", x + a + 2600, y + 450));
        blocks.add(new Blocks("Brick", x + a + 2750, y + 250));
        blocks.add(new Blocks("Brick", x + a + 2800, y + 250));
        blocks.add(new Blocks("Brick", x + a + 2850, y + 250));
        blocks.add(new Blocks("Brick", x + a + 3100, y + 250));
        blocks.add(new Blocks("Mystery", x + a + 3150, y + 250));
        blocks.add(new Blocks("Mystery", x + a + 3200, y + 250));
        blocks.add(new Blocks("Brick", x + a + 3150, y + 450));
        blocks.add(new Blocks("Brick", x + a + 3200, y + 450));
        blocks.add(new Blocks("Brick", x + a + 3250, y + 250));
        for(int i = 1; i <= 4; i++){
            for(int j = 0; j < i; j++){
                blocks.add(new Blocks("Stone", x + a + 3550 - (50 * j), y + 400 + (50 * i)));
            }
        }
        for(int i = 1; i <= 4; i++){
            for(int j = 0; j < i; j++){
                blocks.add(new Blocks("Stone", x + a + 3700 + (50 * j), y + 400 + (50 * i)));
            }
        }
        //add b
        for(int i = 1; i <= 4; i++){
            for(int j = 0; j < i; j++){
                blocks.add(new Blocks("Stone", x + b + 500 - (50 * j), y + 400 + (50 * i)));
            }
        }
        for(int n = 0; n < 4; n++){
            blocks.add(new Blocks("Stone", x + b + 550, y + 450 + (50 * n)));
        }
        for(int i = 1; i <= 4; i++){
            for(int j = 0; j < i; j++){
                blocks.add(new Blocks("Stone", x + b + 700 + (50 * j), y + 400 + (50 * i)));
            }
        }
        blocks.add(new Blocks("Pipe_top_l", x + b + 1100, y + 550));
        blocks.add(new Blocks("Pipe_top_r", x + b + 1150, y + 550));
        blocks.add(new Blocks("Pipe_l", x + b + 1100, y + 600));
        blocks.add(new Blocks("Pipe_r", x + b + 1150, y + 600));

        blocks.add(new Blocks("Brick", x + b + 1400, y + 450));
        blocks.add(new Blocks("Brick", x + b + 1450, y + 450));
        blocks.add(new Blocks("Mystery", x + b + 1500, y + 450));
        blocks.add(new Blocks("Brick", x + b + 1550, y + 450));

        blocks.add(new Blocks("Pipe_top_l", x + b + 1950, y + 550));
        blocks.add(new Blocks("Pipe_top_r", x + b + 2000, y + 550));
        blocks.add(new Blocks("Pipe_l", x + b + 1950, y + 600));
        blocks.add(new Blocks("Pipe_r", x + b + 2000, y + 600));
        for(int i = 1; i <= 8; i++){
            for(int j = 0; j < i; j++){
                blocks.add(new Blocks("Stone", x + b + 2400 - (50 * j), y + 200 + (50 * i)));
            }
        }
        for(int n = 0; n < 8; n++){
            blocks.add(new Blocks("Stone", x + b + 2450, y + 250 + (50 * n)));
        }
        blocks.add(new Blocks("Stone", x + b + 2900, y + 600));
    }

    public void sort()
    {
        for(Blocks block : blocks){
            if(block.getType().equals("MYSTERY"))
                mystery.add(block);
            if(block.getY() < y + 350 && !block.getType().equals("Stone"))
                layerT.add(block);
            else if(block.getY() < y + 500){
                String test = block.getType();
                if(!test.equals("Stone")&&!test.equals("PIPE_TOP_L")&&!test.equals("PIPE_TOP_R"))
                    layerB.add(block);
            }
        }
    }

    /**
     * Creates background screen.
     */
    private void renderBack(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(165, 253, 255)); // Background Color.
        g2.fillRect(0,0,mario.getWidth(),mario.getHeight());
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
        for(Blocks block :  blocks){
            Rectangle bounds = block.getBounds();
            block.checkCollision(chX, chY);
        }

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
            if(mario.left && x < 0)
                x += Mario.speed; // Stage moves Left (Actually right).
            // When the Player presses RIGHT or D.
            if(mario.right)
                x -= Mario.speed; // Stage moves Right (Actually left).
        }

        // Lowers Flag on ending.
        if(mario.finish && flagY < y + 550)
            flagY += Platform.FLAG_SPEED;
    }
}


