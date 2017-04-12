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
	
	ArrayList<Snake>body = new ArrayList<>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	static final Color COLOR = Color.gray;
	Snake s = new Snake(5,10);
	Egg egg = new Egg(3, 4);
//	Image offScreen = null;
	/**
	 * ��¼������
	 */
	public void launch(){
		this.setLocation(100,100);
		this.setSize((COLS+3)*(BLOCK_SIZE), (ROWS+3)*(BLOCK_SIZE));
		this.setVisible(true);
		body.add(s);
		this.setBackground(COLOR);
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
	 * ��ʼ����ͼ����
	 */
	public void paint(Graphics g) {
		// TODO �Զ����ɵķ������
		Color c = g.getColor();
		g.setColor(Color.black);
		for(int i = 1;i <= ROWS+2;i++)
			g.drawLine(BLOCK_SIZE, i*BLOCK_SIZE, (COLS+2)*(BLOCK_SIZE), i*BLOCK_SIZE);
		for(int i = 1;i <= COLS+2;i++)
			g.drawLine(BLOCK_SIZE*i, 2*BLOCK_SIZE, i*BLOCK_SIZE, (2+ROWS)*(BLOCK_SIZE));
		g.setColor(c);
	}
	/**
	 * ���º���
	 */
	public void update(Graphics g){
		s.draw(g);
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
