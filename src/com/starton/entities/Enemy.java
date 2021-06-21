package com.starton.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.starton.main.Game;
import com.starton.main.Sound;
import com.starton.world.AStar;
import com.starton.world.Camera;
import com.starton.world.Vector2i;
import com.starton.world.World;

public class Enemy extends Entity{
	
	//private double speed = 0.1;
	
	private int frames = 0,maxFrames = 17 /*max frames para reduzir a velocidade da animação do inimigo*/,index = 0,maxIndex = 1;
	
	private BufferedImage[] sprites;
	
	private int count = 0, maxCount = 300;
	//private int life = 10, ChanceOfWalk = 80;
	public static String enemyState = "Normal";
	
	public boolean fear = false, dead = false;
	private int damageFrames = 10, damageCurrent = 0;
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height,2,sprite);
		sprites = new BufferedImage[12];
		//normal
		sprites[0] = Game.spritesheet.getSprite(48, 32, 16, 16);
		sprites[1] = Game.spritesheet.getSprite(64, 32, 16, 16);
		sprites[2] = Game.spritesheet.getSprite(80, 32, 16, 16);
		sprites[3] = Game.spritesheet.getSprite(96, 32, 16, 16);
		//fear
		sprites[4] = Game.spritesheet.getSprite(48, 48, 16, 16);
		sprites[5] = Game.spritesheet.getSprite(64, 48, 16, 16);
		sprites[6] = Game.spritesheet.getSprite(80, 48, 16, 16);
		sprites[7] = Game.spritesheet.getSprite(96, 48, 16, 16);
		//dead
		sprites[8] = Game.spritesheet.getSprite(48, 64, 16, 16);
		sprites[9] = Game.spritesheet.getSprite(64, 64, 16, 16);
		sprites[10] = Game.spritesheet.getSprite(80, 64, 16, 16);
		sprites[11] = Game.spritesheet.getSprite(96, 64, 16, 16);
	}

	
	public void tick() {
		//Algoritimo de perseguição 2 (A*)
		depth = 0;
		if(!fear) {
			if(!isColiddingWithPlayer()) {
				if(path == null || path.size() == 0) {
					Vector2i start = new Vector2i((int)(x/16),(int)(y/16));
					Vector2i end = new Vector2i((int)(Game.player.x/16),(int)(Game.player.y/16));
					path = AStar.findPath(Game.world, start, end);
				}
				followPath(path);
				if(x % 16 == 0 && y % 16 == 0) {
					Vector2i start = new Vector2i((int)(x/16),(int)(y/16));
					Vector2i end = new Vector2i((int)(Game.player.x/16),(int)(Game.player.y/16));
					path = AStar.findPath(Game.world, start, end);

				}
			}else{
				System.out.println("GAME OVER");
				fear = true;
			}
		}else {
			if(path == null || path.size() == 0) {
				Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
				Vector2i end = new Vector2i(Player.enemyFearX,Player.enemyFearY);
				path = AStar.findPath(Game.world, start, end);
			}
			followPath(path);
			if(x % 16 == 0 && y % 16 == 0) {
				if(new Random().nextInt(100) < 55) {
					Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
					Vector2i end = new Vector2i(Player.enemyFearX,Player.enemyFearY);
					path = AStar.findPath(Game.world, start, end);
					
					if(start.x == end.x) {
						//System.out.println("atack");
					}else if(start.x < end.x){
						//System.out.println("right");
						//enemyDirection = "right";
					}else if(start.x > end.x) {
						//System.out.println("left");
						//enemyDirection = "left";
					}
					
					if(start.y < end.y){
						//System.out.println("down");
						//enemyDirection = "down";
					}else if(start.y > end.y) {
						//System.out.println("up");
						//enemyDirection = "up";
					}
				}
			}
			
			count++;
			if(count == maxCount) {
				fear = false;
				count = 0;
			}else {
				//isDamaged = true;
				System.out.println(count);
			}
			
		}
		
		
		
		if(dead) {
				if(path == null || path.size() == 0) {
					Vector2i start = new Vector2i((int)(x/16),(int)(y/16));
					Vector2i end = new Vector2i((Game.WIDTH/2)/16,(Game.HEIGHT/2)/16);
					path = AStar.findPath(Game.world, start, end);
				}
				
				followPath(path);
				if(x % 16 == 0 && y % 16 == 0) {
					Vector2i start = new Vector2i((int)(x/16),(int)(y/16));
					Vector2i end = new Vector2i((Game.WIDTH/2)/16,(Game.HEIGHT/2)/16);
					path = AStar.findPath(Game.world, start, end);
					frames++;
					if(frames == maxFrames) {
						frames = 0;
						index++;
						if(index > maxIndex) {
							index = 0;
						}
					}
				}
			count++;
			if(count == maxCount) {
				dead = false;
				count = 0;
			}else {
				//isDamaged = true;
				System.out.println(count);
			}
		}

		

	}
	
	public void destroySelf() {
		Game.enemies.remove(this);
		Game.entities.remove(this);
	}
	
	public boolean isColiddingWithPlayer() { //testa colisão do inimigo com o player
		Rectangle enemyCurrent = new Rectangle(this.getX(),this.getY(),16,16);
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),16,16);
		
		return enemyCurrent.intersects(player);
	}
	

	
	public void render(Graphics g) { //trocar o tamanho da mascara (para colidirem com o tamanho correto)
		if(!fear)
			g.drawImage(sprites[index], this.getX() - Camera.x,this.getY() - Camera.y,null);
		else
			g.drawImage(sprites[8], this.getX() - Camera.x,this.getY() - Camera.y,null);
	}
	
}
