/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golden.momoyfindpatch;
// GTGE
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.GameLoader;
//memasukkan main menu ke game engine
import com.golden.gamedev.funbox.GameSettings;
import com.golden.gamedev.funbox.KeyCapture;
import com.golden.momoyfindpatch.menu.*;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
/**
 *
 * @author cyber-blackhat
 */
public class MomoyGame extends GameEngine {
    public static final int GAME_INTRO = 0;
    public static final int MainMenu = 1;
    public static final int Momoy_Game = 2;
    public static final int TRANSITION = 3;
    public static final int LOADING = 4;

    public int gameState = LOADING;

    private boolean first=true, countdown=true;

    
    public int[]	controller;

    public int                  level  = 1;
    public final int            totallevel = 5;
    public BufferedImage[]	levelScreenShot;
    public String[] levelDesc = new String[] { "Easy", "Normal", "Hard" };

    public boolean	showFPS = false;
    KeyCapture	keyMaxFPS;


    public MomoyGame() {
         distribute = true; 
    }

    @Override
    public void initResources() {

		getImages("images/dragon_flying.png", 8, 8);
                //getImages("images/font.png", 16, 6);
                getImages("image_karakter/MOMOY_JOIN.png",9,4);


                keyMaxFPS = new KeyCapture(bsInput, "HYPERSPEED", 1000) { // 1000ms = 1 second
			public void keyCaptured() {
				setFPS((getFPS() != 3000) ? 3000 : 50);
			}
		};
	}

    @Override
    public void update(long elapsedTime) {
		keyMaxFPS.update(elapsedTime);
	}

    @Override
	public void render(Graphics2D g) {
		if (showFPS) {
			drawFPS(g, 20, 580);
		}
	}

    @Override
    public GameObject getGame(int GameID) {
       switch (GameID) {
            
    case 0:
      return new YpIntro(this);
    case 1:
      return new Main_Menu(this);
    case 2:
      return new Momoy(this);
		}
	return null;
    }

    public static void main(String[] args) {

//        GameLoader gameLoader=new GameLoader();
//        gameLoader.setup(new MomoyGame(), new Dimension(800,600), true);
//        gameLoader.start();


	GameSettings settings = new GameSettings(MomoyGame.class.getResource("images/textonsetting.png")) {
	public void  start() {
		GameLoader game = new GameLoader();
		game.setup(new MomoyGame(), new Dimension(800,600),
                    fullscreen.isSelected(),//);
                    //fullscreen.setToolTipText("Untuk mengeset resolusi penuh pada monitor anda");
                    bufferstrategy.isSelected());
                      
                game.start();
			}
            @Override
                        protected JPanel initSettings() {
				JPanel pane = super.initSettings();
                                ///pane.remove(bufferstrategy);
				pane.remove(sound);
                                
				return pane;
			}
                };
               
     
    }
  //  { distribute = true; }

}
//
//JCheckBox opengl;
//
//    protected JPanel initSettings() {
//       JPanel pane = super.initSettings();
//
//       // remove bufferstrategy option
//       pane.remove(bufferstrategy);
//
//       // add opengl option
//       opengl = new JCheckBox("OpenGL", true);
//       pane.add(opengl, 0);
//    }
// };

