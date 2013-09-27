/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golden.momoyfindpatch.bonuscollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.momoyfindpatch.Momoy;

/**
 *
 * @author cyber-blackhat
 */
public class PlayerPOWERUP1Collision extends BasicCollisionGroup{

    Momoy game;

    public PlayerPOWERUP1Collision(Momoy game){
            this.game = game;
    }
    
    @Override
    public void collided(Sprite s1, Sprite s2) {
        s2.setActive(false);
        game.powerUP = true;
        game.powertime2 += 50;
    }

}
