package fr.eni.enchere.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.enchere.bo.Utilisateur;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {

	private static final String TROUVE_TOUT = "SELECT id, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS";
	private static final String TROUVE_AVEC_ID ="SELECT id, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE id = :id";
	private static final String TROUVE_AVEC_PSEUDO = "SELECT pseudo, mot_de_passe FROM UTILISATEURS WHERE pseudo = :pseudo";
	private static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES (:pseudo,:nom,:prenom,:email,:telephone,:rue,:code_postal,:ville,:mot_de_passe,0,0)";

	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	
	public UtilisateurDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Utilisateur> trouveTout() {
		return jdbcTemplate.query(TROUVE_TOUT, new UtilisateurRowMapper());
	}

	@Override
	public Utilisateur lire(long id) {
		MapSqlParameterSource parametreSource = new MapSqlParameterSource();
		parametreSource.addValue("id", id);
		return jdbcTemplate.queryForObject(TROUVE_AVEC_ID, parametreSource, new UtilisateurRowMapper() );
	}
/**
 * @param : Utilisateur
 * Créer un utilisateur en base 
 * on ne set pas l'id car c'est une identity en bdd 
 * 
 * */
	@Override
	public void creerUtilisateur(Utilisateur utilisateur) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("pseudo", utilisateur.getPseudo());
		mapSqlParameterSource.addValue("nom", utilisateur.getNom());
		mapSqlParameterSource.addValue("prenom", utilisateur.getPrenom());
		mapSqlParameterSource.addValue("email", utilisateur.getEmail());
		mapSqlParameterSource.addValue("telephone", utilisateur.getTelephone());
		mapSqlParameterSource.addValue("rue", utilisateur.getRue());
		mapSqlParameterSource.addValue("ville", utilisateur.getVille());
		mapSqlParameterSource.addValue("code_postal", utilisateur.getCodePostal());
		mapSqlParameterSource.addValue("mot_de_passe", utilisateur.getMotDePasse());
		mapSqlParameterSource.addValue("credit", utilisateur.getCredit());
		
		jdbcTemplate.update(INSERT_UTILISATEUR, mapSqlParameterSource);
	}
	
	@Override
	public Utilisateur trouveParPseudo(String pseudo) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("pseudo", pseudo);
		System.out.println(pseudo);
		return jdbcTemplate.queryForObject(TROUVE_AVEC_PSEUDO, parameterSource , new BeanPropertyRowMapper<>(Utilisateur.class));
	}

	class UtilisateurRowMapper implements org.springframework.jdbc.core.RowMapper<Utilisateur>{

		@Override
		public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNoUtlisateur(rs.getInt("id"));
			utilisateur.setPseudo(rs.getString("pseudo"));
			utilisateur.setNom(rs.getString("nom"));
			utilisateur.setPrenom(rs.getString("prenom"));
			utilisateur.setEmail(rs.getString("email"));
			utilisateur.setTelephone(rs.getString("telephone"));
			utilisateur.setRue(rs.getString("rue"));
			utilisateur.setCodePostal(rs.getString("code_postal"));
			utilisateur.setVille(rs.getString("ville"));
			utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
			utilisateur.setCredit(rs.getInt("credit"));
			utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
			return utilisateur;
		}
		
		
	}




	
}
