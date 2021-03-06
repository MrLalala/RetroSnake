package com.snake.Gan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Snake {

	Snake fSnake = null;
	Snake lSnake = null;
	/**
	 * 起始位置
	 */
	private boolean eatFlag = false;
	public void setEatFlag(boolean eatFlag) {
		this.eatFlag = eatFlag;
	}

	private int headX,headY;

	public void setHeadY(int headY) {
		this.headY = headY;
	}

	public void setHeadX(int headX) {
		this.headX = headX;
	}

	/**
	 * 尾部位置
	 */
	private int tailX,tailY;
	public int getTailX() {
		return tailX;
	}

	public int getTailY() {
		return tailY;
	}

	/**
	 * 移动标记
	 */

	private Yard yard = null;
	/**
	 * 新的蛇的构造函数
	 * @param x 横坐标
	 * @param y	纵坐标
	 */
	public Snake(int x, int y) {
		this.tailX = this.headX = x;
		this.tailY = this.headY = y+1;
	}

	public Snake(int x, int y,Yard yard){
		this(x, y);
		this.yard = yard;
	}
	public Snake(int x, int y,Yard yard,Direction dir){
		this(x, y, yard);
		this.dir = dir;
	}
	Direction dir = Direction.R;
	Direction oldDir = dir;
	
	public void keyPressedEvent(KeyEvent e){
		if(this == yard.firstSnake){
			oldDir = dir;
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
	/**
	 * 
	 * @param g
	 */
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillRect(Yard.BLOCK_SIZE*headX, Yard.BLOCK_SIZE*headY, Yard.BLOCK_SIZE,Yard.BLOCK_SIZE);
		if (this.lSnake == null&& !eatFlag ) {
			g.setColor(Yard.COLOR);
			g.fillRect(tailX * Yard.BLOCK_SIZE, Yard.BLOCK_SIZE * tailY, Yard.BLOCK_SIZE, Yard.BLOCK_SIZE);
		}
		if(!eatFlag)
			move();
		else{
			if(this.fSnake == null){
				for(int i = 0;i<yard.body.size();i++){
					yard.body.get(i).setEatFlag(true);
				}
			}
			eatFlag = false;
		}
		g.setColor(c);
	}
	/**
	 * 
	 * @return
	 */
	public Rectangle getRect(){
		return new Rectangle(Yard.BLOCK_SIZE*headX, Yard.BLOCK_SIZE*headY,Yard.BLOCK_SIZE ,Yard.BLOCK_SIZE);
	}
	
	public boolean eatFood(Egg e){
		if(this.fSnake == null && e.isLive() &&this.getRect().intersects(e.getRect()) ){
			e.setLive(false);
			yard.body.add(new Snake(e.getX(), e.getY()-1,this.yard,this.dir));
			yard.firstSnake = yard.body.get(yard.body.size()-1);
			yard.firstSnake.lSnake = this;
			this.fSnake = yard.firstSnake;
			eatFlag = true;
			return true;
		}
		else return false;
	}
	
	public boolean testCollision(){
		for(int i = 0;i<yard.body.size();i++){
			if(yard.body.get(i)!=this&&this.getRect().intersects(yard.body.get(i).getRect()))
				return true;
		}
		return false;
	}
}
