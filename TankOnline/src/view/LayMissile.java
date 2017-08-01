package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import animation.Missile;

public class LayMissile extends Lay{
	
	private Image missileL = new ImageIcon("Graphics/game/misShotL.png").getImage();
	private Image missileR = new ImageIcon("Graphics/game/misShotR.png").getImage();
	private Image hit = new ImageIcon("Graphics/animation/hit01.png").getImage();

	public LayMissile(){
		super(0,416);
	}
	
	public void showLay(Missile mis, Graphics g) {
		try{
			showMis(mis, g);
			int dx = mis.x - 90;
			int dy = 320;
			int sx = mis.getImgIdx().x * 192;
			int sy = mis.getImgIdx().y * 192;
			g.drawImage(hit, dx, dy, dx + 192, dy+192, sx, sy, sx + 192, sy + 192,
					null);
		}catch (NullPointerException e) {
		}
	}
	
	/**
	 * 绘制导弹专用
	 * @author xiaoE
	 * */
	private void showMis(Missile mis,Graphics g){
		if(mis.isShow()){
			int sub = -(int)((Math.atan(mis.getTgAngle())*(180.0/Math.PI)-90)/30);
			int x = mis.x - 14;
			int y = mis.y - 28;
			g.drawImage(mis.isLeft()?missileL:missileR, x, y, x+28, y+28, 
					sub*28, 0, (sub+1)*28, 28, null);
		}
	}
}
