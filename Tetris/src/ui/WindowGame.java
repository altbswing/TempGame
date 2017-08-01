package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import entity.Node;

/**
 * 游戏主窗口
 * */
public class WindowGame extends Window{
	
	public WindowGame() {
		super(402,24,336,592);
	}
	
	public void drawTertis(int type,int fangXiang,Graphics g){
		
	}
	
	public void windowRepaint(Graphics g){
		this.createWindow(g);
		Image img = new ImageIcon("Graphics/sign/title.png").getImage();
		this.drawImg(img, 0, 0, g);
	}

	public void windowRepaint(boolean isLose, boolean isPause,boolean[][] map, int lv, 
			Node[] tetris, int type, boolean shadowOn,Graphics g) {
		this.createWindow(g);
		if(shadowOn){	//投影
			int [] ary = new int[4];
			for (int i = 0; i < tetris.length; i++) {
				ary[i] = tetris[i].getX();
			}
			ary = bubleSort(ary);
			int x = ary[0];
			int w = (Math.abs(ary[0]-ary[3])+1)*32;
			g.drawImage(hin, xIn+x*32, this.yIn, w, 576, null);
		}
		
		for (int i = 0; i < tetris.length; i++) { // 显示活动块
			this.drawSubImg(
			rectAct, tetris[i].getX() * 32,tetris[i].getY() * 32, 
				isLose?8:type+1, g);
		}
		
		for (int i = 0; i < 10; i++) { // 显示地图
			for (int j = 0; j < 18; j++) {
				if (map[i][j]) {
					this.drawSubImg(rectAct, i * 32, j * 32, 
							isLose?8:(lv==1?0:(lv-2)%7+1), g);
				}
			}
		}
		if(isPause)
			this.drawImg(pause, 32, 210, g);
		}
	}
	
//	2.冒泡排序
	public int[] bubleSort(int[] ary){
		for(int i=0; i<ary.length; i++){
			for(int j= 0; j<ary.length-i-1; j++){
				if(ary[j] > ary[j+1]){
					int temp = ary[j];
					ary[j] = ary[j+1];
					ary[j+1] = temp;
				}
			}
		}
		return ary;
	}
}
