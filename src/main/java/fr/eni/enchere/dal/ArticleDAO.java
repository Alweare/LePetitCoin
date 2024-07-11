package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;


public interface ArticleDAO {
	List<ArticleVendu>  trouveTout();
	List<ArticleVendu> touveToutEnCours();
	List<ArticleVendu> trouveMesEncheresEnCours(int id);
	List<ArticleVendu> trouveMesEncheresRemporter(int id);
	List<ArticleVendu> trouveMesVentesEnCour(int id);
	List<ArticleVendu> trouveMesVentesNonDebutées(int id);
	List<ArticleVendu> trouveMesVentesTerminer(int id);
	ArticleVendu lire(int id);	
	int creer(ArticleVendu enchere);
	Enchere trouverEnchereParID(int id);
	List<Categorie> trouverCategories();
	List<Categorie> chercheTout();
	List<ArticleVendu> filtrerArticle(int idUtilisateur);
	Categorie trouveCategorieParIdint(int id);
	List<ArticleVendu> rechercherArticlesParCategorieEtNom(int id,String recherche);
	
	
	void creerEnchere(int idUtilisateur,int idArticle, int montantEnchere);
	int nbEnchereArticle(int idArticle);
	ArticleVendu enchereArticle (int idArticle); 
	void save(int id, ArticleVendu article);


}
