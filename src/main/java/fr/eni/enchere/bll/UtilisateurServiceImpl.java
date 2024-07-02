package fr.eni.enchere.bll;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.UtilisateurDAO;
import fr.eni.enchere.exceptions.BusinessException;

@Service

public class UtilisateurServiceImpl implements UtilisateurService {
	
	private UtilisateurDAO utilisateurDAO;
	private PasswordEncoder passwordEncoder;

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
		BusinessException be = new BusinessException();
		boolean estValid = verifPseudo(null, be);
		String motDePasseCrypte = passwordEncoder.encode(utilisateur.getMotDePasse());
		System.out.println(motDePasseCrypte);
		utilisateur.setMotDePasse(motDePasseCrypte);
		
		utilisateurDAO.creerUtilisateur(utilisateur);
		
	}

	@Override
	public boolean verifierPseudoEtMotPasse(String pseudo, String motDePasse) {
		Utilisateur utilisateur = utilisateurDAO.trouveParPseudo(pseudo);
		return utilisateur !=null && utilisateur.getMotDePasse().equals(motDePasse);
	}
	
	public boolean verifPseudo(Utilisateur utilisateur, BusinessException be) {
		boolean estValid = false;
		String pseudoRegex = "^[a-zA-Z0-9]+$"; // caractère alphanumérique seulement
		Pattern pseudoPattern = Pattern.compile(pseudoRegex);
		
		// non nullité
		if(utilisateur != null) {
			Utilisateur utilisateurRecu = utilisateurDAO.trouveParPseudo(utilisateur.getPseudo());
			// test pseudo pas en base
			if(utilisateurRecu.getPseudo() == null){
				if(pseudoPattern.matcher(utilisateurRecu.getPseudo()).matches()) {
					estValid = true;
					
					
				}else {
					be.add("Caractère interdis dans le pseudo");
				}
				
				
			}else {
				be.add("il vous faut un pseudo");
			}
		}else {
			be.add("problème liaison bdd utilisateur");
		}
		
		return estValid;
		
	}





}
