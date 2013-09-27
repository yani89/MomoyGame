/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golden.momoyfindpatch.properticollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.momoyfindpatch.Momoy;

/**
 *
 * @author cyber-blackhat
 */
public class PlayerBoxmovedCollision extends BasicCollisionGroup{

    Momoy game;

    public PlayerBoxmovedCollision(Momoy game){
            this.game = game;
    }

    @Override
        public void collided(Sprite s1, Sprite s2) {
            game.buka2 = true;
            //s2.setActive(false)
        }

}
