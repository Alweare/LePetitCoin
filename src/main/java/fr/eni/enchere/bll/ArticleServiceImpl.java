package fr.eni.enchere.bll;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.dal.RetraitDAO;

@Service
public class ArticleServiceImpl implements ArticleService {
	private ArticleDAO articleDao;
	private RetraitDAO retraitDao;
	
	
	public ArticleServiceImpl(ArticleDAO enchereDao) {
		this.articleDao = enchereDao;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void CreerArticle(ArticleVendu article) {
		System.out.println(article);
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
	public ArticleVendu RecupererArticle(int idArticle) {
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
	public List<ArticleVendu> recupererCategorie() {
		
		return articleDao.trouverCategories();

	}
	
}
