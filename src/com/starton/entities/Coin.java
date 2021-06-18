package com.starton.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.starton.main.Game;
import com.starton.world.Camera;

public class Coin extends Entity{
	
	private int frames = 0,maxFrames = 10 /*max frames para reduzir a velocidade da animação do inimigo*/,index = 0,maxIndex = 3;
	
	private BufferedImage[] sprites;

	public Coin(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		sprites = new BufferedImage[4];
		sprites[0] = Game.spritesheet.getSprite(112, 0, 16, 16);
		sprites[1] = Game.spritesheet.getSprite(128, 0, 16, 16);
		sprites[2] = Game.spritesheet.getSprite(144, 0, 16, 16);
		sprites[3] = Game.spritesheet.getSprite(128, 0, 16, 16);
	}
	
	public void tick() {
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) {
				index = 0;
			}
		}
		
	}

	public void render(Graphics g) { //trocar o tamanho da mascara (para colidirem com o tamanho correto)
		g.drawImage(sprites[index], this.getX() - Camera.x,this.getY() - Camera.y,null);
	}

}
