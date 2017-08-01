package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class WindowPoint extends Window{
	
	private Image pointText = new ImageIcon("Graphics/string/point.png").getImage();
	private Image rmlineText = new ImageIcon("Graphics/string/rmline.png").getImage();
	
	public WindowPoint() {
		super(RA_X,276,A_WIDTH,170);
	}
	
	public void windowRepaint(int point, int rmLine, Graphics g){
		this.createWindow(g);
		this.drawImg(pointText, 8, 8, g);
		this.drawImg(rmlineText, 8, 64, g);
		this.drawNumberLP(124, 16, point, YELLOW, g);	//显示分数
		this.drawNumberLP(124, 72, rmLine, YELLOW, g);	//显示总共消除多少行
		while(rmLine>=20){
			rmLine -= 20;
		}
		double p = rmLine/20.0;
		this.darwImgRect(16,126,242,p,false,g);
		this.drawString("下一级", Color.WHITE, new Font("黑体", 0, 18),16, 139, g);
	}
}
