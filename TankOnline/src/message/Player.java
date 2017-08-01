package message;

import java.io.Serializable;

public class Player implements Serializable{
	private static final long serialVersionUID = 7449366353064746722L;
	
	public static final double ATK = 40.0;
	public static final int MIGHT = 80;		//	爆炸范围（像素）
	
	public static final double MAX_HP = 100.0;
	public static final double MAX_MP = 100.0;
	
	public static final double MP_DOUBLE = 49.9;
	public static final double MP_RAGE = 19.9;
	public static final double MP_SHIELD = 29.9;
	public static final double MP_SPEED = 9.9;
	
	public static final int DOUBLE = 0;
	public static final int RAGE = 1;
	public static final int SHIELD = 2;
	public static final int SPEED = 3;
	
	public int x;
	private boolean left = false;
	private boolean shotting = false;
	private boolean show = true;
	
	private String name;
	private double HP = MAX_HP;
	private double MP = MAX_MP;
	
	private boolean[] skill;
	
	public Player(){
		this.show = false;
	}
	
	public Player(String name, int x, boolean left){
		this.x = x;
		this.name = name;
		this.left = left;
		this.skill = new boolean[]{false,false,false,false};
	}
	
	public Player(Player p){
		this.x = p.getX();
		this.left = p.isLeft();
		this.shotting = p.isShotting();
		this.show = p.isShow();
		this.name = p.getName();
		this.HP = p.getHP();
		this.MP = p.getMP();
		this.skill = new boolean[4];
		for (int i = 0; i < 4; i++) {
			this.skill[i] = (p.getSkill())[i];
		}
	}
	
	/**
	 * 坦克移动
	 * @author xiaoE
	 * */
	public void move(int m) {
		this.left = m < 0;
		if (MP > 0) {
			this.MP -= Math.abs(m) * 0.25;
			this.x += skill[SPEED] ? m * 2 : m;
			if (x < 15) {
				x = 15;
			} else if (x > 1105) {
				x = 1105;
			}
		}
	}
	
	/**
	 * 改变血
	 * @author xiaoE
	 * */
	public void changeHP(double ch){
		HP += ch;
		if(HP > MAX_HP){
			HP = MAX_HP;
		}else if(HP < 0){
			HP = 0;
		}
	}
	
	/**
	 * 改变蓝
	 * @author xiaoE
	 * */
	public void changeMP(double ch){
		this.MP += ch;
		if(this.MP>MAX_MP){
			this.MP = MAX_MP;
		}else if(this.MP < 0){
			MP = 0;
		}
	}
	
	
	public boolean skillDouble() {
		if( (!(skill[DOUBLE]||skill[RAGE])) && MP>MP_DOUBLE ) {
//			doubleAd.start();
			MP -= MP_DOUBLE;
			skill[DOUBLE] = true;
			return true;
		}else{
			return false;
//			failAd.start();
		}
	}

	public boolean skillRage() {
		if( (!(skill[DOUBLE]||skill[RAGE])) && MP>MP_RAGE ) {
//			rageAd.start();
			MP -= MP_RAGE;
			skill[RAGE] = true;
			return true;
		}else{
//			failAd.start();
			return false;
		}
	}

	public boolean skillShield() {
		if(!skill[SHIELD] && MP>MP_SHIELD ) {
//			shieldAd.start();
			MP -= MP_SHIELD;
			skill[SHIELD] = true;
			return true;
		}else{
//			failAd.start();
			return false;
		}
	}

	public boolean skillSpeed() {
		if(!skill[SPEED] && MP>MP_SPEED ) {
//			speedAd.start();
			MP -= MP_SPEED;
			skill[SPEED] = true;
			return true;
		}else{
//			failAd.start();
			return false;
		}
	}
	
	public void skillClose(int idx){
		skill[idx] = false;
	}
	
//	======================= get set 方法 ==========================
	
	public String toString(){
		return this.name;
	}
	
	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean isShotting() {
		return shotting;
	}

	public void setShotting(boolean shottiting) {
		this.shotting = shottiting;
	}
	
	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public double getHP() {
		return HP;
	}

	public void setHP(double HP) {
		this.HP = HP;
	}

	public double getMP() {
		return MP;
	}

	public void setMP(double MP) {
		this.MP = MP;
	}

	public int getX() {
		return x;
	}

	public boolean[] getSkill() {
		return skill;
	}
}
