package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class WindowLevel extends Window {
	
	private Image levelText = new ImageIcon("Graphics/string/level.png").getImage();
	
	public WindowLevel() {
		super(RA_X+160,130,114,128);
	}
	
	public void windowRepaint(int level, Graphics g){
		this.createWindow(g);
		this.drawImg(levelText, 15, 16, g);
		this.drawNumberLP(-36, 70, level, 2, g);
	}
}
