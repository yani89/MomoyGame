/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golden.momoyfindpatch.playercollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.momoyfindpatch.Momoy;

/**
 *
 * @author cyber-blackhat
 */
public class PlayerGuntingCollision extends AdvanceCollisionGroup{

    public BasicCollisionGroup playerwithgunting;
    Momoy game;

    public PlayerGuntingCollision(Momoy game){
            this.game = game;
    }

    @Override
    public void collided(Sprite s1, Sprite s2) {
         s2.setActive(false);
            game.jumlahappletermakan = true;
            if (game.jumlahappletermakan == true){
                game.playerwithgunting = new BasicCollisionGroup(){

                @Override
                public void collided(Sprite s1, Sprite s2) {
                    revertPosition2();
                    s1.setActive(false);
                }
           };
               // game.playfield.addCollisionGroup(game.TILE, game.MOMOY, playerwithgunting);
           }
           game.addBintang2(s2.getX() + (s2.getWidth()/2), s2.getY() + (s2.getHeight()/2));

    }

}
