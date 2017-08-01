package util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

public class JButtonEx extends JButton {
	private static final long serialVersionUID = 6640554570736375116L;
	
	private final int KeyCode;
	private final String text;
	
	public JButtonEx(String text, int keyCode) {
		this.KeyCode = keyCode;
		this.text = text;
		this.setText(text);
		this.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode() == KeyCode) {
					
				}
			}
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
		});
	}

}
