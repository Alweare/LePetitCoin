package fr.eni.enchere.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.enchere.bll.UtilisateurServiceImpl;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.exceptions.BusinessException;

@Controller
public class UtilisateurController {
	
	private UtilisateurServiceImpl utilisateurImpl;

	
	
	public UtilisateurController(UtilisateurServiceImpl utilisateurImpl) {

		this.utilisateurImpl = utilisateurImpl;
	}
	
	@GetMapping("/creationProfil")
	public String creationProfil(Model model) {
		Utilisateur newUtilisateur = new Utilisateur();
		model.addAttribute("newUtilisateur", newUtilisateur);
		
		return "creationProfil";
	}
	
	@PostMapping("/creationProfil")
	public String inscriptionUtilisateur (Utilisateur utilisateur)throws BusinessException {
		BusinessException be = new BusinessException();
		Utilisateur nouveauUtilisateur = new Utilisateur();
		
		if(utilisateur != null) {
			this.utilisateurImpl.creerUtilisateur(utilisateur);
			be.add("Veuillez saisir tout les champs");
		}else {
			throw be;
		}
		
		
		return "view-index";
		
	}
	
	
	@GetMapping("/monProfil")
	public String monProfil() {
		return "monProfil";
	}
	
	
	
	
	
	

}
