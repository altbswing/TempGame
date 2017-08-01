package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import message.Player;
import message.Shot;

/**
 * 面板层 by小翼
 * */
public class LayShot extends Lay {

	private Image windL = new ImageIcon("Graphics/window/windL.png").getImage();
	private Image windR = new ImageIcon("Graphics/window/windR.png").getImage();
	private Image noAngle = new ImageIcon("Graphics/window/noAngle.png").getImage();
	private Image noPower = new ImageIcon("Graphics/window/noPower.png").getImage();

	public LayShot() {
		super(0, 416);
	}

	public void showLay(Shot shot, Player p1, Player p2, 
			int wind, int type, Graphics g) {
		drawWind(wind, g);
		try {
			double p = (shot.getPower()-5.0)/(Shot.MAX_SHOT_SPEED-5.0);
			if (type == PanelGame.SERVER){
				g.drawImage(noPower, 644, 602, null);
				drawImgC(noAngle, 1040, 144, g);	
				drawAngle(80, 144, 56, (int)shot.getAngle(), p1.isLeft(), g);	
				darwImgRect(176, 186, 299, p, 269, g);
			}else if (type == PanelGame.CLIENT){
				g.drawImage(noPower, 176, 602, null);
				drawImgC(noAngle, 80, 144, g);
				drawAngle(1040, 144, 56, (int)shot.getAngle(), p2.isLeft(), g);
				darwImgRect(943, 186, -299, p, 269, g);
			}
		} catch (NullPointerException e) {
		}
	}

	/**
	 * 绘制风力指示器 by小翼
	 * */
	private void drawWind(int wind, Graphics g) {
		drawNumberLP(521, 108, Math.abs(wind), Lay.YELLOW, g);
		for (int i = 0; i < Math.abs(wind); i++) {
			if (wind < 0) {
				g.drawImage(windL, 517 - i * 20, this.y + 114, null);
			} else if (wind > 0) {
				g.drawImage(windR, 583 + i * 20, this.y + 114, null);
			}
		}
	}
}
