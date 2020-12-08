package sprites;

import java.awt.Graphics;
import java.awt.Image;

import util.Assets;

/*
 * SARA GIL GONZALEZ
 * 
 */

public class Laser {

	private Image laser;
	public int x;
	public int y;

	public Laser(int x) {
		this.x = x;
		y = 360;
		laser = Assets.iilaser.getImage();
	}

	public void move() {
		y -= 10;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void draw(Graphics g) {
		g.drawImage(laser, x, y, null);
	}

}
