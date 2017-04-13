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

	private boolean isGame = true;
	private paintThread pt;
	static final Color COLOR = Color.gray;
	Snake firstSnake = new Snake(3, 3, this);
	Snake s2;
	Snake s3;
	Egg nowFood;

	/**
	 * ��¼������
	 */
	public void launch() {
		this.setLocation(100, 100);
		this.setSize((COLS + 3) * (BLOCK_SIZE), (ROWS + 3) * (BLOCK_SIZE));
		this.setVisible(true);
		firstSnake = new Snake(3, 3, this);
		s2 = new Snake(2, 3);
		s3 = new Snake(1, 3);
		nowFood = new Egg(6, 3, this);
		firstSnake.lSnake = s2;
		s2.fSnake = firstSnake;
		s2.lSnake = s3;
		s3.fSnake = s2;
		body.add(s3);
		body.add(s2);
		body.add(firstSnake);
		this.setBackground(COLOR);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				firstSnake.keyPressedEvent(e);
				if (!isGame && (e.getKeyCode() == KeyEvent.VK_F2)) {
					isGame = true;
					reBegin();
					setBackground(COLOR);
					repaint();
				}
			}
		});
		pt = new paintThread();
		pt.start();
	}

	public void reBegin() {
		firstSnake = new Snake(3, 3, this);
		s2 = new Snake(2, 3);
		s3 = new Snake(1, 3);
		nowFood = new Egg(6, 3, this);
		firstSnake.lSnake = s2;
		s2.fSnake = firstSnake;
		s2.lSnake = s3;
		s3.fSnake = s2;
		body.add(s3);
		body.add(s2);
		body.add(firstSnake);
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
		if (!isGame)
			return;
		firstSnake.draw(g);
		if (firstSnake.eatFood(nowFood))
			return;
		Snake temp = firstSnake;
		while ((temp = temp.lSnake) != null) {
			temp.draw(g);
			temp.setHeadX(temp.fSnake.getTailX());
			temp.setHeadY(temp.fSnake.getTailY());
		}
		nowFood.draw(g);
		drawYard(g);
		if (false == (isGame = !firstSnake.testCollision())) {
			g.drawString("�Ƿ����¿�ʼ", 200, 300);
			body.removeAll(body);
		}
	}

	public void drawYard(Graphics g) {
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

	private class paintThread extends Thread {
		boolean flag = true;

		public void run() {
			while (flag) {
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
