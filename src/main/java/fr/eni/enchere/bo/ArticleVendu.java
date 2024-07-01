package fr.eni.enchere.bo;

import java.time.LocalDate;

public class ArticleVendu {
	
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEnchere;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private String etatVente;
	
	private Retrait lieuretrait;
	private Categorie CategorieArticle;
	
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEnchere,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, String etatVente, Retrait lieuRetrait, Categorie CategorieArticle) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.lieuretrait = lieuRetrait;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebutEnchere() {
		return dateDebutEnchere;
	}

	public void setDateDebutEnchere(LocalDate dateDebutEnchere) {
		this.dateDebutEnchere = dateDebutEnchere;
	}

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public String getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	public Retrait getLieuretrait() {
		return lieuretrait;
	}

	public void setLieuretrait(Retrait lieuretrait) {
		this.lieuretrait = lieuretrait;
	}

	public Categorie getCategorieArticle() {
		return CategorieArticle;
	}

	public void setCategorieArticle(Categorie categorieArticle) {
		CategorieArticle = categorieArticle;
	}

	@Override
	public String toString() {
		return String.format(
				"ArticleVendu [noArticle=%s, nomArticle=%s, description=%s, dateDebutEnchere=%s, dateFinEncheres=%s, miseAPrix=%s, prixVente=%s, etatVente=%s, lieuretrait=%s, CategorieArticle=%s]",
				noArticle, nomArticle, description, dateDebutEnchere, dateFinEncheres, miseAPrix, prixVente, etatVente,
				lieuretrait, CategorieArticle);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
