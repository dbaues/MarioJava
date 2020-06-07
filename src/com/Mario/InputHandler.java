package com.Mario;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Canvas;
import javax.swing.JFrame;

/**
 * KeyListener for Mario Game.
 * @author Dan Bauer
 */
public class InputHandler implements KeyListener
{
    Mario mario;

    /** Constructor for the Input Handler.
     * (*, this, this - for last two)
     * @param f JFrame of the current JFrame instance.
     * @param c Canvas of the current Canvas instance.
     *          Mario Class is a subclass of Canvas.
     * @param m Main Game instance.
     */
    public InputHandler(JFrame f, Canvas c, Mario m)
    {
        this.mario = m;
        f.addKeyListener(this); // Adds KeyListener to Frame.
        c.addKeyListener(this); // Adds KeyListener to Canvas.
    }

    /**
     * Method keyPressed - Executes when certain keys are pressed.
     */
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        // When the UP ARROW or W KEY is pressed.
        if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W /*&& mario.jump*/) {
            mario.down = false;
            mario.up = true; // Up boolean. Moves Player up.
        }

        // When the DOWN ARROW or S KEY is pressed.
        if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S)
            mario.down = true; // Down boolean. Moves Player down.

        if(mario.scroll){
            // When the LEFT ARROW or A KEY is pressed.
            if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A){
                mario.left = true; // Left Boolean. Player goes Left (In actuality, Stage moves).
                mario.moving = true;
            }
            // When the RIGHT ARROW or D KEY is pressed.
            if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D){
                mario.right = true; // Right Boolean. Player goes Right (In actuality, Stage moves).
                mario.moving = true;
            }
        }

        // Toggles debugging info when TAB is pressed.
        if(keyCode == KeyEvent.VK_P)
            mario.debuggingMode = !mario.debuggingMode;
    }

    /**
     * Method keyReleased - Executes when user releases the key.
     */
    public void keyReleased(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        // When UP ARROW or W KEY is released.
        if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W){
            mario.up = false; // Up Motion will stop.
            mario.down = true;
        }
        // When DOWN ARROW or S KEY is released.
        if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S)
            mario.down = false; // Down Motion will stop.

        // When LEFT ARROW or A KEY is released.
        if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A){
            mario.left = false; // Left Motion will stop.
            mario.moving = false;
        }
        // When RIGHT ARROW or D KEY is released.
        if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D){
            mario.right = false; // Right Motion will stop.
            mario.moving = false;
        }
    }

    /**
     * Method that is required to compile. Has no function.
     * Part of the interface implementation.
     */
    public void keyTyped(KeyEvent e){}
}