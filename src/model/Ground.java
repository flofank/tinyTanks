package model;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

import control.Game;

public class Ground {
	//private static String background_image = "";
	//private static String background_image = "t_ground.jpg";
	private static String background_image = "a_ground_2.jpg";
	private Area ground;
	private Polygon g_poly;
	
	public Ground() {
		g_poly = new Polygon();
		g_poly.addPoint(0, Game.WORLDSIZE.height);
		Random r = new Random();
		int level = r.nextInt(Game.WORLDSIZE.height / 2);
		int mountain_width = r.nextInt(40) + 20;
		int riser = r.nextInt(3) - 1;
		for (int x = 0; x <= Game.WORLDSIZE.width; x++) {
			level += r.nextInt(3) - 1 + riser;
			if (level < Game.WORLDSIZE.height / 8 ) {
				level = Game.WORLDSIZE.height / 8;
			}
			if (x % mountain_width == 0) {
				riser = r.nextInt(3) - 1;
				mountain_width = r.nextInt(40) + 20;
			}
			g_poly.addPoint(x, Game.WORLDSIZE.height - level);
		}
		g_poly.addPoint(Game.WORLDSIZE.width, Game.WORLDSIZE.height);
		ground = new Area(g_poly);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		try {
			BufferedImage b = ImageIO.read(this.getClass().getResource(background_image));
			TexturePaint tp = new TexturePaint(b, new Rectangle(0,0,b.getWidth(),b.getHeight()));
			g2.setPaint(tp);	
		} catch (IOException e) {
			g2.setStroke(new BasicStroke(1));
			g2.setColor(Color.green);
		}
			
		g2.fillPolygon(g_poly);
	}
	
	public int getLevel(int x) {
		if (x <= Game.WORLDSIZE.width && x >= 0) {
			return Game.WORLDSIZE.height - g_poly.ypoints[x];
		}
		return 0;
	}
}
