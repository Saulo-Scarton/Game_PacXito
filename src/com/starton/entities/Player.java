package com.starton.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.starton.main.Game;
import com.starton.world.Camera;
import com.starton.world.World;

public class Player extends Entity{
	
	public boolean right,up,left,down;

	public BufferedImage sprite_left, sprite_right, sprite_up, sprite_down;
	
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		sprite_left = Game.spritesheet.getSprite(48, 0, 16, 16);
		sprite_right = Game.spritesheet.getSprite(64, 0, 16, 16);
		sprite_up = Game.spritesheet.getSprite(80, 0, 16, 16);
		sprite_down = Game.spritesheet.getSprite(96, 0, 16, 16);
	}
	
	public void tick(){
		depth = 1;
		if(right && World.isFree((int)(x+speed),this.getY())) {
			x+=speed;	
		}
		else if(left && World.isFree((int)(x-speed),this.getY())) {
			x-=speed;
		}
		if(up && World.isFree(this.getX(),(int)(y-speed))){
			y-=speed;
		}
		else if(down && World.isFree(this.getX(),(int)(y+speed))){
			y+=speed;
		}
		
		catchCoin();
		
		if(Game.coin_now == Game.coin_count) {
			//Win
			
		}
		
	}
	
	public void catchCoin() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity current = Game.entities.get(i);
			if(current instanceof Coin) {
				if(Entity.isColidding(this,current)) {
					Game.entities.remove(i);
					Game.coin_now++;
					return;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		if(left){
			g.drawImage(sprite_left,this.getX() - Camera.x,this.getY() - Camera.y,null);
		}else if(right){
			g.drawImage(sprite_right,this.getX() - Camera.x,this.getY() - Camera.y,null);
		}else if(up){
			g.drawImage(sprite_up,this.getX() - Camera.x,this.getY() - Camera.y,null);
		}else if(down){
			g.drawImage(sprite_down,this.getX() - Camera.x,this.getY() - Camera.y,null);
		}else {
			super.render(g);
		}
	}

	


}
