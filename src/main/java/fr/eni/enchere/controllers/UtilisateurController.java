package fr.eni.enchere.controllers;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
	public String afficherProfil( Model model, Principal principal) {
		Utilisateur utilisateur = utilisateurService.trouverUtilisateurParPseudo(principal.getName());
		model.addAttribute("utilisateur",utilisateur);
		return "monProfil";
	}
	
	@ModelAttribute("utilisateurEnSession")
	public java.util.List<Utilisateur> chargerUtilisateur(){
		return utilisateurService.consulterUtilisateur();
	}

	@PostMapping("/miseAJourProfil")
	public String miseAJourProfil(@ModelAttribute("utilisateur") Utilisateur utilisateur,Model model, Principal principal) {
		
		try {
			
			Utilisateur utilisateurExist = utilisateurService.trouverUtilisateurParPseudo(principal.getName());
			utilisateur.setId(utilisateurExist.getId());
			utilisateurService.mettreAJourUtilisateur(utilisateur);
			model.addAttribute("utilisateur", utilisateur);
			 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		        if (auth != null) {
		        	 SecurityContextHolder.getContext().setAuthentication(null);
		        }
		        return "redirect:/"; // Rediriger vers la page de connexion après déconnexion
		} catch (BusinessException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "monProfil";
		}
		
	}
	///VOIR DEMAIN POUR LA REDIRECTION APRES DECONNEXION 
	@GetMapping("/logout")
	public String deconnexion() {
		return "/view-index";
	}
	
	@PostMapping("/supprimerCompte")
	public String supprimerCompte(Principal principal) {
		Utilisateur utilisateur = utilisateurService.trouverUtilisateurParPseudo(principal.getName());
		utilisateurService.supprimerUtilisateur(utilisateur.getId());
		return "/view-index";
	}
	
	
	

}
