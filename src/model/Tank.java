package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import control.Game;

public class Tank extends GameObject{
	private static Dimension size = new Dimension(20,10);
	private int angle = 30;
	private int direction = 1;
	private int power = 200;
	
	public Tank(int x, int y, Ground ground) {
		super(x, y, size, ground);
	}

	@Override
	public void paint(Graphics g) {
		//Tank
		g.setColor(Color.YELLOW);
		g.fillRect((int) x, (int) (Game.WORLDSIZE.height - y), size.width, size.height);
		g.setColor(Color.BLACK);
		g.fillRect((int) x+1, (int) (Game.WORLDSIZE.height - y + 1), size.width - 2, size.height - 2);
		
		//Canon
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		int dx = (int) ((collBox.width / 4 * 3) * Math.cos(Math.toRadians(angle)) * direction);
		int dy = (int) ((collBox.width / 4 * 3) * Math.sin(Math.toRadians(angle))); 
		g2.drawLine((int) x + collBox.width / 2,(int) (Game.WORLDSIZE.height - (y - 4)),(int) x + collBox.width / 2 + dx,(int) (Game.WORLDSIZE.height - (y - 4 + dy)));
	}
	
	public Ball shoot() {
		int dx = (int) ((collBox.width / 4 * 3) * Math.cos(Math.toRadians(angle)) * direction);
		int dy = (int) ((collBox.width / 4 * 3) * Math.sin(Math.toRadians(angle))); 
		Ball b = new Ball((int) x + collBox.width / 2 + dx,(int) y - 4 + dy, ground);
		b.accX( (float) (Math.cos(Math.toRadians(angle)) * direction * power));
		b.accY( (float) Math.sin(Math.toRadians(angle)) * power);
		return b;
	}
	
	public int powerUp() {
		return power++;
	}
	public int powerDown() {
		return power--;
	}
	public void up() {
		if (angle < 90) {
			angle++;
		}
	}
	public void down() {
		if (angle > 0) {
			angle--;
		}
	}
	@Override
	public void moveX(int d) {
		if (d > 0) {
			direction = 1;
		} else {
			direction = -1;
		}
		move(d, 0);
	}

}
