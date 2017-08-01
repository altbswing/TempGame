package game.agent;

import game.entity.koma.Koma;
import game.entity.koma.KomaOu;
import game.entity.pla.Player;
import ui.cui.Printer;
import ui.window.main.PanelContext;
import util.Point;
import cfg.DeBug;
import cfg.GameConfig;
import cfg.setter.GameSetter;

public abstract class GameAgent {
	
	public static final int CAN_NOT_CONTROL = -1;
	public static final int NULL = 0;
	public static final int BAN_KARA_MOTI = 1;
	public static final int DAI_KARA_MOTI = 2;
	
	protected Point[] ouP = new Point[2];
	protected boolean[] ouTe= new boolean[2]; 
	protected final Player[] player = new Player[2];
	protected final Hand hand = new Hand();
	protected Koma[][] ban = new Koma[GameConfig.BAN_SIZE][GameConfig.BAN_SIZE];
	protected final int[][] dai = new int[2][GameConfig.DAI_SIZE];
	protected boolean[][] shadow = new boolean[GameConfig.BAN_SIZE][GameConfig.BAN_SIZE];
	protected int state = CAN_NOT_CONTROL;
	protected boolean showShadow = false;
	protected PanelContext panelContext = null;
	protected int maxFirstTime = 0;
	protected int maxSecondTime = 0;

	public GameAgent(PanelContext panelContext) {
		this.panelContext = panelContext;
	}

	/**
	 * 重置一套棋局，包括各种练习局，残局
	 * */
	protected void gameInit(GameSetter gs) {
		ban = gs.getBan();
		ouP[1] = gs.getOu1();
		ouP[0] = gs.getOu0();
		int[] gsdai1 = gs.getDai1();
		int[] gsdai0 = gs.getDai0();
		for(int i = 0; i < 7; i++) {
			dai[1][i] = gsdai1[i];
			dai[0][i] = gsdai0[i];
		}
		player[1] = new Player();
		player[0] = new Player();
	}
	
	/**
	 * 追踪王的移动，记录坐标
	 * */
	protected void changeOuPoint(Koma koma, Point to) {
		if(koma instanceof KomaOu) {
			if(koma.isMine() && ouP[1] != null) {
				ouP[1].x = to.x;
				ouP[1].y = to.y;
			} else if((!koma.isMine()) && ouP[0] != null) {
				ouP[0].x = to.x;
				ouP[0].y = to.y;
			}
			if(DeBug.CUI_ON) {
				Printer.message(koma + "移动到" + to);
			}
		}
	}
	
	/**
	 * 计算分数
	 * */
	protected void setPointAndWorth() {
		int p1Worth = 0, p0Worth = 0, p1Point = 0, p0Point = 0;
		for (int y = 0; y < GameConfig.BAN_SIZE; y++) {
			for (int x = 0; x < GameConfig.BAN_SIZE; x++) {
				if(ban[x][y] != null) {
					if(ban[x][y].isMine()) {
						p1Worth += ban[x][y].getWorth();
						p1Point += ban[x][y].getPoint();
					} else {
						p0Worth += ban[x][y].getWorth();
						p0Point += ban[x][y].getPoint();
					}
				}
			}
		}
		for (int i = 0; i < 7; i++) {
			p1Worth += dai[1][i] * GameConfig.KOMA_WORTH[i];
			p1Point += dai[1][i] * GameConfig.KOMA_POINT[i];
			p0Worth += dai[0][i] * GameConfig.KOMA_WORTH[i];
			p0Point += dai[0][i] * GameConfig.KOMA_POINT[i];
		}
		player[1].setPoint(p1Point);
		player[1].setWorth(p1Worth);
		player[0].setPoint(p0Point);
		player[0].setWorth(p0Worth);
	}
	
	/**
	 * 是否王手
	 * */
	public boolean[] getOuTeiKadouka() {
		return ouTe;
	}
	
	/**
	 * 状态重置
	 * */
	protected void resetState() {
		shadow = new boolean[9][9];
		showShadow = false;
		hand.reset();
		setState(NULL);
	}
	
	/**
	 * 改变当前状态
	 * */
	protected void setState(int state) {
		this.state = state;
		if(DeBug.CUI_ON) {
			Printer.stateMessage("GameAgentFreedom: ", this.state);
		}
	}
	
	/**
	 * 获得当前状况
	 * */
	public int getState() {
		return state;
	}
	
	/**
	 * 获得王将坐标
	 * */
	public Point[] getOuP() {
		return ouP;
	}
	
	public int getMaxFirstTime() {
		return maxFirstTime;
	}

	public void setMaxFirstTime(int maxFirstTime) {
		this.maxFirstTime = maxFirstTime;
	}

	public int getMaxSecondTime() {
		return maxSecondTime;
	}

	public void setMaxSecondTime(int maxSecondTime) {
		this.maxSecondTime = maxSecondTime;
	}

	/**
	 * 是否显示阴影
	 * */
	public boolean isShowShadow() {
		return showShadow;
	}
	
	/**
	 * 获得棋盘对象
	 * */
	public Koma[][] getBan() {
		return ban;
	}
	
	/**
	 * 获得阴影数组
	 * */
	public boolean[][] getShadow() {
		return shadow;
	}

	/**
	 * 获得棋台
	 * */
	public int[][] getDai() {
		return dai;
	}

	public Player[] getPlayer() {
		return player;
	}

	/**
	 * 注入PanelContext实例对象
	 * */
	public void setPanelContext(PanelContext panelContext) {
		this.panelContext = panelContext;
	}
	
	/**
	 * 响应鼠标左键事件(盘域)
	 * */
	public abstract void clickBan(int x, int y);
	
	/**
	 * 响应鼠标左键事件(台域)
	 * */
	public abstract void clickDai(int komaType, boolean isMine);
	
	/**
	 * 将一个棋子从kara移动到he
	 * */
	public abstract void moveKoma(Point kara, Point he);
	
	/**
	 * 将一个棋子放置到棋盘上
	 * */
	public abstract void setKoma(int x, int y);
	
	/**
	 * 响应右键
	 * */
	public abstract void rightMouse();
	
}
