package com.snake.Gan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Egg {
	
	private int x,y;
	
	

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public Egg(int x, int y) {
		this.x = x;
		this.y = y+1;
	}
	
	private boolean live = true;
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public void draw(Graphics g){
		if(!live)
			return;
		Color color = g.getColor();
		g.setColor(Color.green);
		g.fillRect(x*Yard.BLOCK_SIZE, y*Yard.BLOCK_SIZE,Yard.BLOCK_SIZE, Yard.BLOCK_SIZE);
		g.setColor(color);
	}
	
	public Rectangle getRect(){
		return new Rectangle(x*Yard.BLOCK_SIZE, y*Yard.BLOCK_SIZE, Yard.BLOCK_SIZE, Yard.BLOCK_SIZE);
	}
}
