/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golden.momoyfindpatch.enemycollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import com.golden.momoyfindpatch.Momoy;

/**
 *
 * @author cyber-blackhat
 */

//Tabrakan antara enemy dan halilintar
public class EnemyHalilintarCollision extends AdvanceCollisionGroup {

    Momoy game;

    public EnemyHalilintarCollision(Momoy game){
            this.game = game;
    }
    
    @Override
        public void collided(Sprite s1, Sprite s2) {
            s2.setActive(true);
            game.addExplosionAsap(s1.getX() + (s1.getWidth()/2), s1.getY() + (s1.getHeight()/2));
            s1.setActive(false);
    }

}
