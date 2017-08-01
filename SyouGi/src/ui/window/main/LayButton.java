package ui.window.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import cfg.Text;
import cfg.setter.ImageReader;

public class LayButton extends Lay implements ActionListener{
	private static final long serialVersionUID = -1776015288575546531L;

	private static final int SPA = 16;
	private static final int SIZE = 32;
	
	private List<JButton> btns = new ArrayList<JButton>();
	
	public LayButton(Rectangle rect, PanelContext panelContext) {
		super(rect.x, rect.y, rect.width, rect.height);
		this.panelContext = panelContext;
		this.setLayout(null);
		btnInit();
	}
	
	private void btnInit() {
		for (int i = 0; i < 2; i++) {
			createButton(i, ImageReader.BTN_ICON.get(i), Text.BTN[i]);
		}
		for (JButton jb : btns) {
			this.add(jb);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.createWindow(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btns.get(0)) {
			System.out.println("styleBtn");
			//TODO
		} else if(e.getSource() == btns.get(1)){
			System.out.println("linkBtn");
			//TODO
		}
	}
	
	private void createButton(int i, ImageIcon icon, String text) {
		JButton jb = new JButton(icon);
		jb.setToolTipText(text);
		jb.setSize(SIZE, SIZE);
		jb.setLocation(SPA + ((SPA >> 1) + SIZE) * i, SPA);
		jb.addActionListener(this);
		btns.add(jb);
	}
}
