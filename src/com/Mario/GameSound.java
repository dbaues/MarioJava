package com.Mario;

import javax.sound.sampled.*;
import java.io.*;

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

    // Sound IDs
    public static final int COIN_SOUND = 0;
    public static final int DEATH_SOUND = 1;
    public static final int GAMEOVER_SOUND = 2;
    public static final int END_SOUND = 3;
    public static final int MAIN_SOUND = 4;
    public static final int JUMP_SOUND = 5;
    public static final int FLAGPOLE_SOUND = 6;
    public static final int POWERUP_SOUND = 7;
    public static final int POWERDOWN_SOUND = 8;

    //public static final GameSound PUP = new GameSound(GameSound.POWERUP_SOUND_URL);

    // Class Fields.
    private Clip clip;
    private boolean fileFound;
    private boolean mainSoundTrack;

    /**
     * Initializes a Sound clip.
     * @param fileName String File name. URL. Use the static Final values.
     */
    public GameSound(String fileName)
    {
        // Boolean whether it's the Main Soundtrack.
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
