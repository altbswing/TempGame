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
			case 1: return "һ��-";
			case 2: return "����-";
			case 3: return "����-";
			case 4: return "�Ľ�-";
			case 5: return "���-";
			case 6: return "����-";
			case 7: return "�߽�-";
			case 8: return "�˽�-";
			case 9: return "�Ž�-";
			case 16: return "����-";
			case 30:return "�칫��-";
			default:return "����";
		}
	}
}
