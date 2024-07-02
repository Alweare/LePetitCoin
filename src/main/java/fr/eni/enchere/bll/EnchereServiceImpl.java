package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.EnchereDAO;

public class EnchereServiceImpl implements EnchereService {
	private EnchereDAO enchereDao;
	
	
	
	public EnchereServiceImpl(EnchereDAO enchereDao) {
		this.enchereDao = enchereDao;
	}

	@Override
	public void CreerEnchere(Enchere enchere) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Enchere> recupererEnchereEnCours() {
		
		return this.enchereDao.touveToutEnCours();
	}

	@Override
	public List<Enchere> recupererParParticipant(int idParticipant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enchere> recupererEnchereGagnee(int idAcquereur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere RecupererArticle(int idArticle) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
