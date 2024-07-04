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
	private static final String TROUVE_AVEC_PSEUDO = "SELECT id, pseudo, nom, prenom, email, telephone, rue,code_postal,ville, mot_de_passe, credit FROM UTILISATEURS WHERE pseudo = :pseudo";
	private static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES (:pseudo,:nom,:prenom,:email,:telephone,:rue,:code_postal,:ville,:mot_de_passe,0,0)";
	private static final String UPDATE_UTILISATEUR = "UPDATE UTILISATEURS SET pseudo = :pseudo, nom = :nom, prenom = :prenom, email = :email, telephone = :telephone, rue = :rue, code_postal = :code_postal, ville = :ville WHERE id = :id";
	private static final String SUPPRIMER_COMPTE = "DELETE FROM UTILISATEURS WHERE id = :id";
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	
	public UtilisateurDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Utilisateur> trouveTout() {
		return jdbcTemplate.query(TROUVE_TOUT, new UtilisateurRowMapper());
	}


/**
 * @param : Utilisateur
 * Créer un utilisateur en base 
 * on ne set pas l'id car c'est une identity en bdd 
 * 
 * */
	@Override
	public void creerUtilisateur(Utilisateur utilisateur) {
		System.out.println(utilisateur);
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("pseudo", utilisateur.getPseudo());
		mapSqlParameterSource.addValue("nom", utilisateur.getNom());
		mapSqlParameterSource.addValue("prenom", utilisateur.getPrenom());
		mapSqlParameterSource.addValue("email", utilisateur.getEmail());
		mapSqlParameterSource.addValue("telephone", utilisateur.getTelephone());
		mapSqlParameterSource.addValue("rue", utilisateur.getRue());
		mapSqlParameterSource.addValue("code_postal", utilisateur.getCodePostal());
		mapSqlParameterSource.addValue("ville", utilisateur.getVille());
		mapSqlParameterSource.addValue("mot_de_passe", utilisateur.getMotDePasse());
		
		jdbcTemplate.update(INSERT_UTILISATEUR, mapSqlParameterSource);
	}
	
	@Override
	public Utilisateur trouveParPseudo(String pseudo) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("pseudo", pseudo);
		return jdbcTemplate.queryForObject(TROUVE_AVEC_PSEUDO, parameterSource , new BeanPropertyRowMapper<>(Utilisateur.class));
	}
	@Override
	public Utilisateur lire(int id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		return jdbcTemplate.queryForObject(TROUVE_AVEC_ID, mapSqlParameterSource,new UtilisateurRowMapper());
		 
	}

	@Override
	public void modifierCreditParId(int id) {
		// TODO Auto-generated method stub
		
	}
	class UtilisateurRowMapper implements org.springframework.jdbc.core.RowMapper<Utilisateur>{

		@Override
		public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setId(rs.getInt("id"));
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
	@Override
	public void miseAjourUtilisateur(Utilisateur utilisateur) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", utilisateur.getId());
		mapSqlParameterSource.addValue("pseudo", utilisateur.getPseudo());
		mapSqlParameterSource.addValue("nom", utilisateur.getNom());
		mapSqlParameterSource.addValue("prenom", utilisateur.getPrenom());
		mapSqlParameterSource.addValue("email", utilisateur.getEmail());
		mapSqlParameterSource.addValue("telephone", utilisateur.getTelephone());
		mapSqlParameterSource.addValue("rue", utilisateur.getRue());
		mapSqlParameterSource.addValue("code_postal", utilisateur.getCodePostal());
		mapSqlParameterSource.addValue("ville", utilisateur.getVille());
		
		jdbcTemplate.update(UPDATE_UTILISATEUR, mapSqlParameterSource);
	}

	@Override
	public void supprimerUnUtilisateur(int id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		jdbcTemplate.update(SUPPRIMER_COMPTE, mapSqlParameterSource);
	}

	
}
