package model;

import java.awt.Dimension;
import java.awt.Graphics;

import control.Game;

public abstract class GameObject {
	protected Ground ground;
	protected float x, y, vx, vy;
	protected boolean visible;
	protected boolean grav;
	protected long lastAcc;
	protected Dimension collBox;
	
	public GameObject(int x, int y, Dimension collBox, Ground ground) {
		this.ground = ground;
		this.x = x;
		this.y = y;
		this.collBox = collBox;
		lastAcc = System.currentTimeMillis();
		vx = 0;
		vy = 0;
		visible = true;
		grav = true;
	}
	
	public void show() {
		visible = true;
	}
	public void hide() {
		visible = false;
	}
	public void setGravityState(boolean on) {
		grav = on;
	}
	
	public void move(int dx, int dy) {
		long now = System.currentTimeMillis();
		if (grav) {
			vy -= ((double) (now - lastAcc) / 1000) * Game.GRAVITY;
		}
		this.x += ((double) (now - lastAcc) / 1000) * vx + dx;
		this.y += ((double) (now - lastAcc) / 1000) * vy + dy;	
		lastAcc = now;
		groundCollision();
	}
	public void move() {
		move(0,0);
	}
	public void moveX(int d) {
		move(d,0);
	}
	public void moveY(int d) {
		move(0,d);
	}
	public void setX(int x) {
		this.x = x;
		this.y = 0;
		groundCollision();
	}
	public void setY(int y) {
		this.y = y;
	}
	public void accX(float v) {
		this.vx += v;
	}
	public void accY(float v) {
		this.vy += v;
	}
	public void acc(float vx, float vy) {
		this.vx += vx;
		this.vy += vy;
	}
	public void stopX() {
		this.vx = 0;
	}
	public void stopY() {
		this.vy = 0;
	}
	public void stop() {
		stopX();
		stopY();
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	
	public boolean getVisible() {
		return visible;
	}
	
	public abstract void paint(Graphics g);
	
	private void groundCollision() {
		int groundHeight = ground.getLevel((int) (x + collBox.width / 2));
		if (groundHeight > y - collBox.height) {
			y = groundHeight + collBox.height;
			stopY();
		}
	}
	
}
