/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golden.momoyfindpatch;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.background.ColorBackground;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author cyber-blackhat
 */
public class YpIntro extends GameObject {


    BufferedImage yanisoft;
    ColorBackground background;
    int counter = 0;
    
    private float alpha = 0.0F;

    


    public YpIntro(GameEngine parent){
        super(parent);
        playSound("sound/laserpowerup.wav");
    }

    @Override
    public void initResources()
  {
    this.yanisoft = getImage("images/Main.png");
    this.background = new ColorBackground(Color.black);
    
  }

    public void update(long elapsedTime)
  {
    this.counter += 1;
    if ((this.counter < 50) && (this.counter % 5 == 0)) {
      this.alpha += 0.1F;
      playSound("sound/bosslaser.wav");
    }
    if ((this.counter > 155) && (this.counter % 5 == 0)) {
      this.alpha -= 0.1F;
      playSound("sound/bosslaserblast.wav");
    }
    if (this.bsInput.getKeyPressed() != -2147483648) {
      this.counter = 200;
    }
    if (this.bsInput.isMousePressed(1))
      this.counter = 200;
  }

    @Override
    public void render(Graphics2D g) {
        this.background.render(g);
        Composite old = g.getComposite();
        g.setComposite(AlphaComposite.getInstance(3, this.alpha));
        g.drawImage(this.yanisoft, 150, 100, null);
        g.setComposite(old);

        if (this.counter == 200) {
            this.parent.nextGameID = 1;
            finish();
        }
    }

}
