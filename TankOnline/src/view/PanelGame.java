package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import service.Game;
import control.Control;

public class PanelGame extends JPanel implements Runnable, KeyListener{
	private static final long serialVersionUID = 2648860778246327645L;
	
	public static final int SERVER = 1;
	public static final int CLIENT = 2;
	public static final int LOCAL = 0;

	private Image wind5 = new ImageIcon("Graphics/picture/bg5.png").getImage();
	private Image wind6 = new ImageIcon("Graphics/picture/bg6.png").getImage();
	private Image wind7 = new ImageIcon("Graphics/picture/bg7.png").getImage();
	private Image wind8 = new ImageIcon("Graphics/picture/bg8.png").getImage();
	private Image wind9 = new ImageIcon("Graphics/picture/bg9.png").getImage();
	private Image wind;
	
	private LayBG layBG = new LayBG();
	private LayGame layGame = new LayGame();
	private LayShot layShot = new LayShot();
	private LayPlayer layPlayer = new LayPlayer();
	private LayMissile layMissile = new LayMissile();
	private LayTitle layTitle = new LayTitle();
	
	private Game game;
	private int type;
	private Control ctrl;
	private Thread refresh;
	
	
	public PanelGame(Game game, int type){
		this.game = game;
		this.type = type;
		this.wind = wind5;
		this.ctrl = new Control(game);
		this.addKeyListener(ctrl);
		this.addKeyListener(this);
		refresh = new Thread(this);
		refresh.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		requestFocus(true);
		layBG.showLay(game.wind(), wind, g);
		layGame.showLay(game.p1(),game.p2(),g);
		layShot.showLay(game.getShot(), game.p1(), game.p2(), 
				game.wind().getSpeed(), type, g);
		layPlayer.showLay(game.p1(),game.p2(),game.isP1Action(), g);
		layMissile.showLay(game.missile(), g);
		layTitle.showLay(game.getWinner(), type, g);
	}
	
	
	public void run() {
		while(true){
			try {
				Thread.sleep(Game.SLEEP);
				this.repaint();
			} catch (InterruptedException e) {}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_5:
			wind = wind5;
			break;
		case KeyEvent.VK_6:
			wind = wind6;
			break;
		case KeyEvent.VK_7:
			wind = wind7;
			break;
		case KeyEvent.VK_8:
			wind = wind8;
			break;
		case KeyEvent.VK_9:
			wind = wind9;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}
}
