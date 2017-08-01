package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import service.Game;

/**
 * 主窗口 16：9
 * @author xiaoE
 * */
public class FrameMain extends JFrame{
	private static final long serialVersionUID = -5459127417090437252L;

	public FrameMain(PanelGame panel, String title){
		this.setTitle("Java玩具坦克(" + title + ") v"+ Game.VERSION + " by小翼");
		this.setIconImage(new ImageIcon("Graphics/icon/icon.png").getImage());
		this.setSize(1126,672);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.setVisible(true);
	}
}
