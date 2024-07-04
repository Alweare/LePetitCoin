package fr.eni.enchere.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.exceptions.BusinessException;

@Service
public class ArticleServiceImpl implements ArticleService {
	private ArticleDAO articleDao;
	
	
	
	
	public ArticleServiceImpl(ArticleDAO enchereDao) {
		this.articleDao = enchereDao;
	}

	@Override
	public void CreerArticle(ArticleVendu article) {
		System.out.println(article);
		this.articleDao.creer(article);
		
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
	public ArticleVendu RecupererArticle(int idArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleVendu> recupererCategorie() {
		
		return articleDao.trouverCategories();
	}
	
}
