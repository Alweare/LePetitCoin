package fr.eni.enchere.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bll.UtilisateurServiceImpl;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.exceptions.BusinessException;
import jakarta.validation.constraints.AssertFalse.List;

@Controller
@SessionAttributes({"utilisateurSession"})
public class UtilisateurController {
	
	private UtilisateurService utilisateurService;

	public UtilisateurController(UtilisateurService utilisateurService) {

		this.utilisateurService = utilisateurService;
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
			this.utilisateurService.creerUtilisateur(utilisateur);
			be.add("Veuillez saisir tout les champs");
		}
		
		
		return "view-index";
		
	}
	@GetMapping("/monProfil")
	public String afficherProfil(@ModelAttribute("utilisateurEnSession")Utilisateur utilisateurSession, Model model) {
		Utilisateur utilisateur = this.utilisateurService.consulterUtilisateurParId(utilisateurSession.getId());
		System.err.println("Je suis la 1");
		System.out.println(utilisateurSession);
		model.addAttribute("utilisateur", utilisateur);
		return "monProfil";
	}
	
	@ModelAttribute("utilisateurEnSession")
	public java.util.List<Utilisateur> chargerUtilisateur(){
		return utilisateurService.consulterUtilisateur();
	}

	@PostMapping("/miseAJourProfil")
	public String miseAJourProfil(@ModelAttribute("utilisateur") Utilisateur utilisateur, Model model) {
		try {
			utilisateurService.mettreAJourUtilisateur(utilisateur);
			model.addAttribute("utilisateur", utilisateur);
			return "redirect:/";
		} catch (BusinessException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "monProfil";
		}
		
	}
	
	
	
	
	
	

}
