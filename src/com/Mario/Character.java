package com.Mario;
/**
 * Character attributes, draws character and checks collision
 * and gravity physics
 *
 * @Daniel Bauer
 * @5/26/18
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.*;

public class Character
{
    Mario mario;
    public boolean big;
    private int x, y;   // Player Coordinates.
    private int bottom, gone, distance, dif, obsolete = 15;
    private int a, b;
    private String name;

    /**
     * Constructor for objects of class Character.
     * @param name String of Player.
     * @param x Coordinate.
     * @param y Coordinate.
     * @param m Main Game instance.
     *          Allows access to attributes of M.
     */
    public Character(String name, int x, int y, Mario m)
    {
        mario = m;
        this.x = x;
        this.y = y;
        bottom = 560;
        gone = 800;
        a = 3250;
        b = a + 3750;
        distance = 215;
        this.name = name;
        //pf = mario.pf;
    }

    /**
     * Resets the Character location.
     * Resets the stage after death.
     * @param x Coordinate.
     * @param y Coordinate.
     */
    public void setLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
        mario.pf.reset();
    }

    /**
     * Executes on every "Tick"
     */
    public void tick()
    {
        // Checks Player Collisions.
        this.checkCollision();

        // Basic UP/DOWN motions.
        if(mario.up){ y-= mario.speed; }
        if(mario.down){ y += mario.speed; }

        // Previous Code.
        //if(mario.left){ x -= mario.speed; }
        //if(mario.right){ x += mario.speed; }
        /*
        //checkCollision();
        if(mario.jump){
            if(mario.up){ // if player presses the up arrow
                if(obsolete > 0){ // limits jump
                    y -= mario.jspeed;
                    obsolete--;
                    if(obsolete == 14)
                        mario.playSound(GameSound.JUMP_SOUND);
                }
                else{
                    obsolete = 15;
                    mario.up = false;
                    mario.jump = false;
                }
            }
            else
                obsolete = 15;
        }
        */
        /*if(mario.down && big) // if player presses the down arrow
        //big = false; //player ducks
        else if(big)
        big = true;*/
        if(mario.finish) // mario leaves screen upon ending
            x += mario.speed;
    }

    /**
     * Gets the Players X Coordinate.
     * @return x Coordinate.
     */
    public int getX()
    {
        return x;
    }

    /**
     * Gets the Players Y Coordinate.
     * @return y Coordinate.
     */
    public int getY()
    {
        return y;
    }

    /**
     * Draws the Character.
     * Uses Sprites static renders.
     * @param g Graphics object.
     */
    public void render(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        if(this.name.toUpperCase().equals("ALTERNATE"))
            Sprites.renderAlt(x, y, big, g);
        else{
            Sprites.renderMario(x, y, big, "IDK YET", g);
        }
    }

    /**
     * New Player Collision Check.
     */
    public void checkCollision()
    {

    }

    /**
     * [OUTDATED]
     * collision tests for the character
     * 300 or so lines to check every platform collision
     */
    public void checkCollision(boolean preventBeingUsed)
    {
        int pfX = mario.pf.getX();
        int pfY = mario.pf.getY();
        if(big)
            dif = 0;
        else
            dif = -30;

        //if(y <= 560 /*&& !mario.up*/){
        //pipe/end wall collision tests
        if(y > pfY + 275){
            if(x > pfX+b + 2475){
                if(x <= pfX+b + 2500)
                    mario.left = false;
            }
        }
        if(y > pfY + 450){
            if(x >= pfX + 2160 && x <= pfX + 2200)
                mario.right = false;
            else if(x >= pfX + 2250 && x <= pfX + 2300)
                mario.left = false;
            else if(x >= pfX + 2710 && x <= pfX + 2750)
                mario.right = false;
            else if(x >= pfX + 2800 && x <= pfX + 2850)
                mario.left = false;
        }
        if(y > pfY + 500){
            if(x >= pfX + 1760 && x <= pfX + 1800)
                mario.right = false;
            else if(x >= pfX + 1850 && x <= pfX + 1900)
                mario.left = false;
        }
        if(y > pfY + 550){
            if(x >= pfX + 1260 && x <= pfX + 1300)
                mario.right = false;
            else if(x >= pfX + 1350 && x <= pfX + 1400)
                mario.left = false;
            else if(x >= pfX+b + 1060 && x <= pfX+b + 1100)
                mario.right = false;
            else if(x >= pfX+b + 1150 && x <= pfX+b + 1200)
                mario.left = false;
            else if(x >= pfX+b + 1910 && x <= pfX+b + 1950)
                mario.right = false;
            else if(x >= pfX+b + 2000 && x <= pfX+b + 2050)
                mario.left = false;
        }
        //blocks and steps collision tests
        if(x > pfX + 860 && x < pfX + 1150){
            if(x > pfX + 960 && x < pfX + 1050){
                if(y > pfY + 475)
                    fall(bottom); // below them
                else if(y < pfY + 475 && y > pfY + 275){
                    fall(360); //line of bricks and mysteries
                }
                else if(y < pfY + 275)
                    fall(160); //high mystery box
            }
            else{
                if(y > pfY + 475){
                    fall(bottom);
                }
                if(y < pfY + 475)
                    fall(360);
            }
        } //first mystery box
        else if(x > pfX + 660 && x < pfX + 750){
            if(y > pfY + 475){
                fall(bottom);
            }
            if(y < pfY + 475)
                fall(360);
        }
        else if(x > pfX + 1260 && x < pfX + 1400){
            fall(bottom - 100); //first pipe
        }
        else if(x > pfX + 1760 && x < pfX + 1900){
            fall(bottom - 150); //second pipe
        }
        else if((x > pfX + 2160 && x < pfX + 2300) ||
                (x > pfX + 2710 && x < pfX + 2850)){
            fall(bottom - 200); // third and fourth pipe
        }
        else if(x > pfX+a + 510 && x < pfX+a + 700){
            if(y > pfY + 475){
                fall(bottom);
            }
            if(y < pfY + 475)
                fall(360);
            if(x > pfX+a + 660 && y < pfY + 275)
                fall(160);
        }
        else if(x > pfX+a + 660 && x < pfX+a + 1100){
            if(y > pfY + 275){
                fall(bottom);
            }
            if(y < pfY + 275)
                fall(160);
        }
        else if(x > pfX+a + 1210 && x < pfX+a + 1450){
            if(y > pfY + 275){
                fall(bottom);
            }
            if(y < pfY + 275)
                fall(160);
            if(x > pfX+a + 1360){
                if(y > pfY + 275 && y < pfY + 475)
                    fall(360);
            }
        }
        else if(x > pfX+a + 1660 && x < pfX+a + 1800){
            if(y > pfY + 475){
                fall(bottom);
            }
            if(y < pfY + 475)
                fall(360);
        }
        else if(x > pfX+a + 1960 && x < pfX+a + 2050){
            if(y > pfY + 475){
                fall(bottom);
            }
            if(y < pfY + 475)
                fall(360);
        }
        else if(x > pfX+a + 2110 && x < pfX+a + 2200){
            if(y > pfY + 475){
                fall(bottom);
            }
            else if(y < pfY + 475 && y > pfY + 275)
                fall(360);
            else if(y < pfY + 275)
                fall(160);
        }
        else if(x > pfX+a + 2260 && x < pfX+a + 2350){
            if(y > pfY + 475){
                fall(bottom);
            }
            if(y < pfY + 475)
                fall(360);
        }
        else if(x > pfX+a + 2560 && x < pfX+a + 2650){
            if(y > pfY + 475){
                fall(bottom);
            }
            if(y < pfY + 475)
                fall(360);
        }
        else if(x > pfX+a + 2710 && x < pfX+a + 2900){
            if(y > pfY + 275){
                fall(bottom);
            }
            if(y < pfY + 275)
                fall(160);
        }
        else if(x > pfX+a + 3060 && x < pfX+a + 3300){
            if(y > pfY + 275){
                fall(bottom);
            }
            if(y < pfY + 275)
                fall(160);
            if(x > pfX+a + 3110 && x < pfX+a + 3250){
                if(y > pfY + 475)
                    fall(bottom);
                else if(y < pfY + 475 && y > pfY + 275)
                    fall(360);
                else if(y < pfY + 275)
                    fall(160);
            }
        }
        else if(x > pfX+b + 1060 && x < pfX+b + 1200){
            fall(bottom - 100);
        }
        else if(x > pfX+b + 1360 && x < pfX+b + 1600){
            if(y > pfY + 475){
                fall(bottom);
            }
            if(y < pfY + 475)
                fall(360);
        }
        else if(x > pfX+b + 1910 && x < pfX+b + 2050){
            fall(bottom - 100);
        }
        else if(x >= pfX+b + 2050 && x < pfX+b  + 2500){
            if(x > pfX+b + 2360){
                fall(160);
            }
            else if(x > pfX+b + 2310){
                fall(210);
                if(y > 170){
                    if(x >= pfX+b + 2360)
                        mario.right = false;
                }
            }
            else if(x > pfX+b + 2260){
                fall(260);
                if(y > 220){
                    if(x >= pfX+b + 2310)
                        mario.right = false;
                }
            }
            else if(x > pfX+b + 2210){
                fall(310);
                if(y > 270){
                    if(x >= pfX+b + 2260)
                        mario.right = false;
                }
            }
            else if(x > pfX+b + 2160){
                fall(360);
                if(y > 320){
                    if(x >= pfX+b + 2210)
                        mario.right = false;
                }
            }
            else if(x > pfX+b + 2110){
                fall(410);
                if(y > 370){
                    if(x >= pfX+b + 2160)
                        mario.right = false;
                }
            }
            else if(x > pfX+b + 2060){
                fall(460);
                if(y > 420){
                    if(x >= pfX+b + 2110)
                        mario.right = false;
                }
            }
            else if(x >= pfX+b + 2010){
                fall(510);
                if(y > 470){
                    if(x >= pfX+b + 2060)
                        mario.right = false;
                }
            }
            else
                fall(bottom);
        }
        else if(x > pfX+b + 660 && x <= pfX+b + 900){
            if(x < pfX+b + 750){
                fall(360);
            }
            else if(x < pfX+b + 800){
                fall(410);
                if(y > 370 && x <= pfX+b + 750)
                    mario.left = false;
            }
            else if(x < pfX+b + 850){
                fall(460);
                if(y > 420 && x <= pfX+b + 800)
                    mario.left = false;
            }
            else if(x < pfX+b + 900){
                fall(510);
                if(y > 470 && x <= pfX+b + 850)
                    mario.left = false;
            }
            else{
                fall(bottom);
                if(y > 520 && x <= pfX+b + 900)
                    mario.left = false;
            }
        }
        else if(x >= pfX+b + 310 && x < pfX+b + 600){
            if(x > pfX+b + 460){
                fall(360);
            }
            else if(x > pfX+b + 410){
                fall(410);
                if(y > 370 && x >= pfX+b + 460){
                    mario.right = false;
                }
            }
            else if(x > pfX+b + 360){
                fall(460);
                if(y > 420 && x >= pfX+b + 410){
                    mario.right = false;
                }
            }
            else if(x > pfX+b + 310){
                fall(510);
                if(y > 470 && x >= pfX+b + 360){
                    mario.right = false;
                }
            }
            else{
                fall(bottom);
                if(y > 520 && x >= pfX+b + 310){
                    mario.right = false;
                }
            }
        }
        else if(x >= pfX+a + 3600 && x <= pfX+a + 3660){
            fall(bottom);
            if(y > 370){
                if(x <= pfX+a + 3600)
                    mario.left = false;
                else if(x >= pfX+a + 3660)
                    mario.right = false;
            }
        }
        else if(x >= pfX+a + 3360 && x < pfX+a + 3600){
            if(x > pfX+a + 3510){
                fall(360);
            }
            else if(x > pfX+a + 3460){
                fall(410);
                if(y > 370 && x >= pfX+a + 3510){
                    mario.right = false;
                }
            }
            else if(x > pfX+a + 3410){
                fall(460);
                if(y > 420 && x >= pfX+a + 3460){
                    mario.right = false;
                }
            }
            else if(x > pfX+a + 3360){
                fall(510);
                if(y > 470 && x >= pfX+a + 3410){
                    mario.right = false;
                }
            }
            else{
                fall(bottom);
                if(y > 520 && x >= pfX+a + 3360){
                    mario.right = false;
                }
            }
        }
        else if(x > pfX+a + 3660 && x <= pfX+a + 3900){
            if(x < pfX+a + 3750){
                fall(360);
            }
            else if(x < pfX+a + 3800){
                fall(410);
                if(y > 370 && x <= pfX+a + 3750)
                    mario.left = false;
            }
            else if(x < pfX+a + 3850){
                fall(460);
                if(y > 420 && x <= pfX+a + 3800)
                    mario.left = false;
            }
            else if(x < pfX+a + 3900){
                fall(510);
                if(y > 470 && x <= pfX+a + 3850)
                    mario.left = false;
            }
            else{
                fall(bottom);
                if(y > 520 && x <= pfX+a + 3900)
                    mario.left = false;
            }
        }
        else
            fall(bottom);
        //}

        if(x > pfX + 3350 && x < pfX + 3410){ // holes
            if(y > 500){
                fall(gone);
                if(y > 580){
                    mario.left = false;
                    mario.right = false;
                    mario.up = false;
                    mario.died = true;
                }
            }
        }
        else if(x > pfX+a + 1000 && x < pfX+a + 1110){
            if(y > 500){
                fall(gone);
                if(y > 580){
                    mario.left = false;
                    mario.right = false;
                    mario.up = false;
                    mario.died = true;
                }
            }
        }
        else if(x >= pfX+b + 600 && x <= pfX+b + 660){
            if(y > 400){
                fall(gone);
                if(y > 450){
                    mario.left = false;
                    mario.right = false;
                    mario.up = false;
                    mario.died = true;
                }
            }
        }

        if(y > pfY + 500){ // bottom collision tests
            if(x > pfX + 660 && x < pfX + 750){
                if(y <= pfY + 540 + dif){
                    mario.up = false;
                    mario.pf.action(mario.pf.mystery.get(0), "Mushroom");
                }
            }
            else if(x > pfX + 860 && x < pfX + 1150){
                if(y <= pfY + 540 + dif){
                    mario.up = false;
                    if(x > pfX + 935 && x < pfX + 1010)
                        mario.pf.action(mario.pf.mystery.get(1), "Coin");
                    else if(x > pfX + 1035 && x < pfX +1110)
                        mario.pf.action(mario.pf.mystery.get(3), "Coin");
                }
            }
            else if(x > pfX+a + 510 && x < pfX+a + 700){
                if(y <= pfY + 540 + dif){
                    mario.up = false;
                    if(x > pfX+a + 585 && x < pfX+a + 660)
                        mario.pf.action(mario.pf.mystery.get(4), "Coin");
                }
            }
            else if(x > pfX+a + 1360 && x < pfX+a + 1450){
                if(y <= pfY + 540 + dif)
                    mario.up = false;
            }
            else if(x > pfX+a + 1660 && x < pfX+a + 1800){
                if(y <= pfY + 540 + dif)
                    mario.up = false;
            }
            else if(x > pfX+a + 1960 && x < pfX+a + 2050){
                if(y <= pfY + 540 + dif){
                    mario.up = false;
                    mario.pf.action(mario.pf.mystery.get(6), "Mushroom");
                }
            }
            else if(x > pfX+a + 2110 && x < pfX+a + 2200){
                if(y <= pfY + 540 + dif){
                    mario.up = false;
                    mario.pf.action(mario.pf.mystery.get(7), "Coin");
                }
            }
            else if(x > pfX+a + 2260 && x < pfX+a + 2350){
                if(y <= pfY + 540 + dif){
                    mario.up = false;
                    mario.pf.action(mario.pf.mystery.get(9), "COIN+");
                }
            }
            else if(x > pfX+a + 2560 && x < pfX+a + 2650){
                if(y <= pfY + 540 + dif){
                    mario.up = false;
                    //mario.pf.action(mario.pf.layerB.get(15), "Extra");
                }
            }
            else if(x > pfX+a + 3110 && x < pfX+a + 3250){
                if(y <= pfY + 540 + dif)
                    mario.up = false;
            }
            else if(x > pfX+b + 1360 && x < pfX+b + 1600){
                if(y <= pfY + 540 + dif){
                    mario.up = false;
                    if(x > pfX+b + 1460 && x < pfX+b + 1560)
                        mario.pf.action(mario.pf.mystery.get(12), "Coin");
                }
            }
        }
        else if(y > pfY + 300){
            if(x > pfX + 960 && x < pfX + 1050){
                if(y <= pfY + 340 + dif){
                    mario.up = false;
                    mario.pf.action(mario.pf.mystery.get(2), "Coin");
                }
            }
            else if(x > pfX+a + 660 && x < pfX+a + 1150){
                if(y <= pfY + 340 + dif){
                    mario.up = false;
                }
            }
            else if(x > pfX+a + 1210 && x < pfX+a + 1450){
                if(y <= pfY + 340 + dif){
                    mario.up = false;
                    if(x > pfX+a + 1385)
                        mario.pf.action(mario.pf.mystery.get(5), "Coin");
                }
            }
            else if(x > pfX+a + 2110 && x < pfX+a + 2200){
                if(y <= pfY + 340 + dif){
                    mario.up = false;
                    mario.pf.action(mario.pf.mystery.get(8), "Coin");
                }
            }
            else if(x > pfX+a + 2710 && x < pfX+a + 2900){
                if(y <= pfY + 340 + dif)
                    mario.up = false;
            }
            else if(x > pfX+a + 3060 && x < pfX+a + 3300){
                if(y <= pfY + 340 + dif){
                    mario.up = false;
                    if(x > pfX+a + 3135 && x < pfX+a + 3185)
                        mario.pf.action(mario.pf.mystery.get(10), "Coin");
                    else if(x > pfX+a + 3185 && x < pfX+a + 3235)
                        mario.pf.action(mario.pf.mystery.get(11), "Coin");
                }
            }
        }

        if(y > pfY + 410 && y < pfY + 540 + dif){

        }
        else if(y > pfY + 210 && y < pfY + 340){
            if(x > pfX + 960 && x < pfX + 1050){
                if(x >= pfX + 960 && x < pfX + 1000)
                    mario.right = false;
                else if(x > pfX + 1000 && x <= pfX + 1050)
                    mario.left = false;
            }
        }

        if(x > pfX+b + 2925){
            //Finish game
            mario.finish = true;
        }
    }

    /**
     * Gravity function of sorts.
     * @param stop Integer of when to stop falling.
     */
    public void fall(int stop)
    {
        if(!mario.up){
            if(y < stop){
                mario.jump = false;
                y += mario.jspeed + 1;
            }
            else if(y >= stop){
                y = stop;
                mario.jump = true;
            }
            if(stop == bottom){
                if(y >= 550){
                    y = 560;
                    mario.jump = true;
                }
            }
        }
    }
}
