package ui.window.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import util.ComponentUtil;
import cfg.DeBug;
import cfg.GuiConfig;
import cfg.Text;


public class FrameMain extends JFrame {
	private static final long serialVersionUID = -5423493478603446651L;

	public FrameMain(PanelMain panelContext) {
		this.setSize(GuiConfig.MAIN_FRAME_W, GuiConfig.MAIN_FRAME_H);
		this.setTitle(this.createTitle());
		this.setContentPane(panelContext);
		ComponentUtil.frameInit(this);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ComponentUtil.exit(FrameMain.this);
			}
		});
		this.setVisible(true);
	}
	
	private String createTitle() {
		String title = Text.TITLE_MAIN + Text.VERSION + Text.AUTHOR;
		if(DeBug.CUI_ON) {
			title += Text.TITLE_SPA;
		}
		
		return title;
	}
	
}
