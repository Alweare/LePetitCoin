	package fr.eni.enchere.controllers;

	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PostMapping;
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

	
	 
	 
		 
		 @PostMapping("/connexion")
		 public String verifierConnextion(@RequestParam("pseudo") String pseudo,@RequestParam String motDePasse) {
			System.out.println("entrer dans la verif utilisateur");
			 boolean valide = utilisateurService.verifierPseudoEtMotPasse(pseudo, motDePasse);
			 if(valide) {
				 System.out.println("utilisateur validé");
				 return "redirect:/index";
			 }else {
				 return "connexion";
			 }
			 
		 }
		
	}

