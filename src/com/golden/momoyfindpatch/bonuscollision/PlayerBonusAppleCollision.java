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
public class PlayerBonusAppleCollision extends BasicCollisionGroup{

    Momoy game;

    public PlayerBonusAppleCollision(Momoy game){
            this.game = game;
    }

    @Override
    public void collided(Sprite s1, Sprite s2) {
         s2.setActive(false);
         game.life += 1;
         game.jumlahapple += 1;
         game.addBintang2(s2.getX() + (s2.getWidth()/2), s2.getY() + (s2.getHeight()/2));
    }

}
