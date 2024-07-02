package fr.eni.enchere.bll.contexte;

import fr.eni.enchere.bo.Utilisateur;

public interface ContexteService {

	Utilisateur charger(String pseudo);
}
