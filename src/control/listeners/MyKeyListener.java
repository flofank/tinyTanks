package control.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import control.Game;

public class MyKeyListener implements KeyListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			Game.stopGame();
			break;
		case KeyEvent.VK_SPACE:
			Game.getInstance().shoot();
			break;
		case KeyEvent.VK_RIGHT:
			Game.getInstance().move(1);
			break;
		case KeyEvent.VK_LEFT:
			Game.getInstance().move(-1);
			break;
		case KeyEvent.VK_UP:
			Game.getInstance().up();
			break;
		case KeyEvent.VK_DOWN:
			Game.getInstance().down();
			break;
		case KeyEvent.VK_NUMPAD8:
			Game.getInstance().powerUp();
			break;
		case KeyEvent.VK_NUMPAD2:
			Game.getInstance().powerDown();
			break;
		default:
			System.out.println(KeyEvent.getKeyText(arg0.getKeyCode()));
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
