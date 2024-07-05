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

@Service
public class ArticleServiceImpl implements ArticleService {
	private ArticleDAO articleDao;
	private RetraitDAO retraitDao;
	private UtilisateurDAO utilisateurDao;
	
	

	public ArticleServiceImpl(ArticleDAO articleDao, RetraitDAO retraitDao, UtilisateurDAO utilisateurDao) {
		this.articleDao = articleDao;
		this.retraitDao = retraitDao;
		this.utilisateurDao = utilisateurDao;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void CreerArticle(ArticleVendu article, String ville, String rue, String cp) {
		//********************zone de test à supprimer************************************
		//********************************************************************************
		article.setCategorieArticle(new Categorie());
		article.getCategorieArticle().setId(1);
		//*******************************************************************************
		//********************************************************************************
		Retrait retrait = new Retrait(rue, cp, ville);
		article.setLieuretrait(retrait);
		if (article.getLieuretrait().getRue().isEmpty()) {
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

	public List<Categorie> recupererCategorie() {
		return articleDao.trouverCategories();
	}
	
}
