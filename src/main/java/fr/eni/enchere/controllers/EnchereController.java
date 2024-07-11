package fr.eni.enchere.controllers;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.web.multipart.MultipartFile;




import fr.eni.enchere.bll.ArticleService;
import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

import fr.eni.enchere.exceptions.BusinessException;
import jakarta.validation.Valid;


@Controller
@SessionAttributes({"categoriesSession"})
public class EnchereController {
	@Autowired
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
	public String acceuil(Model model) {
		model.addAttribute("listeEncheres", articleService.recupererEnchereEnCours());
		return "view-index";
	}


	@GetMapping("/creationVente")
	public String creationVente(Model model, Principal principal) {
		ArticleVendu articleVendu = new ArticleVendu();
		articleVendu.setLieuRetrait(this.utilisateurService.ConsulterAdressePArId(this.utilisateurService.trouverUtilisateurParPseudo(principal.getName()).getId()));
		model.addAttribute("nouvelleEnchere", articleVendu);
		return "creationVente";
	}

	@PostMapping("/creationVente")
	public String creerVente(
			@Valid @ModelAttribute("nouvelleEnchere") ArticleVendu article, 
			BindingResult bindingResult,
			Principal principal
			) {		
		if(bindingResult.hasErrors()) {
			return "creationVente";
		}
		article.setVendeur(utilisateurService.trouverUtilisateurParPseudo(principal.getName()));
		try {
			articleService.CreerArticle(article);
		} catch (BusinessException e) {
			e.getErreurs().forEach(err -> {				
				ObjectError error = new ObjectError("globalError", err);
				bindingResult.addError(error);
			});
			return "creationVente";
			}
		return "listeEnchere";




	}

	@PostMapping("/enchere")
	public String Encherir(@RequestParam("id") int id, @RequestParam("proposition") int proposition, Principal principal, Model model) {
		Utilisateur utilisateur = utilisateurService.trouverUtilisateurParPseudo(principal.getName());
		ArticleVendu article = articleService.RecupererArticleParId(id);
		
		try {
			articleService.encherir(utilisateur.getId(), id, proposition);
	
		} catch (BusinessException e) {
		
			e.printStackTrace();
		}
		String currentUsername = principal != null ? principal.getName() : null;
        boolean isVendeur = false;
        if (currentUsername != null) {
            Utilisateur utilisateur1 = utilisateurService.trouverUtilisateurParPseudo(currentUsername);
            isVendeur = article.getVendeur().getId() == utilisateur1.getId();

        }
        
        model.addAttribute("a", article);
        model.addAttribute("isVendeur", isVendeur);
		 

		return "encherir";
	}

	@ModelAttribute("creationVente")
	public String ajouteEnchere(Model model) {
		model.addAttribute("ajoutVente", new Enchere());
		return "creationVente";
	}


	@ModelAttribute("categoriesSession")
	public List<Categorie> chargerCategorieSession(){
		return articleService.consulterCategorie();
	}

	@PostMapping("/categories")
	public String afficherCategorieFiltrer(@RequestParam("categories") int id,
										@RequestParam(name="recherche",required= false)String recherche,
										@RequestParam(name="choixAchat", required = false) String choixAchat,
										@RequestParam(name="encheresOuvertes", required =false)String encheresOuvertes,
										@RequestParam(name="encheresEnCours", required=false)String encheresEnCours,
										@RequestParam(name="encheresRemportees",required=false)String encheresRemportees,
										@RequestParam(name="choixVente",required=false)String choixVente,
										@RequestParam(name="ventesEnCours", required=false)String ventesEnCours,
										@RequestParam(name="ventesNonDebutees",required=false)String ventesNonDebutees,
										@RequestParam(name="ventesTerminees", required=false)String ventesTerminees,
										Principal principal,
										Model model){		
		String pseudo = principal.getName();
		Utilisateur utilisateur = utilisateurService.trouverUtilisateurParPseudo(pseudo);
		int idUtilisateur = utilisateur.getId();		
		if(id != 0) {
			if(recherche !=null && !recherche.isEmpty()) {
				model.addAttribute("listeEncheres", articleService.rechercherArticlesParCategorieEtNom(id, recherche));
			}else {
				model.addAttribute("listeEncheres", articleService.afficherCategorieFiltrer(id));
				if(encheresOuvertes !=null) {
					model.addAttribute("listeEncheres", articleService.recupererEnchereEnCours());
				}
				if(encheresEnCours !=null) {
					model.addAttribute("listeEncheres", articleService.recupereMesEncheresEnCours(idUtilisateur));
				}
				if(encheresRemportees != null) {
					model.addAttribute("listeEncheres", articleService.recupereMesEncheresRemporter(idUtilisateur));
				}
				if(ventesEnCours !=null) {
					model.addAttribute("listeEncheres", articleService.recupereMesVentesEnCours(idUtilisateur));
				}
				if(ventesNonDebutees != null) {
					model.addAttribute("listeEncheres", articleService.recupereMesVentesNonDebuter(idUtilisateur));
				}
				if(ventesTerminees != null) {
					model.addAttribute("listeEncheres", articleService.recupereMesVentesTerminee(idUtilisateur));
				}				
			}
		}else {
			if(encheresOuvertes !=null) {
				model.addAttribute("listeEncheres", articleService.recupererEnchereEnCours());
			}
			if(encheresEnCours !=null) {
				model.addAttribute("listeEncheres", articleService.recupereMesEncheresEnCours(idUtilisateur));
			}
			if(encheresRemportees != null) {
				model.addAttribute("listeEncheres", articleService.recupereMesEncheresRemporter(idUtilisateur));
			}
			if(ventesEnCours !=null) {
				model.addAttribute("listeEncheres", articleService.recupereMesVentesEnCours(idUtilisateur));
			}
			if(ventesNonDebutees != null) {
				model.addAttribute("listeEncheres", articleService.recupereMesVentesNonDebuter(idUtilisateur));
			}
			if(ventesTerminees != null) {
				model.addAttribute("listeEncheres", articleService.recupereMesVentesTerminee(idUtilisateur));
			}
		}
		return "view-index";
	}

	 @GetMapping("/encherir")
	    public String verifierVendeur(@RequestParam("idArticle") int id, Model model, Principal principal) {
	        ArticleVendu article = articleService.RecupererArticleParId(id);
	        String currentUsername = principal != null ? principal.getName() : null;
	        boolean isVendeur = false;

	        if (currentUsername != null) {
	            Utilisateur utilisateur = utilisateurService.trouverUtilisateurParPseudo(currentUsername);
	            isVendeur = article.getVendeur().getId() == utilisateur.getId();
	        }	        
	        model.addAttribute("a", article);
	        model.addAttribute("isVendeur", isVendeur);

	        System.out.println("username LAAAAAAA : "+article);
	        if (currentUsername != null) {
	            Utilisateur utilisateur = utilisateurService.trouverUtilisateurParPseudo(currentUsername);
	            isVendeur = article.getVendeur().getId() == utilisateur.getId();

	        }
	        LocalDateTime currentDate = LocalDateTime.now();
	        
	        model.addAttribute("a", article);
	        model.addAttribute("isVendeur", isVendeur);
	        model.addAttribute("currentDate", currentDate);


	        return "encherir";
	    }
	 @PostMapping("/encherir/modifier")
	 public String modifierArticle(
			 @RequestParam("idArticle") int id,
			 @RequestParam("monArticle") String monArticle,
			 @RequestParam("description") String description,
			 @RequestParam("prixVente") int prixVente,
			 @RequestParam("dateFinEncheres") LocalDateTime dateFinEncheres,
			  Principal principal ) {
		 
		 ArticleVendu article = articleService.RecupererArticleParId(id);
		 String utilisateurActuel = principal != null ? principal.getName() : null;
		 
		 
		 if(utilisateurActuel != null) {
			 Utilisateur utilisateur = utilisateurService.trouverUtilisateurParPseudo(utilisateurActuel);
			 if(article.getVendeur().getId() == utilisateur.getId()) {
				 article.setNomArticle(monArticle);
				 article.setDescription(description);
				 article.setPrixVente(prixVente);
				 article.setDateFinEncheres(dateFinEncheres);
				 
				
				 
				 articleService.sauvegarderArticle(id,article); // faire la methode sauvegarderArticle
				
			 }
		 }
		 return "redirect:/encherir?idArticle=" + id; 
 	 }
}
