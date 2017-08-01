package ui.window.main;

import game.agent.GameAgent;
import game.entity.koma.Koma;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import cfg.DeBug;
import cfg.setter.ImageReader;

public class LayDai0 extends Lay{
	private static final long serialVersionUID = 2501864318471599112L;
	
	public LayDai0(Rectangle rect, PanelContext panelContext) {
		super(rect.x, rect.y, rect.width, rect.height);
		this.panelContext = panelContext;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(ImageReader.DAI_IMG, 0, 0, null);
		GameAgent gameAgent = panelContext.getGameAgent();
		if(gameAgent != null) {
			paintLay(gameAgent, g);
		}
		if(DeBug.GUI_ON) {
			g.drawImage(DeBug.DAI, 0, 0, null);
		}
	}

	private void paintLay(GameAgent gameAgent, Graphics g) {
		int dai[] = gameAgent.getDai()[0];
		paintKoma(135, 184, dai[Koma.HI], 
				ImageReader.KOMA_IMG[Koma.HI][Koma.NOT_MINE], g);
		paintKoma(51, 184, dai[Koma.KAKU],
				ImageReader.KOMA_IMG[Koma.KAKU][Koma.NOT_MINE], g);
		paintKoma(135, 128, dai[Koma.KIN],
				ImageReader.KOMA_IMG[Koma.KIN][Koma.NOT_MINE], g);
		paintKoma(51, 128, dai[Koma.GIN],
				ImageReader.KOMA_IMG[Koma.GIN][Koma.NOT_MINE], g);
		paintKoma(135, 72, dai[Koma.KEI],
				ImageReader.KOMA_IMG[Koma.KEI][Koma.NOT_MINE], g);
		paintKoma(51, 72, dai[Koma.KYOU],
				ImageReader.KOMA_IMG[Koma.KYOU][Koma.NOT_MINE], g);
		paintKomaHu(dai[Koma.HU], g);
	}
	
	private void paintKoma(
			int xo, int yo, int sum, Image img, Graphics g) {
		double move = 0.0;
		if(sum > 1){
			move = -42 / (sum - 1);
		}
		for (int i = 0; i < sum; i++) {
			g.drawImage(img, xo + (int)(move * i) , yo, null);
		}
	}
	
	private void paintKomaHu(int amount, Graphics g) {
		double move = 0.0;
		if(amount > 1){
			move = -128 / (amount - 1);
		}
		for (int i = 0; i < amount; i++) {
			g.drawImage(ImageReader.KOMA_IMG[Koma.HU][Koma.NOT_MINE], 
					135 + (int)(int)Math.round((i * move)), 16, null);
		}
	}
}
