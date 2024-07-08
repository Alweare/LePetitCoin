package fr.eni.enchere.bll;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.dal.RetraitDAO;
import fr.eni.enchere.dal.UtilisateurDAO;
import fr.eni.enchere.exceptions.BusinessException;

@Service
public class ArticleServiceImpl implements ArticleService {
	private ArticleDAO articleDao;
	private UtilisateurDAO utilisateurDao;
	private RetraitDAO retraitDao;
	
	
	
	



	public ArticleServiceImpl(ArticleDAO articleDao, UtilisateurDAO utilisateurDao, RetraitDAO retraitDao) {
		this.articleDao = articleDao;
		this.utilisateurDao = utilisateurDao;
		this.retraitDao = retraitDao;
	}

	@Override

	@Transactional(rollbackFor = Exception.class)
	public void CreerArticle(ArticleVendu article, String ville, String rue, String cp) {
		Retrait retrait = new Retrait(rue, cp, ville);
		article.setLieuretrait(retrait);
		
		if (article.getLieuretrait().getRue().isEmpty()) {
			System.err.println(this.utilisateurDao.trouveAdressParId(article.getVendeur().getId()));
			article.setLieuRetrait(this.utilisateurDao.trouveAdressParId(article.getVendeur().getId()));
		}
		int idArticle = this.articleDao.creer(article);
		this.retraitDao.creer(article.getLieuRetrait(), idArticle);
	}

	@Override
	public List<ArticleVendu> recupererEnchereEnCours() {
		return this.articleDao.touveToutEnCours();
	}

	@Override
	public List<ArticleVendu> recupererParParticipant(int idParticipant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleVendu> recupererEnchereGagnee(int idAcquereur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere RecupererArticle(int idArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override

	public Enchere trouverEnchereParID(int id) {
		Enchere article =  articleDao.trouverEnchereParID(id);
		return article;
	}

	@Override
	public void changerID(int ancienId, int nouveauId) {
		articleDao.changerIdDansEnchere(ancienId, nouveauId);
	}
	
	@Override
	public List<Categorie> recupererCategories() {
		
		return articleDao.trouverCategories();

	}

	@Override
	public List<Categorie> consulterCategorie() {
		return articleDao.chercheTout();
	}

	@Override
	public List<ArticleVendu> afficherCategorieFiltrer(int idUtilisateur) {
		return articleDao.filtrerArticle(idUtilisateur);
	}

	@Override
	public Categorie consulterCategorieParId(int idCategorie) {
		return this.articleDao.trouveCategorieParIdint(idCategorie);
	}
  
	@Override
		public List<ArticleVendu> rechercherArticlesParCategorieEtNom(int id, String recherche) {
			return articleDao.rechercherArticlesParCategorieEtNom(id, recherche);
		}
	
	@Override
	public ArticleVendu RecupererArticleParId(int id) {
		ArticleVendu article =  articleDao.lire(id);
		return article;
	}

@Override
public List<ArticleVendu> recupereMesEncheresEnCours(int id) {
	return articleDao.trouveMesEncheresEnCours(id);
}

@Override
public List<ArticleVendu> recupereMesEncheresRemporter(int id) {
	return articleDao.trouveMesEncheresRemporter(id);
}

@Override
public List<ArticleVendu> recupereMesVentesEnCours(int id) {
	return articleDao.trouveMesVentesEnCour(id);
}

@Override
public List<ArticleVendu> recupereMesVentesNonDebuter(int id) {
	return articleDao.trouveMesVentesNonDebutées(id);
}

@Override
public List<ArticleVendu> recupereMesVentesTerminee(int id) {
	return articleDao.trouveMesVentesTerminer(id);
}



}
