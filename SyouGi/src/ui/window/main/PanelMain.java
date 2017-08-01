package ui.window.main;

import java.awt.Graphics;

import javax.swing.JPanel;

import cfg.setter.ImageReader;

public class PanelMain extends JPanel {
	private static final long serialVersionUID = -4230702302470810940L;
	
	public PanelMain() {
		this.setLayout(null);
	}

	@Override
	public synchronized void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ImageReader.BACKGROUND_IMG, 0, 0, null);
	}
}
