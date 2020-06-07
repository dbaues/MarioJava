package com.Mario;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Main Game Class.
 * @author Dan Bauer
 * Original: 5/23/2018
 * Updated: 5/27/2018
 */
public class Mario extends Canvas implements Runnable
{
    public boolean running;
    public boolean up, down, left, right; // player movement
    //public static boolean go; //start motion
    //public boolean motion = true;
    public static final int width = 1035, height = (15*Block.HEIGHT)-11; // JFrame dimensions
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Dimension dim = new Dimension(width, height);
    Thread gameThread;
    JFrame frame;
    UI ui;
    InputHandler ih;
    Platform pf;
    Character ch;
    ArrayList<Items> items;
    public static double speed = 8, jspeed = 12; // Player movement speeds.

    // Game Fields.
    public int score, coins, lives = 4;
    public String name;
    public boolean jump, moving, finish, gameover, scroll = true;
    public long timer, startTime;
    public boolean died;
    private int obsolete = 0, obsolete2 = 0, tickTimer = 0;
    private boolean bandaid = false;

    public boolean debuggingMode = false;

    // Sound Fields.
    private final GameSound coinSound, deathSound, gameoverSound, endSound, mainSound;
    private final GameSound jumpSound, flagpoleSound, powerUpSound, powerDownSound;

    /**
     * Run method to control frame rate.
     * Main Game Thread.
     */
    public void run()
    {
        boolean shouldRender;
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60D;
        double delta = 0D;

        // Main Game Thread Loop.
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            shouldRender = false;

            while(delta >= 1) {
                tick();
                delta--;
                shouldRender = true;
            }
            if(shouldRender){ render(); }
        }
    }

    /**
     * Constructor
     * Initializes Objects of the Game.
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
        ch = new Character(name, 384, 480, this);
        items = new ArrayList<>();

        // New Sound Initialization. Loads the Misc. GameSounds.
        coinSound = GameSound.COIN_SOUND;
        deathSound = GameSound.DEATH_SOUND;
        gameoverSound = GameSound.GAMEOVER_SOUND;
        endSound = GameSound.END_SOUND;
        mainSound = GameSound.MAIN_SOUND;
        jumpSound = GameSound.JUMP_SOUND;
        flagpoleSound = GameSound.FLAGPOLE_SOUND;
        powerUpSound = GameSound.POWERUP_SOUND;
        powerDownSound = GameSound.POWERDOWN_SOUND;
    }

    /**
     * Tick method, Executes every frame.
     * Controls motion and Collision Checks.
     */
    public void tick()
    {
        if(!finish && !gameover){
            timer = (400000 - Math.abs(startTime - System.currentTimeMillis()))/1000;
            tickTimer++;
            if(tickTimer == 1 && !bandaid){
                bandaid = true;
                playSound(GameSound.MAIN_SOUND_ID);
            }
        }
        if(pf != null){ pf.tick(); } // Platform.
        if(ch != null){ ch.tick(); } // Character.
        if(ui != null){ ui.tick(); } // User Interface.
        for(Items i : items)         // Items.
            i.tick();

        if(lives >= 0) {
            if(died && ui.getA() >= 255){
                obsolete++;
                if(obsolete == 1){
                    lives--;
                    playSound(GameSound.DEATH_SOUND_ID);
                    mainSound.stop();   // Stops Main Soundtrack in event of Death.
                }
                else if(obsolete == 183){
                    ch.setLocation(350,500);
                    playSound(GameSound.MAIN_SOUND_ID);
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
                    playSound(GameSound.FLAGPOLE_SOUND_ID);
                    playSound(GameSound.END_SOUND_ID);
                    score += timer * 10;
                }
                else if(gameover)
                    playSound(GameSound.GAMEOVER_SOUND_ID);
            }
        }
    }

    /**
     * Render method, creates the board with ball, players, and background
     * Draws the objects of the Game.
     * Theoretical 60 frames per second.
     */
    public void render()
    {
        // Couldn't tell you what these three do XD.
        BufferStrategy strat = getBufferStrategy();
        if(strat == null){ createBufferStrategy(3); return; }
        Graphics g = strat.getDrawGraphics();

        // Draws Game Elements.
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        if(pf != null){ pf.render(g); } // Draws Platform.
        if(ch != null){ ch.render(g); } // Draws the Character.
        if(ui != null){ ui.render(g); } // Draws UI Elements.

        // Testing Drawing.
        //Sprites.renderMushroom(50, 50, g);
        //Sprites.render1Up(100, 50, g);

        // Cleanup.
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
            case GameSound.COIN_SOUND_ID -> coinSound.play();
            case GameSound.DEATH_SOUND_ID -> deathSound.play();
            case GameSound.GAMEOVER_SOUND_ID -> gameoverSound.play();
            case GameSound.END_SOUND_ID -> endSound.play();
            case GameSound.MAIN_SOUND_ID -> mainSound.play();
            case GameSound.JUMP_SOUND_ID -> jumpSound.play();
            case GameSound.FLAGPOLE_SOUND_ID -> flagpoleSound.play();
            case GameSound.POWERUP_SOUND_ID -> powerUpSound.play();
            case GameSound.POWERDOWN_SOUND_ID -> powerDownSound.play();
        }
    }
}
