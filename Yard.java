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
	private static final int BLOCK_SIZE =20;
	
	Image offScreen = null;
	/**
	 * 登录主函数
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
	 * 绘图函数
	 */
	public void paint(Graphics g) {
		// TODO 自动生成的方法存根
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
		// TODO 自动生成的方法存根
		new Yard().launch();
	}

}
