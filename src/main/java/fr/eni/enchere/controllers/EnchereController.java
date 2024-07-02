package fr.eni.enchere.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnchereController {
	
	
	
	@GetMapping("/listeEnchere")
	public String listeEnchere() {
		return "listeEnchere";
	}
	

	@GetMapping("/creationVente")
	public String creationVente() {
		return "creationVente";
	}
}
