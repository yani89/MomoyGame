/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golden.momoyfindpatch.enemycollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;
import com.golden.gamedev.object.collision.CollisionShape;
import com.golden.momoyfindpatch.Momoy;
import com.golden.momoyfindpatch.Musuh;

/**
 *
 * @author cyber-blackhat
 */

//Tabrakan antara Enemy dan Tile

public class EnemyTileCollision2 extends CollisionGroup{

    Momoy game;

    public EnemyTileCollision2(Momoy game){
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

    public void collided(Sprite s1, Sprite s2) {
            s2.setActive(true);
            if (isCollide(s1, s2, getCollisionShape1(s1), getCollisionShape2(s2)) == true) {// sprite not stuck
				((Musuh) s1).hitWall();
	    }
           revertPosition1();
           //s2.setActive(true);
        }

}
