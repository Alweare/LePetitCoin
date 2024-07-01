package fr.eni.enchere.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.UtilisateurDAO;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
	
	private UtilisateurDAO utilisateurDAO;
	

	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {

		this.utilisateurDAO = utilisateurDAO;
	}

	@Override
	public List<Utilisateur> consulterUtilisateur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur consulterUtilisateurParId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creerUtilisateur(Utilisateur utilisateur) {
		utilisateurDAO.creerUtilisateur(utilisateur);
		
	}

}
