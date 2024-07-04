package fr.eni.enchere.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;

public interface ArticleService {
	void CreerArticle(ArticleVendu article);
	List<ArticleVendu> recupererEnchereEnCours();
	List<ArticleVendu> recupererParParticipant(int idParticipant);
	List<ArticleVendu> recupererEnchereGagnee(int idAcquereur);
	ArticleVendu RecupererArticle(int idArticle);

}
