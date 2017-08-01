package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	/**
	 * 按格式返回系统时间
	 * @author 小翼
	 * */
	public static String now(String timeFmt){
		SimpleDateFormat fmt = new SimpleDateFormat(timeFmt);
		Date now = new Date();
		String date = fmt.format(now);
		return date;
	}
	
	/**
	 * 在字符串前添加系统时间HH:mm:ss
	 * @author 小翼
	 * */
	public static String timeMessage(String str){
		return now("HH:mm:ss") + " " + str;
	}
	
	/**
	 * 输出一句带系统时间HH:mm:ss的字符串
	 * @author 小翼
	 * */
	public static void message(int i){
		message(new Integer(i).toString());
	}
	
	/**
	 * 输出一句带系统时间HH:mm:ss的字符串
	 * @author 小翼
	 * */
	public static void message(String str){
		System.out.println(now("HH:mm:ss")+"　"+str);
	}
	
	/**
	 * 测试
	 * */
	public static void main(String[] args) {
		System.out.println("src"+now("yyyyMMddHHmm"));
	}
}
