package fr.eni.enchere.bo;

import java.time.LocalDate;

public class Retrait extends ArticleVendu{
	
	private String rue;
	private String code_postal;
	private String ville;
	
	public Retrait(int noArticle, String nomArticle, String description, LocalDate dateDebutEnchere,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, String etatVente, Retrait lieuRetrait,
			Categorie CategorieArticle, String rue, String code_postal, String ville) {
		super(noArticle, nomArticle, description, dateDebutEnchere, dateFinEncheres, miseAPrix, prixVente, etatVente,
				lieuRetrait, CategorieArticle);
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return String.format("Retrait [rue=%s, code_postal=%s, ville=%s]", rue, code_postal, ville);
	}
	
	

}
