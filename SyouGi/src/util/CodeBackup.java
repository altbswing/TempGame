package util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 一键备份本工程src目录
 * 依赖 IOUtil by刘苍松
 * @author xiaoE
 * */
public class CodeBackup {
	
	public static void main(String[] args) throws IOException {
		File dir = new File("src.bak");
		if(!dir.exists()){
			dir.mkdir();
		}
	    backup();
	}
	
	/**
	 * 创建src目录的副本
	 * @author xiaoE
	 * */
	public static void createBackDir(File src, File target) {
		if (!target.exists()) {
			target.mkdir();
		}
		File[] files = src.listFiles();
		for (File file : files) {
			if (file.isDirectory())
				createBackDir(file, new File(target, file.getName()));
		}
	}
	
	/**
	 * 备份src
	 * @author xiaoE
	 * @throws IOException 
	 * */
	public static void backup() throws IOException{
		File src = new File("src");
	    String targetPath = "src.bak\\"+ now("yyyy年MM月dd日HH点mm分ss秒");
	    File target = new File(targetPath);
	    createBackDir(src, target);
		List<File> files = IOUtils.listAll(src);
		for (int i = 0; i < files.size(); i++) {
			String temp = files.get(i).getPath();
			String targetTemp = target + temp.substring(temp.indexOf("src")+"src".length());
			IOUtils.cp(temp, targetTemp);
			message(files.get(i).getName()+"   已备份到 " +targetTemp);
		}
	}
	
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
	 * 输出一句带系统时间HH:mm:ss的字符串
	 * @author 小翼
	 * */
	public static void message(String str){
		System.out.println(now("HH:mm:ss")+"　"+str);
	}
}
