package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	private boolean[] keys = new boolean[6];
	private boolean[] keys1 = new boolean[4];
	private boolean[] keys2 = new boolean[4];
	
	public boolean[] getKeys() {
		return keys;
	}
	
	public boolean[] getKeys(int player) {
		if (player == 1)
			return keys1;
		return keys2;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			keys[0] = true;
		}
		
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			keys[1] = true;
		}
		
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			keys[2] = true;
		}
		
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			keys[3] = true;
		}
		
		if (code == KeyEvent.VK_SPACE) {
			keys[4] = true;
		}
		
		if (code == KeyEvent.VK_ENTER) {
			keys[5] = true;
		}
		
		if (code == KeyEvent.VK_W ) {
			keys1[0] = true;
		}
		
		if (code == KeyEvent.VK_S) {
			keys1[1] = true;
		}
		
		if (code == KeyEvent.VK_A) {
			keys1[2] = true;
		}
		
		if (code == KeyEvent.VK_D ) {
			keys1[3] = true;
		}
		
		if (code == KeyEvent.VK_UP ) {
			keys2[0] = true;
		}
		
		if (code == KeyEvent.VK_DOWN) {
			keys2[1] = true;
		}
		
		if (code == KeyEvent.VK_LEFT) {
			keys2[2] = true;
		}
		
		if (code == KeyEvent.VK_RIGHT ) {
			keys2[3] = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			keys[0] = false;
		}
		
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			keys[1] = false;
		}
		
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			keys[2] = false;
		}
		
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			keys[3] = false;
		}
		
		if (code == KeyEvent.VK_SPACE) {
			keys[4] = false;
		}
		
		if (code == KeyEvent.VK_ENTER) {
			keys[5] = false;
		}
			
		if (code == KeyEvent.VK_W ) {
			keys1[0] = false;
		}
		
		if (code == KeyEvent.VK_S) {
			keys1[1] = false;
		}
		
		if (code == KeyEvent.VK_A) {
			keys1[2] = false;
		}
		
		if (code == KeyEvent.VK_D ) {
			keys1[3] = false;
		}
		
		if (code == KeyEvent.VK_UP ) {
			keys2[0] = false;
		}
		
		if (code == KeyEvent.VK_DOWN) {
			keys2[1] = false;
		}
		
		if (code == KeyEvent.VK_LEFT) {
			keys2[2] = false;
		}
		
		if (code == KeyEvent.VK_RIGHT ) {
			keys2[3] = false;
		
		}
	}
		
	
}