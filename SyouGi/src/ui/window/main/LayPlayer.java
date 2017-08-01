package ui.window.main;

import game.agent.GameAgent;
import game.entity.pla.Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import util.StrUtil;

import cfg.GameConfig;
import cfg.Text;
import cfg.setter.ImageReader;

public class LayPlayer extends Lay{
	private static final long serialVersionUID = 7641317835926566953L;

	protected static final int LEFT_X = 16;
	protected static final int RIGHT_X = 120;
	protected static final int FACE_Y = 12;
	protected static final int FACE_W = 160;
	protected static final int FACE_H = 96;
	protected static final int NAME_Y = 124;
	protected static final int MOVE_Y = 29;
	protected static final int TEXT_Y = 144;
	protected static final int RECT_Y = TEXT_Y + 3;
	protected static final int RECT_W = 156;
	protected static final int RECT_H = 6;
	protected static final Font FONT = new Font("����", 1, 15);
	
	public LayPlayer(Rectangle rect, PanelContext panelContext) {
		super(rect.x, rect.y, rect.width, rect.height);
		this.panelContext = panelContext;
	}

	@Override
	public void paintComponent(Graphics g) {
		createWindow(g);
		paintTitle(g);
		paintRectBG(g);
	}
	
	/**
	 * �����Ϣ
	 * */
	protected void paintInfo(Player pla, Graphics g) {
		GameAgent game = panelContext.getGameAgent();
		int[] numData = new int[]{
				pla.getFirstTime(), pla.getSecondTime(),
				pla.getPoint(), pla.getWorth(),
		};
		String[] strData = new String[]{
				StrUtil.leftPad(StrUtil.toTime(numData[0]), ' ', 6),
				StrUtil.leftPad(StrUtil.toTime(numData[1]), ' ', 6),
				StrUtil.leftPad(numData[2], ' ', 6),
				StrUtil.leftPad(numData[3], ' ', 6)
		};
		double[] pData = new double[] {
				(double)numData[0] / game.getMaxFirstTime(),
				(double)numData[1] / game.getMaxSecondTime(),
				(double)numData[2] / GameConfig.MAX_POINT,
				(double)numData[3] / GameConfig.MAX_WORTH
		};
		g.drawImage(pla.getFace(), LEFT_X, FACE_Y, FACE_W, FACE_H, null);
//		g.setFont(FONT);
		g.drawString(pla.getName(), LEFT_X, NAME_Y);
//		g.setFont(FONT);
		for (int i = 0; i < strData.length; i++) {
			g.drawString(strData[i], RIGHT_X, TEXT_Y + MOVE_Y * i);
		}
		for (int i = 0; i < pData.length; i++) {
			darwImgRect(LEFT_X + 1,  RECT_Y + MOVE_Y * i + 1, RECT_W, pData[i], g);
		}
		
	}
	
	/**
	 * ֵ�۱���
	 * */
	private void paintRectBG(Graphics g) {
		for (int i = 0; i < 4; i++) {
			g.setColor(Color.WHITE);
			g.fillRect(LEFT_X, RECT_Y + MOVE_Y * i, RECT_W + 2, RECT_H + 2);
			g.setColor(Color.BLACK);
			g.fillRect(LEFT_X + 1, RECT_Y + MOVE_Y * i + 1, RECT_W, RECT_H);
		}
		g.setColor(Color.WHITE);
	}
	
	/**
	 * ��ͼƬ��ñ�ɫֵ��,byС��
	 * */
	private void darwImgRect(
			int x, int y, int w, double p, Graphics g) {
			g.drawImage(ImageReader.RECT_IMG, x, y, 
					x + (int)(w * p), y + RECT_H,
					(int)(99 * p), 0, (int)(99 * p) + 1, 12, null);
	}
	
	/**
	 * �ֶ���
	 * */
	private void paintTitle(Graphics g) {
		g.setFont(FONT);
		g.setColor(Color.WHITE);
		for (int i = 0; i < Text.PLA_TITLE.length; i++) {
			g.drawString(Text.PLA_TITLE[i], LEFT_X, TEXT_Y + MOVE_Y * i);
		}
	}
}
