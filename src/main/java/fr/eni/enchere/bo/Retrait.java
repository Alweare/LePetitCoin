package fr.eni.enchere.bo;

public class Retrait {
	
	private int idArticle;
	private String rue;
	private String code_postal;
	private String ville;
	
		
	public Retrait() {

	}
	public Retrait(int id, String rue, String code_postal, String ville) {
		this.idArticle = id;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}

	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
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
