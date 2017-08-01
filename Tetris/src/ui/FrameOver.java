package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import service.GameService;

public class FrameOver extends JFrame implements ActionListener{
	private static final long serialVersionUID = 8074414246694926351L;
	
	private JTextField idField = new JTextField();
	private GameService game;
	private JButton commit; 

	public FrameOver(){
		this.init();
	}
	
	public FrameOver(GameService game){
		this.game = game;
		this.init();
	}
	
	private void init() {
		setTitle("你输了");
		setLocation(860,190);
		setResizable(false);
		setSize(300, 130);
		setContentPane(createPane());
	}
	
	private JPanel createPane(){
		JPanel pane = new JPanel(new BorderLayout());
		pane.setBorder(new EmptyBorder(8, 16, 8, 16));
		pane.add(BorderLayout.NORTH, new JLabel("名字必须是10位以内英文或数字", JLabel.CENTER));
		pane.add(BorderLayout.CENTER, createIdPane());
		pane.add(BorderLayout.SOUTH, createBtnPane());
		return pane;
	}
	
	private Component createIdPane() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(BorderLayout.WEST, new JLabel("请输入你的名字:"));
		idField = new JTextField();
		pane.add(BorderLayout.CENTER, idField);
		return pane;
	}
	
	private Component createBtnPane() {
		JPanel pane = new JPanel();
		commit = new JButton("提交");
		commit.addActionListener(this);
		pane.add(commit);
		getRootPane().setDefaultButton(commit);
		return pane;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == commit && idField.getText().matches("^\\w{1,10}$")) {
			game.setDate(idField.getText());
			this.setVisible(false);
		}
	}
	
	public String getId() {
		return idField.getText();
	}

	public void setGame(GameService game) {
		this.game = game;
	}
}
