package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ui.FrameOver;
import ui.PanelGame;
import util.Cheak;
import util.IPUtil;
import util.TimeUtil;
import dao.DataBase;
import entity.Node;
import entity.Player;

public class GameService implements Runnable{
	
	private static final int RED = 0;
	private static final int ORANGE = 1;
	private static final int YELLOW = 2;
	private static final int GREEN = 3;
	private static final int CYAN = 4;
	private static final int BLUE = 5;
	private static final int MAGENTA = 6;
	
	private static final int PLUS_POINT = 1;
	private static final int LEVEL_UP = 20;
	
	private Random r = new Random();
	private int point;
	private int level = 0;
	private int RMline;
	private int EXP;
	private PanelGame panel;
	private boolean[][] map = new boolean[10][18];
	private int type = 7;
	private int nextType = 7;
	private boolean isStart;
	private boolean isLose;
	private boolean isPause = false;
	
	private Thread gameThread;
	private Node[] tetris = new Node[4];
	
	private DataBase DB;
	private FrameOver over;
	private Object lock = new Object();

	private List<Player> tPlayers = new ArrayList<Player>();
	private List<Player> wPlayers = new ArrayList<Player>();
	
	public GameService(){}
	
	public GameService(DataBase DB){
		this.DB = DB;
	}
	
	/**
	 * ��Ϸ��ʼ�� byС��
	 * */
	public void gameStart(){
		point = 0;
		level = 1;
		RMline = 0;
		EXP = 0;
		over.setVisible(false);
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 18; y++) {
				map[x][y] = false;
			}
		}
		nextType = r.nextInt(7);
		type = r.nextInt(7);
		createNewTetris(type);
		isLose = false;
		isStart = true;
		panel.repaint();
		gameThread = null;
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void run() {
		while (!isLose) {
			if(!isPause){
				try {
					int sleepMs = 5000/(level+4);
					Thread.sleep(sleepMs);
					synchronized (lock) {
						move(0, 1);
						if(!isLose){
							panel.requestFocus(true);
						}
					}
					panel.repaint();
				} catch (InterruptedException e) {
				}
			}
		}
	}
	
	/**
	 * ��ͣ
	 * */
	public void pause() {
		if (isStart) {
			isPause = !isPause;
			panel.repaint();
		}
	}

	/**
	 * �ƶ����� byС��
	 * */
	public void move(int x, int y){
		if(canMove(x,y)){
			for (int i = 0; i < tetris.length; i++) {
				tetris[i].setX(tetris[i].getX()+x);
				tetris[i].setY(tetris[i].getY()+y);
			}
		}
		panel.repaint();
	}
	
	/**
	 * ��ת���� byС��
	 * */
	public void round(){
		if(type!=CYAN&&canRound()){
			for (int i = 1; i < tetris.length; i++) {
				int newX = tetris[0].getX() - tetris[0].getY() + tetris[i].getY();
				int newY = tetris[0].getX() + tetris[0].getY() - tetris[i].getX();
				tetris[i].setX(newX);
				tetris[i].setY(newY);
			}
		}
		panel.repaint();
	}
	
	/**
	 * �ж��Ƿ������ת byС��
	 * */
	private boolean canRound() {
		for (int i = 1; i < tetris.length; i++) {
			int newX = tetris[0].getX() - tetris[0].getY() + tetris[i].getY();
			int newY = tetris[0].getX() + tetris[0].getY() - tetris[i].getX();
			if(newX<0||newX>=10||newY<0||newY>=18
					||map[newX][newY]){
				return false;
			}
		}
		return true;
	}

	/**
	 * �ж��Ƿ�����ƶ� byС��
	 * */
	private boolean canMove(int x,int y) {
		for (int i = 0; i < tetris.length; i++) {
			if(tetris[i].getX()+x<0 || tetris[i].getX()+x>=10
					||map[tetris[i].getX()+x][tetris[i].getY()]){
				panel.repaint();
				return false;
			}else if(tetris[i].getY()+y >= 18
					||map[tetris[i].getX()][tetris[i].getY()+y]){
				addToMap();
				rmRow();
				panel.repaint();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * �����㷨 byС��
	 * */
	private void rmRow() {
		int RMCount = 0;
		naxtLine:
		for(int y = 0; y < 18; y++) {
			for (int x = 0; x < 10; x++) {		//ɨ���y�з���falseֱ��ɨ����һ��
				if(!map[x][y]){	
					continue naxtLine;
				}
			}
			for (int x = 0; x < 10; x++) {
				map[x][y] = false;
			}//һ������
			RMCount += 1;
			mapDown(y);
		}
		RMline += RMCount;
		EXP += RMCount;
		if(RMCount < 2) {	//��һ�л��߲�����
			point += RMCount*PLUS_POINT;
		}else{				//�������н���
			point += RMCount*PLUS_POINT + Math.pow(2, (RMCount-2))*PLUS_POINT;
		}
		levelUP();
	}

	/**
	 * ���� 
	 * */
	private void levelUP() {
		if(EXP>=LEVEL_UP){
			level += 1;
			EXP -= LEVEL_UP;
		}
	}

	/**
	 * ���к�����׹�� byС��
	 * */
	private void mapDown(int Row) {
		for (int x = 0; x < 10; x++) {
			for (int y = Row; y > 0; y--) {
				map[x][y] = map[x][y-1];
			}
			map[x][0] = false;
		}
	}
	
	/**
	 * �ո���������� byС��
	 * */
	public void quickDown() {
		while(true){
			if(canMove(0,1)){
				move(0,1);
			}else{
				break;
			}
		}
	}

	/**
	 * �ѷ�����뵽��ͼ���飬�������·��� byС��
	 * */
	private void addToMap() {
		for (int i = 0; i < tetris.length; i++) {
			map[tetris[i].getX()][tetris[i].getY()] = true;
		}
		type = nextType;
		nextType = r.nextInt(7);
//		nextType = 4;
		createNewTetris(type);
		for (int i = 3; i < 7; i++) {
			if(map[i][0]){
				isLose = true;
				gameThread = null;
				panel.setBtnStart(true);
				break;
			}
		}
		panel.repaint();
	}

	/**
	 * �����·��� byС��
	 * */
	private void createNewTetris(int type) {
		switch (type) {
		case RED:		//����I
			tetris[0] = new Node(5, 0);//��ת��
			tetris[1] = new Node(4, 0);
			tetris[2] = new Node(3, 0);
			tetris[3] = new Node(6, 0);
			break;
		case ORANGE:	//T��
			tetris[0] = new Node(5, 0);//��ת��
			tetris[1] = new Node(4, 0);
			tetris[2] = new Node(6, 0);
			tetris[3] = new Node(5, 1);
			break;
		case YELLOW:	//L��
			tetris[0] = new Node(5, 0);//��ת��
			tetris[1] = new Node(6, 0);
			tetris[2] = new Node(4, 0);
			tetris[3] = new Node(4, 1);
			break;
		case GREEN:	//��Z
			tetris[0] = new Node(5, 1);//��ת��
			tetris[1] = new Node(6, 0);
			tetris[2] = new Node(4, 1);
			tetris[3] = new Node(5, 0);
			break;
		case CYAN:		//������
			tetris[0] = new Node(4, 0);//����ת
			tetris[1] = new Node(5, 0);
			tetris[2] = new Node(4, 1);
			tetris[3] = new Node(5, 1);
			break;
		case BLUE:		//��L
			tetris[0] = new Node(4, 0);//��ת��
			tetris[1] = new Node(3, 0);
			tetris[2] = new Node(5, 0);
			tetris[3] = new Node(5, 1);
			break;
		case MAGENTA:	//Z��
			tetris[0] = new Node(4, 1);//��ת��
			tetris[1] = new Node(3, 0);
			tetris[2] = new Node(4, 0);
			tetris[3] = new Node(5, 1);
			break;
		default:
			break;
		}
		if(!canMove(0,0)){
			lose();
		}
		panel.repaint();
	}
	
	/**
	 * ��Ϸʧ�ܵĳ��� byС��
	 * */
	private void lose() {
		isLose = true;
		gameThread = null;
		over.setVisible(true);
		panel.setBtnStart(true);
	}

	/**
	 * ɾ�����ؼ�¼ byС��
	 * */
	public void deleteWorldData() {
		this.DB.deleteLocalData();
	}
	
	/**
	 * �������ֺ�������� byС��
	 * */
	public void setDate(String name) {
		Player p = new Player(name, TimeUtil.today(), point);
		p.setCheakCode(Cheak.code(p));
		p.setIp(IPUtil.IP());
		DB.setWorldData(p);
		DB.setTarenaData(p);
	}
	
	//========= һ���� get set ���� ======
	
	public List<Player> gettPlayers() {
		return tPlayers;
	}

	public void settPlayers(List<Player> tPlayers) {
		this.tPlayers = tPlayers;
		this.panel.repaint();
	}

	public List<Player> getwPlayers() {
		return wPlayers;
	}

	public void setwPlayers(List<Player> wPlayers) {
		this.wPlayers = wPlayers;
		this.panel.repaint();
	}
	
	public int getPoint() {
		return point;
	}
	
	public DataBase getDB(){
		return this.DB;
	}

	public int getLevel() {
		return level;
	}

	public int getRMline() {
		return RMline;
	}

	public void setPanel(PanelGame panel) {
		this.panel = panel;
	}

	public boolean[][] getMap() {
		return map;
	}

	public int getType() {
		return type;
	}

	public int getNextType() {
		return nextType;
	}

	public boolean isStart() {
		return isStart;
	}
	
	public Node[] getTetris() {
		return tetris;
	}
	
	public boolean isLose() {
		return isLose;
	}
	
	public int getEXP() {
		return EXP;
	}

	public void setDB(DataBase DB) {
		this.DB = DB;
	}
	
	public void setOver(FrameOver over) {
		this.over = over;
	}
	
	public boolean isPause(){
		return isPause;
	}
	
	/**
	 * �����üӼ��㷨
	 * */
	public void spLevelUp(){
		this.level+=1;
		panel.repaint();
	}
}
