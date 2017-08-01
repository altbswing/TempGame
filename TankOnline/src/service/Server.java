package service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

import message.Message;
import message.Shot;
import message.User;
import start.Factory;
import start.FrameLink;

/**
 * 服务器，等待主机连接
 * @author xiaoE
 * */
public class Server implements Runnable{
	
	private GameServer game;
	
	private ServerSocket serverSocket;
	private Socket socket;
	private FrameLink frame;
	private ObjectInputStream serverIn;
	private ObjectOutputStream serverOut;
	
	private String joinName;
	private String mainName;
	
	public Server(FrameLink frame){
		this.frame = frame;
		int port = new Integer(frame.mainIp()[1]);
		try {
			frame.showMessage("你的IP: "+frame.mainIp()[0]+":"+port);
//			frame.showMessage("正在创建游戏……");
			serverSocket = new ServerSocket(port);
			frame.showMessage("游戏已创建，等待玩家加入");
			new Thread(this).start();
		} catch (BindException e) {
			frame.showMessage("错误：端口号冲突，请尝试更改默认端口");
			frame.linkButtonSwitch(true);
		} catch (IOException e) {
			frame.showMessage(e.toString());
			frame.linkButtonSwitch(true);
		}
	}

	public void run() {
		try {
			socket = serverSocket.accept();
			serverIn = new ObjectInputStream(socket.getInputStream());
			serverOut = new ObjectOutputStream(socket.getOutputStream());
			while (true) {
				Message messIn = (Message) serverIn.readObject();
				messIn(messIn);
			}
		} catch (Exception e) {
			frame.showMessage(e.toString());
		}
	}

	/**
	 * 服务器输入信息包处理
	 * @author xiaoE
	 * */
	public void messIn(Message messIn) {
		try {
			switch (messIn.getId()) {
			case Message.LINK:
				joinName = ((User) (messIn.getObj())).getName();
				frame.showMessage(((User) (messIn.getObj())).toString() + "加入了游戏");
				messOut(new Message(Message.LINK_OK, frame.mainIp()[0]));
				break;
			case Message.READY:
				frame.showMessage("\""+ joinName + "\"已准备就绪");
				frame.startOn();
				break;
			case Message.P2_MOVE:
				int i = (Integer)messIn.getObj();
				game.p2().move(i);
				break;
			case Message.ANIMATION_DONE:
				game.setClientAnmOK(true);
				break;
			case Message.P2_SHOT:
				game.printShot((Shot)messIn.getObj());
				break;
			case Message.P2_DOUBLE:
				game.p2().skillDouble();
				game.syncPlayer();
				break;
			case Message.P2_RAGE:
				game.p2().skillRage();
				game.syncPlayer();
				break;
			case Message.P2_SHIELD:
				game.p2().skillShield();
				game.syncPlayer();
				break;
			case Message.P2_SPEED:
				game.p2().skillSpeed();
				game.syncPlayer();
				break;
			case Message.HIT:
				game.setJoinHit(true);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			frame.showMessage(e.toString());
		}
	}
	
	/**
	 * 开始游戏
	 * @author xiaoE
	 * */
	public void startGame() {
		Message start = new Message(Message.START);
		messOut(start);
		frame.setVisible(false);
		Factory.createGame(this);
	}
	
	/**
	 * 服务器输出信息包处理
	 * @author xiaoE
	 * */
	public void messOut(Message messOut) {
		try{
			serverOut.writeObject(messOut);
			serverOut.flush();
		}catch (Exception e) {
			frame.showMessage(e.toString());
			e.printStackTrace();
		}
	}
	
//	===================================================
	
	public void setGame(GameServer game){
		this.game = game;
	}
	
	public String getJoinName(){
		return this.joinName;
	}
	
	public String getMainName(){
		return this.mainName;
	}
	
	public void setMainName(String mainName){
		this.mainName = mainName;
	}
}
