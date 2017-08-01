package ui.window.main;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import game.agent.GameAgent;
import cfg.GuiConfig;
import cfg.DeBug;
import ui.cui.Printer;
import ui.listener.*;

public class PanelContext {

	private GameAgent gameAgent = null;
	private PanelMain panelMain = null;
	private LayButton layButton = null;
	private LayChat layChat = null;
	private LayBan layBan = null;
	private LayDai1 layDai1 = null;
	private LayDai0 layDai0 = null;
	private LayPlayer1 layPlayer1 = null;
	private LayPlayer0 layPlayer0 = null;
	
	public PanelContext() {
		init();
		addCom();
		addListener();
	}
	
	/**
	 * �������
	 * */
	private void init() {
		panelMain = new PanelMain();
		layBan = new LayBan(GuiConfig.BAN_LAY_RECT, this);
		layDai1 = new LayDai1(GuiConfig.DAI_LAY_RECT_1, this);
		layDai0 = new LayDai0(GuiConfig.DAI_LAY_RECT_0, this);
		layPlayer1 = new LayPlayer1(GuiConfig.PLA_LAY_RECT_1, this);
		layPlayer0 = new LayPlayer0(GuiConfig.PLA_LAY_RECT_0, this);
		layButton = new LayButton(GuiConfig.BTN_LAY_RECT, this);
		layChat = new LayChat(GuiConfig.CHAT_LAY_RECT, this);
	}
	
	/**
	 * �������
	 * */
	private void addCom() {
		panelMain.add(layButton);
		panelMain.add(layChat);
		panelMain.add(layPlayer1);
		panelMain.add(layPlayer0);
		panelMain.add(layDai1);
		panelMain.add(layDai0);
		panelMain.add(layBan);
	}
	
	/**
	 * ���������
	 * */
	private void addListener() {
		panelMain.addMouseListener(new MouseBasic(this));
		layBan.addMouseListener(new MouseBan(this));
		layDai1.addMouseListener(new MouseDai1(this));
		layDai0.addMouseListener(new MouseDai0(this));
	}
	
	/**
	 * ˢ�����̺���̨
	 * */
	public synchronized void repaintGame() {
		long s, e, time;
		s = System.currentTimeMillis();
		panelMain.repaint();
		layBan.repaint();
		layDai1.repaint();
		layDai0.repaint();
		layPlayer1.repaint();
		layPlayer0.repaint();
		e = System.currentTimeMillis();
		time = e - s;
		if(DeBug.CUI_ON) {
			Printer.message("����ˢ�»����ʱ��" + time + "ms");
		}
	}
	
	/**
	 * ˢ�¶Է��������Ϣ���
	 * */
	public void repaintPlayer0() {
		layPlayer0.repaint();
	}
	
	/**
	 * ˢ���Լ��������Ϣ���
	 * */
	public void repaintPlayer1() {
		layPlayer1.repaint();
	}
	
	/**
	 * ��������
	 * */
	public PanelMain getPanelMain() {
		return panelMain;
	}
	
	/**
	 * ������Ϸ�߼�ģ��
	 * */
	public void setGameAgent(GameAgent gameAgent) {
		this.gameAgent = gameAgent;
	}

	/**
	 * �����Ϸ�߼�ģ��
	 * */
	public GameAgent getGameAgent() {
		return gameAgent;
	}

	/**
	 * �Ƿ�ɵĶԻ���
	 * */
	public boolean nariWinYes() {
		int val = JOptionPane.showConfirmDialog(layBan, "�ɣ�",
                UIManager.getString("OptionPane.titleText"),
                JOptionPane.YES_NO_OPTION);
		if (val == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}
}
