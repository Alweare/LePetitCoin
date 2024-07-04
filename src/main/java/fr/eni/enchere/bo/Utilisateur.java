package fr.eni.enchere.bo;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

public class Utilisateur implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	@NotBlank(message ="Saisir pseudo")
	private String pseudo;
	@NotBlank(message ="saisir nom")
	private String nom;
	@NotBlank(message ="saisir prenom")
	private String prenom;
	@NotBlank(message ="saisir email")
	private String email;
	@NotBlank(message ="saisir telephone")
	private String telephone;
	@NotBlank(message ="saisir rue")
	private String rue;
	@NotBlank(message ="saisir code postal")
	private String codePostal;
	@NotBlank(message ="saisir ville")
	private String ville;
	@NotBlank(message ="saisir mot de passe")
	private String motDePasse;
	private int credit;
	private boolean administrateur;
	
	
	
	public Utilisateur() {
	}


	public Utilisateur(int id, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur) {
		this.id = id;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
	}


	public int getId() {
		return id;
	}


	public void setId(int noUtlisateur) {
		this.id = noUtlisateur;
	}


	public String getPseudo() {
		return pseudo;
	}


	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getRue() {
		return rue;
	}


	public void setRue(String rue) {
		this.rue = rue;
	}


	public String getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public String getMotDePasse() {
		return motDePasse;
	}


	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}


	public int getCredit() {
		return credit;
	}


	public void setCredit(int credit) {
		this.credit = credit;
	}


	public boolean isAdministrateur() {
		return administrateur;
	}


	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}


	@Override
	public String toString() {
		return String.format(
				"Utilisateur [noUtlisateur=%s, pseudo=%s, nom=%s, prenom=%s, email=%s, telephone=%s, rue=%s, codePostal=%s, ville=%s, motDePasse=%s, credit=%s, administrateur=%s]",
				id, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit,
				administrateur);
	}
	
	
	
	
	
}
