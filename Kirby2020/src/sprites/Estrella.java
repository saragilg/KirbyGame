package sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import util.Assets;
import util.Constant;

/*
 * SARA GIL GONZALEZ
 * 
 */

public class Estrella {
	
	private Image estrella;
	
	private int x;
	private int y;
	
	private int columnframe;
	private int rowFrame;
	
	private int ANCHO;
	private int ALTO;
	
	private final static int COLUMNAS = 8;
	private final static int FILAS = 2;
	
	private int speed;

	public Estrella() {

		x = (int)(Math.random()*(Constant.WIDTHSCREEN-ANCHO));
		y = (int)(Math.random()*201);
		
		if (((int)(Math.random()*2))==0) {
			speed = 4;
			rowFrame = 1;
		}
		else {
			speed = -4;
			rowFrame = 0;
		}
		
		columnframe = 0;

		ANCHO = Assets.iiestrella.getIconWidth() / COLUMNAS;
		ALTO = Assets.iiestrella.getIconHeight() /FILAS;
		estrella = Assets.iiestrella.getImage();
	}

	public void move() {
		if (x<0) rowFrame = 0; // izquierda change a derecha;
		if (x > Constant.WIDTHSCREEN-ANCHO) rowFrame = 1; // derecha change a izquierda
		if ((x<0) || (x > Constant.WIDTHSCREEN-ANCHO)) speed*=-1;//  choca => change dir
		x += speed;
	}

	public void update() {
		move();
		columnframe = ++columnframe % 4;
	}

	public void draw(Graphics g) {
		int frameX = columnframe * ANCHO;
		int frameY = rowFrame * ALTO;
		g.drawImage(estrella, x, y, x + ANCHO, y + ALTO, frameX, frameY, frameX + ANCHO, frameY + ALTO, null);
	}
	
	public boolean colision(Laser laser) {
		Rectangle recEstrella = new Rectangle(x , y, ANCHO, ALTO);
		Rectangle recLaser = new Rectangle(laser.x, laser.y, Assets.iilaser.getIconWidth(),Assets.iilaser.getIconHeight());
		return recEstrella.intersects(recLaser);
	}


}
