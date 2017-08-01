package util;

import java.util.Arrays;

public class StrUtil {
	
	/**
	 * ʵ��leftPad����, ���ַ�ʵ�������
	 * 
	 * @param str
	 *            ������ַ�: 5
	 * @param ch
	 *            ����ַ�: #
	 * @param length
	 *            ����Ժ�ĳ���: 8
	 * @return "#######5"
	 */
	public static String leftPad(String str, char ch, int length) {
		if (str.length() == length) {
			return str;
		}
		char[] chs = new char[length];
		Arrays.fill(chs, ch);
		System.arraycopy(str.toCharArray(), 0, chs, length - str.length(),
				str.length());
		return new String(chs);
	}
	
	public static String leftPad(int i, char ch, int length) {
		return leftPad(i + "", ch, length);
	}
	
	/**
	 * 
	 * */
	public static String toTime(int t) {
		String mm = t / 60 + "";
		String ss = t % 60 + "";
		if(ss.length() < 2) {
			ss = "0" + ss;
		}
		return mm + ":" + ss;
	}
	
	/**
	 * 半角判断
	 * */
	public static boolean isHafe(char c) {
		return c <= 126 || c >= 65382;
	}
	
	/**
	 * ����byte �����ȫ������Ϊ�ַ�, ��hex(ʮ�����)��ʽ���� 
	 * ��: ����{0x41, 0x42, 0x43, 0xd6, 0xd0}
	 * ���: "[41, 42, 43, d6, d0]"
	 */
	public static String join(byte[] ary) {
		if (ary == null || ary.length == 0)
			return "[]";
		StringBuilder buf = new StringBuilder();
		buf.append("[").append(
				leftPad(Integer.toHexString(ary[0] & 0xff), '0', 2));
		for (int i = 1; i < ary.length; i++) {
			String hex = Integer.toHexString(ary[i] & 0xff);
			buf.append(",").append(leftPad(hex, '0', 2));
		}
		buf.append("]");
		return buf.toString();
	}

	public static String toBinString(byte[] ary) {
		if (ary == null || ary.length == 0)
			return "[]";
		StringBuilder buf = new StringBuilder();
		buf.append("[").append(
				leftPad(Integer.toBinaryString(ary[0] & 0xff), '0', 8));
		for (int i = 1; i < ary.length; i++) {
			String hex = Integer.toBinaryString(ary[i] & 0xff);
			buf.append(",").append(leftPad(hex, '0', 8));
		}
		buf.append("]");
		return buf.toString();
	}
}
