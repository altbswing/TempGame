package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	/**
	 * 返回系统时间，24时:分:秒
	 * */
	public static String now(){
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
		Date now = new Date();
		String date = fmt.format(now);
		return date;
	}
	
	/**
	 * 返回系统时间，年-月-日
	 * */
	public static String today(){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String date = fmt.format(today);
		return date;
	}
	
	public static void message(int i){
		message(new Integer(i).toString());
	}
	
	public static void message(String str){
		System.out.println(now()+"　"+"no message");
	}
}
