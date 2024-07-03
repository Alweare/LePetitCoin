package fr.eni.enchere.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.eni.enchere.bll.EnchereService;

@Controller
public class EnchereController {
	
	private EnchereService enchereService;
	
	
	
	public EnchereController(EnchereService enchereService) {
		this.enchereService = enchereService;
	}


	@GetMapping("/listeEnchere")
	public String listeEnchere(Model model) {
		model.addAttribute("listeEncheres", enchereService.recupererEnchereEnCours());
		return "listeEnchere";
	}
	

	@GetMapping("/creationVente")
	public String creationVente() {
		return "creationVente";
	}
	
	
	
	
	
	
	
	
	
	
	
}
