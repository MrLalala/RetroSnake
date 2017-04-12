package com.snake.Gan;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
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
	private static final int BLOCK_SIZE =20;
	
	Image offScreen = null;
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
		g.setColor(c);
	}
	/**
	 * 
	 */
//	public void update(Graphics g){
//		
//	}
//	
//	/**
//	 * 
//	 * @author Gan
//	 *
//	 */
//
//	private class paintThread implements Runnable{
//		public void run() {
//			try{
//				Thread.sleep(50);
//			}catch (Exception e) {
//				System.out.print(e.getMessage());
//			}
//		}
//	}
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		new Yard().launch();
	}

}
