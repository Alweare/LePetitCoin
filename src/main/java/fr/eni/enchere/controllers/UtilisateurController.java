package fr.eni.enchere.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import fr.eni.enchere.bll.UtilisateurServiceImpl;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.exceptions.BusinessException;

@Controller
public class UtilisateurController {
	
	private UtilisateurServiceImpl utilisateurImpl;

	public UtilisateurController(UtilisateurServiceImpl utilisateurImpl) {

		this.utilisateurImpl = utilisateurImpl;
	}
	
	public String inscriptionUtilisateur (Utilisateur utilisateur)throws BusinessException {
		BusinessException be = new BusinessException();
		Utilisateur nouveauUtilisateur = new Utilisateur();
		
		if(nouveauUtilisateur != null) {
			this.utilisateurImpl.creerUtilisateur(nouveauUtilisateur);
			be.add("Veuillez saisir tout les champs");
		}
		
		
		return "view-index";
		
	}
	
	@GetMapping("/creationProfil")
	public String creationProfil() {
		return "creationProfil";
	}
	
	@GetMapping("/monProfil")
	public String monProfil() {
		return "monProfil";
	}
	

}
