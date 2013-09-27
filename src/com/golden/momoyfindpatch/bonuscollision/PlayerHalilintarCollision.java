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

//kekuatan halilintar fire
public class PlayerHalilintarCollision extends BasicCollisionGroup{

    Momoy game;

    public PlayerHalilintarCollision(Momoy game){
            this.game = game;
    }

    @Override
    public void collided(Sprite s1, Sprite s2) {
        s2.setActive(false);
        game.halilintarUP = true; 
    }

}
