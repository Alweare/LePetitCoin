package fr.eni.enchere.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;

@Repository
public class EnchereDAOImpl implements EnchereDAO {
	private static final String TROUVE_TOUT = "SELECT AV.id, AV.nomArticle, AV.description, AV.prixInitial, AV.prixVente, AV.dateDebutEncheres, \r\n"
			+ "	AV.dateDebutEncheres, C.id, C.libelle,E.dateEnchere, E.montantEnchere,U.id, U.pseudo"
			+ "	FROM ENCHERES as E "
			+ "		INNER JOIN UTILISATEURS as U ON (e.idUtilisateur = u.id) \r\n"
			+ "		INNER JOIN ARTICLES_VENDUS as AV ON (AV.idUtilisateur = u.id) \r\n"
			+ "		INNER JOIN CATEGORIES AS C ON (AV.idCategorie = C.id) ";
	private static final String TROUVE_ACTIVES = TROUVE_TOUT + " WHERE AV.dateFinEncheres > CURRENT_TIMESTAMP;";
	
	private NamedParameterJdbcTemplate jdbc;
	
	
	
	
	public EnchereDAOImpl(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public List<Enchere> trouveTout() {
		return this.jdbc.query(TROUVE_TOUT, new BeanPropertyRowMapper<Enchere>(Enchere.class));
	}

	@Override
	public List<Enchere> touveToutEnCours() {
		return this.jdbc.query(TROUVE_ACTIVES, new BeanPropertyRowMapper<Enchere>(Enchere.class));
	}

	@Override
	public List<Enchere> trouveEnCoursParCategorie(int idCat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enchere> trouveEnchereGagneeParUtilisateur(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere lire(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur trouverAcquereurParProduit(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enchere> trouverParVendeur(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enchere> trouveParEncherisseur(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creer(Enchere enchere) {
		// TODO Auto-generated method stub
		
	}

}
