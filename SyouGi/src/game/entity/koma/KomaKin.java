package game.entity.koma;

import java.awt.Image;

import cfg.GameConfig;
import cfg.setter.ImageReader;

public class KomaKin extends Koma {

	public KomaKin(boolean mine) {
		this.mine = mine;
	}

	@Override
	public Image getImage() {
		return mine ? ImageReader.KOMA_IMG[2][1] : ImageReader.KOMA_IMG[2][0];
	}

	@Override
	public int code() {
		return Koma.KIN;
	}

	@Override
	public String toString() {
		return "[" + (mine ? "▲金" : "▼金") + "]";
	}

	@Override
	public boolean nari(int karaY, int heY) {
		return false;
	}

	@Override
	public int getPoint() {
		return GameConfig.KOMA_POINT[Koma.KIN];
	}

	@Override
	public int getWorth() {
		return GameConfig.KOMA_WORTH[Koma.KIN];
	}

}
