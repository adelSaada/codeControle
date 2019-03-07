package cleControleCarteVitale;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.StringTokenizer;

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
public class FenetreCodeControleCarteVitale extends JFrame {
	

	private static final long serialVersionUID = 4493984067056073040L;
	
	
	private JPanel contener;
	private JPanel contenuSaisie = new JPanel();
	
	private JFormattedTextField saisieTexteFormattee;
	private JLabel presentation = new JLabel("Saisie num�ro de code INSEE : ");
	private JButton calculer = new JButton("Calculer");
	private JLabel resultat = new JLabel("Cl� de contr�le :  ");
	
	private JLabel validite = new JLabel("   "); // un espace vide pour que la m�thode pack() ne r�duise pas trop l'affichage du JLabel.
	
	
	public FenetreCodeControleCarteVitale() throws IOException {
		
		this.setTitle("Code Controle Carte Vitale");
		
		this.setResizable(false);
		this.setSize(400,200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		contener = new JPanel();
		contener.setLayout(new BoxLayout(contener,BoxLayout.Y_AXIS));
		
		/* Centrer l'alignement horizontal des composants */
		presentation.setAlignmentX(CENTER_ALIGNMENT);
		calculer.setAlignmentX(CENTER_ALIGNMENT);
		resultat.setAlignmentX(CENTER_ALIGNMENT);
		validite.setAlignmentX(CENTER_ALIGNMENT);
		
		contenuSaisie.add(presentation);
		
		/* Utilisation d'un MaskFormatter pour la saisie des 13 premiers nombres du code INSEE. */
		try {
			MaskFormatter formatCodeInsee = new MaskFormatter("#-##-##-##-###-###");
			saisieTexteFormattee = new JFormattedTextField(formatCodeInsee);
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
		
		// inf�rence de type : la m�thode attend une autre m�thode contenant en param�tre un objet de type ActionEvent.
		calculer.addActionListener(this::actionBoutonCalculer);						

		
		this.getContentPane().add(contener);
		
		pack();
		this.setVisible(true);

	}
	
	/**
	 * M�thode li�e � l'action du bouton "calculer"
	 * 1/ R�cup�re la chaine contenu dans le JField formatt� (en enlevant le s�parateur "-")
	 * 2/ V�rifie si la chaine est valide (non null, 16 caract�res)
	 * 3/ La transmet en param�tre de la m�thode codeLuhn 
	 * 
	 * 4/ modifie le texte et la couleur du Jlabel en fonction du resultat de la m�thode calculCodeSecuriteSociale.
	 */
	
	private void actionBoutonCalculer(ActionEvent e) {
		String valeur = saisieTexteFormattee.getText();
		StringTokenizer st = new StringTokenizer(valeur,"-");
		
		String nouvelleValeur ="";
	    while (st.hasMoreTokens()) {
	    	nouvelleValeur += st.nextToken();
        }
		if(nouvelleValeur.length() == 13 && nouvelleValeur != null) {
			
			try {
				long code = CodeControleSecuriteSociale.calculCodeSecuriteSociale(nouvelleValeur);
				resultat.setText("Cl� de contr�le : "+code);
				
			} catch(NumberFormatException ex) {
				validite.setForeground(Color.RED);
				validite.setText("Veuillez saisir un nombre constitu� de 13 chiffres !");
			}
			
		}
		
	}

}
