package com.bugz.bombers.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private static int MAX_KEY = 120;
	public static int HOLD_DURATION = 10;	//how many updates of duration elapses before press is considered a long press
	
	private boolean[] keyDown = new boolean[MAX_KEY];	//is each key currently pressed
	private int[] keyPressDuration = new int[MAX_KEY];	//how many updates has each key been pressed
	
	public boolean up, down, left, right, act1, act1long;
	
	public void update() {
		//Update long holds
		for(int i = 0; i < MAX_KEY; i++) {
			if(keyDown[i]) keyPressDuration[i]++; else keyPressDuration[i] = 0;
		}
		up = keyDown[KeyEvent.VK_UP] || keyDown[KeyEvent.VK_W];
		down = keyDown[KeyEvent.VK_DOWN] || keyDown[KeyEvent.VK_S];
		left = keyDown[KeyEvent.VK_LEFT] || keyDown[KeyEvent.VK_A];
		right = keyDown[KeyEvent.VK_RIGHT] || keyDown[KeyEvent.VK_D];
		act1 = keyDown[KeyEvent.VK_E];
		act1long = keyPressDuration[KeyEvent.VK_E] > HOLD_DURATION; 
	}
	
	public void keyPressed(KeyEvent e) {
		keyDown[e.getKeyCode()] = true;		
	}

	public void keyReleased(KeyEvent e) {
		keyDown[e.getKeyCode()] = false;		
	}

	public void keyTyped(KeyEvent e) {
		//Deliberately empty
	}

}
