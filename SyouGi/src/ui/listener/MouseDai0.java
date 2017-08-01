package ui.listener;

import game.agent.GameAgent;

import java.awt.event.MouseEvent;

import ui.cui.Printer;
import ui.window.main.PanelContext;
import cfg.DeBug;
import cfg.MouseConfig;

public class MouseDai0 extends MouseBasic {

	public MouseDai0(PanelContext panelContext) {
		super(panelContext);
	}

	public synchronized void mouseClicked(MouseEvent e) {
		GameAgent gameAgent =panelContext.getGameAgent();
		if (e.getButton() == 1) {
			int mx = e.getX();
			int my = e.getY();
			if(inDai(mx, my)) {
				int x = (mx - MouseConfig.DAI_MIN_X) / MouseConfig.DAI_KOMA_W;
				int y = (my - MouseConfig.DAI_MIN_Y) / MouseConfig.DAI_KOMA_H;
				int idx = 7 - (x + y * 2);
				if (idx > 6) {
					idx = 6;
				}
				if (DeBug.CUI_ON) {
					Printer.message("左键：[对方棋台 " + idx + "]\n");
				}
				if(gameAgent != null) {
					gameAgent.clickDai(idx, false);
				}
			}
		} else if (e.getButton() == 3) {
			if (DeBug.CUI_ON) {
				Printer.message("右键: 取消\n");
			}
			if(gameAgent != null) {
				gameAgent.rightMouse();
			}
		}
	}

}
