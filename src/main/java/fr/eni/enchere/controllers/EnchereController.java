package fr.eni.enchere.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.enchere.bll.EnchereService;
import fr.eni.enchere.bll.EnchereServiceImpl;
import fr.eni.enchere.bo.Enchere;

@Controller
public class EnchereController {
	
	private EnchereServiceImpl enchereServiceImpl;
	
	
	


	public EnchereController(EnchereServiceImpl enchereServiceImpl) {
		super();
		this.enchereServiceImpl = enchereServiceImpl;
	}

	@GetMapping("/listeEnchere")
	public String listeEnchere(Model model) {
		model.addAttribute("listeEncheres", enchereServiceImpl.recupererEnchereEnCours());
		return "listeEnchere";
	}
	
	 @GetMapping("/")
	 public String acceuil(Model model) {
//		 model.addAttribute("listeEncheres", enchereService.recupererEnchereEnCours());
//		 System.out.println(model.getAttribute("listeEnchere"));
		 return "view-index";
	 }

 

	@GetMapping("/creationVente")
	public String creationVente() {
		return "creationVente";
	}
	
	@PostMapping("/creationVente")
	public String creerVente(Enchere enchere) {
		this.enchereServiceImpl.CreerEnchere(enchere);
		
		
		return "listeEnchere";
	}
	
	@ModelAttribute("creationVente")
	public String ajouteEnchere(Model model) {
		model.addAttribute("ajoutVente", new Enchere());
		return "creationVente";
	}
	
	
	
	
	
	
	
}
