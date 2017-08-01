package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import message.Player;


/**
 * 主游戏层 by小翼
 * */
public class LayGame extends Lay {

	private Image playerL = new ImageIcon("Graphics/game/playerL.png").getImage();
	private Image playerR = new ImageIcon("Graphics/game/playerR.png").getImage();
	private Image misL = new ImageIcon("Graphics/game/misL.png").getImage();
	private Image misR = new ImageIcon("Graphics/game/misR.png").getImage();
	
	private Image attack = new ImageIcon("Graphics/icon/attack.png").getImage();
	private Image rage = new ImageIcon("Graphics/icon/rage.png").getImage();
	private Image shield = new ImageIcon("Graphics/icon/shield.png").getImage();
	private Image speed = new ImageIcon("Graphics/icon/speed.png").getImage();

	public LayGame() {
		super(0, 416);
	}

	public void showLay(Player p1, Player p2, Graphics g) {
		try {
			showPlayer(p1, g);
			showPlayer(p2, g);
		} catch (Exception e) {}
	}

	private void showPlayer(Player pla, Graphics g) {
		if(pla.isShow()){
			if(!pla.isShotting()){
				g.drawImage(pla.isLeft() ? misL : misR, 
						pla.isLeft()?(pla.x-11):(pla.x - 16), 384,null);
			}
			g.drawImage(pla.isLeft() ? playerL : playerR, pla.x - 16, 384,null);
		}
		
		if(pla.getSkill()[Player.RAGE]){
			drawImgC(rage, pla.x, -16, g);
		}
		if(pla.getSkill()[Player.SHIELD]){
			drawImgC(shield, pla.x, -16, g);
		}
		if(pla.getSkill()[Player.SPEED]){
			drawImgC(speed, pla.x-11, -48, g);
		}
		if(pla.getSkill()[Player.DOUBLE]){
			drawImgC(attack, pla.x+11, -48, g);
		}
	}
}
