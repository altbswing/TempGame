package ui.window.main;

import java.awt.Graphics;
import java.awt.Rectangle;


public class LayChat extends Lay{
	private static final long serialVersionUID = 8236441276090055632L;

	public LayChat(Rectangle rect, PanelContext panelContext) {
		super(rect.x, rect.y, rect.width, rect.height);
		this.panelContext = panelContext;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.createWindow(g);
	}
}
