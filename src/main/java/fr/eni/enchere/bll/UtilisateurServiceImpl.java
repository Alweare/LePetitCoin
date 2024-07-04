package fr.eni.enchere.bll;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.dao.DataAccessException;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.UtilisateurDAO;
import fr.eni.enchere.exceptions.BusinessException;

@Service

public class UtilisateurServiceImpl implements UtilisateurService {
	
	private UtilisateurDAO utilisateurDAO;
	private PasswordEncoder passwordEncoder;

	

	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO, PasswordEncoder passwordEncoder) {
		super();
		this.utilisateurDAO = utilisateurDAO;
		this.passwordEncoder = passwordEncoder;
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
	public void creerUtilisateur(Utilisateur utilisateur) throws BusinessException {
		BusinessException be = new BusinessException();
		boolean estValid = verifPseudo(utilisateur, be);
		estValid &= verifEmail(utilisateur, be);
		
		if(estValid) {
			try {
				String motDePasseCrypte = passwordEncoder.encode(utilisateur.getMotDePasse());
				System.out.println(motDePasseCrypte);
				utilisateur.setMotDePasse(motDePasseCrypte);
				utilisateur.setCredit(200);
				
				utilisateurDAO.creerUtilisateur(utilisateur);
				
			} catch (DataAccessException e) {
				be.add("Problème avec la connexion base");
				
			
				
			}
		}else {
			be.getErreurs().forEach(System.out::println);
			throw be;
		}
	

		
	}

	private boolean verifPseudo(Utilisateur utilisateur, BusinessException be) {

		boolean estValid = false;
		String pseudoRegex = "^[a-zA-Z0-9]+$"; // caractère alphanumérique seulement
		Pattern pseudoPattern = Pattern.compile(pseudoRegex);
		// non nullité
		if(utilisateur != null) {
			// test pseudo pas en base
			if(!utilisateurDAO.trouveTout().stream().anyMatch(pseudo -> pseudo.getPseudo().equals(utilisateur.getPseudo()))){

				if(pseudoPattern.matcher(utilisateur.getPseudo()).matches()) {
					estValid = true;
					
					
				}else {
					be.add("Caractère interdis dans le pseudo");
				}
				
				
			}else {
				be.add("pseudo existant merci d'en saisir un autre");
			}
		}else {
			be.add("problème liaison bdd utilisateur");
		}
		
		return estValid;
		
	}
	private boolean verifEmail(Utilisateur utilisateur, BusinessException be) {
		boolean estValid = false;
		if(!utilisateurDAO.trouveTout().stream().anyMatch(utilisateurExistant -> utilisateurExistant.getEmail().equals(utilisateur.getEmail()))) {
			
			estValid = true;
		}else {
			be.add("l'email existe déjà");
		}
		
		
		
		return estValid;
		
	}


	@Override
	public void mettreAJourUtilisateur(Utilisateur utilisateur)  throws BusinessException{
		BusinessException be = new BusinessException();
		try {
		utilisateurDAO.miseAjourUtilisateur(utilisateur);
		}catch (DataAccessException e) {
			be.add("Un problème est survenu lors de la connexion à la base de données");
			throw be;
		}
	}


	@Override
	public Utilisateur trouverUtilisateurParPseudo(String pseudo) {
		return utilisateurDAO.trouveParPseudo(pseudo);
	}





}
