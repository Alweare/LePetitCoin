package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurService {

	List<Utilisateur> consulterUtilisateur();
	
	Utilisateur consulterUtilisateurParId(int id);
//	Utilisateur consulterUtilisateurParPseudo(String pseudo);

	
	boolean verifierPseudoEtMotPasse(String pseudo, String motDePasse);
	
	void creerUtilisateur(Utilisateur utilisateur);

}
