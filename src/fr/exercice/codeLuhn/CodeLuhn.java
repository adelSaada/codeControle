package fr.exercice.codeLuhn;

public class CodeLuhn {

	
	/**
	 *  Clé de controle qui permet de vérifier si une carte bancaire
	 *  est valide.
	 * @param chiffreCB (une chaine de caractère representant 15 chiffres)
	 * @return le code luhn (qui doit être conforme avec le 16 eme chiffre de la CB)
	 */
	
	public static int calculCodeLuhn(String chiffreCB) {
		
		double total=0;
		
		for(int i= 0; i<chiffreCB.length()-1; i++) {
			
			/* Doublez les chiffres de rangs impairs 
			 * si le double est supérieur ou égal à 10, faire somme des deux
			 * chiffres obtenus.
			 * 
			 * Faire ensuite la somme des 15 nouveaux nombres obtenus.
			 */
			if((i+1)%2 != 0) {
				total += (Double.parseDouble(chiffreCB.charAt(i)+"")*2)%9 ;				
			}
			else {
				total += Double.parseDouble(chiffreCB.charAt(i)+"");
			}
		}
		
		/*
		 * Faire division euclidienne de ce résultat par 10 (reste)
		 * puis complément à 10 du reste obtenu.
		 */
		total = 10 - total%10;
		
		return (int) total;
	}
	
	
}
