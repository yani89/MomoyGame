/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golden.momoyfindpatch.logictype;

import com.golden.gamedev.object.Timer;
import com.golden.gamedev.util.Utility;
import com.golden.momoyfindpatch.Momoy;
import com.golden.momoyfindpatch.Musuh;
import java.awt.image.BufferedImage;

/**
 * @author kevin glass
 * @remaker source cyber-blackhat
 */
public class Round extends Musuh{

    private Timer 	timer;


	public Round(Momoy game,BufferedImage[] image,double speed, long animationDelay) {
		super(game,image,speed,animationDelay);

		changeDirection();
		setAnimate(true);
		setLoopAnim(true);

		timer = new Timer(2500); // change direction every 2.5 sec
	}


    @Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);

		if (timer.action(elapsedTime) && Utility.getRandom(0, 2) > 0) {
			changeDirection();
		}
	}


    @Override
	public void hitWall() {
		super.hitWall();

		int oldDir = getDirection();
		int dir = Utility.getRandom(LEFT, BOTTOM);
		while (dir == oldDir) {
			dir = Utility.getRandom(LEFT, BOTTOM);
		}

		setDirection(dir);
	}


	private void changeDirection() {
		setDirection(Utility.getRandom(LEFT, BOTTOM));
	}
}
