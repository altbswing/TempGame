package ui.window.main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import cfg.setter.ImageReader;

public class Lay extends JPanel{
	private static final long serialVersionUID = -1010852249527680267L;
	
	private static final int SIZE = 8;
	
	private static final Image RECT_PIC = ImageReader.RECT_IMG;
	protected static final int RECT_H = RECT_PIC.getHeight(null);
	protected static final int RECT_RED = 0;
	protected static final int RECT_GREEN = 99;
	protected static final int RECT_YELLOW = 50;
	protected static final int RECT_GtoR = -1;
	protected static final int RECT_CYAN = 100;
	
	protected PanelContext panelContext = null;
	protected int w;
	protected int h;
	
	public Lay(int x, int y, int w, int h) {
		this.setLocation(x, y);
		this.setSize(w, h);
		this.w = this.getSize().width;
		this.h = this.getSize().height;
	}
	
	protected void createWindow(Graphics g){
		createWindow(0, 0, w, h, true, g);
		this.setOpaque(false);
	}
	
	protected void createWindow(int x, int y, int w, int h, 
			boolean cc, Graphics g){
		int ch = h - (SIZE << 1);
		int cw = w - (SIZE << 1);
		g.drawImage(ImageReader.WINDOW_LT, x, y, null);
		g.drawImage(ImageReader.WINDOW_RT, x+w-8, y,null);	
		g.drawImage(ImageReader.WINDOW_LB, x, y+h-8, null);
		g.drawImage(ImageReader.WINDOW_RB, x+w-8, y+h-8, null);
		g.drawImage(ImageReader.WINDOW_CT, x+8, y, cw, 8, null);
		g.drawImage(ImageReader.WINDOW_LC, x, y + 8, 8, ch, null);
		g.drawImage(ImageReader.WINDOW_RC, x+w-8, y+8, 8, ch, null);
		g.drawImage(ImageReader.WINDOW_CB, x+8, y+h-8, cw, 8, null);
		if(cc) {
			g.drawImage(ImageReader.WINDOW_CC, 8, 8, cw, ch, null);
		}
	}
	
	public void setPanelContext(PanelContext panelContext) {
		this.panelContext = panelContext;
	}
	
}
