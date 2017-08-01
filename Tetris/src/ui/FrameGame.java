package ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameGame extends JFrame{
	
	private static final long serialVersionUID = 8963854705444380117L;

	public FrameGame(PanelGame panel){
		setTitle("Java¶íÂÞË¹·½¿é v1.28 byÐ¡Òí");
		setSize(1146,672);
		center();
		setResizable(false);
		this.add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void center() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		Dimension w = this.getSize();
		int x = (screen.width - w.width) / 2;
		int y = (screen.height - w.height) / 2;
		this.setLocation(x, y-16);
	}
}
