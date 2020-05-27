package com.Mario;

/**
 * Key listener event handler
 *
 * @Daniel Bauer
 * @5/24/18
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Canvas;
import javax.swing.JFrame;

public class InputHandler implements KeyListener
{
    Mario mario;
    int preset;
    //private int up, down, left, right;
    /** Constructor
     * @param JFrame, canvas, WHG (this, this - for last two)
     */
    public InputHandler(JFrame f, Canvas c, Mario m)
    {
        mario = m;
        f.addKeyListener(this); // adds key listener to the frame
        c.addKeyListener(this); // adds key listener to the drawn objects (canvas)
        //preset = 1;
    }

    /** Method keyPressed - does when certain keys are pressed
     *
     */
    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_UP && mario.jump) // if up arrow is pressed
            mario.up = true; // up boolean is true (player 2 will go up)
        if(keyCode == KeyEvent.VK_DOWN){ // if down arrow is pressed
            mario.down = true; // down boolean is true (player 2 will go down)
            //mario.moving = true;
        }
        if(mario.scroll){
            if(keyCode == KeyEvent.VK_LEFT){ // if w key is pressed
                mario.left = true; // up boolean is true (player 1 will go up)
                mario.moving = true;
            }
            if(keyCode == KeyEvent.VK_RIGHT){ // if right arrow key is pressed
                mario.right = true; // s boolean is true (player 1 will go down)
                mario.moving = true;
            }
        }
    }

    /** Method keyReleased - instructions for when user releases the key
     *
     */
    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_UP){ // if up arrow is released
            mario.up = false; // up boolean is false (player 2 will stop going up)
            mario.jump = false;
        }
        if(keyCode == KeyEvent.VK_DOWN) //if down arrow is released
            mario.down = false; // down boolean is false (player 2 will stop going down)
        if(keyCode == KeyEvent.VK_LEFT){ // if w key is released
            mario.left = false; // w boolean is false (player 1 will stop going up)
            mario.moving = false;
        }
        if(keyCode == KeyEvent.VK_RIGHT){ // if s key is released
            mario.right = false; // s key is false (player 1 will stop going down)
            mario.moving = false;
        }
    }

    /** Method that is required to compile, but has no function
     *
     */
    public void keyTyped(KeyEvent e){}
}