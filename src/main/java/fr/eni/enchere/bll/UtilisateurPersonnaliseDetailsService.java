package fr.eni.enchere.bll;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurPersonnaliseDetailsService implements UserDetailsService{

	
	private String SELECT_UTILISATEUR_BY_PSEUDO = "SELECT pseudo, mot_de_passe, 1 as enabled FROM UTILISATEURS WHERE pseudo = ?";
	private String SELECT_UTILISATEUR_BY_EMAIL = "SELECT pseudo, mot_de_passe, 1 as enabled FROM UTILISATEURS WHERE email = ?";
	
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	

	public UtilisateurPersonnaliseDetailsService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails utilisateur;
		try {
			utilisateur = jdbcTemplate.queryForObject(SELECT_UTILISATEUR_BY_PSEUDO, new Object[] {username}, utilisateurMapper);
			if(utilisateur !=null) {
				return utilisateur;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			utilisateur = jdbcTemplate.queryForObject(SELECT_UTILISATEUR_BY_EMAIL, new Object[] {username}, utilisateurMapper);
			if(utilisateur !=null) {
				return utilisateur;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		throw new UsernameNotFoundException("Utilisateur introuvable avec nom d'utilisateur ou email: " +username);
	}
	private RowMapper<UserDetails> utilisateurMapper = (rs,rowNum) -> {
		String pseudo = rs.getString("pseudo");
		String motDePasse = rs.getString("mot_de_passe");
		Boolean enabled = rs.getBoolean("enabled");
		return new org.springframework.security.core.userdetails.User(pseudo, motDePasse, enabled ,true, true, true, new ArrayList<>());
	};
	
	
}
