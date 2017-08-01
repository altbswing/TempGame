package cfg.setter;

import game.entity.koma.Koma;
import game.entity.koma.KomaOu;

import java.util.List;

import org.dom4j.Element;

import util.Point;

public class GameSetter {
	
	private String name = "�������";
	private Koma[][] ban = new Koma[9][9];
	private Point ou1 = null;
	private Point ou0 = null;
	private int[] dai1 = new int[7];
	private int[] dai0 = new int[7];
	
	public GameSetter() {
		
	}

	/**
	 * ��xml���������
	 * */
	public void setName(Element elGame) {
		this.name = elGame.attributeValue("name");
	}

	/**
	 * ��xml�������
	 * */
	public void setBan(Element elBan) {
		ban = new Koma[9][9];
		int size = elBan.elements("koma").size();
		List<Element> es = elBan.elements("koma");
		for (int i = 0; i < size; i++) {
			Element elKoma = (Element)elBan.elements("koma").get(i);
			int code = new Integer(elKoma.attributeValue("code"));
			int x = new Integer(elKoma.attributeValue("x"));
			int y = new Integer(elKoma.attributeValue("y"));
			boolean mine = new Boolean(elKoma.attributeValue("mine"));
			boolean nari = new Boolean(elKoma.attributeValue("nari"));
			ban[x][y] = Koma.createKoma(code, mine, nari);
			if(code == Koma.OU) {
				if(mine) {
					ou1 = (new Point(x, y));
				} else {
					ou0 = new Point(x, y);
				}
				ban[x][y] = new KomaOu(mine);
			} 
		}
	}
	
	/**
	 * ��xml�з���̨����
	 * */
	public void setDai0(Element elDai0) {
		this.dai0 = getDaiFromXML(elDai0);
	}

	/**
	 * ��xml�ҷ���̨����
	 * */
	public void setDai1(Element elDai1) {
		this.dai1 = getDaiFromXML(elDai1);
	}
	
	private int[] getDaiFromXML(Element elDai) {
		int[] dai = new int[7];
		dai[Koma.HI] = new Integer(elDai.attributeValue("hi"));
		dai[Koma.KAKU] = new Integer(elDai.attributeValue("kaku"));
		dai[Koma.KIN] = new Integer(elDai.attributeValue("kin"));
		dai[Koma.GIN] = new Integer(elDai.attributeValue("gin"));
		dai[Koma.KEI] = new Integer(elDai.attributeValue("kei"));
		dai[Koma.KYOU] = new Integer(elDai.attributeValue("kyou"));
		dai[Koma.HU] = new Integer(elDai.attributeValue("hu"));
		return dai;
	}
	
//	================get����================
	
	public Point getOu1() {
		return ou1;
	}

	public Point getOu0() {
		return ou0;
	}

	public Koma[][] getBan() {
		return ban;
	}

	public int[] getDai1() {
		return dai1;
	}

	public int[] getDai0() {
		return dai0;
	}

	public String getName() {
		return name;
	}
}
