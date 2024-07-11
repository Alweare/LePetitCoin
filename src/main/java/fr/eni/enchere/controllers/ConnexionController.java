package fr.eni.enchere.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class ConnexionController {


	public ConnexionController() {

	}

	@GetMapping("/nouveauMDP")
	public String modifierMDP() {
		return "/nouveauMDP";
	}

	@GetMapping("/index")
	public String acceuil() {		
		return "view-index";
	}

	@GetMapping("/connexion")
	public String connexion() {
		return "connexion";
	}		 


}
