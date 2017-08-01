package start;

import java.io.IOException;

import service.Client;
import service.GameClient;
import service.GameServer;
import service.Server;
import view.FrameMain;
import view.PanelGame;

public class Factory {
	
	/**
	 * 组装主机模块
	 * @throws IOException 
	 * */
	public static void createGame(Server server){
		GameServer game = new GameServer(server);
		server.setGame(game);
		PanelGame panel= new PanelGame(game, PanelGame.SERVER);
		new FrameMain(panel,"服务端");
	}
	
	/**
	 * 组装加入机模块
	 * */
	public static void joinGame(Client client){
		GameClient game = new GameClient(client);
		client.setGame(game);
		PanelGame panel= new PanelGame(game, PanelGame.CLIENT);
		new FrameMain(panel,"客户端");
	}
	
	/**
	 * 组装单机游戏模块
	 * */
	public void localGame(){
		//TODO 组装单机游戏模块
		System.out.println("单机游戏");
	}
	
	/**
	 * 测试游戏
	 * */
	public void testGame(){
		//TODO 组装测试游戏模块
		System.out.println("单机游戏");
	}
}
