package message;

import java.io.Serializable;

import service.GameServer;


public class Wind implements Runnable, Serializable{
	private static final long serialVersionUID = 6875023751217547875L;
	
	private int speed = 0;		// 风速
	private int location = -560;
	private static final long SLEEP = GameServer.SLEEP * 2;
	
	public Wind(){
		
	}
	
	public String toString(){
		return speed + "";
		
	}
	
	/**
	 * 改变风速
	 * @author xiaoE
	 * */
	public void change(int speed) {
		this.speed = speed;
	}
	
	public void run() {
		while(true){
			try {
				location += (speed*2);
				if(location < -560 ){
					location = 0;
				}else if(location > 0){
					location = -560;
				}
				Thread.sleep(SLEEP);
			} catch (InterruptedException e) {}
		}
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getLocation() {
		return location;
	}
}
