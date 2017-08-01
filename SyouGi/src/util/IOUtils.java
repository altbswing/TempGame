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

/** IO ������ */
public class IOUtils {
	
	/**
	 * ��ʾĿ¼��ȫ���ļ�, ָ����չ�����ļ�
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
	 * ��ȡĿ¼��ȫ���ļ�
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
	 * ��ȡĿ¼��ȫ���ļ�, ָ����չ�����ļ�
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
	 * �ݹ��ȡĿ¼��ȫ���ļ�
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
	 * �ݹ��ȡĿ¼��ȫ���ļ�, ָ����չ�����ļ�
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
	 * �����ļ�
	 */
	public static void cp(String from, String to) throws IOException {
		cp(new File(from), new File(to));
	}

	/**
	 * �����ļ�
	 */
	public static void cp(File from, File to) throws IOException {
		FileInputStream in = new FileInputStream(from);
		OutputStream out = new FileOutputStream(to);
		cp(in, out);
		in.close();
		out.close();
	}

	/**
	 * �����ļ�
	 */
	public static void cp(InputStream in, OutputStream out) throws IOException {
		// 1K byte �Ļ�����!
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
	 * �����ж�ȡһ���ı�, ��ȡ��һ�еĽ���Ϊֹ
	 * 
	 * @param in
	 * @return һ���ı�
	 */
	public static String readLine(InputStream in, String charset)
			throws IOException {
		byte[] buf = {};
		int b;
		while (true) {
			b = in.read();
			if (b == '\n' || b == '\r' || b == -1) {// �����Ƿ��ǻس�����
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
	 * ��ȡ�ļ���ȫ�����ݵ�һ��byte���� ���Ի���һ��"С"�ļ������ڴ���
	 */
	public static byte[] read(String filename) throws IOException {
		return read(new File(filename));
	}

	/**
	 * ��ȡ�ļ���ȫ�����ݵ�һ��byte���� ���Ի���һ��"С"�ļ������ڴ���
	 */
	public static byte[] read(File file) throws IOException {
		// ��RAF���ļ�
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		// ��װ�ļ��ĳ��ȿ��� ����������(byte����)
		byte[] buf = new byte[(int) raf.length()];
		// ��ȡ�ļ��Ļ�����
		raf.read(buf);
		// �ر��ļ�(RAF)
		raf.close();
		// ���ػ�������������.
		return buf;
	}

	/**
	 * ��ȡ�ļ���ȫ�����ݵ�һ��byte���� ���Ի���һ��"С"�ļ������ڴ��� 
	 * ��: �ļ�����: ABC�� ��ȡΪ: {41, 42, 43, d6,d0}
	 */
	public static byte[] read(InputStream in) throws IOException {
		byte[] ary = new byte[in.available()];
		in.read(ary);
		in.close();
		return ary;
	}

	/**
	 * ��text׷�ӵ��ļ� filename��β�� ʹ��ϵͳĬ���ı�����
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
	 * ���������һ���ַ���, ʹ��Ĭ�ϱ��� ���ر���
	 * 
	 * @param out
	 *            Ŀ����
	 * @param text
	 *            �ı�
	 * @throws IOException
	 */
	public static void println(OutputStream out, String text)
			throws IOException {
		out.write(text.getBytes());
		out.write('\n');
	}

	/**
	 * ���������һ���ַ���, ʹ��ָ���ı��� ���ر���
	 * 
	 * @param out
	 *            Ŀ����
	 * @param text
	 *            �ı�
	 * @param charset
	 *            ָ���ı���
	 * @throws IOException
	 */
	public static void println(OutputStream out, String text, String charset)
			throws IOException {
		out.write(text.getBytes(charset));
		out.write('\n');
	}

	/**
	 * �з��ļ�, ��: file.dat �з�Ϊ file.dat.0, file.dat.1 ...
	 * 
	 * @param file
	 * @param size
	 *            ��С, ��KByteΪ��λ
	 */
	public static void split(String file, int size) throws IOException {
		/** ���ļ��������� filename �ǵ�һ���ļ��� �� �磺 file.dat.0 */
		if (size <= 0) {
			throw new IllegalArgumentException("��ɶѽ!");
		}
		int idx = 0;// �ļ������
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
	 * ���ļ���������
	 * 
	 * @param filename
	 *            �ǵ�һ���ļ���,��:file.dat.0
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
	 * ���л�����
	 */
	public static byte[] Serialize(Serializable obj) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(os);
		out.writeObject(obj);	// �������л�, foo
		out.close();
		byte[] ary = os.toByteArray();
		return ary;
	}
	
	/**
	 * �����л�
	 * */
	public static Object Unserialize(byte[] data) throws IOException,
			ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(
				data));
		Object o = in.readObject();// �����л�
		in.close();
		return o;
	}

	/**
	 * �������㸳ֵ(��¡)
	 * 
	 * @param obj
	 * @return ����ĸ���
	 * @throws ClassNotFoundException
	 */
	public static Object clone(Serializable obj) throws IOException,
			ClassNotFoundException {
		return Unserialize(Serialize(obj));
	}

	/**
	 * ��ȡ���е��ַ�����
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
