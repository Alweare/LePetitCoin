package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.exceptions.BusinessException;

public interface UtilisateurService {

	List<Utilisateur> consulterUtilisateur();
	
	Utilisateur consulterUtilisateurParId(int id);

	
	void creerUtilisateur(Utilisateur utilisateur) throws BusinessException;

}
