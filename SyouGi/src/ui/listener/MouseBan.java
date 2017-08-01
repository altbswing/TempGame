package ui.listener;

import game.agent.GameAgent;

import java.awt.event.MouseEvent;

import ui.cui.Printer;
import ui.window.main.PanelContext;
import cfg.DeBug;
import cfg.MouseConfig;

/**
 * ¼àÌýÆåÅÌÊÂ¼þ
 * */
public class MouseBan extends MouseBasic {
	
	public MouseBan(PanelContext panelContext) {
		super(panelContext);
	}
	
	@Override
	public synchronized void mouseClicked(MouseEvent e) {
		GameAgent gameAgent = panelContext.getGameAgent();
		if (e.getButton() == 1) {
			int mx = e.getX();
			int my = e.getY();
			if (mx >= MouseConfig.BAN_MIN_X && mx < MouseConfig.BAN_MAX_X
					&& my >= MouseConfig.BAN_MIN_Y && my < MouseConfig.BAN_MAX_Y) {
				int x = (mx - MouseConfig.BAN_MIN_X) / MouseConfig.KOMA_W;
				int y = (my - MouseConfig.BAN_MIN_Y) / MouseConfig.KOMA_H;
				if (DeBug.CUI_ON) {
					Printer.message("×ó¼ü: [" + x + "," + y + "]");
				}
				if(gameAgent != null) {
					panelContext.getGameAgent().clickBan(x, y);
				}
			}
		} else if (e.getButton() == 3) {
			if(gameAgent != null) {
				panelContext.getGameAgent().rightMouse();
			}
			if (DeBug.CUI_ON) {
				Printer.message("ÓÒ¼ü: È¡Ïû\n");
			}
		}
	}
}
