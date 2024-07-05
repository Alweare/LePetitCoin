package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;


public interface ArticleDAO {
	List<ArticleVendu>  trouveTout();
	List<ArticleVendu> touveToutEnCours();
	List<ArticleVendu>  trouveEnCoursParCategorie(int idCat);
	List<ArticleVendu>  trouveEnchereGagneeParUtilisateur(int id);
	ArticleVendu lire(int id);
	Utilisateur trouverAcquereurParProduit(int id);
	List<ArticleVendu>  trouverParVendeur(int id);
	List<ArticleVendu>  trouveParEncherisseur(int id);
	int creer(ArticleVendu enchere);
	Enchere trouverEnchereParID(int id);
	void changerIdDansEnchere(int ancienId, int nouveauId);
	List<Categorie> trouverCategories();
	List<Categorie> chercheTout();
	List<ArticleVendu> filtrerArticle(int idUtilisateur);
	Categorie trouveCategorieParIdint(int id);
	List<ArticleVendu> rechercherArticlesParCategorieEtNom(int id,String recherche);


}
