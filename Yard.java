package com.snake.Gan;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Gan
 * 
 */
public class Yard extends Frame {
	
	
	/**
	 * Ժ�ӵ�������Ĭ��ֵΪ25
	 */
	private static final int ROWS = 30;
	/**
	 * Ժ�ӵ�����,Ĭ��ֵΪ25
	 */
	private static final int COLS = 40;
	/**
	 * Ĭ��ÿ������Ĵ�С
	 */
	static final int BLOCK_SIZE =20;
	
	
	Snake s = new Snake();
//	Image offScreen = null;
	/**
	 * ��¼������
	 */
	public void launch(){
		this.setLocation(100,100);
		this.setSize((COLS+1)*(BLOCK_SIZE), (ROWS+1)*(BLOCK_SIZE));
		this.setVisible(true);
		this.setBackground(Color.GRAY);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e){
				
			}
			public void keyPressed(KeyEvent e) {
				s.keyPressedEvent(e);
			}
		});
		Thread thread = new Thread(new paintThread());
		thread.start();
	}
	
	/**
	 * ��ͼ����
	 */
	public void paint(Graphics g) {
		// TODO �Զ����ɵķ������
		Color c = g.getColor();
		g.setColor(Color.black);
		for(int i = 1;i <= ROWS;i++)
			g.drawLine(BLOCK_SIZE, i*BLOCK_SIZE, COLS*(BLOCK_SIZE), i*BLOCK_SIZE);
		for(int i = 1;i <= COLS;i++)
			g.drawLine(BLOCK_SIZE*i, 2*BLOCK_SIZE, i*BLOCK_SIZE, ROWS*(BLOCK_SIZE));
		s.draw(g);
		g.setColor(c);
	}
	/**
	 * 
	 */
	public void update(Graphics g){
		s.draw(g);
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
					Thread.sleep(400);
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
