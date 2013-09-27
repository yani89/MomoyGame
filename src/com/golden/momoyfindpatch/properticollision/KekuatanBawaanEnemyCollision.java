/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golden.momoyfindpatch.properticollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import com.golden.momoyfindpatch.Momoy;

/**
 *
 * @author cyber-blackhat
 */
public class KekuatanBawaanEnemyCollision extends AdvanceCollisionGroup{

    Momoy game;

    public KekuatanBawaanEnemyCollision(Momoy game){
            this.game = game;
    }

    @Override
    public void collided(Sprite s1, Sprite s2) {
        s1.setActive(false);
        game.enemylife -= 1;
        if(game.enemylife == 0){
            s2.setActive(false);
            game.enemylife += 15;
        }else if(game.enemylife <= 0){
            game.enemylife += 5;
        }
    }

}
