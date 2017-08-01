package game.entity.koma;

import java.awt.Image;
import java.util.Collection;
import java.util.HashSet;

import util.Point;
import cfg.GameConfig;
import cfg.setter.ImageReader;

/**
 * 香車
 * */
public class KomaKyou extends Koma {

	public KomaKyou(boolean mine) {
		this.mine = mine;
	}

	@Override
	public void createMoveShadow(boolean[][] shadow, Koma[][] ban, Point now) {
		if (nari) {
			super.createMoveShadow(shadow, ban, now);
		} else {
			shadow[now.x][now.y] = true;
			Point p = new Point(now.x, now.y);
			while (true) {
				p = new Point(p.x, p.y + (mine ? -1 : 1));
				if (!addmove(shadow, ban, p)) {
					break;
				}
			}
		}
	}

	@Override
	public void createSetShadow(boolean[][] shadow, Koma[][] ban) {
		int ys = mine ? 1 : 0;
		int ye = mine ? 9 : 8;
		for (int y = ys; y < ye; y++) {
			for (int x = 0; x < ban.length; x++) {
				if (ban[x][y] == null) {
					shadow[x][y] = true;
				}
			}
		}
	}

	@Override
	protected Collection<Point> moveCollection(final Koma[][] ban,
			final Point now) {
		Collection<Point> coll = new HashSet<Point>();
		if (nari) {
			coll = super.moveCollection(ban, now);
		} else {
			Point p = new Point(now.x, now.y);
			// coll.add(p);
			while (true) {
				p = new Point(p.x, p.y + (mine ? -1 : 1));
				if (!addmove(coll, ban, p)) {
					break;
				}
			}
		}
		return coll;
	}

	@Override
	public Image getImage() {
		return nari ? (mine ? ImageReader.KOMA_IMG[5][3]
				: ImageReader.KOMA_IMG[5][2])
				: (mine ? ImageReader.KOMA_IMG[5][1]
						: ImageReader.KOMA_IMG[5][0]);
	}

	@Override
	public int code() {
		return Koma.KYOU;
	}

	@Override
	public String toString() {
		return "[" + (mine ? "▲" : "▼") + (nari ? "香" : "成香") + "]";
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
		return GameConfig.KOMA_POINT[Koma.KYOU];
	}

	@Override
	public int getWorth() {
		return nari ? GameConfig.KOMA_NARI_WORTH[Koma.KYOU] 
		             : GameConfig.KOMA_WORTH[Koma.KYOU];
	}
}
