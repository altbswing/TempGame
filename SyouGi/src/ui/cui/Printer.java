package ui.cui;

import game.agent.GameAgent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Printer {
	
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
	public static void message(Object obj){
		System.out.println(now("HH:mm:ss")+"　"+ obj.toString());
	}
	
	/**
	 * 输出当前棋盘状态，将棋专用
	 * */
	public static void stateMessage(String str, int state) {
		String stateMsg = "";
		switch (state) {
		case GameAgent.CAN_NOT_CONTROL:
			stateMsg = str + "当前状态：【不能操作】";
			break;
		case GameAgent.NULL:
			stateMsg = str + "当前状态：【空手】";
			break;
		case GameAgent.BAN_KARA_MOTI:
			stateMsg = str + "当前状态：【从“棋盘”上拿起一个棋子】";
			break;
		case GameAgent.DAI_KARA_MOTI:
			stateMsg = str + "当前状态：【从“棋台”上拿起一个棋子】";
			break;
		default:
			break;
		}
		message(stateMsg);
	}
}
