	package fr.eni.enchere.controllers;


import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bll.contexte.ContexteService;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.UtilisateurDAO;

@Controller
@SessionAttributes({"utilisateurSession"})
public class ConnexionController {

	private UtilisateurService utilisateurService;
	private ContexteService contexteService;
	

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
	@ModelAttribute("utilisateurSession")
	public Utilisateur ajouteUtilisateurEnSession() {
		return new Utilisateur();
	}
	@GetMapping("/session")
	public String connexionSession(@ModelAttribute("utilisateurSession") Utilisateur utilisateurEnSession, Principal principal) {
		String pseudo = principal.getName();
		Utilisateur utilisateur = this.contexteService.charger(pseudo);
		if(utilisateur != null) {
			utilisateurEnSession.setNoUtlisateur(utilisateur.getNoUtlisateur());
			utilisateurEnSession.setPseudo(utilisateur.getPseudo());
			utilisateurEnSession.setNom(utilisateur.getNom());
			utilisateurEnSession.setPrenom(utilisateur.getPrenom());
			utilisateurEnSession.setEmail(utilisateur.getEmail());
			utilisateurEnSession.setTelephone(utilisateur.getTelephone());
			utilisateurEnSession.setRue(utilisateur.getRue());
			utilisateurEnSession.setCodePostal(utilisateur.getCodePostal());
			utilisateurEnSession.setVille(utilisateur.getVille());
			utilisateurEnSession.setCredit(utilisateur.getCredit());
			utilisateurEnSession.setAdministrateur(utilisateur.isAdministrateur());
		}else {
			utilisateurEnSession.setNoUtlisateur(0);
			utilisateurEnSession.setPseudo(null);
			utilisateurEnSession.setNom(null);
			utilisateurEnSession.setPrenom(null);
			utilisateurEnSession.setEmail(null);
			utilisateurEnSession.setTelephone(null);
			utilisateurEnSession.setRue(null);
			utilisateurEnSession.setCodePostal(null);
			utilisateurEnSession.setVille(null);
			utilisateurEnSession.setCredit(0);
			utilisateurEnSession.setAdministrateur(false);
			
		}
		return "redirect:/index";
	}
	 
	 

	 
}
