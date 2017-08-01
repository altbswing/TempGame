package cfg;

import java.awt.Rectangle;

import cfg.setter.ImageReader;

public class GuiConfig {
	
	private static int SPA = 8;
	private static int WIN_W = 6;
	private static int WIN_H = 32;
	private static int BAN_W = ImageReader.BAN_IMG.getWidth(null);
	private static int BAN_H = ImageReader.BAN_IMG.getHeight(null);
	private static int DAI_W = ImageReader.DAI_IMG.getWidth(null);
	private static int DAI_H = ImageReader.DAI_IMG.getHeight(null);
	private static int BTN_W = BAN_W + DAI_W * 2 + SPA * 2;
	private static int BTN_H = 64;
	private static int BAN_X = DAI_W + SPA * 2;
	private static int BAN_Y = SPA * 2 + BTN_H;
	private static int DAI_X_1 = BAN_X + BAN_W + SPA;
	private static int DAI_Y_1 = BAN_Y + BAN_H - DAI_H;
	private static int CHAT_X = DAI_X_1 + DAI_W + SPA;
	private static int VIEW_H = BAN_H + BTN_H + SPA * 3;
	private static int VIEW_W = VIEW_H / 9 * 16;
	private static int CHAT_W = VIEW_W - CHAT_X - SPA;
	private static int CHAT_H = DAI_Y_1 + DAI_H - SPA;
	
	public static final int BAN_CENTER = BAN_Y + BAN_H / 2;
	public static final int MAIN_FRAME_H = VIEW_H + WIN_H;
	public static final int MAIN_FRAME_W = VIEW_W + WIN_W;
	
	public static final Rectangle BTN_LAY_RECT = new Rectangle(SPA, SPA, BTN_W, BTN_H);
	public static final Rectangle BAN_LAY_RECT = new Rectangle(BAN_X, BAN_Y, BAN_W, BAN_H);
	public static final Rectangle DAI_LAY_RECT_0 = new Rectangle(SPA, BAN_Y, DAI_W, DAI_H);
	public static final Rectangle PLA_LAY_RECT_0 = new Rectangle(DAI_X_1, BAN_Y, DAI_W, DAI_H);
	public static final Rectangle DAI_LAY_RECT_1 = new Rectangle(DAI_X_1, DAI_Y_1, DAI_W, DAI_H);
	public static final Rectangle PLA_LAY_RECT_1 = new Rectangle(SPA, DAI_Y_1, DAI_W, DAI_H);
	public static final Rectangle CHAT_LAY_RECT = new Rectangle(CHAT_X, SPA, CHAT_W, CHAT_H);
	
}
