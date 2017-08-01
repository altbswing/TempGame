package main;

import service.GameService;
import ui.FrameGame;
import ui.FrameOver;
import ui.PanelGame;
import dao.DataBase;

public class Factory {

	public static void startGame() {
		GameService game = new GameService();
		PanelGame panel = new PanelGame(game);
		game.setPanel(panel);
		DataBase DB = new DataBase(game);
		game.setDB(DB);
		FrameOver over = new FrameOver(game);
		game.setOver(over);
		FrameGame frame = new FrameGame(panel);
		frame.setVisible(true);
	}
}
