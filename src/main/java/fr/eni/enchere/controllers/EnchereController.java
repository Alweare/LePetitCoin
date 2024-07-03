package fr.eni.enchere.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.enchere.bll.EnchereService;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.exceptions.BusinessException;

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
	
	 @GetMapping("/")
	 public String acceuil(Model model) {
		 model.addAttribute("listeEncheres", enchereService.recupererEnchereEnCours());
		 System.out.println(model.getAttribute("listeEnchere"));
		 return "view-index";
	 }

 

	@GetMapping("/creationVente")
	public String creationVente(Model model) {
		model.addAttribute("nouvelleEnchere");
		return "creationVente";
	}
	
	@PostMapping("/creationVente")
	public String creerVente(Enchere enchere) {
		BusinessException be = new BusinessException();
		Enchere nouvelleEnchere = new Enchere(null, 0);
		this.enchereService.CreerEnchere(enchere);
		
		
		return "listeEnchere";
	}
	
	
	
	
	
	
	
	
}
