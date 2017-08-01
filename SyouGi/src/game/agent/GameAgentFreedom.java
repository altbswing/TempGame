package game.agent;

import game.entity.koma.Koma;

import org.dom4j.DocumentException;

import ui.window.main.PanelContext;
import util.Point;
import cfg.setter.GameXmlReader;

public class GameAgentFreedom extends GameAgent{
	
	public GameAgentFreedom(PanelContext panelContext) {
		super(panelContext);
		try {
			gameInit(GameXmlReader.readSetter());
		} catch (DocumentException e) {
			// TODO 该异常日后抛给界面层，或内部处理
			e.printStackTrace();
		}
		setState(NULL);
	}
	
	@Override
	public void clickBan(int x, int y) {
		switch (state) {
		case NULL:
			komaMotteBan(x, y); 
			break;
		case BAN_KARA_MOTI:
			moveKoma(x, y); 
			break;
		case DAI_KARA_MOTI:
			setKoma(x, y); 
			break;
		default:
			break;
		}
		panelContext.repaintGame();
	}
	
	@Override
	public void clickDai(int komaType, boolean isMine) {
		switch (state) {
		case NULL:
			komaMotteDai(komaType, isMine); 
			break;
		case BAN_KARA_MOTI:
			resetState();
			komaMotteDai(komaType, isMine); 
			break;
		case DAI_KARA_MOTI:
			resetState();
			komaMotteDai(komaType, isMine);
			break;
		default:
			break;
		}
		panelContext.repaintGame();
	}
	
	@Override
	public void moveKoma(Point from, Point to) {
		Koma koma = hand.getKoma();
		ban[to.x][to.y] = koma;
		ban[from.x][from.y] = null;
		changeOuPoint(koma, to);
		showShadow = false;
		panelContext.repaintGame();
		if(koma.nari(from.y, to.y) && panelContext.nariWinYes()) {
			koma.setNari();
		}
		setOuTe();
		resetState();
	}
	
	@Override
	public void setKoma(int x, int y) {
		if(shadow[x][y]) {
			ban[x][y] = hand.getKoma();
			if(hand.getKoma().isMine()) {
				dai[1][hand.getKoma().code()]--;
			} else {
				dai[0][hand.getKoma().code()]--;
			}
		}
		setOuTe();
		resetState();
	}
	
	public void rightMouse() {
		resetState();
		panelContext.repaintGame();
	}
	
	/**
	 * 设置王将的危险格
	 * */
	private void setOuTe() {
		ouTe[0] = (ouP[0] != null && 
				ban[ouP[0].x][ouP[0].y].ouTeKakunin(ban, ouP[0]));
		ouTe[1] = (ouP[1] != null && 
				ban[ouP[1].x][ouP[1].y].ouTeKakunin(ban, ouP[1]));
	}

	/**
	 * 尝试从[棋盘]上拿起一个棋子
	 * */
	private void komaMotteBan(int x, int y) {
		if(ban[x][y] != null) {
			ban[x][y].createMoveShadow(shadow, ban, new Point(x, y));
			hand.setKara(new Point(x, y));
			hand.setKoma(ban[x][y]);
			showShadow = true;
			setState(BAN_KARA_MOTI);
		} else {
			resetState();
		}
	}

	/**
	 * 尝试从[棋台]上拿起一个棋子
	 * */
	private void komaMotteDai(int komaType, boolean isMine) {
		if(isMine ? dai[1][komaType] > 0 : dai[0][komaType] > 0) {
			Koma koma = Koma.createKoma(komaType, isMine);
			koma.createSetShadow(shadow, ban);
			hand.setKoma(koma);
			showShadow = true;
			setState(DAI_KARA_MOTI);
		}
	}
	
	/**
	 * 尝试手中的棋子移动到x, y
	 * */
	private void moveKoma(int x, int y) {
		if(shadow[x][y]) {//在行走范围内
			if(ban[x][y] == null) {//点空
				moveKoma(hand.getKara(), new Point(x, y));
			} else if(ban[x][y].isMine() != hand.getKoma().isMine()
					&& hand.getKara() != null) {//点对方 吃子
				if(ban[x][y].isMine()) {	//正方向
					dai[0][ban[x][y].code()]++;
				} else {					//反方向
					dai[1][ban[x][y].code()]++;
				}
				moveKoma(hand.getKara(), new Point(x, y));
			} else {//点自己
				resetState();
			}
		} else {	//在行走范围外
			if(ban[x][y] == null) {//点空
				resetState();
			} else {//点子
				resetState();
				komaMotteBan(x, y);
			}
		}
		setPointAndWorth();
	}
}
