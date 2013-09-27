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
public class EnemyPenjepitCollision extends AdvanceCollisionGroup{

    Momoy game;

    public EnemyPenjepitCollision(Momoy game){
            this.game = game;
    }

    @Override
    public void collided(Sprite s1, Sprite s2) {
        s2.setActive(true);
        s1.setActive(false);
        game.addPenjepit(s1.getX() + (s1.getWidth()-5), s1.getY() + (s1.getHeight()-80));
    }
}
