package com.starton.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.starton.main.Game;
import com.starton.world.Camera;
import com.starton.world.World;

public class Player extends Entity{
	
	public boolean right,up,left,down;
	public String playerNow;
	
	public BufferedImage sprite_left, sprite_right, sprite_up, sprite_down;
	public static int enemyFearX, enemyFearY;
	
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		sprite_left = Game.spritesheet.getSprite(48, 0, 16, 16);
		sprite_right = Game.spritesheet.getSprite(64, 0, 16, 16);
		sprite_up = Game.spritesheet.getSprite(80, 0, 16, 16);
		sprite_down = Game.spritesheet.getSprite(96, 0, 16, 16);
	}
	
	public void tick(){
		depth = 1;
		//System.out.println(x + " " + y);
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
		catchPower();
		
		if(this.getX()+8 < Game.WIDTH/2 && this.getY()+8 < Game.HEIGHT/2) {
			//UP LEFT
			//System.out.println("UP LEFT");
			enemyFearX = Game.WIDTH/16 -2;
			enemyFearY = Game.HEIGHT/16 -2;
		}else if(this.getX()+8 > Game.WIDTH/2 && this.getY()+8 < Game.HEIGHT/2){
			//UP RIGHT
			//System.out.println("UP RIGHT");
			enemyFearX = 1;
			enemyFearY = Game.HEIGHT/16 -2;
		}else if(this.getX()+8 < Game.WIDTH/2 && this.getY()+8 > Game.HEIGHT/2){
			//DOWN LEFT
			//System.out.println("DOWN LEFT");
			enemyFearX = Game.HEIGHT/16 -2;
			enemyFearY = 1;
		}else if(this.getX()+8 > Game.WIDTH/2 && this.getY()+8 > Game.HEIGHT/2){
			//DOWN RIGHT
			//System.out.println("DOWN RIGHT");
			enemyFearX = 1;
			enemyFearY = 1;
		}else if(this.getX()+8 == Game.WIDTH/2 && this.getY()+8 < Game.HEIGHT/2){
			//UP
			//System.out.println("UP");
		}else if(this.getX()+8 < Game.WIDTH/2 && this.getY()+8 == Game.HEIGHT/2){
			//LEFT
			//System.out.println("LEFT");
		}else if(this.getX()+8 > Game.WIDTH/2 && this.getY()+8 == Game.HEIGHT/2){
			//RIGHT
			//System.out.println("RIGHT");
		}else if(this.getX()+8 == Game.WIDTH/2 && this.getY()+8 > Game.HEIGHT/2){
			//DOWN
			//System.out.println("DOWN");
		}else {
			//System.out.println("CENTER");
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
	
	public void catchPower() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity current = Game.entities.get(i);
			if(current instanceof Power) {
				if(Entity.isColidding(this,current)) {
					Game.entities.remove(i);
					Enemy.enemyState = "Fear";
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
