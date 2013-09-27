/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golden.momoyfindpatch.playercollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import com.golden.momoyfindpatch.Momoy;

/**
 *
 * @author cyber-blackhat
 */
public class PlayerDindingCollision extends AdvanceCollisionGroup{

    Momoy game;

    public PlayerDindingCollision(Momoy game){
            this.game = game;
    }

    @Override
    public void collided(Sprite s1, Sprite s2) {
        revertPosition1();
        s2.setActive(true);
    }
    

}
