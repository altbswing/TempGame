package game.entity.koma;

import java.awt.Image;
import java.util.Collection;
import java.util.HashSet;

import util.Point;
import cfg.GameConfig;
import cfg.setter.ImageReader;

public class KomaHi extends Koma{

	public KomaHi(boolean mine) {
		this.mine = mine;
	}
	
	@Override
	public void createMoveShadow(boolean[][] shadow, Koma[][] ban, Point now) {
		shadow[now.x][now.y] = true;
		Point p = new Point(now.x, now.y);
		while(true) {	//上
			p = new Point(p.x, p.y - 1);
			if(!addmove(shadow, ban, p)) {
				break;
			}
		}
		p = new Point(now.x, now.y);
		while(true) {	//下
			p = new Point(p.x, p.y + 1);
			if(!addmove(shadow, ban, p)) {
				break;
			}
		}
		p = new Point(now.x, now.y);
		while(true) {	//左
			p = new Point(p.x - 1, p.y);
			if(!addmove(shadow, ban, p)) {
				break;
			}
		}
		p = new Point(now.x, now.y);
		while(true) {	//右
			p = new Point(p.x + 1, p.y);
			if(!addmove(shadow, ban, p)) {
				break;
			}
		}
		if(nari) {
			addmove(shadow, ban, new Point(now.x + 1, now.y + 1));
			addmove(shadow, ban, new Point(now.x - 1, now.y - 1));
			addmove(shadow, ban, new Point(now.x + 1, now.y - 1));
			addmove(shadow, ban, new Point(now.x - 1, now.y + 1));
		}
	}
	
	@Override
	protected Collection<Point> moveCollection(Koma[][] ban, Point now) {
		Collection<Point> coll = new HashSet<Point>();
		Point p = new Point(now.x, now.y);
//		coll.add(p);
		while(true) {	//上
			p = new Point(p.x, p.y - 1);
			if(!addmove(coll, ban, p)) {
				break;
			}
		}
		p = new Point(now.x, now.y);
		while(true) {	//下
			p = new Point(p.x, p.y + 1);
			if(!addmove(coll, ban, p)) {
				break;
			}
		}
		p = new Point(now.x, now.y);
		while(true) {	//左
			p = new Point(p.x - 1, p.y);
			if(!addmove(coll, ban, p)) {
				break;
			}
		}
		p = new Point(now.x, now.y);
		while(true) {	//右
			p = new Point(p.x + 1, p.y);
			if(!addmove(coll, ban, p)) {
				break;
			}
		}
		if(nari) {
			addmove(coll, ban, new Point(now.x + 1, now.y + 1));
			addmove(coll, ban, new Point(now.x - 1, now.y - 1));
			addmove(coll, ban, new Point(now.x + 1, now.y - 1));
			addmove(coll, ban, new Point(now.x - 1, now.y + 1));
		}
		return coll;
	}

	@Override
	public Image getImage() {
		return nari ? (mine ? ImageReader.KOMA_IMG[0][3]
				: ImageReader.KOMA_IMG[0][2])
				: (mine ? ImageReader.KOMA_IMG[0][1]
						: ImageReader.KOMA_IMG[0][0]);
	}
	
	@Override
	public int code() {
		return Koma.HI;
	}
	
	@Override
	public String toString() {
		return "[" + (mine ? "▲" : "▼")
				+(nari ? "飛" : "竜") + "]";
	}

	@Override
	public int getPoint() {
		return GameConfig.KOMA_POINT[Koma.HI];
	}

	@Override
	public int getWorth() {
		return nari ? GameConfig.KOMA_NARI_WORTH[Koma.HI] 
		             : GameConfig.KOMA_WORTH[Koma.HI];
	}

}
