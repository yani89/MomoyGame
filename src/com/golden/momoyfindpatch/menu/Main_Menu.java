package com.golden.momoyfindpatch.menu;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.sprite.AdvanceSprite;
import com.golden.momoyfindpatch.MomoyGame;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author cyber-blackhat
 */
public class Main_Menu extends GameObject {

    public GameFont font;
    public GameFont smallfont;
    public GameFont titlefont2;

    GameFont tulis;

    private int pilihan;

    BufferedImage background;

    private AdvanceSprite leftanim, rightanim , upanim ,downanim;
    
    MomoyGame game;

    int		TOTAL_OPTION;
    int		selectedIndex;

    int TOTAL_OPTION2;
    int selectedIndex2;

    public Main_Menu(MomoyGame parent){
        super(parent);
        this.game = parent;
    }

    Main_Menu(GameEngine parent) {
        super(parent);
    }

    @Override
    public void initResources() {
        
        titlefont2 = fontManager.getFont(getImage("images/BitmapFont.png"));
        font = fontManager.getFont(getImages("images/COPFont.png", 8, 12));
        smallfont = fontManager.getFont(getImages("images/SmallFont.png", 8, 12));

        background = getImage("images/BackgroundMain.png");
        tulis = fontManager.getFont(getImages("images/font2.png", 16, 6));

        leftanim = new AdvanceSprite(getImages("images/dragon_left.png",8,1));
        leftanim.setAnimationFrame(new int[] { 0, 1, 2, 3, 4, 5, 6 });
        leftanim.setAnimate(true);
        leftanim.setLoopAnim(true);
        leftanim.getAnimationTimer().setDelay(120);

        rightanim = new AdvanceSprite(getImages("images/dragon_right.png",8,1));
        rightanim.setAnimationFrame(new int[] {0,1,2,3,4,5,6,7});
        rightanim.setAnimate(true);
        rightanim.setLoopAnim(true);
        rightanim.getAnimationTimer().setDelay(120);

        upanim = new AdvanceSprite(getImages("images/dragon_flying.png",8,8));
        upanim.setAnimationFrame(new int[] {8,9,10,11,12,13,14,15});
        upanim.setAnimate(true);
        upanim.setLoopAnim(true);
        upanim.getAnimationTimer().setDelay(120);

        downanim = new AdvanceSprite(getImages("images/dragon_flying.png",8,8));
        downanim.setAnimationFrame(new int[] {32,33,34,35,36,37,38,39});
        downanim.setAnimate(true);
        downanim.setLoopAnim(true);
        downanim.getAnimationTimer().setDelay(120);

        //playMusic("music/Music2.mid");
         playMusic("music/Music5.mid");
        
    }

    @Override
    public void update(long elapsedTime) {
        leftanim.update(elapsedTime);
        rightanim.update(elapsedTime);
        upanim.update(elapsedTime);
        downanim.update(elapsedTime);
//
        //jika di pencet turun maka akan turun dan berbunyi
        //memilih menu down
        if (keyPressed(KeyEvent.VK_DOWN)) {
			if (++pilihan > 6) {
				pilihan = 0;
			}
			playSound("sound/MenuSelect.wav");
	}
	if (keyPressed(KeyEvent.VK_UP)) {
			if (--pilihan < 0) {
				pilihan = 6;
			}
			playSound("sound/MenuSelect.wav");
       }
        if (keyPressed(KeyEvent.VK_ENTER)) {
			//playSound("sounds/switch.wav");

			switch (pilihan) {
				// start game
				case 0:
                                playSound("sound/MenuSelect.wav");
                                parent.nextGameID  = MomoyGame.Momoy_Game;
				///rent.nextGameID = WarlockGame.WARLOCK_GAME;
					finish();
				break;

				// instructions
				case 1:
                                playSound("sound/MenuSelect.wav");
				TutorialGame instructions = new TutorialGame(parent);
				instructions.start();
                                //finish();
				break;

                                //OptionMenu
                                case 2:
                                playSound("sound/MenuSelect.wav");
                                OptionMenu panggil = new OptionMenu(parent);
                                panggil.start();
                                //finish();
                                break;


				// high score
				case 3:
                                playSound("sound/MenuSelect.wav");
                                ScoreLast score = new ScoreLast(parent);
                                score.start();
				break;

				// sound on/off
				case 4:
                                playSound("sound/MenuSelect.wav");
				game.level++;
					if (game.level > 2) {
						game.level = 0;
					}
                                //finish();
				break;

				// level
				case 5:
                                playSound("sound/MenuSelect.wav");
				Credit credits = new Credit(parent);
				credits.start();
                                   
				break;

				// credits
				case 6:
                                 finish();
                                   
				break;

			}
		}
        
    }

    @Override
    public void render(Graphics2D g) {

        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(background, 10, 10, null);
	
        
        String lvl = game.levelDesc[game.level];
        tulis.drawString(g, "Start Game", 	GameFont.CENTER, 0, 190, getWidth());
        tulis.drawString(g, "Instructions",     GameFont.CENTER, 0, 230, getWidth());
	tulis.drawString(g, "Options Menu", 	GameFont.CENTER, 0, 270, getWidth());
	tulis.drawString(g, "Hi-Scores", 	GameFont.CENTER, 0, 310, getWidth());
	tulis.drawString(g, "Level:"+lvl,          GameFont.CENTER, 0, 350, getWidth());
	tulis.drawString(g, "Credits", 		GameFont.CENTER, 0, 410, getWidth());
	tulis.drawString(g, "Quit Game", 	GameFont.CENTER, 0, 540, getWidth());

        int y = (pilihan == 6) ? 480 : 150+(pilihan*40);
	leftanim. render(g,160,y);
	rightanim.render(g,520,y);
        
    }
    

}
