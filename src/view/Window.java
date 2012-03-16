package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Game;


public class Window extends JFrame{
	private JLabel info;
	private JPanel world;
	private int border = 12;
	private int space_top = 20;
		
	public Window(String title) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Game.WORLDSIZE.width + border * 2, Game.WORLDSIZE.height + border * 2 + space_top);
		getContentPane().setBackground(Color.BLACK);
		setLayout(null);
		setLocationRelativeTo(null);
		setTitle(title);
		setResizable(false);
		setUndecorated(true);
		
		initComponents();
	}
	public void addWorld(World world) {
		this.world = world;
		add(world);
		world.setLocation(border, border + space_top);
	}
	private void initComponents() {
		info = new JLabel();
		info.setText("infofield");
		info.setForeground(Color.WHITE);
		info.setLocation(border,border);
		info.setSize(100, 20);
		add(info);
	}
	@Override
	public void addKeyListener(KeyListener l) {
		super.addKeyListener(l);
		world.addKeyListener(l);
	}
	@Override
	public void addMouseListener(MouseListener l) {
		super.addMouseListener(l);
		world.addMouseListener(l);
		
	}
	@Override
	public void addMouseMotionListener(MouseMotionListener l) {
		super.addMouseMotionListener(l);
		world.addMouseMotionListener(l);
	}	

	public void setInfo(String text) {
		
		info.setText(text);
	}
	public Graphics giveTheGraphics() {
		return world.getGraphics();
	}
}
