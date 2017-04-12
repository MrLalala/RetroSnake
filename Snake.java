package com.snake.Gan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Snake {
	/**
	 * 起始长度
	 */
	private int length = 1;
	/**
	 * 起始位置
	 */
	private int x = 5,y = 10;
	
	private enum Direction { L,U,R,D };
	
	private Direction dir = Direction.R;
	
	public void keyPressedEvent(KeyEvent e){
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			dir = Direction.R;
			break;
		case KeyEvent.VK_LEFT:
			dir = Direction.L;
			break;
		case KeyEvent.VK_DOWN:
			dir = Direction.D;
			break;
		case KeyEvent.VK_UP:
			dir = Direction.U;
			break;
		}
	}
	
	public void keyReleasedEvent(KeyEvent e){
		
	}
	
	public void move(){
		switch (dir) {
		case R:
			x +=1;
			break;
		case L:
			x -=1;
			break;
		case D:
			y +=1;
			break;
		case U:
			y -=1;
			break;
		}
	}
	
	public void draw(Graphics g){
		Color c = g.getColor();
		move();
		g.setColor(Color.RED);
		g.fillRect(Yard.BLOCK_SIZE*(x-1), Yard.BLOCK_SIZE*(y-2), Yard.BLOCK_SIZE,Yard.BLOCK_SIZE);
		g.setColor(c);
	}
}
