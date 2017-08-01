package service;

import java.util.Random;

import message.Message;
import message.Player;
import message.Shot;
import message.Wind;
import animation.Missile;


public class GameServer implements Game{
	
	public static final double FPS = 40.0;		//	桢/秒
	public static final long SLEEP = (long)(1000/FPS);
	
	private Random r = new Random();
	
	private int winner = -1;
	
	private Server server = null;
	private Player p1 = null;
	private Player p2 = null;
	private Player atkPla = null;
	private Player defPla = null;
	private Shot shot = null;
	
	private Thread windThread = null;
	private Thread animation = null;
	private Missile missile = null;
	private Wind wind;
	
	private boolean canCtrl = false;
	private boolean clientAnmOK = true;
	private boolean p1Action = false;
	private boolean joinHit = false;
	
	public GameServer(Server server){
		this.server = server;
		wind = new Wind();
		windThread = new Thread(wind);
		windThread.start();
		shot = new Shot();
	}
	
	public void start(){
		winner = GO_ON;
		wind.change(r.nextBoolean()?-1:1);
		server.messOut(new Message(Message.WIND, wind.getSpeed()));
		p1 = new Player(server.getMainName(), 164, false);		//164
		p2 = new Player(server.getJoinName(), 956, true);		//956
		shot.setLeft(false);
		syncPlayer();
		server.messIn(new Message(Message.GAME_START));
		p1Action = r.nextBoolean();
		atkPla = p1Action ? p1 : p2;
		defPla = p1Action ? p2 : p1;
		actionChange();
	}
	
	/**
	 * 行动更换
	 * @author xiaoE
	 * */
	private void actionChange(){
		p1Action = !p1Action;
		Player temp = defPla;
		defPla = atkPla;
		atkPla = temp;
		atkPla.changeMP(20.0);
		syncPlayer();
		if(p1Action){
			canCtrl = true;
		}else{
			server.messOut(new Message(Message.YOU_CAN_CTRL));
		}
		server.messOut(new Message(Message.ACTION, p1Action));
	}
	
	/**
	 * 同步客户端的Player数据
	 * @author xiaoE
	 * */
	public void syncPlayer(){
		Player p1 = new Player(this.p1);
		Player p2 = new Player(this.p2);
		server.messOut(new Message(Message.WINNER, new Integer(winner)));
		server.messOut(new Message(Message.PLAYER, new Player[]{p1,p2}));
	}
	
	@Override
	public void move(int i) {
		p1.move(i);
		shot.setLeft(i<0);
		syncPlayer();
	}
	
	@Override
	public void angleUp() {
		this.shot.angleUp();
	}

	@Override
	public void angleDown() {
		this.shot.angleDown();
	}
	
	@Override
	public void powerUp() {
		shot.powerUp();
	}
	
	@Override
	public void shot() {
		this.canCtrl = false;
		this.p1.setShotting(true);
		Shot shot = new Shot(this.shot.getAngle(), this.shot.getPower(), this.shot.isLeft());
		this.clientAnmOK = false;
		server.messOut(new Message(Message.P1_SHOT, shot));
		missile = new Missile(p1.x, wind.getSpeed(), shot, this);
		animation = new Thread(missile);
		animation.start();
		this.shot.powerFresh();
	}
	
	/**
	 * 该方法用于打印对方的发射动画
	 * @author xiaoE
	 * */
	public void printShot(Shot shot){
		p2.setShotting(true);
		missile = new Missile(p2.x, wind.getSpeed(), shot, this);
		animation = new Thread(missile);
		animation.start();
	}
	
	@Override
	public void hit(int misX){
		damage(p1,misX);
		while(!joinHit){}
		damage(p2,misX);
		syncPlayer();
	}
	
	@Override
	public void shotFinish() {
		while(!clientAnmOK){}
		p1.setShotting(false);
		p2.setShotting(false);
		syncPlayer();
		changeWind();
		closeSkill();
		actionChange();
		winner = winner();
		if(winner != GO_ON){
			canCtrl = false;
			server.messOut(new Message(Message.YOU_CAN_T_CTRL));
			server.messOut(new Message(Message.WINNER, new Integer(winner)));
			syncPlayer();
		}
	}
	
	/**
	 * 把该关的技能关掉
	 * @author xiaoE
	 * */
	private void closeSkill() {
		atkPla.skillClose(Player.DOUBLE);
		atkPla.skillClose(Player.SPEED);
		defPla.skillClose(Player.RAGE);
		syncPlayer();
	}

	/**
	 * 胜负判定
	 * @author xiaoE
	 * */
	private int winner() {
		if(p1.getHP()<=0 && p2.getHP()<=0){
			p1.setShow(false);
			p2.setShow(false);
			return DRAW;
		}
		if(p1.getHP() <= 0){
			p1.setShow(false);
			return P2;
		}else if(p2.getHP() <= 0){
			p2.setShow(false);
			return P1;
		}
		return GO_ON;
	}
	
	/**
	 * 伤害计算
	 * @author xiaoE
	 * */
	public void damage(Player pla, int misX) {
		int distance = Math.abs(misX-pla.x);
		if(distance < Player.MIGHT){
			double damage = -Player.ATK/Player.MIGHT*distance+40;
			if(atkPla.getSkill()[Player.DOUBLE]){
				damage *= 2;	//如果攻击玩家有双倍
			}
			if(atkPla.getSkill()[Player.RAGE]){
				damage *= 2;	//如果攻击玩家有狂暴
			}
			if(pla.getSkill()[Player.RAGE]){
				damage *= 2;	//如果受攻击玩家有狂暴
			}
			if(pla.getSkill()[Player.SHIELD]){
				damage /= 2;	//如果受攻击玩家有盾，则减半伤害，并关盾
				pla.skillClose(Player.SHIELD);
			}
			pla.changeHP(-damage);
		}
		syncPlayer();
	}
	
	/**
	 * 改变风向
	 * @author xiaoE
	 * */
	private void changeWind() {
		if(r.nextBoolean()){
			int speed; 
			do{
				speed = r.nextInt(7)-3;
			}while(speed == 0);
			wind.change(speed);
			server.messOut(new Message(Message.WIND, wind.getSpeed()));
		}
	}
	
	@Override
	public void skillDouble() {
		if(p1.skillDouble()){
			syncPlayer();
		}
	}

	@Override
	public void skillRage() {
		if(p1.skillRage()){
			syncPlayer();
		}
	}

	@Override
	public void skillShield() {
		if(p1.skillShield()){
			syncPlayer();
		}
	}

	@Override
	public void skillSpeed() {
		if(p1.skillSpeed()){
			syncPlayer();
		}
	}

//	===============================================================
	
	@Override
	public boolean canCtrl(){
		return this.canCtrl;
	}

	@Override
	public Shot getShot() {
		return this.shot;
	}

	@Override
	public Player p1() {
		return this.p1;
	}

	@Override
	public Player p2() {
		return this.p2;
	}
	
	@Override
	public Missile missile(){
		return this.missile;
	}

	@Override
	public Wind wind() {
		return this.wind;
	}
	
	public void setClientAnmOK(boolean clientAnmOK) {
		this.clientAnmOK = clientAnmOK;
	}
	
	@Override
	public boolean isP1Action() {
		return p1Action;
	}

	@Override
	public int getWinner() {
		return this.winner;
	}

	public void setJoinHit(boolean b) {
		this.joinHit = b;
	}
}
