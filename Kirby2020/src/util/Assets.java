package util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;

/*
 * SARA GIL GONZALEZ
 * 
 */

@SuppressWarnings("deprecation")
public class Assets {
	
    public static ImageIcon iikirby;
    public static ImageIcon iilaser;
    public static ImageIcon iibomba;
    public static ImageIcon iiestrella;
    
	public static AudioClip audiolaser;
	public static AudioClip audiovida;
	public static AudioClip audioexplosion;
    
      
    public static void loadAssets() {
		iikirby = new ImageIcon("assets/kirbysheet.png");
		iilaser = new ImageIcon("assets/laser.png");
		iibomba = new ImageIcon("assets/bomb.png");
		iiestrella = new ImageIcon("assets/starssheet.png");
		
		try {
			audiolaser = Applet.newAudioClip(new File("assets/ball.wav").toURI().toURL());
			audiovida = Applet.newAudioClip(new File("assets/getvida.wav").toURI().toURL());
			audioexplosion = Applet.newAudioClip(new File("assets/explosion.wav").toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
    }
}