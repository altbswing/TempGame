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
		this.drawNumberLP(124, 16, point, YELLOW, g);	//��ʾ����
		this.drawNumberLP(124, 72, rmLine, YELLOW, g);	//��ʾ�ܹ�����������
		while(rmLine>=20){
			rmLine -= 20;
		}
		double p = rmLine/20.0;
		this.darwImgRect(16,126,242,p,false,g);
		this.drawString("��һ��", Color.WHITE, new Font("����", 0, 18),16, 139, g);
	}
}
