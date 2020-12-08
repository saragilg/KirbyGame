package sprites;

import java.awt.Graphics;
import java.awt.Image;

import util.Assets;
import util.Constant;

/*
 * SARA GIL GONZALEZ
 * 
 */

public class Kirby {
	
	private Image kirby;
	private int x;
	private int y;
	private int rowFrame;
	private int columnframe;
	private int ANCHO;
	private int ALTO;
	private final static int COLUMNAS = 8;
	private final static int FILAS = 2;

	public Kirby() {
		x = (int) (Math.random()*(Constant.WIDTHSCREEN-ANCHO));
		y = 360;
		columnframe = 0;
		ANCHO = Assets.iikirby.getIconWidth() / COLUMNAS;
		ALTO = Assets.iikirby.getIconHeight() / FILAS;
		kirby = Assets.iikirby.getImage();
	}
	public void left(){
		rowFrame = 1;
		x-=10;
		if (x < 0) x=0;
	}
	
	public void right(){
		rowFrame = 0;
		x+=10;
		if (x > (Constant.WIDTHSCREEN-ANCHO)) x=Constant.WIDTHSCREEN-ANCHO;
	}
	

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void update() {
		columnframe = ++columnframe % 8;
	}

	public void draw(Graphics g) {
		int frameX = columnframe * ANCHO;
		int frameY = rowFrame * ALTO;
		g.drawImage(kirby, x, y, x + ANCHO, y + ALTO, frameX, frameY, frameX + ANCHO, frameY + ALTO, null);
	}
}
