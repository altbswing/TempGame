package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import service.Game;

/**
 * 游戏控制器
 * @author xiaoE
 * */
public class Control implements KeyListener {
	
	private Game game;

	public Control(Game game){
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_3 ){
//			System.out.println("技能3");
			game.skillShield();
		}
		if (game.canCtrl()) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP: 		// 上-增加角度
				game.angleUp();
				break;
			case KeyEvent.VK_DOWN: 	// 下-减小角度
				game.angleDown();
				break;
			case KeyEvent.VK_LEFT: 	// 左-移动
				game.move(-1);
				break;
			case KeyEvent.VK_RIGHT: 	// 右-移动
				game.move(1);
				break;
			case KeyEvent.VK_1: 		// 技能1
				game.skillDouble();
				break;
			case KeyEvent.VK_2: 		// 技能2
				game.skillRage();
				break;
			case KeyEvent.VK_4: 		// 技能3
				game.skillSpeed();
				break;
			case KeyEvent.VK_W: 		// 上-增加角度
				game.angleUp();
				break;
			case KeyEvent.VK_S: 	// 下-减小角度
				game.angleDown();
				break;
			case KeyEvent.VK_A: 	// 左-移动
				game.move(-1);
				break;
			case KeyEvent.VK_D: 	// 右-移动
				game.move(1);
				break;
			case KeyEvent.VK_SPACE: 	// 空格-蓄力
				game.powerUp();
				break;
			default:
				break;
			}
		} else if(e.getKeyCode() == KeyEvent.VK_ENTER 
				&& game.getWinner() != Game.GO_ON){
			game.start();
		} 
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE && game.canCtrl()) {
			game.shot();
		}
	}

	public void keyTyped(KeyEvent e) {}
	
}
