package game.entity.koma;

import java.awt.Image;
import java.util.Collection;
import java.util.HashSet;

import cfg.setter.ImageReader;

import util.Point;

public class KomaOu extends Koma {

	public KomaOu(boolean mine) {
		this.mine = mine;
	}

	@Override
	public void createMoveShadow(boolean[][] shadow, Koma[][] ban, Point now) {
		shadow[now.x][now.y] = true;
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				Point p = new Point(now.x - 1 + x, now.y - 1 + y);
				addmove(shadow, ban, p);
			}
		}
	}

	/**
	 * �Ƿ���ֽ���
	 * */
	@Override
	public boolean ouTeKakunin(final Koma[][] ban, final Point now) {
		Collection<Point> vs = new HashSet<Point>();
		for (int x = 0; x < ban.length; x++) { // ��öԷ�����ȫ�����߷�Χ
			for (int y = 0; y < ban.length; y++) {
				if ((ban[x][y] != null) && (ban[x][y].isMine() != mine)) {
					vs.addAll(ban[x][y].moveCollection(ban, new Point(x, y)));
				}
			}
		}
		return vs.contains(now);
	}

	@Override
	protected Collection<Point> moveCollection(final Koma[][] ban,
			final Point now) {
		Point p = new Point(now.x, now.y);
		Collection<Point> coll = new HashSet<Point>();
		coll.add(p);
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				p = new Point(now.x - 1 + x, now.y - 1 + y);
				addmove(coll, ban, p);
			}
		}
		return coll;
	}

	@Override
	public boolean nari(int karaY, int heY) {
		return false;
	}

	@Override
	public Image getImage() {
		return mine ? ImageReader.KOMA_IMG[7][1] : ImageReader.KOMA_IMG[7][0];
	}

	@Override
	public String toString() {
		return "[" + (mine ? "����" : "����") + "]";
	}

	@Override
	public int getPoint() {
		return 0;
	}

	@Override
	public int getWorth() {
		return 0;
	}

}
