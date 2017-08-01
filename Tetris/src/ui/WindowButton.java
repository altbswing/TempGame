package ui;

import java.awt.Graphics;

public class WindowButton extends Window {
	public WindowButton() {
		super(RA_X,24,A_WIDTH,88);
	}
	
	public void windowRepaint(Graphics g){
		this.createWindow(g);
	}
}
