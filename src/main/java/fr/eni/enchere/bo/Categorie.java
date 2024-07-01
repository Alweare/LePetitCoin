package fr.eni.enchere.bo;

import java.time.LocalDate;

public class Categorie extends ArticleVendu{

	private int id;
	private String libelle;
	
	public Categorie(int noArticle, String nomArticle, String description, LocalDate dateDebutEnchere,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, String etatVente, Retrait lieuRetrait,
			Categorie CategorieArticle, int noCategorie, String libelle) {
		super(noArticle, nomArticle, description, dateDebutEnchere, dateFinEncheres, miseAPrix, prixVente, etatVente,
				lieuRetrait, CategorieArticle);
		this.id = noCategorie;
		this.libelle = libelle;
	}

	public int getNoCategorie() {
		return id;
	}

	public void setNoCategorie(int noCategorie) {
		this.id = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return String.format("Categorie [noCategorie=%s, libelle=%s]", id, libelle);
	}
	
	
	
	
	
	
	
}
