package control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import model.*;
import control.*;
import control.listeners.*;
import view.*;

public class Game implements Runnable {
	public static final int FRAMERATE = 25;
	public static final double GRAVITY = 150;
	public static Dimension WORLDSIZE;
	
	private static Game inst;
	private Thread animator;
	private Window window;
	private World world;
	private Ground ground;
	private Tank t1;
	private ArrayList<Ball> balls;
	
	
	public static void startGame(Dimension worldSize) {
		Game.WORLDSIZE = worldSize;
		if (inst == null) {
			inst = new Game();
		}
	}
	public static Game getInstance() {
		return inst;
	}
	public static void stopGame() {
		inst.stop();
	}
	
	
	private Game() {
		window = new Window("Tanks");
		world = new World();
		window.addWorld(world);
		window.addKeyListener(new MyKeyListener());
		window.addMouseMotionListener(new MyMouseMotionListener());
		createGameObjects();		
		window.setVisible(true);
		animator = new Thread(this);
		animator.start();
	}
	private void createGameObjects() {
		ground = new Ground();
		balls = new ArrayList<Ball>();
		t1 = new Tank(50, 50, ground);
	}
	private void stop() {
		System.exit(0);
	}
	private void gamecycle() {
		window.repaint();
		t1.move();
		for (Ball b : balls) {
			b.move();
		}
		//window.setInfo("x: " + t1.getX() + " | y: " + t1.getY());
	}
	public void paint(Graphics g) {
		ground.paint(g);
		t1.paint(g);
		for (Ball b : balls) {
			b.paint(g);
		}
	}
	
	
	//
	//		Key Events
	//
	public void setTank(int x) {
		t1.setX(x);	
	}	
	public void shoot() {
		balls.add(t1.shoot());
	}
	public void move(int direction) {
		t1.moveX(1 * direction);	
	}
	public void up() {
		t1.up();
	}
	public void down() {
		t1.down();
	}
	public void powerDown() {
		window.setInfo(t1.powerDown() + "");
	}
	public void powerUp() {
		window.setInfo(t1.powerUp() + "");
	}
	
	
	
	/**
	 * Timed Animation Thread
	 */
	@Override
	public void run() {
		long beforeTime, timeDiff, sleep;
		beforeTime = System.currentTimeMillis();
		
		while (true) {
			gamecycle();
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = Game.FRAMERATE - timeDiff;
			
			if (sleep > 0) {
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					System.out.println("interrupted");
				}
			}			
			beforeTime = System.currentTimeMillis();
		}
	}	
}
