/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golden.momoyfindpatch.logictype;

import com.golden.momoyfindpatch.Momoy;
import com.golden.momoyfindpatch.Musuh;
import java.awt.image.BufferedImage;

/**
 * @author kevin glass
 * @remaker source cyber-blackhat
 */
public class RightPatrol extends Musuh{

    public RightPatrol(Momoy game,BufferedImage[] image,double speed, long animationDelay){
        super(game,image,speed,animationDelay);
        setDirection(BOTTOM);
	setAnimate(true);
        setLoopAnim(true);
    }
    @Override
	public void hitWall() {
		super.hitWall();
                switch ( getDirection()) {
			case UP: 	setDirection(RIGHT); break;
			case RIGHT: 	setDirection(BOTTOM); break;
			case BOTTOM: 	setDirection(LEFT); break;
			case LEFT:	setDirection(UP); break;
		}
    }
}
