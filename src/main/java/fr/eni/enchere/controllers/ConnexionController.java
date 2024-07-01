package fr.eni.enchere.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConnexionController {

	 @GetMapping("/connexion")
	    public String connexion() {
	        return "connexion";
	    }
	
}
