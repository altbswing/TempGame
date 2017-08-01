package util;

public class AngleMath {
	
	/**
	 * 正切
	 * */
	public static double tg(double angle){
		return Math.tan(toPi(angle));
	}
	
	/**
	 * 余切
	 * */
	public static double ctg(double angle) {
		return 1 / Math.tan(toPi(angle));
	}
	
	/**
	 * 正弦
	 * */
	public static double sin(double angle){
		return Math.sin(toPi(angle));
	}
	
	/**
	 * 余弦
	 * */
	public static double cos(double angle) {
		return Math.sin(toPi(angle)) / Math.tan(toPi(angle));
	}
	
	/**
	 * 正割
	 * */
	public static double sec(double angle) {
		return 1 / cos(toPi(angle));
	}
	
	/**
	 * 余割
	 * */
	public static double csc(double angle) {
		return 1 / sin(toPi(angle));
	}
	
	/**
	 * 角度转弧度
	 * */
	private static double toPi(double angle){
		return angle * (Math.PI / 180);
	}
	
	public static void main(String[] args) {
		System.out.println(Math.PI);
	}
}
