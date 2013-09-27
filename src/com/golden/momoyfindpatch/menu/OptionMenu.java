/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golden.momoyfindpatch.menu;


import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.util.FileUtil;
import com.golden.momoyfindpatch.MomoyGame;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author cyber-blackhat
 */
public class OptionMenu extends GameObject {

    public GameFont font;
    public GameFont smallfont;
    int		TOTAL_OPTION;
    int		selectedIndex;

    int TOTAL_OPTION2;
    int selectedIndex2;

    //active and close sound
    private int selected = 1;
    private int selected2 = 1;

    //membuat menjadi berlembar-lembar
   // private GameFont font;
    private PlayField   playfield;
    private Background  background1;
    private int pages= 1;

    //menganti control
    private int controlNum;
    private boolean gantiControl;
    private BufferedImage arrow;
    private int selectede;

    private boolean blink;
    private Timer blinkTimer = new Timer(400);

    private boolean blinking;
    private Timer blinkTimer2 = new Timer(1000);


    public OptionMenu(GameEngine parent){
        super(parent);
    }

    @Override
    public void initResources() {
        //super.initResources();
        background1 = new ImageBackground(getImage("images/CreaditBackground.png"), 800, 600);
        font = fontManager.getFont(getImages("images/COPFont.png", 8, 12));
        smallfont = fontManager.getFont(getImages("images/SmallFont.png", 8, 12));

        TOTAL_OPTION = 7;
	selectedIndex = TOTAL_OPTION;

        TOTAL_OPTION2 = 2;
	selectedIndex2 = TOTAL_OPTION2;
        arrow = getImage("images/Arrow.png");
	
		
		//TOTAL_OPTION = 7;
		//selectedIndex = TOTAL_OPTION;
    }

    @Override
    public void update(long elapsedTime) {


        if(keyPressed(KeyEvent.VK_ESCAPE)){
               finish();
        }

        if (blinkTimer.action(elapsedTime)) {
            blink = !blink;
        }

        if (blinkTimer2.action(elapsedTime)){
            blinking = !blinking;
        }


        //super.update(elapsedTime);
        background1.update(elapsedTime);

        if (blinkTimer.action(elapsedTime)) {
            blink = !blink;
        }
     //untuk mengatur konfigurasi jika ditekan
        if (keyPressed(KeyEvent.VK_UP)) {
			if (--selectede < 0) {
				selectede = 2;
			}
        }
        if (keyPressed(KeyEvent.VK_DOWN)) {
			if (++selectede > 1) {
				selectede = 0;
			}
        }

        if (keyPressed(KeyEvent.VK_ENTER)) {

            switch (selectede) {
                case 0:
                    ((MomoyGame) parent).showFPS = !((MomoyGame) parent).showFPS;
                  break;
                case 1:
                    bsMusic.setActive(!bsMusic.isActive());
                    bsSound.setActive(!bsSound.isActive());

			if (bsMusic.isActive()) {
				
				bsMusic.play(bsMusic.getLastAudioFile());
						// equal to -> playMusic(bsMusic.getLastAudioFile());
			}
                 break;
                    
            }

        }

        //berpindah dari page 1 to page 2
        

        /*************************************************************************/
        //menganti kontrol pada game
        if (!gantiControl) {
		// quit
		if (keyPressed(KeyEvent.VK_ESCAPE)) {
			//saveController();
		}
                // select
		if (keyPressed(KeyEvent.VK_0)) {
                    switch (selected) {
			// return to main menu
			case 7:
			//playSound("sound/MenuSelect.wav");
			//saveController();
			finish();
			break;

			// change control
				default:
					//playSound("sounds/MenuSelect.wav");
					gantiControl = true;
					controlNum = selected;
					break;
				}
			}
                 }  else {
			// read key input
			int input = bsInput.getKeyPressed();

			if (input != bsInput.NO_KEY) {
				gantiControl = false;

				if (input != KeyEvent.VK_ESCAPE) {
					((MomoyGame) parent).controller[selected] = input;
				}
			}

		}
        
        /****************************************************************/
         if(keyPressed(KeyEvent.VK_SPACE)){
                             if(++pages > 1){
                                finish();
                        }
                   }
    }

    @Override
    public void render(Graphics2D g) {
        //super.render(g);
        g.fillRect(0, 0, getWidth(), getHeight());
        background1.render(g);

        
        switch(pages){
            case 1:
                font.drawString(g, "===== OPTION MENU =====", GameFont.CENTER, 0, 110, getWidth());

                String showFPS = (((MomoyGame) parent).showFPS) ? " ON " : " OFF";
                font.drawString(g, "Show FPS        :   " + showFPS, GameFont.CENTER,400, 150,(selected = 0));

                String sound = (bsMusic.isActive()) ? " ON " : " OFF";
                font.drawString(g, "Active Sounds   :   " + sound, GameFont.CENTER, 400, 200,(selected = 1));

//                String lvl = (((MomoyGame) parent).levelDesc[]) ;  // game.levelDesc[game.level];
//                font.drawString(g, "Level:"+lvl,          GameFont.CENTER, 0, 350, getWidth());
//                font.drawString(g, "PRESS TO CONTROLLER CHANGE", GameFont.CENTER, 0, 430, getWidth());
//                if (!blink) {
//                font.drawString(g,"Press SPACE TO NEXT PAGE", GameFont.CENTER,0,480,getWidth());
//                }
                if(!blinking){
                font.drawString(g,"tekan escape untuk keluar", GameFont.CENTER,0,550,getWidth());
                }

                int y = (selectede == 2) ? -50 : -200+(selectede*50);
                if (!blink) {
                    g.drawImage(arrow, 100, 160+(selectede*40), null);
                 }

                break;
            case 2:
//                // draw control
//                if (!blink) {
//		drawText(g, "GAME CONTROL", 2, false);
//                }
//
//
//		drawText(g, "------------", 3, false);
//                int[] controller = ((MomoyGame) parent).controller;
//                drawControl(g, "LEFT        : ", controller[0], 6, (selectede == 3));
//                drawControl(g, "RIGHT     : ", controller[1],7,(selectede == 4));
//                drawControl(g, "UP        : ", controller[2],8,(selectede == 5));
//                drawControl(g, "DOWN      : ",controller[3],9,(selectede == 6));
////                drawControl(g, "HAJAR     : ",controller[4],10,selectedIndex == 4);
//                drawText(g, "Return to Main Menu", 20, (selectedIndex == TOTAL_OPTION));
//
//
//
//                if (!blink) {
//                    g.drawImage(arrow, 100, 160+(selectede*25), null);
//                 }
//            break;


        }
    }

    
}

