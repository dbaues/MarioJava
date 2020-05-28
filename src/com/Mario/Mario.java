package com.Mario;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Main Game Class.
 * BY: Dan Bauer
 * Original: 5/23/2018
 * Update: 5/27/2018
 */
public class Mario extends Canvas implements Runnable
{
    public boolean running;
    public boolean up, down, left, right; // player movement
    //public static boolean go; //start motion
    //public boolean motion = true;
    public static final int width = 1035, height = 715; // JFrame dimensions
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Dimension dim = new Dimension(width, height);
    Thread gameThread;
    JFrame frame;
    UI ui;
    InputHandler ih;
    Platform pf;
    Character ch;
    Items shroom, shroom2;
    public static double speed = 10, jspeed = 15; // Player movement speeds.
    private double FPS, UPS, frames, ticks, delta;
    private boolean shouldRender;

    // Game Fields.
    public int score, coins, lives = 4;
    public String name;
    public boolean jump, moving, finish, gameover, scroll = true;
    public long timer, startTime;
    public Enemy[] enemy;
    private Enemy testGoomba, testKoopa;
    public boolean died;
    private int obsolete = 0, obsolete2 = 0, tickTimer = 0;
    private boolean bandaid = false;

    // Sound Fields.
    private GameSound coinSound, deathSound, gameoverSound, endSound, mainSound;
    private GameSound jumpSound, flagpoleSound, powerUpSound, powerDownSound;

    /** Run method to control framerate.
     *  Main Game Thread.
     */
    public void run()
    {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60D;
        //long lastTimer = System.currentTimeMillis();
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
                //lastTimer += 1000;
                FPS = frames;
                UPS = ticks;
                frames = 0;
                ticks = 0;
            }
        }
    }

    /** Constructor
     *  Initializes Objects of the Game.
     */
    public Mario(String name)
    {
        createWindow();

        if(name.equals("Alt"))
            this.name = "Alternate";
        else
            this.name = name;

        // Sets Default Values.
        jump = true;
        died = false;
        finish = false;
        startTime = System.currentTimeMillis();

        // Objects of Game.
        ui = new UI(this);
        ih = new InputHandler(frame, this, this);
        pf = new Platform(this);
        ch = new Character(name, 300, 500, this);
        enemy = new Enemy[13];

        // New Sound Initialization. Loads the Misc. GameSounds.
        coinSound = new GameSound(GameSound.COIN_SOUND_URL);
        deathSound = new GameSound(GameSound.DEATH_SOUND_URL);
        gameoverSound = new GameSound(GameSound.GAMEOVER_SOUND_URL);
        endSound = new GameSound(GameSound.END_SOUND_URL);
        mainSound = new GameSound(GameSound.MAIN_SOUND_URL);
        jumpSound = new GameSound(GameSound.JUMP_SOUND_URL);
        flagpoleSound = new GameSound(GameSound.FLAGPOLE_SOUND_URL);
        powerUpSound = new GameSound(GameSound.POWERUP_SOUND_URL);
        powerDownSound = new GameSound(GameSound.POWERDOWN_SOUND_URL);

        // Experimental Features.
        //testGoomba = new Enemy("Goomba", pf.getX() + 800, pf.getY() + 600, this);
        //testGoomba.setBounds(pf.getX() + 200, pf.getX() + 400);
    }

    /** Tick method, does this method every frame.
     *  Controls motion and Collision Checks.
     */
    public void tick()
    {
        if(!finish && !gameover){
            timer = (400000 - Math.abs(startTime - System.currentTimeMillis()))/1000;
            tickTimer++;
            if(tickTimer == 1 && !bandaid){
                bandaid = true;
                playSound(GameSound.MAIN_SOUND);
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
        if(lives >= 0) {
            if(died && ui.getA() >= 255){
                obsolete++;
                if(obsolete == 1){
                    lives--;
                    playSound(GameSound.DEATH_SOUND);
                    mainSound.stop();   // Stops Main Soundtrack in event of Death.
                }
                else if(obsolete == 183){
                    ch.setLocation(300, 500);
                    //clipMain.setMicrosecondPosition(1610000);
                    //clipDeath.stop();
                    //clipDeath.setMicrosecondPosition(0);
                    playSound(GameSound.MAIN_SOUND);
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
        if(finish || gameover){
            obsolete2++;
            if(obsolete2 == 1){
                mainSound.stop();   // Stops Main Soundtrack.
                deathSound.stop();  // Stops Death sound. (In event of GameOver).

                if(finish){
                    playSound(GameSound.FLAGPOLE_SOUND);
                    playSound(GameSound.END_SOUND);
                    score += timer * 10;
                }
                else if(gameover)
                    playSound(GameSound.GAMEOVER_SOUND);
            }
        }
    }

    /**
     * Render method, creates the board with ball, players, and background
     *  Draws the objects of the Game.
     *  Theoretical 60 frames per second.
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

    /**
     * Starts main Game Thread.
     */
    public synchronized void start()
    {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Draws the JFrame that the game will be played in
     * Establishes the window.
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
     * Plays the GameSounds.
     * @param soundID Static Integer ID for each sound type.
     */
    public void playSound(int soundID)
    {
        switch (soundID){
            case GameSound.COIN_SOUND: coinSound.play(); break;
            case GameSound.DEATH_SOUND: deathSound.play(); break;
            case GameSound.GAMEOVER_SOUND: gameoverSound.play(); break;
            case GameSound.END_SOUND: endSound.play(); break;
            case GameSound.MAIN_SOUND: mainSound.play(); break;
            case GameSound.JUMP_SOUND: jumpSound.play(); break;
            case GameSound.FLAGPOLE_SOUND: flagpoleSound.play(); break;
            case GameSound.POWERUP_SOUND: powerUpSound.play(); break;
            case GameSound.POWERDOWN_SOUND: powerDownSound.play(); break;
            default: return;
        }
    }
}
