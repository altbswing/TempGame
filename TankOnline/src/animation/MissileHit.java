package animation;

import java.awt.Point;

public class MissileHit implements Runnable{

	private Point imgIdx;
	private int location;
	
	public MissileHit(int location){
		this.location = location;
	}
	//TODO Rational Rose
	public void run() {
		imgIdx = new Point(0,0);
		for (int i = 0; i < 15; i++) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {}
			imgIdx.x++;
			if(imgIdx.x > 4){
				imgIdx.x = 0;
				imgIdx.y += 1;
			}
		}
	}

//===========================================
	
	public Point getImgIdx() {
		return imgIdx;
	}

	public void setImgIdx(Point imgIdx) {
		this.imgIdx = imgIdx;
	}

	public int getLocation() {
		return location;
	}
}
