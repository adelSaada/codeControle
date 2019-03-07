package fr.exercice.codeLuhn;

import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * 
 * @author Adel SAADA KHELKHAL
 *
 */
public class Main {

	public static void main(String[] args) throws IOException{

		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel()); //utilisation d'un look type Nimbus pour un meilleur visuel de la fenetre.
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		FenetreCodeLuhn maFenetre = new FenetreCodeLuhn();
	}

}
