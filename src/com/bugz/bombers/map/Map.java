package com.bugz.bombers.map;

import java.awt.Graphics;

enum TileType {
	Floor,
	Wall,
	Block
}

public class Map {
	private int mapWidth, mapHeight;
	private int tileWidth = 20, tileHeight = 20;
	private TileType[][] map;
	
	public Map(int height, int width) {
		map = new TileType[width][height];
	}
	
	public boolean isPassable(int x, int y) {
		return map[x][y] == TileType.Floor;
	}
	
	public boolean isDestructible(int x, int y) {
		return false;
	}
	
	public void draw(Graphics g) {
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				drawTile(g, i, j, map[i][j]);
			}
		}
	}
	
	private void drawTile(Graphics g, int i, int j, int tileIndex) {
		
	}
}
