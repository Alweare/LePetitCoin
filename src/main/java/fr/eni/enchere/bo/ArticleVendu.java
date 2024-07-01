package fr.eni.enchere.bo;

import java.time.LocalDate;
import java.util.Objects;

public class ArticleVendu {
	
	private int id;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEnchere;
	private LocalDate dateFinEncheres;
	private int prixInitial;
	private int prixVente;
	private String etatVente;
	
	private Retrait lieuRetrait;
	private Categorie CategorieArticle;
	private Utilisateur acheteur;
	private Utilisateur vendeur;
	
	

	public ArticleVendu(int id, String nomArticle, String description, LocalDate dateDebutEnchere,
			LocalDate dateFinEncheres, int prixInitial, int prixVente, String etatVente, Retrait lieuRetrait,
			Categorie categorieArticle, Utilisateur acheteur, Utilisateur vendeur) {
		super();
		this.id = id;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.lieuRetrait = lieuRetrait;
		CategorieArticle = categorieArticle;
		this.acheteur = acheteur;
		this.vendeur = vendeur;
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
		return prixInitial;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.prixInitial = miseAPrix;
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
		return lieuRetrait;
	}

	public void setLieuretrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	public Categorie getCategorieArticle() {
		return CategorieArticle;
	}

	public void setCategorieArticle(Categorie categorieArticle) {
		CategorieArticle = categorieArticle;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public Utilisateur getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleVendu other = (ArticleVendu) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return String.format(
				"ArticleVendu [id=%s, nomArticle=%s, description=%s, dateDebutEnchere=%s, dateFinEncheres=%s, prixInitial=%s, prixVente=%s, etatVente=%s, lieuRetrait=%s, CategorieArticle=%s, acheteur=%s, vendeur=%s]",
				id, nomArticle, description, dateDebutEnchere, dateFinEncheres, prixInitial, prixVente, etatVente,
				lieuRetrait, CategorieArticle, acheteur, vendeur);
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
