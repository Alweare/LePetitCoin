package fr.eni.enchere.bo;

import java.util.Objects;

public class Categorie {

	private int id;
	private String libelle;
	
	public Categorie() {

	}

	public Categorie(int id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}

	public int getId() {
		return id;
	}

	public void setId(int noCategorie) {
		this.id = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
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
		Categorie other = (Categorie) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return String.format("Categorie [noCategorie=%s, libelle=%s]", id, libelle);
	}
	
}
