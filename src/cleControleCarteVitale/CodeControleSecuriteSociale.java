package cleControleCarteVitale;

public class CodeControleSecuriteSociale {

	
	/**
	 * Retourne un nombre(type : long) representant le code controle du code INSEE (les deux derniers chiffres).
	 * @param numeroInsee (sans les deux derniers chiffres, il faut donc 13 caractères)
	 * @return la valeur du code Controle ou -1 si la chaine ne comporte pas 11 chiffres.
	 */
	
	public static long calculCodeSecuriteSociale(String numeroInsee) {
		
		long resultat = -1;
		if(numeroInsee.length() == 13) {
			resultat = Long.parseLong(numeroInsee);
			resultat = resultat % 97;
			resultat =  97 - resultat ;			
		}
		return resultat;
	}
	
	
	public static void main(String[] args) {
		String numero = "1540250025005"; // il faut saisir les 13 premières chiffres
		System.out.println(calculCodeSecuriteSociale(numero));
	}
	
}
