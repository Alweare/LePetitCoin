package fr.eni.enchere.bll;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;
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
	public void CreerArticle(ArticleVendu article) throws BusinessException {
		BusinessException be = new BusinessException();
		
		if (article.getDateFinEncheres().isBefore(article.getDateDebutEnchere().plusHours(24))) {
			be.add("La date de fin doit être postérieur de 24h à la date de début de l'enchère");
			throw be;
		}
		
		int idArticle = this.articleDao.creer(article); //TODO gérer cas creer return 0
		this.retraitDao.creer(article.getLieuRetrait(), idArticle);
	}

	@Override
	public List<ArticleVendu> recupererEnchereEnCours() {
		return this.articleDao.touveToutEnCours();
	}

	@Override
	public Enchere trouverEnchereParID(int id) { 
		return articleDao.trouverEnchereParID(id);
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

		return  articleDao.lire(id);
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
	
	@Override
	public void encherir(int idUtilisateur, int idArticle, int montantEnchere) throws BusinessException {
		BusinessException be = new BusinessException();
		
			if(!utilisateurExiste(idUtilisateur, be)) {
				throw be;
			}
			if(enchereExistepas(idArticle) && creditSuffisant(idUtilisateur, montantEnchere, be)) {
				
				int nouveauCreditAcheteur = utilisateurDao.lire(idUtilisateur).getCredit() - montantEnchere;
				utilisateurDao.modifierCreditParId(idUtilisateur, nouveauCreditAcheteur);
				
				int nouveauCreditVendeur = articleDao.lire(idArticle).getVendeur().getCredit() + montantEnchere;
				utilisateurDao.modifierCreditParId(articleDao.lire(idArticle).getVendeur().getId(), nouveauCreditVendeur);
				
				articleDao.creerEnchere(utilisateurDao.lire(idUtilisateur).getId(), articleDao.lire(idArticle).getId(), montantEnchere);
			}
			
			if(!enchereExistepas(idArticle) && articleDao.enchereArticle(idArticle)!=null) {
				ArticleVendu enchere = articleDao.enchereArticle(idArticle);
				
				if(enchere.getPrixVente() < montantEnchere && enchere.getDateFinEncheres().isAfter(LocalDateTime.now()) 
						&& creditSuffisant(idUtilisateur, montantEnchere, be)) {
					
					Utilisateur ancienAcheteur = utilisateurDao.lire(enchere.getAcheteur().getId());
					int nouveauCreditEncherisseur = ancienAcheteur.getCredit() + enchere.getPrixVente();
					utilisateurDao.modifierCreditParId(ancienAcheteur.getId(), nouveauCreditEncherisseur);

					articleDao.creerEnchere(idUtilisateur, idArticle, montantEnchere);			
					int nouveauCreditAcheteur = utilisateurDao.lire(idUtilisateur).getCredit() - montantEnchere;
					utilisateurDao.modifierCreditParId(utilisateurDao.lire(idUtilisateur).getId(), nouveauCreditAcheteur);
				}
			}	
	}
	
	// est ce que l'utilisateur à assez d'argent sur son compte pour faire une enchère ?
	private boolean creditSuffisant(int idUtilisateur,int montantEnchere,BusinessException be) {
		boolean estValid = false;
		Utilisateur utilisateur = utilisateurDao.lire(idUtilisateur);
		
		if(utilisateur.getCredit() >= montantEnchere ) {
			estValid = true;
		}else {
			be.add("credit insuffisant");
		}
		return estValid;
	}
	// test enchere existe
	private boolean enchereExistepas(int idArticle) {
		boolean existe = false;
		
		if(articleDao.nbEnchereArticle(idArticle) == 0) {
			existe = true;	
		}
		
		return existe;
	}
	
	// utilisateur en base
	private boolean utilisateurExiste(int idUtilisateur,BusinessException be) {
		boolean existe = false;
		
		if(utilisateurDao.lire(idUtilisateur) != null) {
			existe = true;
		}else {
			be.add("Connectez-vous svp !");
		}
		return existe;
	}
	
	@Override
	public void sauvegarderArticle(int id,ArticleVendu article) {
		articleDao.save(id,article);	
	}
}
