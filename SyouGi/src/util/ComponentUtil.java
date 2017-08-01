package util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import cfg.Text;

public class ComponentUtil {

	/**
	 * 剧中方法
	 * 
	 * @author 刘苍松
	 * */
	public static void center(final JFrame frame) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		Dimension w = frame.getSize();
		int x = (screen.width - w.width) / 2;
		int y = (screen.height - w.height) / 2;
		frame.setLocation(x, y - 16);
	}

	/**
	 * 窗口初始化
	 * */
	public static void frameInit(final JFrame frame) {
		center(frame);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(0);
	}

	/**
	 * 
	 * */
	public static void exit(final JFrame frame) {
		int val = JOptionPane.showConfirmDialog(frame, Text.EXIT,
				UIManager.getString("OptionPane.titlseText"),
				JOptionPane.YES_NO_OPTION);
		if (val == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}
