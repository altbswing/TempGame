package game.entity.koma;

import java.awt.Image;
import java.util.Collection;
import java.util.HashSet;

import cfg.GameConfig;
import cfg.setter.ImageReader;

import util.Point;

public class KomaGin extends Koma {

	public KomaGin(boolean mine) {
		this.mine = mine;
	}
	
	@Override
	public void createMoveShadow(boolean[][] shadow, Koma[][] ban, Point now) {
		if(nari) {
			super.createMoveShadow(shadow, ban, now);
		} else {
			shadow[now.x][now.y] = true;
			addmove(shadow, ban, new Point(now.x + 1, now.y + 1));
			addmove(shadow, ban, new Point(now.x - 1, now.y - 1));
			addmove(shadow, ban, new Point(now.x - 1, now.y + 1));
			addmove(shadow, ban, new Point(now.x + 1, now.y - 1));
			addmove(shadow, ban, new Point(now.x, now.y + (mine ? -1 : 1)));
		}
	}
	
	@Override
	protected Collection<Point> moveCollection(Koma[][] ban, Point now) {
		Collection<Point> coll = new HashSet<Point>();
		if(nari) {
			coll = super.moveCollection(ban, now);
		} else {
//			coll.add(new Point(now.x, now.y));
			addmove(coll, ban, new Point(now.x + 1, now.y + 1));
			addmove(coll, ban, new Point(now.x - 1, now.y - 1));
			addmove(coll, ban, new Point(now.x - 1, now.y + 1));
			addmove(coll, ban, new Point(now.x + 1, now.y - 1));
			addmove(coll, ban, new Point(now.x, now.y + (mine ? -1 : 1)));
		}
		return coll;
	}

	@Override
	public Image getImage() {
		return nari ? 
				(mine ? ImageReader.KOMA_IMG[3][3] : ImageReader.KOMA_IMG[3][2])
				: (mine ? ImageReader.KOMA_IMG[3][1] : ImageReader.KOMA_IMG[3][0]);
	}
	
	@Override
	public int code() {
		return Koma.GIN;
	}
	
	@Override
	public String toString() {
		return "[" + (mine ? "▲" : "▼")
				+(nari ? "銀" : "成銀") + "]";
	}

	@Override
	public int getPoint() {
		return GameConfig.KOMA_POINT[Koma.GIN];
	}

	@Override
	public int getWorth() {
		return nari ? GameConfig.KOMA_NARI_WORTH[Koma.GIN] 
		             : GameConfig.KOMA_WORTH[Koma.GIN];
	}

}
