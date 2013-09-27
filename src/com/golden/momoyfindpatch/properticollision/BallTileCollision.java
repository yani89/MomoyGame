/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golden.momoyfindpatch.properticollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import com.golden.gamedev.object.collision.CollisionShape;
import com.golden.momoyfindpatch.Momoy;

/**
 *
 * @author cyber-blackhat
 */
  //ball fire collision

public class BallTileCollision extends AdvanceCollisionGroup{

    Momoy game;

    public BallTileCollision(Momoy game){
            this.game = game;
    }

    @Override
        public CollisionShape getCollisionShape1(Sprite s1) {
                rect1.setBounds(s1.getX()+4, s1.getY()+18, s1.getWidth()-8, s1.getHeight()-18);
		return rect1;
        }
        @Override
        public CollisionShape getCollisionShape2(Sprite s2) {
                rect2.setBounds(s2.getX()-3, s2.getY()+3, s2.getWidth(), s2.getHeight());
		return rect2;
        }
        @Override
        public void collided(Sprite s1, Sprite s2) {
                s2.setActive(true);
                if (isCollide(s1, s2, getCollisionShape1(s1), getCollisionShape2(s2)) == true) {// sprite not stuck
				//((Musuh) s1).hitWall();
                                s1.setActive(true);
                                if(game.boomtime == 0){
                                     //boomtime = 100;
                                     game.addExplosionEffect(s1.getX() + (s1.getWidth()/2), s1.getY() + (s1.getHeight()/2));
                                     s1.setActive(false);
                                     s2.setActive(false);
                                }
			}
           revertPosition1();
        }

}
