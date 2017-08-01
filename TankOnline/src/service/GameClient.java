package service;

import animation.Missile;
import message.Message;
import message.Player;
import message.Shot;
import message.Wind;

public class GameClient implements Game{
	
	private Client client;
	
	private int winner = Game.NOT_START;
	private Player p1;
	private Player p2;
	private Shot shot;
	private Missile missile;
	private Thread animation;
	private boolean canCtrl = false;
	private boolean p1Action = false;
	
	private Wind wind;
	private Thread windThread = null;
	
	public GameClient(Client client){
		this.client = client;
		this.wind = new Wind();
		shot = new Shot();
		windThread = new Thread(wind);
		windThread.start();
	}
	
	public void gameStart() {
		winner = Game.GO_ON;
		shot.setLeft(true);
	}
	
	@Override
	public void shot() {
		this.canCtrl = false;
		this.p2.setShotting(true);
		Shot shot = new Shot(this.shot.getAngle(), this.shot.getPower(), p2.isLeft());
		client.messOut(new Message(Message.P2_SHOT, shot));
		missile = new Missile(p2.x, wind.getSpeed(), shot, this);
		animation = new Thread(missile);
		animation.start();
		this.shot.powerFresh();
	}
	
	/**
	 * 该方法用于打印对方的发射动画
	 * @author xiaoE
	 * */
	public void printShot(Shot shot){
		p1.setShotting(true);
		missile = new Missile(p1.x, wind.getSpeed(), shot, this);
		animation = new Thread(missile);
		animation.start();
	}
	
	@Override
	public void shotFinish() {
		p1.setShotting(false);
		p2.setShotting(false);
		client.messOut(new Message(Message.ANIMATION_DONE));
	}

	@Override
	public void move(int i) {
		p2.move(i);
		shot.setLeft(i<0);
		client.messOut(new Message(Message.P2_MOVE, i));
	}
	
	@Override
	public void skillDouble() {
		client.messOut(new Message(Message.P2_DOUBLE));
	}

	@Override
	public void skillRage() {
		client.messOut(new Message(Message.P2_RAGE));
	}

	@Override
	public void skillShield() {
		client.messOut(new Message(Message.P2_SHIELD));
	}

	@Override
	public void skillSpeed() {
		client.messOut(new Message(Message.P2_SPEED));
	}

//	================================================
	
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
	public Wind wind() {
		return this.wind;
	}
	
	public void setWind(int wind){
		this.wind.change(wind);
	}

	public void setP1(Player p1){
		this.p1 = p1;
	}
	
	public void setP2(Player p2){
		this.p2 = p2;
	}
	
	public double getAngle(){
		return shot.getAngle();
	}
	
	public double power(){
		return shot.getPower();
	}

	public Missile missile() {
		return this.missile;
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
	public boolean isP1Action() {
		return p1Action;
	}

	public void setP1Action(boolean p1Action) {
		this.p1Action = p1Action;
	}

	public void setCanCtrl(boolean canCtrl) {
		this.canCtrl = canCtrl;
	}

	@Override
	public void hit(int mesX) {
		client.messOut(new Message(Message.HIT, new Integer(mesX)));
	}

	@Override
	public void start() {}

	@Override
	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}
}
