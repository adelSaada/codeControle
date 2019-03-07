package fr.exercice.codeLuhn;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

/**
 * 
 * @author Adel SAADA
 *
 */
public class FenetreCodeLuhn extends JFrame {
	

	private static final long serialVersionUID = 4493984067056073040L;
	
	
	private JPanel contener;
	private JPanel contenuSaisie = new JPanel();
	
	private JFormattedTextField saisieTexteFormattee;
	private JLabel presentation = new JLabel("Saisie numéro de Carte Bancaire : ");
	private JButton calculer = new JButton("Calculer");
	private JLabel resultat = new JLabel("code Luhn :  ");
	
	private JLabel validite = new JLabel("   "); // un espace vide pour que la méthode pack() ne réduise pas trop l'affichage du JLabel.
	
	
	public FenetreCodeLuhn() throws IOException {
		
		this.setTitle("Vérificateur Carte Bancaire");
		
		this.setIconImage(ImageIO.read(new File("iconeVisa.png"))); // l'icone doit se trouver dans le repertoire de ProjetCodeLuhn
		this.setResizable(false);
		this.setSize(400,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		contener = new JPanel();
		contener.setLayout(new BoxLayout(contener,BoxLayout.Y_AXIS));
		
		/* Centrer l'alignement horizontal des composants */
		presentation.setAlignmentX(CENTER_ALIGNMENT);
		calculer.setAlignmentX(CENTER_ALIGNMENT);
		resultat.setAlignmentX(CENTER_ALIGNMENT);
		validite.setAlignmentX(CENTER_ALIGNMENT);
		
		contenuSaisie.add(presentation);
		
		/* Utilisation d'un MaskFormatter pour la saisie des nombres de la Carte Bancaire. */
		try {
			MaskFormatter formatCB = new MaskFormatter("####-####-####-####");
			saisieTexteFormattee = new JFormattedTextField(formatCB);
			saisieTexteFormattee.setAlignmentX(CENTER_ALIGNMENT);
			saisieTexteFormattee.setPreferredSize(new Dimension(140,25));
			contenuSaisie.add(saisieTexteFormattee);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		contener.add(contenuSaisie);
		contener.add(Box.createVerticalStrut(20));
		contener.add(calculer);
		contener.add(Box.createVerticalStrut(20));
		contener.add(resultat);
		contener.add(validite);
		
		// inférence de type : la méthode attend une autre méthode contenant en paramètre un objet de type ActionEvent.
		calculer.addActionListener(this::actionBoutonCalculer);						

		
		this.getContentPane().add(contener);
		
		pack();
		this.setVisible(true);

	}
	
	/**
	 * Méthode liée à l'action du bouton "calculer"
	 * 1/ Récupère la chaine contenu dans le JField formatté (en enlevant le séparateur "-")
	 * 2/ Vérifie si la chaine est valide (non null, 16 caractères)
	 * 3/ La transmet en paramètre de la méthode codeLuhn 
	 * 
	 * 4/ modifie le texte et la couleur du Jlabel en fonction du resultat de la méthode codeLuhn.
	 */
	
	private void actionBoutonCalculer(ActionEvent e) {
		String valeur = saisieTexteFormattee.getText();
		StringTokenizer st = new StringTokenizer(valeur,"-");
		
		String nouvelleValeur ="";
	    while (st.hasMoreTokens()) {
	    	nouvelleValeur += st.nextToken();
        }
		if(nouvelleValeur.length() == 16 && nouvelleValeur != null) {
			
			try {
				int code = CodeLuhn.calculCodeLuhn(nouvelleValeur);
				resultat.setText("code Luhn : "+code);
	
				int derniereValeurChaine = Integer.parseInt(""+nouvelleValeur.charAt(nouvelleValeur.length()-1));
				
				if( derniereValeurChaine == code) {
					validite.setForeground(Color.decode("#6B8E23")); // couleur vert foncé
					validite.setText("Carte Bancaire valide !");
				}
				else {
					validite.setForeground(Color.red);
					validite.setText("Carte Bancaire Invalide !");
				}
			} catch(NumberFormatException ex) {
				validite.setForeground(Color.RED);
				validite.setText("Veuillez saisir un nombre constitué de 16 chiffres !");
			}
			
		}
		
	}

}
