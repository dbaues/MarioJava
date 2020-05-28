package com.Mario;

/**
 * Renders the platforms
 *
 * @Daniel Bauer
 * @5/26/18
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.*;
import java.util.ArrayList;

public class Platform
{
    Blocks block;
    Mario mario;
    private int x, y, a, b;
    private int flagY;
    private int turn;
    private static double flagSpeed = 10;
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
                mario.playSound(GameSound.COIN_SOUND);
                mario.coins++;
                mario.score += 100;
            }
            else if(function.equals("COIN+")){
                mario.playSound(GameSound.COIN_SOUND);
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
                if(mario.shroom == null)
                    mario.shroom = new Items(x + block.getX() + 5, block.getY() - 40, mario);
                else if(mario.shroom2 == null)
                    mario.shroom2 = new Items(x + block.getX() + 5, block.getY() - 40, mario);
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
     * draws platform elements
     */
    public void render(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        for(Blocks block : blocks){
            block.render(x + block.getX(), g);
        }

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
        //Test.paintComponent(100, 300));
    }

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
                blocks.add(new Blocks("Test", x + a + 3550 - (50 * j), y + 400 + (50 * i)));
            }
        }
        for(int i = 1; i <= 4; i++){
            for(int j = 0; j < i; j++){
                blocks.add(new Blocks("Test", x + a + 3700 + (50 * j), y + 400 + (50 * i)));
            }
        }
        //add b
        for(int i = 1; i <= 4; i++){
            for(int j = 0; j < i; j++){
                blocks.add(new Blocks("Test", x + b + 500 - (50 * j), y + 400 + (50 * i)));
            }
        }
        for(int n = 0; n < 4; n++){
            blocks.add(new Blocks("Test", x + b + 550, y + 450 + (50 * n)));
        }
        for(int i = 1; i <= 4; i++){
            for(int j = 0; j < i; j++){
                blocks.add(new Blocks("Test", x + b + 700 + (50 * j), y + 400 + (50 * i)));
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
                blocks.add(new Blocks("Test", x + b + 2400 - (50 * j), y + 200 + (50 * i)));
            }
        }
        for(int n = 0; n < 8; n++){
            blocks.add(new Blocks("Test", x + b + 2450, y + 250 + (50 * n)));
        }
        blocks.add(new Blocks("Test", x + b + 2900, y + 600));
    }

    public void sort()
    {
        for(Blocks block : blocks){
            if(block.getType().equals("MYSTERY"))
                mystery.add(block);
            if(block.getY() < y + 350 && !block.getType().equals("TEST"))
                layerT.add(block);
            else if(block.getY() < y + 500){
                String test = block.getType();
                if(!test.equals("TEST")&&!test.equals("PIPE_TOP_L")&&!test.equals("PIPE_TOP_R"))
                    layerB.add(block);
            }
        }
    }

    /**
     * Creates background screen
     */
    public void renderBack(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(165, 253, 255));
        g2.fillRect(0,0,mario.getWidth(),mario.getHeight());
    }

    /**
     * checks collision
     */
    public void checkCollision()
    {
        int chX = mario.ch.getX();
        int chY = mario.ch.getY();
        for(Blocks block :  blocks){
            /*if(chX > block.getX() - 40 && chX < block.getX() + 50){
            if(chY > block.getY() + 20 && chY <= block.getY() + 90){
            block.action();
            }
            }*/
            block.checkCollision(chX, chY);
        }
    }

    /**
     * moves the board to simulate the mario character motion sytle
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
        if(mario.finish && flagY < y + 550)
            flagY += flagSpeed;
    }
}


