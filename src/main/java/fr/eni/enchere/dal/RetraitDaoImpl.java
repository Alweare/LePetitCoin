package fr.eni.enchere.dal;

import java.sql.ResultSet;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.enchere.bo.Retrait;
@Repository
public class RetraitDaoImpl implements RetraitDAO{
	private static final String TROUVE_PAR_ID_ARTICLE = "SELECT idArticle, rue, code_postal, ville FROM RETRAITS WHERE idArticle = :idArticle";
	private static final String CREER_RETRAIT= "INSERT INTO RETRAITS (idArticle, rue, code_postal,ville) VALUES (:idArticle, :rue, :codePostal, :ville);";
	
	private NamedParameterJdbcTemplate jdbc;	
	
	public RetraitDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super();
		this.jdbc = jdbc;
	}

	@Override
	public void creer(Retrait retrait, int idArticle) {
		MapSqlParameterSource map = new MapSqlParameterSource();		
		map.addValue("idArticle", idArticle);
		map.addValue("rue", retrait.getRue());
		map.addValue("codePostal", retrait.getCode_postal());
		map.addValue("ville", retrait.getVille());		
		this.jdbc.update(CREER_RETRAIT, map);		
	}

	@Override
	public void recupererparidArticle(int idArticle) {
		MapSqlParameterSource map = new MapSqlParameterSource();		
		map.addValue("idArticle", idArticle);
		this.jdbc.queryForObject(TROUVE_PAR_ID_ARTICLE, map, new BeanPropertyRowMapper<Retrait>(Retrait.class));		
	}

}
