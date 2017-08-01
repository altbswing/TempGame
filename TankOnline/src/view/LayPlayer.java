package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import message.Player;

public class LayPlayer extends Lay{
	
	private Image lamp = new ImageIcon("Graphics/window/lamp.png").getImage();
	private Image light = new ImageIcon("Graphics/window/light.png").getImage();
	private Image skillIcon = new ImageIcon("Graphics/icon/skill.png").getImage();
	private Image skilldark = new ImageIcon("Graphics/icon/cantUse.png").getImage();

	public LayPlayer(){
		super(0,416);
	}
	
	public void showLay(Player p1, Player p2, boolean p1Action, Graphics g){
		try {
			double p;
			p = p1.getHP()/Player.MAX_HP;//P1血条
			darwImgRect(176, 136, 238, p, Lay.RECT_GtoR, g);
			p = p1.getMP()/Player.MAX_MP; //P1蓝
			darwImgRect(176, 161, 268, p, 170, g);
			p = p2.getHP()/Player.MAX_HP;//P2血条
			darwImgRect(943, 136, -238, p, Lay.RECT_GtoR, g);
			p = p2.getMP()/Player.MAX_MP; //P2蓝
			darwImgRect(943, 161, -268, p, 170, g);
			g.drawImage(skillIcon, 176, 506, null);
			g.drawImage(skillIcon, 786, 506, null);
			drawImgC(lamp, 399, 112, g);
			drawImgC(lamp, 720, 112, g);
			drawStringC(p1.getName(), 395, 116, g);
			drawStringC(p2.getName(), 716, 116, g);
			drawImgC(light, p1Action?399:720, 112, g);
			
			if(p1.getMP()<Player.MP_DOUBLE)
				g.drawImage(skilldark, 176, this.y+90, null);
			if(p1.getMP()<Player.MP_RAGE)
				g.drawImage(skilldark, 218, this.y+90, null);
			if(p1.getMP()<Player.MP_SHIELD)
				g.drawImage(skilldark, 260, this.y+90, null);
			if(p1.getMP()<Player.MP_SPEED)
				g.drawImage(skilldark, 302, this.y+90, null);
			
			if(p2.getMP()<Player.MP_DOUBLE)
				g.drawImage(skilldark, 786, this.y+90, null);
			if(p2.getMP()<Player.MP_RAGE)
				g.drawImage(skilldark, 828, this.y+90, null);
			if(p2.getMP()<Player.MP_SHIELD)
				g.drawImage(skilldark, 870, this.y+90, null);
			if(p2.getMP()<Player.MP_SPEED)
				g.drawImage(skilldark, 912, this.y+90, null);
		} catch (NullPointerException e) {}
	}
}
