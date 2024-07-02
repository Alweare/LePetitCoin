package fr.eni.enchere.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.UtilisateurDAO;
import fr.eni.enchere.exceptions.BusinessException;

@Service

public class UtilisateurServiceImpl implements UtilisateurService {
	
	private UtilisateurDAO utilisateurDAO;
	

	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {

		this.utilisateurDAO = utilisateurDAO;
	}


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
	public void creerUtilisateur(Utilisateur utilisateur) {
		utilisateurDAO.creerUtilisateur(utilisateur);
		
	}

	@Override
	public boolean verifierPseudoEtMotPasse(String pseudo, String motDePasse) {
		Utilisateur utilisateur = utilisateurDAO.trouveParPseudo(pseudo);
		return utilisateur !=null && utilisateur.getMotDePasse().equals(motDePasse);
	}
	
	public boolean checkPseudo(Utilisateur utilisateur, BusinessException be) {
		boolean isValid = false;
		Utilisateur testUtilisateur = utilisateurDAO.trouveParPseudo(utilisateur.getPseudo());
		
//		if(testUtilisateur.getPseudo()) {
//			
//		}
		
		
		

		
		return isValid;
		
	}





}
