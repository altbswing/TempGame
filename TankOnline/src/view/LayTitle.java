package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import service.Game;

public class LayTitle extends Lay{

	private Image title = new ImageIcon("Graphics/string/title.png").getImage();
	private Image win = new ImageIcon("Graphics/string/win.png").getImage();
	private Image lose = new ImageIcon("Graphics/string/lose.png").getImage();
	private Image draw = new ImageIcon("Graphics/string/draw.png").getImage();
	private Image enter = new ImageIcon("Graphics/string/enter.png").getImage();
	private Image wait = new ImageIcon("Graphics/string/wait.png").getImage();
	
	private static final int IMG_X = 560;
	private static final int IMG_Y = 208;
	private static final int IMG_BY = 416;
	
	public LayTitle(){
		super(0,0);
	}
	
	public void showLay(int winner, int type, Graphics g){
		if (winner != Game.GO_ON && type == PanelGame.SERVER){
			switch (winner) {
			case Game.NOT_START:
				drawImgC(title, IMG_X, IMG_Y, g);
				break;
			case Game.P1:
				drawImgC(win, IMG_X, IMG_Y, g);
				break;
			case Game.P2:
				drawImgC(lose, IMG_X, IMG_Y, g);
				break;
			case Game.DRAW:
				drawImgC(draw, IMG_X, IMG_Y, g);
				break;
			default:
				break;
			}
			drawImgC(enter, IMG_X, IMG_BY, g);
		}else if(winner != Game.GO_ON && type == PanelGame.CLIENT){
			switch (winner) {
			case Game.NOT_START:
				drawImgC(title, IMG_X, IMG_Y, g);
				break;
			case Game.P1:
				drawImgC(lose, IMG_X, IMG_Y, g);
				break;
			case Game.P2:
				drawImgC(win, IMG_X, IMG_Y, g);
				break;
			case Game.DRAW:
				drawImgC(draw, IMG_X, IMG_Y, g);
				break;
			default:
				break;
			}
			drawImgC(wait, IMG_X, IMG_BY, g);
		}
	}
}
