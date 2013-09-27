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

//Tabrakan antara enemy dan player

public class EnemyPlayerCollision extends AdvanceCollisionGroup{

    Momoy game;

    public EnemyPlayerCollision(Momoy game){
            this.game = game;
    }
    
    @Override
        public void collided(Sprite s1, Sprite s2) {
            s2.setActive(true);
            

            game.life -= 1;
            if(game.life >= 1){
                game.initLevel();
            }
            if(game.life == 0){
               game.showHiScore();
            }
            game.addExplosionEffect2(s1.getX() + (s1.getWidth()/2), s1.getY() + (s1.getHeight()/2));
        }

}
