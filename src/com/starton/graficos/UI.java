package com.starton.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.starton.main.Game;

public class UI {

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,18));
		g.drawString("Coins: " + Game.coin_now + "/" + Game.coin_count, 30, 30);
	}
	
}
