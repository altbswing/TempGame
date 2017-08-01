package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import util.IPUtil;

import entity.Player;

public class WindowDataBase extends Window {
	
	private Image logo = new ImageIcon("Graphics/string/db.png").getImage();
	
	public WindowDataBase() {
		super(LA_X,24,A_WIDTH,287);
	}
	
	public void windowRepaint(List<Player> Players, int point, Graphics g){
		this.createWindow(g);
		this.drawImg(logo, 8, 8, g);
		for (int i = 0; i < 5&&i<Players.size(); i++) {
			double p = point/(double)Players.get(i).getPoint();
			if(Players.get(i).getName().equals("No Data")){
				this.darwImgRect(14, 66+i*42, 244, 0.0, true, g);
				this.drawString(Players.get(i).getName(), 
						Color.WHITE, new Font("ºÚÌå",0,20), 14, 80+i*42, g);
			}else{
				this.darwImgRect(14, 66+i*42, 244, p, true, g);
				this.drawString(IPUtil.classroom(Players.get(i).getIp())
						+Players.get(i).getName(), 
						Color.WHITE, new Font("ºÚÌå",0,20), 14, 80+i*42, g);
				this.drawString(leftPad5(Players.get(i).getPoint()), 
						Color.WHITE, new Font("ºÚÌå",0,20), 192, 80+i*42, g);
			}
		}
	}
}
