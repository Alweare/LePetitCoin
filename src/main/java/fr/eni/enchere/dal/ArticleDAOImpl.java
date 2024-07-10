package fr.eni.enchere.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
			+ " AV.cheminImage,"
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
			+ "	UV.pseudo AS vendeurPseudo"
			+ "		FROM ARTICLES_VENDUS as AV\r\n"
			+ "		INNER JOIN UTILISATEURS as UV ON (UV.id = AV.idUtilisateur)"
			+ "		INNER JOIN CATEGORIES AS C ON (AV.idCategorie = C.id) "
			+ "		INNER JOIN RETRAITS as R ON (AV.id = R.idArticle)";
	private static final String TROUVE_ACTIVES = TROUVE_TOUT + " WHERE AV.dateDebutEncheres <= GETDATE() AND AV.dateFinEncheres > GETDate()";
	private static final String CREER = "INSERT INTO ARTICLES_VENDUS (nomArticle, description, cheminImage, dateDebutEncheres, dateFinEncheres, prixInitial, prixVente, idUtilisateur, idCategorie)"
			+ "	VALUES (:nomArticle, :description, :cheminImage, :dateDebut, :dateFin, :prixInitial, :prixVente, :idUtilisateur, :idCategorie);";
	private static final String TROUVE_ENCHERE ="SELECT idUtilisateur, idArticle, dateEnchere, montantEnchere FROM ENCHERES";
	private static final String TROUVE_ENCHERE_PAR_ID_UTILISATEUR=TROUVE_ENCHERE + " where idUtilisateur = :id";
	private static final String CHANGER_ID_ENCHERES="ALTER TABLE ENCHERES DROP enchere_pk;"
			+ "UPDATE ENCHERES SET idUtilisateur=:nouveauId WHERE idUtilisateur=:ancienId;"
			+ "ALTER TABLE ENCHERES ADD CONSTRAINT enchere_pk PRIMARY KEY (idUtilisateur,idArticle)"
			+ "	VALUES (:nomArticle, :description, :dateDebut, :dateFin, :prixInitial, :prixVente, :idUtilisateur, :idCategorie);";
	private static final String TROUVE_CATEGORIES = "SELECT id, libelle FROM CATEGORIES";
	private static final String CHERCHE_TOUT_CATEGORIE="SELECT id,libelle FROM CATEGORIES";
	private static final String TROUVE_CATEGORIE_PAR_ID= TROUVE_CATEGORIES + " WHERE id = :id";
	private static final String TROUVE_ARTICLE_FILTRER=TROUVE_ACTIVES + " AND c.id = :idCategorie";
	private static final String TROUVE_ARTICLE_FILTRER_AVEC_NOM=TROUVE_ARTICLE_FILTRER + " AND av.nomArticle = :nomArticle";
	private static final String TROUVE_ENCHERE_PAR_ID_ARTICLE=TROUVE_TOUT + " WHERE AV.id = :id";
	private static final String TROUVE_MES_VENTES_EN_COURS = TROUVE_ACTIVES +" AND av.idUtilisateur =:id";
	private static final String TROUVE_ENCHERES = "SELECT\r\n"
			+ "	AV.id,\r\n"
			+ "	AV.nomArticle, \r\n"
			+ "	AV.description, \r\n"
			+ "	AV.dateDebutEncheres, \r\n"
			+ "	AV.dateFinEncheres,\r\n"
			+ "	AV.prixVente,\r\n"
			+ "	AV.prixInitial,\r\n"
			+ "	AV.idUtilisateur AS idVendeur,\r\n"
			+ "	C.id AS idCategorie, \r\n"
			+ "	C.libelle, \r\n"
			+ "	R.rue,\r\n"
			+ "	R.code_postal,\r\n"
			+ "	R.ville,\r\n"
			+ "	UV.id AS idVendeur,\r\n"
			+ "	UV.pseudo AS vendeurPseudo,\r\n"
			+ "	E.montantEnchere\r\n"
			+ "		FROM ARTICLES_VENDUS as AV\r\n"
			+ "		INNER JOIN UTILISATEURS as UV ON (UV.id = AV.idUtilisateur)\r\n"
			+ "		INNER JOIN CATEGORIES AS C ON (AV.idCategorie = C.id) \r\n"
			+ "		INNER JOIN RETRAITS as R ON (AV.id = R.idArticle)\r\n"
			+ "		INNER JOIN ENCHERES as E on (AV.idUtilisateur = E.idUtilisateur)\r\n";
	private static final String TROUVE_ENCHERES_EN_COURS = TROUVE_ENCHERES 
			+ "	WHERE AV.dateFinEncheres <= CURRENT_TIMESTAMP AND E.idUtilisateur=:id";
	private static final String TROUVE_ENCHERES_REMPORTER=TROUVE_ENCHERES_EN_COURS+ " AND E.montantEnchere = (SELECT MAX(montantEnchere) FROM ENCHERES WHERE idArticle = AV.id);";
	private static final String TROUVE_MES_VENTES_NON_DEBUTER = TROUVE_TOUT + " WHERE AV.dateDebutEncheres > CURRENT_TIMESTAMP AND AV.idUtilisateur =:id;";
	private static final String TROUVE_MES_VENTES_TERMINER = TROUVE_TOUT + "WHERE AV.dateFinEncheres < CURRENT_TIMESTAMP AND AV.idUtilisateur =:id";
	private static final String CREER_ENCHERE ="INSERT INTO ENCHERES( idUtilisateur, idArticle,dateEnchere,montantEnchere) VALUES (:idUtilisateur,:idArticle,CURRENT_TIMESTAMP , :montantEnchere)";
	private static final String COUNT_ENCHERE_ARTICLE = "SELECT COUNT(*) FROM ENCHERES WHERE idArticle = :idArticle";
	private static final String ENCHERE_PAR_ARTICLE_PAR_DATE = "SELECT TOP 1\r\n"
			+ "	         AV.id,\r\n"
			+ "	         AV.nomArticle,\r\n"
			+ "	        AV.description,\r\n"
			+ "	       AV.dateDebutEncheres,\r\n"
			+ "	       AV.dateFinEncheres,\r\n"
			+ "	       AV.prixVente,\r\n"
			+ "	        AV.prixInitial,\r\n"
			+ "	       AV.idUtilisateur AS idVendeur,\r\n"
			+ "		   E.idUtilisateur AS idAcheteur,\r\n"
			+ "		   E.montantEnchere,\r\n"
			+ "	      E.dateEnchere,\r\n"
			+ "	     UV.id AS vendeurId,\r\n"
			+ "	      UV.pseudo AS vendeurPseudo\r\n"
			+ "	       FROM \r\n"
			+ "	      ARTICLES_VENDUS AS AV\r\n"
			+ "	      INNER JOIN UTILISATEURS AS UV ON UV.id = AV.idUtilisateur\r\n"
			+ "	      INNER JOIN ENCHERES AS E ON AV.id = E.idArticle\r\n"
			+ "	      WHERE\r\n"
			+ "	        AV.id = 26 \r\n"
			+ "	        ORDER BY \r\n"
			+ "	        E.dateEnchere DESC";
	private static final String MISE_A_JOUR_PRIX_ARTICLE = "UPDATE ARTICLES_VENDUS SET prixVente = :prixVente WHERE id = :id";
	
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
	public List<ArticleVendu> trouveMesEncheresEnCours(int id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		return jdbc.query(TROUVE_ENCHERES_EN_COURS,mapSqlParameterSource ,new ArticleRowMapper());
	}
	
	@Override
	public List<ArticleVendu> trouveMesVentesEnCour(int id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		return jdbc.query(TROUVE_MES_VENTES_EN_COURS,mapSqlParameterSource ,new ArticleRowMapper());
	}
		
	@Override
	public ArticleVendu lire(int id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id); 
		return this.jdbc.queryForObject(TROUVE_ENCHERE_PAR_ID_ARTICLE, mapSqlParameterSource, new ArticleRowMapper());
	}
	
	@Override
	public int creer(ArticleVendu article) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource map = new MapSqlParameterSource();
		//(:nomArticle, :description, :dateDebut, :dateFin, :prixInitial, :prixVente, :idutilisateur, :inCategoie);";
		map.addValue("nomArticle", article.getNomArticle());
		map.addValue("description", article.getDescription());
		map.addValue("cheminImage", "DEFAULT");
		map.addValue("dateDebut", article.getDateDebutEnchere());
		map.addValue("dateFin", article.getDateFinEncheres());
		map.addValue("prixInitial", article.getPrixInitial());
		map.addValue("prixVente", article.getPrixVente());
		map.addValue("idUtilisateur", article.getVendeur().getId());
		map.addValue("idCategorie", article.getCategorieArticle().getId()); 		
		this.jdbc.update(CREER, map, keyHolder);	
		 if (keyHolder != null && !keyHolder.getKey().equals(0)) {
			 return keyHolder.getKey().intValue();
		 }
		 return 0;
	}

	@Override
	public Enchere trouverEnchereParID(int id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		return jdbc.queryForObject(TROUVE_ENCHERE_PAR_ID_UTILISATEUR, mapSqlParameterSource, new BeanPropertyRowMapper<>(Enchere.class));
	}

	@Override
	public void changerIdDansEnchere(int ancienId, int nouveauId) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("ancienId",ancienId);
		mapSqlParameterSource.addValue("nouveauId", nouveauId);
		jdbc.update(CHANGER_ID_ENCHERES, mapSqlParameterSource);
	}	

	public List<Categorie> trouverCategories() {		
		return this.jdbc.query(TROUVE_CATEGORIES, new BeanPropertyRowMapper<Categorie>(Categorie.class));
	}

	@Override
	public List<Categorie> chercheTout() {
		return jdbc.query(CHERCHE_TOUT_CATEGORIE, new BeanPropertyRowMapper<>(Categorie.class));
	}

	
	@Override
	public Categorie trouveCategorieParIdint(int id) {
		MapSqlParameterSource map = new MapSqlParameterSource();		
		map.addValue("id", id);
		return this.jdbc.queryForObject(TROUVE_CATEGORIE_PAR_ID, map, new BeanPropertyRowMapper<Categorie>(Categorie.class));
	}
	
	@Override
	public List<ArticleVendu> filtrerArticle(int idUtilisateur) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("idCategorie", idUtilisateur);
		return jdbc.query(TROUVE_ARTICLE_FILTRER, mapSqlParameterSource, new ArticleRowMapper());
	}
	
	@Override
	public List<ArticleVendu> rechercherArticlesParCategorieEtNom(int id, String recherche) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("idCategorie", id);
		mapSqlParameterSource.addValue("nomArticle", recherche);
		return jdbc.query(TROUVE_ARTICLE_FILTRER_AVEC_NOM, mapSqlParameterSource,new ArticleRowMapper());
	}
	
	@Override
	public List<ArticleVendu> trouveMesEncheresRemporter(int id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		return jdbc.query(TROUVE_ENCHERES_REMPORTER,mapSqlParameterSource, new ArticleRowMapper());
	}
	
	@Override
	public List<ArticleVendu> trouveMesVentesNonDebutées(int id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		return jdbc.query(TROUVE_MES_VENTES_NON_DEBUTER,mapSqlParameterSource, new ArticleRowMapper());
	}

	@Override
	public List<ArticleVendu> trouveMesVentesTerminer(int id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		return jdbc.query(TROUVE_MES_VENTES_TERMINER,mapSqlParameterSource, new ArticleRowMapper());
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
			article.setCheminImage(rs.getString("cheminImage"));
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
	public void creerEnchere(int idUtilisateur,int idArticle, int montantEnchere) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("idUtilisateur", idUtilisateur);
		mapSqlParameterSource.addValue("idArticle", idArticle);
		mapSqlParameterSource.addValue("montantEnchere", montantEnchere);		
		jdbc.queryForObject(CREER_ENCHERE, mapSqlParameterSource, new BeanPropertyRowMapper<>(Enchere.class));		

		mapSqlParameterSource.addValue("montantEnchere", montantEnchere);
		
		jdbc.update(CREER_ENCHERE, mapSqlParameterSource);


	}

	@Override
	public int nbEnchereArticle(int idArticle) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("idArticle", idArticle);		
		return jdbc.queryForObject(COUNT_ENCHERE_ARTICLE,mapSqlParameterSource, Integer.class);
	}

	@Override
	public ArticleVendu enchereArticle(int id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

		mapSqlParameterSource.addValue("idArticle", idArticle);		
		return jdbc.queryForObject(ENCHERE_PAR_ARTICLE_PAR_DATE, mapSqlParameterSource, new BeanPropertyRowMapper<>(ArticleVendu.class));

		mapSqlParameterSource.addValue("id", id);
		
		return jdbc.queryForObject(ENCHERE_PAR_ARTICLE_PAR_DATE, mapSqlParameterSource, new ArticleRowMapperEnchere());

	}


	public class ArticleRowMapperEnchere implements RowMapper<ArticleVendu> {

		@Override
		public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
			ArticleVendu article = new ArticleVendu();
			Utilisateur vendeur = new Utilisateur();
			Utilisateur acheteur = new Utilisateur();
			Enchere enchere = new Enchere();
			
			article.setId(rs.getInt("id"));
			article.setNomArticle(rs.getString("nomArticle"));
			article.setDescription(rs.getString("description"));
			article.setDateDebutEnchere(rs.getTimestamp("dateDebutEncheres").toLocalDateTime());
			article.setDateFinEncheres(rs.getTimestamp("dateFinEncheres").toLocalDateTime());
			article.setPrixInitial(rs.getInt("prixInitial"));
			article.setPrixVente(rs.getInt("prixVente"));
			
			enchere.setDateEnchere(rs.getTimestamp("dateEnchere").toLocalDateTime());
			enchere.setMontantEnchere(rs.getInt("montantEnchere"));
			
			article.setEncheres(enchere);

			vendeur.setId(rs.getInt("idVendeur"));
			vendeur.setPseudo(rs.getString("vendeurPseudo"));
			
			acheteur.setId(rs.getInt("idAcheteur"));


			article.setVendeur(vendeur);
			article.setAcheteur(acheteur);
			
			return article;
		}
	}
	


}
	