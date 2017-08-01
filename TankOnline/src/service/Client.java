package service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import message.Message;
import message.Player;
import message.Shot;
import message.User;
import start.Factory;
import start.FrameLink;
import util.IPUtil;

public class Client implements Runnable {

	private GameClient game;

	private FrameLink frame;
	private Socket s;
	private String ip;
	private int port;
	private ObjectOutputStream clientOut;
	private ObjectInputStream clientIn;

	public Client(FrameLink frame) throws Exception {
		this.frame = frame;
		this.ip = frame.joinIp()[0];
		this.port = new Integer(frame.joinIp()[1]);
		s = new Socket(ip, port);
		clientOut = new ObjectOutputStream(s.getOutputStream());
		clientIn = new ObjectInputStream(s.getInputStream());
		new Thread(this).start();
		linkToServer();
	}

	public void run() {
		while (true) {
			try {
				Message messIn;
				messIn = (Message) clientIn.readObject();
				messIn(messIn);
			} catch (Exception e) {
				frame.showMessage(e.toString() + "111111");
			}
		}
	}

	/**
	 * 连接到服务器
	 * 
	 * @author xiaoE
	 */
	public void linkToServer() throws Exception {
		User u = new User(IPUtil.IP(), frame.joinName());
		messOut(new Message(Message.LINK, u));
	}

	/**
	 * 向服务器发送准备就绪信息
	 * 
	 * @author xiaoE
	 * */
	public void ready() throws Exception {
		messOut(new Message(Message.READY));
	}

	/**
	 * 客户端发送信息处理
	 * 
	 * @author xiaoE
	 * */
	public void messOut(Message messOut) {
		try {
			clientOut.writeObject(messOut);
			clientOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 客户端接收信息处理
	 * 
	 * @author xiaoE
	 * */
	private void messIn(Message messIn) {
		switch (messIn.getId()) {
		case Message.LINK_OK:
			frame.showMessage("已连接到" + (String) messIn.getObj());
			frame.showMessage("请点击\"准备就绪\"");
			break;
		case Message.START:
			frame.setVisible(false);
			Factory.joinGame(this);
			break;
		case Message.GAME_START:
			game.gameStart();
		case Message.WIND:
			game.setWind((Integer) messIn.getObj());
			break;
		case Message.PLAYER:
			Player[] plas = (Player[]) messIn.getObj();
			game.setP1(plas[0]);
			game.setP2(plas[1]);
			break;
		case Message.P1_MOVE:
			game.p1().move((Integer) messIn.getObj());
			break;
		case Message.P2_MOVE:
			game.p2().move((Integer) messIn.getObj());
			break;
		case Message.P1_SHOT:
			game.printShot((Shot) messIn.getObj());
			break;
		case Message.YOU_CAN_CTRL:
			game.setCanCtrl(true);
			break;
		case Message.YOU_CAN_T_CTRL:
			game.setCanCtrl(false);
			break;
		case Message.ACTION:
			game.setP1Action((Boolean) (messIn.getObj()));
			break;
		case Message.WINNER:
			game.setWinner((Integer) (messIn.getObj()));
		default:
			break;
		}
	}

	public void setGame(GameClient game) {
		this.game = game;
	}
}
