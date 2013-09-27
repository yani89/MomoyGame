/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golden.momoyfindpatch.menu;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.momoyfindpatch.HiScoreDat;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *
 * @author cyber-blackhat
 */
public class ScoreLast extends GameObject {

    public static final int score = 0;
    public static final int insert_score =1;

    int gameState = score;

    int	insertScore = 0;

    BufferedImage	titleImage;
    GameFont		font;
    private GameFont font2;

    HiScoreDat[]           hiscore;
    HiScoreDat		addscore;
    boolean			newHiScore;
    Timer			blinkTimer = new Timer(300);
    boolean			blink;

    private Background back;



    public ScoreLast(GameEngine parent){
        super(parent);
    }

    
    @Override
    public void initResources() {
        font = fontManager.getFont(getImages("images/COPFont.png", 8, 12));
        font2 = fontManager.getFont(getImage("images/BitmapFontSelected.png"));
        back = new ImageBackground(getImage("images/backdrop_ihs.png"), 816, 624);


        //logika untuk menambahkan jika score tertinggi dari 9 urutan
        hiscore = new HiScoreDat[9];
		for (int i=0;i < hiscore.length;i++) {
			hiscore[i] = new HiScoreDat();
	}

        // loading file
	try {
            File f = bsIO.getFile("hiscore.dat", bsIO.WORKING_DIRECTORY);
		DataInputStream din = new DataInputStream(new FileInputStream(f));
			for (int i=0;i < hiscore.length;i++) {
				hiscore[i] = new HiScoreDat(din.readUTF(), din.readUTF(),din.readUTF());
			}
	} catch (Exception e) {
		// error occured when loading hi-score file
		// either by corrupted file or file has not been created
		//saveHiScore();
	}

        if (addscore != null) {
			newHiScore = false;
			for (int i=0;i < hiscore.length;i++) {
				if (Integer.parseInt(addscore.score) > Integer.parseInt(hiscore[i].score)) {
					// new high-score
					for (int j=hiscore.length-1;j > i;j--) {
						hiscore[j] = hiscore[j-1];
					}
					hiscore[i] = addscore;
					newHiScore = true;
					gameState = insert_score;
					break;
				}
			}

			if (newHiScore == false) {
				// failed to achieve high score
				// listed at the bottom
				addscore.name = "you";
			}
		}
        
    }
    private void saveHiScore() {
		try {
			File f = bsIO.setFile("hiscore.dat", bsIO.WORKING_DIRECTORY);
			DataOutputStream dout = new DataOutputStream(new FileOutputStream(f));
			for (int i=0;i < hiscore.length;i++) {
				dout.writeUTF(hiscore[i].score);
				dout.writeUTF(hiscore[i].name);

				dout.writeUTF(hiscore[i].level);
			}
		} catch (Exception e) {
		}
	}
    public void insertScore(int score, int level) {
		addscore = new HiScoreDat(score, level);
	}
    

    @Override
    public void update(long elapsedTime) {
        if (blinkTimer.action(elapsedTime)) {
                blink = !blink;
                }
        
        switch (gameState) {
			case score:
				if (keyPressed(KeyEvent.VK_ESCAPE) || keyPressed(KeyEvent.VK_ENTER)) {
                                        
					finish();
                                        //parent.nextGameID = parent.nextGameID()''
				}
			break;

			case insert_score:
				if (blinkTimer.action(elapsedTime)) {
					blink = !blink;
				}

				int keyCode = bsInput.getKeyPressed();
				switch (keyCode) {
					case KeyEvent.VK_BACK_SPACE:
						addscore.name = addscore.name.substring(0, addscore.name.length()-1);
					break;

					case KeyEvent.VK_ESCAPE:
					case KeyEvent.VK_ENTER:
						saveHiScore();
						finish();
					break;

					default:
						String st = getKeyText(keyCode);
						if (st != null && addscore.name.length() < 8) {
							addscore.name += st;
						}
					break;
				}

			break;
		}

    }

    private String getKeyText(int keyCode) {
		String st = null;

		switch (keyCode) {
			case KeyEvent.VK_SPACE: st = " "; break;
			case KeyEvent.VK_MINUS: st = "-"; break;
			case KeyEvent.VK_BACK_QUOTE: st = "`"; break;
			case KeyEvent.VK_QUOTE: st = "'"; break;
			default:
				st = KeyEvent.getKeyText(keyCode).toUpperCase();
				if (st.startsWith("NUMPAD")) {
					st = st.substring(7);
				}
				if (st.length() == 0 || st.length() > 1) {
					// invalid key
					return null;
				}
		}

		if (bsInput.isKeyDown(KeyEvent.VK_SHIFT)) {
			switch (keyCode) {
				case KeyEvent.VK_1: st = "!"; break;
				case KeyEvent.VK_2: st = "@"; break;
				case KeyEvent.VK_3: st = "#"; break;
				case KeyEvent.VK_4: st = "$"; break;
				case KeyEvent.VK_5: st = "%"; break;
				case KeyEvent.VK_6: st = "^"; break;
				case KeyEvent.VK_7: st = "&"; break;
				case KeyEvent.VK_8: st = "*"; break;
				case KeyEvent.VK_9: st = "("; break;
				case KeyEvent.VK_0: st = ")"; break;
			}
		}

		return st;
	}


    @Override
    public void render(Graphics2D g) {
                g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
                back.render(g);
		//g.drawImage(titleImage, 10, 10, null);

		for (int i=0;i < hiscore.length;i++) {
			printHiScore(g, i, hiscore[i]);

			if (gameState == insert_score && hiscore[i] == addscore) {
				if (!blink && addscore.name.length() < 3) {
					font.drawString(g, "_", 260+(addscore.name.length()*22), 115+(i*36));
				}
			}
		}

		if (addscore != null && newHiScore == false) {
			printHiScore(g, 9, addscore);
		}
                if (!blink) {
                    font.drawString(g, "TEKAN ESC TO MAIN MENU", GameFont.CENTER, 20,150,getWidth());
                }
    }

    	private void printHiScore(Graphics2D g, int num, HiScoreDat data) {
		int y = 220+(num*36);
		num++;
		if (num < 10) {
			font.drawString(g, " "+num, 50, y);
		} else {
			font.drawString(g, String.valueOf(num), 30, y);
		}

		if (data.score.equals("-1")) {
			font.drawString(g, "<Kosong>", 100, y);

		} else {
			font.drawString(g, data.score, 170, y);
			font.drawString(g, data.name,  350, y);
			//font.drawString(g, data.stage, 350, y);
			font.drawString(g, data.level, 590, y);
		}
	}

   
}
