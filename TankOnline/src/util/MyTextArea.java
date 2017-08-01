package util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextArea;

/**
 * 不可修改内容的多行文本域
 * @author 小翼
 * */
public class MyTextArea extends JTextArea {
	private static final long serialVersionUID = 8982883182346382734L;

	public MyTextArea(){
		super();
		init();
	}
	
//	public MyTextArea(String string, int rows, int columns) {
//		super(string, rows, columns);
//		init();
//	}
	
	public void init(){
		addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent key) {
				key.consume();
			}

			public void keyPressed(KeyEvent key) {
				JTextArea temp = (JTextArea) key.getSource();
				int caretPos = temp.getCaretPosition();
				switch (key.getKeyCode()) {
				case (KeyEvent.VK_LEFT):
				case (KeyEvent.VK_BACK_SPACE):
					if (caretPos > 0) {
						temp.setCaretPosition(caretPos - 1);
					}
					break;
				case (KeyEvent.VK_RIGHT):
					if (caretPos < temp.getText().length()) {
						temp.setCaretPosition(caretPos + 1);
					}
					break;
				case (KeyEvent.VK_DELETE):
					System.out.println("aaa");
					break;
				default:
					break;
				}
				key.consume();
			}

			public void keyReleased(KeyEvent key) {
				key.consume();
			}
		});
	}
	
	public void showMessage(String str) {
		this.append(TimeUtil.timeMessage(str)+"\n\r");
		this.setSelectionStart(this.getText().length());
	}
	
	public void showMessage(int i){
		showMessage(new Integer(i).toString());
	}
}