package fr.eni.enchere.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.dal.UtilisateurDAO;

@Controller
public class ConnexionController {
	
	private UtilisateurService utilisateurService;

	 @GetMapping("/connexion")
	    public String connexion() {
	        return "connexion";
	    }
	 
	 @GetMapping()
	 public String verifierConnextion(@RequestParam String pseudo,@RequestParam String motDePasse) {
		 boolean valide = utilisateurService.verifierPseudoEtMotPasse(pseudo, motDePasse);
		 if(valide) {
			 return "redirect:/index";
		 }else {
			 return "connextion";
		 }
	 }
	
	
	 
	 
	 
}
