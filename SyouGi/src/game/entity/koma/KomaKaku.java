package game.entity.koma;

import java.awt.Image;
import java.util.Collection;
import java.util.HashSet;

import util.Point;
import cfg.GameConfig;
import cfg.setter.ImageReader;

public class KomaKaku extends Koma {

	public KomaKaku(boolean mine) {
		this.mine = mine;
	}
	
	@Override
	public void createMoveShadow(boolean[][] shadow, Koma[][] ban, Point now) {
		shadow[now.x][now.y] = true;
		Point p = new Point(now.x, now.y);
		while(true) {	//左上
			p = new Point(p.x - 1, p.y - 1);
			if(!addmove(shadow, ban, p)) {
				break;
			}
		}
		p = new Point(now.x, now.y);
		while(true) {	//左下
			p = new Point(p.x - 1, p.y + 1);
			if(!addmove(shadow, ban, p)) {
				break;
			}
		}
		p = new Point(now.x, now.y);
		while(true) {	//右上
			p = new Point(p.x + 1, p.y - 1);
			if(!addmove(shadow, ban, p)) {
				break;
			}
		}
		p = new Point(now.x, now.y);
		while(true) {	//右下
			p = new Point(p.x + 1, p.y + 1);
			if(!addmove(shadow, ban, p)) {
				break;
			}
		}
		if(nari) {
			addmove(shadow, ban, new Point(now.x + 1, now.y));
			addmove(shadow, ban, new Point(now.x - 1, now.y));
			addmove(shadow, ban, new Point(now.x, now.y + 1));
			addmove(shadow, ban, new Point(now.x, now.y - 1));
		}
	}
	
	@Override
	protected Collection<Point> moveCollection(Koma[][] ban, Point now) {
		Collection<Point> coll = new HashSet<Point>();
		Point p = new Point(now.x, now.y);
//		coll.add(p);
		while(true) {	//左上
			p = new Point(p.x - 1, p.y - 1);
			if(!addmove(coll, ban, p)) {
				break;
			}
		}
		p = new Point(now.x, now.y);
		while(true) {	//左下
			p = new Point(p.x - 1, p.y + 1);
			if(!addmove(coll, ban, p)) {
				break;
			}
		}
		p = new Point(now.x, now.y);
		while(true) {	//右上
			p = new Point(p.x + 1, p.y - 1);
			if(!addmove(coll, ban, p)) {
				break;
			}
		}
		p = new Point(now.x, now.y);
		while(true) {	//右下
			p = new Point(p.x + 1, p.y + 1);
			if(!addmove(coll, ban, p)) {
				break;
			}
		}
		if(nari) {
			addmove(coll, ban, new Point(now.x + 1, now.y));
			addmove(coll, ban, new Point(now.x - 1, now.y));
			addmove(coll, ban, new Point(now.x, now.y + 1));
			addmove(coll, ban, new Point(now.x, now.y - 1));
		}
		return coll;
	}

	@Override
	public Image getImage() {
		return nari ? 
				(mine ? ImageReader.KOMA_IMG[1][3] : ImageReader.KOMA_IMG[1][2])
				: (mine ? ImageReader.KOMA_IMG[1][1] : ImageReader.KOMA_IMG[1][0]);
	}
	
	@Override
	public int code() {
		return Koma.KAKU;
	}
	
	@Override
	public String toString() {
		return "[" + (mine ? "▲" : "▼")
				+(nari ? "角" : "馬") + "]";
	}

	@Override
	public int getPoint() {
		return GameConfig.KOMA_POINT[Koma.KAKU];
	}

	@Override
	public int getWorth() {
		return nari ? GameConfig.KOMA_NARI_WORTH[Koma.KAKU] 
		             : GameConfig.KOMA_WORTH[Koma.KAKU];
	}

}
