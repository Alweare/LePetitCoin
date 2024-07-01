package fr.eni.enchere.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.UtilisateurDAO;

@Service
public  class UtilisateurServiceImpl implements UtilisateurService {
	
	private UtilisateurDAO utilisateurDAO;

	@Override
	public List<Utilisateur> consulterUtilisateur() {
		List<Utilisateur> listUtilisateur = utilisateurDAO.trouveTout();
		return listUtilisateur;
	}

	@Override
	public Utilisateur consulterUtilisateurParId(int id) {
		Utilisateur utilisateur = utilisateurDAO.lire(id);
		return utilisateur;
	}

	@Override
	public void creerUtilisateur() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verifierPseudoEtMotPasse(String pseudo, String motDePasse) {
		Utilisateur utilisateur = utilisateurDAO.trouveParPseudo(pseudo);
		return utilisateur !=null && utilisateur.getMotDePasse().equals(motDePasse);
	}

}
