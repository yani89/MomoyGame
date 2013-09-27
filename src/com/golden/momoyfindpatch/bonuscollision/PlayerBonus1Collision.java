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


 /*****************************************************************************
  * BONUSSS COLLISION .................................................
  * ADD COIN WITH STAR SYMBOL
  * ***************************************************************************
  */
public class PlayerBonus1Collision extends BasicCollisionGroup {

    Momoy game;

    public PlayerBonus1Collision(Momoy game){
            this.game = game;
    }

    @Override
    public void collided(Sprite s1, Sprite s2) {
        s2.setActive(false);
        game.score += 100;
        game.spark = true;
        game.jumlahbintang += 1;
        game.addBintang2(s2.getX() + (s2.getWidth()/2), s2.getY() + (s2.getHeight()/2));
        game.playSound("sound/sparkle.wav");
    }

}
