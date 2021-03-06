package com.snake.Gan;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
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
	 * 院子的行数，默认值为25
	 */
	static final int ROWS = 20;
	/**
	 * 院子的列数,默认值为25
	 */
	static final int COLS = 30;
	/**
	 * 默认每个方格的大小
	 */
	static final int BLOCK_SIZE = 20;
	static Image offScreen;
	private boolean isGame = true;
	private paintThread pt;
	static final Color COLOR = Color.gray;
	Snake firstSnake = new Snake(3, 3, this);
	Snake s2;
	Snake s3;
	Egg nowFood;
	/**
	 * 登录主函数
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
					offScreen = createImage((COLS + 3) * (BLOCK_SIZE), (ROWS + 3) * (BLOCK_SIZE));
					Graphics goff = offScreen.getGraphics();
					goff.setColor(COLOR);
					drawYard(goff);
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
	 * 初始化绘图函数
	 */
	public void paint(Graphics g) {
		// TODO 自动生成的方法存根
		Color c = g.getColor();
		g.setColor(Color.black);
		drawYard(g);
		g.setColor(c);
	}

	/**
	 * 更新函数
	 */
	public void update(Graphics g) {
		if(offScreen != null){
			g.drawImage(offScreen, 0, 0, null);
			offScreen = null;
		}
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
			g.drawString("是否重新开始", 200, 300);
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
		// TODO 自动生成的方法存根
		new Yard().launch();
	}

}
