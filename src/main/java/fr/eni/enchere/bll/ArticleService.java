package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.exceptions.BusinessException;

public interface ArticleService {
	void CreerArticle(ArticleVendu article, String ville, String rue, String cp) throws BusinessException;
	List<ArticleVendu> recupererEnchereEnCours();
	List<ArticleVendu> recupererParParticipant(int idParticipant);
	List<ArticleVendu> recupererEnchereGagnee(int idAcquereur);
	Enchere RecupererArticle(int idArticle);
	Enchere trouverEnchereParID(int id);
	void changerID(int ancienId, int nouveauId);
	List<Categorie> recupererCategories();
	List<Categorie>consulterCategorie();
	Categorie consulterCategorieParId(int idCategorie);
	List<ArticleVendu> afficherCategorieFiltrer(int idUtilisateur);
	List<ArticleVendu> rechercherArticlesParCategorieEtNom(int id, String recherche);
	ArticleVendu RecupererArticleParId (int id);
	List<ArticleVendu> recupereMesEncheresEnCours(int id);
	List<ArticleVendu> recupereMesEncheresRemporter(int id);
	List<ArticleVendu> recupereMesVentesEnCours(int id);
	List<ArticleVendu> recupereMesVentesNonDebuter(int id);
	List<ArticleVendu> recupereMesVentesTerminee(int id);
	void encherir(int idUtilisateur, int idArticle, int montantEnchere) throws BusinessException ;


}
