/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golden.momoyfindpatch.menu;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.object.sprite.AdvanceSprite;
//import com.sun.java.swing.plaf.windows.TMSchema.Part;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.Vector;
import java.util.*;


/**
 *
 * @author cyber-blackhat
 */
public class Credit extends GameObject {

    PlayField        playfield;         // the game playfield
    Background       background;
    AdvanceSprite    design;
    SpriteGroup      ENEMY_GROUP;
    SpriteGroup      DESIGN_GROUP;
    Timer            moveTimer;

    private boolean blink;
    private Timer blinkTimer = new Timer(400);
    GameFont tulis;

    private int beginning, ending;



   // private int counter, cntMod;

    double scrollSpeed;
    Timer		fasterScrollTimer;	// timer to make the scrolling faster
    int			fasterTime;			// counter how many times

//    
    

/****************************************************************************************/
    private int state = 0;
    private int state2 = 0;
    private int counter = 0;
    private int counter1 = 0;
    private int countertext =0;
    private int countertext1 = 1;
    private int countertext2 = 2;
    private int countertext3 = 3;
    private int countertext4 = 4;
    private int countertext5 = 5;
    private BufferedImage judul;




    public Credit(GameEngine parent){
        super(parent);

       // scrollSpeed = 0.0125;

				// faster the scrolling every 12 secs
	//fasterScrollTimer = new Timer(12000);
        
    }

    @Override
    public void initResources() {
        // create the game playfield
        playfield = new PlayField();
        background = new ColorBackground(Color.yellow);


        tulis = fontManager.getFont(getImages("images/COPFont.png", 8, 12));
        
//        // associate the playfield with a background
        background = new ImageBackground(getImage("images/CreaditBackground.png"), 800, 600);
        playfield.setBackground(background);


       this.judul = getImage("images/cooltext443370249.png");
//
//        DESIGN_GROUP = playfield.addGroup(new SpriteGroup("judul"));
//        BufferedImage image2 = getImage("images/JUDUL.png");
//        int startX2 = 12, startY2 =  90;
//        for(int j=0;j<1;j++){
//            for(int i=0;i<1;i++){
//                Sprite jUdul = new Sprite(image2,startX2+(i*20),startY2+(j*20));
//                jUdul.setHorizontalSpeed(0.44);
//                DESIGN_GROUP.add(jUdul);
//            }
//        }
//
//
//
//        ENEMY_GROUP = playfield.addGroup(new SpriteGroup("Enemy"));
//        BufferedImage image = getImage("images/Title copy.png");
//        int startX = 20, startY = 40;     // starting coordinate
//        for (int j=0;j < 1;j++) {         // 4 rows
//            for (int i=0;i < 1;i++) {     // 7 sprites in a row
//                Sprite enemy = new Sprite(image, startX+(i*20), startY+(j*20));
//                enemy.setHorizontalSpeed(0.22);
//                ENEMY_GROUP.add(enemy);
//            }
//        }
//        moveTimer = new Timer(2000); // every 2 secs the enemies reverse its speed




    }

    @Override
    public void update(long elapsedTime) {
       playfield.update(elapsedTime);
     

    if (blinkTimer.action(elapsedTime)) {
            blink = !blink;
        }
       
    if (this.state == 0) {
        this.counter += 1;
      if (this.counter == 350) {
        this.state += 1;
      }
    }
    if (this.state == 1) {
      this.counter1 += 1;
      if (this.counter1 == 60) {
        this.state += 1;
      }
    }


   //mengatur gerakan dari gambar jalan
    if (this.state == 0) {
        this.counter += 1;
      if (this.counter == 120) {
        this.state += 1;
      }
    }
    if (this.state == 1) {
      this.countertext1 += 1;
      if (this.countertext1 == 60) {
        this.state += 1;
      }
    }
    if(this.state == 1){
        this.countertext2 += 1;
        if (this.countertext2 == 60){
           this.state += 1;
        }
    }
    if (this.state == 1){
        this.countertext3 += 1;
        if(this.countertext3 == 60){
            this.state += 1;
        }
    }
    if(this.state == 1){
        this.countertext4 += 1;
        if(this.countertext4 == 60){
            this.state += 1;
        }
    }
    if(this.state == 1){
        this.countertext5 += 1;
        if(this.countertext5 == 60){
            this.state += 1;
        }
    }

    if (keyPressed(KeyEvent.VK_ENTER) || keyPressed(KeyEvent.VK_ESCAPE)) {
	//playSound("sounds/MenuSelect.wav");
	finish();
    }
}

    

    @Override
    public void render(Graphics2D g) {
         playfield.render(g);

            g.drawImage(judul, -400 + this.counter * 4, -400 + this.counter1 * 8, null);
            tulis.drawString(g, "design & programming", GameFont.CENTER, 10, -250 + this.countertext1 *8,getWidth());
            tulis.drawString(g, "yaniar prayogo", 	GameFont.CENTER, 20, -200 + this.countertext2 *8, getWidth());
            tulis.drawString(g, "game created under", GameFont.CENTER, 30,-150 + this.countertext3 *8,getWidth());
            tulis.drawString(g, "",GameFont.CENTER,40, -100 + this.countertext4 *8,getWidth());
            tulis.drawString(g, "Golden T Engine (GTGE) Framework",GameFont.CENTER, 50, -50 + this.countertext5 *7,getWidth());
            tulis.drawString(g, "graphic for:", GameFont.CENTER, 30,-20 + this.countertext3 *8,getWidth());
            tulis.drawString(g, "www.reinertileset.4players.de", GameFont.CENTER, 30,-5 + this.countertext3 *8,getWidth());
            if (!blink) {
            tulis.drawString(g, "Press Enter", GameFont.CENTER, 0, 500, getWidth());
            }

    }

    
}
   