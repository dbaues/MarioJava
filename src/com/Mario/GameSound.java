package com.Mario;

import javax.sound.sampled.*;
import java.io.*;

/**
 * Defines a GameSound.
 * Contains predefined GameSounds for the main Nine Sounds.
 * @author Dan Bauer
 */
public class GameSound
{
    // File name Strings.
    public static final String COIN_SOUND_URL = "res/Coin_Sound.wav";
    public static final String DEATH_SOUND_URL = "res/Death_Sound.wav";
    public static final String GAMEOVER_SOUND_URL = "res/Gameover_Sound.wav";
    public static final String END_SOUND_URL = "res/End_Sound.wav";
    public static final String MAIN_SOUND_URL = "res/Main_Sound.wav";
    public static final String JUMP_SOUND_URL = "res/Jump_Sound.wav";
    public static final String FLAGPOLE_SOUND_URL= "res/Flag_Pole_Sound.wav";
    public static final String POWERUP_SOUND_URL = "res/Power_Up_Sound.wav";
    public static final String POWERDOWN_SOUND_URL = "res/Power_Down_Sound.wav";

    // Sound IDs.
    public static final int COIN_SOUND_ID = 0;
    public static final int DEATH_SOUND_ID = 1;
    public static final int GAMEOVER_SOUND_ID = 2;
    public static final int END_SOUND_ID = 3;
    public static final int MAIN_SOUND_ID = 4;
    public static final int JUMP_SOUND_ID = 5;
    public static final int FLAGPOLE_SOUND_ID = 6;
    public static final int POWERUP_SOUND_ID = 7;
    public static final int POWERDOWN_SOUND_ID = 8;

    // Static defined Sounds.
    public static final GameSound COIN_SOUND= new GameSound(GameSound.COIN_SOUND_URL);
    public static final GameSound DEATH_SOUND= new GameSound(GameSound.DEATH_SOUND_URL);
    public static final GameSound GAMEOVER_SOUND= new GameSound(GameSound.GAMEOVER_SOUND_URL);
    public static final GameSound END_SOUND= new GameSound(GameSound.END_SOUND_URL);
    public static final GameSound MAIN_SOUND= new GameSound(GameSound.MAIN_SOUND_URL);
    public static final GameSound JUMP_SOUND= new GameSound(GameSound.JUMP_SOUND_URL);
    public static final GameSound FLAGPOLE_SOUND= new GameSound(GameSound.FLAGPOLE_SOUND_URL);
    public static final GameSound POWERUP_SOUND= new GameSound(GameSound.POWERUP_SOUND_URL);
    public static final GameSound POWERDOWN_SOUND= new GameSound(GameSound.POWERDOWN_SOUND_URL);

    // Class Fields.
    private Clip clip;
    private boolean fileFound;
    private final boolean mainSoundTrack;

    /**
     * Initializes a Sound clip.
     * @param fileName String File name. URL. Use the static Final values.
     */
    public GameSound(String fileName)
    {
        // Boolean determining whether it's the Main Soundtrack.
        this.mainSoundTrack = fileName.equals(GameSound.MAIN_SOUND_URL);

        try{
            // Gets the Clip from File.
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName).getAbsoluteFile());
            this.clip = AudioSystem.getClip();
            this.clip.open(ais);
            this.fileFound = true;
        }
        catch(Exception ex){ ex.printStackTrace(); this.fileFound = false; }
    }

    /**
     * Plays the Audio clip.
     */
    public void play()
    {
        // Doesn't play if FileNotFoundException.
        if(!fileFound){ return; }

        // Stops, resets, and then plays clip.
        this.stop();
        clip.setMicrosecondPosition(0);
        clip.start();

        // If Main Soundtrack, loop continuously.
        if(mainSoundTrack){ clip.loop(Clip.LOOP_CONTINUOUSLY); }
    }

    /**
     * Stops this clip.
     */
    public void stop(){ clip.stop(); }
}
