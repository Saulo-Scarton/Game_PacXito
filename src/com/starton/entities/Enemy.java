package com.starton.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.starton.main.Game;
import com.starton.world.AStar;
import com.starton.world.Vector2i;



public class Enemy extends Entity{
	
	public boolean enemyFearMode = false;
	public int enemyFrames = 0;
	public int nextTime = Entity.rand.nextInt(60*5 - 60*3) + 60*3;

	public Enemy(int x, int y, int width, int height,int speed, BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
	}

	public void tick(){
		depth = 0;
		if(enemyFearMode == false) {
			if(path == null || path.size() == 0) {
				Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
				Vector2i end = new Vector2i(((int)(Game.player.x/16)),((int)(Game.player.y/16)));
				path = AStar.findPath(Game.world, start, end);
			}
			
			if(new Random().nextInt(100) < 100)
				followPath(path);
			
			if(x % 16 == 0 && y % 16 == 0) {
				if(new Random().nextInt(100) < 10) {
					Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
					Vector2i end = new Vector2i(((int)(Game.player.x/16)),((int)(Game.player.y/16)));
					path = AStar.findPath(Game.world, start, end);
				}
			}
		}
			enemyFrames++;
			if(enemyFrames == nextTime) {
				nextTime = Entity.rand.nextInt(60*5 - 60*3) + 60*3;
				enemyFrames = 0;
				if(enemyFearMode == false) {
					System.out.println("Modo fear");
					enemyFearMode = true;
				}else {
					enemyFearMode = false;
				}
			}
		
	}
	

	
	public void render(Graphics g) {
		super.render(g);
	}
	
	
}
