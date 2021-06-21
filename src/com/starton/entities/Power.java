package com.starton.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.starton.main.Game;
import com.starton.world.Camera;

public class Power extends Entity{

	private int frames = 0,maxFrames = 10 /*max frames para reduzir a velocidade da animação do inimigo*/,index = 0,maxIndex = 2;
	
	private BufferedImage[] sprites;

	public Power(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		sprites = new BufferedImage[3];
		sprites[0] = Game.spritesheet.getSprite(112, 16, 16, 16);
		sprites[1] = Game.spritesheet.getSprite(128, 16, 16, 16);
		sprites[2] = Game.spritesheet.getSprite(144, 16, 16, 16);
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
