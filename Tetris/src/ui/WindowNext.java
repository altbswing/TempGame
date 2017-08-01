package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class WindowNext extends Window {
	
	public WindowNext() {
		super(RA_X,130,160,128);
	}
	
	public void windowRepaint(int type, Graphics g){
		this.createWindow(g);
		Image img = new ImageIcon("Graphics/game/"+type+".png").getImage();
		this.drawImg(img, 0, 0, g);
		
	}
}
