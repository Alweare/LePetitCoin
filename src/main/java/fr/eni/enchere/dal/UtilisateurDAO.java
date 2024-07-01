package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurDAO {

	List<Utilisateur> trouveTout();
	
	Utilisateur lire(long id);
	
	void creerUtilisateur(Utilisateur utilisateur);
	
	Utilisateur trouveParPseudo(String pseudo);
	
	
}
