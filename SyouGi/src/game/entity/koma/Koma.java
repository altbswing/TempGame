package game.entity.koma;

import java.awt.Image;
import java.util.Collection;
import java.util.HashSet;

import util.Point;

public abstract class Koma {
	
	public static final int NULL	= -1;
	public static final int HI	= 0;
	public static final int KAKU	= 1;
	public static final int KIN	= 2;
	public static final int GIN	= 3;
	public static final int KEI	= 4;
	public static final int KYOU	= 5;
	public static final int HU	= 6;
	public static final int OU	= 7;
	
	public static final int NOT_MINE = 0;
	public static final int MINE = 1;
	public static final int MINE_NARI = 2;
	public static final int NOT_MINE_NARI = 3;
	
	protected boolean mine = false;
	protected boolean nari = false;
	
	/**
	 * 返回该棋子的方向
	 * */
	public boolean isMine() {
		return this.mine;
	}

	/**
	 * 该棋子成
	 * */
	public void setNari() {
		nari = true;
	}
	
	/**
	 * 将单个棋盘格设置为可移动
	 * */
	protected boolean addmove(boolean[][] shadow, Koma[][] ban, Point p) {
		if(p.x >= 0 && p.x <= 8 && p.y >= 0 && p.y <= 8) {	//如果在棋盘中
			if(ban[p.x][p.y] == null) {	//该格子无其他棋子，直接添加
				shadow[p.x][p.y] = true;
				return true;
			} else {	//该格子有其他棋子，且与自己的方向相反则添加
				if(ban[p.x][p.y].isMine() != this.mine) {
					shadow[p.x][p.y] = true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 将单个棋盘格添加到可移动范围的集合中
	 * 主要用于王手计算
	 * */
	protected boolean addmove(Collection<Point> coll, Koma[][] ban, Point p) {
		if(p.x >= 0 && p.x <= 8 && p.y >= 0 && p.y <= 8) {	//如果在棋盘中
			if(ban[p.x][p.y] == null) {	//该格子无其他棋子，直接添加
				coll.add(p);
				return true;
			} else {	//TODO 该格子有其他棋子，且与自己的方向相反则添加
				if(ban[p.x][p.y].isMine() != this.mine) {
					coll.add(p);
				}
			}
		}
		return false;
	}
	
	/**
	 * 生成一个该棋子移动范围的集合，既棋子的移动规则
	 * 不覆盖则为金将的移动规则
	 * 主要用于王手计算
	 * */
	protected Collection<Point> moveCollection(Koma[][] ban, Point now){
		Collection<Point> coll = new HashSet<Point>();
//		coll.add(new Point(now.x, now.y));
		addmove(coll, ban, new Point(now.x + 1, now.y));
		addmove(coll, ban, new Point(now.x - 1, now.y));
		addmove(coll, ban, new Point(now.x, now.y + 1));
		addmove(coll, ban, new Point(now.x, now.y - 1));
		addmove(coll, ban, new Point(now.x + 1, now.y + (mine ? -1 : 1)));
		addmove(coll, ban, new Point(now.x - 1, now.y + (mine ? -1 : 1)));
		return coll;
	}
	
	/**
	 * 设置该棋子所有可移动的点
	 * 既棋子的移动规则
	 * 不覆盖则为金将的移动规则
	 * */
	public void createMoveShadow(boolean[][] shadow, Koma[][] ban, Point now) {
		shadow[now.x][now.y] = true;
		addmove(shadow, ban, new Point(now.x + 1, now.y));
		addmove(shadow, ban, new Point(now.x - 1, now.y));
		addmove(shadow, ban, new Point(now.x, now.y + 1));
		addmove(shadow, ban, new Point(now.x, now.y - 1));
		addmove(shadow, ban, new Point(now.x + 1, now.y + (mine ? -1 : 1)));
		addmove(shadow, ban, new Point(now.x - 1, now.y + (mine ? -1 : 1)));
	}
	
	/**
	 * 生成一个棋子从棋台上放到棋盘上的范围
	 * 桂，香，步 必须覆盖该方法
	 * */
	public void createSetShadow(boolean[][] shadow, Koma[][] ban){
		for (int y = 0; y < ban.length; y++) {
			for (int x = 0; x < ban.length; x++) {
				if(ban[x][y] == null) {
					shadow[x][y] = true;
				}
			}
		}
	}
	
	/**
	 * 返回该棋子的识别码
	 * */
	public int code() {
		return Koma.NULL;
	}
	
	/**
	 * 创建一个棋子
	 * @param 类型 
	 * @param 是否是我的 
	 * @param 是否成
	 * */
	public static Koma createKoma(int komaType, boolean isMine, boolean nari) {
		Koma koma = createKoma(komaType, isMine);
		if(nari) {
			koma.setNari();
		}
		return koma;
	}
	
	/**
	 * 创建一个棋子
	 * @param 类型 
	 * @param 是否是我的 
	 * */
	public static Koma createKoma(int komaType, boolean isMine) {
		switch (komaType) {
		case HI:
			return new KomaHi(isMine);
		case KAKU:
			return new KomaKaku(isMine);
		case KIN:
			return new KomaKin(isMine);
		case GIN:
			return new KomaGin(isMine);
		case KEI:
			return new KomaKei(isMine);
		case KYOU:
			return new KomaKyou(isMine);
		case HU:
			return new KomaHu(isMine);
		default:
			return null;
		}
	}

	/**
	 * 棋子是否可以成
	 * 该返回值用来确定是否弹出成否的对话框
	 * */
	public boolean nari(int fromY, int toY) {
		if(nari) {
			return false;
		}
		if (mine && ((fromY >= 3 && toY <= 2) || fromY <= 2)) {
			// 我方棋子杀入敌阵，走出敌阵，或在敌阵中移动
			return true;
		}
		if (!mine && ((fromY <= 5 && toY >= 6) || fromY >= 6)) {
			// 敌方棋子杀入敌阵，走我敌阵，或在我阵中移动
			return true;
		}
		return false;
	}

	public boolean ouTeKakunin(final Koma[][] ban, final Point now) {
		return false;
	}
	
	/**
	 * 返回该棋子的图片对象
	 * */
	public abstract Image getImage();
	
	/**
	 * 返回该棋子的点数
	 * */
	public abstract int getPoint();
	
	/**
	 * 返回该棋子的实战价值
	 * */
	public abstract int getWorth();
		
}
