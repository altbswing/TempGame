package ui.window.main;

import game.entity.pla.Player;

import java.awt.Graphics;
import java.awt.Rectangle;

public class LayPlayer1 extends LayPlayer{
	private static final long serialVersionUID = 312843851197889003L;
	
	public LayPlayer1(Rectangle rect, PanelContext panelContext) {
		super(rect, panelContext);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Player pla = panelContext.getGameAgent().getPlayer()[1];
		paintInfo(pla, g);
	}
	
}
