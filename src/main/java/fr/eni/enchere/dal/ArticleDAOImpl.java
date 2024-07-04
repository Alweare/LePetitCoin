package fr.eni.enchere.dal;

import java.security.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

@Repository
public class ArticleDAOImpl implements ArticleDAO {
	private static final String TROUVE_TOUT = "SELECT"
			+ "	AV.id,"
			+ "	AV.nomArticle, "
			+ "	AV.description,"
			+ "	AV.prixInitial,"
			+ "	AV.prixVente,"
			+ "	AV.dateDebutEncheres,"
			+ "	AV.dateFinEncheres,"
			+ "	AV.prixVente,"
			+ "	AV.prixInitial,"
			+ "	AV.idUtilisateur AS idVendeur,"
			+ "	C.id AS idCategorie,"
			+ "	C.libelle, "
			+ "	R.rue,"
			+ "	R.code_postal,"
			+ "	R.ville,"
			+ "	UV.id AS idVendeur,"
			+ "	UV.pseudo AS vendeurPseudo\r\n"
			+ "		FROM ARTICLES_VENDUS as AV\r\n"
			+ "		INNER JOIN UTILISATEURS as UV ON (UV.id = AV.idUtilisateur)"
			+ "		INNER JOIN CATEGORIES AS C ON (AV.idCategorie = C.id) "
			+ "		INNER JOIN RETRAITS as R ON (AV.id = R.idArticle)";
	private static final String TROUVE_ACTIVES = TROUVE_TOUT + " WHERE AV.dateFinEncheres > CURRENT_TIMESTAMP;";
	private static final String CREER = "INSERT INTO ARTICLES_VENDUS (nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, prixVente, idUtilisateur, idCategorie)"
			+ "	VALUES (:nomArticle, :description, :dateDebut, :dateFin, :prixInitial, :prixVente, :idutilisateur, :idCategoie);";
	private static final String TROUVE_CATEGORIES = "SELECT id, libelle FROM categories";
	
	private NamedParameterJdbcTemplate jdbc;
	
	
	
	
	public ArticleDAOImpl(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public List<ArticleVendu>  trouveTout() {
		return this.jdbc.query(TROUVE_TOUT, new ArticleRowMapper());
	}

	@Override
	public List<ArticleVendu> touveToutEnCours() {
		return this.jdbc.query(TROUVE_ACTIVES, new ArticleRowMapper());
	}

	@Override
	public List<ArticleVendu> trouveEnCoursParCategorie(int idCat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleVendu>  trouveEnchereGagneeParUtilisateur(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticleVendu lire(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur trouverAcquereurParProduit(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleVendu> trouverParVendeur(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleVendu>  trouveParEncherisseur(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creer(ArticleVendu article) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		//(:nomArticle, :description, :dateDebut, :dateFin, :prixInitial, :prixVente, :idutilisateur, :inCategoie);";
		map.addValue("nomArticle", article.getNomArticle());
		map.addValue("description", article.getDescription());
		map.addValue("dateDebut", article.getDateDebutEnchere());
		map.addValue("dateFin", article.getDateFinEncheres());
		map.addValue("prixInitial", article.getPrixInitial());
		map.addValue("prixVente", article.getPrixVente());
		map.addValue("idUtilisateur", article.getVendeur().getId());
		map.addValue("idCategorie", article.getCategorieArticle().getId());
		
	}
	public class ArticleRowMapper implements RowMapper<ArticleVendu> {

		@Override
		public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
			ArticleVendu article = new ArticleVendu();
			Categorie categorie = new Categorie();
			Retrait retrait = new Retrait();
			Utilisateur vendeur = new Utilisateur();
			
			article.setId(rs.getInt("id"));
			article.setNomArticle(rs.getString("nomArticle"));
			article.setDescription(rs.getString("description"));
			article.setDateDebutEnchere(rs.getTimestamp("dateDebutEncheres").toLocalDateTime());
			article.setDateFinEncheres(rs.getTimestamp("dateFinEncheres").toLocalDateTime());
			article.setPrixInitial(rs.getInt("prixInitial"));
			article.setPrixVente(rs.getInt("prixVente"));
			
			categorie.setId(rs.getInt("idCategorie"));
			categorie.setLibelle(rs.getString("libelle"));
			
			retrait.setCode_postal(rs.getString("code_postal"));
			retrait.setRue(rs.getString("rue"));
			retrait.setVille(rs.getString("ville"));
			
			vendeur.setId(rs.getInt("idVendeur"));
			vendeur.setPseudo(rs.getString("vendeurPseudo"));

			article.setVendeur(vendeur);
			article.setLieuRetrait(retrait);
			article.setCategorieArticle(categorie);
			
			return article;
		}
	}
	@Override
	public List<ArticleVendu> trouverCategories() {
		
		return this.jdbc.query(TROUVE_CATEGORIES, new BeanPropertyRowMapper<>(ArticleVendu.class));
	}
}

