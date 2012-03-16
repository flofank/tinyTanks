package control.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import control.Game;

public class MyMouseMotionListener implements MouseMotionListener{

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		Game.getInstance().setTank(arg0.getX());
		
	}

}
