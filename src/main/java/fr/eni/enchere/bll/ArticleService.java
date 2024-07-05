package fr.eni.enchere.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;

public interface ArticleService {
	void CreerArticle(ArticleVendu article, String ville, String rue, String cp);
	List<ArticleVendu> recupererEnchereEnCours();
	List<ArticleVendu> recupererParParticipant(int idParticipant);
	List<ArticleVendu> recupererEnchereGagnee(int idAcquereur);
	ArticleVendu RecupererArticle(int idArticle);
	Enchere trouverEnchereParID(int id);
	void changerID(int ancienId, int nouveauId);
  List<Categorie> recupererCategories();
	List<Categorie>consulterCategorie();
	List<ArticleVendu> afficherCategorieFiltrer(int idUtilisateur);
	List<ArticleVendu> rechercherArticlesParCategorieEtNom(int id, String recherche);

}
