package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurService {

	List<Utilisateur> consulterUtilisateur();
	
	Utilisateur consulterUtilisateurParId(int id);
	
	void creerUtilisateur();
	
	boolean verifierPseudoEtMotPasse(String pseudo, String motDePasse);
	
}
