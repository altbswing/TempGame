package ui.window.setter;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import util.ComponentUtil;

public class FrameSetter extends JFrame {
	private static final long serialVersionUID = -2852502847629524502L;

	private JButton selectBtn = null;

	public FrameSetter() {
		this.setResizable(false);
		this.setDefaultCloseOperation(3);//
		this.setContentPane(mainPanel());
		this.setSize(300, 200);
		ComponentUtil.center(this);
		this.setVisible(true);
	}

	private JPanel mainPanel() {
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		jp.add(selectPanel(), BorderLayout.NORTH);
		String[] items = new String[] { "1111", "2222", "3333", "1111", "2222",
				"3333", "1111", "2222", "3333", "1111", "2222", "3333" };//
		jp.add(selectList(items));
		return jp;
	}

	private JPanel selectPanel() {
		JPanel jp = new JPanel();
		return jp;
	}

	private JScrollPane selectList(String[] item) {
		JList sl = new JList(item);
		sl.setVisibleRowCount(2);
		JScrollPane jsp = new JScrollPane(sl);
		return jsp;
	}

	public static void main(String[] args) {
		byte[] hex = new byte[] { -104, -11, -15 };
	}

}
