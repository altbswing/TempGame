package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import message.Shot;
import util.AngleMath;

/**
 * �� byС��
 * */
public class Lay {
	
	private Image numPic = new ImageIcon("Graphics/string/num.png").getImage();
	private Image rect = new ImageIcon("Graphics/window/rect.png").getImage();
	private Image angleImg = new ImageIcon("Graphics/window/angle.png").getImage();
	
	protected int x;			//���panel���Ͻ�x���
	protected int y;			//���panel���Ͻ�y���
	
	public Lay(){}
	
	public Lay(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public void showLay(){}
	
	/**
	 * �Ƕ�ָʾ����Բ��XY�����ȣ��Ƕȣ�g byС��
	 * */
	protected void drawAngle(int x, int y, int r, 
			int angle, boolean left, Graphics g){
		//�Ƕȼ�ֵָʾ��
		g.setColor(Color.GREEN);
		g.drawLine(this.x+x, this.y+y, 
				this.x+x-(int)(left?r*AngleMath.cos(Shot.MAX_SHOT_ANGLE)
						:-r*AngleMath.cos(Shot.MAX_SHOT_ANGLE)), 
				this.y+y-(int)(r*AngleMath.sin(Shot.MAX_SHOT_ANGLE)));
		g.drawLine(this.x+x, this.y+y, 
				this.x+x-(int)(left?r*AngleMath.cos(Shot.MIN_SHOT_ANGLE)
						:-r*AngleMath.cos(Shot.MIN_SHOT_ANGLE)), 
				this.y+y-(int)(r*AngleMath.sin(Shot.MIN_SHOT_ANGLE)));
		//�Ƕ�ָʾ��
		g.setColor(Color.YELLOW);
		int dx = (int)(r*AngleMath.cos(angle));
		g.drawLine(this.x+x, this.y+y, this.x+x+ (left?-dx:dx), 
				this.y+y-(int)(r*AngleMath.sin(angle)));
		//�߿�Բ��ˮƽ��
		g.setColor(Color.RED);
		g.drawOval(this.x+x-r, this.y+y-r, 2*r, 2*r);				
		g.drawLine(this.x+x-r, this.y+y, this.x+x+r, this.y+y);
		//С����
		drawImgC(angleImg, x, y, g);
		//����
		drawNumberLP(x-26, y+8, angle, BLUE, g);
	}
	
	protected static final int YELLOW = 0;
	protected static final int RED = 36;
	protected static final int PURPLE = 72;
	protected static final int GREEN = 108;
	protected static final int BLUE = 144;
	private static final int W = 26;
	private static final int H = 36;
	/**
	 * �����2λ��ͼƬ��ʾ���� byС��
	 * XY���Ͻ���꣬���֣���ɫ��g
	 * */
	protected void drawNumberLP(int x, int y, int num, int cl, Graphics g){
		String numStr = new Integer(num).toString();
		int length = numStr.length();
		for (int i = 0; i < 2; i++) {
			if (2-i <= length){
				int n = new Integer(numStr.charAt(i-2+length)-'0');
				g.drawImage(numPic, this.x+x+W *i, this.y+y, this.x+x+W *(i+1), this.y+y+H, 
						n*W, cl, n*W+W, cl+H, null);
			}
		}
	}
	
	/**
	 * ָ�����ĵ������ʾͼƬ byС��
	 * */
	protected void drawImgC(Image img, int x, int y, Graphics g){
		int w = img.getWidth(null);
		int h = img.getHeight(null); 
		g.drawImage(img, this.x+x-w/2, this.y+y-h/2, null);
	}
	
	/**
	 * ָ�����ĵ������ʾͼƬ byС��
	 * */
	protected void drawImgC(Image img, Point p, Graphics g){
		drawImgC(img, p.x, p.y ,g);
	}
	
	/**
	 * ָ�����ĵ�����ַ� byС��
	 * */
	protected void drawStringC(String str, int x, int y, Graphics g){
		g.setColor(Color.BLACK);
		double size = 14.0;
		double dx = (double)x;
		g.setFont(new Font("����", Font.BOLD, (int)size));
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i)<=122){
				dx -= size/4;
			}else{
				dx -= size/2;
			}
		}
		g.drawString(str, this.x+(int)dx, this.y+y);
	}
	
	public static final int RECT_RED = 0;
	public static final int RECT_GREEN = 99;
	public static final int RECT_YELLOW = 50;
	public static final int RECT_GtoR = -1;
	public static final int RECT_CYAN = 100;
	/**
	 * ��ͼƬ��ñ�ɫֵ��,byС��
	 * */
	protected void darwImgRect(int x, int y, int w, double p, int color,
			Graphics g) {
		int h = rect.getHeight(null);
		if (color == RECT_GtoR) {
			g.drawImage(rect, this.x + x, this.y + y, this.x + x
					+ (int) (w * p), this.y + y + h, (int) (99 * p), 0,
					(int) (99 * p) + 1, 12, null);
		} else {
			g.drawImage(rect, this.x + x, this.y + y, this.x + x
					+ (int) (w * p), this.y + y + h, color, 0, color + 1, 12,
					null);
		}
	}
}
