package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import control.Game;

public class Ball extends GameObject{
	private static Dimension size = new Dimension(4,4);
	
	public Ball(int x, int y, Ground ground) {
		super(x, y, size, ground);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval((int) x, (int) (Game.WORLDSIZE.height - y), size.width, size.height);
		g.setColor(Color.RED);
		g.fillOval((int) x + 1, (int) (Game.WORLDSIZE.height - (y + 1)), size.width - 2, size.height - 2);
	}

}
