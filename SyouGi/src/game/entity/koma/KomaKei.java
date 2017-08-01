package game.entity.koma;

import java.awt.Image;
import java.util.Collection;
import java.util.HashSet;

import util.Point;
import cfg.GameConfig;
import cfg.setter.ImageReader;

public class KomaKei extends Koma {

	public KomaKei(boolean mine) {
		this.mine = mine;
	}

	@Override
	public void createMoveShadow(boolean[][] shadow, Koma[][] ban, Point now) {
		if(nari) {
			super.createMoveShadow(shadow, ban, now);
		} else {
			shadow[now.x][now.y] = true;
			addmove(shadow, ban, new Point(now.x + 1, now.y + (mine ? -2 : 2)));
			addmove(shadow, ban, new Point(now.x - 1, now.y + (mine ? -2 : 2)));
		}
	}
	
	@Override
	public void createSetShadow(boolean[][] shadow, Koma[][] ban){
		int ys = mine ? 2 : 0;
		int ye = mine ? 9 : 7;
		for (int y = ys; y < ye; y++) {
			for (int x = 0; x < ban.length; x++) {
				if(ban[x][y] == null) {
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
			addmove(coll, ban, new Point(now.x + 1, now.y + (mine ? -2 : 2)));
			addmove(coll, ban, new Point(now.x - 1, now.y + (mine ? -2 : 2)));
		}
		return coll;
	}

	@Override
	public Image getImage() {
		return nari ? (mine ? ImageReader.KOMA_IMG[4][3]
				: ImageReader.KOMA_IMG[4][2])
				: (mine ? ImageReader.KOMA_IMG[4][1]
						: ImageReader.KOMA_IMG[4][0]);
	}
	
	@Override
	public int code() {
		return Koma.KEI;
	}
	
	@Override
	public String toString() {
		return "[" + (mine ? "¡ø" : "¨‹")
				+(nari ? "¹ð" : "³É¹ð") + "]";
	}
	
	@Override
	public boolean nari(int karaY, int heY) {
		if(mine && heY <= 1) {
			setNari();
			return false;
		} 
		if(!mine && heY >= 7) {
			setNari();
			return false;
		}
		return super.nari(karaY, heY);
	}

	@Override
	public int getPoint() {
		return GameConfig.KOMA_POINT[Koma.KEI];
	}

	@Override
	public int getWorth() {
		return nari ? GameConfig.KOMA_NARI_WORTH[Koma.KEI] 
		             : GameConfig.KOMA_WORTH[Koma.KEI];
	}

}
