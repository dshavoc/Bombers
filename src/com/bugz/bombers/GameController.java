package com.bugz.bombers;

import java.util.Vector;

import com.bugz.bombers.players.*;

enum GameState {
	SETUP,
	PLAYING,
	PAUSED
}

/*
 * The Game Controller holds the high-level state machine(s), as well as hooks to all the relevant data
 * to assure valid transitions, such as players, map...
 */
public class GameController {

	/*
	 * Players
	 */
	Vector<Player> players;
	private final int MAX_PLAYERS = 2;
	
	GameState gameState;
	
	public GameController() {
		players = new Vector<Player>();
		gameState = GameState.SETUP;
	}
	
	/*
	 * Game Setup Functions (Inputs)
	 */
	public void registerPlayer(Player p) {
		if( players.size() < MAX_PLAYERS ) {
			players.add(p);
		}
	}
	
	/*
	 * State Control
	 */
	
	//Checks whether the requirements to start a game have been met, then advances the state
	public boolean startGame() {
		boolean startIsValid = true;
		
		//Check minimum player requirement
		if( players.size() == 0 )
			startIsValid = false;
		
		if(startIsValid && gameState == GameState.SETUP) {
			gameState = GameState.PLAYING;
			return true;
		}
		else {
			return false;
		}
	}
	
	public void pauseGame() {
		if(gameState == GameState.PLAYING) {
			gameState = GameState.PAUSED;
		}
	}
	
	public void resumeGame() {
		if(gameState == GameState.PAUSED) {
			gameState = GameState.PLAYING;
		}
	}
	
	/*
	 * Game State Representation
	 */
	
	//Is game time currently ticking
	public boolean isRunning() {
		return gameState == GameState.PLAYING;
	}
}
