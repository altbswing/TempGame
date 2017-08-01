package ui.window.main;

import game.entity.pla.Player;

import java.awt.Graphics;
import java.awt.Rectangle;

public class LayPlayer0 extends LayPlayer{
	private static final long serialVersionUID = 7641317835926566953L;

	public LayPlayer0(Rectangle rect, PanelContext panelContext) {
		super(rect, panelContext);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Player pla = panelContext.getGameAgent().getPlayer()[0];
		paintInfo(pla, g);
	}
}
