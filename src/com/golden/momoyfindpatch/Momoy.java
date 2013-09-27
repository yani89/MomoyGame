/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golden.momoyfindpatch;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.*;
import com.golden.gamedev.object.collision.*;
import com.golden.gamedev.object.sprite.PatternSprite;
import com.golden.gamedev.object.sprite.VolatileSprite;
import com.golden.gamedev.util.*;
import com.golden.gamedev.util.ImageUtil;
import com.golden.momoyfindpatch.bonuscollision.*;
import com.golden.momoyfindpatch.enemycollision.*;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.StringTokenizer;
import com.golden.momoyfindpatch.logictype.*;
import com.golden.momoyfindpatch.menu.ScoreLast;
import com.golden.momoyfindpatch.playercollision.*;
import com.golden.momoyfindpatch.properticollision.*;
import com.golden.momoyfindpatch.menu.OptionMenu;



/**
 *
 * @author cyber-blackhat
 */
public class Momoy extends GameObject {

    /***************************** PLAY FIELD ***********************************/

        
	public PlayField 	playfield;
        public Background	background;
        public SpriteGroup     MOMOY,
                        TILE,
                        DINDINGSCORE,
                        GOAL,
                        PATTERN,
                        LANTAI,
                        SUNGAI,
                        LIFE,
                        BONUS,//aplle
                        BONUS1,//bintang
                        BONUS2,//gunting
                        BONUS3,//apple
                        POWERUP1,//MEAT POWER
                        HALILINTARPOWER,//HALILINTAR POWER 
                        ENEMY_GROUP,
                        ENEMY_GROUP2,
                        PAPAN,
                        PENJEPIT,
                        PAPANPATTERN,
                        HALILINTAR,
                        JEMBATAN,
                        KEKUATANBAWAAN,
                        PLAYER_MISSLE_GROUP,
                        Terakahir
                        ;


     /*************************** LEVEL VARIABLES ********************************/

	public int level;
        public Timer		levelTimer;			// timer how long the level title shown
	public Sprite		levelImage;			// level title and description
        double		scrollSpeed;		// background scroll speed
	int		scrollTimes;

    /**************************** GAME STATUS *************************************/
         //private boolean checkout; //inisialisasi saat keluar
         private int	gamestatus; //status game
         public static final int INTRO = 0; //show level description
         public static final int PLAYING =1; //mulai bermain
         public static final int GO_NEXT=2; //pergi ke next level
         public static final int GO_DEAD=3; //kalah
         public static final int GO_WINNER=4; //menang
         public static final int SHOWTITLE=5; //menampilkan title.

    /*************             OTHER PROPERTI   **********************************/
         public GameFont titlefont;
         public GameFont titlefont2;
         public GameFont font;
         private String loseTitle;
         public int time;
         private Timer	timerTime = new Timer(1000);
         public int score  = 0;
         public int life;
         public boolean checkout;
         public int jumlahapple;
         public boolean jumlahappletermakan;
         public int jumlahbintang;
         public boolean spark = true; //for sparkl.png
         private int rotibesar; //keluar jika posisi score == 2000;
         private boolean viewrotibesar; //set true or false        
         private Timer	timerNext	= new Timer(2000);
         private boolean blink;
         private Timer blinkTimer = new Timer(400);
         //misile meledak warna ungu
         public int boomtime;
         public Timer boom = new Timer(100);
         public boolean meledak;
         //private boolean notready;
         public Timer   fireTimer;
         private BufferedImage[] player_image;
         private BufferedImage[] lifeimage;
         private BufferedImage[] sungai;
         MomoySprite momoy;
         public MomoySprite player;
         public MomoyGame game;
    /*******************************************************************************
                                Enemy init
     *******************************************************************************/
         public Musuh musuhe;
         public boolean buka = false;
         public boolean buka2 = false;
    /*
     * BASICCOLLISION FOR BONUS
     */
         public BasicCollisionGroup playerwithgunting;
         //setting kekuatan ball fire
         public boolean powerUP;
         private Timer	powertime = new Timer(500);
         public int powertime2 = 50;
         //setting kekuatan halilintar fire
         public boolean halilintarUP;
         private Timer Lari  = new Timer(1000);
         public int Laris = 0;
         public boolean LariOnStop;
         //setting lama waktu berlari

         Sprite ESC,LG,APPLE,BINTANG,TA;

         public int enemylife;
         public boolean enemynyawa;
         public Timer enemylif = new Timer(1000);


         public Timer guntingaction = new Timer(1000);
         

         public BufferedImage[] playerMissle2;

    public Momoy (MomoyGame parent){
        super(parent);
        game = parent;

        playMusic("music/Music9.mid");
        
    }
    public void showHiScore() {
		ScoreLast hiscoredat = new ScoreLast(parent);
		hiscoredat.insertScore(score,level);
		hiscoredat.start();
		// back to main menu
		parent.nextGameID = MomoyGame.MainMenu;
		finish();
	}


    @Override
    public void initResources() {
        titlefont = fontManager.getFont(getImage("images/BitmapFontSelected.png"));
        titlefont2 = fontManager.getFont(getImage("images/BitmapFont.png"));
        font =fontManager.getFont(getImages("images/font2.png", 16, 6),
					 		" !\"#$%&'()*+,-./" +
							"0123456789:;<=>?" +
							"@ABCDEFGHIJKLMNO" +
							"PQRSTUVWXYZ['\\]^" +
							"_abcdefghijklmno" +
							"pqrstuvwxyz{|}~");
        player_image = getImages("image_karakter/walking.png",8,13);
        lifeimage = getImages("IMG_BONUS/apple.png",1,1);

        BufferedImage esc = getImage("images/OptionEsc.jpg");
        ESC = new Sprite(esc);

        //LG = LIFE GAMBAR
        BufferedImage lg = getImage("IMG_BONUS/fav.png");
        LG = new Sprite(lg);
        //APLLE GAMBAR
        BufferedImage AG = getImage("IMG_BONUS/apple.png");
        APPLE = new Sprite(AG);

        //DOOR AKHIR
        BufferedImage DOOR = getImage("IMG_BONUS/America Online.png");
        TA = new Sprite(DOOR);

        //BINTANG GAMBAR
        BufferedImage BINTANG2 = getImage("IMG_BONUS/Favourites.png");
        BINTANG = new Sprite(BINTANG2);
        
        // show the level title for 2.5 seconds
	levelTimer = new Timer(2500);
	levelImage = new Sprite();
	levelImage.setHorizontalSpeed(1.25);
        //INISIALISASI LEVEL PERTAMA
        level = 1;
	initLevel();
        //properti set
        life = 3;
        jumlahapple = 0;
        jumlahappletermakan = false;
        jumlahbintang = 0;
        powerUP = false;
        halilintarUP = false;
        LariOnStop = false;
        //spark = false;
       //notready = false;
        fireTimer = new Timer(1000);
        //misile meledak warna pink
        meledak = false;
        Timer enemyMissleTimer = new Timer( 500 );

        

    }
    @Override
    public void update(long elapsedTime) {
        playfield.update(elapsedTime);
        ESC.update(elapsedTime);
        LG.update(elapsedTime);
        
        APPLE.update(elapsedTime);
        TA.update(elapsedTime);

        if (fireTimer.isActive() && fireTimer.action(elapsedTime)) {
		// able to fire again
		fireTimer.setActive(false);
	}
        if(powertime.action(elapsedTime)){
            powertime2--;
            if(powertime2 == 0){
                halilintarUP = false;
                powerUP = false;
            }
        }
        if(powertime2 <= 0){
            powertime2 += 30;
        }

        if(Lari.action(elapsedTime)){
            Laris++;
            LariOnStop = true;
        }
        if(Laris == 30){
            LariOnStop = false;
            //Lari.wait();
        }
         /*
         * JIKA CHECK OUT MAKA LIFE - 1 DAN LIHAT SCORE
         */
        if(life == 0){
             gamestatus = GO_DEAD;
             showHiScore();
        }
        if (checkout) {
            if (keyPressed(KeyEvent.VK_Q) || keyPressed(KeyEvent.VK_ESCAPE)) {
		showHiScore();
                //parent.nextGameID = ship2wargame.main_menu;
                //finish();
            } else if (keyPressed(KeyEvent.VK_R)) {
		// restart level
		life-=1;
                jumlahbintang = 0;
                jumlahapple = 1;
                if (life >= 0){
                parent.nextGameID = MomoyGame.MainMenu;
                //gamestatus = GO_DEAD;
                initLevel();
                }
                checkout = false;
		} else if (keyPressed(KeyEvent.VK_C) || keyPressed(KeyEvent.VK_ENTER)) {
		// resume game
                    //initLevel();
                    checkout = false;
		}else if(keyPressed(KeyEvent.VK_O)){
                   OptionMenu panggil = new OptionMenu(parent);
                   panggil.start();
                }
		return;
		}
        //GAME STATUS
        switch(gamestatus){
            case INTRO:
                levelImage.update(elapsedTime);
                if (levelTimer.isActive()) {
			if (levelImage.getX() > 30) {
			levelImage.setX(30);
			levelImage.setHorizontalSpeed(0);
                        } else if (levelImage.getX() == 30) {
                            if (levelTimer.action(elapsedTime)) {
				levelImage.setHorizontalSpeed(25*50/1000);
				levelTimer.setActive(false);
			}
                    }
                }                
           case PLAYING:
             //momoy.update(elapsedTime);
               if (keyPressed(KeyEvent.VK_ESCAPE)) {
                   checkout = true;
                   //ENEMY_GROUP.setActive(false);
                   //ENEMY_GROUP2.setActive(false);
               }
               if (levelImage.getX() > 640) {
			gamestatus = PLAYING;
		}
               if (timerTime.action(elapsedTime)) {
				time--;
				if (time <= 0) {
					timeReminder();
				} else if (time < 10) {
					//playSound("sounds/time.wav");
				}
			}

               if(boom.action(elapsedTime)){
                        boomtime--;
                        if(boomtime == 0){
                            //meledak = true;
                        }
               }
               if(boomtime == -10){
                    boomtime += 100;
               }               
            //KECEPATAN 
            double maxSpeed =  0.1;
            //CONTROLLING 
            if (keyDown(KeyEvent.VK_LEFT) && keyDown(KeyEvent.VK_RIGHT)) {
                player.setFrame(0);
                player.setDirection(player.BOTTOM);
            }else if (game.keyDown(KeyEvent.VK_LEFT)) {
            //moveX(-playerSpeed * elapsedTime);
                player.addHorizontalSpeed(elapsedTime, -0.1, -maxSpeed);
                player.setAnimate(true);
                player.setDirection(player.LEFT);
                player.setStatus(player.WALKING);
                ///game.playSound("sound/movegood.wav");
            }else if (player.getHorizontalSpeed() > 0){
                player.addHorizontalSpeed(elapsedTime, 0.1, 0);
                player.setStatus(player.STAND);
            } else if (game.keyDown(KeyEvent.VK_RIGHT)) {
            //moveX(playerSpeed * elapsedTime);
                player.addHorizontalSpeed(elapsedTime, 0.1, maxSpeed);
                player.setDirection(player.RIGHT);
                player.setStatus(player.WALKING);
               /// game.playSound("sound/movegood.wav");
            }else if (player.getHorizontalSpeed() < 0) {
                player.addHorizontalSpeed(elapsedTime, -0.1, 0);
                player.setStatus(player.STAND);
            }else if (game.keyDown(KeyEvent.VK_UP) && game.keyDown(KeyEvent.VK_DOWN)) {
                player.setFrame(0);
                player.setDirection(player.UP);
                player.setStatus(player.STAND);
            }else if (game.keyDown(KeyEvent.VK_DOWN) && game.keyDown(KeyEvent.VK_RIGHT)) {
                player.setFrame(0);
                player.setDirection(player.RIGHT);
                player.setStatus(player.STAND);
                //setDirection(BOTTOM);
            }else if (game.keyDown(KeyEvent.VK_RIGHT) && game.keyDown(KeyEvent.VK_DOWN)) {
                player.setFrame(0);
                player.setDirection(player.BOTTOM);
                player.setStatus(player.STAND);
                //setDirection(BOTTOM);
            }else if (game.keyDown(KeyEvent.VK_DOWN) && game.keyDown(KeyEvent.VK_LEFT)) {
                player.setFrame(0);
                player.setDirection(player.BOTTOM);
                player.setStatus(player.STAND);
                //setDirection(BOTTOM);
            }else if (game.keyDown(KeyEvent.VK_LEFT) && game.keyDown(KeyEvent.VK_DOWN)) {
                player.setFrame(0);
                player.setDirection(player.BOTTOM);
                //setDirection(BOTTOM);
            }else if (game.keyDown(KeyEvent.VK_UP)) {
                // moveY(-playerSpeed * elapsedTime);
                player.addVerticalSpeed(elapsedTime, -0.1, -maxSpeed);
                player.setAnimate(true);
                player.setDirection(player.UP);
                player.setStatus(player.WALKING);
               // game.playSound("sound/movegood.wav");
            }else if(player.getVerticalSpeed() < 0 ){
                player.addVerticalSpeed(elapsedTime, 0.1, -0);
                player.setStatus(player.STAND);
            }else if (game.keyDown(KeyEvent.VK_DOWN)) {
                //moveY(playerSpeed * elapsedTime);
                player.addVerticalSpeed(elapsedTime, 0.1, maxSpeed);
                player.setDirection(player.BOTTOM);
                player.setAnimate(true);
                player.setStatus(player.WALKING);
                //game.playSound("sound/movegood.wav");
            } else if(player.getVerticalSpeed() > 0) {
                player.addVerticalSpeed(elapsedTime, 0.1,-maxSpeed);
                player.setStatus(player.STAND);
            }           
            //********************************************************************************
            // logika : jika momoy sudah menemukkan peri maka akan memiliki kekuatan penghancur enemy
            // change boll fight
            //********************************************************************************
       //if(powerUP = true){
            if(game.keyDown(KeyEvent.VK_SPACE) && game.keyDown(KeyEvent.VK_LEFT)){
                player.setDirection(player.LEFT_FIRE);
                player.setAnimate(true);
                player.setStatus(player.FIRE);
                player.fireTimer.setActive(true);
                //tembakan kekanan
                Sprite playerMissle = new Sprite( getImage( "images/Fire1.png" ));
                playerMissle.setLocation(player.getX()-5, player.getY()+10 );
                playerMissle.setHorizontalSpeed(-0.5);
                playerMissle.setActive(powerUP);
                PLAYER_MISSLE_GROUP.add( playerMissle );

            }else if(game.keyDown(KeyEvent.VK_SPACE) && game.keyDown(KeyEvent.VK_RIGHT)){
                player.fireTimer.setActive(true);
                player.setDirection(player.RIGHT_FIRE);
                player.setAnimate(true);
                player.setStatus(player.FIRE);
                //tembakan kekanan
                Sprite playerMissle = new Sprite( getImage( "images/Fire1.png" ));
                playerMissle.setLocation(player.getX()+25, player.getY()+10 );
                playerMissle.setHorizontalSpeed(0.5);
                playerMissle.setActive(powerUP);
                PLAYER_MISSLE_GROUP.add( playerMissle );
            }else if(game.keyDown(KeyEvent.VK_SPACE) && game.keyDown(KeyEvent.VK_UP)){
                player.fireTimer.setActive(true);
                player.setDirection(player.UP_FIRE);
                player.setAnimate(true);
                player.setStatus(player.FIRE);
                //tembakan keatas
                Sprite playerMissle = new Sprite( getImage( "images/Fire1.png" ));
                playerMissle.setLocation( player.getX()+5, player.getY()-15 );
                playerMissle.setVerticalSpeed( -0.5 );
                playerMissle.setActive(powerUP);
                PLAYER_MISSLE_GROUP.add( playerMissle );
            }else if(game.keyDown(KeyEvent.VK_SPACE) && game.keyDown(KeyEvent.VK_DOWN)){
                player.fireTimer.setActive(true);
                player.setDirection(player.BOTTOM_FIRE);
                player.setAnimate(true);
                player.setStatus(player.FIRE);
                //tembakan kebawah
                Sprite playerMissle = new Sprite( getImage( "images/Fire1.png" ));
                playerMissle.setLocation( player.getX()+5, player.getY()+25 );
                playerMissle.setVerticalSpeed( 0.5 );
                playerMissle.setActive(powerUP);
                PLAYER_MISSLE_GROUP.add( playerMissle );
            }
      // }
            //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
            //kekuatan halilintar
            //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
            if(game.keyDown(KeyEvent.VK_ALT) && game.keyDown(KeyEvent.VK_LEFT)){
                player.setDirection(player.LEFT_FIRE);
                player.setAnimate(true);
                player.setStatus(player.FIREBOLT);
                player.fireTimer.setActive(true);
                //tembakan kekanan
                Sprite HalilintarMisile = new Sprite( getImage( "images/boltLeft.png" ));
                HalilintarMisile.setLocation(player.getX()-190, player.getY()+10 );
                HalilintarMisile.setHorizontalSpeed(-0.5);
                HalilintarMisile.setActive(halilintarUP);
                HALILINTAR.add( HalilintarMisile );
                playSound("sound/megalaser.wav");
            }else if(game.keyDown(KeyEvent.VK_ALT) && game.keyDown(KeyEvent.VK_RIGHT)){
                player.fireTimer.setActive(true);
                player.setDirection(player.RIGHT_FIRE);
                player.setAnimate(true);
                player.setStatus(player.FIRE);
                Sprite HalilintarMisile = new Sprite( getImage( "images/boltRight.png" ));
                HalilintarMisile.setLocation(player.getX()+20, player.getY()+10 );
                HalilintarMisile.setHorizontalSpeed(0.5);
                HalilintarMisile.setActive(halilintarUP);
                HALILINTAR.add( HalilintarMisile );
                playSound("sound/megalaser.wav");
            }else if(game.keyDown(KeyEvent.VK_ALT) && game.keyDown(KeyEvent.VK_UP)){
                player.fireTimer.setActive(true);
                player.setDirection(player.UP_FIRE);
                player.setAnimate(true);
                player.setStatus(player.FIRE);
                Sprite HalilintarMisile = new Sprite( getImage( "images/boltUp.png" ));
                HalilintarMisile.setLocation(player.getX()+10, player.getY()-200 );
                HalilintarMisile.setVerticalSpeed( -0.5 );
                HalilintarMisile.setActive(halilintarUP);
                HALILINTAR.add( HalilintarMisile );
                playSound("sound/megalaser.wav");
            }else if(game.keyDown(KeyEvent.VK_ALT) && game.keyDown(KeyEvent.VK_DOWN)){
                player.fireTimer.setActive(true);
                player.setDirection(player.BOTTOM_FIRE);
                player.setAnimate(true);
                player.setStatus(player.FIRE);
                Sprite HalilintarMisile = new Sprite( getImage( "images/boltDown.png" ));
                HalilintarMisile.setLocation(player.getX()+10, player.getY()+25 );
                HalilintarMisile.setVerticalSpeed( 0.5 );
                HalilintarMisile.setActive(halilintarUP);
                HALILINTAR.add( HalilintarMisile );
                playSound("sound/megalaser.wav");
            }
            /***********************************************************************************
             * *********************************************************************************
             * ************************ MOMOY SPRINT *******************************************
             * *********************************************************************************
             ************************************************************************************/           
                double maxrun = 0.1;
                double maxrun2 = -0.1;
                if(game.keyDown(KeyEvent.VK_CONTROL) && game.keyDown(KeyEvent.VK_LEFT)){
                    player.addHorizontalSpeed(elapsedTime, -0.1, maxrun2*3);
                    player.setDirection(player.RUN_LEFT);
                    player.setActive(LariOnStop);
                    player.setStatus(player.RUN);
                    playSound("sound/Swoosh.wav");
                }else if(game.keyDown(KeyEvent.VK_CONTROL) && game.keyDown(KeyEvent.VK_RIGHT)){
                    player.addHorizontalSpeed(elapsedTime, 0.1, maxrun*3);
                    player.setDirection(player.RUN_RIGHT);
                    player.setActive(LariOnStop);
                    player.setStatus(player.RUN);
                    playSound("sound/Swoosh.wav");
                }else if(game.keyDown(KeyEvent.VK_CONTROL) && game.keyDown(KeyEvent.VK_UP)){
                    player.addVerticalSpeed(elapsedTime, -0.1, -maxrun*3);
                    player.setAnimate(true);
                    player.setActive(LariOnStop);
                    player.setDirection(player.RUN_UP);
                    player.setStatus(player.RUN);
                    playSound("sound/Swoosh.wav");
                }else if(game.keyDown(KeyEvent.VK_CONTROL) && game.keyDown(KeyEvent.VK_DOWN)){
                    player.addVerticalSpeed(elapsedTime, 0.1, maxrun*3);
                    player.setAnimate(true);
                    player.setActive(LariOnStop);
                    player.setDirection(player.RUN_DOWN);
                    player.setStatus(player.RUN);
                    playSound("sound/Swoosh.wav");
                }
                /*
                 *              TEMBAKAN DIBAWA SEJAK MASIH AKTIF TAPI TIDAK DAPAT MENEMBUS TILE
                 */
                 if(game.keyDown(KeyEvent.VK_SHIFT) && game.keyDown(KeyEvent.VK_LEFT)){
                 player.setDirection(player.LEFT_FIRE);
                 player.setAnimate(true);
                 player.setStatus(player.FIRE);
                 player.fireTimer.setActive(true);
                 playSound("sound/sound1.wav");
//                 Sprite playerMissle2 = new Sprite(getImages("images/staticglow.png", 5, 1));
//                 playerMissle2.setLocation(player.getX()-5, player.getY()+10 );
//                 playerMissle2.setHorizontalSpeed(-0.5);
//                 playerMissle2.setActive(powerUP);

                 BufferedImage[] playermissle = getImages("images/staticspark.png",10,1);
                 AnimatedSprite pl = new AnimatedSprite(playermissle,-100,100);
                 pl.setLocation(player.getX()-5, player.getY()+10 );
                 pl.setHorizontalSpeed(-0.08);
                 pl.setActive(true);
                 KEKUATANBAWAAN.add(pl);
                
                 }else if(game.keyDown(KeyEvent.VK_SHIFT) && game.keyDown(KeyEvent.VK_RIGHT)){
                 player.fireTimer.setActive(true);
                 player.setDirection(player.RIGHT_FIRE);
                 player.setAnimate(true);
                 player.setStatus(player.FIRE);
                 playSound("sound/sound1.wav");
                 BufferedImage[] playermissle = getImages("images/staticspark.png",10,1);
                 AnimatedSprite pl = new AnimatedSprite(playermissle,-100,100);
                 pl.setLocation(player.getX()+20, player.getY()+10 );
                 pl.setHorizontalSpeed(0.08);
                 pl.setActive(true);
                 KEKUATANBAWAAN.add(pl);
                 
                 }else if(game.keyDown(KeyEvent.VK_SHIFT) && game.keyDown(KeyEvent.VK_DOWN)){
                 player.fireTimer.setActive(true);
                 player.setDirection(player.BOTTOM_FIRE);
                 player.setAnimate(true);
                 player.setStatus(player.FIRE);
                 playSound("sound/sound1.wav");

                 BufferedImage[] playermissle = getImages("images/staticspark2.png",1,10);
                 AnimatedSprite pl = new AnimatedSprite(playermissle,-100,100);
                 pl.setLocation(player.getX()+10, player.getY());
                 pl.setVerticalSpeed(0.08);
                 pl.setActive(true);
                 KEKUATANBAWAAN.add(pl);
                 
                 }else if(game.keyDown(KeyEvent.VK_SHIFT) && game.keyDown(KeyEvent.VK_UP)){
                 player.fireTimer.setActive(true);
                 player.setDirection(player.UP_FIRE);
                 player.setAnimate(true);
                 player.setStatus(player.FIRE);
                 playSound("sound/sound1.wav");

                 BufferedImage[] playermissle = getImages("images/staticspark2.png",1,10);
                 AnimatedSprite pl = new AnimatedSprite(playermissle,-100,100);
                 pl.setLocation(player.getX()+10, player.getY());
                 pl.setVerticalSpeed(-0.08);
                 pl.setActive(true);
                 KEKUATANBAWAAN.add(pl);
                                  }

//            case GO_NEXT:
////                if(timerNext.action(elapsedTime)||keyPressed(KeyEvent.VK_ENTER)){
////                        gamestatus = INTRO;
////                        level++;
////                        initLevel();
////                        time += 100;
//               // }
//
//            break;

            case GO_DEAD:
              if (keyPressed(KeyEvent.VK_Q) || keyPressed(KeyEvent.VK_ESCAPE)) {
                        //showHiScore();
                        //finish();
                     }
//                if (life >= 0) {
//			gamestatus = PLAYING;
//			initLevel();
//		} else {
//			// die!
//			//showHiScore();
//		}
            break;
            }

            /*
             *
             * BONUS GET OUT
             *
             */
             if(score == 200){
                BONUS.setActive(true);               
             }
             if(jumlahbintang == 2){
                BONUS.setActive(true);
             }else if (jumlahbintang == 4){
                BONUS3.setActive(true);
             }else if(jumlahbintang == 6){
                BONUS.setActive(true);
             }
            /*inisialisasi jika jumlahapple sudah 3 maka akan mendapat gunting
             * dengan logika jika aplle sudah termakan 3 maka
             * gunting keluar
             */
            if(jumlahapple == 3){
                BONUS2.setActive(true);
            }
   }

    @Override
    public void render(Graphics2D g) {
        //g.setColor(Color.GREEN.darker());
        playfield.render(g);

        if (checkout) {
                ESC.render(g,280,280);
		titlefont.drawString(g, "CONTINUE", 300, 300);
		titlefont.drawString(g, "RESTART", 300, 360);
                titlefont.drawString(g, "OPTIONS",300,330);
		titlefont.drawString(g, "QUIT", 300, 390);

		return;
	}

        // render game state specific
		switch (gamestatus) {
			// level title
			case INTRO:
				levelImage.render(g);                   
			break;
                        case PLAYING:
                            font.drawString(g, "waktu:" + time, 15, 10);
                            //font.drawString(g, "powertimer :" + powertime2, 80, 200);
                            //font.drawString(g, "PowerLari :" + Laris,80,260);
                            //font.drawString(g,"musuh life :"+ enemylife,80,260);
                            font.drawString(g, "nilai" +String.valueOf( score ), 250, 10);
                            LG.render(g, 700, 10);
                            font.drawString(g, " " + String.valueOf(life),720, 10);
                            APPLE.render(g, 480, 10);
                            font.drawString(g, " " + String.valueOf(jumlahapple),500,10);
                            BINTANG.render(g, 550, 10);
                            font.drawString(g, " " + String.valueOf(jumlahbintang),560,10);
                            font.drawString(g, "boom time :" + boomtime, 272,600);
                            break;
                        case GO_DEAD:
                            if (time >= 0) {
				font.drawString(g, loseTitle, GameFont.CENTER, 0, 224, getWidth());
                                //showHiScore();                             
                            } else {
				font.drawString(g, "Argh! You're Dead!", GameFont.CENTER, 0, 224, getWidth());
                            }                           
                        break;
                }
    }

///inisialisasi level
    public void initLevel() {
        gamestatus = INTRO;
        playSound("sound/v_getready.wav");
        if(buka2 == true){
            buka = true;
        }
        //memulai dengan intro game
        
        //memulai initialisasi terhadap file level 
        String levelFile = "levels/Level" + level + ".txt";
	String[] content = FileUtil.fileRead(bsIO.getStream(levelFile));

        //membaca file dari intro
        StringTokenizer token = new StringTokenizer(content[0], "+");
        String desc[] = new String[token.countTokens()];
        for (int i=0;i<desc.length;i++){
            desc[i] = token.nextToken();
        }

        //membuat file description
        BufferedImage tem = getImage("images/LevelDescrip.png");
        BufferedImage image = ImageUtil.createImage(tem.getWidth(),tem.getHeight(),Transparency.BITMASK);

        Graphics2D g =  image.createGraphics();
            //draw panel
        g.drawImage(tem, 0, 0, null);
            //draw title level title
        titlefont.drawString(g, "LEVEL " + level , GameFont.CENTER, 0,17,tem.getWidth());
        titlefont.drawString(g, "------" , GameFont.CENTER, 0,37,tem.getWidth());
            //mengambar description dari map
        for (int i=0;i<desc.length;i++){
        titlefont.drawString(g, desc[i], GameFont.CENTER, 0,70+(i*20),tem.getWidth());
            }
        g.dispose();

            levelImage.setImage(image);
            levelImage.setLocation(-image.getWidth(), 250);
            levelTimer.setActive(true);
            
	token = new StringTokenizer(content[1], " x ");
	int w = Integer.parseInt(token.nextToken()), 	// level width
            h = Integer.parseInt(token.nextToken());	// level height
        playfield 	= new PlayField();
        BufferedImage img = getImage("images/Background copy.png", false);
	background = new ParallaxBackground(new Background[] {
	new Background(w*48, h*48),
        new ImageBackground(img, 
			   (img.getWidth() > w*48) ? w*48 : img.getWidth(),
			   (img.getHeight() > h*48) ? h*48 : img.getHeight())
        //new ColorBackground(Color.BLACK, 816, 624)
	});
        player = new MomoySprite(this, player_image);
        
	playfield.setBackground(background);

        JEMBATAN = playfield.addGroup(new SpriteGroup("jembatan"));
        
        LANTAI  = playfield.addGroup(new SpriteGroup("Lantai"));

        LIFE = playfield.addGroup(new SpriteGroup("Life"));

        BONUS = playfield.addGroup(new SpriteGroup("bonus"));//aplle untuk nyawa

        BONUS1 = playfield.addGroup(new SpriteGroup("bonus2"));

        BONUS2 = playfield.addGroup(new SpriteGroup("bonus3"));

        BONUS3 = playfield.addGroup(new SpriteGroup("bonus4"));

        POWERUP1 = playfield.addGroup(new SpriteGroup("meatpower"));

        HALILINTARPOWER = playfield.addGroup(new SpriteGroup("halilintarpower"));

        SUNGAI = playfield.addGroup(new SpriteGroup("sungai"));

        TILE  	= playfield.addGroup(new AdvanceSpriteGroup("Tile", 624));

        DINDINGSCORE = playfield.addGroup(new SpriteGroup("TILE2"));

		GOAL	= playfield.addGroup(new SpriteGroup("Objective Goal"));

		PATTERN = playfield.addGroup(new SpriteGroup("Pattern"));

        ENEMY_GROUP = playfield.addGroup(new SpriteGroup("ENEMY"));

        ENEMY_GROUP2 = playfield.addGroup(new SpriteGroup("ENEMYFY"));

        PAPAN = playfield.addGroup(new SpriteGroup("PAPAN"));

        PENJEPIT = playfield.addGroup(new SpriteGroup("PAPAN"));

        PAPANPATTERN = playfield.addGroup(new SpriteGroup("PAPAN2"));

        MOMOY = playfield.addGroup(new SpriteGroup("MOmoy"));

        PLAYER_MISSLE_GROUP = playfield.addGroup(new SpriteGroup("tembak1"));

        HALILINTAR = playfield.addGroup(new SpriteGroup("Halilintar"));

        KEKUATANBAWAAN = playfield.addGroup(new SpriteGroup("kekuatanbawaan"));

        Terakahir = playfield.addGroup(new SpriteGroup("terakhir"));
       

        //PLAYER_MISSLE_GROUP2 = playfield.addGroup(new SpriteGroup("playermisile"));
        time = 100;
        
        boomtime = 100;

        BufferedImage[] OpenClosed = getImages("images/box_bergerak.png",5,1);

        AnimatedSprite OpenClose = new AnimatedSprite(OpenClosed,-100,100);
        OpenClose.setAnimate(buka);
        OpenClose.setLoopAnim(buka);
        PAPANPATTERN.add(OpenClose);

        BufferedImage[] sungai = getImages("images/sungai6.png",2,1);
        AnimatedSprite air = new AnimatedSprite(sungai,-100,100);
        air.setAnimate(true);
        air.setLoopAnim(true);
        PAPANPATTERN.add(air);

        BufferedImage[] sungai2 = getImages("images/sungai5.png",1,2);
        AnimatedSprite air2 = new AnimatedSprite(sungai2,-100,100);
        air2.setAnimate(true);
        air2.setLoopAnim(true);
        PAPANPATTERN.add(air2);

        BufferedImage[] trapped = getImages("IMG_BONUS/trapped.png",9,1);
        AnimatedSprite trapped2 = new AnimatedSprite(trapped,-100,100);
        trapped2.setAnimate(true);
        trapped2.setLoopAnim(true);
        PAPANPATTERN.add(trapped2);


        /* ##############################################################################
         * ##############################################################################
         * ####################  INISIALISASI MUSUH #####################################
         * #######################    START IN   ########################################
         * ##############################################################################
         * ##############################################################################
         */

         BufferedImage[] imag5 = getImages("img_enemy/level5.png",8,4);
         BufferedImage[] imag4 = getImages("img_enemy/enemy4.png",8,4);
         BufferedImage[] imag3 = getImages("img_enemy/enemy3.png",8,4);
         BufferedImage[] imag2 = getImages("img_enemy/enemy2.png",8,4);
         BufferedImage[] imag = getImages("img_enemy/enemy.png", 8, 4);
         double speed = 0;
         long animationDelay = 100;
         double kecepatanmusuh = 0.1;

         switch(game.level){
             case 0:
                 kecepatanmusuh = 0.5;
                 break;
             case 1:
                 kecepatanmusuh = 1.3;
                 break;
             case 2:
                 kecepatanmusuh = 3.0;
                 break;
         }


         //// level 1 image
         Musuh enemylevel1;
         //enemylevel1 = new FollowYou(this, imag, speed = 0.1*kecepatanmusuh, animationDelay, player);
         enemylevel1 = new LeftPatrol(this, imag, speed = 0.1*kecepatanmusuh, animationDelay);

         Musuh enemylevel12;
         enemylevel12 = new LeftRight(this, imag, speed = 0.1*kecepatanmusuh, animationDelay);

         Musuh enemylevel13;
         enemylevel13 = new Round(this, imag,  speed = 0.1*kecepatanmusuh, animationDelay);

         Musuh enemylevel14;
         enemylevel14 = new RightPatrol(this, imag, speed = 0.1*kecepatanmusuh, animationDelay);

         //LEVEL 2

         Musuh enemylevel2;
         enemylevel2 = new Round(this, imag,  speed = 0.1*kecepatanmusuh, animationDelay);

         Musuh enemylevel21;
         enemylevel21 = new LeftRight(this, imag2,  speed = 0.1*kecepatanmusuh, animationDelay);
         
         Musuh enemylevel22;
         enemylevel22 = new UpDown(this, imag2, speed  = 0.1*kecepatanmusuh, animationDelay);

         Musuh enemylevel23;
         enemylevel23 = new UpDown(this, imag2, speed  = 0.1*kecepatanmusuh, animationDelay);

         Musuh enemylevel24;
         enemylevel24 = new LeftRight(this, imag2,  speed = 0.1*kecepatanmusuh, animationDelay);

         //LEVEL 3
         
         Musuh enemylevel3;
         enemylevel3 = new LeftPatrol(this, imag3,  speed  = 0.1*kecepatanmusuh, animationDelay);

         Musuh enemylevel31;
         enemylevel31 = new UpDown(this, imag3,  speed  = 0.1*kecepatanmusuh, animationDelay);

         Musuh enemylevel32;
         enemylevel32 = new Round(this, imag3, speed  = 0.1*kecepatanmusuh, animationDelay);

         Musuh enemylevel33;
         enemylevel33 = new Round(this, imag3,  speed  = 0.1*kecepatanmusuh, animationDelay);

         Musuh enemylevel34;
         enemylevel34 = new LeftRight(this, imag3, speed = 0.1*kecepatanmusuh, animationDelay);

         //LEVEL 4

         Musuh enemylevel4;
         enemylevel4 = new UpDown(this, imag4,  speed  = 0.1*kecepatanmusuh, animationDelay);

         Musuh enemylevel42;
         enemylevel42 = new Round(this, imag4,  speed  = 0.1*kecepatanmusuh, animationDelay);

         Musuh enemylevel43;
         enemylevel43 = new UpDown(this, imag4,speed = 0.1*kecepatanmusuh, animationDelay);

         Musuh enemylevel44;
         enemylevel44 = new RightPatrol(this, imag4, speed = 0.1*kecepatanmusuh, animationDelay);

         Musuh enemylevel45;
         enemylevel45 = new LeftRight(this, imag4, speed, animationDelay);

         //LEVEL 5
         Musuh enemylevel51;
         enemylevel51 = new FollowYou(this, imag5, speed= 0.1*kecepatanmusuh, animationDelay, player);

         Musuh enemylevel52;
         enemylevel52 = new FollowYou(this, imag5, speed= 0.1*kecepatanmusuh, animationDelay, player);

         Musuh enemylevel53;
         enemylevel53 = new  LeftRight(this, imag5, speed= 0.1*kecepatanmusuh, animationDelay);

         Musuh enemylevel54;
         enemylevel54 = new LeftRight(this, imag5, speed= 0.1*kecepatanmusuh, animationDelay);

         Musuh enemylevel55;
         enemylevel55 = new Round(this, imag5, speed= 0.1*kecepatanmusuh, animationDelay);

         //LEVEL 6

        /* ##############################################################################
         * ##############################################################################
         * #########################      END CODE  #####################################
         * ##############################################################################
         * ##############################################################################
         * ##############################################################################
         */
        for (int y=0;y < h;y++) {
		for (int x=0;x < w;x++) {
			int tile = 0;

			try {
				// get tile from file content
				char c = content[y + 2].charAt(x);

				switch (c) {
                                    case ' ': tile = 0; break;
                                    case '*': tile = 100;break;
                                    case 'X': tile = 1000;break; // this is the goal
                                    case '$': tile = 1001;break;
                                    case '#': tile = 1002;break;
                                    case '@': tile = 1003;break;
                                    case '|': tile = 5000;break;
                                    case '/': tile = 3000;break; //sungai
                                    case '.': tile = 3001;break; //sungai 2
                                    case '(': tile = 3002;break;
                                    case ')': tile = 3003;break;
                                    case '_': tile = 3004;break; //terakhir...level
                                   /*
                                    * bonus case
                                    */
                                    case 'a':tile =200;break;//apple ==> bonus
                                    case 'b':tile =201;break;//bintang ==> hero
                                    case 'c':tile =202;break;//gunting ==> strong
                                    case 'd':tile =203;break;//apple ==> life
                                    case 'e':tile =204;break;//cookies
                                    case 'f':tile =250;break;//meatpower bonus
                                    case 'g':tile =251;break;//halilintar power
                                    case ']':tile =205;break;
                                    //case ''
                                    /*
                                     * ENEMY VALUE
                                     */
                                    //level 1
                                    case 'Q':tile = 300;break;
                                    case 'W':tile = 301;break;
                                    case 'E':tile = 302;break;
                                    case 'R':tile = 303;break;//KOSONG
                                    case 'T':tile = 304;break;//KOSONG
                                    case 'Y':tile = 305;break;//KOSONG
                                    //level 2
                                    case 'U':tile = 306;break;
                                    case 'I':tile = 307;break;
                                    case 'O':tile = 308;break;
                                    case 'P':tile = 309;break;
                                    case 'A':tile = 310;break;
                                    //level 3
                                    case 'S':tile = 311;break;
                                    case 'D':tile = 312;break;
                                    case 'F':tile = 313;break;
                                    case 'G':tile = 314;break;
                                    case 'H':tile = 315;break;
                                    //LEVEL 4
                                    case 'J':tile = 316;break;
                                    case 'K':tile = 317;break;
                                    case 'L':tile = 318;break;
                                    case 'Z':tile = 319;break;
                                    case '<':tile = 320;break;
                                    //LEVEL 5
                                    case 'C':tile = 321;break;
                                    case 'V':tile = 322;break;
                                    case 'B':tile = 323;break;
                                    case 'N':tile = 324;break;
                                    case 'M':tile = 325;break;
                                    //PAPAN BERGERAK
                                    case ':': tile = 350;break;                                       
                                    default : tile = Character.getNumericValue(c); break;
				}
			} catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
			switch (tile) {
                                case 0:// LANTAI 
                                    //Sprite groundTile0 = new Sprite(getImage("images_lantai/lantai7.jpg"),x*48,y*48);
                                   // LANTAI.add(groundTile0);
                                    break;
				case 1: // TEMBOK SAMPING
                                    Sprite groundTile = new Sprite(getImage("images_tembok/Tembok4.png"),x*48, y*48);
                                    TILE.add(groundTile);
				break;
				case 2: // TEMBOK TENGAH
                                    Sprite groundTile2 = new Sprite(getImage("images_tembok/Tembok1.png"),x*48, y*48);
                                    TILE.add(groundTile2);
				break;
				case 3: // b
                                    Sprite groundTile3 = new Sprite(getImage("images_tembok/fir C ani0007.png"),x*48, y*48);
				    TILE.add(groundTile3);
                                    break;
				case 4:
                                    Sprite groundTile4 = new Sprite(getImage("images_tembok/trees2.png"),x*48, y*48);
				    TILE.add(groundTile4);
                                    break;
				case 5:
                                    Sprite groundTile5 = new Sprite(getImage("images_tembok/trees3.png"),x*48, y*48);
				    TILE.add(groundTile5);
                                    break;
                                case 6:
                                    Sprite groundTile6 = new Sprite(getImage("images_tembok/trees5.png"),x*48, y*48);
				    TILE.add(groundTile6);
                                    break;
                                case 7:
                                    Sprite groundTile7 = new Sprite(getImage("images_tembok/Tembok7.png"),x*48,y*48);
                                    TILE.add(groundTile7);
                                    break;
                                case 200:
                                    Sprite bonus1 = new Sprite(getImage("IMG_BONUS/apple.png"),x*48,y*48);
                                    BONUS.add(bonus1);
                                    BONUS.setActive(false);
                                    break;
                                case 201:
                                    Sprite bonus2 = new Sprite(getImage("IMG_BONUS/Favourites.png"),x*48,y*48);
                                    BONUS1.add(bonus2);
                                    break;
                                case 202:
                                    Sprite bonus3 = new Sprite(getImage("IMG_BONUS/Gunting.png"),x*48,y*48);
                                    BONUS2.add(bonus3);
                                    BONUS2.setActive(false);
                                    break;
                                case 203:
                                    Sprite bonusapp3 = new Sprite(getImage("IMG_BONUS/apple.png"),x*48,y*48);
                                    BONUS3.add(bonusapp3);
                                    BONUS3.setActive(false);
                                    break;

                                case 205:
                                    Sprite ROLLING2 = new PatternSprite(trapped2,x*48,y*48);
                                    PENJEPIT.add(ROLLING2);
                                    break;
                                case 250:
                                    Sprite meatpower = new Sprite(getImage("IMG_BONUS/meat0003.png"),x*48,y*48);
                                    POWERUP1.add(meatpower);
                                    break;
                                case 251:
                                    Sprite bladepower = new Sprite(getImage("IMG_BONUS/Halilintar.png"),x*48,y*48);
                                    HALILINTARPOWER.add(bladepower);
                                    break;
                                //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%/
                                //%%%%%%%%%%%%%%%%%%%%%ENEMY ADD PLAYFIELD%%%%%%%%%%%%%%%%%%%%%%%%/
                                //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%/
                                //level 1
                                case 300:
                                    enemylevel1.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel1);
                                    break;
                                case 301:
                                    enemylevel12.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel12);
                                    break;      
                                case 302:
                                    enemylevel13.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel13);
                                    break;
                                case 303:
                                    enemylevel14.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel14);
                                    break;
                                //LEVEL 2
                                case 306:
                                    enemylevel2.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel2);
                                    break;
                                case 307:
                                    enemylevel21.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel21);
                                    break;
                                case 308:
                                    enemylevel22.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel22);
                                    break;
                                case 309:
                                    enemylevel23.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel23);
                                    break;
                                case 310:
                                    enemylevel24.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel24);
                                    break;
                                //LEVEL 3
                                case 311:
                                    enemylevel3.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel3);
                                    break;
                                case 312:
                                    enemylevel31.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel31);
                                    break;
                                case 313:
                                    enemylevel32.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel32);
                                    break;
                                case 314:
                                    enemylevel33.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel33);
                                    break;
                                case 315:
                                    enemylevel34.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel34);
                                    break;
                                //LEVEL 4
                                case 316:
                                    enemylevel4.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel4);
                                    break;
                                case 317:
                                    enemylevel42.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel42);
                                    break;
                                case 318:
                                    enemylevel43.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel43);
                                    break;
                                case 319:
                                    enemylevel44.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel44);
                                    break;
                                case 320:
                                    enemylevel45.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel45);
                                    break;
                                //LEVEL 5
                                case 321:
                                    enemylevel51.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel51);
                                    break;
                                case 322:
                                    enemylevel52.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel52);
                                    break;
                                case 323:
                                    enemylevel53.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel53);
                                    break;
                                case 324:
                                    enemylevel54.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel54);
                                    break;
                                case 325:
                                    enemylevel55.setLocation(x*48, y*48);
                                    ENEMY_GROUP.add(enemylevel55);
                                    break;

                                case 350:
                                    Sprite ROLLING = new PatternSprite(OpenClose,x*48,y*48);
                                    PAPAN.add(ROLLING);
                                    break;
				case 100:
                                    player.setLocation(x*48, y*48);
                                    break;
				case 1000: // objective!
                                    GOAL.add(new Sprite(getImage("images/LantaiUP.png"), x*48, y*48));
                                    break;
                                case 1001:
                                    GOAL.add(new Sprite(getImage("images/LantaiUP2.png"),x*48,y*48));
                                    break;
                                case 1002:
                                    GOAL.add(new Sprite(getImage("images/LantaiUP3.png"),x*48,y*48));
                                    break;
                                case 1003:
                                    GOAL.add(new Sprite(getImage("images/LantaiUP4.png"),x*48,y*48));
                                case 3000:
                                    Sprite Sungai = new PatternSprite(air,x*48,y*48);
                                    SUNGAI.add(Sungai);
                                    break;
                                case 3001:
                                    Sprite Sungai2 = new PatternSprite(air2,x*48,y*48);
                                    SUNGAI.add(Sungai2);
                                    break;
                                case 3002:
                                    Sprite jembatan = new Sprite(getImage("images/jembatan.png"),x*48, y*48);
                                    JEMBATAN.add(jembatan);
                                    break;
                                case 3003:
                                    Sprite jembatan2 = new Sprite(getImage("images/jembatan2.png"),x*48, y*48);
                                    JEMBATAN.add(jembatan2);
                                    break;
                            case 3004:
                                Sprite DOOR = new Sprite(getImage("IMG_BONUS/America Online.png"),x*48,y*48);
                                Terakahir.add(DOOR);
                                break;

                                case 5000:
                                   // Sprite Score = new Sprite(getImage("images_tembok/DINDINGSCORE.png"), x*48, y*48));
                                    DINDINGSCORE.add(new Sprite(getImage("images_tembok/DINDINGSCORE.png"), x*48, y*48));
                                    break;
                                }
                            }
                         }

           //ADD karakter ke player
            MOMOY.add(player);
            //background.setToCenter(momoy);
            playfield.update(0);
            playfield.addCollisionGroup(MOMOY, TILE,new PlayerTileCollision(this));
            playfield.addCollisionGroup(ENEMY_GROUP, TILE, new EnemyTileCollision2(this));
            playfield.addCollisionGroup(ENEMY_GROUP,MOMOY,new EnemyPlayerCollision(this));
           //playfield.addCollisionGroup(ENEMY_GROUP2,TILE, new EnemyTileFYCollision());
            playfield.addCollisionGroup(ENEMY_GROUP, HALILINTAR, new EnemyHalilintarCollision(this));
            playfield.addCollisionGroup(ENEMY_GROUP, SUNGAI, new EnemySungaiCollision(this));
            playfield.addCollisionGroup(MOMOY, BONUS, new PlayerBonusAppleCollision(this)); //APLLE
            playfield.addCollisionGroup(MOMOY, BONUS3, new PlayerBonusAppleCollision(this)); //APLLE
            playfield.addCollisionGroup(MOMOY, BONUS1, new PlayerBonus1Collision(this));
            playfield.addCollisionGroup(MOMOY, BONUS2, new PlayerGuntingCollision());
            playfield.addCollisionGroup(MOMOY,GOAL,new PlayerGoalCollision(this));
            playfield.addCollisionGroup(MOMOY, DINDINGSCORE, new PlayerDindingCollision(this));
            //playfield.addCollisionGroup(MOMOY, BONUS, new PlayerBonusCollision());
            //MISILE&HALILINTAR COLLISION
            playfield.addCollisionGroup(HALILINTAR, TILE, new HalilintarTileCollision(this));
            playfield.addCollisionGroup(PLAYER_MISSLE_GROUP, TILE, new BallTileCollision(this));
            playfield.addCollisionGroup(HALILINTAR, DINDINGSCORE, new HalilintarDindingCollision(this));
            playfield.addCollisionGroup(PLAYER_MISSLE_GROUP, DINDINGSCORE, new BallDindingCollision(this));
            playfield.addCollisionGroup(MOMOY, PAPAN, new PlayerBoxmovedCollision(this));
            playfield.addCollisionGroup(MOMOY, POWERUP1, new PlayerPOWERUP1Collision(this));
            playfield.addCollisionGroup(MOMOY,HALILINTARPOWER,new PlayerHalilintarCollision(this));
            playfield.addCollisionGroup(MOMOY,SUNGAI, new PlayerSungaiCollision(this));
            playfield.addCollisionGroup(ENEMY_GROUP, PENJEPIT, new EnemyPenjepitCollision(this));
            playfield.addCollisionGroup(KEKUATANBAWAAN, TILE, new KekuatanBawaanTileCollision(this));
            playfield.addCollisionGroup(KEKUATANBAWAAN, ENEMY_GROUP,new KekuatanBawaanEnemyCollision(this));
            playfield.addCollisionGroup(MOMOY, Terakahir, new PlayerWithTerakhir(this));
            
            //playfield.addCollisionGroup(TILE, MOMOY, playerwithgunting);

            enemylife = 20;
    }
/*
 *
 *  EFFECT TAMBAHAN
 *
 */
 public void timeReminder() {
		life-=1;
		if (time == 0) {
                    gamestatus = GO_DEAD;
                    //if(!blink){
                    loseTitle = "Nyawa anda habis!!!";
                    //}
                    initLevel();
                }
                if(life == 0){
                    loseTitle = "YourDead!!!";
                }
	}
//LEDAKAN KECIL
public void addExplosionEffect2(double centerX, double centerY) {
	    BufferedImage[] image = getImages("images/explode.png", 15, 1);
            double x = centerX - (image[0].getWidth()/2),y = centerY - (image[0].getHeight()/2);
            VolatileSprite explosion = new VolatileSprite(image, x, y);
            explosion.getAnimationTimer().setDelay(80);
            playfield.add(explosion);
}
//LEDAKAN BESAR
public void addExplosionEffect(double centerX, double centerY) {
	    BufferedImage[] image = getImages("images/Explosion.png", 5, 3);
            double x = centerX - (image[0].getWidth()+5),y = centerY - (image[0].getHeight()+10);
            VolatileSprite explosion = new VolatileSprite(image, x, y);
            explosion.getAnimationTimer().setDelay(80);
            playfield.add(explosion);
	}
//ASAP MENGEMPUL
public void addExplosionAsap(double centerX, double centerY) {
	    BufferedImage[] image = getImages("images/mushsmoke.png", 3, 1);
            double x = centerX - (image[0].getWidth()/2),y = centerY - (image[0].getHeight()/2);
            VolatileSprite explosion = new VolatileSprite(image, x, y);
            explosion.getAnimationTimer().setDelay(80);
            playfield.add(explosion);
}
//EFEK BINTANG2
public void addBintang2(double centerX, double centerY) {
	    BufferedImage[] image = getImages("images/sparkle.png", 6, 1);
            double x = centerX - (image[0].getWidth()/2),y = centerY - (image[0].getHeight()/2);
            VolatileSprite explosion = new VolatileSprite(image, x, y);
            explosion.getAnimationTimer().setDelay(80);
            playfield.add(explosion);
}
//efek kena penjepit
public void addPenjepit(double centerX,double centerY){
    BufferedImage[] image = getImages("images/beamfire.png",5,1);
    double x = centerX - (image[0].getWidth()/2),y = centerY - (image[0].getHeight()/2);
    VolatileSprite explosion = new VolatileSprite(image, x, y);
    explosion.getAnimationTimer().setDelay(100);
    playfield.add(explosion);
}

//efek ledakan dari kekuatan bawaan
public void addLedakanKekuatanBawaan(double centerX,double centerY){
    BufferedImage[] image = getImages("images/beamfire.png",5,1);
    double x = centerX - (image[0].getWidth()/2),y = centerY - (image[0].getHeight()/2);
    VolatileSprite explosion = new VolatileSprite(image, x, y);
    explosion.getAnimationTimer().setDelay(100);
    playfield.add(explosion);
}

/***********************************************************************************************/
/*                                    COLLISION MANAGER                                        */
/***********************************************************************************************/

    /*jika momoy mendapatkan 3 buah apel maka
      akan muncul 1 gunting
     * int jumlahapple;
     * 
     * 2. playfield Momoy,TILE,NEW PlayerBonus2Collision
     * 1. playfield MOMOY,BONUS2,NEW PLAYERBONUS2COLLISION
     * 
      gunting berfungsi untuk agar obyek momoy dapat memotong dari tile..
     */
    class PlayerBonus2Collision extends BasicCollisionGroup{
        @Override
        public void collided(Sprite s1, Sprite s2) {
         
        }
    }

    //jika ball menyentuh dinding score maka ball akan hilang atau meledak

    class PlayerGuntingCollision extends AdvanceCollisionGroup{

        @Override
    public void collided(Sprite s1, Sprite s2) {
         s2.setActive(false);
            jumlahappletermakan = true;
            if (jumlahappletermakan == true){
                playerwithgunting = new BasicCollisionGroup(){

                @Override
                public void collided(Sprite s1, Sprite s2) {
                    revertPosition2();
                    s1.setActive(false);
                    jumlahapple -= 1;
                }
           };
               playfield.addCollisionGroup(TILE,MOMOY, playerwithgunting);
           }
           addBintang2(s2.getX() + (s2.getWidth()/2), s2.getY() + (s2.getHeight()/2));

    }


}
}
