package com.snake.Gan;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.xml.ws.handler.MessageContext.Scope;

/**
 * @author Gan
 * 
 */
public class Yard extends Frame {
	
	ArrayList<Snake>body = new ArrayList<>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 院子的行数，默认值为25
	 */
	private static final int ROWS = 30;
	/**
	 * 院子的列数,默认值为25
	 */
	private static final int COLS = 40;
	/**
	 * 默认每个方格的大小
	 */
	static final int BLOCK_SIZE =20;
	
	static final Color COLOR = Color.gray;
	Snake s = new Snake(5,10);
	Snake s2 = new Snake(4, 10);
	Egg egg = new Egg(3, 4);
//	Image offScreen = null;
	/**
	 * 登录主函数
	 */
	public void launch(){
		this.setLocation(100,100);
		this.setSize((COLS+3)*(BLOCK_SIZE), (ROWS+3)*(BLOCK_SIZE));
		this.setVisible(true);
		body.add(s);
		s.lSnake = s2;
		s2.fSnake = s;
		this.setBackground(COLOR);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				s.keyPressedEvent(e);
			}
		});
		Thread thread = new Thread(new paintThread());
		thread.start();
	}
	
	/**
	 * 初始化绘图函数
	 */
	public void paint(Graphics g) {
		// TODO 自动生成的方法存根
		Color c = g.getColor();
		g.setColor(Color.black);
		for(int i = 1;i <= ROWS+2;i++)
			g.drawLine(BLOCK_SIZE, i*BLOCK_SIZE, (COLS+2)*(BLOCK_SIZE), i*BLOCK_SIZE);
		for(int i = 1;i <= COLS+2;i++)
			g.drawLine(BLOCK_SIZE*i, 2*BLOCK_SIZE, i*BLOCK_SIZE, (2+ROWS)*(BLOCK_SIZE));
		g.setColor(c);
	}
	/**
	 * 更新函数
	 */
	public void update(Graphics g){
		//s.draw(g);
		//s.eatFood(egg);
//		Snake temp = s;
//		while(temp.lSnake != null){
//			temp.lSnake.dir = temp.oldDir;
//			temp = temp.lSnake;
//			temp.draw(g);
//		}
		s.draw(g);
		s2.draw(g);
		s2.setHeadX(s.getTailX());
		s2.setHeadY(s.getTailY());
		egg.draw(g);
		for(int i = 1;i <= ROWS+1;i++)
			g.drawLine(BLOCK_SIZE, i*BLOCK_SIZE, COLS*(BLOCK_SIZE), i*BLOCK_SIZE);
		for(int i = 1;i <= COLS+1;i++)
			g.drawLine(BLOCK_SIZE*i, 2*BLOCK_SIZE, i*BLOCK_SIZE, ROWS*(BLOCK_SIZE));
	}
	
	/**
	 * 
	 * @author Gan
	 *
	 */

	private class paintThread implements Runnable{
		public void run() {
			while (true) {
				try {
					Thread.sleep(40);
				} catch (Exception e) {
					System.out.print(e.getMessage());
				}
				repaint();
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new Yard().launch();
	}

}
