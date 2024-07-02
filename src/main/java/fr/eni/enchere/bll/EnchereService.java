package fr.eni.enchere.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.Enchere;

public interface EnchereService {
	void CreerEnchere(Enchere enchere);
	List<Enchere> recupererEnchereEnCours();
	List<Enchere> recupererParParticipant(int idParticipant);
	List<Enchere> recupererEnchereGagnee(int idAcquereur);
	Enchere RecupererArticle(int idArticle);
}
