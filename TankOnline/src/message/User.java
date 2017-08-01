package message;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = -8019136525451804662L;
	
	private String ip;
	private String name;
	
	public User(String ip, String name){
		this.ip = ip;
		this.name = name;
	}
	
	public String toString() {
		return "\""+name + "\"("+ ip +")";
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
