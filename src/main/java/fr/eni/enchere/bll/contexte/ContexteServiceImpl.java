package fr.eni.enchere.bll.contexte;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.UtilisateurDAO;

@Service
@Primary
public class ContexteServiceImpl implements ContexteService {

	private UtilisateurDAO utilisateurDAO;
	
	
	
	
	public ContexteServiceImpl(UtilisateurDAO utilisateurDAO) {
		this.utilisateurDAO = utilisateurDAO;
	}




	@Override
	public Utilisateur charger(String pseudo) {
		return this.utilisateurDAO.trouveParPseudo(pseudo);
	}

}
