package com.bugz.bombers.players;

import com.bugz.bombers.input.Keyboard;

public class LocalPlayer extends Player {

	Keyboard keyboard;
	
	public LocalPlayer(Keyboard key) {
		keyboard = key;
	}
	
	@Override
	public boolean isCmdUp() {
		return keyboard.up;
	}

	@Override
	public boolean isCmdDown() {
		return keyboard.down;
	}

	@Override
	public boolean isCmdLeft() {
		return keyboard.left;
	}

	@Override
	public boolean isCmdRight() {
		return keyboard.right;
	}

	@Override
	public boolean isCmdAct1() {
		return keyboard.act1;
	}

	@Override
	public boolean isCmdAct1Hold() {
		return keyboard.act1long;
	}

}
