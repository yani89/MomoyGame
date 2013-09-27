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
 //jika ball menyentuh dinding score maka ball akan hilang atau meledak
public class BallDindingCollision extends AdvanceCollisionGroup{

    Momoy game;

    public BallDindingCollision(Momoy game){
            this.game = game;
    }

    @Override
	public CollisionShape getCollisionShape1(Sprite s1) {
	  rect1.setBounds(s1.getX()+4, s1.getY()+18, s1.getWidth()-8, s1.getHeight()-18);
          return rect1;
	}
        public void collided(Sprite s1, Sprite s2) {
			revertPosition1();
                        s2.setActive(true);
                        s1.setActive(false);
                        game.addExplosionEffect(s1.getX() + (s1.getWidth()-3), s1.getY() + (s1.getHeight()+36));
		}

}
