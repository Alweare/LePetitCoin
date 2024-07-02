package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;


public interface EnchereDAO {
	List<Enchere> trouveTout();
	List<Enchere> touveToutEnCours();
	List<Enchere> trouveEnCoursParCategorie(int idCat);
	List<Enchere> trouveEnchereGagneeParUtilisateur(int id);
	Enchere lire(int id);
	Utilisateur trouverAcquereurParProduit(int id);
	List<Enchere> trouverParVendeur(int id);
	List<Enchere> trouveParEncherisseur(int id);
	void creer(Enchere enchere);
}
