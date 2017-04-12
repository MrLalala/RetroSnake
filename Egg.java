package com.snake.Gan;

import java.awt.Color;
import java.awt.Graphics;

public class Egg {
	
	private int x,y;

	public Egg(int x, int y) {
		this.x = x;
		this.y = y+1;
	}
	
	public void draw(Graphics g){
		Color color = g.getColor();
		g.setColor(Color.green);
		g.fillRect(x*Yard.BLOCK_SIZE, y*Yard.BLOCK_SIZE,Yard.BLOCK_SIZE, Yard.BLOCK_SIZE);
		g.setColor(color);
	}
	
}
