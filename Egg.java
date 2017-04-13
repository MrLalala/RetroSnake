package com.snake.Gan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Egg {
	
	private int x,y;
	
	private Yard yard;
	
	private Random rXY = new Random();
	
	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public Egg(int x, int y) {
		this.x = x;
		this.y = y+1;
	}
	public Egg(int x, int y,Yard yard) {
		this(x, y);
		this.yard = yard;
	}
	
	private boolean live = true;
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public void draw(Graphics g){
		if(!live){
			int x,y;
			do{
				x = rXY.nextInt(Yard.COLS)+1;
				y = rXY.nextInt(Yard.ROWS)+1;
			}while(!reuse(x, y,g));
			return;
		}
		Color color = g.getColor();
		g.setColor(Color.green);
		g.fillRect(x*Yard.BLOCK_SIZE, y*Yard.BLOCK_SIZE,Yard.BLOCK_SIZE, Yard.BLOCK_SIZE);
		g.setColor(color);
	}
	
	public boolean reuse(int x,int y,Graphics g){
		Egg temp = new Egg(x,y,this.yard);
		for(int i = 0;i<yard.body.size();i++){
			if(temp.getRect().intersects(yard.body.get(i).getRect())){
				return false;
			}
		}
		yard.nowFood = temp;
		return true;
	}
	
	public Rectangle getRect(){
		return new Rectangle(x*Yard.BLOCK_SIZE, y*Yard.BLOCK_SIZE, Yard.BLOCK_SIZE, Yard.BLOCK_SIZE);
	}
}
