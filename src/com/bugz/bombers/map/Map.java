package com.bugz.bombers.map;

import java.awt.Graphics;

public class Map {
	private int width, height;
	private int[][] map;
	
	public Map(int height, int width) {
		map = new int[width][height];
	}
	
	public boolean isPassable(int x, int y) {
		return true;
	}
	
	public boolean isDestructible(int x, int y) {
		return false;
	}
	
	public void draw(Graphics g) {
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				//map[i][j].draw();
			}
		}
	}
}
