package util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * һ�����ݱ�����srcĿ¼
 * ���� IOUtil by������
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
	 * ����srcĿ¼�ĸ���
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
	 * ����src
	 * @author xiaoE
	 * @throws IOException 
	 * */
	public static void backup() throws IOException{
		File src = new File("src");
	    String targetPath = "src.bak\\"+ now("yyyy��MM��dd��HH��mm��ss��");
	    File target = new File(targetPath);
	    createBackDir(src, target);
		List<File> files = IOUtils.listAll(src);
		for (int i = 0; i < files.size(); i++) {
			String temp = files.get(i).getPath();
			String targetTemp = target + temp.substring(temp.indexOf("src")+"src".length());
			IOUtils.cp(temp, targetTemp);
			message(files.get(i).getName()+"   �ѱ��ݵ� " +targetTemp);
		}
	}
	
	/**
	 * ����ʽ����ϵͳʱ��
	 * @author С��
	 * */
	public static String now(String timeFmt){
		SimpleDateFormat fmt = new SimpleDateFormat(timeFmt);
		Date now = new Date();
		String date = fmt.format(now);
		return date;
	}
	
	/**
	 * ���һ���ϵͳʱ��HH:mm:ss���ַ���
	 * @author С��
	 * */
	public static void message(String str){
		System.out.println(now("HH:mm:ss")+"��"+str);
	}
}
