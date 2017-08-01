package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	/**
	 * ����ϵͳʱ�䣬24ʱ:��:��
	 * */
	public static String now(){
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
		Date now = new Date();
		String date = fmt.format(now);
		return date;
	}
	
	/**
	 * ����ϵͳʱ�䣬��-��-��
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
		System.out.println(now()+"��"+"no message");
	}
}
