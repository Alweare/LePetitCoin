package fr.eni.enchere.bo;

import jakarta.validation.constraints.NotBlank;

public class Retrait {
	
	@NotBlank
	private String rue;
	@NotBlank
	private String code_postal;
	@NotBlank
	private String ville;
	
		
	public Retrait() {

	}
	public Retrait(String rue, String code_postal, String ville) {
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
		return String.format("%s%n%s%n%s", rue, code_postal, ville);
	}
	
	

}
