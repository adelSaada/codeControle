package fr.exercice.codeLuhn;

public class CodeLuhn {

	
	/**
	 *  Cl� de controle qui permet de v�rifier si une carte bancaire
	 *  est valide.
	 * @param chiffreCB (une chaine de caract�re representant 15 chiffres)
	 * @return le code luhn (qui doit �tre conforme avec le 16 eme chiffre de la CB)
	 */
	
	public static int calculCodeLuhn(String chiffreCB) {
		
		double total=0;
		
		for(int i= 0; i<chiffreCB.length()-1; i++) {
			
			/* Doublez les chiffres de rangs impairs 
			 * si le double est sup�rieur ou �gal � 10, faire somme des deux
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
		 * Faire division euclidienne de ce r�sultat par 10 (reste)
		 * puis compl�ment � 10 du reste obtenu.
		 */
		total = 10 - total%10;
		
		return (int) total;
	}
	
	
}
