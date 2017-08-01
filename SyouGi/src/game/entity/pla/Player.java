package game.entity.pla;

import java.awt.Image;

import cfg.GameConfig;
import cfg.setter.ImageReader;

public class Player {
	
	private Image face;
	private String name;
	private int firstTime;
	private int secondTmie;
	private int point;
	private int worth;
	
	public Player() {
		face = ImageReader.FACE_IMG.get(27);
		name = "Player";
		firstTime = 0;
		secondTmie = 0;
		point = GameConfig.INI_POINT;
		worth = GameConfig.INI_WORTH;
	}

	public Image getFace() {
		return face;
	}

	public void setFace(Image face) {
		this.face = face;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(int firstTime) {
		this.firstTime = firstTime;
	}

	public int getSecondTime() {
		return secondTmie;
	}

	public void setSecondTmie(int secondTmie) {
		this.secondTmie = secondTmie;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getWorth() {
		return worth;
	}

	public void setWorth(int worth) {
		this.worth = worth;
	}
	
}
