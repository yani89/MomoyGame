/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golden.momoyfindpatch.logictype;

import com.golden.momoyfindpatch.Momoy;
import com.golden.momoyfindpatch.MomoySprite;
import com.golden.momoyfindpatch.Musuh;
import java.awt.image.BufferedImage;

/**
 * @author kevin glass
 * @remaker source cyber-blackhat
 */
public class LeftRight extends Musuh{
        
        public LeftRight(Momoy game,BufferedImage[] imag,double speed,long animationDelay){
            super(game, imag, speed, animationDelay);
             
		setAnimate(true);
		setLoopAnim(true);
	}


    @Override
	public void hitWall() {
		super.hitWall();

		setDirection((getDirection() == LEFT) ? RIGHT : LEFT);
	}
}
