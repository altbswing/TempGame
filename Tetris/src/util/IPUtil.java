package util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class IPUtil {
	public static String IP() {
		Enumeration<NetworkInterface> netInterfaces = null;
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					String ip = ips.nextElement().getHostAddress();
					String iphead = ip.substring(0, 7);
					if(iphead.equals("192.168")){
						return ip;
					}
				}
			}
		} catch (Exception e) {}
		return "127.0.0.1";
	}
	
	public static String classroom(String ip){
		String[] ipad = ip.split("\\.");
		switch (new Integer(ipad[2])) {
			case 1: return "一教-";
			case 2: return "二教-";
			case 3: return "三教-";
			case 4: return "四教-";
			case 5: return "五教-";
			case 6: return "六教-";
			case 7: return "七教-";
			case 8: return "八教-";
			case 9: return "九教-";
			case 16: return "无线-";
			case 30:return "办公室-";
			default:return "其他";
		}
	}
}
