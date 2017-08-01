package game.entity.koma;

import java.awt.Image;
import java.util.Collection;
import java.util.HashSet;

import cfg.GameConfig;
import cfg.setter.ImageReader;

import util.Point;

public class KomaHu extends Koma {

	public KomaHu(boolean mine) {
		this.mine = mine;
	}

	@Override
	public void createMoveShadow(boolean[][] shadow, Koma[][] ban, Point now) {
		if (nari) {
			super.createMoveShadow(shadow, ban, now);
		} else {
			shadow[now.x][now.y] = true;
			addmove(shadow, ban, new Point(now.x, now.y + (mine ? -1 : 1)));
		}
	}

	@Override
	public void createSetShadow(boolean[][] shadow, Koma[][] ban) {
		int ys = mine ? 1 : 0;
		int ye = mine ? 9 : 8;
		for (int x = 0; x < ban.length; x++) {
			if (hasOtherHu(ban, mine, x)) {
				continue;
			}
			for (int y = ys; y < ye; y++) {
				if (ban[x][y] == null) {
					shadow[x][y] = true;
				}
			}
		}
	}
	
	@Override
	protected Collection<Point> moveCollection(Koma[][] ban, Point now) {
		Collection<Point> coll = new HashSet<Point>();
		if(nari) {
			coll = super.moveCollection(ban, now);
		} else {
//			coll.add(new Point(now.x, now.y));
			addmove(coll, ban, new Point(now.x, now.y + (mine ? -1 : 1)));
		}
		return coll;
	}

	/**
	 * 判断本行是否还有其他本方步兵
	 * */
	private boolean hasOtherHu(Koma[][] ban, boolean isMine, int x) {
		for (int y = 0; y < ban.length; y++) {
			if(ban[x][y] instanceof KomaHu
					&& ban[x][y].mine == isMine
					&& !ban[x][y].nari) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Image getImage() {
		return nari ? (mine ? ImageReader.KOMA_IMG[6][3]
				: ImageReader.KOMA_IMG[6][2])
				: (mine ? ImageReader.KOMA_IMG[6][1]
						: ImageReader.KOMA_IMG[6][0]);
	}

	@Override
	public int code() {
		return Koma.HU;
	}

	@Override
	public String toString() {
		return "[" + (mine ? "▲" : "▼") + (nari ? "歩" : "と") + "]";
	}

	@Override
	public boolean nari(int karaY, int heY) {
		if (mine && heY == 0) {
			setNari();
			return false;
		}
		if (!mine && heY == 8) {
			setNari();
			return false;
		}
		return super.nari(karaY, heY);
	}

	@Override
	public int getPoint() {
		return GameConfig.KOMA_POINT[Koma.HU];
	}

	@Override
	public int getWorth() {
		return nari ? GameConfig.KOMA_NARI_WORTH[Koma.HU] 
		             : GameConfig.KOMA_WORTH[Koma.HU];
	}

}
