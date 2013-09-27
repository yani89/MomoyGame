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
/*
 * COLLISION MUSUH DENGAN TEMBOK
 */
public class EnemyTileCollision extends AdvanceCollisionGroup{

    Momoy game;

    public EnemyTileCollision(Momoy game){
            this.game = game;
    }

    @Override
    public void collided(Sprite s1, Sprite s2) {
         if (collisionSide == LEFT_RIGHT_COLLISION ||collisionSide == RIGHT_LEFT_COLLISION) {
            revertPosition1();
            s2.setActive(true);
         }
    }

}
