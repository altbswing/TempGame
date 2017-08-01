package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LineCount {

	public final static long CODE_LINE;
	public final static long DOC_LINE;
	public final static long SP_LINE;
	
	static {
		CountNumber cn = new CountNumber();
		treeFile(new File("src"), cn);
		CODE_LINE = cn.code;
		DOC_LINE = cn.doc;
		SP_LINE = cn.sp;
	}
	
	/**
	 * 
	 * 查找出一个目录下所有的.java文件
	 * @param f
	 *            要查找的目录
	 */
	private static void treeFile(File f, CountNumber cn) {
		File[] childs = f.listFiles();
		for (int i = 0; i < childs.length; i++) {
			if("LineCount.java".equals(childs[i].getName())) {
				continue;
			}
			if (!childs[i].isDirectory()) {
				if (childs[i].getName().matches(".*\\.java$")) {
//					System.out.println(childs[i].getName());
					sumCode(childs[i], cn);
				}
			} else {
				treeFile(childs[i], cn);
			}
		}
	}

	/**
	 * 
	 * 计算一个.java文件中的代码行，空行，注释行
	 * @param file
	 *            要计算的.java文件
	 */
	private static void sumCode(File file, CountNumber cn) {
		BufferedReader br = null;
		boolean comment = false;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = "";
			try {
				while ((line = br.readLine()) != null) {
					line = line.trim();
					if (line.matches("^[\\s&&[^\\n]]*$")) {
						cn.sp++;
					} else if (line.startsWith("/*") && !line.endsWith("*/")) {
						cn.doc++;
						comment = true;
					} else if (true == comment) {
						cn.doc++;
						if (line.endsWith("*/")) {
							comment = false;
						}
					} else if (line.startsWith("//")) {
						cn.doc++;
					} else {
						cn.code++;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("代码行：" + LineCount.CODE_LINE);
		System.out.println("注释行：" + LineCount.DOC_LINE);
		System.out.println("空行：" + LineCount.SP_LINE);
	}
}

class CountNumber {
	public long code;
	public long doc;
	public long sp;
}
