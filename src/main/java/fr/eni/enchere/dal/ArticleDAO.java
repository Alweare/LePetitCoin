package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
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
	void creer(ArticleVendu enchere);
}
