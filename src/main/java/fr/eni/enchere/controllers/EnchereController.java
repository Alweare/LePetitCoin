package fr.eni.enchere.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.enchere.bll.ArticleService;
import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;

@Controller
public class EnchereController {
	
	private ArticleService articleService;
	private UtilisateurService utilisateurService;
	
	
	
	


	public EnchereController(ArticleService articleService, UtilisateurService utilisateurService) {
		super();
		this.articleService = articleService;
		this.utilisateurService = utilisateurService;
	}

	@GetMapping("/listeEnchere")
	public String listeEnchere(Model model, Principal principal) {
		model.addAttribute("listeEncheresEncours", articleService.recupererEnchereEnCours());
		
		return "listeEnchere";
	}
	
	 @GetMapping("/")
	 public String acceuil(Model model, Principal principal) {

		 model.addAttribute("listeEncheres", articleService.recupererEnchereEnCours());
		
		 return "view-index";
	 }

 

	@GetMapping("/creationVente")
	public String creationVente(Model model, Principal principal) {
		ArticleVendu articleVendu = new ArticleVendu();
		model.addAttribute("nouvelleEnchere", articleVendu);
		System.out.println(principal.getName());
		return "creationVente";
	}
	
	

	
	@PostMapping("/creationVente")
	public String creerVente(@ModelAttribute("nouvelleEnchere") ArticleVendu article, Principal principal) {
		article.setVendeur(utilisateurService.trouverUtilisateurParPseudo(principal.getName()));
		article.setCategorieArticle(new Categorie());
		articleService.CreerArticle(article);

		return "listeEnchere";
	}
	
	@GetMapping("/encherir")
	public String afficherEncherir(@RequestParam("idArticle") int idArticle ,Model model) {
		
		return "encherir";
	}
	@PostMapping("/enchere")
	public String Encherir() {
		
		return "encherir";
	}
	
	
	
	@ModelAttribute("creationVente")
	public String ajouteEnchere(Model model) {
		model.addAttribute("ajoutVente", new Enchere());
		return "creationVente";
	}
	
//	@ModelAttribute("enchere")
	
	
	
	
	
	
	
	
	
	
}
