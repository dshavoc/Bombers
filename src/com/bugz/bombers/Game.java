package com.bugz.bombers;

import javax.swing.JFrame;

import com.bugz.bombers.graphics.Screen;
import com.bugz.bombers.input.Keyboard;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private JFrame frame;
	
	public static int HEIGHT = 600;
	public static int WIDTH = HEIGHT * 16 / 9;
	public static String title = "Bombers";
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private Screen screen;
	
	//Game thread variables
	private Thread thread;
	private boolean gameThreadRunning = false;
	
	Keyboard keyboard;
	
	private double targetUPS = 30;
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
	
	public Game() {
		frame = new JFrame();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		keyboard = new Keyboard();
		addKeyListener(keyboard);
		
		screen = new Screen(WIDTH, HEIGHT, pixels);
	}


	public void run() {
		long lastUpdate = System.nanoTime();
		long now;
		long counter = System.currentTimeMillis();
		double nsPerUpdate = 1000000000 / targetUPS;
		int renderCount = 0, updateCount = 0;	//Since last report
		double numUpdatesPending = 0;			//Yes, it's a fraction
		
		requestFocus();
		
		while(gameThreadRunning) {
			now = System.nanoTime();
			numUpdatesPending += (now - lastUpdate) / nsPerUpdate;
			lastUpdate = now;
			
			while(numUpdatesPending >= 1) {
				update();
				updateCount++;
				numUpdatesPending -= 1.0;
			}
			
			
			render();
			renderCount++;
			
			//Limit fps to 1000. Otherwise it was doing 32Mfps!
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//report
			now = System.currentTimeMillis();
			if(now - counter > 1000) {
				frame.setTitle(title + " | " + updateCount + " ups, " + renderCount + " fps");
				updateCount = 0;
				renderCount = 0;
				counter += 1000;
			} else if(now - counter < 0) {
				counter = System.currentTimeMillis();
				System.err.println("Midnight");
			}
			
		}
	}

	private void update() {
		keyboard.update();
		System.out.println(keyboard.act1long);
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		//Drawing code here
		screen.clear();
		screen.render();
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		g.dispose();
		bs.show();
		
	}
	
	public synchronized void start() {
		gameThreadRunning = true;
		thread = new Thread(this, "Game");
		thread.start();
	}
	
	public synchronized void stop() {
		gameThreadRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
