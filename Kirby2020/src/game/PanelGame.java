package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import input.InputHandler;
import sprites.Bomba;
import sprites.Estrella;
import sprites.Kirby;
import sprites.Laser;
import util.Assets;
import util.Constant;

/*
 * SARA GIL GONZALEZ
 * 
 */

@SuppressWarnings("serial")
public class PanelGame extends JPanel {

	public boolean isRunning = true;
	public boolean isPause = false;
	private InputHandler input;
	public GameLoop gameLoop;

	// ACTORES
	private Kirby kirby;
	private ArrayList<Laser> lasers;
	private Bomba bomba;
	private ArrayList<Bomba> bombas;
	private Estrella estrella;
	private ArrayList<Estrella> estrellas;

	//controles actores
	private int moverkirby = 0;
	private int moverbomba = 0;
	private int moverestrella = 0;

	private int nacelaser = 0;
	private int nacebomba = 0;
	private int naceestrella = 0;
	
	// score
	private int score=0;
	
	public PanelGame() {
		super();
		Assets.loadAssets();
		
		gameLoop = new GameLoop(this);
		input = new InputHandler(this);

		kirby = new Kirby();
		lasers = new ArrayList<Laser>();
		bombas = new ArrayList<Bomba>();
		estrellas = new ArrayList<Estrella>();

	}

	@SuppressWarnings("deprecation")
	void update() {
		//-----TECLAS
		if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
			kirby.right();
		}
		if (input.isKeyDown(KeyEvent.VK_LEFT)) {
			kirby.left();
		}
		if (input.isKeyDown(KeyEvent.VK_SPACE)) {
			// retrasar el disparo al pulsar muchos espacios en blanco
			nacelaser = ++nacelaser % Constant.VECESNACELASER;
			if (nacelaser == 0) {
				// añadimos el laser al array
				lasers.add(new Laser(kirby.getX()));

				// sonido disparo
				Assets.audiolaser.play();
			}
		}

		//--------KIRBY
		// mover
		moverkirby = ++moverkirby % Constant.VECESMOVERKIRBY;
		if (moverkirby == 0) {
			kirby.update();
		}

		// movimiento laser de kirby
		for (Laser laser : lasers) {
			laser.move();
			// si llega arriba lo eliminamos del array
			if (laser.getY() < 0) {
				lasers.remove(laser);
				break;
			}
		}

		// -----ESTRELLAS
		// mover
		moverestrella = ++moverestrella % Constant.VECESMOVERESTRELLA;
		if (moverestrella == 0) {
			for (Estrella estrella : estrellas) {
				estrella.update();
			}
		}

		// nacer
		naceestrella = ++naceestrella % Constant.VECESNACEESTRELLA;
		if (naceestrella == 0 && estrellas.size() < 20) {
			estrella = new Estrella();
			estrellas.add(estrella);
		}

		// ------BOMBA----------------
		// mover
		moverbomba = ++moverbomba % Constant.VECESMOVERBOMBA;
		if (moverbomba == 0) {
			for (Bomba bomba : bombas) {
				bomba.update();
			}
		}

		// nacer
		nacebomba = ++nacebomba % Constant.VECESNACEBOMBA;
		if (nacebomba == 0 && bombas.size() < 10) {
			bomba = new Bomba();
			bombas.add(bomba);
		}

		// -----COLISIONES
		for (Laser laser : lasers) {
			for (Estrella estrella : estrellas) {
				if (estrella.colision(laser)) {
					score+=15;
					//choque estrella audio
					Assets.audiovida.play();
					estrellas.remove(estrella);
					break;
				}
			}
			for (Bomba bomba : bombas) {
				if (bomba.colision(laser)) {
					gameOver();
					return;
				}
			}
		}

	}

	private void drawScore(Graphics2D g) {
		g.setColor(Color.decode("#C1008F"));
		g.drawString("Score: "+score, 20, 20);
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		//Fondo
		g2d.setColor(Color.decode("#95E4E8"));
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		//score
		drawScore(g2d);
		
		// kirby
		kirby.draw(g2d);

		// lasers
		for (Laser laser : lasers) {
			laser.draw(g2d);
		}
		// bombas
		for (Bomba bomba : bombas) {
			bomba.draw(g2d);
		}

		// estrellas
		for (Estrella estrella : estrellas) {
			estrella.draw(g2d);
		}

	}

	public void start() {
		gameLoop.start();
	}
	
	@SuppressWarnings("deprecation")
	private void gameOver() {
		isRunning = false;
		Assets.audioexplosion.play();
		JOptionPane.showMessageDialog(this, "Game Over");
	} 
}
