/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golden.momoyfindpatch.menu;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.object.sprite.AdvanceSprite;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author cyber-blackhat
 */
public class TutorialGame extends GameObject {

    
	private AdvanceSprite momoy_left,momoy_right,momoy_up,momoy_down;
        private AdvanceSprite enemy_1_left,enemy_1_right,enemy_1_up,enemy_1_down;
        private AdvanceSprite enemy_2_left,enemy_2_right,enemy_2_up,enemy_2_down;
	private AdvanceSprite enemy_3_left,enemy_3_right,enemy_3_up,enemy_3_down;
	private AdvanceSprite enemy_4_left,enemy_4_right,enemy_4_up,enemy_4_down;
	private AdvanceSprite enemy_5_left,enemy_5_right,enemy_5_up,enemy_5_down;

        private Sprite left,right,up,down,ctrl,alt,shift,space,esc,controlcouple,controlcouple2;
	
	
//	private BufferedImage door;
//	private BufferedImage key;

        private Sprite jalancerita;
	private Sprite jeniskarakter;
	private Sprite tomboldigunakan;

	private BufferedImage titleImage;
        private GameFont font;

        //membuat agar sprite dapat ditaruh pada background
        private PlayField   playfield;
        private Background  background;
        private SpriteGroup Momoy;
        private SpriteGroup Enemy;

        private int page = 1;

        private boolean blink;
        private Timer blinkTimer = new Timer(400);
        public GameFont titlefont2;
    
    public TutorialGame(GameEngine parent){
        super(parent);
    }
    
    @Override
    public void initResources() {

        titlefont2 = fontManager.getFont(getImage("images/BitmapFont.png"));
            // create the game playfield
             //playfield = new PlayField();
        
           titleImage = getImage("images/Title copy.png");
	   //key = getImages("images/");

           background = new ImageBackground(getImage("images/CreaditBackground.png"), 800, 600);
            //playfield.setBackground(background);
        
            //       background = new ImageBackground(getImage("images/CreaditBackground.png"), 800, 600);
            //       playfield.setBackground(background);
	   
	   font = fontManager.getFont(getImages("images/Font.png",16, 6));
           // font = fontManager.getFont(getImage("images/BitmapFont.png"));

           left = new Sprite(getImage("IMG_Control/kiri.png"));
           right = new Sprite(getImage("IMG_Control/kanan.png"));
           up = new Sprite(getImage("IMG_Control/up.png"));
           down = new Sprite(getImage("IMG_Control/down.png"));
           shift = new Sprite(getImage("IMG_Control/shift.png"));
           ctrl = new Sprite(getImage("IMG_Control/ctrl.png"));
           esc = new Sprite(getImage("IMG_Control/esc.png"));
           space = new Sprite(getImage("IMG_Control/space.png"));
           alt = new Sprite(getImage("IMG_Control/alt.png"));
           controlcouple = new Sprite(getImage("images/controlcouple.png"));
           controlcouple2 = new Sprite(getImage("images/controlcouple2.png"));


           jalancerita = new Sprite(getImage("IMG_Control/JalanCerita.png"));
           jeniskarakter = new Sprite(getImage("IMG_Control/jeniskarakter.png"));
           tomboldigunakan = new Sprite(getImage("IMG_Control/tomboldigunakan.png"));

           //image dari momoy character.............
           
	   momoy_left = new AdvanceSprite(getImages("image_karakter/MOMOY_JOIN.png",9,4));
	   momoy_left.setAnimationFrame(new int[] {27,28,29,30,31,32,33,34,35});
           momoy_left.getAnimationTimer().setDelay(160);
           momoy_left.setAnimate(true);
           momoy_left.setLoopAnim(true);
           momoy_left.setBackground(background);

           momoy_right = new AdvanceSprite(getImages("image_karakter/MOMOY_JOIN.png",9,4));
           momoy_right.setAnimationFrame(new int[] {0,1,2,3,4,5,6,7,8});
           momoy_right.getAnimationTimer().setDelay(160);
           momoy_right.setAnimate(true);
           momoy_right.setLoopAnim(true);
           momoy_right.setBackground(background);

           momoy_up = new AdvanceSprite(getImages("image_karakter/MOMOY_JOIN.png",9,4));
           momoy_up.setAnimationFrame(new int[] {9,10,11,12,13,14,15,16,17});
           momoy_up.getAnimationTimer().setDelay(160);
           momoy_up.setAnimate(true);
           momoy_up.setLoopAnim(true);
           momoy_up.setBackground(background);

           momoy_down = new AdvanceSprite(getImages("image_karakter/MOMOY_JOIN.png",9,4));
           momoy_down.setAnimationFrame(new int[] {18,19,20,21,22,23,24,25,26});
           momoy_down.getAnimationTimer().setDelay(160);
           momoy_down.setAnimate(true);
           momoy_down.setLoopAnim(true);
           momoy_down.setBackground(background);

           //enemy level_1
           enemy_1_left =  new AdvanceSprite(getImages("image_karakter/enemy_level_1.png",8,9));
           enemy_1_left.setAnimationFrame(new int[] {63,64,65,66,67,68,69,70,71});
           enemy_1_left.getAnimationTimer().setDelay(160);
           enemy_1_left.setAnimate(true);
           enemy_1_left.setLoopAnim(true);
           enemy_1_left.setBackground(background);

           enemy_1_right =  new AdvanceSprite(getImages("image_karakter/enemy_level_1.png",8,9));
           enemy_1_right.setAnimationFrame(new int[] {36,37,38,39,40,41,42,43,44});
           enemy_1_right.getAnimationTimer().setDelay(160);
           enemy_1_right.setAnimate(true);
           enemy_1_right.setLoopAnim(true);
           enemy_1_right.setBackground(background);

           enemy_1_up =  new AdvanceSprite(getImages("image_karakter/enemy_level_1.png",8,9));
           enemy_1_up.setAnimationFrame(new int[] {9,10,11,12,13,14,15,16,17});
           enemy_1_up.getAnimationTimer().setDelay(160);
           enemy_1_up.setAnimate(true);
           enemy_1_up.setLoopAnim(true);
           enemy_1_up.setBackground(background);

           enemy_1_down =  new AdvanceSprite(getImages("image_karakter/enemy_level_1.png",8,9));
           enemy_1_down.setAnimationFrame(new int[] {18,19,20,21,22,23,24,25,26});
           enemy_1_down.getAnimationTimer().setDelay(160);
           enemy_1_down.setAnimate(true);
           enemy_1_down.setLoopAnim(true);
           enemy_1_down.setBackground(background);

           //enemy level_1
           enemy_2_left =  new AdvanceSprite(getImages("image_karakter/enemy_level_2.png",8,14));
           enemy_2_left.setAnimationFrame(new int[] {100,101,102,103,104,105,106,107});
           enemy_2_left.getAnimationTimer().setDelay(160);
           enemy_2_left.setAnimate(true);
           enemy_2_left.setLoopAnim(true);
           enemy_2_left.setBackground(background);

           enemy_2_right =  new AdvanceSprite(getImages("image_karakter/enemy_level_2.png",8,17));
           enemy_2_right.setAnimationFrame(new int[] {36,37,38,39,40,41,42,43,44});
           enemy_2_right.getAnimationTimer().setDelay(160);
           enemy_2_right.setAnimate(true);
           enemy_2_right.setLoopAnim(true);
           enemy_2_right.setBackground(background);

           enemy_2_up =  new AdvanceSprite(getImages("image_karakter/enemy_level_2.png",8,17));
           enemy_2_up.setAnimationFrame(new int[] {76,77,78,79,80,81,82,83});
           enemy_2_up.getAnimationTimer().setDelay(160);
           enemy_2_up.setAnimate(true);
           enemy_2_up.setLoopAnim(true);
           enemy_2_up.setBackground(background);

           enemy_2_down =  new AdvanceSprite(getImages("image_karakter/enemy_level_2.png",8,17));
           enemy_2_down.setAnimationFrame(new int[] {92,93,94,95,96,97,98,99});
           enemy_2_down.getAnimationTimer().setDelay(160);
           enemy_2_down.setAnimate(true);
           enemy_2_down.setLoopAnim(true);
           enemy_2_down.setBackground(background);

//           enemy level_3
           enemy_3_left =  new AdvanceSprite(getImages("image_karakter/enemy_level_5.png",8,17));
           enemy_3_left.setAnimationFrame(new int[] {100,101,102,103,104,105,106,107});
           enemy_3_left.getAnimationTimer().setDelay(160);
           enemy_3_left.setAnimate(true);
           enemy_3_left.setLoopAnim(true);
           enemy_3_left.setBackground(background);
//
           enemy_3_right =  new AdvanceSprite(getImages("image_karakter/enemy_level_5.png",8,17));
           enemy_3_right.setAnimationFrame(new int[] {36,37,38,39,40,41,42,43,44});
           enemy_3_right.getAnimationTimer().setDelay(160);
           enemy_3_right.setAnimate(true);
           enemy_3_right.setLoopAnim(true);
           enemy_3_right.setBackground(background);

//
           enemy_3_up =  new AdvanceSprite(getImages("image_karakter/enemy_level_5.png",8,14));
           enemy_3_up.setAnimationFrame(new int[] {76,77,78,79,80,81,82,83});
           enemy_3_up.getAnimationTimer().setDelay(160);
           enemy_3_up.setAnimate(true);
           enemy_3_up.setLoopAnim(true);
           enemy_3_up.setBackground(background);
//
           enemy_3_down =  new AdvanceSprite(getImages("image_karakter/enemy_level_5.png",8,14));
           enemy_3_down.setAnimationFrame(new int[] {92,93,94,95,96,97,98,99});
           enemy_3_down.getAnimationTimer().setDelay(160);
           enemy_3_down.setAnimate(true);
           enemy_3_down.setLoopAnim(true);
           enemy_3_down.setBackground(background);

            //enemy level_4
            enemy_4_left =  new AdvanceSprite(getImages("img_enemy/enemy4.png",8,4));
            enemy_4_left.setAnimationFrame(new int[] {0,1,2,3,4,5,6,7});
            enemy_4_left.getAnimationTimer().setDelay(160);
            enemy_4_left.setAnimate(true);
            enemy_4_left.setLoopAnim(true);
            enemy_4_left.setBackground(background);
//
           enemy_4_right =  new AdvanceSprite(getImages("img_enemy/enemy4.png",8,4));
           enemy_4_right.setAnimationFrame(new int[] {24,25,26,27,28,29,30,31});
           enemy_4_right.getAnimationTimer().setDelay(160);
           enemy_4_right.setAnimate(true);
           enemy_4_right.setLoopAnim(true);
           enemy_4_right.setBackground(background);
//
           enemy_4_up =  new AdvanceSprite(getImages("img_enemy/enemy4.png",8,4));
           enemy_4_up.setAnimationFrame(new int[] {8,9,10,11,12,13,14,15});
           enemy_4_up.getAnimationTimer().setDelay(160);
           enemy_4_up.setAnimate(true);
           enemy_4_up.setLoopAnim(true);
           enemy_4_up.setBackground(background);
//
           enemy_4_down =  new AdvanceSprite(getImages("img_enemy/enemy4.png",8,4));
           enemy_4_down.setAnimationFrame(new int[] {16,17,18,19,20,21,22,23});
           enemy_4_down.getAnimationTimer().setDelay(160);
           enemy_4_down.setAnimate(true);
           enemy_4_down.setLoopAnim(true);
           enemy_4_down.setBackground(background);
//
//            //enemy level_3
            enemy_5_left =  new AdvanceSprite(getImages("img_enemy/level5.png",8,4));
            enemy_5_left.setAnimationFrame(new int[] {0,1,2,3,4,5,6,7});
            enemy_5_left.getAnimationTimer().setDelay(160);
            enemy_5_left.setAnimate(true);
            enemy_5_left.setLoopAnim(true);
            enemy_5_left.setBackground(background);
//
           enemy_5_right =  new AdvanceSprite(getImages("img_enemy/level5.png",8,4));
           enemy_5_right.setAnimationFrame(new int[] {24,25,26,27,28,29,30,31});
           enemy_5_right.getAnimationTimer().setDelay(160);
           enemy_5_right.setAnimate(true);
           enemy_5_right.setLoopAnim(true);
           enemy_5_right.setBackground(background);
//
           enemy_5_up =  new AdvanceSprite(getImages("img_enemy/level5.png",8,4));
           enemy_5_up.setAnimationFrame(new int[] {8,9,10,11,12,13,14,15});
           enemy_5_up.getAnimationTimer().setDelay(160);
           enemy_5_up.setAnimate(true);
           enemy_5_up.setLoopAnim(true);
           enemy_5_up.setBackground(background);
//
           enemy_5_down =  new AdvanceSprite(getImages("img_enemy/level5.png",8,4));
           enemy_5_down.setAnimationFrame(new int[] {16,17,18,19,20,21,22,23});
           enemy_5_down.getAnimationTimer().setDelay(160);
           enemy_5_down.setAnimate(true);
           enemy_5_down.setLoopAnim(true);
           enemy_5_down.setBackground(background);;

           
    }

    @Override
    public void update(long elapsedTime) {
        background.update(elapsedTime);

        momoy_right.update(elapsedTime);
        momoy_left.update(elapsedTime);
        momoy_up.update(elapsedTime);
        momoy_down.update(elapsedTime);

        enemy_1_left.update(elapsedTime);
        enemy_1_right.update(elapsedTime);
        enemy_1_up.update(elapsedTime);
        enemy_1_down.update(elapsedTime);

        enemy_2_left.update(elapsedTime);
        enemy_2_right.update(elapsedTime);
        enemy_2_up.update(elapsedTime);
        enemy_2_down.update(elapsedTime);

        enemy_3_left.update(elapsedTime);
        enemy_3_right.update(elapsedTime);
        enemy_3_up.update(elapsedTime);
        enemy_3_down.update(elapsedTime);

        enemy_4_left.update(elapsedTime);
        enemy_4_right.update(elapsedTime);
        enemy_4_up.update(elapsedTime);
        enemy_4_down.update(elapsedTime);
//
        enemy_5_left.update(elapsedTime);
        enemy_5_right.update(elapsedTime);
        enemy_5_up.update(elapsedTime);
        enemy_5_down.update(elapsedTime);

        if(keyPressed(KeyEvent.VK_ESCAPE)){
            finish();
        }
        if(keyPressed(KeyEvent.VK_RIGHT)){
            if(++page > 4){
                finish();
            }
        }
        if(page >= 2){
        if(keyPressed(KeyEvent.VK_LEFT)){
            if(--page > 4){
                }
            }
        }
        if (blinkTimer.action(elapsedTime)) {
            blink = !blink;
        }

    }

    @Override
    public void render(Graphics2D g) {
       // g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(titleImage, 10, 10, null);
//        playfield.render(g);
        background.render(g);


        switch(page){
            case 1:
            jalancerita.render(g, 70, 60);
            font.drawString(g, "Momoy adalah seorang pangeran", 70, 150);
            font.drawString(g, "dari negeri antah berantah", 100, 180);
            font.drawString(g, "yang tersesat dalam sebuah", 70, 210);
            font.drawString(g, "labirin", 200, 240);
            font.drawString(g, "dan dia mencoba untuk keluar", 100, 270);
            font.drawString(g, "dari negeri antah berantah  ", 100, 300);
            font.drawString(g, "dari labirin yang penuh ", 80, 330);
            font.drawString(g, "dengan zombie dan lain-lain", 90, 360);
            if(!blink){
            font.drawString(g, "   [>>]", GameFont.CENTER, 0, 430, getWidth());
            }
            break;
            case 2:
            jeniskarakter.render(g, 70, 60);
            font.drawString(g, "momoy character :", 110, 150);
            momoy_right.render(g, 480, 130);
            momoy_up.render(g, 530, 130);
            momoy_down.render(g, 580, 130);
            momoy_left.render(g, 630, 130);

            font.drawString(g, "Enemy level 1 : ", 110, 250);
            enemy_1_left.render(g, 480, 230);
            enemy_1_right.render(g,530,230);
            enemy_1_up.render(g, 580, 230);
            enemy_1_down.render(g,630,230);

            font.drawString(g, "Enemy Level 2 : ", 110, 330);
            enemy_2_left.render(g,480,300);
            enemy_2_up.render(g, 530, 300);
            enemy_2_right.render(g, 580, 300);
            enemy_2_down.render(g, 630, 300);
            if(!blink){
            font.drawString(g, "[<<] [>>]", GameFont.CENTER, 0, 430, getWidth());
            }
        break;
            case 3:
            jeniskarakter.render(g, 70, 60);
            font.drawString(g, "Enemy level 3 : ", 110, 150);
            enemy_3_left.render(g, 480, 130);
            enemy_3_up.render(g, 530, 130);
            enemy_3_right.render(g,580, 130);
            enemy_3_down.render(g, 630, 130);
//
            font.drawString(g, "Enemy level 4 : ", 110, 250);
            enemy_4_left.render(g,480,230);
            enemy_4_up.render(g, 530, 230);
            enemy_4_right.render(g,580, 230);
            enemy_4_down.render(g, 630, 230);
//
//
            font.drawString(g, "Enemy level 5 : ", 110, 350);
            enemy_5_left.render(g,480,330);
            enemy_5_up.render(g, 530, 330);
            enemy_5_right.render(g,580, 330);
            enemy_5_down.render(g, 630, 330);
            if(!blink){
            font.drawString(g, "[<<] [>>]", GameFont.CENTER, 0, 500, getWidth());
            }

        break;
            case 4:
                tomboldigunakan.render(g, 70, 60);
                //font.drawString(g, "TOMBOL-TOMBOL YANG DIGUNAKAN", GameFont.CENTER,0,80,getWidth());
                left.render(g, 100, 150);
                font.drawString(g, "KIRI", 180, 150);
                right.render(g, 100,200);
                font.drawString(g, "KANAN", 180, 200);
                up.render(g, 100, 250);
                font.drawString(g, "ATAS", 180, 250);
                down.render(g,100,300);
                font.drawString(g,"BAWAH", 180, 300);

                controlcouple.render(g, 320, 150);
                font.drawString(g, "+", 420, 150);
                ctrl.render(g, 460, 140);
                font.drawString(g,"=",520,150);
                font.drawString(g, "LARI ", 550, 150);

                controlcouple2.render(g, 320, 200);
                font.drawString(g, "+", 420, 200);
                alt.render(g, 460, 200);
                font.drawString(g,"=",520,200);
                font.drawString(g, "BALL ", 550, 200);
                font.drawString(g,"MISSILE",550, 250);

                controlcouple2.render(g, 320, 300);
                font.drawString(g, "+", 420, 300);
                space.render(g, 460, 300);
                font.drawString(g,"=",520,300);
                font.drawString(g, "HALILIN-", 550, 300);
                font.drawString(g,"TAR",550,350);

                controlcouple2.render(g, 320, 300);
//                shift.render(g, 420, 300);
//                font.drawString(g, "Ball laser", 480, 300);
//                if(!blink){
//                font.drawString(g, "[<<] [>>]", GameFont.CENTER, 0, 500, getWidth());
//                }
                break;

            

        }
    }

}
