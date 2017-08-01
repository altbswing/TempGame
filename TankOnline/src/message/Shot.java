package message;

import java.io.Serializable;

public class Shot implements Serializable{
	private static final long serialVersionUID = -1771182067003261626L;
	
	public static final double MAX_SHOT_SPEED = 30.0;
	public static final double MIN_SHOT_SPEED = 5.0;
	public static final double MAX_SHOT_ANGLE = 75.0;
	public static final double MIN_SHOT_ANGLE = 20.0;
	
	private double angle = 45.0;
	private double power = MIN_SHOT_SPEED;
	private boolean left;
	
	public Shot(){}
	
	public Shot(double angle, double power, boolean left){
		this.angle = angle;
		this.power = power;
		this.left = left;
	}
	
	@Override
	public String toString(){
		return "mis = ["+ angle + "," + power +"]";
	}
	
	/**
	 * 清空能量
	 * @author xiaoE
	 * */
	public void powerFresh(){
		power = MIN_SHOT_SPEED;
	}
	
	/**
	 * 加大角度
	 * @author xiaoE
	 * */
	public void angleUp() {
		angle += 1;
		if (angle > MAX_SHOT_ANGLE){
			angle = MAX_SHOT_ANGLE;
		}
	}
	
	/**
	 * 减小角度
	 * @author xiaoE
	 * */
	public void angleDown() {
		angle -= 1;
		if (angle < MIN_SHOT_ANGLE){
			angle = MIN_SHOT_ANGLE;
		}
	}
	
	/**
	 * 增加力量（初速度）
	 * @author xiaoE
	 * */
	public void powerUp() {
		if(power<MAX_SHOT_SPEED){
			power += 1.0;
		}
	}
	
	public double getAngle() {
		return angle;
	}

	public double getPower() {
		return power;
	}

	public boolean isLeft() {
		return this.left;
	}
	
	public void setLeft(boolean left){
		this.left = left;
	}
}
