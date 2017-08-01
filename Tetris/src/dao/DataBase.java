package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import service.GameService;
import util.Cheak;
import util.ConnectionUtils;
import util.TimeUtil;
import entity.Player;

public class DataBase implements Runnable{
	
	private GameService game;
	
	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private Thread readTarena = new Thread(this);
	
	private static final String SQL_FIND 
		= "select name,to_char(day,'yyyy-mm-dd'),point,ip,check_code from xe_tetris121";
	
	public DataBase(GameService game){
		this.game = game;
		getWorldData();
		readTarena.start();
	}

	public void run() {
		getTarenaData();
	}

	/**
	 * ��ȡ�������ݿ������ byС��
	 * */
	public void getTarenaData() {
		List<Player> players = new ArrayList<Player>();
		setNullDate(players);
		game.settPlayers(players);
		try{
			con = ConnectionUtils.getConnection();
			TimeUtil.message("Tarena:�ǩ`���٩`�����������ɹ�");
			stmt = con.prepareStatement(SQL_FIND);
			rs = stmt.executeQuery();
			TimeUtil.message("Tarena:SQL�Ĥ���Ф��ޤ���");
			while(rs.next()){
				Player p = new Player(rs.getString(1),rs.getString(2),rs.getInt(3));
				p.setIp(rs.getString(4));
				p.setCheakCode(rs.getString(5));
				if(p.isCheat()){
					TimeUtil.message("Tarena:1�Ĥ΂Υǩ`����Ҋ�Ĥ��ޤ����� "+ p);
				}else{
					players.add(p);
				}
			}
			TimeUtil.message("Tarena:�ǩ`����ȡ���z�ߤޤ���");
			Collections.sort(players);
			rs.close(); stmt.close(); con.close();
		}catch(Exception e){
			TimeUtil.message("Tarena:�ǩ`����ȡ���z�ߤ�ʧ�����ޤ���");
		}
		game.settPlayers(players);
	}
		
	/**
	 * ��������ݿ�д������ byС��
	 * */
	public void setTarenaData(Player p) {
		try {
			con = ConnectionUtils.getConnection();
			stmt = con.prepareStatement(insertSQL(p));
			int row = stmt.executeUpdate();
			TimeUtil.message("Tarena:"+row+"�ĤΥǩ`����TarenaDataBase�˕����z�ߤޤ���");
		} catch (Exception e) {
			TimeUtil.message("Tarena:�ǩ`���Ε����z�ߤ�ʧ�����ޤ���");
		}
		this.getTarenaData();
	}
	
	/**
	 * ����һ��Player p����һ�������sql��� byС��
	 * */
	public String insertSQL(Player p){
		String head = "insert into xe_tetris121 values('";
		String center = p.getName()+"', to_date('"
			+p.getDate()+"','yyyy-mm-dd'),"
			+p.getPoint()+",'"
			+p.getIp()+"', '"+p.getCheakCode();
		String over = "')";
		return head+center+over;
	}

	/**
	 * ��ȡ��������
	 * */
	public void getWorldData() {
		List<Player> players = new ArrayList<Player>();
		setNullDate(players);
		try {
			players.clear();
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					"data/record.dat"));
			players = (List<Player>) in.readObject();
			in.close();
			TimeUtil.message("Local:�ǩ`����ȡ���z�ߤޤ���");
			for (int i = 0; i < players.size(); i++) {
				if(players.get(i).isCheat()){
					TimeUtil.message("Local:1�Ĥ΂Υǩ`����Ҋ�Ĥ��ޤ����� "+ players.get(i));
					players.remove(i);
					i--;
				}
			}
		} catch (Exception e) {
			TimeUtil.message("Local:�ǩ`����ȡ���z�ߤ�ʧ�����ޤ���");
		}
		setNullDate(players);
		Collections.sort(players);
		game.setwPlayers(players);
	}

	/**
	 * ����������Է�ֹ��Ϸ������ʾ�հ�
	 * */
	private void setNullDate(List<Player> Players) {
		for (int i = 0; i < 5; i++) {
			Player p = new Player("No Data","null",0);
			p.setCheakCode("ecf8");
			Players.add(p);
		}
	}

	/**
	 * д�뱾������
	 * */
	public void setWorldData(Player p) {
		List<Player> players = game.getwPlayers();
		players.add(p);
		try{
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("data/record.dat"));
			out.writeObject(players);
			out.close();
			game.setwPlayers(players);
			TimeUtil.message("Local:�ǩ`���������z�ߤ��ޤ���");
		}catch(IOException e){
			TimeUtil.message("Local:�ǩ`���Ε����z�ߤ�ʧ�����ޤ���");
		}
		Collections.sort(players);
		game.setwPlayers(players);
	}
	
	public void setGame(GameService game) {
		this.game = game;
	}

	/**
	 * ��ձ�������
	 * */
	public void deleteLocalData() {
		try{
			List<Player> players = new ArrayList<Player>();
			setNullDate(players);
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("data/record.dat"));
			out.writeObject(players);
			out.close();
			game.setwPlayers(players);
			TimeUtil.message("Local:�ǩ`�����������ޤ���");
		}catch(Exception e){
			TimeUtil.message("Local:�ǩ`����������ʧ�����ޤ���");
		}
	}

	public void readTarena() {
		readTarena = new Thread(this);
		readTarena.start();
	}
	
//	public Thread getReadTarena() {
//		return ReadTarena;
//	}
}