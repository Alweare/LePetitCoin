package fr.eni.enchere.controllers;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.enchere.bll.ArticleService;
import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bll.UtilisateurServiceImpl;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.exceptions.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertFalse.List;

@Controller
@SessionAttributes({"utilisateurSession"})
public class UtilisateurController {

	
	private UtilisateurService utilisateurService;
	private ArticleService articleService;

	public UtilisateurController(UtilisateurService utilisateurService, ArticleService articleService) {
		this.utilisateurService = utilisateurService;
		this.articleService = articleService;
	}

	@GetMapping("/creationProfil")
	public String creationProfil(Model model) {
		Utilisateur newUtilisateur = new Utilisateur();
		model.addAttribute("newUtilisateur", newUtilisateur);
		return "creationProfil";
	}

	@PostMapping("/creationProfil")
	public String inscriptionUtilisateur (@Valid  @ModelAttribute("newUtilisateur") Utilisateur utilisateur, BindingResult bindingResult)throws BusinessException {
		if(bindingResult.hasErrors()) {
			return "creationProfil";
		}
		try {
			this.utilisateurService.creerUtilisateur(utilisateur);
			return "redirect:/";
		}
		catch (BusinessException e) {
			e.getErreurs().forEach(err -> {
				ObjectError error = new ObjectError("globalError", err);
				bindingResult.addError(error);
			});
			return "creationProfil";
			}
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
	public String miseAJourProfil(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult,Model model, Principal principal) throws BusinessException {
		if(bindingResult.hasErrors()) {
			return "monProfil";
		}
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
			e.getErreurs().forEach(err -> {
				ObjectError error = new ObjectError("globalError", err);
				bindingResult.addError(error);	
			});
			return "monProfil";
		}		
	}

	
	@PostMapping("/supprimerCompte")
	public String supprimerCompte(Principal principal) {
		Utilisateur utilisateur = utilisateurService.trouverUtilisateurParPseudo(principal.getName());
		int idUtilisateur = utilisateur.getId();
		Enchere enchere = articleService.trouverEnchereParID(idUtilisateur);		
		if(enchere == null) {
			utilisateurService.supprimerUtilisateur(idUtilisateur);
		}else {
			articleService.changerID(idUtilisateur,100);
			utilisateurService.supprimerUtilisateur(idUtilisateur);
		}		
		return "/view-index";
	}
	
	

}

