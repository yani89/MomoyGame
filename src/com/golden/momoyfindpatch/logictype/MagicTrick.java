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
 *
 * @author  cyber-blackhat
 */
public class MagicTrick extends Musuh{

    private Timer time;

    public MagicTrick(Momoy game,BufferedImage[] imag, double speed, long animationDelay){
        super(game, imag, speed, animationDelay);
        setActive(true);
        setLoopAnim(true);
        setDirection(UP);

        time = new Timer(4000);
    }
    @Override
    public void update(long elapsedTime){
        super.update(elapsedTime);
        if (time.action(elapsedTime) && Utility.getRandom(0, 10) > 10) {
			changeDirection();
		}
    }
    @Override
    public void hitWall(){
        super.hitWall();
        int oldDir = getDirection();
		int dir = Utility.getRandom(LEFT, BOTTOM);
		//while (dir == oldDir) {
		//	dir = Utility.getRandom(LEFT, BOTTOM);
		//}
                if(dir == oldDir){
                    dir= Utility.getRandom(UP, RIGHT);
                }//else if(dir == oldDir){
                //    dir = Utility.getRandom(BOTTOM, UP);
                //}

		setDirection(dir);
    }
    private void changeDirection(){
        setDirection(Utility.getRandom(LEFT, BOTTOM));
    }
    
}
