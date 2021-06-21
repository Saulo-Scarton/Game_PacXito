package com.starton.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.starton.main.Game;
import com.starton.world.AStar;
import com.starton.world.Camera;
import com.starton.world.Vector2i;



public class Enemy extends Entity{
	
	public static String enemyState = "Normal";
	public static boolean enemyFearMode = false;
	public int enemyFrames = 0;
	public int nextTime = /*Entity.rand.nextInt(60*5 - 60*3) + 60*3*/ 300;
	private BufferedImage[] sprites;
	public String enemyDirection;
	
	public Enemy(int x, int y, int width, int height,int speed, BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
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

	public void tick(){
		depth = 0;
		catchEnemy();
		if(enemyState == "Normal") {
			if(path == null || path.size() == 0) {
				Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
				Vector2i end = new Vector2i(((int)(Game.player.x/16)),((int)(Game.player.y/16)));
				path = AStar.findPath(Game.world, start, end);
				
			}
			
			if(new Random().nextInt(100) < 100) {
				followPath(path);
			}
				
			
			if(x % 16 == 0 && y % 16 == 0) {
				if(new Random().nextInt(100) < 100) {
					Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
					Vector2i end = new Vector2i(((int)(Game.player.x/16)),((int)(Game.player.y/16)));
					path = AStar.findPath(Game.world, start, end);
					System.out.println(end.x + " " + end.y);
					if(start.x == end.x) {
						//System.out.println("atack");
					}else if(start.x < end.x){
						//System.out.println("right");
						enemyDirection = "right";
					}else if(start.x > end.x) {
						//System.out.println("left");
						enemyDirection = "left";
					}
					
					if(start.y < end.y){
						//System.out.println("down");
						enemyDirection = "down";
					}else if(start.y > end.y) {
						//System.out.println("up");
						enemyDirection = "up";
					}
				}
			}
			
			
		}else if(enemyState == "Fear"){
			//System.out.println(enemyFrames);

			if(path == null || path.size() == 0) {
				Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
				Vector2i end = new Vector2i(Player.enemyFearX,Player.enemyFearY);
				path = AStar.findPath(Game.world, start, end);
				
			}
			
			if(new Random().nextInt(100) < 100) {
				followPath(path);
			}
				
			
			if(x % 16 == 0 && y % 16 == 0) {
				if(new Random().nextInt(100) < 55) {
					Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
					//Vector2i end = new Vector2i(((int)(Game.player.x/16)),((int)(Game.player.y/16)));
					Vector2i end = new Vector2i(Player.enemyFearX,Player.enemyFearY);
					//System.out.println(end.x + " " + end.y);
					path = AStar.findPath(Game.world, start, end);
					
					if(start.x == end.x) {
						//System.out.println("atack");
					}else if(start.x < end.x){
						//System.out.println("right");
						enemyDirection = "right";
					}else if(start.x > end.x) {
						//System.out.println("left");
						enemyDirection = "left";
					}
					
					if(start.y < end.y){
						//System.out.println("down");
						enemyDirection = "down";
					}else if(start.y > end.y) {
						//System.out.println("up");
						enemyDirection = "up";
					}
				}
			}
			enemyFrames++;
			if(enemyFrames == nextTime) {
				
				//nextTime = Entity.rand.nextInt(60*5 - 60*3) + 60*3;
				nextTime = 300;
				enemyFrames = 0;
				if(enemyState == "Normal") {
					enemyState = "Fear";
				}else {
					enemyState = "Normal";
				}
			}
		}else if(enemyState == "Dead"){
			//System.out.println(enemyFrames);

			if(path == null || path.size() == 0) {
				Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
				Vector2i end = new Vector2i((Game.WIDTH/2)/16,(Game.HEIGHT/2)/16);
				path = AStar.findPath(Game.world, start, end);
				
			}
			
			if(new Random().nextInt(100) < 100) {
				followPath(path);
			}
				
			
			if(x % 16 == 0 && y % 16 == 0) {
				if(new Random().nextInt(100) < 55) {
					Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
					//Vector2i end = new Vector2i(((int)(Game.player.x/16)),((int)(Game.player.y/16)));
					Vector2i end = new Vector2i((Game.WIDTH/2)/16,(Game.HEIGHT/2)/16);
					//System.out.println(end.x + " " + end.y);
					path = AStar.findPath(Game.world, start, end);
					
					if(start.x == end.x) {
						//System.out.println("atack");
					}else if(start.x < end.x){
						//System.out.println("right");
						enemyDirection = "right";
					}else if(start.x > end.x) {
						//System.out.println("left");
						enemyDirection = "left";
					}
					
					if(start.y < end.y){
						//System.out.println("down");
						enemyDirection = "down";
					}else if(start.y > end.y) {
						//System.out.println("up");
						enemyDirection = "up";
					}
				}
			}
			enemyFrames++;
			if(enemyFrames == nextTime) {
				
				//nextTime = Entity.rand.nextInt(60*5 - 60*3) + 60*3;
				nextTime = 600;
				enemyFrames = 0;
				if(enemyState == "Normal") {
					enemyState = "Fear";
				}else {
					enemyState = "Normal";
				}
			}
		}
	}
	
	public void catchEnemy() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity current = Game.entities.get(i);
			if(current instanceof Player) {
				if(Entity.isColidding(this,current)) {
					if(Enemy.enemyState == "Fear") {
						//Game.entities.remove(this);
						enemyState = "Dead";
					}else if (Enemy.enemyState == "Normal") {
						System.out.println("GAME OVER");
					}
					return;
				}
			}
		}
	}

	
	public void render(Graphics g) {
		if(enemyState == "Normal") {
			if(enemyDirection == "left") {
				g.drawImage(sprites[0], this.getX() - Camera.x,this.getY() - Camera.y,null);
			}else if(enemyDirection == "right") {
				g.drawImage(sprites[1], this.getX() - Camera.x,this.getY() - Camera.y,null);
			}else if(enemyDirection == "up") {
				g.drawImage(sprites[2], this.getX() - Camera.x,this.getY() - Camera.y,null);
			}else if(enemyDirection == "down") {
				g.drawImage(sprites[3], this.getX() - Camera.x,this.getY() - Camera.y,null);
			}else {
				super.render(g);
			}	
		}else if(enemyState == "Fear"){
			if(enemyDirection == "left") {
				g.drawImage(sprites[4], this.getX() - Camera.x,this.getY() - Camera.y,null);
			}else if(enemyDirection == "right") {
				g.drawImage(sprites[5], this.getX() - Camera.x,this.getY() - Camera.y,null);
			}else if(enemyDirection == "up") {
				g.drawImage(sprites[6], this.getX() - Camera.x,this.getY() - Camera.y,null);
			}else if(enemyDirection == "down") {
				g.drawImage(sprites[7], this.getX() - Camera.x,this.getY() - Camera.y,null);
			}else {
				super.render(g);
			}	
		}else if(enemyState == "Dead"){
			if(enemyDirection == "left") {
				g.drawImage(sprites[8], this.getX() - Camera.x,this.getY() - Camera.y,null);
			}else if(enemyDirection == "right") {
				g.drawImage(sprites[9], this.getX() - Camera.x,this.getY() - Camera.y,null);
			}else if(enemyDirection == "up") {
				g.drawImage(sprites[10], this.getX() - Camera.x,this.getY() - Camera.y,null);
			}else if(enemyDirection == "down") {
				g.drawImage(sprites[11], this.getX() - Camera.x,this.getY() - Camera.y,null);
			}else {
				super.render(g);
			}	
		}
			
	}
	
	
}
