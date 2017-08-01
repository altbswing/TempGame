package cfg.setter;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import util.ImageClipper;

public class ImageReader {
	
	private static final String PATH = "Graphics/style/";
	private static final String FACE_PATH = "Graphics/buf/face.buf";
	private static final String BUTTON_PATH = "Graphics/buf/button.buf";
	private static String BACKGROUND = "/background.png";
	private static String WINDOW = "/window.png";
	private static String RECT = "/rect.png";
	private static String BAN = "/ban.png";
	private static String BAN_SHADOW = "/ban_shadow.png";
	private static String DAI = "/dai.png";
	private static String KOMA = "/koma.png";
	private static String KOMA_OUTE = "/koma_oute.png";
	private static String KOMA_SHADOW = "/koma_shadow.png";
	private static String KOMA_LIGHT = "/koma_light.png";
	
	public static Image BACKGROUND_IMG = null;	//背景样式
	public static Image RECT_IMG = null;			//值槽样式
	public static Image BAN_IMG = null;			//棋盘样式
	public static Image BAN_SHADOW_IMG = null;	//棋盘阴影样式
	public static Image KOMA_OUTE_IMG = null;		//棋盘王手样式
	public static Image KOMA_SHADOW_IMG = null;	//棋盘阴影样式（不可行区域）
	public static Image KOMA_LIGHT_IMG = null;	//棋盘亮光样式（可行区域）
	public static Image DAI_IMG = null;			//棋台样式
	
	//以下是棋子样式
	public static Image[][] KOMA_IMG = new Image[8][4];
	
	public static Image WINDOW_LT = null;		//窗体样式
	public static Image WINDOW_LC = null;
	public static Image WINDOW_LB = null;
	public static Image WINDOW_CT = null;
	public static Image WINDOW_CC = null;
	public static Image WINDOW_CB = null;
	public static Image WINDOW_RT = null;
	public static Image WINDOW_RC = null;
	public static Image WINDOW_RB = null;
	
	public static List<Image> FACE_IMG = null; //头像
	public static List<ImageIcon> BTN_ICON = null; //按钮

	public static void imageInit() throws IOException {
		FACE_IMG = ImageClipper.clipImageToList(FACE_PATH, 160, 96);
		BTN_ICON = ImageClipper.clipImageIconToList(BUTTON_PATH, 32, 32);
		setSytle("basic");
	}
	
	public static void setSytle(
			final String styleName) throws IOException {
		String stylePath = PATH + styleName;
		BACKGROUND_IMG = new ImageIcon(stylePath + BACKGROUND).getImage();
		RECT_IMG = new ImageIcon(stylePath + RECT).getImage();
		BAN_IMG = new ImageIcon(stylePath + BAN).getImage();
		BAN_SHADOW_IMG = new ImageIcon(stylePath + BAN_SHADOW).getImage();
		DAI_IMG = new ImageIcon(stylePath + DAI).getImage();
		KOMA_OUTE_IMG = new ImageIcon(stylePath + KOMA_OUTE).getImage();
		KOMA_SHADOW_IMG = new ImageIcon(stylePath + KOMA_SHADOW).getImage();
		KOMA_LIGHT_IMG = new ImageIcon(stylePath + KOMA_LIGHT).getImage();
		BufferedImage bufImg = ImageIO.read(new File(stylePath + WINDOW));
		WINDOW_LT = new ImageIcon(bufImg.getSubimage(0, 0, 8, 8)).getImage();
		WINDOW_CT = new ImageIcon(bufImg.getSubimage(8, 0, 112, 8)).getImage();
		WINDOW_RT = new ImageIcon(bufImg.getSubimage(120, 0, 8, 8)).getImage();
		WINDOW_LC = new ImageIcon(bufImg.getSubimage(0, 8, 8, 112)).getImage();
		WINDOW_CC = new ImageIcon(bufImg.getSubimage(8, 8, 112, 112)).getImage();
		WINDOW_RC = new ImageIcon(bufImg.getSubimage(120, 8, 8, 112)).getImage();
		WINDOW_LB = new ImageIcon(bufImg.getSubimage(0, 120, 8, 8)).getImage();
		WINDOW_CB = new ImageIcon(bufImg.getSubimage(8, 120, 112, 8)).getImage();
		WINDOW_RB = new ImageIcon(bufImg.getSubimage(120, 120, 8, 8)).getImage();
		KOMA_IMG = ImageClipper.clipImageToArray(stylePath + KOMA, 46, 56);
	}
	
}
