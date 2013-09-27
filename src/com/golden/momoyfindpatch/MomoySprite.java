
package com.golden.momoyfindpatch;

// JFC
import com.golden.gamedev.object.Timer;
import java.awt.*;

// GTGE
import com.golden.gamedev.object.sprite.AdvanceSprite;
import java.awt.image.BufferedImage;



public class MomoySprite extends AdvanceSprite {

    int	score;
    public static final int STAND = 1,
                            WALKING = 2,
                            FIRE =3,
                            NOFIRE =4,
                            FIREBOLT = 5,
                            NOFIREBOLT = 6,
                            RUN = 7,
                            NORUN = 8;

    public static final int LEFT = 0,
                            RIGHT = 1,
                            UP = 2,
                            BOTTOM = 3,
                            LEFT_FIRE =4,
                            RIGHT_FIRE = 5,
                            UP_FIRE = 6,
                            BOTTOM_FIRE =7,
                            RUN_LEFT = 8,
                            RUN_RIGHT = 9,
                            RUN_UP = 10,
                            RUN_DOWN = 11
                            ;

    public Momoy game;
    Timer   fireTimer;



    public MomoySprite(Momoy game,BufferedImage[] imag){
        super(imag);
        this.game = game;
        setDirection(BOTTOM);
        fireTimer = new Timer(100);
    }
    @Override
    public void update(long elapsedTime){
        if (game.bsInput.getKeyPressed() == game.bsInput.NO_KEY) {
            if (isAnimate()) {
                setStatus(STAND);
            }         
        }

        super.update(elapsedTime);
    }
    @Override
    public void animationChanged(int oldStat, int oldDir,int status, int direction) {
       // setAnimationFrame(animation[direction]);
		switch (direction) {
			case LEFT:
                            setAnimationFrame(24,31);//new int[] {27,28,29,30,31,32,33,34,35});
                            break;
			case RIGHT:
                            setAnimationFrame(0,7);//new int[] {0,1,2,3,4,5,6,7,8});
                            break;
                        case UP :
                            setAnimationFrame(8,15);//new int[] {9,10,11,12,13,14,15,16,17});
                            break;
                        case BOTTOM :
                            setAnimationFrame(16,23);//new int[] {18,19,20,21,22,23,24,25,26});
                            break;

                        //test serang
                        case LEFT_FIRE :
                            setAnimationFrame(96,103);
                            break;
                        case RIGHT_FIRE:
                            setAnimationFrame(72,79);
                            break;
                        case UP_FIRE:
                            setAnimationFrame(80,87);
                            break;
                        case BOTTOM_FIRE:
                            setAnimationFrame(88,95);
                            break;

                        //MOmoy run
                        case RUN_LEFT:
                            setAnimationFrame(56,63);
                            break;
                        case RUN_RIGHT:
                            setAnimationFrame(32,38);
                            break;
                        case RUN_UP:
                            setAnimationFrame(40,47);
                            break;
                        case RUN_DOWN:
                            setAnimationFrame(48,55);
                            break;
                            

                }
		switch (status) {
			case STAND:
                            //setAnimate(false);
                            setLoopAnim(false);
                            break;
			case WALKING:
                            setAnimate(true);
                            setLoopAnim(true);
                            break;
                        case FIRE:
                            setAnimate(true);
                            //setLoopAnim(true);
                            break;
                        case NOFIRE:
                            setLoopAnim(false);
                            break;
                        case FIREBOLT:
                            setAnimate(true);
                            setLoopAnim(true);
                            break;
                        case NOFIREBOLT:
                            setLoopAnim(false);
                            break;
                        case RUN:
                            setAnimate(true);
                            setLoopAnim(true);
                            break;
                        case NORUN:
                            setLoopAnim(false);
                            break;

		}
    }
    @Override
     public void render(Graphics2D g,int x,int y) {
           super.render(g,x,y);
    }
    public void hitWall() {
        
    }


    
}
