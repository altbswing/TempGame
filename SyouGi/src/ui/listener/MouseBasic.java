package ui.listener;

import game.agent.GameAgent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cfg.DeBug;
import cfg.MouseConfig;

import ui.cui.Printer;
import ui.window.main.PanelContext;

public class MouseBasic implements MouseListener{

	PanelContext panelContext = null;
	
	public MouseBasic(PanelContext panelContext) {
		this.panelContext = panelContext;
	}
	
	public synchronized void mouseClicked(MouseEvent e) {
		GameAgent gameAgent = panelContext.getGameAgent();
		if (e.getButton() == 3) {
			if(gameAgent != null) {
				panelContext.getGameAgent().rightMouse();
			}
			if(DeBug.CUI_ON) {
				Printer.message("ÓÒ¼ü£ºÈ¡Ïû\n");
			}
		}
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	protected boolean inDai(int mx, int my) {
		return mx > MouseConfig.DAI_MIN_X && mx < MouseConfig.DAI_MAX_X
				&& my > MouseConfig.DAI_MIN_Y && my < MouseConfig.DAI_MAX_Y;
	}

}
