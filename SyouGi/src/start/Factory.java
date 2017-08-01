package start;

import cfg.setter.ImageReader;
import game.agent.GameAgent;
import game.agent.GameAgentFreedom;
import ui.window.main.FrameMain;
import ui.window.main.PanelContext;

public class Factory {
	
	GameAgent gameAgent = null;
	PanelContext panelContext = null;
	FrameMain frameMain = null;
	
	public Factory () {
		panelContext = new PanelContext();
		gameAgent = new GameAgentFreedom(panelContext);
		panelContext.setGameAgent(gameAgent);
		frameMain = new FrameMain(panelContext.getPanelMain());
	}
	
	public static void main(String[] args) throws Exception {
		ImageReader.imageInit();
		new Factory();
	}
	
}
