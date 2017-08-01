package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** IO 工具类 */
public class IOUtils {
	
	/**
	 * 显示目录的全部文件, 指定扩展名的文件
	 * @param dir
	 */
	public static void showFiles(File dir) {
		File[] files = dir.listFiles(new FileFilter(){
			public boolean accept(File pathname){
				return pathname.isFile();
			}
		});
		System.out.println(dir.getAbsolutePath());
		for(File file : files){
			System.out.println(file.getName());
		}
	}
	
	/**
	 * 获取目录的全部文件
	 * @param dir
	 * @return
	 */
	public static List<File> listFile(File dir) {
		File[] files = dir.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.isFile();
			}
		});
		return new ArrayList<File>(Arrays.asList(files));
	}

	/**
	 * 获取目录的全部文件, 指定扩展名的文件
	 * 
	 * @param dir
	 * @return
	 */
	public static List<File> listFile(File dir, final String ext) {

		File[] files = dir.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.isFile() && pathname.getName().endsWith(ext);
			}
		});
		return new ArrayList<File>(Arrays.asList(files));
	}

	/**
	 * 递归获取目录的全部文件
	 * 
	 * @param dir
	 * @return
	 */
	public static List<File> listAll(File dir) {
		List<File> all = listFile(dir);
		File[] subs = dir.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});
		for (File sub : subs) {
			all.addAll(listAll(sub));
		}
		return all;
	}

	/**
	 * 递归获取目录的全部文件, 指定扩展名的文件
	 * @param dir
	 * @return
	 */
	public static List<File> listAll(File dir, String ext) {
		List<File> all = listFile(dir, ext);
		File[] subs = dir.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});
		for (File sub : subs) {
			all.addAll(listAll(sub, ext));
		}
		return all;
	}

	/**
	 * 复制文件
	 */
	public static void cp(String from, String to) throws IOException {
		cp(new File(from), new File(to));
	}

	/**
	 * 复制文件
	 */
	public static void cp(File from, File to) throws IOException {
		FileInputStream in = new FileInputStream(from);
		OutputStream out = new FileOutputStream(to);
		cp(in, out);
		in.close();
		out.close();
	}

	/**
	 * 复制文件
	 */
	public static void cp(InputStream in, OutputStream out) throws IOException {
		// 1K byte 的缓冲区!
		byte[] buf = new byte[1024];
		int count;
		while ((count = in.read(buf)) != -1) {
			// System.out.println(count);
			out.write(buf, 0, count);
		}
		// in.close();
		// out.close();
	}

	/**
	 * 从流中读取一行文本, 读取到一行的结束为止
	 * 
	 * @param in
	 * @return 一行文本
	 */
	public static String readLine(InputStream in, String charset)
			throws IOException {
		byte[] buf = {};
		int b;
		while (true) {
			b = in.read();
			if (b == '\n' || b == '\r' || b == -1) {// 编码是否是回车换行
				break;
			}
			buf = Arrays.copyOf(buf, buf.length + 1);
			buf[buf.length - 1] = (byte) b;
		}
		if (buf.length == 0 && b == -1)
			return null;
		return new String(buf, charset);
	}

	/**
	 * 读取文件的全部内容到一个byte数组 可以缓存一个"小"文件到堆内存中
	 */
	public static byte[] read(String filename) throws IOException {
		return read(new File(filename));
	}

	/**
	 * 读取文件的全部内容到一个byte数组 可以缓存一个"小"文件到堆内存中
	 */
	public static byte[] read(File file) throws IOException {
		// 用RAF打开文件
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		// 安装文件的长度开辟 缓冲区数组(byte数组)
		byte[] buf = new byte[(int) raf.length()];
		// 读取文件的缓冲区
		raf.read(buf);
		// 关闭文件(RAF)
		raf.close();
		// 返回缓冲区数组引用.
		return buf;
	}

	/**
	 * 读取文件的全部内容到一个byte数组 可以缓存一个"小"文件到堆内存中 
	 * 如: 文件内容: ABC中 读取为: {41, 42, 43, d6,d0}
	 */
	public static byte[] read(InputStream in) throws IOException {
		byte[] ary = new byte[in.available()];
		in.read(ary);
		in.close();
		return ary;
	}

	/**
	 * 将text追加到文件 filename的尾部 使用系统默认文本编码
	 */
	public static void println(String filename, String text) throws IOException {
		println(new File(filename), text);
	}

	public static void println(File file, String text) throws IOException {
		OutputStream out = new FileOutputStream(file, true);
		println(out, text);
		out.close();
	}

	/**
	 * 向流中输出一行字符串, 使用默认编码 不关闭流
	 * 
	 * @param out
	 *            目标流
	 * @param text
	 *            文本
	 * @throws IOException
	 */
	public static void println(OutputStream out, String text)
			throws IOException {
		out.write(text.getBytes());
		out.write('\n');
	}

	/**
	 * 向流中输出一行字符串, 使用指定的编码 不关闭流
	 * 
	 * @param out
	 *            目标流
	 * @param text
	 *            文本
	 * @param charset
	 *            指定的编码
	 * @throws IOException
	 */
	public static void println(OutputStream out, String text, String charset)
			throws IOException {
		out.write(text.getBytes(charset));
		out.write('\n');
	}

	/**
	 * 切分文件, 如: file.dat 切分为 file.dat.0, file.dat.1 ...
	 * 
	 * @param file
	 * @param size
	 *            大小, 以KByte为单位
	 */
	public static void split(String file, int size) throws IOException {
		/** 将文件进行连接 filename 是第一个文件名 ， 如： file.dat.0 */
		if (size <= 0) {
			throw new IllegalArgumentException("搞啥呀!");
		}
		int idx = 0;// 文件的序号
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		OutputStream out = new BufferedOutputStream(new FileOutputStream(file
				+ "." + idx++));
		int b;
		int count = 0;
		while ((b = in.read()) != -1) {
			out.write(b);
			count++;
			if (count % (size * 1024) == 0) {
				out.close();
				out = new BufferedOutputStream(new FileOutputStream(file + "."
						+ idx++));
			}
		}
		in.close();
		out.close();
	}

	/**
	 * 将文件进行连接
	 * 
	 * @param filename
	 *            是第一个文件名,如:file.dat.0
	 */
	public static void join(String file) throws IOException {
		String filename = file.substring(0, file.lastIndexOf("."));
		String num = file.substring(file.lastIndexOf(".") + 1);
		int idx = Integer.parseInt(num);
		OutputStream out = new FileOutputStream(filename);
		File f = new File(filename + "." + idx++);
		while (f.exists()) {
			InputStream in = new FileInputStream(f);
			cp(in, out);
			in.close();
			f = new File(filename + "." + idx++);
		}
		out.close();
	}

	/**
	 * 序列化对象
	 */
	public static byte[] Serialize(Serializable obj) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(os);
		out.writeObject(obj);	// 对象序列化, foo
		out.close();
		byte[] ary = os.toByteArray();
		return ary;
	}
	
	/**
	 * 反序列化
	 * */
	public static Object Unserialize(byte[] data) throws IOException,
			ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(
				data));
		Object o = in.readObject();// 反序列化
		in.close();
		return o;
	}

	/**
	 * 对象的深层赋值(克隆)
	 * 
	 * @param obj
	 * @return 对象的副本
	 * @throws ClassNotFoundException
	 */
	public static Object clone(Serializable obj) throws IOException,
			ClassNotFoundException {
		return Unserialize(Serialize(obj));
	}

	/**
	 * 读取流中到字符数组
	 * 
	 * @param in
	 * @return
	 */
	public static char[] readChar(Reader in) throws IOException {
		StringBuilder buf = new StringBuilder();
		int c;
		while ((c = in.read()) != -1) {
			buf.append((char) c);
		}
		in.close();
		return buf.toString().toCharArray();
	}

	public static char[] readChar(String filename, String encoding)
			throws IOException {
		return readChar(new File(filename), encoding);
	}

	public static char[] readChar(File file, String encoding)
			throws IOException {
		return readChar(new FileInputStream(file), encoding);
	}

	public static char[] readChar(InputStream in, String encoding)
			throws IOException {
		return readChar(new InputStreamReader(in, encoding));
	}
}
