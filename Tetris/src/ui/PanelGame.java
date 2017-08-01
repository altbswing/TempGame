package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import service.GameService;

public class PanelGame extends JPanel implements ActionListener, KeyListener {
	private static final long serialVersionUID = 9014398153832136788L;

	// ����
	private WindowGame windowGame = new WindowGame();
	private WindowDataBase windowDataTarena = new WindowDataBase();
	private WindowDataWorld windowDataWorld = new WindowDataWorld();
	private WindowButton windowButton = new WindowButton();
	private WindowNext windowNext = new WindowNext();
	private WindowLevel windowLevel = new WindowLevel();
	private WindowVersion windowVersion = new WindowVersion();
	private WindowPoint windowPoint = new WindowPoint();

	// ���
	private GameService game;
	
	private boolean shadowOn = true;

	// ��Ϸ�ڰ�Ŧ
	private JButton btnStart, btnExit, btnUDTarena, btnUDWorld;

	public PanelGame() {
	}

	public PanelGame(GameService game) {
		this.game = game;
		init();
		addCom();
	}

	/**
	 * ������� byС��
	 * */
	private void init() {
		setLayout(null);

		btnStart = new JButton(new ImageIcon("Graphics/string/strat.png"));
		btnStart.setLocation(826, 48);
		btnStart.setSize(105, 40);

		btnExit = new JButton(new ImageIcon("Graphics/string/exit.png"));
		btnExit.setLocation(947, 48);
		btnExit.setSize(105, 40);

		btnUDTarena = new JButton(new ImageIcon("Graphics/icon/update.png"));
		btnUDTarena.setLocation(290, 40);
		btnUDTarena.setSize(32, 32);

		btnUDWorld = new JButton(new ImageIcon("Graphics/icon/del.png"));
		btnUDWorld.setLocation(290, 345);
		btnUDWorld.setSize(32, 32);
	}

	/**
	 * �����齨 byС��
	 * */
	private void addCom() {
		this.add(btnStart);
		this.add(btnExit);
		this.add(btnUDTarena);
		this.add(btnUDWorld);
		btnStart.addActionListener(this);
		btnExit.addActionListener(this);
		btnUDTarena.addActionListener(this);
		btnUDWorld.addActionListener(this);
		this.addKeyListener(this);
	}

	/**
	 * ���ش��� byС��
	 * */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBG(g);
		if (game.isStart()) { // ��Ϸ����
			windowGame.windowRepaint(game.isLose(), game.isPause(),game.getMap(),
					game.getLevel(), game.getTetris(), game.getType(), shadowOn, g);
		} else {
			windowGame.windowRepaint(g); // ��Ϸ����
		}
		windowNext.windowRepaint(game.getNextType(), g); // ��һ������
		windowButton.windowRepaint(g); // ��Ŧ
		windowVersion.windowRepaint(g); // �汾��Ϣ
		windowLevel.windowRepaint(game.getLevel(), g); // �ȼ�
		windowPoint.windowRepaint(game.getPoint(), game.getRMline(), g); // ����
		windowDataTarena.windowRepaint(game.gettPlayers(), game.getPoint(), g); // ���ڼ�¼
		windowDataWorld.windowRepaint(game.getwPlayers(), game.getPoint(), g); // ���ؼ�¼
	}

	/**
	 * ��ñ���ͼƬ byС��
	 * */
	private void setBG(Graphics g) {
		int imgNum = game.getLevel();
		while (imgNum > 9) {
			imgNum -= 9;
		}
		Image bg = new ImageIcon("Graphics/picture/bg0" + imgNum + ".png")
				.getImage();
		g.drawImage(bg, 0, 0, 1140, 640, null); // ����
	}

	/**
	 * ʵ�ְ�Ŧ���� byС��
	 * */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStart) {
			game.gameStart();
			btnStart.setEnabled(false);
			requestFocus(true);
		} else if (e.getSource() == btnExit) {
			System.exit(0);
		} else if (e.getSource() == btnUDTarena) {
			game.getDB().readTarena();
			requestFocus(true);
		} else if (e.getSource() == btnUDWorld) {
			game.deleteWorldData();
			requestFocus(true);
		}
	}

	public void setBtnStart(boolean b) {
		this.btnStart.setEnabled(b);
	}

	/**
	 * ʵ�ּ��̼��� byС��
	 * */
	public void keyPressed(KeyEvent e) {
		if (!(game.isLose()||game.isPause())) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
					game.round();
				break;
			case KeyEvent.VK_DOWN:
					game.move(0, 1);
				break;
			case KeyEvent.VK_LEFT:
					game.move(-1, 0);
				break;
			case KeyEvent.VK_RIGHT:
					game.move(1, 0);
				break;
			case KeyEvent.VK_SPACE:
					game.quickDown();
				break;
			default:
				break;
			}
		}
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SHIFT:
			game.pause();
			break;
		case KeyEvent.VK_CONTROL:
			shadowOn = !shadowOn;
			repaint();
			break;
		default:
			break;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}
}
