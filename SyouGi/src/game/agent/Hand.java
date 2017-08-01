package game.agent;

import game.entity.koma.Koma;

import util.Point;

public class Hand {
	
	private Point kara = null;
	private Koma koma = null;
	
	public Hand() {
		this.kara = null;
		this.koma = null;
	}
	
	public Hand(Point kara) {
		this.kara = kara;
	}
	
	public Hand(Koma koma) {
		this.koma = koma;
	}
	
	public void reset(){
		this.kara = null;
		this.koma = null;
	}
	
	public void setKara(Point kara) {
		this.kara = kara;
	}

	public Koma getKoma() {
		return koma;
	}

	public void setKoma(Koma koma) {
		this.koma = koma;
	}
	
	public Point getKara() {
		return kara;
	}

	public boolean isEmpty() {
		return this.kara == null && this.koma == null;
	}
}
