package com.bugz.bombers.players;

//This class represents a player to the game controller. It does not represent an actor in the game, but
//an interface to a human or AI game participant
public abstract class Player {
	
	public String name;
	
	public abstract boolean isCmdUp();
	public abstract boolean isCmdDown();
	public abstract boolean isCmdLeft();
	public abstract boolean isCmdRight();
	public abstract boolean isCmdAct1();
	public abstract boolean isCmdAct1Hold();
}
