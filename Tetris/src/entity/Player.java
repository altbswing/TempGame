package entity;

import java.io.Serializable;

import util.Cheak;

public class Player implements Comparable<Player>, Serializable{
								//����ӿ�		���л��ӿ�
	private static final long serialVersionUID = -2272563320643985355L;
	
	private String name;
	private String date;
	private int point;
	private String ip;
	private String cheakCode;
	
	public Player(){}
	
	public Player(String name,String date, int point) {
		this.name = name;
		this.date = date;
		this.point = point;
	}
	
	/**
	 * ��֤һ�������Ƿ�Ϊ�������� byС��
	 * */
	public boolean isCheat() {
		if(this.cheakCode == null){
			return true;
		}
		return !Cheak.code(this).equals(this.cheakCode);
	}

	public int compareTo(Player o) {
		return ((Player)o).point - point;
	}
	
	// ================ get set ���� ==================
	
	public String getCheakCode() {
		return cheakCode;
	}

	public void setCheakCode(String cheakCode) {
		this.cheakCode = cheakCode;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString(){
		return name+" "+date+" "+point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	public String getName() {
		return name;
	}

	public int getPoint() {
		return point;
	}
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
