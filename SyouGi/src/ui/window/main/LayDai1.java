package ui.window.main;

import game.agent.GameAgent;
import game.entity.koma.Koma;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import cfg.DeBug;
import cfg.setter.ImageReader;

public class LayDai1 extends Lay{
	private static final long serialVersionUID = 2501864318471599112L;
	
	public LayDai1(Rectangle rect, PanelContext panelContext) {
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
		if(DeBug.GUI_ON) {	//TODO DEBUG
			g.drawImage(DeBug.DAI, 0, 0, null);
		}
	}

	private void paintLay(GameAgent gameAgent, Graphics g) {
		int[] dai = gameAgent.getDai()[1];
		paintKoma(9, 16, dai[Koma.HI],
				ImageReader.KOMA_IMG[Koma.HI][Koma.MINE], g);
		paintKoma(94, 16, dai[Koma.KAKU],
				ImageReader.KOMA_IMG[Koma.KAKU][Koma.MINE], g);
		paintKoma(9, 72, dai[Koma.KIN],
				ImageReader.KOMA_IMG[Koma.KIN][Koma.MINE], g);
		paintKoma(94, 72, dai[Koma.GIN],
				ImageReader.KOMA_IMG[Koma.GIN][Koma.MINE], g);
		paintKoma(9, 128, dai[Koma.KEI],
				ImageReader.KOMA_IMG[Koma.KEI][Koma.MINE], g);
		paintKoma(94, 128, dai[Koma.KYOU],
				ImageReader.KOMA_IMG[Koma.KYOU][Koma.MINE], g);
		paintKomaHu(dai[Koma.HU], g);
	}
	
	private void paintKoma(
			int xo, int yo, int amount, Image img, Graphics g) {
		double move = 0.0;
		if(amount > 1){
			move = 42 / (amount - 1);
		}
		for (int i = 0; i < amount; i++) {
			g.drawImage(img, xo + i * (int)move, yo, null);
		}
	}
	
	private void paintKomaHu(int amount, Graphics g) {
		double move = 0.0;
		if(amount > 1){
			move = 128 / (amount - 1);
		}
		for (int i = 0; i < amount; i++) {
			g.drawImage(ImageReader.KOMA_IMG[Koma.HU][Koma.MINE], 
					9 + (int)(int)Math.round((i * move)), 182, null);
		}
	}
}
