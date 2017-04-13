package com.snake.Gan;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * @author Gan
 * 
 */
public class Yard extends Frame {

	
	ArrayList<Snake> body = new ArrayList<>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Ժ�ӵ�������Ĭ��ֵΪ25
	 */
	static final int ROWS = 20;
	/**
	 * Ժ�ӵ�����,Ĭ��ֵΪ25
	 */
	static final int COLS = 30;
	/**
	 * Ĭ��ÿ������Ĵ�С
	 */
	static final int BLOCK_SIZE = 20;
	Snake firstSnake = null;
	static final Color COLOR = Color.gray;
	Snake s = new Snake(3, 3, this);
	Snake s2 = new Snake(2, 3);
	Snake s3 = new Snake(1, 3);

	Egg egg = new Egg(6, 3,this);
	Egg nowFood = egg;
	// Image offScreen = null;
	/**
	 * ��¼������
	 */
	public void launch() {
		this.setLocation(100, 100);
		this.setSize((COLS + 3) * (BLOCK_SIZE), (ROWS + 3) * (BLOCK_SIZE));
		this.setVisible(true);
		s.lSnake = s2;
		s2.fSnake = s;
//		s2.lSnake = s3;
//		s3.fSnake = s2;
		//body.add(s3);
		body.add(s2);body.add(s);
		firstSnake = s;
		this.setBackground(COLOR);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				firstSnake.keyPressedEvent(e);
			}
		});
		Thread thread = new Thread(new paintThread());
		thread.start();
	}

	/**
	 * ��ʼ����ͼ����
	 */
	public void paint(Graphics g) {
		// TODO �Զ����ɵķ������
		Color c = g.getColor();
		g.setColor(Color.black);
		drawYard(g);
		g.setColor(c);
	}

	/**
	 * ���º���
	 */
	public void update(Graphics g) {

		
		firstSnake.draw(g);
		if(firstSnake.eatFood(nowFood))
			return;
		Snake temp = firstSnake;
		while ((temp = temp.lSnake) != null) {
			temp.draw(g);
			temp.setHeadX(temp.fSnake.getTailX());
			temp.setHeadY(temp.fSnake.getTailY());
		}
		nowFood.draw(g);
		drawYard(g);
		}
	
	public void drawYard(Graphics g){
		for (int i = 1; i <= ROWS + 2; i++)
			g.drawLine(BLOCK_SIZE, i * BLOCK_SIZE, (COLS + 2) * (BLOCK_SIZE), i * BLOCK_SIZE);
		for (int i = 1; i <= COLS + 2; i++)
			g.drawLine(BLOCK_SIZE * i, 2 * BLOCK_SIZE, i * BLOCK_SIZE, (2 + ROWS) * (BLOCK_SIZE));
	
	}
	/**
	 * 
	 * @author Gan
	 *
	 */

	private class paintThread implements Runnable {
		public void run() {
			while (true) {
				try {
					Thread.sleep(300);
				} catch (Exception e) {
					System.out.print(e.getMessage());
				}
				repaint();
			}
		}
	}

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		new Yard().launch();
	}

}
