package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import entity.Player;

public class Cheak {
	public static byte[] toCheak(byte[] data) {
		List<String> l;
		l = new ArrayList<String>();
		l.add("1");
		l = new LinkedList<String>();
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(data);
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return new byte[] {};
	}

	public static String toCheak(String data) {
		try {
			byte[] md5 = toCheak(data.getBytes("utf-8"));
			return toHS(md5);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String toHS(byte[] md5) {
		StringBuilder buf = new StringBuilder();
		for (byte b : md5) {
			buf.append(lp(Integer.toHexString(b & 0xff), '0', 2));
		}
		return buf.toString();
	}

	public static String lp(String hex, char c, int size) {
		char[] cs = new char[size];
		Arrays.fill(cs, c);
		System.arraycopy(hex.toCharArray(), 0, cs, cs.length - hex.length(),
				hex.length());
		return new String(cs);
	}
	
	/**
	 * ���һ����֤�ţ���֤�Ƿ�Ϊ�������
	 * */
	public static String code(Player p){
		int rp = p.getPoint()/10;
		while(rp >= 10){
			rp = rp%10;
		}
		String str = p.getName() + p.getDate() + p.getPoint();
//		System.out.println(str+" r="+rp);
		return toCheak(StringUtils.repeat(str, rp)).substring(24,28);
	}

	public static void main(String[] args) {
		System.out.println(toCheak("1984010289"));
		//00-25-D3-11-A0-92
		//SH2811
	}
}
