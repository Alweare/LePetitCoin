package fr.eni.enchere.dal;

import fr.eni.enchere.bo.Retrait;

public interface RetraitDAO {
	void creer(Retrait retrait);
	void recupererparidArticle(int idArticle);
}
