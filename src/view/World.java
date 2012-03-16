package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import control.Game;

public class World extends JPanel {
	private static String background_image = "a_sky_2.jpg";
	//private static String background_image = "";
	
	public World() {
		super();
		setSize(Game.WORLDSIZE.width, Game.WORLDSIZE.height);
		setBackground(Color.BLUE);
		setDoubleBuffered(true);
		setFocusable(true);
		requestFocus();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		paintBackground(g);	
		Game.getInstance().paint(g);
	}
	
	private void paintBackground(Graphics g) {
		try {
			Graphics2D g2 = (Graphics2D) g;		
			BufferedImage b = ImageIO.read(this.getClass().getResource(background_image));
			TexturePaint tp = new TexturePaint(b, new Rectangle(0,0,b.getWidth(),b.getHeight()));
			g2.setPaint(tp);	
			g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		} catch (Exception e) {}
	}

}
