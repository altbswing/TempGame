package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class WindowVersion extends Window {
	
	private Image sign = new ImageIcon("Graphics/sign/sign.png").getImage();
	
	public WindowVersion() {
		super(RA_X,464,A_WIDTH,152);
	}
	
	public void windowRepaint(Graphics g){
		this.createWindow(g);
		this.drawImg(sign, 0, 0, g);
	}
}
