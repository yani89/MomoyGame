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

//MOMOY WITH GOAL OBJECT
public class PlayerGoalCollision extends AdvanceCollisionGroup{

    Momoy game;

    public PlayerGoalCollision(Momoy game){
            this.game = game;
    }

   @Override
    public CollisionShape getCollisionShape1(Sprite s1) {
	rect1.setBounds(s1.getX(), s1.getY(), s1.getWidth()-50, s1.getHeight());
        return rect1;
	}
    @Override
    public void collided(Sprite s1, Sprite s2) {
        s2.setActive(false);
        game.level++;
        game.time += 100;
        game.playSound("sound/LevelUp.wav");
        if (game.level <= 8){
            game.initLevel();
        }else{
        }
    }

}
