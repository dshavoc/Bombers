package com.bugz.bombers.graphics;

public class Screen {

	private int width, height;
	public int[] pixels;
	
	public Screen(int width, int height, int[] px) {
		this.width = width;
		this.height = height;
		this.pixels = px;	//This class will directly modify the array reference passed in		
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = 0x8080FF;	//light blue
	}
	
	public void render() {
		
	}
}
