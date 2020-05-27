package com.Mario;
/**
 * Main Game class
 *
 * @Daniel Bauer
 * @5/23/2018
 */

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.sound.sampled.*;

public class Mario extends Canvas implements Runnable
{
    public boolean running = false;
    public static boolean up, down, left, right; // player movement
    public static boolean go; //start motion
    public boolean motion = true;
    public static final int width = 1035, height = 715; // JFrame dimensions
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Dimension dim = new Dimension(width, height);
    Thread gameThread;
    JFrame frame;
    UI ui;
    InputHandler ih;
    Platform pf;
    Character ch;
    Items shroom;
    Items shroom2;
    public static double speed = 10;
    public static double jspeed = 15;
    double FPS, UPS, frames, ticks, delta;
    boolean shouldRender;

    public int score;
    public int coins;
    public int lives = 4;
    public String name;
    public boolean jump;
    public boolean moving;
    public boolean finish;
    public boolean scroll = true;
    public long timer, startTime;
    public Enemy[] enemy;
    private Enemy testGoomba;
    private Enemy testKoopa;
    public boolean died;
    private int obsolete = 0;
    private int obsolete2 = 0;
    private int tickTimer = 0;
    public boolean gameover;
    private boolean bandaid = false;
    File coinSound = new File("Coin_Sound.wav");
    File deathSound = new File("Death_Sound.wav");
    File gameoverSound = new File("Gameover_Sound.wav");
    File endSound = new File("End_Sound.wav");
    File mainSound = new File("Main_Sound.wav");
    File jumpSound = new File("Jump_Sound.wav");
    File flagpoleSound = new File("Flag_Pole_Sound.wav");
    File powerUpSound = new File("Power_Up_Sound.wav");
    File powerDownSound = new File("Power_Down_Sound.wav");
    File extraSound = new File("Extra_Sound_;).wav");
    private AudioInputStream aisCoin, aisDeath, aisGameover, aisEnd, aisMain;
    private AudioInputStream aisJump, aisFlag, aisPUp, aisPDown, aisExtra;
    public Clip clipCoin, clipDeath, clipGameover, clipEnd, clipMain;
    public Clip clipJump, clipFlagPole, clipPU, clipPD, clipExtra;
    /** Run method to control framerate
     *
     */
    public void run()
    {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60D;
        long lastTimer = System.currentTimeMillis();
        delta = 0D;

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            shouldRender = false;

            while(delta >= 1) {
                ticks++;
                tick();
                delta--;
                shouldRender = true;
            }
            if(shouldRender){
                frames++;
                render();
            }

            if(System.currentTimeMillis() - lastTime >= 1000){
                lastTimer += 1000;
                FPS = frames;
                UPS = ticks;
                frames = 0;
                ticks = 0;
            }
        }
    }

    /** Constructor
     *
     */
    public Mario(String type)
    {
        createWindow();
        if(type.equals("Alt"))
            name = "Alternate";
        else
            name = type;
        jump = true;
        died = false;
        finish = false;
        startTime = System.currentTimeMillis();
        ui = new UI(this);
        ih = new InputHandler(frame, this, this);
        pf = new Platform(this);
        ch = new Character(type, 300, 500, this);
        enemy = new Enemy[13];
        try {
            aisCoin = AudioSystem.getAudioInputStream(coinSound);
            clipCoin = AudioSystem.getClip();
            clipCoin.open(aisCoin);

            aisJump = AudioSystem.getAudioInputStream(jumpSound);
            clipJump = AudioSystem.getClip();
            clipJump.open(aisJump);

            aisDeath = AudioSystem.getAudioInputStream(deathSound);
            clipDeath = AudioSystem.getClip();
            clipDeath.open(aisDeath);

            aisGameover = AudioSystem.getAudioInputStream(gameoverSound);
            clipGameover = AudioSystem.getClip();
            clipGameover.open(aisGameover);

            aisEnd = AudioSystem.getAudioInputStream(endSound);
            clipEnd = AudioSystem.getClip();
            clipEnd.open(aisEnd);

            aisMain = AudioSystem.getAudioInputStream(mainSound);
            clipMain = AudioSystem.getClip();
            clipMain.open(aisMain);

            aisFlag = AudioSystem.getAudioInputStream(flagpoleSound);
            clipFlagPole = AudioSystem.getClip();
            clipFlagPole.open(aisFlag);

            aisPDown = AudioSystem.getAudioInputStream(powerDownSound);
            clipPD = AudioSystem.getClip();
            clipPD.open(aisPDown);

            aisPUp = AudioSystem.getAudioInputStream(powerUpSound);
            clipPU = AudioSystem.getClip();
            clipPU.open(aisPUp);

            aisExtra = AudioSystem.getAudioInputStream(extraSound);
            clipExtra = AudioSystem.getClip();
            clipExtra.open(aisExtra);
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
        //testGoomba = new Enemy("Goomba", pf.getX() + 800, pf.getY() + 600, this);
        //testGoomba.setBounds(pf.getX() + 200, pf.getX() + 400);
    }

    /** Tick method, does this method every frame
     *
     */
    public void tick()
    {
        if(!finish && !gameover){
            timer = (400000 - Math.abs(startTime - System.currentTimeMillis()))/1000;
            tickTimer++;
            if(tickTimer == 1 && !bandaid){
                bandaid = true;
                playSound("Main");
            }
            else if(clipMain.getMicrosecondPosition() == clipMain.getMicrosecondLength()){
                tickTimer = 0;
                clipMain.setMicrosecondPosition(0);
                playSound("Main");
            }
        }
        if(pf != null){
            pf.checkCollision();
            pf.tick();
        }
        if(ch != null){
            ch.checkCollision();
        }
        if(shroom != null){
            shroom.checkCollision();
            shroom.tick();
        }
        if(shroom2 != null){
            shroom2.checkCollision();
            shroom2.tick();
        }
        if(ui != null)
            ui.tick();
        if(testGoomba != null){
            testGoomba.checkCollision();
            testGoomba.tick();
        }
        if(ch != null){
            ch.tick();
        }
        if(lives >= 0){
            if(died && ui.getA() >= 255){
                obsolete++;
                if(obsolete == 1){
                    lives--;
                    playSound("Death");
                    clipMain.stop();
                }
                else if(obsolete == 183){
                    ch.setLocation(300, 500);
                    clipMain.setMicrosecondPosition(1610000);
                    clipDeath.stop();
                    clipDeath.setMicrosecondPosition(0);
                    playSound("Main");
                    died = false;
                    obsolete = 0;
                    ui.setA(0);
                }
            }
        }
        else{
            if(died && ui.getA() >= 255){
                gameover = true;
            }
        }
        if(finish||gameover){
            obsolete2++;
            if(obsolete2 == 1){
                clipMain.stop();
                clipDeath.stop();
                if(finish){
                    playSound("FLAGPOLE");
                    playSound("End");
                    score += timer * 10;
                }
                else if(gameover)
                    playSound("Gameover");
            }
        }
    }

    /** Render method, creates the board with ball, players, and background
     *
     */
    public void render()
    {
        BufferStrategy strat = getBufferStrategy();
        if(strat == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = strat.getDrawGraphics();

        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        if(pf != null)
            pf.renderBack(g);
        if(pf != null)
            pf.render(g);
        if(ch != null)
            ch.render(g);
        if(ui != null)
            ui.render(g);
        if(shroom != null)
            shroom.render(g);
        if(shroom2 != null)
            shroom2.render(g);
        if(testGoomba != null)
            testGoomba.render(g);
        g.dispose();
        strat.show();
    }

    /** Method to start game
     *
     */
    public synchronized void start(){
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    /** Draws the JFrame that the game will be played in
     *
     */
    public void createWindow()
    {
        frame = new JFrame("SUPER MARIO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setSize(dim);
        frame.setLocationRelativeTo(null);
        //frame.setPreferredSize(dim);
        frame.setMinimumSize(dim);
        frame.add(this);
        frame.setMaximumSize(dim);

        frame.pack();
    }

    /**
     * plays sounds
     * @param sound that is to be played on call
     */
    public void playSound(String sound)
    {
        if(sound.toUpperCase().equals("COIN")){
            clipCoin.setMicrosecondPosition(0);
            clipCoin.start();
        }
        else if(sound.toUpperCase().equals("DEATH")){
            clipDeath.start();
        }
        else if(sound.toUpperCase().equals("GAMEOVER")){
            clipGameover.start();
        }
        else if(sound.toUpperCase().equals("END")){
            clipEnd.start();
        }
        else if(sound.toUpperCase().equals("MAIN")){
            clipMain.start();
        }
        else if(sound.toUpperCase().equals("JUMP")){
            clipJump.setMicrosecondPosition(0);
            clipJump.start();
        }
        else if(sound.toUpperCase().equals("FLAGPOLE")){
            clipFlagPole.start();
        }
        else if(sound.toUpperCase().equals("PDOWN")){
            clipPD.setMicrosecondPosition(0);
            clipPD.start();
        }
        else if(sound.toUpperCase().equals("PUP")){
            clipPU.setMicrosecondPosition(0);
            clipPU.start();
        }
        else if(sound.toUpperCase().equals("EXTRA")){
            clipExtra.start();
        }
    }
}
