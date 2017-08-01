package message;

import java.io.Serializable;

/**
 * 服务器和客户端的通讯数据包编号
 * @author xiaoE
 * */
public class Message implements Serializable{
	private static final long serialVersionUID = -1783293105255607859L;
	
	public static final int LINK = 0;
	public static final int LINK_OK = 1;
	public static final int READY = 2;
	public static final int START = 3;
	public static final int WIND = 4;
	public static final int PLAYER = 5;
	public static final int P1_MOVE = 6;
	public static final int P2_MOVE = 7;
	public static final int P1_SHOT = 8;
	public static final int P2_SHOT = 9;
	public static final int ANIMATION_DONE = 10;
	public static final int YOU_CAN_CTRL = 11;
	public static final int YOU_CAN_T_CTRL = 12;
	public static final int ACTION = 13;
	public static final int GAME_START = 14;
	public static final int WINNER = 15;

	public static final int P2_DOUBLE = 16;
	public static final int P2_RAGE = 17;
	public static final int P2_SHIELD = 18;
	public static final int P2_SPEED = 19;
	public static final int HIT = 20;
	
	private int id;
	private Object obj;
	
	public Message(int id){
		this.id = id;
	}
	
	public Message(int id, Object obj){
		this.obj = obj;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
