package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Window {
	
	private Image window = new ImageIcon("Graphics/window/window.png").getImage();
	private Image rect = new ImageIcon("Graphics/game/rect.png").getImage();
	protected Image rectAct = new ImageIcon("Graphics/game/rectAct.png").getImage();
	protected Image numPic = new ImageIcon("Graphics/string/num.png").getImage();
	protected Image pause = new ImageIcon("Graphics/string/pause.png").getImage();
	protected Image hin = new ImageIcon("Graphics/game/hin.png").getImage();
	
	protected int x;			//���panel���Ͻ�x����
	protected int y;			//���panel���Ͻ�y����
	protected int w;			//���ڿ��
	protected int h;			//���ڸ߶�
	protected int xIn;		//������ԭ��x
	protected int yIn;		//������ԭ��y
	
	public static final int SIZE = 8;
	public static final int A_WIDTH = 274;	//�����ڿ��
	public static final int LA_X = 64;
	public static final int RA_X = 802;
	public static final int IN_WINDOW_SX = 24;
	public static final int IN_WINDOW_SY = 24;
	
	private static final int W = 26;
	private static final int H = 36;
	
	public static final int YELLOW = 0;
	public static final int RED = 36;
	public static final int PURPLE = 72;
	public static final int GREEN = 108;
	public static final int BLUE = 144;
	
	public Window(){}
	
	public Window(int x,int y,int w,int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.xIn = this.x+SIZE;
		this.yIn = this.y+SIZE;
	}
	
	/**
	 * ����Ϸ�д���һ��ͼ�δ��� byС��
	 * */
	protected void createWindow(Graphics g){
		g.drawImage(window, x, y, x+SIZE, y+SIZE, 0, 0, SIZE, SIZE, null);						//���Ͻ�
		g.drawImage(window, x+w-SIZE,	y,x+w, y+SIZE,	120, 0,128,SIZE, null);					//���Ͻ�
		g.drawImage(window, x, y+h-SIZE, x+SIZE, y+h, 0, 120, SIZE, 128, null);				//���½�
		g.drawImage(window, x+w-SIZE,	y+h-SIZE, x+w, y+h,120, 120, 128, 128, null);			//���½�
		g.drawImage(window, x+SIZE, y, x+w-SIZE, y+SIZE,SIZE, 0, 120, SIZE, null);				//�ϱ�
		g.drawImage(window, x, y+SIZE, x+SIZE, y+h-SIZE, 0, SIZE, SIZE, 120, null);			//���
		g.drawImage(window, x+w-SIZE, y+SIZE, x+w, y+h-SIZE,120, SIZE, 128, 120, null);		//�ұ�
		g.drawImage(window, x+SIZE,y+h-SIZE, x+w-SIZE ,y+h, SIZE, 120, 120, 128, null);		//�±�
		g.drawImage(window, x+SIZE, y+SIZE, x+w-SIZE, y+h-SIZE, SIZE, SIZE, 120, 120, null);	//�����
	}
	
	protected void drawString(String text, Color cl, Font ft, int x, int y, Graphics g){
		g.setColor(cl);
		g.setFont(ft);
		g.drawString(text, this.xIn+x, this.yIn+y);
	}
	
	/**
	 * �����5λ��ͼƬ��ʾ���� byС��
	 * Ư�������֣�����5λ
	 * XY���Ͻ����꣬���֣���ɫ��g
	 * */
	protected void drawNumberLP(int x, int y, int num, int cl, Graphics g){
		String numStr = new Integer(num).toString();
		int length = numStr.length();
		for (int i = 0; i < 5; i++) {
			if (5-i <= length){
				int n = new Integer(numStr.charAt(i-5+length)-'0');
				g.drawImage(numPic, this.x+x+W *i, this.y+y, this.x+x+W *(i+1), this.y+y+H, 
						n*W, cl, n*W+W, cl+H, null);
			}
		}
	}
	
	/**
	 * ��ʾͼƬ byС��
	 * */
	protected void drawImg(Image img, int x, int y, Graphics g){
		int w = img.getWidth(null);
		int h = img.getHeight(null); 
		g.drawImage(img, this.xIn+x, this.yIn+y, w, h, null);
	}
	
	/**
	 * ͼƬ������x������y��subֵ��g byС��
	 * */
	protected void drawSubImg(Image img, int x, int y, int subIdx, Graphics g){
		int wh = img.getHeight(null);
		g.drawImage(img, xIn+x, yIn+y, xIn+x+wh, yIn+y+wh, 
				subIdx*wh, 0, (subIdx+1)*wh, wh, null);
	}
	
	
	protected void fillRect(int x, int y, int w, int h, Graphics g){
		g.fillRect(this.xIn+x, this.yIn+y, w, h);
	}
	
	/**
	 * ��Img��ʽ���ƾ���ֵ�� byС��
	 * ��������
	 * */
	protected void darwImgRect(int x,int y,int w,Image img,Image fill,Double p,Graphics g){
		int h = img.getHeight(null);
		g.setColor(Color.BLACK);
		g.fillRect(this.x+x, this.y+y, w, h+6);
		g.setColor(Color.WHITE);
		g.fillRect(this.x+x + 1, this.y+y + 1, w-2, h+4);
		g.setColor(Color.BLACK);
		g.fillRect(this.x+x + 2, this.y+y +2, w-4, h+2);
		if (p<1){
			g.drawImage(img, this.x+x+3, this.y+y+3, (int)(w*p), h, null);
		}else{
			p = 1.0;
			g.drawImage(fill, this.x+x+3, this.y+y+3, w-6, h, null);
		}
	}
	
	/**
	 * ��ͼƬ��ñ�ɫֵ�� byС��
	 * */
	protected void darwImgRect(int x,int y,int w,Double p,boolean fill,Graphics g){
		int h = rect.getHeight(null)+8;
		g.setColor(Color.BLACK);
		g.fillRect(this.x+x, this.y+y, w, h);
		g.setColor(Color.WHITE);
		g.fillRect(this.x+x+1, this.y+y+1, w-2, h-2);
		g.setColor(Color.BLACK);
		g.fillRect(this.x+x+2, this.y+y+2, w-4, h-4);
		if(p>=1.0&&fill){
			g.drawImage(rect, this.x+x+3, this.y+y+3, this.x+x+w-3, this.y+y+h-3, 
					200, 0, 201, 22, null);
		}else{
			g.drawImage(rect, this.x+x+3, this.y+y+3, this.x+x+(int)((w-6)*p+3), this.y+y+h-3, 
					(int)(199*p), 0, (int)(199*p)+1, 22, null);
		}
	}
	
	/**
	 * 5λ�������,String byС��
	 * */
	protected String leftPad5(String str){
		StringBuffer sb = new StringBuffer(str);
		StringBuffer newStr = new StringBuffer("");
		int pd = 5 - sb.length();
		for (int j = 0; j < 5; j++) {
			if(j>=pd){
				newStr.append(sb.charAt(j-pd));
			}else{
				newStr.append(" ");
			}
		}
		return newStr.toString();
	}
	
	/**
	 * 5λ������䣬int byС��
	 * */
	protected String leftPad5(int i){
		return leftPad5(new Integer(i).toString());
	}
	
	protected static String rightPad10(String str){
		StringBuffer newStr = new StringBuffer(str);
		for (int i = 0; i < 10-str.length(); i++) {
			newStr.append(" ");
		}
		return newStr.toString();
	}
	
	/**
	 * xy���Ͻ����꣬w��ȣ�h�߶� byС��
	 * */
	protected void darwRGBRect(int x,int y,int w,int h,Double p,Graphics g){
		g.setColor(new Color(0, 0, 0));
		g.fillRect(this.x+x, this.y+y, w, h+4);
		g.setColor(new Color(255,255,255));
		g.fillRect(this.x+x+1, this.y+y+1, w-2, h+2);
		g.setColor(new Color(0, 0, 0));
		g.fillRect(this.x+x+2, this.y+y+1 + 1, w-4, h);
		if (p>=1){
			p = 1.0;
		}
		for (int i = 0; i < 4; i++) {
			int R;
			int G;
			if(p<0.5){
				G = (int)((250-i*50)*p*2);
				R = 250-i*50;
			}else{
				G = 250-i*50;
				R = (int)( (250-i*50) * ((1/p)-1) );
			}
			g.setColor(new Color(R, G, 0));
			g.fillRect(this.x+x+2, this.y+y+5+i, (int) ((w-4) * p), 1);
			g.fillRect(this.x+x+2, this.y+y+5-i, (int) ((w-4) * p), 1);
		}
	}
}
