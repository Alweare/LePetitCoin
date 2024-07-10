package fr.eni.enchere.bo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

<<<<<<< Updated upstream
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
=======
>>>>>>> Stashed changes

public class ArticleVendu {
	
	private int id;
	
	@NotBlank
	private String nomArticle;
	
	@NotBlank
	@Size(min= 15, max = 500, message= "La description doit avoir entre 15 et 500 caractères")
	private String description;
	
	//TODO IMPLEMENT IN FORM
	private String cheminImage;

	@NotNull
	@FutureOrPresent
	private LocalDateTime dateDebutEnchere;
	
	@Future
	@NotNull
	private LocalDateTime dateFinEncheres;
	@Min(value = 1, message = "La mise à prix doit être au minimum de 1")
	private int prixInitial;
	private int prixVente;
	private String etatVente;
	
	private Retrait lieuRetrait;
	private Categorie CategorieArticle;
	private Utilisateur acheteur;
	private Utilisateur vendeur;
	private List<Enchere> encheres;
	private String photo;
	

	

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
			Categorie categorieArticle, Utilisateur acheteur, Utilisateur vendeur, String photo) {
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
		this.photo = photo;
		
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
	
	public String getCheminImage() {
		return cheminImage;
	}

	public void setCheminImage(String cheminImage) {
		this.cheminImage = cheminImage;
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
	
	public String getDateDebut() {
		return this.dateDebutEnchere.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	public String getHeureDebut() {
		return this.dateDebutEnchere.format(DateTimeFormatter.ofPattern("HH'h'mm"));
	}
	
	public String getDateFin() {
		return this.dateFinEncheres.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	public String getHeureFin() {
		return this.dateFinEncheres.format(DateTimeFormatter.ofPattern("HH'h'mm"));
	}
	

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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
