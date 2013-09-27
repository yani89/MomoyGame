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
public class KekuatanBawaanTileCollision extends AdvanceCollisionGroup{

    Momoy game;

    public KekuatanBawaanTileCollision(Momoy game){
            this.game = game;
    }
    
    @Override
    public void collided(Sprite s1, Sprite s2) {
        s1.setActive(false);
        game.addLedakanKekuatanBawaan(s1.getX() + (s1.getWidth()-5), s1.getY() + (s1.getHeight()));
    }

}
