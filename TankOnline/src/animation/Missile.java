package animation;

import java.awt.Point;

import message.Shot;
import service.Game;
import util.AngleMath;

public class Missile extends Point implements Runnable {
	private static final long serialVersionUID = 3837805811945968056L;

	public static final double METER = 20.0; // 像素/米
	public static final double G = 9.8; // 不解释

	private AudioPlayWave shotAd = new AudioPlayWave("Audio\\shot.wav");
	private AudioPlayWave hitAd = new AudioPlayWave("Audio\\cannon.wav");
	private double tgAngle = 1; // 导弹与地平线的夹角(弧度)
	private double wind;

	private double vX = 0.0;
	private double vY = 0.0;
	private Point imgIdx = null;

	private Game game;

	private boolean show = true;
	private boolean left = false;

	private static final long SLEEP = Game.SLEEP;

	public Missile(int x, int wind, Shot shot, Game game) {
		super(x, 405);
		this.game = game;
		this.wind = wind;
		this.left = shot.isLeft();
		tgAngle = AngleMath.tg(shot.getAngle());
		vX = shot.getPower() * AngleMath.cos(shot.getAngle());
		vX = left ? -vX : vX;
		vY = shot.getPower() * AngleMath.sin(shot.getAngle());
	}

	@Override
	public String toString() {
		return "mis = [" + x + "," + y + "]\t" + "img = [" + imgIdx.x + ","
				+ imgIdx.y + "]";
	}

	/**
	 * 抛物线计算 by小翼
	 * */
	public void run() {
		shotAd.start();
		Point o = new Point(this.x, this.y);
		double t; // 运动总时间,ms
		try {
			for (int i = 0; y < 416; i++) {
				t = i * SLEEP / 1000.0; // 毫秒转换为秒
				this.x = (int) (o.x + (vX + wind * t) * t * METER);
				this.y = (int) (o.y - (vY - G * t) * t * METER);
				this.tgAngle = derivative(t * vX * METER);
				Thread.sleep(SLEEP);
			}
			y = 416;
			hitAd.start();
			this.show = false;
			this.imgIdx = new Point(0, 0);
			game.hit(x + 6);
			for (int i = 0; i < 15; i++) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
				}
				imgIdx.x++;
				if (imgIdx.x > 4) {
					imgIdx.x = 0;
					imgIdx.y += 1;
				}
			}
			this.imgIdx = null;
			Thread.sleep(1000);
			game.shotFinish();
		} catch (Exception e) {
		}
	}

	/**
	 * 抛物线的导函数
	 * */
	private double derivative(double x) {
		double y = (vY / vX) - ((2 * G) / (vX * vX * METER)) * x;
		return y;
	}

	// ==================================================

	public void setTgAngle(double tgAngle) {
		this.tgAngle = tgAngle;
	}

	public double getTgAngle() {
		return tgAngle;
	}

	public void setVX(double vX) {
		this.vX = vX;
	}

	public void setVY(double vY) {
		this.vY = vY;
	}

	public void setWind(double w) {
		this.wind = w;
	}

	public boolean isShow() {
		return this.show;
	}

	public boolean isLeft() {
		return this.left;
	}

	public Point getImgIdx() {
		return imgIdx;
	}
}


