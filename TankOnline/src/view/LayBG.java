package view;

import java.awt.Graphics;

import java.awt.Image;

import javax.swing.ImageIcon;

import message.Wind;


/**
 * 背景层 by小翼
 * */
public class LayBG extends Lay {
	
	private static Image BOT = new ImageIcon("Graphics/window/bot.png").getImage();
	private static Image GROUND = new ImageIcon("Graphics/picture/ground.png").getImage();
	
	public LayBG() {
		super(0,0);
	}
	
	public void showLay(Wind w, Image wind, Graphics g){
		int x = w.getLocation();
		for (int i = 0; i < 3; i++) {
			g.drawImage(wind, x+560*i, 0, null);
		}
		g.drawImage(GROUND, 0, 416, null);
		g.drawImage(BOT, 0, 416, null);
	}
}
