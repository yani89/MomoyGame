/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golden.momoyfindpatch;

import java.awt.image.BufferedImage;

/**
 *
 * @author cyber-blackhat
 */
public class Musuh extends MomoySprite {

    private double speed;

    public Musuh (Momoy game,BufferedImage[] imag,double speed,long animationDelay){
         super(game, imag);
         this.speed =  speed;
         getAnimationTimer().setDelay(animationDelay);
    }
   @Override
	public void setDirection(int direction) {
		super.setDirection(direction);
		switch (direction) {
			case LEFT:
                            setSpeed(-speed, 0);
                            setAnimationFrame(24,31);
                            break;
			case RIGHT:
                            setSpeed(speed, 0);
                            setAnimationFrame(0,7);
                            break;
			case UP:
                            setSpeed(0, -speed);
                            setAnimationFrame(8,15);
                            break;
			case BOTTOM:
                            setSpeed(0, speed);
                            setAnimationFrame(16,23);
                            break;
		}
	}
    @Override
        public void update(long elapsedTime){
            super.update(elapsedTime);
        }
        @Override
	protected void updateMovement(long elapsedTime) {
		super.updateMovement(elapsedTime);

	}

}
