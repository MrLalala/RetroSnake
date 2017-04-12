package com.snake.Gan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {
	/**
	 * ��ʼ����
	 */
	private int length = 1;
	/**
	 * ��ʼλ��
	 */
	private int headX,headY;
	/**
	 * ��������
	 */
	private ArrayList<Point> body = new ArrayList<>();
	/**
	 * β��λ��
	 */
	private int tailX,tailY;
	/**
	 * �µ��ߵĹ��캯��
	 * @param x ������
	 * @param y	������
	 */
	public Snake(int x, int y) {
		this.tailX = this.headX = x;
		this.tailY = this.headY = y+1;
	}

	private enum Direction { L,U,R,D };
	
	private Direction dir = Direction.R;
	
	public void keyPressedEvent(KeyEvent e){
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			if(dir != Direction.L)
				dir = Direction.R;
			break;
		case KeyEvent.VK_LEFT:
			if(dir != Direction.R)
				dir = Direction.L;
			break;
		case KeyEvent.VK_DOWN:
			if(dir != Direction.U)
				dir = Direction.D;
			break;
		case KeyEvent.VK_UP:
			if(dir != Direction.D)
				dir = Direction.U;
			break;
		}
	}
	
	public void move(){
		tailX = this.headX;
		tailY = this.headY;
		switch (dir) {
		case R:
			headX +=1;
			break;
		case L:
			headX -=1;
			break;
		case D:
			headY +=1;
			break;
		case U:
			headY -=1;
			break;
		}
	}
	
	public void draw(Graphics g){
		Color c = g.getColor();
		move();
		g.setColor(Color.RED);
		g.fillRect(Yard.BLOCK_SIZE*headX, Yard.BLOCK_SIZE*headY, Yard.BLOCK_SIZE,Yard.BLOCK_SIZE);
		g.setColor(Yard.COLOR);
		g.fillRect(tailX*Yard.BLOCK_SIZE, Yard.BLOCK_SIZE*tailY, Yard.BLOCK_SIZE, Yard.BLOCK_SIZE);
		g.setColor(c);
	}
}
