package fr.eni.enchere.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArticleVendu {
	
	private int id;
	private String nomArticle;
	private String description;
	private LocalDateTime dateDebutEnchere;
	private LocalDateTime dateFinEncheres;
	private int prixInitial;
	private int prixVente;
	private String etatVente;
	
	private Retrait lieuRetrait;
	private Categorie CategorieArticle;
	private Utilisateur acheteur;
	private Utilisateur vendeur;
	private List<Enchere> encheres;
	
	

	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}

	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	public List<Enchere> getEncheres() {
		return encheres;
	}

	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}
	public ArticleVendu() {
		
	}
	public ArticleVendu(int id, String nomArticle, String description, LocalDateTime dateDebutEnchere,
			LocalDateTime dateFinEncheres, int prixInitial, int prixVente, String etatVente, Retrait lieuRetrait,
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
		
		this.encheres= new ArrayList<Enchere>();
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

	public LocalDateTime getDateDebutEnchere() {
		return dateDebutEnchere;
	}

	public void setDateDebutEnchere(LocalDateTime dateDebutEnchere) {
		this.dateDebutEnchere = dateDebutEnchere;
	}

	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getMiseAPrix() {
		return prixInitial;
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

	public int getPrixVente() {
		return prixVente;
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
