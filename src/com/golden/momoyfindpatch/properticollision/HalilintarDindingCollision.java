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
public class HalilintarDindingCollision extends BasicCollisionGroup {

    Momoy game;

    public HalilintarDindingCollision(Momoy game){
            this.game = game;
    }
    @Override
        public void collided(Sprite s1, Sprite s2) {
            s1.setActive(false);
            s2.setActive(true);
        }

}
