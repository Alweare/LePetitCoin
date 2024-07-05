package fr.eni.enchere.dal;

import fr.eni.enchere.bo.Retrait;

public interface RetraitDAO {
	void creer(Retrait retrait, int idArticle);
	void recupererparidArticle(int idArticle);
}
