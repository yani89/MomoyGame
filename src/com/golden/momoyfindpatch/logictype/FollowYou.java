/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golden.momoyfindpatch.logictype;

import com.golden.gamedev.object.Timer;
import com.golden.gamedev.util.Utility;
import com.golden.momoyfindpatch.Momoy;
import com.golden.momoyfindpatch.MomoySprite;
import com.golden.momoyfindpatch.Musuh;
import java.awt.image.BufferedImage;

/**
 * @author kevin glass
 * @remaker source cyber-blackhat
 */
public class FollowYou extends Musuh {
    
    private MomoySprite target;
    private Timer 	timer;

    public FollowYou(Momoy game,BufferedImage[] imag,double speed,long animationDelay,MomoySprite target){
        super(game, imag, speed, animationDelay);
        setAnimate(true);
	setLoopAnim(true);
        timer = new Timer(10);

        this.target = target;

        findTarget(-1);
    }
    @Override
    public void update(long elapsedTime) {
		if (timer.action(elapsedTime)) {
			findTarget(-1);
			timer.setDelay(10);
		}
		super.update(elapsedTime);
	}
    @Override
    public void hitWall() {
		super.hitWall();
		// target direction and current direction must be different
		findTarget(getDirection());
		timer.setDelay(300);
	}
    private void findTarget(int disallowDir) {
		int dir = -1;
		if ((int) getX() < (int) target.getX() && disallowDir != RIGHT) {
			dir = RIGHT;
		}
		if (dir == -1 && (int) getX() > (int) target.getX() && disallowDir != LEFT) {
			dir = LEFT;
		}
   		if (dir == -1 && (int) getY() < (int) target.getY() && disallowDir != BOTTOM) {
			dir = BOTTOM;
		}
		if (dir == -1 && (int) getY() > (int) target.getY() && disallowDir != UP) {
			dir = UP;
		}
		if (dir == -1 || (getX() == getOldX() && getY() == getOldY())) {
			// can't find target
			changeDirection(disallowDir);
			timer.setDelay(200);
			return;
		}
		setDirection(dir);
	}


	private void changeDirection(int disallowDir) {
		int dir = Utility.getRandom(LEFT, BOTTOM);
		while (dir == disallowDir) {
			dir = Utility.getRandom(LEFT, BOTTOM);
		}

		setDirection(dir);
	}



}
